<template>
	<view>
		<view class="main">
			<view class="form">
				<h2>FLORA</h2>
				<input placeholder="请输入手机号码" v-model="phone" placeholder-class="placeholder-class" />
				<input password="true" placeholder="请输入密码" v-model="password" placeholder-class="placeholder-class">
				<view class="agreement">
					<checkbox @tap="checkChange" class="round red" id="check" style="transform: scale(0.6,0.6);" />
					<label>我已阅读并同意<text @tap="showAgreement">《用户协议》</text><text @tap="showPrivacy">《隐私政策》</text></label>
				</view>
				<view class="button" @tap="login" role="button">
					<span>登录</span>
				</view>
				<view class="register" @tap="goRegister">
					用户注册
				</view>
				<view class="forget" @tap="forgetPwd">
					忘记密码？
				</view>
				<view class="clear">

				</view>
				<view class="fast">
					<view class="f-text">
						—— 快速登录 ——
					</view>
					<image src="../../static/login/wx3.png" @tap="oauthLogin('weixin')"></image>
				</view>
			</view>
			
		</view>
		<view class="protocol" v-if="agreementShow">
			<rich-text :nodes="agreement.content"></rich-text>
			<view class="p-button" @tap="closeAgreement">
				关闭
			</view>
		</view>
		<view class="protocol" v-if="privacyShow">
			<rich-text  :nodes="privacy.content"></rich-text>
			<view class="p-button" @tap="closePrivacy">
				关闭
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				agree: false,
				phone: '',
				password: '',
				agreement:{},
				privacy:{},
				agreementShow:false,
				privacyShow:false
			}
		},
		onShow(){
			this.loadAgreement()
			this.loadPrivacy()
		},
		methods: {
			//关闭协议窗口
			closeAgreement(){
				this.agreementShow = false;
			},
			closePrivacy(){
				this.privacyShow = false;
			},
			showAgreement(){
				this.agreementShow = true;
			},
			showPrivacy(){
				this.privacyShow = true;
			},
			
			//初始化协议信息
			loadAgreement(){
				var that = this;
				console.log(this.$api);
				this.$api.loadAgreement({
					types:'用户协议'
				}).then(resp=>{
					that.agreement = resp[0]
				})
			},
			loadPrivacy(){
				var that = this;
				this.$api.loadAgreement({
					types:'隐私政策'
				}).then(resp=>{
					that.privacy = resp[0]
				})
			},
			
			
			//快速登录的方法--这里需要在微信端口
			oauthLogin(provider) {
				var checked = this.agree
				if (!checked) {
					uni.showToast({ title: '请阅读协议', icon: "none" });
					return;
				}
				/* this.getpower() */
				//跳转授权登录页面
				uni.navigateTo({
					url: "/pages/result/result"
				})

			},
			//lc 忘记密码和注册页面
			goRegister() {
				uni.navigateTo({
					url: "/pages/register/register"
				})
			},
			forgetPwd() {
				uni.navigateTo({
					url: "/pages/resetpwd/resetpwd"
				})
			},
			
			checkChange() {
				this.agree = !this.agree
			},
			
			//登录方法--获取到信息lc
			login() {
				var phone = this.phone;
				var password = this.password;
				var agree = this.agree;
				if (agree == false) {
					uni.showToast({
						title: "请阅读协议",
						icon: "none",
						duration: 1000
					})
					return;
				}
				//验证手机号码
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
					uni.showToast({ title: '手机号码不正确', icon: "none" });
					return false;
				}
				if (!password) {
					uni.showToast({
						title: "请输入密码",
						icon: "none",
						duration: 1000
					})
					return false;
				}
				var data = {
					phone: phone,
					password: password,
					reserved: '用户'
				}
				this.$api.login(data).then(resp => {
					if (resp) {
						uni.showToast({
							title: "登录成功",
							icon: "success",
							duration: 1000
						})
						//本地内存中存放当前用户的信息
						uni.setStorageSync("userInfoObj", resp)
						setTimeout(() => {
							uni.navigateBack({
								delta: 1
							})
						}, 1000)
						return;
					}
					uni.showToast({
						title: "电话号码或密码错误",
						duration: 1000,
						icon: "error"
					})
				})
			}
		}
	}
</script>

