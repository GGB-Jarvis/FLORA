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
				var local = encodeURIComponent(window.location.href)
				let url =
					"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx88cf274021ec5453&response_type=code&scope=snsapi_userinfo&state=STATE&connect_redirect=1&redirect_uri=" +
					local + "#wechat_redirect"

				window.location.href = url
			},
			sendCode(code) {
				this.$api.loginWX({
					code:code
				}).then(resp=>{
					uni.setStorageSync("userInfoObj", resp)
					window.location.href = "http://localhost:8081"
				})
			},
		},
		onLoad() {
			var code = this.getUrlCode("code");
			uni.removeStorageSync("userInfoObj")
			uni.clearStorageSync();
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
