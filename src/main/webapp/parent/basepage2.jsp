<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	response.setHeader("Pragma","No-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
	response.flushBuffer();
%>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<meta name="keywords" content="云南金控">
	<meta name="description" content="云南金控后台管理系统">
	<title>
		<page:block name="title"></page:block>
	</title>
	
	<link rel="favicon.ico" href="${ctx}/statics/images/favicon.ico"  type="image/x-icon"/>
	<link rel="icon" href="${ctx}/statics/images/favicon.ico"  type="image/x-icon"/>
	<link rel="shortcut icon" href="${ctx}/statics/images/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/skin/bluet/skin.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"> <!-- zTree插件 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/pagination.css" media="screen"/> <!-- 分页样式 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/lib/font-awesome/css/font-awesome.min.css" /> <!-- 自定义样式 -->
	<link rel="stylesheet" type="text/css" href="${ctx}/statics/css/style.css" /> <!-- 自定义样式 -->
	
	<page:block name="css"></page:block>
	<style type="text/css">
		.ui-widget-overlay {
		    opacity: .5;
		    background: #eee;
		    filter: Alpha(Opacity=80);
		}
		p {
		    margin-bottom: 0px;
		}
	</style>
	<script type="text/javascript">
    	var ctx = '${ctx}'
   		function clear() {
    		$('#roleIds').val('');
    		$('#roleNames').val('');
    	}
    </script>
	<!--[if lt IE 9]>
	<script type="text/javascript" src="${ctx}/statics/lib/html5shiv.js"></script>
	<script type="text/javascript" src="${ctx}/statics/lib/respond.min.js"></script>
	<![endif]-->
	<!--[if IE 6]>
	<script type="text/javascript" src="${ctx}/statics/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="${ctx}/statics/lib/jquery/1.9.1/jquery.core.autocomplete.js"></script> 
	<script type="text/javascript" src="${ctx}/statics/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/H-ui.min.js"></script>
	<script type="text/javascript" src="${ctx}/statics/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="${ctx}/statics/lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script> <!-- zTree插件 -->
	<script type="text/javascript" src="${ctx}/statics/js/jquery.core.ajaxtable.js"></script> <!-- table插件 -->
	<script type="text/javascript" src="${ctx}/statics/js/jquery.pagination.js"></script> <!-- 翻页插件 -->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery-ui/iss-util.js"></script> <!-- 信息提示框 -->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery-ui/iss-util-editer.js"></script> <!-- 信息提示框 -->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery.validation/1.14.0/jquery.validate.js"></script> <!-- 表单验证插件 -->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery.validation/1.14.0/validate-methods.js"></script> <!-- 表单验证插件 -->
	<script type="text/javascript" src="${ctx}/statics/lib/jquery.validation/1.14.0/messages_zh.js"></script> <!-- 表单验证插件 -->
	
	<script type="text/javascript" src="${ctx}/statics/lib/angular/v1.2.30/angular.min.js"></script><!-- 引入angularJS -->
	<script type="text/javascript" src="${ctx}/statics/lib/angular/v1.2.30/app.js"></script><!-- 引入angularJS -->
	<script type="text/javascript" src="${ctx}/statics/lib/angular/v1.2.30/controllers.js"></script>
	<script type="text/javascript" src="${ctx}/statics/lib/angular/v1.2.30/services.js"></script>
	
	<page:block name="javascript"></page:block>
</head>
<body ng-app="objectApp">
	<page:block name="body"></page:block>
</body>
</html>