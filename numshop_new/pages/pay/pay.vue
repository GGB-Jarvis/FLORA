<template>
	<!--用户订单创建成功后，跳转的页面，页面接收订单的id
		显示出的订单页面
		可能是一个--就是购买后直接跳转的或者是--一次性付清的
	-->
	<view>
		<view class="head">
			<view class="create">
				订单创建成功
			</view>
			<view class="detail">
				订单详情
			</view>
		</view>
		
		<view class="content">
			<!--遍历时最好绑定key属性-->
			<view class="order">
				<image mode="aspectFit" :src="goodslist[0].product.imageurl"></image>
				<view class="name">
					<view class="left">
						{{goodslist[0].product.name}}
					</view>
					<view class="right">
						￥{{goodslist[0].product.price}}
					</view>
				</view>
				<view class="count">
					X{{goodslist.length}}
				</view>
			</view>
			<view class="amount">
				共{{goodslist.length}}份小计￥{{goodslist[0].product.price*goodslist.length}}元
			</view>
		</view>
		<view class="button" @tap="submit">
			确认并支付订单
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				orderId: '',//订单的id
				money: 0,
				goodslist: [],//商品列表//通过订单查询相关数据//数组
				
				param: {
					appId: "", //商户ID（必填）
					type: "alipay", //支付类型alipay(正常)wxpay（必填）
					out_trade_no: "12343455", //你的系统生成得订单号（必填）
					notify_url: '', //异步通知地址（非必填,不填服务器端接收不到付款通知）
					return_url: '', //支付成功后页面内跳转地址，本地已做轮询（非必填。默认值保留）
					name: "这是一个测试5", //商品名称（必填）
					money: "0.01", //商品价格（必填）number类型
					sitename: "localhost:8080", //当前请求网站地址（非必填，默认值保留）
					sign: "", //签名后字符串（该值不用管，已经封装了自动生成签名字符串）
					signType: "MD5", //签名方式（默认值保留
					timeStamp: "",
					nonceStr: '',
					package: ''
				}
			}
		},
		onLoad(options) {
			//先获取到订单的id
			var orderId = options.id;
			this.orderId = orderId;
			this.loadDetails()
		},
		methods: {
			//提交支付请求
			submit() {
				var that = this;
				if (that.money <= 0) {
					//这里支付有应该是留给一些免费的东西
					//不用花钱判断你的订单中是否有该商品--通过数量判断
					//用不到支付接口
					if (that.goodslist.length < 1) {
						return;
					} else {
						for (var i = 0; i < that.goodslist.length; i++) {
							var g = that.goodslist[i]
							that.money += g.product.reserved * g.count;
						}
						if (that.money <= 0) {
							uni.request({
								url: 'http://localhost:8080/flora/orders/update',
								dataType: 'json',
								data: {
									id: that.orderId,
									status: '已完成'
								}
							}).then(res => {
								uni.showToast({
									title: "支付成功",
									duration: 1000,
									icon: 'none'
								})
								setTimeout(() => {
									uni.navigateTo({
										url: '/pages/orders/orders'
									})
								}, 1000)
							})
							return;
						}
					}

				}
				this.$api.payOrder({
					id: that.orderId
				}).then(res => {
					that.param.appId = res.appId;
					that.param.timeStamp = res.timeStamp;
					that.param.nonceStr = res.nonceStr;
					that.param.package = res.package;
					that.param.paySign = res.paySign;
					that.callpay()
				})
			},
			callpay() {
				//WeixinJSBridge 微信内置私有网
				if (typeof WeixinJSBridge == "undefined") {
					if (document.addEventListener) {
						document.addEventListener('WeixinJSBridgeReady', onBridgeReady,
							false);
					} else if (document.attachEvent) {
						document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
						document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
					}
				} else {
					this.onBridgeReady();
				}
			},
			onBridgeReady() {
				var that = this;
				WeixinJSBridge.invoke('getBrandWCPayRequest', {//这里应该就是处理订单问题
					"appId": that.param.appId,
					"timeStamp": that.param.timeStamp,
					"nonceStr": that.param.nonceStr,
					"package": that.param.package,
					"signType": that.param.signType,
					"paySign": that.param.paySign
				}, function(res) { // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回  ok，但并不保证它绝对可靠。
					//alert(res.err_msg);
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						//$("h1").text("恭喜您，支付成功，即将跳转订单界面...");
						uni.showToast({
							title: "支付成功",
							icon: 'none'
						})
					}
					if (res.err_msg == "get_brand_wcpay_request:cancel") {
						uni.showToast({
							title: "取消支付",
							icon: 'none'
						})
					}
					if (res.err_msg == "get_brand_wcpay_request:fail") {
						uni.showToast({
							title: "支付失败",
							icon: 'none'
						})
					}
					var tim = setTimeout(function() {
						//跳转到支付页面
						window.location.href = "http://localhost:8080/#/pages/orders/orders"
					}, 1000);
				});
			},
			//@lc
			//通过订单号加载订单的具体细节并展示
			//可能是页面传值有点不方便
			loadDetails() {
				var that = this;
				var orderId = this.orderId;
				var that = this;
				this.$api.orderDetails({
					orderid: orderId
				}).then(resp => {
					if (resp) {
						that.goodslist = resp//将返回的json数组转化为数组对象的形式
						for (var i = 0; i < that.goodslist.length; i++) {
							var g = that.goodslist[i]//计算总金额
							that.money += g.product.reserved * g.count;
						}
					}
				})

			},
		}
	}
