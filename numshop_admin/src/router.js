import Vue from 'vue'
import Router from 'vue-router'

const Login = () => import('./components/Login.vue')
const Home = () => import('./components/Home.vue')
const Welcome = () => import('./components/Welcome.vue')

const Details = () => import('./components/Details.vue')
const Orders = () => import('./components/Orders.vue')
const User = () => import('./components/User.vue')
const Product = () => import('./components/Product.vue')
const Notes = () => import('./components/Notes.vue')
const Slideshow = () => import('./components/Slideshow.vue')
const ranking = () => import('./components/ranking.vue')
Vue.use(Router)

const router = new Router({
	routes: [
		{ path: '/', redirect: '/login' },
		{ path: '/login', component: Login },
		{
			path: '/home',
			component: Home,
			redirect: '/welcome',
			children: [
				{ path: '/welcome', component: Welcome },
				{ path: '/Details', component: Details },
				{ path: '/Orders', component: Orders },
				{ path: '/User', component: User },
				{ path: '/Product', component: Product },
				{ path: '/Notes', component: Notes },
				{ path: '/Slideshow', component: Slideshow },
				{ path: '/ranking', component: ranking }
			]
		}
	]
})

// 挂载路由导航守卫
router.beforeEach((to, from, next) => {
	// to 将要访问的路径
	// from 代表从哪个路径跳转而来
	// next 是一个函数，表示放行
	//     next()  放行    next('/login')  强制跳转

	if (to.path === '/login') return next()
	// 获取token
	//const tokenStr = window.sessionStorage.getItem('token')
	//if (!tokenStr) return next('/login')
	next()
})

export default router
