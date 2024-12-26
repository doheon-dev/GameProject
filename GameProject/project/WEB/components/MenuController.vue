<template>
  <div class="MenuController">
    <el-main>
      <h1 style="margin-top:3%;">{{ g_pageName }}</h1>
        <el-form style="margin-top:7%;">
        <el-form-item>
          <el-col :span="10" style="margin-left:55%;">
            <el-input placeholder="검색할 메뉴명을 입력하세요." v-model="g_searchText">
              <el-button slot="append" icon="el-icon-search" @click="searchInfo"></el-button>
            </el-input>
          </el-col>
        </el-form-item>
        </el-form>
        <el-table :data="g_tableData" height="350" ref="menuTable" :empty-text="g_emptyText" style="width: 95%; margin-bottom:3%; margin-left:2%;">
          <el-table-column label="메뉴명" width="300" prop="menuid"></el-table-column>
          <el-table-column label="메뉴명" width="300" prop="menuname"></el-table-column>
          <el-table-column label="메뉴링크" width="350" prop="menulink"></el-table-column>
          <el-table-column label="메뉴설명" width="350" prop="menumean"></el-table-column>
          <el-table-column align="right">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="menuModify(scope.$index, scope.row)">수정</el-button>
              <el-button size="mini" type="danger" @click="menuDelete(scope.$index, scope.row)">삭제</el-button>
            </template>
          </el-table-column>
        </el-table>
      <el-form>
        <el-form-item size="small" style="text-align: right; margin-right:3%; margin-bottom:8%;">
          <el-button type="primary" @click="addMenu">메뉴 추가</el-button>
        </el-form-item>
      </el-form>
    </el-main>
    <popup_menuDetail ref="popup_menuDetail" ></popup_menuDetail>
    <popup_menuMake ref="popup_menuMake" @setAddMenu="setAddMenu" ></popup_menuMake>
  </div>
</template>

<script>
/* eslint-disable */
/**
 * @see searchInfo
 * @see menuDelete
 * @see listSerch
 * @see MenuModify
 * @see addMenu
 */
import popup_menuDetail from '@/popup/popup_menuDetail'
import popup_menuMake from '@/popup/popup_menuMake'
export default {
  name: 'MenuController',
  components: { popup_menuDetail, popup_menuMake },
  data () {
    return {
      g_pageName: '메뉴 관리',
      g_searchText: '',
      g_tableData: [],
      g_emptyText :"목록이 없습니다."
    }
  },
  created () {
    this.listSerch();
  },
  methods: {
    setAddMenu(){
      this.listSerch();
    },
    searchInfo () {
      if (this.g_searchText == '') {
        this.listSerch();
        this.$message({ type: 'info', message: '검색할 항목을 입력하세요.' });
      } else if (this.g_searchText != '') {
        var params = { menuname: this.g_searchText };
        this.$http.get('/api/menuSearch', { params: params }).then(response => {
          if (response.status === 200) {
            this.g_tableData = response.data.menuList;
            if(this.g_tableData.length == 0){
              this.g_emptyText = "검색 결과가 없습니다.";
            }
          } else {
            this.$message({ type: 'info', message: '검색 항목을 가져올 수 없습니다.' });
          }
        })
      }
    },
    menuDelete (index, row) {
      this.$confirm('메뉴를 삭제하시겠습니까?', '메뉴 삭제', {
        confirmButtonText: '확인', cancelButtonText: '취소', type: 'warning'
      }).then(() => {
        var params = { menuid: row.menuid };
        this.$http.delete('/api/deleteMenu',{params: params}).then(response => {
          if (response.status == 200) {
            this.listSerch();
            this.$message({ type: 'success', message: '삭제 되었습니다.' });
          } else {
            this.$message({ type: 'success', message: '삭제에 실패하였습니다.' });
          }
        })
      }).catch(() => {
        this.$message({ type: 'info', message: '취소 되었습니다.' });
      })
    },
    listSerch() {
      this.$http.get('/api/menuList').then(response => {
        if (response.status == 200) {
          this.g_tableData = response.data.menuList;
        }
      })
    },
    menuModify (index, row) {
      var p_params = row;
      this.$refs.popup_menuDetail.setVisble(p_params);
    },
    addMenu () {
      this.$refs.popup_menuMake.setVisble();
    }
  }
}
</script>
<style scoped>
.el-main{
    background-color:rgb(243, 242, 242);
}
 /* { background-color:rgb(243, 242, 242); } */
</style>
