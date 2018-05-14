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
	<script type="text/javascript">
		$(function () {
			$("#formId").validate({
				rules: {
					realName: { required: true},
					loginName: { required: true},
					password: { required: true},
					locked: { required: true},
					enable: { required: true},
					email: { required: true},
					mobile: { required: true},
					remark: { required: true},
					position: { required: true},
					lastLoginTime: { required: true},
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
			$.openTip('����ʧ�ܣ����Ժ�����...',true, function() {
				$.closeLoading();
				return ;
			});
		};
		var _success_callback = function (response) {
			var data = eval("(" + response + ")");
			if (data.responseCode == 200) {
				$.openTip(data.responseMessage,true, function() {
					$.closeLoading();
					parent.window.location.href = ctx +'/platform/user/userList.do';
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
			$.openTip('ȷ��������',false, function(){
				$.closeLoading();
				$.openLoading();
        		jQuery.ajax({
            		type: "POST",
            		url: ctx + '/platform/useruserCreate.json',
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
					realName��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������realName" id="realName" name="realName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					loginName��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������loginName" id="loginName" name="loginName">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					password��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������password" id="password" name="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					locked��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������locked" id="locked" name="locked">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					enable��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������enable" id="enable" name="enable">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					email��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������email" id="email" name="email">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					mobile��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������mobile" id="mobile" name="mobile">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					remark��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������remark" id="remark" name="remark">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					position��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������position" id="position" name="position">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					lastLoginTime��
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="" placeholder="������lastLoginTime" id="lastLoginTime" name="lastLoginTime">
				</div>
			</div>
			<div class="row cl" style="text-align: right;margin-right: 2px">
				<button class="btn btn-success" type="submit">
					<i class="Hui-iconfont Hui-iconfont-save"></i>
			 		&nbsp;������Ϣ
				</button>
			</div>
		</form>
	</article>
</page:extends>
<jsp:include page="/parent/basepage.jsp" />
