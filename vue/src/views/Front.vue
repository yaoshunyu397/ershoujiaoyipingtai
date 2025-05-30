<template>
  <div>
    <!--头部-->
    <div class="front-header">
      <div class="front-header-left" @click="navTo('/front/home')">
        <div class="title">二手交易平台</div>
      </div>
      <div class="front-header-center" style="text-align: right">
        <el-button
          v-if="user.role === 'ADMIN' || user.role === 'BUSINESS'"
          @click="navTo('/home')"
          style="margin-right: 15px"
        >
          返回{{ user.role === 'ADMIN' ? '管理' : '商家' }}后台
        </el-button>
        <el-input style="width: 250px" placeholder="请输入商品名称" v-model="name"></el-input>
        <el-button type="primary" style="margin-left: 5px" @click="search">搜索</el-button>
      </div>
      <div class="front-header-right">
        <div v-if="!user || !user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.avatar" @click="navTo('/front/userPerson')" alt="">
              <div style="margin-left: 10px">
                <span>{{ user.name }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div style="text-decoration: none" @click="navTo('/front/userPerson')">个人信息</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div style="text-decoration: none" @click="navTo('/front/userPassword')">修改密码</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" @update:user="updateUser" />
    </div>
  </div>

</template>

<script>

export default {
  name: "FrontLayout",

  data () {
    return {
      top: '',
      notice: [],
      name: '',
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
    }
  },

  mounted() {
    this.loadNotice()
  },
  methods: {
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notice = res.data
        let i = 0
        if (this.notice && this.notice.length) {
          this.top = this.notice[0].content
          setInterval(() => {
            this.top = this.notice[i].content
            i++
            if (i === this.notice.length) {
              i = 0
            }
          }, 2500)
        }
      })
    },
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    navTo(url) {
      location.href = url
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },
    search() {
      let name = this.name ? this.name : ''
      location.href = '/front/search?name=' + name
    }
  }

}
</script>

<style scoped>
  @import "@/assets/css/front.css";
</style>