(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d22bd04"],{f125:function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchFormData,size:"mini"}},[a("el-form-item",{attrs:{label:"内容"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"内容"},model:{value:e.searchFormData.content,callback:function(t){e.$set(e.searchFormData,"content",t)},expression:"searchFormData.content"}})],1),a("el-form-item",{attrs:{label:"发送时间"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"发送时间"},model:{value:e.searchFormData.createtime,callback:function(t){e.$set(e.searchFormData,"createtime",t)},expression:"searchFormData.createtime"}})],1),a("el-form-item",{attrs:{label:"发送手机"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"发送手机"},model:{value:e.searchFormData.phone,callback:function(t){e.$set(e.searchFormData,"phone",t)},expression:"searchFormData.phone"}})],1),a("el-form-item",{attrs:{label:"有效期"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"有效期"},model:{value:e.searchFormData.validitytime,callback:function(t){e.$set(e.searchFormData,"validitytime",t)},expression:"searchFormData.validitytime"}})],1),a("el-form-item",{attrs:{label:"类型"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"类型"},model:{value:e.searchFormData.type,callback:function(t){e.$set(e.searchFormData,"type",t)},expression:"searchFormData.type"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.loadTable}},[e._v("查询")])],1)],1),a("el-button",{staticStyle:{"margin-bottom":"10px"},attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.onAdd}},[e._v("新增\n\t")]),a("el-table",{attrs:{data:e.pageInfo.rows,border:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"id",label:"ID"}}),a("el-table-column",{attrs:{prop:"content",label:"内容"}}),a("el-table-column",{attrs:{prop:"createtime",label:"发送时间"}}),a("el-table-column",{attrs:{prop:"phone",label:"发送手机"}}),a("el-table-column",{attrs:{prop:"validitytime",label:"有效期"}}),a("el-table-column",{attrs:{prop:"type",label:"类型"}}),a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(a){return e.onTableUpdate(t.row)}}},[e._v("修改")]),a("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(a){return e.onTableDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"5px"},attrs:{background:"","current-page":e.searchFormData.pageIndex,"page-size":e.searchFormData.pageSize,"page-sizes":[5,10,20,40],total:e.pageInfo.total,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.onSizeChange,"current-change":e.onPageIndexChange}}),a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogVisible=t},close:function(t){return e.resetForm("dialogForm")}}},[a("el-form",{ref:"dialogForm",attrs:{rules:e.dialogFormRules,model:e.dialogFormData,"label-width":"120px",size:"mini"}},[a("el-form-item",{attrs:{prop:"content",label:"内容"}},[a("el-input",{model:{value:e.dialogFormData.content,callback:function(t){e.$set(e.dialogFormData,"content",t)},expression:"dialogFormData.content"}})],1),a("el-form-item",{attrs:{prop:"createtime",label:"发送时间"}},[a("el-input",{model:{value:e.dialogFormData.createtime,callback:function(t){e.$set(e.dialogFormData,"createtime",t)},expression:"dialogFormData.createtime"}})],1),a("el-form-item",{attrs:{prop:"phone",label:"发送手机"}},[a("el-input",{model:{value:e.dialogFormData.phone,callback:function(t){e.$set(e.dialogFormData,"phone",t)},expression:"dialogFormData.phone"}})],1),a("el-form-item",{attrs:{prop:"validitytime",label:"有效期"}},[a("el-input",{model:{value:e.dialogFormData.validitytime,callback:function(t){e.$set(e.dialogFormData,"validitytime",t)},expression:"dialogFormData.validitytime"}})],1),a("el-form-item",{attrs:{prop:"type",label:"类型"}},[a("el-input",{model:{value:e.dialogFormData.type,callback:function(t){e.$set(e.dialogFormData,"type",t)},expression:"dialogFormData.type"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.onDialogSave}},[e._v("保 存")])],1)],1)],1)},l=[],i=a("bc3a"),r=a.n(i),n=(r.a.create({baseURL:Object({NODE_ENV:"production",BASE_URL:"/"}).VUE_APP_BASE_API,timeout:6e4}),{data:function(){return{searchFormData:{id:"",content:"",createtime:"",phone:"",validitytime:"",type:"",pageIndex:1,pageSize:10},pageInfo:{rows:[],total:0},dialogVisible:!1,dialogTitle:"",dialogFormData:{id:"",content:"",createtime:"",phone:"",validitytime:"",type:""},dialogFormRules:{id:[{required:!0,message:"不能为空",trigger:"blur"}],content:[{required:!0,message:"不能为空",trigger:"blur"}],createtime:[{required:!0,message:"不能为空",trigger:"blur"}],phone:[{required:!0,message:"不能为空",trigger:"blur"}],validitytime:[{required:!0,message:"不能为空",trigger:"blur"}],type:[{required:!0,message:"不能为空",trigger:"blur"}]}}},created:function(){this.loadTable()},methods:{loadTable:function(){var e=this;this.$http.post("/notes/list",this.searchFormData).then(function(t){e.pageInfo.rows=t}),this.$http.post("/notes/total",this.searchFormData).then(function(t){e.pageInfo.total=t})},onTableUpdate:function(e){var t=this;this.dialogTitle="修改",this.dialogVisible=!0,this.$nextTick(function(){Object.assign(t.dialogFormData,e)})},onTableDelete:function(e){var t=this;this.$confirm("确认要删除该记录吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){var a={id:e.id};t.$http.post("/notes/del",a).then(function(){t.$alert("删除成功"),t.loadTable()})})},onDialogSave:function(){var e=this;this.$refs.dialogForm.validate(function(t){if(t){var a=e.dialogFormData.id?"/notes/update":"/notes/add";e.$http.post(a,e.dialogFormData).then(function(){e.dialogVisible=!1,e.loadTable()})}})},onSizeChange:function(e){this.searchFormData.pageSize=e,this.loadTable()},onAdd:function(){this.dialogTitle="新增",this.dialogVisible=!0,this.dialogFormData.id=0},onPageIndexChange:function(e){this.searchFormData.pageIndex=e,this.loadTable()}}}),s=n,c=a("2877"),m=Object(c["a"])(s,o,l,!1,null,null,null);t["default"]=m.exports}}]);
//# sourceMappingURL=chunk-2d22bd04.3966eb6f.js.map