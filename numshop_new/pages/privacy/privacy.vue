<template>
	<view>
		<view class="content">
			<rich-text :nodes="protocol.content"></rich-text>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				protocol:{}
			}
		},
		onShow(){
			this.loadProtocol()
		},
		methods: {
			loadProtocol(){
				var that = this;
				this.$api.loadAgreement({
					types:'隐私政策'
				}).then(resp=>{
					that.protocol = resp[0]
				})
			}
		}
	}
</script>

<style>
	.content{
		width:622rpx;
		margin: 30rpx;
		border-radius: 20rpx;
		padding-bottom: 30rpx;
		background: #fff;
		font-size: 30rpx;
		font-family: SourceHanSansSC;
		font-weight: 400;
		color: #000;
		padding:34rpx;
	}
</style>
