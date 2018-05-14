<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.common.page/core" prefix="page"%>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>
<page:extends name="title">
	Menu
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
					url: { required: true},
					alias: { required: true},
					remark: { required: true},
					menuId: { required: true},
					enable: { required: true},
					shows: { required: true},
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
			$.openTip('±£¥Ê ß∞‹£¨«Î…‘∫Ú‘Ÿ ‘...',true, function() {
				$.closeLoading();
				return ;
			});
		};
		var _success_callback = function (response) {
			var data = eval("(" + response + ")");
			if (data.responseCode == 200) {
				$.openTip(data.responseMessage,true, function() {
					$.closeLoading();
					parent.window.location.href = ctx +'/platform/menu/menuEdit.do';
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
			$.openTip('»∑∂®±£¥Ê¬£ø',false, function(){
				$.closeLoading();
				$.openLoading();
        		jQuery.ajax({
            		type: "POST",
            		url: ctx + '/platform/menumenuEdit.json',
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
					code£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.code}" placeholder="«Î ‰»Îcode" id="code" name="code">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					name£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.name}" placeholder="«Î ‰»Îname" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					url£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.url}" placeholder="«Î ‰»Îurl" id="url" name="url">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					alias£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.alias}" placeholder="«Î ‰»Îalias" id="alias" name="alias">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					remark£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.remark}" placeholder="«Î ‰»Îremark" id="remark" name="remark">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					menuId£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.menuId}" placeholder="«Î ‰»ÎmenuId" id="menuId" name="menuId">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					enable£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.enable}" placeholder="«Î ‰»Îenable" id="enable" name="enable">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2">
					<span class="c-red">*</span>
					shows£∫
				</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="text" class="input-text" value="${menu.shows}" placeholder="«Î ‰»Îshows" id="shows" name="shows">
				</div>
			</div>
			<div class="row cl" style="text-align: right;margin-right: 2px">
				<button class="btn btn-success" type="submit">
					<i class="Hui-iconfont Hui-iconfont-save"></i>
			 		&nbsp;±£¥Ê–≈œ¢
				</button>
			</div>
		</form>
	</article>
</page:extends>
<jsp:include page="/parent/basepage.jsp" />
