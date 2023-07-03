<template>
	<div class="app-container">
		<el-form :inline="true" :model="searchFormData" class="demo-form-inline" size="mini">
			<el-form-item label="内容">
				<el-input v-model="searchFormData.content" :clearable="true" placeholder="内容" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="发送时间">
				<el-input v-model="searchFormData.createtime" :clearable="true" placeholder="发送时间"
					style="width: 250px;" />
			</el-form-item>
			<el-form-item label="发送手机">
				<el-input v-model="searchFormData.phone" :clearable="true" placeholder="发送手机" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="有效期">
				<el-input v-model="searchFormData.validitytime" :clearable="true" placeholder="有效期"
					style="width: 250px;" />
			</el-form-item>
			<el-form-item label="类型">
				<el-input v-model="searchFormData.type" :clearable="true" placeholder="类型" style="width: 250px;" />
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="loadTable">查询</el-button>
			</el-form-item>
		</el-form>
		<el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button>
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column prop="id" label="ID" />
			<el-table-column prop="content" label="内容" />
			<el-table-column prop="createtime" label="发送时间" />
			<el-table-column prop="phone" label="发送手机" />
			<el-table-column prop="validitytime" label="有效期" />
			<el-table-column prop="type" label="类型" />
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
				<el-form-item prop="content" label="内容">
					<el-input v-model="dialogFormData.content" />
				</el-form-item>
				<el-form-item prop="createtime" label="发送时间">
					<el-input v-model="dialogFormData.createtime" />
				</el-form-item>
				<el-form-item prop="phone" label="发送手机">
					<el-input v-model="dialogFormData.phone" />
				</el-form-item>
				<el-form-item prop="validitytime" label="有效期">
					<el-input v-model="dialogFormData.validitytime" />
				</el-form-item>
				<el-form-item prop="type" label="类型">
					<el-input v-model="dialogFormData.type" />
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
					content: '',
					createtime: '',
					phone: '',
					validitytime: '',
					type: '',
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
					content: '',
					createtime: '',
					phone: '',
					validitytime: '',
					type: ''
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					content: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					createtime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					phone: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					validitytime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					type: [
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
				this.$http.post('/notes/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/notes/total', this.searchFormData).then(resp => {
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
					that.$http.post('/notes/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/notes/update' : '/notes/add'
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
