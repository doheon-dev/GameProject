/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'
import Main from '@/components/Main'
import Login from '@/components/Login'
import UserDetail from '@/components/UserDetail'
import UserDetailModify from '@/components/UserDetailModify'
import Ranking from '@/components/Ranking'
import MenuController from '@/components/MenuController'
import UserController from '@/components/UserController'
import WaitingRoom from '@/components/WaitingRoom'
import GameRoom from '@/components/GameRoom'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
  var userInfo = sessionStorage.getItem('userInfo')
  if (userInfo)
    return next()
  next('/')
}

export default new Router({
  mode: 'history',
  routes: [{
      path: '/',
      name: 'Login',
      component: Login
    }, {
      path: '/Main',
      name: 'Main',
      component: Main,
      beforeEnter: requireAuth()
    }, {
      path: '/UserDetail',
      name: 'UserDetail',
      component: UserDetail,
      beforeEnter: requireAuth()
    }, {
      path: '/Ranking',
      name: 'Ranking',
      component: Ranking,
      beforeEnter: requireAuth()
    }, {
      path: '/WaitingRoom',
      name: 'WaitingRoom',
      component: WaitingRoom,
      beforeEnter: requireAuth()
    }, {
      path: '/GameRoom',
      name: 'GameRoom',
      component: GameRoom,
      beforeEnter: requireAuth()
    }, {
      path: '/MenuController',
      name: 'MenuController',
      component: MenuController,
      beforeEnter: requireAuth()
    }, {
      path: '/UserController',
      name: 'UserController',
      component: UserController,
      beforeEnter: requireAuth()
    }, {
      path: '/UserDetailModify',
      name: 'UserDetailModify',
      component: UserDetailModify,
      beforeEnter: requireAuth()
    }]
})