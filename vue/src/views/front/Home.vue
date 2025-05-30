<template>
  <div class="main-content">
    <div style="height:10px; background-color: #ecf0f8;"></div>
    <div style="display: flex;">
      <div style="width: 66%; margin-left: 17%; background-color: white;">
        <div style="color: rgb(254,1,55); margin: 15px 0 15px 18px; font-weight: bold; font-size: 16px;">分类</div>
        <div style="display: flex; margin: 0 25px; height: 550px;">
          <div style="flex: 2;">
            <div style="display: flex; color: #666666; margin: 14px 0;" v-for="item in typeData">
              <img :src="item.img" alt="" style="width: 20px; height: 20px;">
              <div style="margin-left: 10px; font-size: 14px;"><a href="#" @click="navTo('/front/type?id=' + item.id)">{{ item.name }}</a></div>
            </div>
          </div>
          <div style="flex: 5; margin-top: 15px">
            <div>
              <el-carousel height="300px" style="border-radius: 10px">
                <el-carousel-item v-for="(item, index) in carousel_top" :key="index">
                  <img :src="item.img" alt="" style="width: 100%; height: 300px; border-radius: 10px; cursor: pointer;" @click="navTo('/front/detail?id=' + item.id)">
                </el-carousel-item>
              </el-carousel>
            </div>
            <div style="margin-top: 30px; display: flex">
              <div style="flex: 1">
                <el-carousel height="300px" style="border-radius: 10px">
                  <el-carousel-item v-for="(item, index) in carousel_left" :key="index">
                    <img :src="item.img" alt="" style="width: 100%; height: 200px; border-radius: 10px; cursor: pointer;" @click="navTo('/front/detail?id=' + item.id)">
                  </el-carousel-item>
                </el-carousel>
              </div>
              <div style="flex: 1; margin-left: 5px">
                <el-carousel height="300px" style="border-radius: 10px">
                  <el-carousel-item v-for="(item, index) in carousel_right" :key="index">
                    <img :src="item.img" alt="" style="width: 100%; height: 200px; border-radius: 10px; cursor: pointer;" @click="navTo('/front/detail?id=' + item.id)">
                  </el-carousel-item>
                </el-carousel>
              </div>
            </div>
          </div>
          <div style="flex: 3; background-color: #f3f3f3ff; margin-top: 15px; margin-left: 15px;border-radius: 10px;">
            <div style="text-align: center; margin-top: 30px;">
              <template v-if="user">
                <img :src="user.avatar" style="width: 80px; height: 80px; border-radius: 50%;">
                <div style="margin-top: 10px">Hi，{{user.name}}</div>
              </template>
              <template v-else>
                <img src="@/assets/imgs/icon.png"  style="width: 80px; height: 80px; border-radius: 50%;">
                <div style="margin-top: 10px">Hi，请登录</div>
              </template>
            </div>
            <div style="margin-top: 20px; padding: 0 15px">
              <img src="@/assets/imgs/right.png" alt="" style="height: 150px; width: 100%; border-radius: 20px">
            </div>
            <div style="margin: 20px 10px 10px 10px; width: 250px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis">
              <i class="el-icon-bell"></i>
              <span style="font-weight: bold">公告</span>
              <span style="color: #666666;">：{{ top }}</span>
            </div>
            <div style="display: flex; margin-top: 50px">
              <div style="flex: 1; text-align: center">
                <a href="#" @click.prevent="navTo('/front/collect')">
                  <img src="@/assets/imgs/收藏.png" alt="" style="height: 25px; width: 25px">
                  <div>我的收藏</div>
                </a>
              </div>
              <div style="flex: 1; text-align: center">
                <a href="#" @click.prevent="navTo('/front/address')">
                  <img src="@/assets/imgs/地址.png" alt="" style="height: 25px; width: 25px">
                  <div>我的地址</div>
                </a>
              </div>
              <div style="flex: 1; text-align: center">
                <a href="#" @click.prevent="navTo('/front/cart')">
                  <img src="@/assets/imgs/购物车.png" alt="" style="height: 25px; width: 25px">
                  <div>我的购物车</div>
                </a>
              </div>
              <div style="flex: 1; text-align: center">
                <a href="#" @click.prevent="navTo('/front/orders')">
                  <img src="@/assets/imgs/订单.png" alt="" style="height: 25px; width: 25px">
                  <div>我的订单</div>
                </a>
              </div>
            </div>
          </div>
        </div>
        <div style="margin: 40px 0 0 15px; height: 40px; font-size: 20px; color: #333; width: 130px; font-weight: bold; line-height: 40px; text-align: center; border-radius: 20px">热卖商品</div>
        <div style="margin: 10px 5px 0 5px">
          <el-row>
            <el-col :span="5" v-for="(item, index) in goodsData" :key="item.id">
              <img @click="navTo('/front/detail?id=' + item.id)" :src="item.img" alt="" style="width: 160px; height: 120px; object-fit: cover; border-radius: 10px; border: #cccccc 1px solid; cursor: pointer;">
              <div style="margin-top: 10px; font-weight: 500; font-size: 16px; width: 180px; color: #000000FF; text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">{{item.name}}</div>
              <div style="margin-top: 5px; font-size: 20px; color: #FF5000FF">￥ {{item.price}} / {{item.unit}}</div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="right"></div>
    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      user: (() => {
        try {
          const user = JSON.parse(localStorage.getItem('xm-user') || '{}');
          return {
            avatar: user.avatar || require('@/assets/imgs/icon.png'),
            name: user.name || '游客',
            id: user.id || null
          };
        } catch (e) {
          return {
            avatar: require('@/assets/imgs/icon.png'),
            name: '游客',
            id: null
          };
        }
      })(),
      typeData: [],
      top: null,
      notice: [],
      goodsData: [],
      carousel_top: [
        {img: require('@/assets/imgs/carousel-1.png'), id: 23},
        {img: require('@/assets/imgs/carousel-2.png'), id: 24},
      ],
      carousel_left: [
        {img: require('@/assets/imgs/carousel-3.png'), id: 16},
        {img: require('@/assets/imgs/carousel-4.png'), id: 22},
      ],
      carousel_right: [
        {img: require('@/assets/imgs/carousel-6.png'), id: 18},
        {img: require('@/assets/imgs/carousel-5.png'), id: 15},
      ],
    }
  },
  mounted() {
    // mounted在dom元素渲染之后进行加载
    this.loadType()
    this.loadNotice()
    this.loadGoods()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadType() {
      this.$request.get('/type/selectAll').then(res => {
        if(res.code === '200') {
          this.typeData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
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
    loadGoods() {
      this.$request.get('/goods/selectTop10').then(res => {
        if (res.code === '200') {
          this.goodsData = res.data.slice(0, 10) // 确保只取10个商品
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    navTo(url) {
      if (!this.user.id) {
        this.$message.warning('请先登录')
        return
      }
      this.$router.push(url)
    },

  }
}
</script>

<style scoped>
.main-content {
  min-height: 100vh;
  background-size: 100%;
  background-image: url('@/assets/imgs/img.png');
}
.right {
  width: 17%;
  background-repeat: no-repeat;
  background-image: url("@/assets/imgs/right-img.png");
}
.el-col-5 {
  width: 20%;
  max-width: 20%;
  padding: 10px 10px;
}
</style>
