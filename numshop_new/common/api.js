import Request from '@/utils/request.js'
let request = new Request().http

//全局定义请求头
export default {
    // 请求样式
    /* classifyLeft: function(data) {
        return request({
            url: '/category/list', //请求头
            method: 'GET', //请求方式
            data: data, //请求数据
        })
    }, */
	//加载商品列表
	loadProductList(data){
		return request({
		    url: '/product/list', //请求头
		    method: 'POST', //请求方式
		    data: data, //请求数据
		})
	},
	//查询商品总量
	loadProductTotal(data){
		return request({
			url:'/product/total',
			method:'POST',
			data:data
		})
	},
	//加载轮播图
	loadSlideshow(data){
		return request({
			url:'/slideshow/list',
			method:'POST',
			data:data
		})
	},
	//发送验证码
	//注意
	sendCode(data){
		return request({
			url:'/user/sendCode',
			method:'POST',
			data:data
		})
	},
	//注册--注册事件+姓名+加密密码+最重要的电话
	register(data){
		return request({
			url:'/user/add',
			method:'POST',
			data:data
		})
	},
	
	//登录
	login(data){
		return request({
			url:'/user/login',
			method:'POST',
			data:data
		})
	},
	
	loadProductById(data){
		return request({
			url:'/product/getById',
			method:'POST',
			data:data
		})
	},
	loadUserById(data){
		return request({
			url:'/user/getById',
			method:'POST',
			data:data
		})
	},
	//实名认证
	verification(data){
		return request({
			url:'/user/verification',
			method:'POST',
			data:data
		})
	},
	//加载订单
	loadDetails(data){
		return request({
			url:'/details/list2',
			method:'POST',
			data:data
		})
	},
	//重置密码
	updatePass(data){
		return request({
			url:'/user/updatePass',
			method:'POST',
			data:data
		})
	},
	sendCode2(data){
		return request({
			url:'/user/sendCode2',
			method:'POST',
			data:data
		})
	},
	//加载我的订单
	loadOrders(data){
		return request({
			url:'/orders/list',
			method:'POST',
			data:data
		})
	},
	deleteOrder(data){
		return request({
			url:'/orders/del',
			method:'POST',
			data:data
		})
	},
	//微信授权登录(非静默)
	loginWX(data){
		return request({
			url:'/goLogin',
			method:'POST',
			data:data
		})
	},
	//微信授权登录(静默)
	loginWX2(data){
		return request({
			url:'/goLogin2',
			method:'POST',
			data:data
		})
	},
	//创建订单
	createOrder(data){
		return request({
			url:'/orders/insert',
			method:'POST',
			data:data
		})
	},
	//创建订单
	createOrder1(data){
		return request({
			url:'/orders/insert1',
			method:'POST',
			data:data
		})
	},
	//订单详情
	orderDetails(data){
		return request({
			url:'/details/list',
			method:'POST',
			data:data
		})
	},
	//统一下单
	payOrder(data){
		return request({
			url:'/orders/pay',
			method:'POST',
			data:data
		})
	},
	
	//加载用户协议和隐私协议
	loadAgreement(data){
		return request({
			url:'/protocol/list',
			method:'POST',
			data:data
		})
	},
	getShoppingCarList(data){
		return request({
			url:'/shoppingcar/list',
			method:'POST',
			data:data
		})
	},
	addShoppingCar(data){
		return request({
			url:'/shoppingcar/insert',
			method:'POST',
			data:data
		})
	},
	updateShoppingCar(data){
		return request({
			url:'/shoppingcar/update',
			method:'POST',
			data:data
		})
	},
	delShoppingCar(data){
		return request({
			url:'/shoppingcar/delete',
			method:'POST',
			data:data
		})
	},
	
	getAddressList(data){
		return request({
			url:'/address/list',
			method:'POST',
			data:data
		})
	},
	addAddress(data){
		return request({
			url:'/address/insert',
			method:'POST',
			data:data
		})
	},
	updateAddress(data){
		return request({
			url:'/address/update',
			method:'POST',
			data:data
		})
	},
	delAddress(data){
		return request({
			url:'/address/delete',
			method:'POST',
			data:data
		})
	}
}
/*
请求样式：
    自定义名字: function(data) {
        return request({
            url: "/banner", //请求头
            method: "GET", //请求方式 
            data: data,    //请求数据
            token: token, // 可传  
            hideLoading: false, //加载样式
        })
    },
*/