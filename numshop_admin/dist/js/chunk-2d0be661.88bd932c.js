(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0be661"],{"2fa8":function(t,e,n){"use strict";n.r(e);var a=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"app-container"},[n("el-table",{attrs:{data:t.pageInfo.rows,border:"","highlight-current-row":""}},[n("el-table-column",{attrs:{label:"用户名"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n\t\t\t\t"+t._s(e.row.user.username)+"\n\t\t\t")]}}])}),n("el-table-column",{attrs:{label:"昵称"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n\t\t\t\t"+t._s(e.row.user.realname)+"\n\t\t\t")]}}])}),n("el-table-column",{attrs:{label:"真实姓名"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n\t\t\t\t"+t._s(e.row.user.name)+"\n\t\t\t")]}}])}),n("el-table-column",{attrs:{label:"电话号码"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n\t\t\t\t"+t._s(e.row.user.phone)+"\n\t\t\t")]}}])}),n("el-table-column",{attrs:{prop:"count",label:"推荐人数"}}),n("el-table-column",{attrs:{label:"角色"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v("\n\t\t\t\t"+t._s(e.row.user.reserved)+"\n\t\t\t")]}}])})],1)],1)},l=[],r={data:function(){return{pageInfo:{rows:[],total:0}}},created:function(){this.loadTable()},methods:{loadTable:function(){var t=this;this.$http.post("/user/rankList",this.searchFormData).then(function(e){t.pageInfo.rows=e})}}},o=r,s=n("2877"),u=Object(s["a"])(o,a,l,!1,null,null,null);e["default"]=u.exports}}]);
//# sourceMappingURL=chunk-2d0be661.88bd932c.js.map