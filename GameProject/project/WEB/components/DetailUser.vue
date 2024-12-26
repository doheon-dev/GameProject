<template>
    <div>
        <h1>{{user.name}} : {{user.number}}</h1>
    </div>
</template>

<script>
export default {
  name: 'DetailUser',
  data () {
    return {
      user: {}
    }
  },
  created () {
    // var id = this.$route.params.id
    this.$http.get(`/api/users/${this.$route.params.id}`).then(response => {
      this.user = response.data.response
    })
  }
}
</script>
<style scoped>
</style>