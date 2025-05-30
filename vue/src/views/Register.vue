<template>
  <div class="container">
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎注册</div>
      <el-form :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password  v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPass">
          <el-input prefix-icon="el-icon-lock" placeholder="请确认密码" show-password  v-model="form.confirmPass"></el-input>
        </el-form-item>
        <el-form-item>
          <el-select v-model="form.role" placeholder="请选择角色" style="width: 100%">
            <el-option label="商家" value="BUSINESS"></el-option>
            <el-option label="用户" value="USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <div style="display: flex; align-items: center">
            <el-input prefix-icon="el-icon-picture" placeholder="请输入验证码" v-model="captchaCode" style="width: 60%"></el-input>
            <div style="width: 40%; padding-left: 10px">
              <img :src="captchaUrl" @click="refreshCaptcha" style="width: 100%; height: 40px; cursor: pointer" title="点击刷新验证码"/>
            </div>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white" @click="register">注 册</el-button>
        </el-form-item>
        <div style="display: flex; align-items: center">
          <div style="flex: 1"></div>
          <div style="flex: 1; text-align: right">
            已有账号？请 <a href="/login">登录</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    // 验证码校验
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.form.password) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      form: {},
      captchaUrl: '', // 验证码图片URL
      captchaCode: '', // 验证码输入
      rules: {
        username: [
          { required: true, message: '请输入账号', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    this.generateCaptcha();
  },
  methods: {
    generateCaptcha() {
      // 创建canvas绘制验证码
      const canvas = document.createElement('canvas');
      canvas.width = 120;
      canvas.height = 40;
      const ctx = canvas.getContext('2d');
      
      // 随机背景色
      ctx.fillStyle = this.randomColor(180, 240);
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      
      // 随机4位验证码
      let code = '';
      const chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
      for (let i = 0; i < 4; i++) {
        const char = chars[Math.floor(Math.random() * chars.length)];
        code += char;
        // 绘制文字
        ctx.font = 'bold 20px Arial';
        ctx.fillStyle = this.randomColor(50, 160);
        ctx.fillText(char, 10 + i * 25, 25);
      }
      
      // 干扰线
      for (let i = 0; i < 5; i++) {
        ctx.strokeStyle = this.randomColor(40, 180);
        ctx.beginPath();
        ctx.moveTo(Math.random() * canvas.width, Math.random() * canvas.height);
        ctx.lineTo(Math.random() * canvas.width, Math.random() * canvas.height);
        ctx.stroke();
      }
      
      this.captchaUrl = canvas.toDataURL('image/png');
      return code;
    },
    randomColor(min, max) {
      const r = Math.floor(Math.random() * (max - min) + min);
      const g = Math.floor(Math.random() * (max - min) + min);
      const b = Math.floor(Math.random() * (max - min) + min);
      return `rgb(${r},${g},${b})`;
    },
    refreshCaptcha() {
      this.generateCaptcha();
    },
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过
          this.$request.post('/register', this.form).then(res => {
            if (res.code === '200') {
              // 自动登录
              this.$request.post('/login', this.form).then(loginRes => {
                if (loginRes.code === '200') {
                  localStorage.setItem("xm-user", JSON.stringify(loginRes.data))
                  if(loginRes.data.role === 'USER') {
                    this.$router.push('/front/home')
                  } else {
                    this.$router.push('/home')
                  }
                  this.$message.success('注册并登录成功')
                } else {
                  this.$message.error(loginRes.msg)
                }
              })
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.container {
  height: 100vh;
  overflow: hidden;
  background-color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}
a {
  color: #2a60c9;
}
</style>