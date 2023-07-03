<template>
	
	<view>
		<view class="header">
			<!--图片中包含了文字-->
			<view class="h-img">
				<view class="title">
				</view>
			</view>
			<view class="h-content">
				<view class="h-c-img">
					<image mode="aspectFit" :src="product.imageurl"></image>
				</view>
				<view class="h-c-left">
					<view class="name">
						{{product.name}}
					</view>
					<view class="h-c-price">
						￥{{product.price}}
					</view>
					<view class="h-c-count">
						库存:{{product.count}}
					</view>
				</view>
				
				<view class="h-right">
					<view class="h-c-img-count">
						<image src="../../static/detail/plus.png" @tap="plus"></image>
						<input type="text" v-model="count" value="1">
						<image src="../../static/detail/subtract.png" @tap="sub"></image>
					</view>
				</view>
				
			</view>
		</view>
		<!--商品信息介绍-->
		<view class="banner">
			<image src="../../static/detail/header.png"></image>
		</view>
		<view class="myselfTodelete">
			<image mode="aspectFit" :src="product.otherurl">
			</image>
		</view>
		<view class="footer">
			<view class="f-price">
				￥{{product.price}}
			</view>
			<view class="f-button-w" v-if="product.buyText=='即将开售'">
				<image src="../../static/detail/clock.png"></image>
				<view class="f-text">
					即将发售
				</view>
				<view class="f-time">
					{{product.shoptime.substring(5,16)}}
				</view>
			</view>
			<view class="f-button-n" @tap="buy()" v-else>
				{{product.buyText}}
			</view>
			<view class="iconfont shoppingcar" @tap="addShoppingCar()">&#xe7c4</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				product:{},
				id:'',
				count:1
			}
		},
		onLoad(option){
			//从url中获取到相关信息
			var id = option.id;
			this.id = id;
		},
		onShow(){
			var that = this;
			this.$api.loadProductById({
				id:that.id
			}).then(resp=>{
				that.product = resp
			})
		},
		methods: {
			addShoppingCar(){
				var that = this;
				var userInfo = uni.getStorageSync("userInfoObj");
				if(!userInfo){
					uni.showToast({
						title:"请登录",
						icon:"none",
						duration:1000
					})
					setTimeout(resp=>{
						uni.navigateTo({
							url:"/pages/login/login"
						})
					},1000)
					return;
				}
				that.$api.addShoppingCar({
					productid: that.product.id,
					count: that.count,
					userid: userInfo.id
				}).then(resp=>{
					if (resp>0) {
						uni.showToast({
							title: "添加成功",
							icon: 'success'
						})
						return;
					}
				})
			},
			buy(){
				var buyText = this.product.buyText
				if(buyText!='立即购买'){
					return;
				}
				//从全局内存中取出变量
				//是否登录了
				var userInfo = uni.getStorageSync("userInfoObj");
				if(!userInfo){
					uni.showToast({
						title:"请登录",
						icon:"none",
						duration:1000
					})
					setTimeout(resp=>{
						uni.navigateTo({
							url:"/pages/login/login"
						})
					},1000)
					return;
				}
				if(!userInfo.openid){//没有openid
					uni.showToast({
						title:"请绑定微信后重新进入",
						icon:"none",
						duration:1000
					})
					setTimeout(()=>{
						uni.navigateTo({
							url:"/pages/result2/result2"
						})
					},1000)
					return;
				}
				var that = this;
				//判定是否实名认证
				//用户在做任何购买操作时，都需要去实名认证
				this.$api.loadUserById({
					id:userInfo.id
				}).then(resp=>{
					if(resp){
						if(resp.authentication==1){//未实名
							uni.showToast({
								title:"请实名认证",
								icon:"none"
							})
							setTimeout(()=>{
								uni.navigateTo({
									url:"/pages/authentication/authentication"
								})
							},1000)
							return;
						}else{//已实名
							//生成订单---(需要通过接口去查询商品相关信息)
							//发送当前的用户id和记录条数加上商品的id
							that.$api.createOrder({
								id: that.id,
								count: that.count,
								userid: userInfo.id
							}).then(resp=>{
								var orderid = resp;//创建一个id去接收返回来的int信息
								if (orderid == 0) {
									uni.showToast({
										title: "库存不足",
										icon: 'none'
									})
									return;
								}
								if (orderid) {
									uni.navigateTo({
										url: '/pages/pay/pay?id=' + orderid
									})
								}
							})
						}
					}
				})
			},
			plus(){
				if(this.count+1>this.product.count){
					return;
				}
				this.count = this.count + 1;
			},
			sub(){
				if(this.count-1<=0){
					return;
				}
				this.count = this.count - 1;
			}
		}
	}