<style>
	@font-face {
	  font-family: titleFont;
	  src: url("@/static/font/Amita-Regular.ttf");
	}
	
	
	@-webkit-keyframes active {
	  from {
	    box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 10px 0px rgba(0, 0, 250, 0.6);
	  }
	  to {
	    box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 3px 3px #CECFD1;
	  }
	}
	@keyframes active {
	  from {
	    box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 10px 0px rgba(0, 0, 250, 0.6);
	  }
	  to {
	    box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 3px 3px #CECFD1;
	  }
	}
	[role="button"] {
	-webkit-appearance: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	display: flex;
	align-items: center;
	justify-content: center;
	outline: none;
	cursor: pointer;
	width: 150px;
	height: 50px;
	text-align: center;
	margin: 30rpx auto;
	background-image: linear-gradient(to top, #D8D9DB 0%, #fff 80%, #FDFDFD 100%);
	border-radius: 30px;
	border: 1px solid #8F9092;
	box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 3px 0 #CECFD1;
	transition: all 0.2s ease;
	font-size: 14px;
	font-weight: 600;
	color: #606060;
	text-shadow: 0 1px #fff;
	}
	button::-moz-focus-inner,
	[role="button"]::-moz-focus-inner {
	  border: 0;
	}
	[role="button"] > * {
	  transition: -webkit-transform 0.2s ease;
	  transition: transform 0.2s ease;
	  transition: transform 0.2s ease, -webkit-transform 0.2s ease;
	  }
	[role="button"]:hover:not([disabled]) {
	  box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 3px 3px #CECFD1;
	}
	[role="button"]:hover:not([disabled]) > * {
	  -webkit-transform: scale(0.975);
	          transform: scale(0.975);
	}
	[role="button"]:focus:not(:active) {
	  -webkit-animation: active 0.9s alternate infinite;
	          animation: active 0.9s alternate infinite;
	  outline: none;
	}
	[role="button"]:active:not([disabled]) {
	  box-shadow: 0 4px 3px 1px #FCFCFC, 0 6px 8px #D6D7D9, 0 -4px 4px #CECFD1, 0 -6px 4px #FEFEFE, inset 0 0 5px 3px #999, inset 0 0 30px #aaa;
	}
	[role="button"]:active:not([disabled]) > * {
	  -webkit-transform: scale(0.95);
	          transform: scale(0.95);
	}
	[role="button"]:disabled {
	  opacity: 0.6;
	  cursor: not-allowed;
	}
	
	.p-button{
		text-align: right;
		margin: 30rpx;
		color:skyblue;
	}
	.protocol{
		width:650rpx;
		position: absolute;
		z-index: 999;
		background: #35475D;
		padding-left: 20rpx;
		padding-right: 20rpx;
		border-radius: 20rpx;
		top:-196rpx;
		left:30rpx;
		margin-bottom: 30rpx;
	}
	.main {
		width: 690rpx;
		height: 980rpx;
		background: rgba(255,255,254,1);
		border-radius: 20rpx;
		margin-top: 220rpx;
		margin-left: 30rpx;
	}
	
	.logo {
		width: 388rpx;
		height: 388rpx;
		border-radius: 50%;
		position: absolute;
		top: -194rpx;
		left: 180rpx;
	}
	
	.form {
		position: absolute;
		top: 180rpx;
	}
	
	.form h2 {
		font-size: 50px;
		position: absolute;
		top: -100rpx;
		left: 220rpx;
		font-family: titleFont;
	}
	
	
	input {
		width: 628rpx;
		height: 98rpx;
		border: 1rpx solid #49627E;
		border-radius: 20rpx;
		font-size: 32rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		padding-left: 20rpx;
		margin-top: 30rpx;
		margin-left: 20rpx;
	}
	
	.placeholder-class {
		color: rgba(128,128,128,1);
	}
	
	/* .button {
		width: 473rpx;
		height: 88rpx;
		background: #8FA9C7;
		border-radius: 10rpx;
		line-height: 88rpx;
		text-align: center;
		margin: 30rpx auto;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #162232;
	} */
	
	.agreement {
		margin-left: 30rpx;
		font-family: SourceHanSansSC;
		color: #000;
	}
	
	.agreement text {
		color: skyblue
	}
	
	.register {
		float: left;
		margin-left: 26rpx;
		color: #000
	}
	
	.forget {
		float: right;
		color: #000
	}
	
	.clear {
		clear: both;
	}
	
	.fast {
		text-align: center;
		margin-top: 120rpx;
		font-family: SourceHanSansSC;
		color: #000;
		font-size: 30rpx;
	}
	
	.fast image {
		width: 64rpx;
		height: 64rpx;
		margin-top: 20rpx;
		
	}
</style>
