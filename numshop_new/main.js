//程序入口文件--引入Vue项目，创建实列并且可以携带插件，不过这里不用去定义路由
// #ifndef VUE3
import Vue from 'vue'
import App from './App'
import api from '@/common/api.js'
// import '@/static/icon_4041412/iconfont.css'
Vue.prototype.$api = api
Vue.config.productionTip = false
App.mpType = 'app'
console.log('开始');
const app = new Vue({
    ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
import App from './App.vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif