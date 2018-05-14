<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<page:extends name="title">
	User
</page:extends>
<page:extends name="css">
	<style type="text/css"></style>
</page:extends>
<page:extends name="javascript">
		var _init_dataGrid = function() {
			$("#dataGridList").dataGrid({
				title:'User�б�',
				url : ctx + '/platform/user/userList.json',
				checkbox : true,
				pageSize: 7,
				orderField : 'createTime',
        		sort: 'desc',
				searchButtonId: '#searchButton',
				queryParamsId: [],
				tableId: '#dataGridList',
				columns:[
					 { field : 'id', className:'text-c'},					 { field : 'realName',className:'text-l',description : 'realName', sort: true},
					 { field : 'loginName',className:'text-l',description : 'loginName', sort: true},
					 { field : 'password',className:'text-l',description : 'password', sort: true},
					 { field : 'locked',className:'text-l',description : 'locked', sort: true},
					 { field : 'enable',className:'text-l',description : 'enable', sort: true},
					 { field : 'email',className:'text-l',description : 'email', sort: true},
					 { field : 'mobile',className:'text-l',description : 'mobile', sort: true},
					 { field : 'remark',className:'text-l',description : 'remark', sort: true},
					 { field : 'position',className:'text-l',description : 'position', sort: true},
					 { field : 'lastLoginTime',className:'text-l',description : 'lastLoginTime', sort: true},
					{ field : 'operate',className:'text-c',description : '����', paramFormatter : function(row) {
						return '<a href="#" title="�޸�" onclick="data_edit(\'' + row.id + '\')">'
								 + '<i class="Hui-iconfont">&#xe60c;</i>'
							 + '</a>&nbsp;&nbsp;'
							 + '<a href="#" title="ɾ��" onclick="datadel(\'' + row.id + '\',true)">'
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
			var url = ctx + '/platform/user/userCreate.do';
			layer.open({
				type: 2,
				title: '����user��Ϣ',
				move: false,
				shadeClose: true,
				shade: 0.6,
				area: ['800px','460px'],
				content: url
			});
		}
		function edit(id){
			var url = ctx + '/platform/user/userEdit.do,?id=' + id;
			layer.open({
				type: 2,
				title: '�޸�user��Ϣ',
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
				url: ctx + '/platform/user/userDelete.json',
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
					$.openTip('ɾ�������쳣�����Ժ�����...',true,function() {
						$.closeLoading();
					})
				}
			},single);
		}
		var _init_dataGrid = function() {
			$("#dataGridList").dataGrid({
				title:'User�б�',
				url : ctx + '/platform/user/userList.json',
				checkbox : true,
				pageSize: 7,
				orderField : 'createTime',
        		sort: 'desc',
				searchButtonId: '#searchButton',
				queryParamsId: [],
				tableId: '#dataGridList',
				columns:[
					 { field : 'id', className:'text-c'},					 { field : 'realName',className:'text-l',description : 'realName', sort: true},
					 { field : 'loginName',className:'text-l',description : 'loginName', sort: true},
					 { field : 'password',className:'text-l',description : 'password', sort: true},
					 { field : 'locked',className:'text-l',description : 'locked', sort: true},
					 { field : 'enable',className:'text-l',description : 'enable', sort: true},
					 { field : 'email',className:'text-l',description : 'email', sort: true},
					 { field : 'mobile',className:'text-l',description : 'mobile', sort: true},
					 { field : 'remark',className:'text-l',description : 'remark', sort: true},
					 { field : 'position',className:'text-l',description : 'position', sort: true},
					 { field : 'lastLoginTime',className:'text-l',description : 'lastLoginTime', sort: true},
					{ field : 'operate',className:'text-c',description : '����', paramFormatter : function(row) {
						return '<a href="#" title="�޸�" onclick="data_edit(\'' + row.id + '\')">'
								 + '<i class="Hui-iconfont">&#xe60c;</i>'
							 + '</a>&nbsp;&nbsp;'
							 + '<a href="#" title="ɾ��" onclick="datadel(\'' + row.id + '\',true)">'
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
