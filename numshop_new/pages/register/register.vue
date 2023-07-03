<template>
	<view>
		<view class="main">
			<view class="form">
				<h2>FLORA</h2>
				<view class="phone">
					<input placeholder="请输入手机号码" v-model="phone" placeholder-class="placeholder-class" />
					<view @tap="sendCode">
						{{hint}}
					</view>
				</view>
				<input placeholder="请输入验证码" v-model="code" placeholder-class="placeholder-class">
				<input password="true" v-model="password" placeholder="请输入密码" placeholder-class="placeholder-class">
				<input password="true" v-model="password2" placeholder="请确认密码" placeholder-class="placeholder-class">

				<view class="button" @tap="register" role="button">
					<span>立即注册</span>
				</view>
				<view class="login" @tap="goLogin">
					已有账号立即登录
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				hint:'获取验证码',
				times:-1,
				id:'',
				phone:'',
				code:'',
				codes:'',
				password:'',
				password2:''
			}
		},
		onShow(){
			var times = uni.getStorageSync("times")
			if(times>0){
				this.times = times;
				this.countDown()
			}
		},
		methods: {
			register(){
				var code = this.code;
				var codes = this.codes;
				var phone = this.phone;
				var password = this.password;
				var password2 = this.password2;
				if(!code || code!=codes){
					uni.showToast({
						title:"验证码不正确",
						icon:"none",
						duration:1000
					})
					return;
				}
				if(!password){
					uni.showToast({
						title:"请输入密码",
						icon:"none",
						duration:1000
					})
					return;
				}
				if(password!=password2){
					uni.showToast({
						title:"两次密码不一致",
						icon:"none",
						duration:1000
					})
					return;
				}
				//注册时，只需要账号密码
				this.$api.register({
					phone:phone,
					password:password
				}).then(resp=>{
					if(resp==1){
						uni.showToast({
							title:"注册成功",
							icon:"success",
							duration:1000
						})
						setTimeout(()=>{
							uni.navigateBack({
								delta:1
							})
						},1000)
					}else{
						uni.showToast({
							title:"注册失败",
							icon:"error"
						})
					}
				})
			},
			//注册页面中又添加页面
			goLogin(){
				uni.navigateTo({
					url:"/pages/login/login"
				})
			},
			countDown(){
				this.id = setInterval(()=>{
					this.times = this.times - 1;
					uni.setStorageSync("times",this.times)
					if(this.times<=0){
						clearInterval(this.id)
						this.hint = "获取验证码"
						return;
					}
					this.hint = "重新获取("+this.times+")"
				},1000)
			},
			/**
			 * !@lc
			 * 发送验证码
			 */
			sendCode(){
				var phone = this.phone
				//验证手机号码
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(this.phone))) {
					uni.showToast({ title: '请填写正确手机号码', icon: "none" });
					return false;
				}
				if(this.hint!='获取验证码'){
					return;
				}
				var that = this;
				this.$api.sendCode({phone:phone}).then(resp=>{
					console.log(resp);
					if(resp.code){
						that.codes = resp.code;
						this.times = 60;
						this.countDown();
					}else{
						uni.showToast({
							title:resp.message,
							icon:"error",
							duration:1000
						})
					}
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
	
	.main {
		width: 690rpx;
		height: 940rpx;
		background: rgba(255,255,254,1);
		border-radius: 20rpx;
		margin-top: 220rpx;
		margin-left: 30rpx;
	}
	.login{
		color: #000;
		text-align: center;
		font-size: 30rpx;
		text-decoration: underline;
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

	.phone input {
		height: 98rpx;
	}

	.phone view {
		width:200rpx;
		height: 78rpx;
		line-height: 78rpx;
		margin-right: 20rpx;
		padding-left: 20rpx;
		border-left: 2rpx solid #49627E;
		margin-top:10rpx;
	}
	.phone view:active {
		color: rgba(128,128,128,1);
	}
	.phone {
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
		display: flex;
		justify-content: space-between;
	}
	
	

	.form>input {
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
		color: #8FA9C7;
	}

	.agreement text {
		color: skyblue
	}

	.register {
		float: left;
		margin-left: 26rpx;
		color: skyblue
	}

	.forget {
		float: right;
		color: skyblue
	}

	.clear {
		clear: both;
	}

	.fast {
		text-align: center;
		margin-top: 120rpx;
		font-family: SourceHanSansSC;
		color: #8FA9C7;
		font-size: 30rpx;
	}

	.fast image {
		width: 64rpx;
		height: 64rpx;
		margin-top: 20rpx;
	}
</style>
