package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Cart;
import com.example.common.enums.ResultCodeEnum;
import com.example.entity.User;
import com.example.entity.Business;
import com.example.mapper.GoodsMapper;
import com.example.entity.Orders;
import com.example.mapper.CartMapper;
import com.example.mapper.OrdersMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.BusinessMapper;
import com.example.exception.CustomException;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：订单业务处理
 */
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private BusinessMapper businessMapper;

    /**
     * 新增
     */
    public void add(Orders orders) {
        orders.setOrderId(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        
        // 计算订单总金额
        double totalAmount = 0;
        for (Cart cart : orders.getCartData()) {
            totalAmount += cart.getNum() * cart.getGoodsPrice();
        }
        
        // 检查买家余额是否足够
        User user = userMapper.selectById(orders.getUserId());
        BigDecimal userBalance = BigDecimal.valueOf(user.getBalance());
        BigDecimal orderAmount = BigDecimal.valueOf(totalAmount);
        
        // 积分抵扣逻辑(每100积分抵扣1元)
        int points = user.getPoints() != null ? user.getPoints() : 0;
        int pointsToUse = (points / 100) * 100; // 可抵扣的积分(100的整数倍)
        BigDecimal pointsDiscount = BigDecimal.valueOf(pointsToUse / 100.0); // 积分抵扣金额
        
        // 计算实际支付金额
        BigDecimal actualPayment = orderAmount.subtract(pointsDiscount);
        if (actualPayment.compareTo(BigDecimal.ZERO) < 0) {
            actualPayment = BigDecimal.ZERO;
        }
        
        if (userBalance.compareTo(actualPayment) < 0) {
            throw new CustomException(ResultCodeEnum.BALANCE_NOT_ENOUGH_ERROR);
        }
        
        // 扣除买家余额
        user.setBalance(userBalance.subtract(actualPayment).doubleValue());
        
        // 更新用户积分(扣除已用积分，增加新积分)
        user.setPoints(points - pointsToUse + (int) totalAmount);
        userMapper.updateById(user);

        for (Cart cart : orders.getCartData()) {
            Orders dbOrders = new Orders();
            BeanUtils.copyProperties(orders, dbOrders);
            dbOrders.setGoodsId(cart.getGoodsId());
            dbOrders.setBusinessId(cart.getBusinessId());
            dbOrders.setNum(cart.getNum());
            dbOrders.setPrice(cart.getNum() * cart.getGoodsPrice());
            ordersMapper.insert(dbOrders);

            // 把购物车里对应的商品删掉
            cartMapper.deleteById(cart.getId());
            
            // 更新商品销量
            goodsMapper.updateCount(cart.getGoodsId(), cart.getNum());
        }
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Orders orders) {
        // 获取原始订单信息
        Orders originalOrder = ordersMapper.selectById(orders.getId());
        
        // 如果是确认收货操作(status变为"已完成")
        if ("已完成".equals(orders.getStatus())) {
            // 设置交易完成时间
            orders.setTransactionTime(new Date());
            
            // 获取商家信息
            Business business = businessMapper.selectById(originalOrder.getBusinessId());
            
            // 根据商家等级计算实际收款比例
            double rate;
            String level = business.getLevel();
            if ("5".equals(level)) {
                rate = 0.99;
            } else if ("4".equals(level)) {
                rate = 0.9925;
            } else if ("3".equals(level)) {
                rate = 0.995;
            } else if ("2".equals(level)) {
                rate = 0.998;
            } else if ("1".equals(level)) {
                rate = 0.999;
            } else {
                rate = 1.0;
            }
            
            // 计算商家实际收款金额(使用BigDecimal避免精度问题)
            BigDecimal amount = BigDecimal.valueOf(originalOrder.getPrice())
                .multiply(BigDecimal.valueOf(rate));
            
            // 更新商家余额
            business.setBalance(BigDecimal.valueOf(business.getBalance().add(amount).doubleValue()));
            businessMapper.updateById(business);
        }
        // 如果是退货成功操作
        else if ("退货成功".equals(orders.getStatus())) {
            // 验证原始状态必须是"退货中"
            if (!"退货中".equals(originalOrder.getStatus())) {
                throw new CustomException(ResultCodeEnum.STATUS_ERROR);
            }
            
            // 获取用户和商家信息
            User user = userMapper.selectById(originalOrder.getUserId());
            Business business = businessMapper.selectById(originalOrder.getBusinessId());
            
            // 退款金额(原价)
            BigDecimal refundAmount = BigDecimal.valueOf(originalOrder.getPrice());
            
            // 增加用户余额
            user.setBalance(BigDecimal.valueOf(user.getBalance()).add(refundAmount).doubleValue());
            userMapper.updateById(user);
            
            // 减少商家余额
            business.setBalance(BigDecimal.valueOf(business.getBalance().subtract(refundAmount).doubleValue()));
            businessMapper.updateById(business);
        }
        // 如果是退货失败操作
        else if ("退货失败".equals(orders.getStatus())) {
            // 验证原始状态必须是"退货中"
            if (!"退货中".equals(originalOrder.getStatus())) {
                throw new CustomException(ResultCodeEnum.STATUS_ERROR);
            }
            // 只需要更新状态和驳回理由，不需要修改余额
        }
        
        ordersMapper.updateById(orders);
    }

    /**
     * 根据ID查询
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Orders> selectAll(Orders orders) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            orders.setUserId(currentUser.getId());       // 用户只查看自己的订单
        }
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            orders.setBusinessId(currentUser.getId());    // 商家只查看自己店铺的订单
        }
        return ordersMapper.selectAll(orders);
    }

    /**
     * 分页查询
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            orders.setUserId(currentUser.getId());       // 用户只查看自己的订单
        }
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            orders.setBusinessId(currentUser.getId());    // 商家只查看自己店铺的订单
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }
}
