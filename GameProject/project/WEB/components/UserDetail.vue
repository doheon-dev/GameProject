<template>
  <div class="UserDetail">
    <el-main>
      <h1 style="margin-top:3%;">{{ g_pageName }}</h1>
      <el-form ref="form" :model="g_user" label-width="100px" size="mini" style="width: 50%; text-align: left; margin-left: 25%; margin-top: 6%; margin-bottom: 8%;">
        <el-form-item v-if="g_showModifyUser == 'Y'" style="margin-bottom: 56%;" label="비밀번호">
          <el-col :span="20">
            <el-input type="text" placeholder="비밀번호는 8자리~16자리 이내, 특수문자를 포함하여주세요." @keyup.enter="passwordCheck" v-model="g_user.passwordCheck" show-password></el-input>
          </el-col>
          <el-col class="line" :span="2"></el-col>
          <el-col :span="4">
            <el-button @click="passwordCheck" size='mini'>확인</el-button>
          </el-col>
        </el-form-item>
        <el-form-item label="유저 아이디" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.userid }}</b></span>
        </el-form-item>
        <el-form-item label="유저 이름" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.username }}</b></span>
        </el-form-item>
        <el-form-item label="휴대폰 번호" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.mobileno }}</b></span>
        </el-form-item>
        <el-form-item label="이메일 주소" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.emailaddr }}</b></span>
        </el-form-item>
        <el-form-item label="유저 순위" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.userrank }}</b></span>
        </el-form-item>
        <el-form-item label="승리 횟수" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.wincount }}</b></span>
        </el-form-item>
        <el-form-item label="패배 휫수" v-if="g_showModifyUser != 'Y'">
          <span><b>{{ g_user.losecount }}</b></span>
        </el-form-item>
      </el-form>
      <el-form>
        <el-form-item size="small" style="text-align: right; margin-right:10%; margin-bottom:8%;">
          <el-button v-if="g_showModifyUser != 'Y'" type="primary" @click="onSubmit">개인정보수정</el-button>
          <el-button v-if="g_showModifyUser == 'Y'" @click="cancel">취소</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </div>
</template>

<script>
/* eslint-disable */
/**
 * @see onSubmit
 * @see passwordCheck
 * @see cancel
 */
export default {
  name: 'UserDetail',
  data () {
    return {
      g_pageName: '개인정보관리',
      g_showModifyUser: '',
      g_user: {
        userid: '',
        username: '',
        password: '',
        mobileno: '',
        emailaddr: '',
        userrank: '',
        wincount: '',
        losecount: '',
        passwordCheck: ''
      }
    }
  },
  created () {
    this.setParameter();
  },
  methods: {
    onSubmit () {
      this.g_showModifyUser = 'Y';
    },
    passwordCheck () {
      if (this.g_user.password === this.g_user.passwordCheck) {
        this.$router.push({name: 'UserDetailModify', params: this.g_user});
      } else if (this.g_user.password !== this.g_user.passwordCheck) {
        this.$message({ type: 'warning', message: '비밀번호를 확인하여 주십시오.' });
        return false;
      }
    }, 
    setParameter(){
      var userInfo = JSON.parse(sessionStorage.getItem('userInfo'));
      this.g_user.userid = userInfo.userid;
      this.g_user.password = userInfo.password;
      var params = { userid: this.g_user.userid, password: this.g_user.password }
      this.$http.get('/api/userInfo', {params: params}).then(response => {
        if (response.status === 200) {
          this.g_user.userid = response.data.result.userid;
          this.g_user.username = response.data.result.username;
          this.g_user.password = response.data.result.password;
          this.g_user.mobileno = response.data.result.mobileno;
          this.g_user.emailaddr = response.data.result.emailaddr;
          var userrank = response.data.result.userrank;
          this.g_user.userrank = userrank + '위';
          var wincount = response.data.result.wincount;
          this.g_user.wincount = wincount + '번';
          var losecount = response.data.result.losecount;
          this.g_user.losecount = losecount + '번';
        }
      })
    },
    cancel () {
      this.$message({ type: 'info', message: '취소되었음.' });
      this.g_showModifyUser = 'N';
    }
  }
}
</script>
<style scoped>
  .el-main { background-color:rgb(243, 242, 242); }
</style>