</script>

<style>
	page {
		background-color: #F6F6F6;
	}
	
	.myselfTodelete{
		text-align: center;
	}

	rich-text img {
		width: 690rpx;
	}
	.detail{
		width:690rpx;
		margin-top: 30rpx;
		margin-left: 30rpx;
	}
	

	.f-time {
		width: 200rpx;
		font-size: 20rpx;
		text-align: center;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
	}

	.f-text {
		width: 200rpx;
		text-align: center;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
	}

	.f-button-w view {
		float: right;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
	}

	.f-button-w image {
		float: left;
		width: 44rpx;
		height: 44rpx;
		margin-top: 24rpx;
		margin-left: 24rpx;
	}

	.f-button-w {
		float: right;
		width: 274rpx;
		height: 92rpx;
		background: #49627E;
		border-radius: 10rpx;
		margin-top: 46rpx;
		margin-right: 30rpx;
	}

	.footer {
		width: 100%;
		height: 100rpx;
	    background-color: #fff;
		box-shadow: #F6F6F6 2px;
	}
	.f-button-n,.shoppingcar{
		width: 200rpx;
		height: 80rpx;
		background: red;
		border-radius: 20rpx;
		color: #fff;
		float: right;
		font-size: 30rpx;
		line-height: 81rpx;
		text-align: center;
	}
	.f-button-n{
		margin-right: 10rpx;
	}
	.shoppingcar{
		border-right: 1px solid #fff;
		font-size: 50rpx;
	}
	.f-price {
		float: left;
		font-size: 40rpx;
		font-family: SourceHanSansSC;
		color: red;/*8FA9C7*/
		width: 200rpx;
		height: 80rpx;
		width: 45%;
		text-align: left;
		margin-top: 20rpx;
	}
	.n-c {
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
		margin-left: 34rpx;
		margin-right: 34rpx;
	}

	.n-h {
		margin-left: 34rpx;
		font-size: 36rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #8FA9C7;
		margin-bottom: 38rpx;
	}

	.notes {
		width: 690rpx;
		height: 500rpx;
		background: #35475D;
		border-radius: 20rpx;
		margin-top: 30rpx;
		margin-left: 30rpx;
		padding-top: 38rpx;
	}

	.banner {
		margin-top: 50rpx;
	}

	.banner image {
		width: 100%;
		height: 76rpx;
	}

	.h-c-count {
		font-size: 24rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #49627E;
	}

	.h-c-price {
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #6E88A6;
		margin-top: 24rpx;
		/**
		 * 价格都变成红色
		 */
		color: red;
	}

	.name {
		font-size: 34rpx;
		color: #A6B2C1;
		margin-top: 28rpx;
		font-weight: bold;
		font-family: SourceHanSansSC;
	}

	.h-c-button {
		margin-top: 40rpx;
		width: 170rpx;
		height: 60rpx;
		background: #8FA9C7;
		border-radius: 10rpx;
		line-height: 60rpx;
		text-align: center;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #162232;
		margin-left: auto;
		margin-right: auto;
	}

	.h-c-left {
		float: left;
	}

	.h-right {
		float: right;
	}

	.h-c-img-count input {
		width: 88rpx;
		text-align: center;
		color: #6E88A6;
	}

	.h-c-img-count {
		display: flex;
		flex-direction: row;
		justify-content: flex-end;
		margin-top: 30rpx;
	}

	.h-right image {
		width: 40rpx;
		height: 40rpx;
	}

	.h-c-img image {
		width: 100%;
		margin: 0 auto;
		border-radius: 20rpx;
	}

	.h-c-img {
		width: 638rpx;
		height: 500rpx;
	}

	.h-content {
		position: absolute;
		top: 24rpx;
		left: 26rpx;
		/* background-color: #fff; */
	}

	.h-img {
		width: 638rpx;
		height: 683rpx;
		border-radius: 20rpx;
		margin-left: 26rpx;
		border-radius: 20rpx;
	}

	.h-text {
		font-size: 38rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #8FA9C7;
		position: relative;
		bottom: 84rpx;
		text-align: center;
	}

	.title {
		height: 86rpx;
		position: relative;
		z-index: 999;
	}

	.title image {
		height: 86rpx;
		border-radius: 20rpx;
	}

	.header {
		position: relative;
		width: 690rpx;
		height: 750rpx;/*890*/
		background: #fff;/*F6F6F6 35475D*/
		border-radius: 20rpx;
		margin: 30rpx;
		padding-top: 24rpx;
	}
</style>
