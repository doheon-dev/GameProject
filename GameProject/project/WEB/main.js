import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import axios from 'axios'
import io from 'socket.io-client'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import moment from 'moment'

const socket = io('http://localhost:3001')

Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$socket = socket
Vue.use(ElementUI)
Vue.prototype.$moment = moment

/* eslint-disable */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})


