(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-10c6c09e"],{b664:function(e,t,a){},be6f:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-form",{staticClass:"demo-form-inline",attrs:{inline:!0,model:e.searchFormData,size:"mini"}},[a("el-form-item",{attrs:{label:"商品名称"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"商品名称"},model:{value:e.searchFormData.name,callback:function(t){e.$set(e.searchFormData,"name",t)},expression:"searchFormData.name"}})],1),a("el-form-item",{attrs:{label:"标签"}},[a("el-input",{staticStyle:{width:"250px"},attrs:{clearable:!0,placeholder:"标签"},model:{value:e.searchFormData.tag,callback:function(t){e.$set(e.searchFormData,"tag",t)},expression:"searchFormData.tag"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary",icon:"el-icon-search"},on:{click:e.loadTable}},[e._v("查询")])],1)],1),a("el-button",{staticStyle:{"margin-bottom":"10px"},attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.onAdd}},[e._v("新增\n\t")]),a("el-table",{attrs:{data:e.pageInfo.rows,border:"","highlight-current-row":""}},[a("el-table-column",{attrs:{prop:"name",label:"商品名称"}}),a("el-table-column",{attrs:{prop:"reserved3",label:"商品类型"}}),a("el-table-column",{attrs:{prop:"count",label:"限购数量"}}),a("el-table-column",{attrs:{prop:"author",label:"作者"}}),a("el-table-column",{attrs:{prop:"authorDesc",label:"作者简介"}}),a("el-table-column",{attrs:{prop:"description",label:"作品简介"}}),a("el-table-column",{attrs:{prop:"tokenId",label:"口令"}}),a("el-table-column",{attrs:{prop:"imageurl",label:"封面图片"},scopedSlots:e._u([{key:"default",fn:function(e){return[a("img",{staticStyle:{width:"150px"},attrs:{src:e.row.imageurl}})]}}])}),a("el-table-column",{attrs:{prop:"reserved4",label:"音乐"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.reserved4?a("audio",{attrs:{src:t.row.reserved4,controls:""}}):e._e()]}}])}),a("el-table-column",{attrs:{prop:"reserved",label:"单价"}}),a("el-table-column",{attrs:{prop:"tag",label:"标签"}}),a("el-table-column",{attrs:{prop:"createtime",label:"创建时间"}}),a("el-table-column",{attrs:{prop:"shoptime",label:"售卖时间"}}),a("el-table-column",{scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(a){return e.onTableUpdate(t.row)}}},[e._v("修改")]),a("el-button",{attrs:{type:"text",size:"mini"},on:{click:function(a){return e.onTableDelete(t.row)}}},[e._v("删除")])]}}])})],1),a("el-pagination",{staticStyle:{"margin-top":"5px"},attrs:{background:"","current-page":e.searchFormData.pageIndex,"page-size":e.searchFormData.pageSize,"page-sizes":[5,10,20,40],total:e.pageInfo.total,layout:"total, sizes, prev, pager, next"},on:{"size-change":e.onSizeChange,"current-change":e.onPageIndexChange}}),a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.dialogVisible,"close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogVisible=t},close:function(t){return e.resetForm("dialogForm")}}},[a("el-form",{ref:"dialogForm",attrs:{rules:e.dialogFormRules,model:e.dialogFormData,"label-width":"120px",size:"mini"}},[a("el-form-item",{attrs:{prop:"name",label:"商品名称"}},[a("el-input",{model:{value:e.dialogFormData.name,callback:function(t){e.$set(e.dialogFormData,"name",t)},expression:"dialogFormData.name"}})],1),a("el-form-item",{attrs:{prop:"count",label:"限购数量"}},[a("el-input",{model:{value:e.dialogFormData.count,callback:function(t){e.$set(e.dialogFormData,"count",t)},expression:"dialogFormData.count"}})],1),a("el-form-item",{attrs:{prop:"author",label:"作者"}},[a("el-input",{model:{value:e.dialogFormData.author,callback:function(t){e.$set(e.dialogFormData,"author",t)},expression:"dialogFormData.author"}})],1),a("el-form-item",{attrs:{prop:"authorDesc",label:"作者简介"}},[a("el-input",{model:{value:e.dialogFormData.authorDesc,callback:function(t){e.$set(e.dialogFormData,"authorDesc",t)},expression:"dialogFormData.authorDesc"}})],1),a("el-form-item",{attrs:{prop:"reserved3",label:"类型"}},[a("el-radio-group",{model:{value:e.dialogFormData.reserved3,callback:function(t){e.$set(e.dialogFormData,"reserved3",t)},expression:"dialogFormData.reserved3"}},[a("el-radio-button",{attrs:{label:"图片"}}),a("el-radio-button",{attrs:{label:"音乐"}})],1)],1),"音乐"==e.dialogFormData.reserved3?a("el-form-item",{attrs:{prop:"reserved4",label:"上传音乐"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://artvery.txwsyun.cn:8080/numshop/upload","show-file-list":!1,"on-success":e.handleAvatarSuccess3}},[e.dialogFormData.reserved4?a("audio",{staticClass:"avatar",attrs:{src:e.dialogFormData.reserved4,controls:""}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1):e._e(),a("el-form-item",{attrs:{prop:"description",label:"商品简介"}},[a("el-input",{model:{value:e.dialogFormData.description,callback:function(t){e.$set(e.dialogFormData,"description",t)},expression:"dialogFormData.description"}})],1),a("el-form-item",{attrs:{prop:"imageurl",label:"封面图片"}},[a("el-upload",{staticClass:"avatar-uploader",attrs:{action:"http://artvery.txwsyun.cn:8080/numshop/upload","show-file-list":!1,"on-success":e.handleAvatarSuccess}},[e.dialogFormData.imageurl?a("img",{staticClass:"avatar",attrs:{src:e.dialogFormData.imageurl}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),a("el-form-item",{attrs:{prop:"otherurl",label:"详情"}},[a("quill-editor",{ref:"myQuillEditor",staticClass:"editor",staticStyle:{"background-color":"black"},attrs:{options:e.editorOption},on:{focus:function(t){return e.quillEditorFocus(t)}},model:{value:e.dialogFormData.otherurl,callback:function(t){e.$set(e.dialogFormData,"otherurl",t)},expression:"dialogFormData.otherurl"}}),a("el-upload",{staticClass:"avatar-uploader-img",attrs:{action:"http://artvery.txwsyun.cn:8080/numshop/upload","show-file-list":!1,"on-success":e.uploadImgSuccess,"before-upload":e.beforeUploadImg,"on-error":e.uploadImgError,data:{pathName:""}}})],1),a("el-form-item",{attrs:{prop:"tag",label:"标签"}},[a("el-input",{model:{value:e.dialogFormData.tag,callback:function(t){e.$set(e.dialogFormData,"tag",t)},expression:"dialogFormData.tag"}})],1),a("el-form-item",{attrs:{prop:"reserved",label:"单价"}},[a("el-input",{model:{value:e.dialogFormData.reserved,callback:function(t){e.$set(e.dialogFormData,"reserved",t)},expression:"dialogFormData.reserved"}})],1),a("el-form-item",{attrs:{prop:"shoptime",label:"售卖时间"}},[a("el-date-picker",{attrs:{type:"datetime",format:"yyyy-MM-dd HH:mm:ss","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"选择售卖时间"},model:{value:e.dialogFormData.shoptime,callback:function(t){e.$set(e.dialogFormData,"shoptime",t)},expression:"dialogFormData.shoptime"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.onDialogSave}},[e._v("保 存")])],1)],1)],1)},o=[],l=[["bold","italic","underline","strike"],["blockquote","code-block"],[{header:1},{header:2}],[{list:"ordered"},{list:"bullet"}],[{script:"sub"},{script:"super"}],[{indent:"-1"},{indent:"+1"}],[{size:["small",!1,"large","huge"]}],[{header:[1,2,3,4,5,6,!1]}],[{color:[]},{background:[]}],[{font:[]}],[{align:[]}],["clean"],["link","image"]],i={data:function(){return{editorOption:{theme:"snow",placeholder:"请输入详情内容...",modules:{toolbar:{container:l,handlers:{image:function(e){e?document.querySelector(".avatar-uploader-img input").click():this.quill.format("image",!1)},link:function(e){if(e){var t=prompt("请输入链接地址：");this.quill.format("link",t)}else this.quill.format("link",!1)}}}}},searchFormData:{id:"",name:"",count:"",imageurl:"",otherurl:"",tag:"",createtime:"",shoptime:"",userid:"",reserved:"",reserved2:"",reserved3:"",reserved4:"",reserved5:"",pageIndex:1,pageSize:10},pageInfo:{rows:[],total:0},userInfo:null,dialogVisible:!1,dialogTitle:"",dialogFormData:{id:"",name:"",count:"",imageurl:"",otherurl:"",tag:"",createtime:"",shoptime:"",userid:"",reserved:"",reserved2:"",reserved3:"图片",reserved4:"",reserved5:"",authorDesc:"",tokenId:"",description:"",author:""},dialogFormRules:{id:[{required:!0,message:"不能为空",trigger:"blur"}],name:[{required:!0,message:"不能为空",trigger:"blur"}],count:[{required:!0,message:"不能为空",trigger:"blur"}],imageurl:[{required:!0,message:"不能为空",trigger:"blur"}],otherurl:[{required:!0,message:"不能为空",trigger:"blur"}],tag:[{required:!0,message:"不能为空",trigger:"blur"}],createtime:[{required:!0,message:"不能为空",trigger:"blur"}],shoptime:[{required:!0,message:"不能为空",trigger:"blur"}],userid:[{required:!0,message:"不能为空",trigger:"blur"}],reserved:[{required:!0,message:"不能为空",trigger:"blur"}],reserved2:[{required:!0,message:"不能为空",trigger:"blur"}],reserved3:[{required:!0,message:"不能为空",trigger:"blur"}],reserved5:[{required:!0,message:"不能为空",trigger:"blur"}]}}},created:function(){this.userInfo=JSON.parse(sessionStorage.getItem("user")),console.log(this.userInfo),this.loadTable()},methods:{beforeUploadImg:function(e){var t="image/jpeg"===e.type||"image/png"===e.type||"image/gif"===e.type;return t?this.quillUpdate=!0:this.$message.error("上传图片只能是 JPG，PNG, GIF 格式!"),t},uploadImgSuccess:function(e,t){var a=this.$refs.myQuillEditor.quill;if(e){console.info(e);var r=a.getSelection().index;a.insertEmbed(r,"image",e),a.setSelection(r+1)}else this.$message.error(e.msg);this.quillUpdate=!1},uploadImgError:function(){this.quillUpdate=!1,this.$message.error("图片插入失败!")},quillEditorFocus:function(e){},handleAvatarSuccess:function(e,t){this.imageUrl=URL.createObjectURL(t.raw),this.dialogFormData.imageurl=e},handleAvatarSuccess2:function(e,t){this.imageUrl=URL.createObjectURL(t.raw),this.dialogFormData.otherurl=e},handleAvatarSuccess3:function(e,t){this.imageUrl=URL.createObjectURL(t.raw),this.dialogFormData.reserved4=e},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,a=e.size/1024/1024<50;return a||this.$message.error("上传头像图片大小不能超过 50MB!"),t&&a},loadTable:function(){var e=this;this.$http.post("/product/list",this.searchFormData).then(function(t){e.pageInfo.rows=t}),this.$http.post("/product/total",this.searchFormData).then(function(t){e.pageInfo.total=t})},onTableUpdate:function(e){var t=this;this.dialogTitle="修改",this.dialogVisible=!0,this.$nextTick(function(){Object.assign(t.dialogFormData,e)})},onTableDelete:function(e){var t=this;this.$confirm("确认要删除该记录吗？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){var a={id:e.id};t.$http.post("/product/del",a).then(function(){t.$alert("删除成功"),t.loadTable()})})},onDialogSave:function(){var e=this;this.$refs.dialogForm.validate(function(t){if(t){var a=e.dialogFormData.id?"/product/update":"/product/add",r=e.dialogFormData;delete r.user,"/product/add"==a&&(r.userid=e.userInfo.id),e.$http.post(a,e.dialogFormData).then(function(){e.dialogVisible=!1,e.loadTable()})}})},onSizeChange:function(e){this.searchFormData.pageSize=e,this.loadTable()},onAdd:function(){this.dialogTitle="新增",this.dialogVisible=!0,this.dialogFormData.id=0},onPageIndexChange:function(e){this.searchFormData.pageIndex=e,this.loadTable()}}},s=i,n=(a("e903"),a("2877")),u=Object(n["a"])(s,r,o,!1,null,null,null);t["default"]=u.exports},e903:function(e,t,a){"use strict";var r=a("b664"),o=a.n(r);o.a}}]);
//# sourceMappingURL=chunk-10c6c09e.3c5fb4d6.js.map