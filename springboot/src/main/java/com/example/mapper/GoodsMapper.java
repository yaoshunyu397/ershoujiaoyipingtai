package com.example.mapper;

import com.example.entity.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 商品接口
 */
public interface GoodsMapper {

    /**
     * 新增
     */
    int insert(Goods goods);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Goods goods);

    /**
     * 根据ID查询
     */
    Goods selectById(Integer id);

    /**
     * 查询所有
     */
    List<Goods> selectAll(Goods goods);

    /**
     * 通过分类查询商品
     */
    @Select("select * from goods where type_id = #{id}")
    List<Goods> selectByTypeId(Integer id);

    /**
     * 根据商家Id查询该商家的所有商品
     */
    @Select("select * from goods where business_id = #{id}")
    List<Goods> selectByBusinessId(Integer id);

    @Select("select * from goods order by count desc, price asc limit 10")
    List<Goods> selectTop10();

    @Select("select * from goods where name like concat('%', #{name}, '%')")
    List<Goods> selectByName(String name);

    /**
     * 更新商品销量
     */
    int updateCount(@Param("id") Integer id, @Param("count") Integer count);
}
