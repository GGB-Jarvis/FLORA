<template>
	<view>
		<view class="main">
			<!-- <image class="bg" src="../../static/content/bg.png"></image> -->
			<view class="content">
				<view class="m-img">
					<image mode="aspectFit" :src="product.imageurl"></image>
					
				</view>
				<view class="name">
					{{product.name}}
				</view>
				<view class="possessor">
					<image class="head-img" :src="userInfo.imageurl?userInfo.imageurl:'../../static/head.jpg'"></image>
					<view class="p-name">
						{{userInfo.realname}}
					</view>
					<view class="sign">
						持有者
					</view>
				</view>
				<view class="other">
					<image src="../../static/content/explain.png"></image>
					<view class="other-f">
						<view class="l-title" >
							<text space="nbsp">区块链唯一标识   https://ctblock.cn/</text>
						</view>
						<view class="o-left">
							<view class="l-content">
								{{product.tokenId}}
							</view>
						</view>
						<view class="o-right">
							<image src="../../static/content/seal.png"></image>
						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- <view class="footer">
			<view class="f-header">
				
			</view>
		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id:0,
				userInfo:{},
				product:{}
			}
		},
		onLoad(option){
			var id = option.id;
			this.id = id;
			this.loadProductById()
		},
		onShow(){
			var userInfo = uni.getStorageSync("userInfoObj")
			if(!userInfo){
				uni.showToast({
					title:"请登录",
					duration:1000
				})
				setTimeout(()=>{
					uni.navigateTo({
						url:"/pages/login/login"
					})
				},1000)
				return;
			}
			this.userInfo = userInfo;
		},
		methods: {
			loadProductById(){
				var id = this.id;
				this.$api.loadProductById({
					id:id
				}).then(resp=>{
					this.product = resp
				})
			}
		}
	}
</script>

<style>
	/* page {
		background-image: linear-gradient(#091323, #243242);
	} */
	.head-img{
		border-radius: 50%;
	}
	.l-title{
		color: #B0DEFF;
		margin-left:40rpx;
		margin-top:10rpx;
	}
	.footer{
		width: 750rpx;
		height: 297rpx;
		background: #35475D;
		border-radius: 20rpx 20rpx 0rpx 0rpx;
	}
	.l-content {
		word-break: break-all;
	}

	.o-right {
		float: right;
		margin-top: 32rpx;
		margin-left: 14rpx;
	}

	.o-left {
		margin-left: 38rpx;
		margin-top: 10rpx;
		color: #B0DEFF;
		width: 426rpx;
		float: left;
	}

	.other-f {
		position: absolute;
		top: 848rpx;
		left: 10rpx;
	}

	.other>image {
		width: 630rpx;
		height: 218rpx;
		position: relative;
		left: 10rpx;
		top: 18rpx;
		z-index: 0;
	}

	.o-right image {
		width: 114rpx;
		height: 100rpx;
	}

	.sign {
		width: 90rpx;
		height: 38rpx;
		background: #35475D;
		border: 1rpx solid #B0DEFF;
		border-radius: 10rpx;
		font-size: 22rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #B0DEFF;
		line-height: 38rpx;
		text-align: center;
		margin-left: 12rpx;
	}

	.p-name {
		font-size: 28rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #B0DEFF;
		margin-left: 12rpx;
	}

	.possessor image {
		width: 56rpx;
		height: 56rpx;
	}

	.possessor {
		display: flex;
		justify-content: center;
		margin-top: 50rpx;
	}

	.name {
		font-size: 36rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #B0DEFF;
		text-align: center;
	}

	.m-img image {
		width: 382rpx;
		height: 500rpx;
		border-radius: 20rpx;
		margin-left: 134rpx;
		margin-top: 50rpx;
	}

	.header {
		text-align: center;
		width: 100%;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
		margin-top: 60rpx;
	}

	.content {
		position: absolute;
		left: 20rpx;
		top: 20rpx;
		width: 650rpx;
	}

	.bg {
		position: relative;
		top: 20rpx;
		left: 20rpx;
		z-index: 1;
		width: 650rpx;
		height: 1066rpx;
	}

	.main {
		position: relative;
		width: 690rpx;
		height: 1106rpx;
		background: #35475D;
		border-radius: 20rpx;
		margin: 30rpx;
	}
</style>
