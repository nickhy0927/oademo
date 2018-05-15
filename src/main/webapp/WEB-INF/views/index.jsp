<%@page import="com.iss.itreasury.module.systemmanage.user.entity.User"%>
<%@page import="com.iss.itreasury.syscore.utils.Singleton"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://commons.tag" prefix="page" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="permission" uri="http://iss.premission.com" %>
<c:set value="${pageContext.request.contextPath }" var="ctx"></c:set>
<page:extends name="title">信息化管理系统</page:extends>
<page:extends name="css">
	<style type="text/css">
		.clearfix:after {
		  content: " ";
		  display: block;
		  clear: both;
		  height: 0;
		}
		.clearfix {
		  zoom: 1;
		}
		.stystemMsg {
			margin-bottom: 0px;
			font-size: 12px;
			max-width: 400px;
			width: auto;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		.wordmore a,.wordm a {
			max-width: 150px;
			width: auto;
			display:block;
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap;
		}
		.msgDate {
			color:#999;
			width: 110px;
			right: 0px;
			top: 0px;
			position: absolute;
			font-size: 12px;
		}
	</style>
</page:extends>
<page:extends name="javascript">
	<script type="text/javascript" src="${ctx}/statics/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
		$(function () {
			$("body").Huitab({
				tabBar:".navbar-wrapper .navbar-levelone",
				tabCon:".Hui-aside .menu_dropdown",
				className:"current",
				index:0
			});
			
			$(".cc-tit").each(function(){
				var this_ul = $(this).next("ul");
				if(this_ul){
					$(this).click(function(){
						var icls = $(this).children().eq(1);
						this_ul.slideToggle();
						icls.toggleClass('hicon');
					});
				}
			});
			
			$("#Hui-msg").mouseover(function(){
				$(this).children("ul").show();
			});
			
			$("#Hui-msg").mouseleave(function(){
				$(this).children("ul").hide();
			});
			initNotice();
		})
		
		function view_self_info () {
            var url = ctx + '/userSelfInfo.do?id=' + new Date().getTime();
            $("#contentIframe").attr('src',url);
            
            var bStopIndex=$("#min_title_list li").first().index();
    		var iframe_box=$("#iframe_box");
    		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
    		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
        }
		function editUserInfo () {
            var url = ctx + '/editUserInfo.do?id=' + new Date().getTime();
            $("#contentIframe").attr('src',url);
            
            var bStopIndex=$("#min_title_list li").first().index();
    		var iframe_box=$("#iframe_box");
    		$("#min_title_list li").removeClass("active").eq(bStopIndex).addClass("active");
    		iframe_box.find(".show_iframe").hide().eq(bStopIndex).show();
        }
		function notice_view(id) {
			var url = ctx + '/system/notice/noticeView.do?id=' + id;
			layer.open({
				type: 2,
				title: '通知详情',
				move: false,
				shadeClose: false,
				shade: 0.6,
				area: ['90%','90%'],
				content: url
			});
			//_init_dataGrid();
		}
		function initNotice() {
			$.ajax({
				url: ctx + "/system/notice/queryNoticeList.json",
				method:'POST',
				dataType:'JSON',
				data:$("#addForm").serialize(),
				success:function (data) {
					if (data.responseCode == 200) {
						var result = data.object;
						$(".badge-danger").text(result.length);
						var html = "";
						for(var i = 0; i < result.length; i ++) {
							var obj = result[i];
							if (i > 6) {
								//2018-05-09 【查看更多】始终可见，不受记录数量的限制
								/* html += '<li><a onclick="moreInfo()">查看更多</a></li>'; */
								break;
							}
							var type = obj.msgType;
							var msgType = "系统消息";
							if (type == 1) {
								msgType = "系统消息";
							} else if (type == 2) {
								msgType = "申请消息";
							} else if (type == 3) {
								msgType = "审批消息";
							} else if (type == 4) {
								msgType = "预警消息";
							}
							html += '<li>' +
										'<a style="font-weight:bold" href="javascript:;" onclick="notice_view(\'' + obj.id + '\')">'+
											'<p style="margin-bottom:0px;">' + msgType + '</p>' + 
											'<div style="width: 520px;position: relative;"><p class="stystemMsg" title=' + obj.content + '>' + obj.content + '</p>' + 
											'<p class="msgDate">' +  new Date(obj.createTime).format('yyyy-MM-dd hh:mm:ss') + '</p></div>' +
										'</a>' +
									'</li>';
						}
						
						html += '<li><a onclick="moreInfo()">查看更多</a></li>';//2018-05-09 modify by liuquan 
						$("#systemMsg").html(html)
					}
				},
				error : function (err) {
				}
			})
		}
		
		controllers.controller('MessageCtrl', ['$scope', function($scope){
			setInterval(function() {
				initNotice();
			}, 1000*60);
		}])
		
		function moreInfo () {
			var url = ctx + '/system/notice/queryNoticeList.do?id=' + new Date().getTime();
			layer.open({
				type: 2,
				title: '通知列表',
				move: false,
				shadeClose: false,
				shade: 0.6,
				area: ['90%','90%'],
				content: url
			});
			// var url = ctx + '/system/notice/queryNoticeList.do?id=' + new Date().getTime();
            // $("#contentIframe").attr('src',url);
		}
	</script>
</page:extends>
<page:extends name="body">
	<!-- top横向导航 -->
	<header class="navbar-wrapper" ng-app="objectApp">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl"> 
				<a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:;"><img src="${ctx}/statics/images/title_icon2.png" alt="logo" style="height: 40px;width: 40px;"></a> 
				<a class="logo navbar-logo-m f-l mr-10 visible-xs" href="javascript:;"><img src="${ctx}/statics/images/title_icon.png" alt="logo" style="height: 40px;width: 40px;"></a> 
				<span class="logo navbar-slogan f-l mr-10 hidden-xs logo-txt">云南能投</span> 
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
				<nav id="top-navigation" class="nav navbar-nav">
					<ul class="cl">
						<c:forEach items="${menuList}" var="menu" varStatus="item">
                            <permission:tag alias="${menu.menuAlias}">
                                <c:choose>
                                    <c:when test="${item.index == 0}">
                                        <li class="navbar-levelone current"><a href="javascript:;">${menu.menuName}</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="navbar-levelone"><a href="javascript:;">${menu.menuName}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </permission:tag>
						</c:forEach>
					</ul>
				</nav>
				<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li><i class="Hui-iconfont">&#xe62c;</i></li>
						<li class="dropDown dropDown_hover">
							<a href="#" class="dropDown_A">
								<% 
									User user = Singleton.getSingletonUser();
								%>
								<%=user.getLoginName() %>
								<i class="Hui-iconfont">&#xe6d5;</i>
							</a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a onClick="view_self_info()">个人信息</a></li>
								<li><a onclick="editUserInfo()">信息修改</a></li>
								<li><a href="${ctx}/logout.do">退出</a></li>
							</ul>
						</li>
						<li id="Hui-msg" ng-controller="MessageCtrl"> 
							<a href="#" title="消息"><span class="badge badge-danger"></span><i class="Hui-iconfont" style="font-size:18px">&#xe6c5;</i></a> 
							<ul class="menu radius box-shadow" id="systemMsg" style="width: auto;right: 0px;position: absolute; display: none;"></ul>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<!-- //top横向导航 -->
	
	<!-- left纵向导航 -->
	<aside class="Hui-aside">
		<c:forEach items="${menuList}" var="menu" varStatus="it">
			<c:set value="${menu.children}" var="children"></c:set>
            <permission:tag alias="${menu.menuAlias}">
                <div class="menu_dropdown bk_2">
                    <dl id="menu-article">
                        <c:forEach items="${children}" var="child">
                            <c:set value="${child.children}" var="cch"></c:set>
                            <permission:tag alias="${child.menuAlias}">
                            	<dt>
	                                <i class="Hui-iconfont">&#xe616;</i>
	                                ${child.menuName}
	                                <i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
	                            </dt>
	                            <dd>
	                                <ul>
	                                    <c:forEach items="${cch}" var="cc">
	                                        <permission:tag alias="${cc.menuAlias}">
	                                            <li>
	                                            	<c:choose>
	                                            		<c:when test="${fn:length(cc.children) > 0}">
                                            				<p class="cc-tit">
                                            					<i class="Hui-iconfont" data-title="${cc.menuName}" title="${cc.menuName}" style="padding-left: 20px;">&#xe6bf;</i>
								                                ${cc.menuName}
								                                <span class="Hui-iconfont down-icon"></span>
                                            				</p>
				                                            <ul style="padding-left: 15px; display: none;">
		                                            			<c:forEach items="${cc.children}" var="chl">
					                                            	<li><p class="wordm">
					                                            		<a data-href="${chl.url}" data-title="${chl.menuName}" title="${chl.menuName}" href="javascript:void(0)">
					                                            			<i class="Hui-iconfont">&#xe667;</i>
					                                            			${chl.menuName}
					                                            		</a></p>
					                                            	</li>
						                                        </c:forEach>
				                                            </ul>
	                                            		</c:when>
	                                            		<c:otherwise>
	                                            		<p class="cc-tit wordmore">
	                                            			<a data-href="${cc.url}" data-title="${cc.menuName}" title="${cc.menuName}" href="javascript:void(0)"><i class="Hui-iconfont">&#xe6bf;</i> ${cc.menuName}</a>
                                            			</p>
	                                            		</c:otherwise>
	                                            	</c:choose>
	                                            </li>
	                                        </permission:tag>
	                                    </c:forEach>
	                                </ul>
	                            </dd>
                            </permission:tag>
                        </c:forEach>
                    </dl>
                </div>
            </permission:tag>
		</c:forEach>
	</aside>
	<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
	<!-- //left纵向导航 -->
	
	<!-- content -->
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active">
						<span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em>
					</li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
				<a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe id="contentIframe" scrolling="yes" frameborder="0" src="${ctx}/task/mainIndex.do"></iframe>
			</div>
		</div>
	</section>
	
	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前 </li>
			<li id="closeall">关闭全部 </li>
		</ul>
	</div>
	<!-- //content -->
</page:extends>
<jsp:include page="/parent/base_page.jsp" />