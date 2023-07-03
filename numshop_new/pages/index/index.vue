<template>
	<view class="container">
		<view class="header">
			<!--滑动容器-->
			<swiper indicator-dots='true' autoplay='true'>
				<swiper-item v-for="s in slideshows" :key="s.id">
					<image mode="aspectFill" class="item" :src="s.imageurl" alt="加载失败"/>
				</swiper-item>
			</swiper>
		</view>
		<!--定义了一个广告位
			mode--图片裁剪、缩放的模式】
			aspectFit--全部展示
			
			精选商品
		-->
		<view class="banner">
			<image mode="aspectFit" src="../../static/index/banner.png" />
		</view>
		<!--定义了一个文本内容-->
		<view class="content">
			<!--不用展示商品的id但是需要有id-->
			<view class="c-item" v-for="product in products" :key="product.id" @tap="goDetail(product.id)">
				<!-- <view class="content-img">
					
				</view> -->
				<view class="c-img-c">
					<image mode="aspectFit" :src="product.imageurl"></image>
				</view>
				<view class="i-name">
					{{product.name}}
				</view>
				<view class="i-price">
					￥{{product.price}}
				</view>
			</view>
		</view>
		<view class="loading">
			—— {{loading}} ——
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				searchObj: {
					pageIndex: 1,
					pageSize: 4
				},
				products: [],
				total: 0,
				loading: '加载中', //有加载或者到底了
				slideshows: [] //容器的滑动界面图片
			}
		},
		//触底刷新
		onReachBottom() {
			if (this.total <= this.products.length) {
				return;
			}
			this.searchObj.pageIndex = this.searchObj.pageIndex + 1;
			this.loadProduct()
		},
		onShow() {
			var that = this;
			this.products = [];
			this.searchObj.pageIndex = 1;
			this.loadSlideshow();
			this.loadProduct();
			this.$api.loadProductTotal(this.searchObj).then(resp => {
				that.total = resp
				if (that.total == that.products.length) {
					that.loading = '到底了'
				}
			})
		},
		methods: {
			//tap应该是点击商品后发送的
			goDetail(id) {
				//从内存中获取，是否有相关配置信息
				var userInfo = uni.getStorageSync("userInfoObj");
				//判定是否登录
				if (!userInfo) {
					uni.showToast({
						title: "请登录",
						icon: "none",
						duration: 1000
					})
					setTimeout(() => {
						uni.navigateTo({
							url: "/pages/login/login"
						})
					}, 1000)
					return;
				}
				if (userInfo) { //有登录信息
					//判定是否有openid
					if (!userInfo.openid) { //openId不为空
						uni.navigateTo({
							url: '/pages/result2/result2'
						})
						return;
					}
				}
				//没有openid，则跳转授权登录
				//这里是通过栈的形式在内存中存放页面
				uni.navigateTo({
					url: "/pages/detail/detail?id=" + id
				})
			},
			loadSlideshow() {
				var that = this;
				this.$api.loadSlideshow({
					status: 1
				}).then(resp => {
					that.slideshows = resp
				})
			},
			loadProduct() {
				var that = this;
				this.$api.loadProductList(this.searchObj).then(resp => {
					//工具包返回的直接就是data数据--json
					for (var i in resp) {
						that.products.push(resp[i]) //将返回的商品数据添加到数组中
					}
					if (that.total == that.products.length) {
						that.loading = '到底了'
					}
				})
			}
		}
	}
</script>

<style>
	page {
		/**
		 * background-image: linear-gradient(#091323,#243242) ;
		 * 
			background-image: linear-gradient(#091323,#243242) ;
			
		 */
		/* background-color: 	#ffffff; */
	}

	.loading {
		text-align: center;
		color: #000;
		font-size: 20rpx;
	}

	.i-price {
		text-align: center;
		font-weight: bold;
		margin-top: -35rpx;
		/**
		 * lc color
		 */
		color: #000;
		font-size: 30rpx;
		position: relative;
		/* right: -120rpx; */
	}

	.i-name {
		/*lc 商品的姓名*/
		text-align: center;
		font-size: 32rpx;
		/*lc 26 */
		margin-bottom: 26rpx;
		/*lc  */
		margin-top: 40rpx;
		/*30*/
		color: #888888;
	}

	.content-img {
		position: relative;
		bottom: 0rpx;
		font-family: SourceHanSansSC;
		color: #fff;
	}

	.c-img-c image {
	    object-fit:cover;
		width: 100%;
		height: 100%;
	}

	.c-img-c {
		background-color: transparent;
		width: 100%;
		height: 332rpx;
	}

	.content {
		flex-direction: row;
		flex-wrap: wrap;
	}

	.c-item {
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 50%;
		height: 100%;
		margin-bottom: 24rpx;
		border-top: none;
		/* background-color: white; */
	}

	.banner {
		text-align: center;
		height: 74rpx;
		margin-top: 38rpx;
	}

	.t-text {
		/*商品头部信息*/
		text-align: center;
		color: #8FA9C7;
		position: absolute;
		top: 0rpx;
		left: 120rpx;
		font-size: 20rpx;
	}

	.title {
		position: relative;
		width: 100%;

	}

	.title image {
		width: 100%;
		height: 69rpx;
		border-radius: 20rpx;

	}

	.banner image {
		width: 100%;
		height: 100%;

	}

	.content {
		display: flex;
		margin-top: 38rpx;
	}

	.header {
		margin-top: 0rpx;
		text-align: center;
	}

	.item {
		width: 95%;
		height: 100%;
		box-shadow: 4px 4px 4px #888888;
	}
</style>
