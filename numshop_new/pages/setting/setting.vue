<template>
	<view>
		<view class="item">
			<image mode="aspectFit" class="head" src="../../static/setting/service.png"></image>
			<view>联系客服</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
		
		<view class="item" @tap="goResetPwd">
			<image mode="aspectFit" class="head" src="../../static/setting/edit.png"></image>
			<view>修改密码</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
		
		<view class="item" @tap="goAgreement">
			<image mode="aspectFit" class="head" src="../../static/setting/agreement.png"></image>
			<view>用户协议</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
		
		<view class="item" @tap="goPrivacy">
			<image mode="aspectFit" class="head" src="../../static/setting/privacy.png"></image>
			<view>隐私政策</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
		
		<view class="item" @tap="goMy">
			<image mode="aspectFit" class="head" src="../../static/setting/user.png"></image>
			<view>关于我们</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
		
		<view class="item" @tap="logout">
			<image mode="aspectFit" class="head" src="../../static/setting/logout.png"></image>
			<view>退出登录</view>
			<image class="right" src="../../static/setting/right.png"></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			goResetPwd(){
				uni.navigateTo({
					url:"/pages/resetpwd/resetpwd"
				})
			},
			goAgreement(){
				uni.navigateTo({
					url:"/pages/agreement/agreement"
				})
			},
			goPrivacy(){
				uni.navigateTo({
					url:"/pages/privacy/privacy"
				})
			},
			goMy(){
				uni.navigateTo({
					url:"/pages/about/about"
				})
			},
			logout(){
				uni.clearStorageSync()
				uni.switchTab({
					url:"/pages/center/center"
				})
			}
		}
	}
</script>

<style>
	/* page{
		background-image: linear-gradient(#091323,#243242);
	} */
	.head{
		float: left;
		width:42rpx;
		height:46rpx;
		margin-top:44rpx;
		margin-left: 30rpx;
	}
	.right{
		float: right;
		width: 16rpx;
		height: 28rpx;
		margin-top:49rpx;
		margin-right: 24rpx;
	}
	.item view{
		float: left;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		margin-top:46rpx;
		margin-left:22rpx;
	}
	.item{
		width: 690rpx;
		height: 130rpx;
		background: #fff;
		border-radius: 20rpx;
		margin-top:42rpx;
		margin-left: 30rpx;
	}
</style>
