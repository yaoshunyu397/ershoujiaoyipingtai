<template>
  <div class="main-content">
    <el-card style="width: 50%; margin: 30px auto">
      <el-form :model="user" label-width="80px" style="padding-right: 20px">
        <div style="margin: 15px; text-align: center">
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <img v-if="user.avatar" :src="user.avatar" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="银行卡号" prop="bankCard">
          <el-input v-model="user.bankCard" placeholder="银行卡号"></el-input>
        </el-form-item>
        <el-form-item label="账户余额" prop="balance">
          <el-input v-model="user.balance" placeholder="账户余额" disabled style="width: 80%"></el-input>
          <el-button type="primary" @click="rechargeDialogVisible = true" style="margin-left: 10px">充值</el-button>
        </el-form-item>
        <el-form-item label="积分" prop="points">
          <el-input v-model="user.points" placeholder="积分" disabled></el-input>
        </el-form-item>
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>

    <el-dialog title="账户充值" :visible.sync="rechargeDialogVisible" width="30%">
      <el-form>
        <el-form-item label="充值金额">
          <el-input v-model.number="rechargeAmount" placeholder="请输入充值金额" type="number"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="rechargeDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleRecharge">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rechargeDialogVisible: false,
      rechargeAmount: null
    }
  },
  methods: {
    update() {
      // 检查银行卡号是否有变化
      const oldUser = JSON.parse(localStorage.getItem('xm-user') || '{}')
      const bankCardChanged = oldUser.bankCard !== this.user.bankCard
      
      // 如果银行卡号变化，重置余额为2000
      if (bankCardChanged) {
        this.user.balance = 2000
      }

      this.$request.put('/user/update', this.user).then(res => {
        if (res.code === '200') {
          this.$message.success('保存成功')
          localStorage.setItem('xm-user', JSON.stringify(this.user))
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAvatarSuccess(response, file, fileList) {
      // 确保头像URL是绝对路径
      let avatarUrl = response.data
      if (!avatarUrl.startsWith('http')) {
        avatarUrl = this.$baseUrl + avatarUrl
      }
      this.$set(this.user, 'avatar', avatarUrl)
      // 立即更新localStorage中的头像
      localStorage.setItem('xm-user', JSON.stringify(this.user))
    },
    handleRecharge() {
      if (!this.rechargeAmount || this.rechargeAmount <= 0) {
        this.$message.error('请输入正确的充值金额')
        return
      }
      this.$request.post('/user/recharge', {
        id: this.user.id,
        balance: this.rechargeAmount
      }).then(res => {
        if (res.code === '200') {
          this.$message.success('充值成功')
          this.user.balance = res.data.balance
          localStorage.setItem('xm-user', JSON.stringify(this.user))
          this.rechargeDialogVisible = false
          this.rechargeAmount = null
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
::v-deep .el-form-item__label {
  font-weight: bold;
}
::v-deep .el-upload {
  border-radius: 50%;
}
::v-deep .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
::v-deep .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>