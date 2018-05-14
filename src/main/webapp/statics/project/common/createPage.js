/**
 * 图标添加页面
 */
$(function () {
	$("#createForm").validate({
	    rules: {
	    	className: { required: true},
            phoneNumber: { required: true, maxlength: 13, number: true}
        },
	    onkeyup: false,
	    focusCleanup: true,
	    success: "valid",
	    submitHandler: function (form) {
	    	_saveForm();
	    }
	});
});

/**
 * 失败回调函数
 */
var _err_callback = function (XMLHttpRequest, error, errorThrown) {
	$.closeLoading();
	$.openTip('保存失败，请稍候再试...',true, function() {
		$.closeLoading();
		return ;
	});
}

var _success_callback = function (response) {
    var data = eval("(" + response + ")");
    console.log(data)
    if (data.responseCode == 200) {
    	$("#test").html(data.object)
    	$.openTip(data.responseMessage,true, function() {
    		$.closeLoading();
//    		parent.window.location.href = ctx + '/system/user/userList.do';
//            var index = parent.layer.getFrameIndex(window.name);
//            parent.layer.close(index);
		});
    } else {
    	$.closeLoading();
    	$.openTip(data.message,true, function() {
    		$.closeLoading();
			return ;
        });
    } 
}


var _saveForm = function () {
	$.openTip('确定保存吗？',false, function(){
		$.closeLoading();
		$.openLoading('正在保存，请稍等...');
        jQuery.ajax({
            type: "POST",
            url: ctx + "/create/page.json",
            data: $("#createForm").serialize(),
            error: _err_callback,
            success: _success_callback
    	 });
    });
}