</script>

<style>
	page {
		
		/*background: linear-gradient(0deg, #091323 0%, #243242 100%);*/
		background-color: white;
	}

	.amount {
		/**
		 * 底部信息
		 */
		color: red;
		clear: both;
		text-align: right;
		padding-right: 30rpx;
		padding-top: 10rpx;
		border-top: 1rpx solid #8FA9C7;
	}

	.count {
		float: left;
		margin-left: 26rpx;
		margin-top: 30rpx;
	}

	.name {
		/**
		 * 订单的名字
		 */
		float: left;
		margin-left: 26rpx;
		font-size: 32rpx;
		font-weight: bold;
		width: 432rpx;
		margin-top: 30rpx;
	}

	.left {
		float: left;
	}

	.right {
		/**
		 * 内容右侧部分得到单价
		 */
		float: right;
		color: red;
		
	}

	.content {
		/**
		 * 内容部分
		 */
		width: 690rpx;
		margin: 30rpx;
		background: white;/*35475D*/
		color: black;/*#8FA9C7*/
		border-radius: 20rpx;
		padding-top: 20rpx;
		padding-bottom: 30rpx;
		overflow: hidden;
		
		/**
		 * 设置盒子阴影
		 */
		/*添加盒子阴影*/
		box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		
	}

	.order {
		margin-bottom: 20rpx;
		overflow: hidden;
	}

	.order image {
		width: 180rpx;
		height: 180rpx;
		margin-left: 22rpx;
		float: left;
		border-radius: 10rpx;
	}

	.button {
		width: 690rpx;
		height: 80rpx;
		margin: 30rpx;
		font-size: 34rpx;
		border-radius: 20rpx;
		background: yellow;/*35475D*/
		color: black;/*#8FA9C7*/
		font-family: SourceHanSansSC;
		line-height: 80rpx;
		text-align: center;
	}

	.head {
		width: 690rpx;
		height: 140rpx;
		background: white;/*35475D*/
		border-radius: 20rpx;
		margin: 30rpx;
		/*添加盒子阴影*/
		box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
		
	}

	.create {
		/*创建成功的文字*/
		color: black;/*#8FA9C7*/
		font-size: 40rpx;
		margin-left: 24rpx;
		padding-top: 30rpx;
		font-family: SourceHanSansSC;
		/*添加盒子阴影*/
		
	}

	.detail {
		color: #8FA9C7;
		font-size: 20rpx;
		margin-left: 24rpx;
		font-family: SourceHanSansSC;
	}
</style>
