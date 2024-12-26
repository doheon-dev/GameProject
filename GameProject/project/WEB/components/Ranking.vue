<template>
  <div class="Ranking">
    <el-main>
      <el-row type="flex" style="margin-left: 20%; margin-bottom: 2%; margin-top:6%">
      <el-col>
        <div style="width: 100px" text-align="center">
          <img src="../../src/assets/copper.png" style="width: 100px;">
          <p style="text-align:center;"><b>{{g_firstRanker}}</b></p>
        </div>
      </el-col>
      <el-col>
        <div style="width: 100px; margin-top:-10%">
          <img src="../../src/assets/gold.png" style="width: 100px;">
          <p style="text-align:center;"><b>{{g_secondRanker}}</b></p>
        </div>
      </el-col>
      <el-col>
        <div style="width: 100px;">
          <img src="../../src/assets/silver.png" style="width: 100px;">
          <p style="text-align:center;"><b>{{g_thirdRanker}}</b></p>
        </div>
      </el-col>
  </el-row>
  <el-row>
    <el-col style="width: 100%; margin-bottom: 9%;" align="center">
      <el-table :data="g_tableData" :empty-text="g_emptyText" style="width: 59%; margin-bottom: 2%;" height="350" align="center">
        <el-table-column prop="userrank" label="순위" width="180"></el-table-column>
        <el-table-column prop="userid" label="유저"></el-table-column>
        <el-table-column prop="wincount" label="승리 횟수" width="180"></el-table-column>
        <el-table-column prop="losecount" label="패배 횟수" width="180"></el-table-column>
      </el-table>
    </el-col>
  </el-row>
  </el-main>
</div>
</template>

<script>
/* eslint-disable */
/**
 * @see rankList
 */
export default {
  name: 'Ranking',
  data () {
    return {
      g_emptyText :"",
      g_tableData: [],
      g_firstRanker: '',
      g_secondRanker: '',
      g_thirdRanker: '',
    }
  },
  created () {
    this.rankList();
  },
  methods: {
    rankList(){
      this.$http.get('/api/rankList').then(response => {
        if (response.status == 200) {
          this.g_tableData = response.data.rankList;
          this.g_firstRanker = this.g_tableData[0].userid;
          this.g_secondRanker = this.g_tableData[1].userid;
          this.g_thirdRanker = this.g_tableData[2].userid;
        } else {
          this.g_emptyText = "목록이 없습니다.";
        }
      })
    }
  }
}
</script>
<style scoped>
  .el-table {
    width: 100%;
    border: 1px solid #aaa6a6;
  }
  .el-main{
    background-image: url('../../src/assets/background1.jpg');
    background-size:86%
      /* background-color:rgb(243, 242, 242); */
  }
</style>
