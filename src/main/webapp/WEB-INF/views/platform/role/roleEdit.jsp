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
	<script type="text/javascript">
		$(function () {
			$("#formId").validate({
				rules: {
					code: { required: true},
					name: { required: true},
					remark: { required: true},
					frozen: { required: true},
				},
				onkeyup: false,
				focusCleanup: true,
				success: "valid",
				submitHandler: function (form) {
					_saveForm();
				}
			});
		});
		var _err_callback = function (XMLHttpRequest, error, errorThrown) {
			$.closeLoading();
			$.openTip('保存失败，请稍候再试...',true, function() {
				$.closeLoading();
				return ;
			});
		};
		var _success_callback = function (response) {
			var data = eval("(" + response + ")");
			if (data.responseCode == 200) {
				$.openTip(data.responseMessage,true, function() {
					$.closeLoading();
					parent.window.location.href = ctx +'/platform/role/roleEdit.do';
					var index = parent.layer.getFrameIndex(window.name);
				});
			} else {
				$.closeLoading();
				$.openTip(data.message,true, function() {
					$.closeLoading();
					return ;
				});
			} 
		};
		var _saveForm = function () {
			$.openTip('确定保存吗？',false, function(){
				$.closeLoading();
				$.openLoading();
        		jQuery.ajax({
            		type: "POST",
            		url: ctx + '/platform/roleroleEdit.json',
            		data: $("#formId").serialize(),
            		error: _err_callback,
           		success: _success_callback
    	 		});
    		});
		}
	</script>
</page:extends>
<page:extends name="body">
	<article class="page-container">
		<form class="form form-horizontal" id="formId">
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					code：
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${role.code}" placeholder="请输入code" id="code" name="code">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					name：
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${role.name}" placeholder="请输入name" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					remark：
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${role.remark}" placeholder="请输入remark" id="remark" name="remark">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					frozen：
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${role.frozen}" placeholder="请输入frozen" id="frozen" name="frozen">
				</div>
			</div>
			<div class="row cl" style="text-align: right;margin-right: 2px">
				<button class="btn btn-success" type="submit">
					<i class="Hui-iconfont Hui-iconfont-save"></i>
			 		&nbsp;保存信息
				</button>
			</div>
		</form>
	</article>
</page:extends>
<jsp:include page="/parent/basepage.jsp" />
