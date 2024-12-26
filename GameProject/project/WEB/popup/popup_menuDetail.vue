<template>
  <div class="popup_menuDetail">
    <el-dialog
      title="메뉴정보 수정"
      width="55%"
      center
      :visible.sync="g_dialogVisible"
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
                <th><span>메뉴 아이디</span></th>
                <td>
                  <span>
                    <el-input v-model="g_menu.menuid"></el-input>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>메뉴 이름</span></th>
                <td>
                  <span>
                    <el-input v-model="g_menu.menuname"></el-input>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>링크</span></th>
                 <td>
                  <span>
                    <el-input v-model="g_menu.menulink"></el-input>
                  </span>
                </td>
              </tr>
              <tr>
                <th><span>메뉴 설명</span></th>
                <td>
                  <span>
                    <el-input v-model="g_menu.menumean"></el-input>
                  </span>
                </td>
              </tr>
            </tbody>
          </table>
        </el-col>
      </el-row>
      <el-form>
        <el-form-item size="small" style="text-align: right; margin-right:10%; margin-top:5%;">
          <el-button type="primary" @click="confirm">완료</el-button>
          <el-button @click="cancel">취소</el-button>
        </el-form-item>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */
/**
 * @see confirm
 * @see setVisble
 * @see cancel
 */
export default {
  name: 'popup_menuDetail',
  data () {
    return {
      g_dialogVisible: false,
      g_menu: {
        menuid: '',
        menuname: '',
        menulink: '',
        menumean: ''
      },

    }
  },
  created () {
  },
  methods: {
    confirm: function (done) {
        if (this.g_menu.menuid == '') {
          this.$message({ type: 'warning', message: '메뉴 아이디는 필수 값입니다.' });
        } else if (this.g_menu.menuname == '') {
          this.$message({ type: 'warning', message: '메뉴 이름은 필수 값입니다.' });
        } else if (this.g_menu.menulink == '') {
          this.$message({ type: 'warning', message: '메뉴 링크가 필수 값입니다.' });
        } else {
        this.$confirm('메뉴를 수정하시겠습니까?', '메뉴정보 수정', {
          confirmButtonText: '확인', cancelButtonText: '취소', type: 'warning'
        }).then(() => {
          this.$http.put('/api/updateMenu', this.g_menu).then(response => {
            if (response.status === 200) {
              this.$message({ type: 'success', message: '수정 되었습니다.' });
              this.cancel();
            } else {
              this.$message({ type: 'warning', message: '수정 실패 되었습니다.' });
            }
          })
        }).catch(() => {
          this.$message({ type: 'info', message: '취소 되었습니다.' });
        })
      }
    },
    setVisble: function (data) {
      this.g_menu = data;
      this.g_dialogVisible = true;
    },
    cancel: function (done) {
      this.g_dialogVisible = false;
    }
  }
}
</script>
<style scoped>
.el-main { background-color:rgb(243, 242, 242); }
  table {
    width: 80%;
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
