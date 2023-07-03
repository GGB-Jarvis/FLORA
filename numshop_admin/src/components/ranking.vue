<template>
	<div class="app-container">
		<el-table :data="pageInfo.rows" border highlight-current-row>
			<el-table-column label="用户名">
				<template slot-scope="scope">
					{{scope.row.user.username}}
				</template>
			</el-table-column>
			<el-table-column label="昵称">
				<template slot-scope="scope">
					{{scope.row.user.realname}}
				</template>
			</el-table-column>
			<el-table-column label="真实姓名">
				<template slot-scope="scope">
					{{scope.row.user.name}}
				</template>
			</el-table-column>
			<el-table-column label="电话号码">
				<template slot-scope="scope">
					{{scope.row.user.phone}}
				</template>
			</el-table-column>
			<el-table-column prop="count" label="推荐人数" />
			<el-table-column label="角色">
				<template slot-scope="scope">
					{{scope.row.user.reserved}}
				</template>
			</el-table-column>
		</el-table>

	</div>
</template>

<script>
	export default {
		data() {
			return {

				pageInfo: {
					rows: [],
					total: 0
				}
			}
		},
		created() {
			this.loadTable()
		},
		methods: {
			loadTable() {
				this.$http.post('/user/rankList', this.searchFormData).then(resp => {
					this.pageInfo.rows = resp
				})
			},

		}
	}
</script>
