<template>
  <div class="Main">
    <el-main>
      <div style="margin-top:20%; margin-bottom:26%">
        <router-link to="/WaitingRoom">
          <el-button style="height:80px; width:450px;" round><h1>{{ g_game }}</h1></el-button>
        </router-link>
      </div>
    </el-main>
  </div>
</template>
<script>
/* eslint-disable */
export default {
  name: 'Main',
  data () {
    return {
      g_game: 'GAME START'
    }
  },
  created () {
  },
  methods: {
  }
}
</script>
<style scoped>
  .el-main{
    background-image: url('../../src/assets/background1.jpg');
    background-size:86%
    /* background-color:rgb(243, 242, 242); */
  }
</style>
