<template>
	<div class="app-container">
		<el-form :inline="true" :model="searchFormData" class="demo-form-inline" size="mini">
			<el-form-item label="订单">
				<el-input v-model="searchFormData.orderid" :clearable="true" placeholder="订单" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="商品">
				<el-input v-model="searchFormData.productid" :clearable="true" placeholder="商品" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="数量">
				<el-input v-model="searchFormData.count" :clearable="true" placeholder="数量" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="商品编码">
				<el-input v-model="searchFormData.productno" :clearable="true" placeholder="商品编码"
					style="width: 250px;" />
			</el-form-item>
			<el-form-item label="商品单价">
				<el-input v-model="searchFormData.price" :clearable="true" placeholder="商品单价" style="width: 250px;" />
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="loadTable">查询</el-button>
			</el-form-item>
		</el-form>
		<el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button>
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column prop="id" label="ID" />
			<el-table-column prop="orderid" label="订单" />
			<el-table-column prop="productid" label="商品" />
			<el-table-column prop="count" label="数量" />
			<el-table-column prop="productno" label="商品编码" />
			<el-table-column prop="price" label="商品单价" />
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
				<el-form-item prop="orderid" label="订单">
					<el-input v-model="dialogFormData.orderid" />
				</el-form-item>
				<el-form-item prop="productid" label="商品">
					<el-input v-model="dialogFormData.productid" />
				</el-form-item>
				<el-form-item prop="count" label="数量">
					<el-input v-model="dialogFormData.count" />
				</el-form-item>
				<el-form-item prop="productno" label="商品编码">
					<el-input v-model="dialogFormData.productno" />
				</el-form-item>
				<el-form-item prop="price" label="商品单价">
					<el-input v-model="dialogFormData.price" />
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
					orderid: '',
					productid: '',
					count: '',
					productno: '',
					price: '',
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
					orderid: '',
					productid: '',
					count: '',
					productno: '',
					price: ''
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					orderid: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					productid: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					count: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					productno: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					price: [
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
				this.$http.post('/details/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/details/total', this.searchFormData).then(resp => {
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
					that.$http.post('/details/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/details/update' : '/details/add'
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
