<template>
  <div id="Login" class="container">
    <el-main style="margin-top:-4%;">
      <img src="../../src/assets/logoImage.png" style="width: 150px; height: auto; margin-top: 12%;">
        <h1>{{ g_title }}</h1>
        <el-form label-width="100px" size="small" style="width: 50%; text-align: center; margin-left: 33%; margin-top: 3%; margin-bottom: 2%;">
        <el-form-item label="아이디" prop="loginid">
          <el-col :span="13">
            <el-input  placeholder="아이디 입력" name="userid" v-model="g_userid"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="비밀번호" prop="password">
          <el-col :span="13">
            <el-input placeholder="비밀번호 입력" name="password" v-model="g_password" show-password></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <el-form>
        <el-form-item style="text-align: center; margin-right:0%; margin-bottom:16%;">
          <el-button type="info" name="login" @click="login">로그인</el-button>
          <el-button type="info" @click="regist">회원가입</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  <popup_userRegist ref="popup_userRegist"></popup_userRegist>
  </div>
</template>
<script>
/* eslint-disable */
/**
 * @see login
 * @see regist
 */
import popup_userRegist from '@/popup/popup_userRegist'
export default {
  name: 'Login',
  components: { popup_userRegist },
  data () {
    return {
      g_title: 'TOWER GAME',
      g_userid: '',
      g_password: ''
    }
  },
  created () {
  },
  methods: {
    login () {
      if (this.g_userid === '' || this.g_password === '') {
         if (this.g_userid === '' && this.g_password !== '') {
          this.$message({ type: 'warning', message: '아이디를 입력해주세요.' });
        } else if (this.g_password === '' && this.g_userid !== '') {
          this.$message({ type: 'warning', message: '비밀번호를 입력해주세요.' });
        } else if(this.g_userid === '' && this.g_password === ''){
          this.$message({ type: 'warning', message: '아이디를 입력해주세요.' });
        } 
      } else if (this.g_userid != '' && this.g_password != '') {
        var params = { userid: this.g_userid, password: this.g_password };
        this.$http.get('/api/loginCheck', {params : params}).then(response => {
          if (response.status === 200) {
            this.$http.get('/api/userMenu', {params : params}).then(response => {
              var menuList = response.data.menuList;
            })
            var accessToken = JSON.stringify(response.data.userinfo);
            this.$store.dispatch('LOGIN', accessToken);
            var result = response.data.result;
            if (result === 1) {
              this.$router.push({ name: 'Main' });
              this.$message({ type: 'success', message: '오늘도 행복한 하루를 보내세요!' });
            } else {
              this.$message({ type: 'warning', message: '아이디 또는 비밀번호를 확인해주세요.' });
            }
          }
        })
      }
    },
    regist () {
      this.$refs.popup_userRegist.setVisble();
    }
  }
}
</script>
<style>
  .el-main {
      width: 100%;
      height: 100%;
      background-size:50%;
      font-size: 110%;
      /* background-image: url('../../src/assets/mainBackground.gif'); */
  }
  .el-form{
    font-size: 12pt;
  }
</style>