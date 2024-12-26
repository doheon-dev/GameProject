<template>
  <div class="UserDetailModify">
    <el-main>
      <h1 style="margin-top:3%;">{{ g_pageName }}</h1>
      <el-form ref="form" :model="g_userInfo" label-width="100px" size="mini" style="width: 60%; text-align: left; margin-left: 25%; margin-top: 6%; margin-bottom: 7%;">
        <el-form-item label="유저 아이디">
          <el-col :span="13">
            <span><b>{{ g_userInfo.userid }}</b></span>
          </el-col>
        </el-form-item>
        <el-form-item label="비밀번호">
          <el-col :span="13">
            <el-input type="text" maxlength="15" size="mini" placeholder="비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요." v-model="g_firstPassword" show-password autocomplete="off" @keyup.native="passwordFormCheck()"></el-input>
            <span style="color:red" v-if="g_passwordCheck != true" >* 비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요.</span>
          </el-col>
        </el-form-item>
        <el-form-item label="비밀번호 확인">
          <el-col :span="13">
            <el-input type="text" maxlength="15" size="mini" placeholder="비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요." v-model="g_secondPassword" show-password autocomplete="off"></el-input>
            <span style="color:red" v-if="g_passwordCorrect != true" >* 비밀번호가 동일하지 않습니다.</span>
          </el-col>
        </el-form-item>
        <el-form-item label="유저 이름" maxlength="15" prop="username">
          <el-col :span="13">
            <el-input v-model="g_userInfo.username"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="휴대폰 번호" prop="mobileno">
          <el-col :span="13">
            <el-input size="mini" v-if="g_mobileCheck == true" placeholder="010-****-****" v-model="g_userInfo.mobileno"></el-input>
            <el-input size="mini" v-if="g_mobileCheck == false" placeholder="010-****-****" v-model="g_userInfo.mobileno" style="border: 1px solid #ff0000;"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="이메일 주소" prop="emailaddr">
          <el-col :span="13">
            <el-input size="mini" v-if="g_emailCheck == true" placeholder="******@*****.com" v-model="g_userInfo.emailaddr"></el-input>
            <el-input size="mini" v-if="g_emailCheck == false" placeholder="******@*****.com" v-model="g_userInfo.emailaddr" style="border: 1px solid #ff0000;"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="유저 순위">
          <span><b>{{ g_userInfo.userrank }}</b></span>
        </el-form-item>
        <el-form-item label="승리 횟수">
          <span><b>{{ g_userInfo.wincount }}</b></span>
        </el-form-item>
        <el-form-item label="패배 휫수">
          <span><b>{{ g_userInfo.losecount }}</b></span>
        </el-form-item>
      </el-form>
      <el-form>
        <el-form-item size="small" style="text-align: right; margin-bottom:9%; margin-right:10%;">
          <el-button @click="leaveUser">회원탈퇴</el-button>
          <el-button type="primary" @click="commit">완료</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </div>
</template>

<script>
/* eslint-disable */
/**
 * @see commit
 * @see leaveUser
 * @see passwordFormCheck
 * @see mobileFormCheck
 * @see emailFormCheck
 */
export default {
  name: 'UserDetailModify',
  data () {
    return {
      g_pageName: '개인정보관리 수정',
      g_userInfo: {
        userid: '',
        username: '',
        password: '',
        mobileno: '',
        emailaddr: '',
        userrank: '',
        wincount: '',
        losecount: ''
      },
      g_firstPassword: '',
      g_secondPassword: '',
      g_passwordCheck: true,
      g_passwordCorrect: true,
      g_mobileCheck: true,
      g_emailCheck: true
    }
  },
  created () {
    this.g_userInfo = this.$router.history.current.params;
  },
  methods: {
    commit () {
      this.$confirm('회원 정보를 수정하시겠습니까?', '개인정보수정', {
        confirmButtonText: '확인', cancelButtonText: '취소', type: 'warning'
      }).then(() => {
        this.mobileFormCheck();
        this.emailFormCheck();
          if (this.g_passwordCheck !== true) {
            this.$message({ type: 'warning', message: '비밀번호가 형식에 맞지 않습니다.' });
          } else if (this.g_firstPassword !== this.g_secondPassword) {
            this.$message({ type: 'warning', message: '비밀번호가 동일하지 않습니다.' });
            this.g_passwordCorrect = false;
          } else if (this.g_userInfo.username == '') {
            this.$message({ type: 'warning', message: '이름은 필수 입력값입니다.' });
          } else if (this.g_mobileCheck === false) {
            this.$message({ type: 'warning', message: '올바른 휴대폰번호를 입력해 주세요.' });
          } else if (this.g_emailCheck === false) {
            this.$message({ type: 'warning', message: '올바른 이메일주소를 입력해 주세요.' });
          } else {
            if (this.g_firstPassword != '') {
              this.g_userInfo.password = this.g_firstPassword;
            }
            this.$http.put('/api/updateUser', this.g_userInfo).then(response => {
              if (response.status === 200) {
                this.$message({ type: 'success', message: '수정 되었습니다.' });
                this.$router.push('/UserDetail');
              } else {
                this.$message({ type: 'info', message: '수정 실패 되었습니다.' });
              }
            })
          }
        }).catch(() => {
          this.$message({ type: 'info', message: '수정 취소 되었습니다.' });
      })
    },
    leaveUser () {
      this.$confirm('회원탈퇴를 하면 개인정보가 사라집니다. 탈퇴하시겠습니까?', '회원탈퇴', {
        confirmButtonText: '확인', cancelButtonText: '취소', type: 'warning'
      }).then(() => {
        var params = { userid: this.g_userInfo.userid };
        this.$http.delete('/api/leaveUser', {params: params}).then(response => {
          if (response.data == "success") {
            this.$store.dispatch('LOGOUT').then(() => 
              this.$message({ type: 'success', message: '회원탈퇴 되었습니다.' }),
              this.$router.push('/'));
          } else {
            this.$message({ type: 'success', message: '회원탈퇴를 실패했습니다.' });
          }
        })
      }).catch(() => {
        this.$message({ type: 'info', message: '회원탈퇴 취소 되었습니다.' });
      })
    },
    passwordFormCheck () {
      if (this.g_firstPassword !== '') {
        var f_passwordReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
        this.g_passwordCheck = f_passwordReg.test(this.g_firstPassword);
      }
    },
    mobileFormCheck () {
      if (this.g_userInfo.mobileno) {
        var f_mobileReg = /^\d{3}-\d{3,4}-\d{4}$/;
        this.g_mobileCheck = f_mobileReg.test(this.g_userInfo.mobileno);
      } else if (this.g_userInfo.mobileno === '') {
        this.g_mobileCheck = true;
      }
    },
    emailFormCheck () {
      if (this.g_userInfo.emailaddr) {
        var f_emailReg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        this.g_emailCheck = f_emailReg.test(this.g_userInfo.emailaddr);
      } else if (this.g_userInfo.emailaddr === '') {
        this.g_emailCheck = true;
      }
    }
  }
}
</script>
<style scoped>
  .el-main { background-color:rgb(243, 242, 242); }
</style>
