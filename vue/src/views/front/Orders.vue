<template>
  <div class="main-content">
    <div style="width: 90%; background-color: white; margin: 30px auto; border-radius: 20px">
      <div style="padding-bottom: 10px">
        <div style="font-size: 18px; color: #000000FF; line-height: 80px; border-bottom: #cccccc 1px solid;">
          <div style="margin-left: 20px">我的订单（{{ ordersData.length }} 个）</div>
        </div>
        <div style="margin: 20px 0; padding: 0 50px">
          <div class="table">
            <el-table :data="ordersData" strip>
              <el-table-column label="商品图片" width="120px">
                <template v-slot="scope">
                  <el-image style="width: 80px; height: 60px; border-radius: 3px" v-if="scope.row.goodsImg"
                            :src="scope.row.goodsImg" :preview-src-list="[scope.row.goodsImg]"></el-image>
                </template>
              </el-table-column>
              <el-table-column prop="orderId" label="订单编号"></el-table-column>
              <el-table-column prop="goodsName" label="商品名称" :show-overflow-tooltip="true">
                <template v-slot="scope">
                  <a :href="'/front/detail?id=' + scope.row.goodsId">{{scope.row.goodsName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="businessName" label="店铺名称">
                <template v-slot="scope">
                  <a :href="'/front/business?id=' + scope.row.businessId">{{scope.row.businessName}}</a>
                </template>
              </el-table-column>
              <el-table-column prop="goodsPrice" label="商品价格">
                <template v-slot="scope">
                  {{scope.row.goodsPrice}} / {{scope.row.goodsUnit}}
                </template>
              </el-table-column>
              <el-table-column prop="num" label="商品数量"></el-table-column>
              <el-table-column prop="price" label="订单总价"></el-table-column>
              <el-table-column prop="username" label="收货人"></el-table-column>
              <el-table-column prop="useraddress" label="收货地址"></el-table-column>
              <el-table-column prop="phone" label="联系电话"></el-table-column>
              <el-table-column prop="status" label="订单状态"></el-table-column>
              <el-table-column label="操作" align="center" width="300">
                <template v-slot="scope">
                  <el-button size="mini" type="primary" v-if="scope.row.status === '待收货'" plain @click="updateStatus(scope.row, '已完成')">确认收货</el-button>
                  <el-button size="mini" type="primary" v-if="scope.row.status === '已完成'" plain @click="addComment(scope.row)">评价</el-button>
                  <el-button size="mini" type="warning" v-if="scope.row.status === '已完成'" plain @click="applyReturn(scope.row)">申请退货</el-button>
                  <el-button size="mini" type="info" v-if="scope.row.status === '退货中'" plain disabled>申请退货中</el-button>
                  <el-button size="mini" type="danger" v-if="scope.row.status === '退货失败'" plain @click="showRejectReason(scope.row)">退货失败</el-button>
                  <el-button size="mini" type="primary" v-if="scope.row.status === '已评价'" plain >已评价</el-button>
                  <el-button size="mini" type="primary" v-if="scope.row.status === '退货成功'" plain @click="addComment(scope.row)">退货评价</el-button>
                  <el-button size="mini" type="danger" plain @click="del(scope.row.id)"> 删除 </el-button>
                </template>
              </el-table-column>
            </el-table>

            <div class="pagination" style="margin-top: 20px">
              <el-pagination
                  background
                  @current-change="handleCurrentChange"
                  :current-page="pageNum"
                  :page-sizes="[5, 10, 20]"
                  :page-size="pageSize"
                  layout="total, prev, pager, next"
                  :total="total">
              </el-pagination>
            </div>
          </div>
        </div>
      </div>
    </div>
    <el-dialog title="请输入评价内容" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form :model="form" label-width="100px" style="padding-right: 50px" :rules="rules" ref="formRef">
        <el-form-item label="评价内容" prop="username">
          <el-input type="textarea" v-model="form.content" placeholder="请输入评价内容"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ordersData: [],
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      form: {},
      fromVisible: false,
      rules: {
        content: [
          { required: true, message: '请输入评价内容', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.loadOrders(1)
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadOrders(pageNum) {
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/orders/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.user.id
        },
        paramsSerializer: params => {
          return Object.entries(params)
            .map(([key, value]) => `${key}=${value}`)
            .join('&')
        }
      }).then(res => {
        if (res.code === '200') {
          this.ordersData = res.data?.list.map(item => {
            return {
              ...item,
              rejectReason: item.rejectReason || '' // 确保rejectReason字段存在
            }
          })
          this.total = res.data?.total
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navTo(url) {
      location.href = url
    },
    del(id) {
      this.$request.delete('/orders/delete/' + id).then(res => {
        if (res.code === '200') {
          this.$message.success('删除成功')
          this.loadOrders(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleCurrentChange(pageNum) {
      this.loadOrders(pageNum)
    },
    updateStatus(row, status) {
      this.form = row
      this.form.status = status
      this.$request.put('/orders/update', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          // 刷新订单列表
          this.loadOrders(1)
          // 刷新用户余额
          this.$request.get('/user/selectById/' + this.user.id).then(res => {
            if (res.code === '200') {
              this.user = res.data
              localStorage.setItem('xm-user', JSON.stringify(res.data))
            }
          })
        } else {
          this.$message.error(res.msg)
        }
      }).catch(err => {
        if (err.response && err.response.data.code === '5007') {
          this.$message.error('余额不足，请充值！')
        } else {
          this.$message.error(err.response?.data?.msg || '操作失败')
        }
      })
    },
    addComment(row) {
      this.fromVisible = true
      this.form = row
    },
    save() {
      let data = {
        userId: this.user.id,
        businessId: this.form.businessId,
        goodsId: this.form.goodsId,
        content: this.form.content,
      }
      this.$request.post('/comment/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('评价成功')
          this.fromVisible = false
          this.updateStatus(this.form, '已评价')
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    applyReturn(row) {
      // 计算当前时间与交易完成时间的差值(小时)
      const now = new Date()
      const transactionTime = new Date(row.transactionTime)
      const hours = (now - transactionTime) / (1000 * 60 * 60)
      
      if (hours > 24) {
        this.$message.error('您已超过24小时退货时限')
        return
      }
      
      this.$confirm('确定要申请退货吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.updateStatus(row, '退货中')
      })
    },
    showRejectReason(row) {
      if (!row.rejectReason) {
        this.$message.warning('暂无驳回理由')
        return
      }
      this.$alert(row.rejectReason, '退货驳回理由', {
        confirmButtonText: '确定'
      }).catch(() => {
        // 用户点击取消，不做任何操作
      })
    }
  }
}
</script>