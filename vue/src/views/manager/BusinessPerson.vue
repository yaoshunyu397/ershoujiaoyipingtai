<template>
  <div>
    <el-card style="width: 50%">
      <el-form :model="user" label-width="100px" style="padding-right: 50px">
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
        <el-form-item label="店铺名" prop="name">
          <el-input v-model="user.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input type="textarea" v-model="user.description" placeholder="店铺介绍"></el-input>
        </el-form-item>
        <el-form-item label="审核" prop="status">
          <el-input v-model="user.status" placeholder="审核状态" disabled></el-input>
        </el-form-item>
        <el-form-item label="账户金额" prop="balance">
          <el-input v-model="user.balance" placeholder="账户金额" disabled></el-input>
        </el-form-item>
        <el-form-item label="商家等级" prop="level">
          <el-input v-model="user.level" placeholder="商家等级" disabled></el-input>
        </el-form-item>
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AdminPerson",
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}')
    }
  },
  created() {
    this.loadBusinessData();
  },
  methods: {
    update() {
      // 保存当前的用户信息到数据库
      this.$request.put('/business/update', this.user).then(res => {
        if (res.code === '200') {
          // 成功更新
          this.$message.success('保存成功')

          // 更新浏览器缓存里的用户信息
          localStorage.setItem('xm-user', JSON.stringify(this.user))

          // 触发父级的数据更新
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadBusinessData() {
      this.$request.get('/business/selectById/' + this.user.id).then(res => {
        if (res.code === '200') {
          this.user = res.data;
          localStorage.setItem('xm-user', JSON.stringify(this.user));
        }
      });
    },
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.$set(this.user, 'avatar', response.data)
    },
  }
}
</script>

<style scoped>
::v-deep(.el-form-item__label) {
  font-weight: bold;
}
::v-deep(.el-upload) {
  border-radius: 50%;
}
::v-deep(.avatar-uploader .el-upload) {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}
::v-deep(.avatar-uploader .el-upload:hover) {
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