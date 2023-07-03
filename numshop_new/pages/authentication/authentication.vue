<template>
	<!--
		实名基本信息
		创建新的号时，只有电话号码和账号密码
	-->
	<view>
		<view class="content">
			
			<view class="title">
				请填写实名认证信息
			</view>
			
			<view class="ts">
				根据相关法律法规，身份证与账号一一绑定，为保障你的信息安全，请填写您本人的实名信息。请务必填写您本人的真实身份信息，平台将不定期随机抽查，无法证明本人的用户将被限制登录或限制转赠。
			</view>
			
			<view class="form">
				<input type="text" placeholder="姓名" v-model="userInfo.name" placeholder-class="form-p" :disabled="userInfo.authentication==2">
				<input type="text" placeholder="身份证号码" v-model="userInfo.cardno" placeholder-class="form-p" :disabled="userInfo.authentication==2">
			</view>
			
			<view class="a-button" @tap="authentication">
				去认证
			</view>
			
			<view class="a-text">
				信息安全保障中，信息仅用于身份确认
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//加载该页面时，重新为它赋值
				userInfo:{
					name:'',
					cardno:''
				}
			}
		},
		onLoad() {
			//申明一个变量存放从内存中提出来的信息
			var userInfo = uni.getStorageSync("userInfoObj");
			if(!userInfo){//判断是否登录
				uni.showToast({
					title:"请登录",
					icon:"none",
					duration:1000
				})
				setTimeout(()=>{
					uni.navigateTo({
						url:"/pages/login/login"
					})
				},1000)
				return;
			}
			this.userInfo = userInfo//取到的值是Vue对象的，而不是自定义的
		},
		methods: {
			authentication(){
				if(this.userInfo.authentication==2){
					return;
				}
				var name = this.userInfo.name;
				var cardno = this.userInfo.cardno;
				var id = this.userInfo.id;
				if(!name){
					uni.showToast({
						title:"请输入姓名",
						icon:"error"
					})
					return;
				}
				var p = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
				if(!p.test(cardno)){
					uni.showToast({
						title:'身份证格式不正确',
						icon:"error"
					})
					return false; 
				}
				var that = this;
				this.$api.verification({
					name:name,
					idcardno:cardno,
					userid:id
				}).then(resp=>{//封装好的json对象
					if(resp.message=='姓名和身份证号一致'){
						uni.showToast({
							title:"认证成功",
							icon:"success",
							duration:1000
						})
						that.$api.loadUserById({
							id:id
						}).then(res=>{
							uni.setStorageSync("userInfoObj",res)
							setTimeout(()=>{
								uni.navigateBack({
									delta:1
								})
							},1000)
							return;
						})
					}
					uni.showToast({
						title:resp.message
					})
				});
			}
		}
	}
</script>

<style>
	.a-text{
		font-size: 24rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		text-align: center;
		margin-top:46rpx;
	}
	.a-button{
		width: 473rpx;
		height: 88rpx;
		background: #fff;
		border: 1rpx solid #000;
		border-radius: 10rpx;
		line-height: 88rpx;
		text-align: center;
		margin-left: auto;
		margin-right: auto;
		margin-top: 40rpx;
		font-size: 30rpx;
		color:#162232;
	}
	.form-p{
		color:#999;
		font-family: SourceHanSansSC;
	}
	.form input{
		width: 620rpx;
		height: 98rpx;
		border: 1rpx solid #49627E;
		border-radius: 20rpx;
		margin-left: auto;
		margin-right: auto;
		color:#000;
		font-size: 32rpx;
		padding-left: 26rpx;
		margin-top:34rpx;
		font-family: SourceHanSansSC;
	}
	.ts{
		width: 598rpx;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		margin-left: 46rpx;
		margin-top:36rpx;
	}
	.title{
		font-size: 36rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #000;
		text-align: center;
	}
	.content{
		width: 690rpx;
		height: 861rpx;
		background: #fff;
		border-radius: 20rpx;
		margin-left:30rpx;
		margin-top:30rpx;
		padding-top: 36rpx;
	}
</style>
