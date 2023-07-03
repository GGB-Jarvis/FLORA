<template>
	<view>
		<!-- 若用户收货信息为空 -->
		<view class="noAddress" v-if="addresses.length==0">暂无收货地址</view>
		<view class="content">
			<view v-for="address in addresses" :key="address.id">
				<view class="info">
					<view class="text1">{{address.adressee}}</view>
					<view class="text2">{{address.phone}}</view>
					<view class="text3">{{address.completedaddress}}</view>
					<view class="text4">{{address.isdefault}}</view>
				</view>
				<view class="modify">
					<view class="update-button iconfont" @tap="openupdateAddress(address.id)">&#xe63e</view>
					<view class="delete-button iconfont" @tap="deleteAddress(address.id)">&#xe7cc</view>
				</view>
			</view>
		</view>
		<view class="noAddress"  @tap="openAddress()">
			<span class="iconfont addicon">&#xe600</span>点击添加收货地址
		</view>
		<view class="filltable" :class="{'isAdd':isAdd}">
			<view>
				<view class="row">
					<view class="">{{method}}</view>
					<view class="iconfont exit" @tap="exit()">&#xeaf2</view>
				</view>
				
				<view class="row">
					<view>收件人：</view>
					<input type="text" id="addressee" class="text" v-model="addressee"/>
				</view>
				<view class="row">
					<view>手机号：</view>
					<input type="text" id="phone" class="text"  v-model="phone"/>
				</view>
				<view class="row">
					<view>地址：</view>
					<input type="text" id="address" class="text" v-model="address"/>
				</view>
				<view class="add-button" @tap="action()">
					确定
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				phone:"",
				addressee:"",
				address:"",
				method:"",
				idForModify:"",
				userInfo:{},
				addresses:[],
				isAdd:false,
				isUpdate:false,
				edit:false
			}
		},
		//页面显示时---获取用户相关信息
		onShow(){
			var userInfo = uni.getStorageSync("userInfoObj")
			if(!userInfo){
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
			this.userInfo = userInfo;
			this.getAddress()
		},
		methods: {
			exit(){
				this.isAdd = false
			},
			openAddress(){
				this.isAdd = true
				this.method = "新增"
			},
			action(){
				if(this.method == "新增"){
					this.addAddress()
				}
				if(this.method == "修改"){
					this.updateAddress(this.idForModify)
				}
			},
			getAddress(){
				var that = this;
				this.$api.getAddressList({
					userid:that.userInfo.id
				}).then(resp=>{
					if(resp==0){
						return;
					}else{
						for(let i = 0;i<resp.length;i++){
							if(resp[i].isdefault == 0){
								resp[i].isdefault = ""
							}else{
								resp[i].isdefault = "默认地址"
							}
						}
						that.addresses = resp
					}
				})
			},
			addAddress(){
				var phone = this.phone;
				var addressee = this.addressee;
				var address = this.address;
				if (!addressee) {
					uni.showToast({
						title: "请输入收件人",
						icon: "error",
						duration: 1000
					})
					return;
				}
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
					uni.showToast({ title: '手机号码不正确', icon: "error" });
					return;
				}
				if (!address) {
					uni.showToast({
						title: "请输入地址",
						icon: "error",
						duration: 1000
					})
					return;
				}
				var that = this;
				this.$api.addAddress({
					userid:that.userInfo.id,
					phone:phone,
					adressee:addressee,
					completedaddress:address,
				}).then(resp=>{
					if(resp==0){
						uni.showToast({
							title:"添加失败",
							icon:"fail",
							duration:1000
						})
						return;
					}else{
						uni.showToast({
							title:"添加成功",
							duration:1000
						})
						this.getAddress()
					}
				})
				this.isAdd = false
			},
			openupdateAddress(id){
				this.isAdd = true
				this.method = "修改"
				this.idForModify = id
			},
			updateAddress(id){
				var phone = this.phone;
				var addressee = this.addressee;
				var address = this.address;
				if (!addressee) {
					uni.showToast({
						title: "请输入收件人",
						icon: "error",
						duration: 1000
					})
					return;
				}
				if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
					uni.showToast({ title: '手机号码不正确', icon: "error" });
					return;
				}
				if (!address) {
					uni.showToast({
						title: "请输入地址",
						icon: "error",
						duration: 1000
					})
					return;
				}
				
				
				var that = this;
				this.$api.updateAddress({
					id:id,
					userid:that.userInfo.id,
					phone:phone,
					adressee:addressee,
					completedaddress:address,
				}).then(resp=>{
					if(resp==0){
						uni.showToast({
							title:"修改失败",
							icon:"fail",
							duration:1000
						})
						return;
					}else{
						uni.showToast({
							title:"修改成功",
							icon:"success",
							duration:1000
						})
						this.getAddress()
					}
				})
				this.isAdd = false
			},
			deleteAddress(id){
				var that = this;
				this.$api.delAddress({
					id:id
				}).then(resp=>{
					if(resp==0){
						uni.showToast({
							title:"删除失败",
							duration:1000
						})
						return;
					}else{
						uni.showToast({
							title:"删除成功",
							icon:"success",
							duration:1000
						})
						this.getAddress()
					}
				})
			}
		}
	}
