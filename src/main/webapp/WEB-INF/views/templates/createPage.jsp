<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<page:extends name="title">创建模板页面</page:extends>
<page:extends name="javascript">
	<script type="text/javascript" src="${ctx}/statics/project/common/createPage.js"></script>
</page:extends>
<page:extends name="css">
	<style type="text/css">
		.row {
		    box-sizing: border-box;
		    margin-bottom: 10px;
		    margin-left: -15px;
		    margin-right: -15px;
		}
	</style>
</page:extends>
<page:extends name="body">
	<article class="page-container">
        <form class="form form-horizontal" id="createForm">
            <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">&nbsp;</span>请选择包名：</label>
                <div class="formControls col-xs-9 col-sm-10">
                    <span class="select-box" style="width:100%;">
                   		 <select id="className" class="select valid" name="className" size="1">
	                        <option value="" selected="selected">请选择</option>
	                        <c:forEach items="${list}" var="item">
		                        <option value="${item.className}">${item.selectName}</option>
	                        </c:forEach>
                   	 	</select>
                    </span>
                </div>
            </div>
            <!-- <div class="row cl">
                <label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>请选择包名：</label>
                <div class="formControls col-xs-9 col-sm-10">
                    <input type="text" class="input-text" value="" placeholder="请选择包名" id="packageName" name="packageName">
                </div>
            </div> -->
            <div class="row cl" style="text-align: right;margin-right: 2px">
                <button class="btn btn-success" type="submit">
                    <i class="Hui-iconfont Hui-iconfont-save"></i>
                    &nbsp;保存用户信息
                </button>
            </div>
            <div id="test"></div>
        </form>
    </article>
</page:extends>
<jsp:include page="/parent/basepage.jsp" />