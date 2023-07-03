import Vue from 'vue'
import App from './App.vue'
import elementUi from 'element-ui'
import router from './router'
import 'element-ui/lib/theme-chalk/index.css'
import axios from './utils/http'

// 引入富文本组件
import QuillEditor from 'vue-quill-editor'
// 引入富文本组件样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(QuillEditor)


Vue.prototype.$http = axios
Vue.config.productionTip = false
Vue.use(elementUi)

new Vue({
	router,
	render: h => h(App), 
}).$mount('#app')
