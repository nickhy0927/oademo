<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<page:extends name="title">
	Role
</page:extends>
<page:extends name="css">
	<style type="text/css"></style>
</page:extends>
<page:extends name="javascript">
		var _init_dataGrid = function() {
			$("#dataGridList").dataGrid({
				title:'Role列表',
				url : ctx + '/platform/role/roleList.json',
				checkbox : true,
				pageSize: 7,
				orderField : 'createTime',
        		sort: 'desc',
				searchButtonId: '#searchButton',
				queryParamsId: [],
				tableId: '#dataGridList',
				columns:[
					 { field : 'id', className:'text-c'},					 { field : 'code',className:'text-l',description : 'code', sort: true},
					 { field : 'name',className:'text-l',description : 'name', sort: true},
					 { field : 'remark',className:'text-l',description : 'remark', sort: true},
					 { field : 'frozen',className:'text-l',description : 'frozen', sort: true},
					{ field : 'operate',className:'text-c',description : '操作', paramFormatter : function(row) {
						return '<a href="#" title="修改" onclick="data_edit(\'' + row.id + '\')">'
								 + '<i class="Hui-iconfont">&#xe60c;</i>'
							 + '</a>&nbsp;&nbsp;'
							 + '<a href="#" title="删除" onclick="datadel(\'' + row.id + '\',true)">'
								+ '<i class="Hui-iconfont">&#xe609;</i>'
							 + '</a>'
						}					}
				]
			});
		};
		$(function() {
			_init_dataGrid();
		});
</page:extends>
<page:extends name="body">
	<article class="page-container">
		<form class="form form-horizontal" id="formId">
	<script type="text/javascript">
		function create(){
			var url = ctx + '/platform/role/roleCreate.do';
			layer.open({
				type: 2,
				title: '新增role信息',
				move: false,
				shadeClose: true,
				shade: 0.6,
				area: ['800px','460px'],
				content: url
			});
		}
		function edit(id){
			var url = ctx + '/platform/role/roleEdit.do,?id=' + id;
			layer.open({
				type: 2,
				title: '修改role信息',
				move: false,
				shadeClose: true,
				shade: 0.6,
				area: ['800px','460px'],
				content: url
			});
		}
		function datadel(id, single) {
			$.closeLoading();
			$.datadel({
				url: ctx + '/platform/role/roleDelete.json',
				method:'POST',
				dataType:'JSON',
				data: {id: id},
				success: function(data) {
					$.closeLoading();
					if(data.responseCode == 200) {
						$.openTip(data.responseMessage, true, function(d) {
							$.closeLoading();
							_init_dataGrid();
						})
					} else {
						$.openTip(data.message, true,function() {
							$.closeLoading();
						})
					}
				},
				error: function(err) {
					$.openTip('删除数据异常，请稍后再试...',true,function() {
						$.closeLoading();
					})
				}
			},single);
		}
		var _init_dataGrid = function() {
			$("#dataGridList").dataGrid({
				title:'Role列表',
				url : ctx + '/platform/role/roleList.json',
				checkbox : true,
				pageSize: 7,
				orderField : 'createTime',
        		sort: 'desc',
				searchButtonId: '#searchButton',
				queryParamsId: [],
				tableId: '#dataGridList',
				columns:[
					 { field : 'id', className:'text-c'},					 { field : 'code',className:'text-l',description : 'code', sort: true},
					 { field : 'name',className:'text-l',description : 'name', sort: true},
					 { field : 'remark',className:'text-l',description : 'remark', sort: true},
					 { field : 'frozen',className:'text-l',description : 'frozen', sort: true},
					{ field : 'operate',className:'text-c',description : '操作', paramFormatter : function(row) {
						return '<a href="#" title="修改" onclick="data_edit(\'' + row.id + '\')">'
								 + '<i class="Hui-iconfont">&#xe60c;</i>'
							 + '</a>&nbsp;&nbsp;'
							 + '<a href="#" title="删除" onclick="datadel(\'' + row.id + '\',true)">'
								+ '<i class="Hui-iconfont">&#xe609;</i>'
							 + '</a>'
						}					}
				]
			});
		};
		$(function() {
			_init_dataGrid();
		});
	</script>
		</form>
	</article>
</page:extends>
<jsp:include page="/parent/basepage.jsp" />
