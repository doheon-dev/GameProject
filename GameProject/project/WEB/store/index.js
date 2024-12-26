/* eslint-disable */
import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

const enhanceAccessToeken = () => {
    var userInfo = sessionStorage.getItem('userInfo')
    if(!!userInfo){
      axios.defaults.headers.common['Authorization'] = 'Bearer' + userInfo;
    }
}
enhanceAccessToeken()

export default new Vuex.Store({
  state: {
    accessToken: null
  },
  mutations: {
    LOGIN (state, accessToken) {
      sessionStorage.setItem('userInfo', accessToken)
      axios.defaults.headers.common['Authorization'] = 'Bearer' + accessToken;
    },
    LOGOUT (state) {
      delete sessionStorage.removeItem('userInfo')
      axios.defaults.headers.common['Authorization'] = undefined
    }
  },
  actions: {
    LOGIN ({commit}, accessToken) {
      commit('LOGIN', accessToken)
    },
    LOGOUT ({commit}) {
      commit('LOGOUT')
    }
  }
})

