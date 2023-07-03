<template>
	<div class="app-container">
		<el-form :inline="true" :model="searchFormData" class="demo-form-inline" size="mini">
			<el-form-item label="商品名称">
				<el-input v-model="searchFormData.name" :clearable="true" placeholder="商品名称" style="width: 250px;" />
			</el-form-item>
			<el-form-item label="标签">
				<el-input v-model="searchFormData.tag" :clearable="true" placeholder="标签" style="width: 250px;" />
			</el-form-item>
			<el-form-item>
				<el-button type="primary" icon="el-icon-search" @click="loadTable">查询</el-button>
			</el-form-item>
		</el-form>
		<el-button type="primary" size="mini" icon="el-icon-plus" style="margin-bottom: 10px;" @click="onAdd">新增
		</el-button>
		<el-table :data="pageInfo.rows" border highlight-current-row>

			<el-table-column prop="name" label="商品名称" />
			<el-table-column prop="reserved3" label="商品类型" />

			<el-table-column prop="count" label="库存数量" />
			<!-- <el-table-column prop="author" label="作者" />
			<el-table-column prop="authorDesc" label="作者简介" /> -->
			<el-table-column prop="description" label="商品描述" />
			<!-- <el-table-column prop="tokenId" label="口令" /> -->
			<el-table-column prop="imageurl" label="封面图片">
				<template slot-scope="scope">
					<img style="width: 150px;" :src="scope.row.imageurl" />
				</template>
			</el-table-column>
			<!-- <el-table-column prop="reserved4" label="音乐">
				<template slot-scope="scope">
					<audio :src="scope.row.reserved4" v-if="scope.row.reserved4" controls></audio>
				</template>
			</el-table-column> -->
			<el-table-column prop="reserved" label="单价" />
			<el-table-column prop="tag" label="标签" />
			<el-table-column prop="createtime" label="创建时间" />
			<el-table-column prop="shoptime" label="售卖时间" />
			<!-- <el-table-column prop="userid" label="创建用户">

				<template slot-scope="scope">
					{{scope.row.user?scope.row.user.realname:''}}
				</template>
			</el-table-column> -->
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
				<el-form-item prop="name" label="商品名称">
					<el-input v-model="dialogFormData.name" />
				</el-form-item>
				<el-form-item prop="count" label="库存数量">
					<el-input v-model="dialogFormData.count" />
				</el-form-item>
				<!-- <el-form-item prop="author" label="作者">
					<el-input v-model="dialogFormData.author" />
				</el-form-item>
				<el-form-item prop="authorDesc" label="作者简介">
					<el-input v-model="dialogFormData.authorDesc" />
				</el-form-item> -->

				<!-- <el-form-item prop="reserved3" label="类型">
					<el-radio-group v-model="dialogFormData.reserved3">
						<el-radio-button label="图片"></el-radio-button>
						<el-radio-button label="音乐"></el-radio-button>
					</el-radio-group>
				</el-form-item> -->
				<!-- <el-form-item prop="reserved4" label="上传音乐" v-if="dialogFormData.reserved3=='音乐'"> -->
					<!-- <el-upload class="avatar-uploader" action="http://artvery.txwsyun.cn:8080/numshop/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess3"> -->
						<!-- <el-upload class="avatar-uploader" action="http://localhost:8080/numshop/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess3" >
						<audio v-if="dialogFormData.reserved4" :src="dialogFormData.reserved4" class="avatar"
							controls />
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
				</el-form-item> -->

				<el-form-item prop="description" label="商品描述">
					<el-input v-model="dialogFormData.description" />
				</el-form-item>

				<el-form-item prop="imageurl" label="封面图片">
					<!-- <el-upload class="avatar-uploader" action="http://artvery.txwsyun.cn:8080/numshop/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess"> -->
						<el-upload class="avatar-uploader" action="http://localhost:8080/flora/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess" >
						<img v-if="dialogFormData.imageurl" :src="dialogFormData.imageurl" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<!-- <el-input v-model="dialogFormData.imageurl" /> -->
				</el-form-item>
				<el-form-item prop="otherurl" label="详情">
					<quill-editor style="background-color: black;" ref="myQuillEditor" v-model="dialogFormData.otherurl"
						class="editor" :options="editorOption" @focus="quillEditorFocus($event)" />
					<!-- <el-upload class="avatar-uploader-img" action="http://artvery.txwsyun.cn:8080/numshop/upload"
						:show-file-list="false" :on-success="uploadImgSuccess" :before-upload="beforeUploadImg"
						:on-error="uploadImgError" :data="{'pathName':''}" /> -->
					<!-- <el-upload class="avatar-uploader" id="upload" action="http://artvery.txwsyun.cn:8080/numshop/upload"
						:show-file-list="false" :on-success="handleAvatarSuccess2" > -->
						<el-upload class="avatar-uploader" action="http://localhost/flora/upload" :show-file-list="false"
							:on-success="handleAvatarSuccess2" > 
						<img v-if="dialogFormData.otherurl" :src="dialogFormData.otherurl" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-input v-model="dialogFormData.otherurl" />

				</el-form-item>
				<el-form-item prop="tag" label="标签">
					<el-input v-model="dialogFormData.tag" />
				</el-form-item>

				<el-form-item prop="reserved" label="单价">
					<el-input v-model="dialogFormData.reserved" />
				</el-form-item>

				<el-form-item prop="shoptime" label="售卖时间">
					<el-date-picker v-model="dialogFormData.shoptime" type="datetime" format="yyyy-MM-dd HH:mm:ss"
						value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择售卖时间">
					</el-date-picker>
					<!-- <el-input v-model="dialogFormData.shoptime" /> -->
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
	// 工具栏配置
	const toolbarOptions = [
		['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
		['blockquote', 'code-block'], // 引用  代码块
		[{ header: 1 }, { header: 2 }], // 1、2 级标题
		[{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
		[{ script: 'sub' }, { script: 'super' }], // 上标/下标
		[{ indent: '-1' }, { indent: '+1' }], // 缩进
		// [{'direction': 'rtl'}],                         // 文本方向
		[{ size: ['small', false, 'large', 'huge'] }], // 字体大小
		[{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
		[{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
		[{ font: [] }], // 字体种类
		[{ align: [] }], // 对齐方式
		['clean'], // 清除文本格式
		// ['link', 'image', 'video'] // 链接、图片
		['link', 'image'] // 链接、图片
	]
	export default {
		data() {
			return {
				editorOption: { // 编辑框操作事件
					theme: 'snow', // or 'bubble'
					placeholder: '请输入详情内容...',
					modules: {
						toolbar: {
							container: toolbarOptions,
							handlers: {
								image: function(value) { // 上传图片
									if (value) {
										document.querySelector('.avatar-uploader-img input').click() // 触发input框选择文件
									} else {
										this.quill.format('image', false)
									}
								},
								link: function(value) { // 添加链接
									if (value) {
										var href = prompt('请输入链接地址：')
										this.quill.format('link', href)
									} else {
										this.quill.format('link', false)
									}
								}
							}
						}
					}
				},
				searchFormData: {
					id: '',
					name: '',
					count: '',
					imageurl: '',
					otherurl: '',
					tag: '',
					createtime: '',
					shoptime: '',
					userid: '',
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
				userInfo: null,
				dialogVisible: false,
				dialogTitle: '',
				dialogFormData: {
					id: '',
					name: '',
					count: '',
					imageurl: '',
					otherurl: '',
					tag: '',
					createtime: '',
					shoptime: '',
					userid: '',
					reserved: '',
					reserved2: '',
					reserved3: '图片',
					reserved4: '',
					reserved5: '',
					authorDesc: '',
					tokenId: '',
					description: '',
					author: ''
				},
				dialogFormRules: {
					id: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					name: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					count: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					imageurl: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					otherurl: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					tag: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					createtime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					shoptime: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					],
					userid: [
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
					reserved5: [
						{ required: true, message: '不能为空', trigger: 'blur' }
					]
				}
			}
		},
		created() {
			this.userInfo = JSON.parse(sessionStorage.getItem("user"))
			console.log(this.userInfo)
			this.loadTable()
		},
		methods: {
			beforeUploadImg(file) { //富文本图片上传前
				const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/gif'
				if (!isJPG) {
					this.$message.error('上传图片只能是 JPG，PNG, GIF 格式!')
				} else {
					// 显示loading动画
					this.quillUpdate = true
				}
				return isJPG
			},

			uploadImgSuccess(res, file) { //富文本图片上传成功
				// res为图片服务器返回的数据
				// 获取富文本组件实例
				const quill = this.$refs.myQuillEditor.quill

				//这里需要注意自己文件上传接口返回内容，我这里code=0表示上传成功，返回的文件地址：res.data.src
				if (!res) {
					this.$message.error(res.msg)
					//this.$message.error('图片插入失败!')
				} else {
					console.info(res)
					// 获取光标所在位置
					const length = quill.getSelection().index
					// 插入图片  res.info为服务器返回的图片地址
					quill.insertEmbed(length, 'image', res)
					// 调整光标到最后
					quill.setSelection(length + 1)
				}
				// loading动画消失
				this.quillUpdate = false
			},
			uploadImgError() { //富文本图片上传失败
				// loading动画消失
				this.quillUpdate = false
				this.$message.error('图片插入失败!')
			},
			quillEditorFocus(event) { //富文本的获得焦点事件
				// 在这里可以设置富文本编辑器是否可编辑
				//event.enable(false);//不可输入和编辑
				//event.enable(true);//可以输入和编辑
			},
			handleAvatarSuccess(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
				this.dialogFormData.imageurl = res
			},
			handleAvatarSuccess2(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
				this.dialogFormData.otherurl = res
			},
			handleAvatarSuccess3(res, file) {
				this.imageUrl = URL.createObjectURL(file.raw);
				this.dialogFormData.reserved4 = res
			},
			beforeAvatarUpload(file) {
				const isJPG = file.type === 'image/jpeg';
				const isLt2M = file.size / 1024 / 1024 < 50;

				/* if (!isJPG) {
					this.$message.error('上传头像图片只能是 JPG 格式!');
				} */
				if (!isLt2M) {
					this.$message.error('上传头像图片大小不能超过 50MB!');
				}
				return isJPG && isLt2M;
			},
			loadTable() {
				this.$http.post('/product/list', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
				this.$http.post('/product/total', this.searchFormData).then(resp => {
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
					that.$http.post('/product/del', data).then(() => {
						//done()
						that.$alert('删除成功')
						that.loadTable()
					})
				})
			},
			onDialogSave() {
				this.$refs.dialogForm.validate((valid) => {
					if (valid) {
						const uri = this.dialogFormData.id ? '/product/update' : '/product/add'
						var obj = this.dialogFormData
						delete obj.user
						if (uri == '/product/add') {
							obj.userid = this.userInfo.id
						}
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
<style>
	.avatar {
		width: 300px;
	}

	/************************************** 富文本编辑器 **************************************/
	/** 设置富文本的高度 **/
	.editor {
		line-height: normal !important;
		height: 730px;
		margin-bottom: 30px;
		width: 100%;
	}

	.ql-container {
		height: 700px !important;
		width: 100%;
	}

	.ql-container img {
		display: block;
	}

	/** 设置富文本的高度 **/

	/** 设置工具栏中上传图片和上传视频对应的文件上传组件样式 **/
	.avatar-uploader-img {
		height: 0;
	}

	.avatar-uploader-video {
		height: 0;
	}

	/** 设置工具栏中上传图片和上传视频对应的文件上传组件样式 **/


	/* /deep/ .ql-snow .ql-tooltip[data-mode=link]::before {
		content: '请输入链接地址：';
	}

	/deep/ .ql-snow .ql-tooltip.ql-editing a.ql-action::after {
		border-right: 0px;
		content: '保存';
		padding-right: 0px;
	}

	/deep/ .ql-snow .ql-tooltip[data-mode=video]::before {
		content: '请输入视频地址：';
	}

	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-label::before,
	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-item::before {
		content: '14px';
	}

	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=small]::before,
	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=small]::before {
		content: '10px';
	}

	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=large]::before,
	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=large]::before {
		content: '18px';
	}

	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-label[data-value=huge]::before,
	/deep/ .ql-snow .ql-picker.ql-size .ql-picker-item[data-value=huge]::before {
		content: '32px';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item::before {
		content: '文本';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='1']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='1']::before {
		content: '标题1';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='2']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='2']::before {
		content: '标题2';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='3']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='3']::before {
		content: '标题3';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='4']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='4']::before {
		content: '标题4';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='5']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='5']::before {
		content: '标题5';
	}

	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-label[data-value='6']::before,
	/deep/ .ql-snow .ql-picker.ql-header .ql-picker-item[data-value='6']::before {
		content: '标题6';
	}

	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-label::before,
	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-item::before {
		content: '标准字体';
	}

	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=serif]::before,
	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=serif]::before {
		content: '衬线字体';
	}

	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-label[data-value=monospace]::before,
	/deep/ .ql-snow .ql-picker.ql-font .ql-picker-item[data-value=monospace]::before {
		content: '等宽字体';
	} */

	/************************************** 富文本编辑器 **************************************/
</style>
