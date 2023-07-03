import axios from 'axios'
import qs from 'qs'

import { Message } from 'element-ui'
import Vue from 'vue'

// loading框设置局部刷新，且所有请求完成后关闭loading框
let loading
function startLoading() {
  loading = Vue.prototype.$loading({
    lock: true,
    text: 'Loading...',
    target: document.querySelector('.loading-area')//设置加载动画区域
  })
}
function endLoading() {
  loading.close()
}
 
//声明一个对象用于存储请求个数
let needLoadingRequestCount = 0
function showFullScreenLoading() {
    if (needLoadingRequestCount === 0) {
        startLoading()
    }
    needLoadingRequestCount++
};
function tryHideFullScreenLoading() {
    if (needLoadingRequestCount <= 0) return
    needLoadingRequestCount--
    if (needLoadingRequestCount === 0) {
      endLoading()
    }
};
// 超时时间
axios.defaults.timeout = 30000
// 跨域请求，允许保存cookie
axios.defaults.withCredentials = false
//axios.defaults.headers = { 'Content-Type': 'application/json; charset=utf-8' }
axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded'
axios.defaults.transformRequest = data => qs.stringify(data)
axios.interceptors.request.use(config => {
	showFullScreenLoading()
	return config
}, error => {
	tryHideFullScreenLoading()
	return Promise.reject(error)
})
axios.defaults.baseURL = 'http://localhost:8080/flora/'
/**
 * 响应拦截
 */
axios.interceptors.response.use(response => {
	tryHideFullScreenLoading()
	return response.data
}, error => {
	tryHideFullScreenLoading()
	return Promise.reject(error)
})
export default axios
