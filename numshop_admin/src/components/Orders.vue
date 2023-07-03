<template>
	<div class="app-container">
		<el-form :inline="true" :model="searchFormData" class="demo-form-inline" size="mini">

			<el-form-item label="支付方式">
				<el-input v-model="searchFormData.paytype" :clearable="true" placeholder="支付方式" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="支付状态">
				<el-input v-model="searchFormData.status" :clearable="true" placeholder="支付状态" style="width: 250px;" />
			</el-form-item>

			<el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="loadTable">查询</el-button>
			</el-form-item>
		</el-form>
		<!-- <el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button> -->
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column prop="id" label="ID" />
			<el-table-column prop="userid" label="商品" >
				<template slot-scope="scope">
					{{scope.row.list[0]?scope.row.list[0].product.name:''}}
				</template>
			</el-table-column>
			<el-table-column prop="userid" label="购买者" >
				<template slot-scope="scope">
					{{scope.row.user?(scope.row.user.name?scope.row.user.name:"未实名认证:"+scope.row.userid):"未通过公众号进入"}}
				</template>
			</el-table-column>
			
			<el-table-column prop="createtime" label="购买数量">
				<template slot-scope="scope">
					{{scope.row.list!=null?scope.row.list.length:'0'}}
				</template>
			</el-table-column>
			<el-table-column prop="createtime" label="购买时间" />
			<el-table-column prop="paytime" label="支付时间" />
			<el-table-column prop="paytype" label="支付方式" />
			<el-table-column prop="status" label="支付状态" />
			<!-- <el-table-column>
				<template slot-scope="scope">
					<el-button type="text" size="mini" @click="onTableUpdate(scope.row)">修改</el-button>
					<el-button type="text" size="mini" @click="onTableDelete(scope.row)">删除</el-button>
				</template>
			</el-table-column> -->
		</el-table>
		<el-pagination background style="margin-top: 5px" :current-page="searchFormData.pageIndex"
			:page-size="searchFormData.pageSize" :page-sizes="[5, 10, 20, 40]" :total="pageInfo.total"
			layout="total, sizes, prev, pager, next" @size-change="onSizeChange" @current-change="onPageIndexChange" />
		<!--dialog-->
		<el-dialog :title="dialogTitle" :visible.sync="dialogVisible" :close-on-click-modal="false"
			@close="resetForm('dialogForm')">
			<el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogFormData" label-width="120px" size="mini">
				<el-form-item prop="userid" label="购买者">
					<el-input v-model="dialogFormData.userid" />
				</el-form-item>
				<el-form-item prop="createtime" label="购买时间">
					<el-input v-model="dialogFormData.createtime" />
				</el-form-item>
				<el-form-item prop="paytime" label="支付时间">
					<el-input v-model="dialogFormData.paytime" />
				</el-form-item>
				<el-form-item prop="paytype" label="支付方式">
					<el-input v-model="dialogFormData.paytype" />
				</el-form-item>
				<el-form-item prop="status" label="支付状态">
					<el-input v-model="dialogFormData.status" />
				</el-form-item>
				<el-form-item prop="reserved" label="reserved">
					<el-input v-model="dialogFormData.reserved" />
				</el-form-item>
				<el-form-item prop="reserved2" label="reserved2">
					<el-input v-model="dialogFormData.reserved2" />
				</el-form-item>
				<el-form-item prop="reserved3" label="reserved3">
					<el-input v-model="dialogFormData.reserved3" />
				</el-form-item>
				<el-form-item prop="reserved4" label="reserved4">
					<el-input v-model="dialogFormData.reserved4" />
				</el-form-item>
				<el-form-item prop="reserved5" label="reserved5">
					<el-input v-model="dialogFormData.reserved5" />
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
					userid: '',
					createtime: '',
					paytime: '',
					paytype: '',
					status: '',
					reserved: '',
					reserved2: '',
					reserved3: '',
					reserved4: '',
					reserved5: '',
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
					userid: '',
					createtime: '',
					paytime: '',
					paytype: '',
					status: '',
					reserved: '',
					reserved2: '',
					reserved3: '',
					reserved4: '',
					reserved5: ''
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					userid: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					createtime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					paytime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					paytype: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					status: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved2: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved3: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved4: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					reserved5: [
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
				this.$http.post('/orders/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/orders/total', this.searchFormData).then(resp => {
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
					that.$http.post('/orders/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/orders/update' : '/orders/add'
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
