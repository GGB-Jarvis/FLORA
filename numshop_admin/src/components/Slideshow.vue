<template>
	<div class="app-container">

		<el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button>
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column prop="id" label="ID" />
			<el-table-column prop="imageurl" label="图片">
				<template slot-scope="scope">
					<el-image :src="scope.row.imageurl" alt="" fit="contain" />
				</template>
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					{{scope.row.status==1?'启用':'禁用'}}
				</template>
			</el-table-column>
			<el-table-column prop="createtime" label="创建时间" />
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
				<el-form-item prop="imageurl" label="图片">
					<el-upload class="avatar-uploader" action="http://localhost:8080/flora/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
						<img v-if="dialogFormData.imageurl" :src="dialogFormData.imageurl" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item>
				<el-form-item prop="status" label="状态">
					<el-radio-group v-model="dialogFormData.status">
						<el-radio label="1">启用</el-radio>
						<el-radio label="2">禁用</el-radio>
					</el-radio-group>
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
					imageurl: '',
					status: '',
					createtime: '',
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
					imageurl: '',
					status: '',
					createtime: ''
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					imageurl: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					status: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					createtime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					]
				}
			}
		},
		created() {
			this.loadTable()
		},
		methods: {
			handleAvatarSuccess(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
				this.dialogFormData.imageurl = res
			},
			loadTable() {
				this.$http.post('/slideshow/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/slideshow/total', this.searchFormData).then(resp => {
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
					that.$http.post('/slideshow/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/slideshow/update' : '/slideshow/add'
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
