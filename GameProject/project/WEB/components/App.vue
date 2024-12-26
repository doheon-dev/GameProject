<template>
  <div id="app" class="container">
    <el-container direction="vertical" style="100vh; margin-top:-4%;">
      <el-row>
        <el-menu :default-active="g_activeIndex" class="el-menu-demo" mode="horizontal">
          <el-menu-item index="1" style="width: 15%;">
            <span @click="routerLink('Main')">
              <img src="../src/assets/logoImage.png" style="width: 20%;" @click="routerLink('Main')">
              <img src="../src/assets/towergameLogo.png" style="width: 85%;">
            </span>
          </el-menu-item>
          <el-menu-item index="2">
            <el-link icon="el-icon-user" @click="routerLink('Ranking')">
              랭킹
            </el-link>
          </el-menu-item>
          <el-menu-item index="3">
            <el-link icon="el-icon-user" @click="routerLink('UserController')">
              사용자관리
            </el-link>
          </el-menu-item>
          <el-menu-item index="4">
            <el-link icon="el-icon-menu" @click="routerLink('MenuController')">
              메뉴관리
            </el-link>
          </el-menu-item>
          <el-menu-item index="5" style="float: right;">
            <span @click="logout">
              로그아웃
            </span>
          </el-menu-item>
          <el-menu-item index="6" style="float: right;">
            <el-link icon="el-icon-user-solid" @click="routerLink('UserDetail')">
              사용자
            </el-link>
          </el-menu-item>
        </el-menu>
      </el-row>
      <transition name="fade" mode="out-in">
        <router-view></router-view>
      </transition>
    </el-container>
  </div>
</template>
<script>
/* eslint-disable */
/**
 * @see checkId
 * @see routerLink
 * @see logout
 */
export default {
  name: 'App',
  data () {
    return {
      g_loginCheck: false,
      g_userInfo: '',
      g_activeIndex: '0'
    }
  },
  created () {
    this.checkId();
  },
  computed: {
  },
  methods: {
    checkId(){
      this.g_userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
      if (this.g_userInfo == null) {
        this.g_loginCheck = false;
      }else if(this.g_userInfo != null){
        this.g_loginCheck = true;
      }
    },
    routerLink(type){
      this.checkId();
      if (this.g_loginCheck == true) {
        this.$router.push({ name: type });
      } else if (!this.g_loginCheck != true) {
        this.$router.push('/');
      }
    },
    logout(){
      if (this.g_loginCheck == true) {
        this.g_loginCheck = false;
        this.$store.dispatch('LOGOUT').then(() => 
        this.$message({ type: 'success', message: '로그아웃되었습니다.' }),
        this.$router.push('/'));
      }
    }
  }
}
</script>
<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
  .el-main {
      width: 100%;
      height: 100%;
      background-color:rgb(243, 242, 242);
      background-size:50%;
      font-size: 110%;
  }
</style>
