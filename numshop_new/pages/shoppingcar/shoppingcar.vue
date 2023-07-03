<template>
	<view>
		<view>
			<view class="item" v-for="product,index in shoppingCarList" :key="product.id">
				<view class="middle">
					<image mode="aspectFit" :src="product.imageurl"  @tap="goDetail(product.id)"></image>
					<view class="textinfo">
						<view class="name">
							{{product.name}}
						</view>
						<view class="types">
							{{product.tag}}
						</view>
						<view class="price">
							<view class="left">
								¥{{product.price}}
							</view>
							<view class="h-right">
								<view class="h-c-img-count">
									<image src="../../static/detail/subtract.png" @tap="sub(product.id)"></image>
									<input type="text" v-model="product.count" value="1">
									<image src="../../static/detail/plus.png" @tap="plus(product.id)"></image>
								</view>
							</view>
						</view>
										
						<view class="footer">
							<view class="del" @tap="deleteShoppingCar(product.id)">
								删除
							</view>
							<view class="choose" :class="{ 'isChoose': product.checked}" @tap="choose(product.id,product.price,product.count,index)">
								{{product.chooseDesc}}
							</view>
					</view>
					</view>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="sum" @tap="submitOrder()">
				结算
			</view>
			<view class="total">
				合计：<span>￥<span>{{total}}</span></span>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				total:0,
				shoppingCarList:[], //返回的是json数组
				userInfo:{},
				choosedProduct:[]
			}
		},
		//获取到页面传来的数据，从主页中点进来的数据
		onLoad() {
			
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
			this.getShoppingCarList()
		},
		methods: {
			//通过id加载数据
			getShoppingCarList(){
				var that = this;
				this.$api.getShoppingCarList({
					userid:that.userInfo.id,
				}).then(resp=>{
					that.shoppingCarList = resp
					for(let i = 0;i < that.shoppingCarList.length;i++){
						that.shoppingCarList[i]["checked"] = false 
						that.shoppingCarList[i]["chooseDesc"] = "点击选择"
						that.shoppingCarList[i]["index"] = i
					}
				})
			},
			plus(pid){
				
				for(let i = 0;i < this.shoppingCarList.length;i++){
					if(this.shoppingCarList[i]['id'] == pid){
						this.shoppingCarList[i]['count'] = this.shoppingCarList[i]['count'] + 1;
					}
				}
				
			},
			sub(pid){
				for(let i = 0;i < this.shoppingCarList.length;i++){
					if(this.shoppingCarList[i]['id'] == pid){
						if(this.shoppingCarList[i]['count'] - 1>0){
							this.shoppingCarList[i]['count'] = this.shoppingCarList[i]['count'] - 1;
						}
						else return;
					}
				}
			},
			deleteShoppingCar(pid){
				
				var that = this
				this.$api.delShoppingCar({
					pid:pid,
					userid:that.userInfo.id
				}).then(resp=>{
					if(resp){
						uni.showToast({
							title:"删除成功",
							icon:"success",
							duration:1000
						})
						this.getShoppingCarList()
					}else{
						return;
					}
				})
			},
			choose(pid,price,count,index){
				for(let i = 0;i < this.shoppingCarList.length;i++){
					if(this.shoppingCarList[i]['index'] == index){
						this.shoppingCarList[i]['checked'] = !this.shoppingCarList[i]['checked']
						if(this.shoppingCarList[i]['checked']){
							this.shoppingCarList[i].chooseDesc="已选中"
							this.total = this.total+price*count
							this.choosedProduct.push({"pid":pid,"count":count})
						}else{
							this.shoppingCarList[i].chooseDesc="点击选择"
							this.total = this.total-price*count
							this.choosedProduct.pop({"pid":pid,"count":count})
						}
					}
				}
				
				
			},
			goDetail(pid){
				uni.navigateTo({
					url:"/pages/detail/detail?id="+pid
				})
			},
			submitOrder(){
				var that = this;
				console.log(that.choosedProduct);
				//判定是否实名认证
				//用户在做任何购买操作时，都需要去实名认证
				this.$api.loadUserById({
					id:that.userInfo.id
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
							that.$api.createOrder1({
								productList: that.choosedProduct,
								userid: that.userInfo.id
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
			}
			// pay(id,productId,count){
			// 	this.$api.loadProductById({
			// 		id:productId
			// 	}).then(resp=>{
			// 		if(resp.count<count){
			// 			uni.showToast({
			// 				title:"商品库存不足",
			// 				icon:"none"
			// 			})
			// 			return;
			// 		}
			// 		uni.navigateTo({
			// 			url:"/pages/pay/pay?id="+id
			// 		})
			// 	})
			// },
			// again(id){
			// 	uni.navigateTo({
			// 		url:"/pages/detail/detail?id="+id
			// 	})
			// },
			// del(id){
			// 	var that = this;
			// 	uni.showModal({
			// 		title: '取消订单',
			// 		content: '确定取消此订单？',
			// 		success: (res)=>{
			// 			if (res.confirm) {
			// 				that.$api.deleteOrder({
			// 					id:id
			// 				}).then(resp=>{
			// 					if(resp==1){
			// 						uni.showToast({
			// 							title:"取消成功",
			// 							duration:1000
			// 						})
			// 						that.loadOrders()
			// 					}
			// 				})
			// 			} else if (res.cancel) {
			// 				//console.log('用户点击取消');
			// 			}
			// 		}
			// 	});
			// },
			//修该状态
			// changeStatus(status){
			// 	this.status = status;
				
			// 	//每一次修改状态后都需要根据状态和id重新加载信息
			// 	this.loadOrders()
			// },
			
		}
	}
</script>

<style>
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
	.item{
		width: 690rpx;
		height: 300rpx;
		background: #fff;
		border-radius: 20rpx;
		margin:30rpx;
	}
	.item .middle{
		width: 90%;
		height: 100%;
		display: flex;
		align-items: center;
		justify-content: center;
	}
	.middle image{
		width: 40%;
		height: 100%;
	}
	.middle .textinfo{
		height: 90%;
		width: 60%;
	}
	.bottom{
	/* 	position: fixed;
		bottom: 50px; */
		height: 100rpx;
		width: 100%;
		background-color: #fff;
	}
	.bottom .sum{
		float: right;
		border: 1rpx solid #fff;
		text-align: center;
		height: 50rpx;
		width: 100rpx;
		border-radius: 30rpx;
		padding: 10rpx 20rpx;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		background-image: linear-gradient(to right,#ff9000 0,#ff7000 100%);
		color: #fff;
		margin-right: 35rpx;
		margin-top: 20rpx;
	}
	.bottom .total{
		float: right;
		text-align: center;
		margin-top: 20rpx;
		margin-right: 35rpx;
	}
	.bottom .total span{
		color: #ff7000;
	}
	.bottom .total span span{
		font-size: 34rpx;
	}
	.footer{
		position: relative;
		bottom: -15%;
		right: 0%;
	}
	.choose{
		float: right;
		width: 100rpx;
		background: #fff;
		
		padding: 10rpx 20rpx;
		border: 1px solid #000;
		border-radius: 2px;
		margin-right: 20rpx;
		font-size: 26rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
	}
	.isChoose{
		/* background: #000; */
		background-image: linear-gradient(to right,#ff9000 0,#ff7000 100%);
		color: #fff;
		border: none;
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
	.h-right {
		position: relative;
		height: 100%;
		left: 50%;
		bottom: 0;
		/* float: right; */
	}
	.h-right .h-c-img-count image {
		position: relative;
		width: 32rpx;
		height: 32rpx;
	}
	.h-c-img-count input {
		width: 60rpx;
		text-align: center;
		color: #000;
	}
	
	.h-c-img-count {
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
	}
	.price{
		width:200px;
		margin-left:26rpx;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: bold;
		color: #000;
	}
	.price image{
		display: block;
		width: 10%;
		height: 10%;
	}
	.middle view{
		float: left;
	}

	.middle image{
		float: left;
		width:180rpx;
		height: 180rpx;
		border-radius: 20rpx;
		/* margin-left: 20rpx; */
	}
	.item{
		width: 690rpx;
		height: 300rpx;
		background: #fff;
		border-radius: 20rpx;
		margin:30rpx;
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
