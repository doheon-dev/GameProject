<template>
  <div class="popup_userRegist">
    <el-dialog
      title="회원 가입"
      width="55%"
      center
      :visible.sync="g_dialogVisible"
      :before-close="cancel"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :modal="true"
      :append-to-body="true">

      <el-row>
        <el-col>
          <table class="popupFormTable">
            <colgroup>
              <col width="10%">
              <col width="20%">
            </colgroup>
            <tbody>
              <tr>
                <th><span>* 아이디</span></th>
                <td>
                  <span>
                    <el-col :span="20">
                      <el-input size="mini" placeholder="아이디 중복체크를 해주세요." @keyup.enter="idCheck()" v-model="g_user.userid"></el-input>
                    </el-col>
                    <el-col class="line" :span="2"></el-col>
                    <el-col :span="4">
                      <el-button @click="idCheck" size='mini'>중복체크</el-button>
                    </el-col>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>* 비밀번호</span></th>
                 <td>
                  <span>
                    <el-col :span="20">
                      <el-input type="text" maxlength="15" size="mini" placeholder="비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요." v-model="g_user.password" show-password autocomplete="off" @keyup.native="passwordFormCheck()"></el-input>
                      <span style="color:red" v-if="g_passwordCheck != true" >* 비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요.</span>
                    </el-col>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>* 비밀번호 확인</span></th>
                 <td>
                  <span>
                    <el-col :span="20">
                      <el-input type="text" maxlength="15" size="mini" placeholder="비밀번호는 영문 8자리~15자리 이내, 특수문자를 포함하여주세요." v-model="g_secondPassword" show-password autocomplete="off"></el-input>
                      <span style="color:red" v-if="g_passwordCorrect != true" >* 비밀번호가 동일하지 않습니다.</span>
                    </el-col>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>* 이름</span></th>
                <td>
                  <span>
                    <el-input size="mini" maxlength="15" v-model="g_user.username"></el-input>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>휴대폰번호</span></th>
                <td>
                  <span>
                    <el-input size="mini" v-if="g_mobileCheck == true" placeholder="010-****-****" v-model="g_user.mobileno"></el-input>
                    <el-input size="mini" v-if="g_mobileCheck == false" placeholder="010-****-****" v-model="g_user.mobileno" style="border: 1px solid #ff0000;"></el-input>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>이메일 주소</span></th>
                <td>
                  <span>
                    <el-input size="mini" v-if="g_emailCheck == true" placeholder="******@*****.com" v-model="g_user.emailaddr"></el-input>
                    <el-input size="mini" v-if="g_emailCheck == false" placeholder="******@*****.com" v-model="g_user.emailaddr" style="border: 1px solid #ff0000;"></el-input>
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>
      <el-form>
        <el-form-item size="small" style="text-align: right; margin-top:5%; margin-right:10%;">
          <el-button type="primary" @click="commit">등록</el-button>
          <el-button @click="cancel">취소</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
/**
 * @see setVisble
 * @see idCheck
 * @see commit
 * @see passwordFormCheck
 * @see mobileFormCheck
 * @see emailFormCheck
 * @see cancel
 */
export default {
  name: 'popup_userRegist',
  data () {
    return {
      g_dialogVisible: false,
      g_idFormCheck: false,
      g_passwordCheck: false,
      g_mobileCheck: true,
      g_emailCheck: true,
      g_secondPassword: '',
      g_passwordCorrect: true,
      g_user: {
        userid: '',
        password: '',
        username: '',
        mobileno: '',
        emailaddr: ''
      },
      g_rank: {
        wincount: 0,
        losecount: 0,
        userrank: 0
      }
    }
  },
  created () {
  },
  methods: {
    setVisble: function (type) {
      this.g_dialogVisible = true;
    },
    /**
     * 아이디 중복체크 
     */ 
    idCheck () {
      if (this.g_user.userid === '') {
        this.$message({ type: 'warning', message: '아이디를 입력해 주세요.' });
      } else if (this.g_user.userid !== '') {
        var params = {userid: this.g_user.userid};
        this.$http.get('/api/idCheck', {params:params}).then(response => {
          var totalCnt = response.data.totalCnt;
          if (totalCnt > 0) {
            this.$message({ type: 'info', message: '중복된 아이디가 있습니다.' });
          } else if (totalCnt === 0) {
            this.g_idFormCheck = true;
            this.$message({ type: 'success', message: '사용가능한 아이디 입니다.' });
          }
        })
      }
    },
    commit () {
      this.mobileFormCheck();
      this.emailFormCheck();
      if (this.g_idFormCheck === false) {
        this.$message({ type: 'warning', message: '아이디 중복체크를 해주세요.' });
      } else if (this.g_passwordCheck !== true) {
        this.$message({ type: 'warning', message: '비밀번호가 형식에 맞지 않습니다.' });
      } else if (this.g_user.password !== this.g_secondPassword) {
        this.$message({ type: 'warning', message: '비밀번호가 동일하지 않습니다.' });
        this.g_passwordCorrect = false;
      } else if (this.g_user.userid === '') {
        this.$message({ type: 'warning', message: '아이디 입력은 필수입니다.' });
      } else if (this.g_user.password === '') {
        this.$message({ type: 'warning', message: '비밀번호 입역은 필수입니다.' });
      } else if (this.g_user.username === '') {
        this.$message({ type: 'warning', message: '이름 입력은 필수입니다.' });
      } else if (this.g_mobileCheck === false) {
        this.$message({ type: 'warning', message: '올바른 휴대폰번호를 입력해 주세요.' });
      } else if (this.g_emailCheck === false) {
        this.$message({ type: 'warning', message: '올바른 이메일주소를 입력해 주세요.' });
      } else {
        var params = Object.assign(this.g_rank, this.g_user)
        this.$http.post('/api/signUp', params).then(response => {
          if (response.status === 200) {
            this.$message({ type: 'success', message: '등록되었습니다.' });
            this.g_dialogVisible = false;
          }
        })
      }
    },
    passwordFormCheck () {
      if (this.g_user.password) {
        var password = this.g_user.password;
        var f_passwordReg = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
        var testing = f_passwordReg.test(password);
        this.g_passwordCheck = testing;
      }
    },
    mobileFormCheck () {
      if (this.g_user.mobileno) {
        var f_mobileReg = /^\d{3}-\d{3,4}-\d{4}$/;
        this.g_mobileCheck = f_mobileReg.test(this.g_user.mobileno);
      } else if (this.g_user.mobileno === '') {
        this.g_mobileCheck = true;
      }
    },
    emailFormCheck () {
      if (this.g_user.emailaddr) {
        var f_emailReg = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        this.g_emailCheck = f_emailReg.test(this.g_user.emailaddr);
      } else if (this.g_user.emailaddr === '') {
        this.g_emailCheck = true;
      }
    },
    initParameter(){
      this.g_dialogVisible = false;
      this.g_idFormCheck = false;
      this.g_passwordCheck = false;
      this.g_mobileCheck = true;
      this.g_emailCheck = true;
      this.g_secondPassword = '';
      this.g_passwordCorrect = true;
      this.g_user = {};
    },
    cancel: function (done) {
      this.$message({ type: 'info', message: '회원가입이 취소 되었습니다.' });
      this.initParameter();
    }
  }
}
</script>
<style scoped>
  .el-main { background-color:rgb(243, 242, 242); }
  table {
    width: 90%;
    /* border: 1px solid #444444; */
  }
  td {
    /* border: 1px solid #444444; */
     padding: 6px;
  }
  th {
    font-weight: normal;
    padding: 6px;
  }
</style>
