<%@ page import="java.util.UUID" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.common.page/core" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<page:extends name="title">用户登录</page:extends>
<page:extends name="css">
    <link rel="stylesheet" type="text/css" href="${ctx}/statics/css/H-ui.login.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/statics/lib/Hui-iconfont/1.0.8/iconfont.css"/>
</page:extends>
<page:extends name="javascript">
    <script type="text/javascript">
        var _topWin = window;
        while ( _topWin != _topWin.parent.window ) {
            _topWin = _topWin.parent.window;
        }
        if ( window != _topWin ) _topWin.document.location.href = '${ctx}/';

        $(function () {
            $("#noClear").click(function () {
                var random = new Date().getTime();
                $("#codeImg").attr('src', ctx + "/getCode.images?time=" + random);
            });
			$(document).keyup(function(event) {
				if (event.keyCode == 13) {
					login();
				}
			});
            $('#loginBtn').click(function () {
            	login();
            });
        });
        
        function login() {
        	$.openLoading();
            $.ajax({
                url : '${ctx}/login.json' ,
                async : false ,
                method:'POST',
                dataType:'JSON',
                data:$("#form-login").serialize(),
                success : function (data) {
                    var index = data.result;
                    if (data.code == 200) {
                        window.location.href = ctx + "/" + index + "?jsessionid=<%=UUID.randomUUID().toString().replaceAll("-","").toLowerCase()%>";
                    } else {
                        $.closeLoading();
                        $("#errMsg").html(data.msg);
                        setTimeout(function () {
                            $("#errMsg").html('');
                        },3000);
                        return;
                    }
                   /*  console.log(data) */
                } ,
                error : function () {
                    $.closeLoading();
                }
            });
        }
    </script>
</page:extends>
<page:extends name="body">
    <div class="header"></div>
    <div class="loginWraper">
        <div id="loginform" class="loginBox">
            <form id="form-login" class="form form-horizontal" method="post">
                <div class="row cl">
                    <div id="errMsg" class="formControls col-xs-12" style="text-align: center;color: red;height: 20px;line-height: 20px">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                    <div class="formControls col-xs-8">
                        <input type="text" name="loginName" placeholder="账户" class="input-text size-L">
                    </div>
                </div>
                <div class="row cl">
                    <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                    <div class="formControls col-xs-8">
                        <input type="password" name="password" placeholder="密码" class="input-text size-L">
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3">
                        <input class="input-text size-L" name="validateCode" type="text" placeholder="验证码"
                               onblur="if(this.value==''){this.value='验证码:'}"
                               onclick="if(this.value=='验证码:'){this.value='';}" style="width:150px;">
                        <img id="codeImg" src="${ctx}/getCode.images?time=<%=System.currentTimeMillis()%>"> <a id="noClear" href="javascript:;">看不清，换一张</a>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3">
                        <label for="online">
                            <input type="checkbox" name="online" id="online" value="">
                            使我保持登录状态
                        </label>
                    </div>
                </div>
                <div class="row cl">
                    <div class="formControls col-xs-8 col-xs-offset-3">
                        <input id="loginBtn" type="button" class="btn btn-success radius size-L mllg"
                               value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="footer">Copyright 你的公司名称 by H-ui.admin v3.1</div>
</page:extends>
<jsp:include page="/parent/basepage.jsp"/>