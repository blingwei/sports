// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from "./store"
import el from "element-ui/src/locale/lang/el";

import VueAMap from 'vue-amap'
Vue.use(VueAMap);
VueAMap.initAMapApiLoader({
  key: '********************',//自己在高德地图平台上的key值
plugin: ['AMap.Autocomplete', 'AMap.PlaceSearch', 'AMap.Scale', 'AMap.OverView', 'AMap.ToolBar', 'AMap.MapType', 'AMap.PolyEditor', 'AMap.CircleEditor','AMap.ControlBar','AMap.MouseTool','AMap.GeometryUtil','AMap.DistrictSearch'],//需要的高德地图插件，需要什么插件添加什么插件（这里只是其中一部分）
// 默认高德 sdk 版本为 1.4.4
v: '1.4.4',
uiVersion:'1.0.11'
});

//设置反向代理
var axios = require('axios');
axios.defaults.baseURL = 'http://localhost:8441/api';
axios.defaults.withCredentials = true;

Vue.prototype.$axios = axios;
Vue.config.productionTip = false;

Vue.use(ElementUI);

router.beforeEach((to, from, next) => {

  if (store.state.user.username) {
    axios.get('/authentication').then(resp => {
      if (resp.data) {
        console.log("then" + resp.data)
        next({
          query: {time: from.path}
        })
      } else {
        store.commit('logout')
        next({
          path: '/login',
          query: {redirect: to.fullPath}
        })
      }
    }).catch(resp => {
      store.commit('logout')
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    })

  } else {
    if (to.path == "/login") {
      next();
    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath}
      })
    }
  }


});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
