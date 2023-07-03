<template>
	<div class="app-container">
		<el-form :inline="true" :model="searchFormData" class="demo-form-inline" size="mini">
			<el-form-item label="用户名">
				<el-input v-model="searchFormData.username" :clearable="true" placeholder="用户名" style="width: 250px;" />
			</el-form-item>
			
			<el-form-item label="真实姓名">
				<el-input v-model="searchFormData.name" :clearable="true" placeholder="真实姓名" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="电话号码">
				<el-input v-model="searchFormData.phone" :clearable="true" placeholder="电话号码" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="身份证号码">
				<el-input v-model="searchFormData.cardno" :clearable="true" placeholder="身份证号码" style="width: 250px;" />
			</el-form-item>
			
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="loadTable">查询</el-button>
			</el-form-item>
		</el-form>
		<el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button>
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column prop="id" label="ID" />
			<el-table-column prop="username" label="用户名" />
			<el-table-column prop="realname" label="昵称" />
			<el-table-column prop="address" label="草田链地址" />
			<el-table-column prop="blockpassword" label="草田链密码" />
			<el-table-column prop="privateKey" label="草田链私钥" />
			<el-table-column prop="name" label="真实姓名" />
			<el-table-column prop="phone" label="电话号码" />
			<el-table-column prop="cardno" label="身份证号码" />
			<el-table-column prop="authentication" label="是否认证" />
			<el-table-column prop="registertime" label="注册时间" />
			<el-table-column prop="authenticationtime" label="认证时间" />
			<el-table-column prop="reserved" label="角色" />
			
			<el-table-column>
				<template slot-scope="scope">
					<el-button type="text" size="mini" @click="onTableUpdate(scope.row)">修改</el-button>
					<el-button type="text" size="mini" @click="onTableDelete(scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination background style="margin-top: 5px" :current-page="searchFormData.pageIndex"
			:page-size="searchFormData.pageSize" :page-sizes="[5, 10, 20, 40]" :total="pageInfo.total"
			layout="total, sizes, prev, pager, next" @size-change="onSizeChange" @current-change="onPageIndexChange" />
		<!--dialog-->
		<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false"
			@close="resetForm('dialogForm')">
			<el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogFormData" label-width="120px" size="mini">
				<el-form-item prop="username" label="用户名">
					<el-input v-model="dialogFormData.username" />
				</el-form-item>
				<el-form-item prop="password" label="密码">
					<el-input v-model="dialogFormData.password" />
				</el-form-item>
				<el-form-item prop="realname" label="昵称">
					<el-input v-model="dialogFormData.realname" />
				</el-form-item>
				
				<el-form-item prop="name" label="真实姓名">
					<el-input v-model="dialogFormData.name" />
				</el-form-item>
				<el-form-item prop="phone" label="电话号码">
					<el-input v-model="dialogFormData.phone" />
				</el-form-item>
				<el-form-item prop="cardno" label="身份证号码">
					<el-input v-model="dialogFormData.cardno" />
				</el-form-item>
			
				<el-form-item prop="reserved" label="角色">
					<el-input v-model="dialogFormData.reserved" />
				</el-form-item>
				
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click="dialogVisible = false">取 消</el-button>
				<el-button type="primary" @click="onDialogSave">保 存</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
	import axios from 'axios'
	// 创建axios实例
	const client = axios.create({
		baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
		timeout: 60000 // 请求超时时间,60秒
	})
	export default {
		data() {
			return {
				searchFormData: {
					id: '',
					username: '',
					password: '',
					realname: '',
					openid: '',
					imageurl: '',
					name: '',
					phone: '',
					cardno: '',
					authentication: '',
					registertime: '',
					authenticationtime: '',
					reserved: '',
					pageIndex: 1,
					pageSize: 10
				},
				pageInfo: {
					rows: [],
					total: 0
				},
				dialogVisible: false,
				dialogTitle: '',
				dialogFormData: {
					id: '',
					username: '',
					password: '',
					realname: '',
					openid: '',
					imageurl: '',
					name: '',
					phone: '',
					cardno: '',
					authentication: '',
					registertime: '',
					authenticationtime: '',
					reserved: '',
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					username: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					password: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					realname: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					openid: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					imageurl: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					name: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					phone: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					cardno: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					authentication: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					registertime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					authenticationtime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					]
				}
			}
		},
		created() {
			this.loadTable()
		},
		methods: {
			loadTable() {
				this.$http.post('/user/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/user/total', this.searchFormData).then(resp => {
					this.pageInfo.total = resp
				})
			},
			onTableUpdate(row) {
				this.dialogTitle = '修改'
				this.dialogVisible = true
				this.$nextTick(() => {
					Object.assign(this.dialogFormData, row)
				})
			},
			onTableDelete(row) {
				var that = this;
				this.$confirm('确认要删除该记录吗？', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(function() {
					const data = {
						id: row.id
					}
					that.$http.post('/user/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/user/update' : '/user/add'
						this.$http.post(uri, this.dialogFormData).then(() => {
							this.dialogVisible = false
							this.loadTable()
						})
					}
				})
			},
			onSizeChange(size) {
				this.searchFormData.pageSize = size
				this.loadTable()
			},
			onAdd() {
				this.dialogTitle = '新增'
				this.dialogVisible = true
				this.dialogFormData.id = 0
			},
			onPageIndexChange(pageIndex) {
				this.searchFormData.pageIndex = pageIndex
				this.loadTable()
			}
		}
	}
</script>