</script>

<style>
	.noAddress{
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
	.addicon{
		font-size: 30rpx;
		padding-right: 10rpx;
	}
	.filltable{
		display: none;
		position: fixed;
		top: 25%;
		width: 100%;
		z-index: 999;
	}
	.filltable>view{
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
		margin: 0 auto;
		width: 90%;
		background-color: #fff;
		
	}
	.filltable>view{
		transition: all .1s;
	}
	
	.filltable .row{
		display: flex;
		flex-direction: row;
		width:inherit;
		margin-left:20rpx;
		height: 98rpx;
		border: 1px solid #49627E;
		line-height: 98rpx;
		margin-top:32rpx;
		border-radius: 10rpx;
	}
	.filltable .row:nth-child(1){
		border: none;
		height: 70rpx;
		font-size: 30rpx;
	}
	.filltable .row>view{
		width: 20%;
		text-align: center;
	}
	.filltable .row input{
		width: 80%;
		height: 100%;
	}
	
	.filltable view .add-button{
		display: flex;
		justify-content: center;
		align-items: center;
		width: 200rpx;
		height: 70rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		background-color: #49627E;
		border-radius: 10px;
		color: #fff;
		font-size: 30rpx;
	}
	.filltable view .update-button2{
		display: flex;
		justify-content: center;
		align-items: center;
		width: 200rpx;
		height: 70rpx;
		margin-top: 20rpx;
		margin-bottom: 10rpx;
		background-color: #49627E;
		border-radius: 10px;
		color: #fff;
		font-size: 30rpx;
	}
	.filltable view .exit{
		display: flex;
		justify-content: flex-end;
		margin-right: 50rpx;
		margin-top: 20rpx;
		width: 100%;
		font-size: 40rpx;
		
		
	}
	.isAdd{
		display: block;
	}
	.isUpdate{
		display: block;
	}
	.content{
		margin-top: 25rpx;
	}
	.content>view{
		display: flex;
		flex-direction: row;
		justify-content: center;
		align-items: center;
		border-bottom: 1px solid #000;
		height: 200rpx;
		border-radius: 0px 0px 10px 10px;
		margin: 0 auto;
		width: 90%;
		background-color: #fff;
	}
	.content>view .info,.content>view .modify{
		height: 100%;
		display: flex;
	}
	.content>view .info{
		flex-direction: column;
		justify-content: center;
		margin-left: 20rpx;
		width: 80%;
	}
	.content>view .modify{
		flex-direction: row;
		justify-content: center;
		align-items: center;
		width: 20%;
	}
	.content>view .info .text1{font-size: 30rpx;}
	.content>view .info .text2{font-size: 20rpx;}
	.content>view .info .text3{font-size: 15rpx;}
	.content>view .modify view{
		width: 100%;
	}
	.content>view .modify .update-button{
	}
	.content>view .modify .delete-button{
		
	}
	
</style>
