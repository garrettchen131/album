import Vue from 'vue'
import Router from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import qs from 'qs'
import Global from '../../static/config/global'


// 引入Element UI 组件
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(Router)
Vue.use(ElementUI)
// Vue.use(VueAxios, axios)

Vue.prototype.$axios = axios
Vue.prototype.$qs = qs
Vue.prototype.GLOBAL = Global
Vue.prototype.HOST = '/api'

//防止访问重复路由报错
const routerPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(error=> error)
}


// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// axios.defaults.baseURL = '/api'
axios.defaults.withCredentials = true


//引入路由组件
import login from '@/components/Entrance/Login';
import register from '@/components/Entrance/Register';
import self from '@/components/AlbumSets/Self';
import share from '@/components/AlbumSets/Share';
import main from '@/components/Main';
import album from '@/components/Album/Detail';
import set from '@/components/Album/Set';
import home from '@/components/Home';
import addAlbum from '@/components/Popup/AddAlbum';
import changePass from '@/components/Popup/ChangePassword';
import addPhotos from '@/components/Popup/AddPhotos';
import editAlbum from '@/components/Popup/EditAlbumInfo';

import test from '@/components/TestAxios'

// 暴露路由对象
export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home/login',
      component: home
    },
    {
      path: '/main',
      component: main,
      children: [
        {
          path: 'self',
          name: 'self',
          component: self
        },
        {
          path: 'share',
          name: 'share',
          component: share
        }
      ]
    },
    {
      path: '/album',
      name: 'album',
      component: album
    },
    {
      path: '/home',
      component: home,
      children: [
        {
          path: 'login',
          component: login
        },
        {
          path: 'register',
          component: register
        }
      ]
    },
    {
      path: '/addAlbum',
      component: addAlbum
    },
    {
      path: '/changePass',
      component: changePass
    },
    {
      path: '/addPhotos',
      component: addPhotos
    },
    {
      path: '/editAlbum',
      component: editAlbum
    },
    {
      path: '/set',
      name: 'set',
      component: set
    },
    {
      path: '/test',
      component: test
    }
  ]
})
