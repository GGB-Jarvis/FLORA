(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d225891"],{e58c:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchFormData,size:"mini"}},[a("el-form-item",{attrs:{label:"支付方式"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"支付方式"},model:{value:e.searchFormData.paytype,callback:function(t){e.$set(e.searchFormData,"paytype",t)},expression:"searchFormData.paytype"}})],1),a("el-form-item",{attrs:{label:"支付状态"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"支付状态"},model:{value:e.searchFormData.status,callback:function(t){e.$set(e.searchFormData,"status",t)},expression:"searchFormData.status"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.loadTable}},[e._v("查询")])],1)],1),a("el-table",{attrs:{data:e.pageInfo.rows,border:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"id",label:"ID"}}),a("el-table-column",{attrs:{prop:"userid",label:"数字藏品"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n\t\t\t\t"+e._s(t.row.list[0]?t.row.list[0].product.name:"")+"\n\t\t\t")]}}])}),a("el-table-column",{attrs:{prop:"userid",label:"购买者"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n\t\t\t\t"+e._s(t.row.user?t.row.user.name?t.row.user.name:"未实名认证:"+t.row.userid:"未通过公众号进入")+"\n\t\t\t")]}}])}),a("el-table-column",{attrs:{prop:"createtime",label:"购买数量"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n\t\t\t\t"+e._s(null!=t.row.list?t.row.list.length:"0")+"\n\t\t\t")]}}])}),a("el-table-column",{attrs:{prop:"createtime",label:"购买时间"}}),a("el-table-column",{attrs:{prop:"paytime",label:"支付时间"}}),a("el-table-column",{attrs:{prop:"paytype",label:"支付方式"}}),a("el-table-column",{attrs:{prop:"status",label:"支付状态"}})],1),a("el-pagination",{staticStyle:{"margin-top":"5px"},attrs:{background:"","current-page":e.searchFormData.pageIndex,"page-size":e.searchFormData.pageSize,"page-sizes":[5,10,20,40],total:e.pageInfo.total,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.onSizeChange,"current-change":e.onPageIndexChange}}),a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogVisible=t},close:function(t){return e.resetForm("dialogForm")}}},[a("el-form",{ref:"dialogForm",attrs:{rules:e.dialogFormRules,model:e.dialogFormData,"label-width":"120px",size:"mini"}},[a("el-form-item",{attrs:{prop:"userid",label:"购买者"}},[a("el-input",{model:{value:e.dialogFormData.userid,callback:function(t){e.$set(e.dialogFormData,"userid",t)},expression:"dialogFormData.userid"}})],1),a("el-form-item",{attrs:{prop:"createtime",label:"购买时间"}},[a("el-input",{model:{value:e.dialogFormData.createtime,callback:function(t){e.$set(e.dialogFormData,"createtime",t)},expression:"dialogFormData.createtime"}})],1),a("el-form-item",{attrs:{prop:"paytime",label:"支付时间"}},[a("el-input",{model:{value:e.dialogFormData.paytime,callback:function(t){e.$set(e.dialogFormData,"paytime",t)},expression:"dialogFormData.paytime"}})],1),a("el-form-item",{attrs:{prop:"paytype",label:"支付方式"}},[a("el-input",{model:{value:e.dialogFormData.paytype,callback:function(t){e.$set(e.dialogFormData,"paytype",t)},expression:"dialogFormData.paytype"}})],1),a("el-form-item",{attrs:{prop:"status",label:"支付状态"}},[a("el-input",{model:{value:e.dialogFormData.status,callback:function(t){e.$set(e.dialogFormData,"status",t)},expression:"dialogFormData.status"}})],1),a("el-form-item",{attrs:{prop:"reserved",label:"reserved"}},[a("el-input",{model:{value:e.dialogFormData.reserved,callback:function(t){e.$set(e.dialogFormData,"reserved",t)},expression:"dialogFormData.reserved"}})],1),a("el-form-item",{attrs:{prop:"reserved2",label:"reserved2"}},[a("el-input",{model:{value:e.dialogFormData.reserved2,callback:function(t){e.$set(e.dialogFormData,"reserved2",t)},expression:"dialogFormData.reserved2"}})],1),a("el-form-item",{attrs:{prop:"reserved3",label:"reserved3"}},[a("el-input",{model:{value:e.dialogFormData.reserved3,callback:function(t){e.$set(e.dialogFormData,"reserved3",t)},expression:"dialogFormData.reserved3"}})],1),a("el-form-item",{attrs:{prop:"reserved4",label:"reserved4"}},[a("el-input",{model:{value:e.dialogFormData.reserved4,callback:function(t){e.$set(e.dialogFormData,"reserved4",t)},expression:"dialogFormData.reserved4"}})],1),a("el-form-item",{attrs:{prop:"reserved5",label:"reserved5"}},[a("el-input",{model:{value:e.dialogFormData.reserved5,callback:function(t){e.$set(e.dialogFormData,"reserved5",t)},expression:"dialogFormData.reserved5"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.onDialogSave}},[e._v("保 存")])],1)],1)],1)},o=[],l=a("bc3a"),i=a.n(l),s=(i.a.create({baseURL:Object({NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_BASE_API,timeout:6e4}),{data:function(){return{searchFormData:{id:"",userid:"",createtime:"",paytime:"",paytype:"",status:"",reserved:"",reserved2:"",reserved3:"",reserved4:"",reserved5:"",pageIndex:1,pageSize:10},pageInfo:{rows:[],total:0},dialogVisible:!1,dialogTitle:"",dialogFormData:{id:"",userid:"",createtime:"",paytime:"",paytype:"",status:"",reserved:"",reserved2:"",reserved3:"",reserved4:"",reserved5:""},dialogFormRules:{id:[{required:!0,message:"不能为空",trigger:"blur"}],userid:[{required:!0,message:"不能为空",trigger:"blur"}],createtime:[{required:!0,message:"不能为空",trigger:"blur"}],paytime:[{required:!0,message:"不能为空",trigger:"blur"}],paytype:[{required:!0,message:"不能为空",trigger:"blur"}],status:[{required:!0,message:"不能为空",trigger:"blur"}],reserved:[{required:!0,message:"不能为空",trigger:"blur"}],reserved2:[{required:!0,message:"不能为空",trigger:"blur"}],reserved3:[{required:!0,message:"不能为空",trigger:"blur"}],reserved4:[{required:!0,message:"不能为空",trigger:"blur"}],reserved5:[{required:!0,message:"不能为空",trigger:"blur"}]}}},created:function(){this.loadTable()},methods:{loadTable:function(){var e=this;this.$http.post("/orders/list",this.searchFormData).then(function(t){e.pageInfo.rows=t}),this.$http.post("/orders/total",this.searchFormData).then(function(t){e.pageInfo.total=t})},onTableUpdate:function(e){var t=this;this.dialogTitle="修改",this.dialogVisible=!0,this.$nextTick(function(){Object.assign(t.dialogFormData,e)})},onTableDelete:function(e){var t=this;this.$confirm("确认要删除该记录吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){var a={id:e.id};t.$http.post("/orders/del",a).then(function(){t.$alert("删除成功"),t.loadTable()})})},onDialogSave:function(){var e=this;this.$refs.dialogForm.validate(function(t){if(t){var a=e.dialogFormData.id?"/orders/update":"/orders/add";e.$http.post(a,e.dialogFormData).then(function(){e.dialogVisible=!1,e.loadTable()})}})},onSizeChange:function(e){this.searchFormData.pageSize=e,this.loadTable()},onAdd:function(){this.dialogTitle="新增",this.dialogVisible=!0,this.dialogFormData.id=0},onPageIndexChange:function(e){this.searchFormData.pageIndex=e,this.loadTable()}}}),n=s,d=a("2877"),u=Object(d["a"])(n,r,o,!1,null,null,null);t["default"]=u.exports}}]);
//# sourceMappingURL=chunk-2d225891.151d6e95.js.map