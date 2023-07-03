<template>
	<view>
		<!--页面基本导航-->
		<view class="nav">
			<view :class="{'active':status==3,'noactive':status!=3}" @tap="changeStatus(3)">全部</view>
			<view :class="{'active':status==1,'noactive':status!=1}" @tap="changeStatus(1)">待付款</view>
			<view :class="{'active':status==2,'noactive':status!=2}" @tap="changeStatus(2)">已完成</view>
		</view>
		
		<view class="noOrder" v-if="orders.length==0">
			暂无相关订单
		</view>
		
		<view v-else>
			<view class="item" v-for="order in orders" :key="order.id">
				<view class="status">
					交易{{order.status}}
				</view>
				<view class="middle">
					<image mode="aspectFit" :src="order.list[0].product.imageurl"></image>
					<view class="name">
						{{order.list[0].product.name}}
					</view>
					<view class="types">
						{{order.list[0].product.tag}}
					</view>
					<view class="price">
						<view class="left">
							¥{{order.list[0].product.price}}
						</view>
						<view class="right">
							X{{order.list.length}}
						</view>
					</view>
				</view>
				<view class="clear">
					
				</view>
				<view class="footer">
					<view class="f-text">
						共{{order.list.length}}件商品  合计¥{{order.list.length*order.list[0].product.price}}
					</view>
					<view class="del" v-if="order.status=='待支付'" @tap="del(order.id)">
						删除订单
					</view>
					<view class="pay" v-if="order.status=='待支付'" @tap="pay(order.id,order.list[0].product.id,order.list.length)">
						付款
					</view>
					<view class="pay" v-if="order.status=='已完成'" @tap="again(order.list[0].product.id)">
						再次购买
					</view>
				</view>
			</view>
			
		</view>
		<view class="bottom">
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				status:3,
				orders:[], //返回的是json数组
				userInfo:{}
			}
		},
		//获取到页面传来的数据，从主页中点进来的数据
		onLoad(option) {
			var status = option.status?option.status:3
			this.status = status
		},
		onShow(){
			//获取到用户的信息
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
			this.userInfo = userInfo
			this.loadOrders()
		},
		methods: {
			pay(id,productId,count){
				this.$api.loadProductById({
					id:productId
				}).then(resp=>{
					if(resp.count<count){
						uni.showToast({
							title:"商品库存不足",
							icon:"none"
						})
						return;
					}
					uni.navigateTo({
						url:"/pages/pay/pay?id="+id
					})
				})
			},
			again(id){
				uni.navigateTo({
					url:"/pages/detail/detail?id="+id
				})
			},
			del(id){
				var that = this;
				uni.showModal({
					title: '取消订单',
					content: '确定取消此订单？',
					success: (res)=>{
						if (res.confirm) {
							that.$api.deleteOrder({
								id:id
							}).then(resp=>{
								if(resp==1){
									uni.showToast({
										title:"取消成功",
										duration:1000
									})
									that.loadOrders()
								}
							})
						} else if (res.cancel) {
							//console.log('用户点击取消');
						}
					}
				});
			},
			//修该状态
			changeStatus(status){
				this.status = status;
				
				//每一次修改状态后都需要根据状态和id重新加载信息
				this.loadOrders()
			},
			//通过id和状态加载数据
			loadOrders(){
				var that = this;
				var status = this.status;
				var types = status==1?'待支付':(status==2?'已完成':'')
				this.$api.loadOrders({
					userid:that.userInfo.id,
					status:types
				}).then(resp=>{
					that.orders = resp
				})
			}
		}
	}
</script>

<style>
	/* page{
		background-image: linear-gradient(#091323,#243242);
	} */
	.noOrder{
		width: 690rpx;
		height: 100rpx;
		text-align: center;
		line-height: 100rpx;
		font-size: 30rpx;
		font-weight: bold;
		color:#000;
		background: #fff;
		border-radius: 20rpx;
		margin:30rpx;
	}
	.bottom{
		height: 50rpx;
	}
	.pay{
		float: right;
		background: #000;
		border-radius: 10rpx;
		padding: 10rpx 20rpx;
		margin-right: 20rpx;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #fff;
	}
	.del{
		float: right;
		border: 1rpx solid #000;
		border-radius: 10rpx;
		padding: 10rpx 20rpx;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		margin-right: 20rpx;
	}
	.f-text{
		float: left;
		font-size: 20rpx;
		font-family: Adobe Heiti Std;
		font-weight: normal;
		color: #000;
		margin-left: 22rpx;
		margin-top:28rpx;
	}
	.status{
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		padding-top:24rpx;
		margin-left:22rpx;
		margin-bottom: 24rpx;
	}
	.clear{
		clear: both;
	}
	.types{
		width:464rpx;
		margin-left:26rpx;
		font-size: 20rpx;
		font-family: Adobe Heiti Std;
		font-weight: normal;
		color: #000;
	}
	.name{
		width:464rpx;
		margin-left:26rpx;
		font-size: 32rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #000;
		margin-top:20rpx;
	}
	.price{
		width:464rpx;
		margin-left:26rpx;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #000;
	}
	.right{
		float: right !important;
		font-size: 20rpx;
		margin-right: 24rpx;
	}
	.middle view{
		float: left;
	}

	.middle image{
		float: left;
		width:180rpx;
		height: 180rpx;
		border-radius: 20rpx;
		margin-left: 20rpx;
	}
	.item{
		width: 690rpx;
		height: 376rpx;
		background: #fff;
		border-radius: 20rpx;
		margin:30rpx;
	}
	.nav {
		width: 750rpx;
		height: 100rpx;
		background: #fff;
		display: flex;
	}

	.noactive {
		font-size: 28rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		height: 100rpx;
		line-height: 100rpx;
		width: 33%;
		text-align: center;
	}

	.active {
		font-size: 28rpx;
		font-family: SourceHanSansSC;
		height: 94rpx;
		line-height: 100rpx;
		width: 33%;
		text-align: center;
		font-weight: bold;
		color: #000;
		border-bottom:6rpx solid #000;
	}
</style>
