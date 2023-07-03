<template>
	<view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				res: ''
			}
		},
		methods: {
			getUrlCode(name) {
				return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.href) ||
					[, ''
					])[1]
					.replace(/\+/g, '%20')) || null
			},
			getpower() {
				console.log("登录")
				var local = encodeURIComponent(window.location.href)
				let url =
					"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx88cf274021ec5453&response_type=code&scope=snsapi_base&state=STATE&connect_redirect=1&redirect_uri=" +
					local + "#wechat_redirect"

				window.location.href = url
			},
			sendCode(code) {
				var userInfo = uni.getStorageSync("userInfoObj")
				this.$api.loginWX2({
					code:code,
					id:userInfo.id
				}).then(resp=>{
					//认证之后就跟新内存中数据+返回首页
					uni.setStorageSync("userInfoObj", resp.data)
					window.location.href = "http://localhost:8081"
				})
			},
			
		},
		onLoad() {
			var code = this.getUrlCode("code");
			if (code == null) {
				this.getpower()
			} else {

				// uni.showToast({
				// 	title:option.code
				// })
				this.sendCode(code)
			}
		}
	}
</script>

<style>

</style>
