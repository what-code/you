<%@page import="com.b5m.tao.common.GlobalWebCfg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page session="false"%>
<script type="text/javascript" src="${rootPath}/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${rootPath}/js/googleAnalytics.js"></script>
<script type="text/javascript" src="${rootPath}/js/b5t.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath}/js/b5tHeader.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath}/js/tao.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath}/js/tao-carousel.js?t=${today}"></script>
<link rel="stylesheet" type="text/css" href="${rootPath}/css/common.css?t=${today}">
<link rel="stylesheet" type="text/css" href="${rootPath}/css/spc_tao.css?t=${today}">
<script>
	if (window.screen.availWidth < 1400) {
		document
				.write('<link rel="stylesheet" type="text/css" href="${rootPath}/css/spc_tao_980.css?t=${today}">');
	}
</script>
<%
	String ucenterHttpUrl = GlobalWebCfg.getHttpUrl(request);
%>
<input type="hidden" id="curPageTag" name="curPageTag" value="${curPageTag }" />
<input type="hidden" id="keyWord" name="keyWord" value="${searchDto.keyWord }" />
<input type="hidden" id="hourHidden" name="hourHidden" value="${hour }" />
<input type="hidden" id="rootPath" name="rootPath" value="${rootPath }" />
<input type="hidden" id="nowDate" name="nowDate" value="${nowDate }" />
<input type="hidden" id="menuCurPageTag" name="menuCurPageTag" value="${menuCurPageTag }" />
<input type="hidden" id="totalPages" name="totalPages" value="${pageList.totalPages}" />
<input type="hidden" id="cityKey" name="cityKey" value="${dataCityKey[searchDto.ipLocate] }" />


<!--public-bar s-->
<div id="public-bar">
	<div class="layout">
		<div class="bar-nav">
			<ul>
				<li><a href="http://www.b5m.com" class="items i-1"><b></b>帮5买首页</a></li>
				<li><span class="lines"></span></li>
				<li><a href="http://tejia.b5m.com" class="items i-2"><b></b>淘特价</a></li>
				<li><span class="lines"></span></li>
				<li><a href="http://hao.b5m.com/" class="items i-3"><b></b>购物导航</a></li>
				<li><span class="lines"></span></li>
				<li><a href="http://app.b5m.com/index.html" class="items i-4"
					target="_blank"><b></b>手机帮5买</a></li>
				<li><span class="lines"></span></li>
				<li><a href="http://tiao.b5m.com/" class="items i-8"
					target="_blank"><b></b>帮5挑</a></li>
			</ul>
		</div>
		<div class="bar-login">
			<!-- 登陆成功的显示 -->
			<c:choose>
				<c:when
					test="${user_session_login == 'true' && user_session_login != null}">
					<input type="hidden" id="isLogin" name="isLogin" value="true" />
					<ul class="logged">
						<li class="per"><span class="items"> <em>${user_session_show_name}
							</em></span> <b class="per-name"></b>
							<ul class="per-list">
								<li><a href="<%=ucenterHttpUrl%>user/user/index.htm"
									class="i-5"><b></b>我的B5M</a></li>
								<li><a href="<%=ucenterHttpUrl%>user/user/logout.htm"
									class="i-7"><b></b>退出</a></li>
							</ul></li>
						<li><a onclick="AddFavorite()"
							style="cursor: pointer; padding-right: 0;" class="items">收藏本站</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<input type="hidden" id="isLogin" name="isLogin" value="false" />
					<ul class="not-logged">
						<li><a
							href="<%=ucenterHttpUrl%>forward.htm?method=/user/user/login"
							class="items">登录</a> <a
							href="<%=ucenterHttpUrl%>forward.htm?method=/user/info/register&userType=13"
							class="items">免费注册</a></li>
						<li><a onclick="AddFavorite()"
							style="cursor: pointer; padding-right: 0;" class="items">收藏本站</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<!--public-bar e-->

<!--search s-->
<div class="search layout clear-fix">
	<div class="hd-logo">
		<!--[if !IE]><!-->
		<h1>
			<a href="${rootPath }"><img
				src="${rootPath}/images/tao/logo_tao_24.png" alt="帮5游"></a>
		</h1>
		<!--<![endif]-->
		<!--[if IE]>
		<h1><a href="${rootPath }"><img src="${rootPath}/images/tao/logo_tao_08.png" alt="帮5游"></a></h1>
		<![endif]-->
	</div>
	<div class="hd-search">
		<ul class="hd-search-tab">
			<li id="hlProd"><a id="prodH" href="http://www1.b5m.com/"
				target="_blank">商品</a></li>
			<li><span class="lines"></span></li>
			<li><a id="taoH1" href="http://tejia.b5m.com">淘特价</a></li>
			<li><span class="lines"></span></li>
			<li><a id="youH" href="http://you.b5m.com/" class="cur">帮5游</a></li>
			<li><span class="lines"></span></li>
			<li id="hlTiao"><a id="tiaoH" href="http://tiao.b5m.com/"
				target="_blank">帮5挑</a></li>
			<li><span class="lines"></span></li>
			<li id="hlTuan"><a id="tuanH" href="http://old.b5m.com/o/tuan"
				target="_blank">团购</a></li>
			<li><span class="lines"></span></li>
			<li id="hlTicket"><a id="ticketH"
				href="http://old.b5m.com/o/ticket" target="_blank">票务</a></li>
			<li><span class="lines"></span></li>
			<li id="hlkorea"><a id="koreaH" href="http://old.b5m.com/korea"
				target="_blank">韩国馆</a></li>
		</ul>
		<div class="hd-search-area">
			<input class="input" autocomplete="off" type="text" id="query"
				x-webkit-speech="" maxlength="40" value="${searchDto.keyWord }" />
			<label id="label" style="display: none;">快速搜索 特惠旅游</label>
			<a id="btnSearch" href="javascript:;" class="btn">帮5搜</a> <b
				id="closes" style="display: none;" class="close">&times;</b>
		</div>
	</div>
</div>
<!--search e-->

<!--nav s-->
<div id="nav" class="layout">
	<div class="item">
		<a ${curPageTag=="index"?"class='cur'":" "} href="${rootPath}/city/${dataCityKey[searchDto.ipLocate]==null||dataCityKey[searchDto.ipLocate]==''?'':dataCityKey[searchDto.ipLocate] }">首页</a>
		<%-- 		<a ${curPageTag=="index"?"class='cur'":" "} href="${rootPath}">首页</a> --%>
		<a ${curPageTag=="domesticTravel"?"class='cur'":" "}
			href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_domesticTravel">国内游</a> 
		<a
			${curPageTag=="abroadTravel"?"class='cur'":" "}
			href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_abroadTravel">境外游</a> 
		<a
			${curPageTag=="peripheryTravel"?"class='cur'":" "}
			href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_peripheryTravel">周边游</a>
		<a ${curPageTag=="hotel"?"class='cur'":" "}
			href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_hotel">酒店</a>
	</div>
	<div class="plug-tools">
		<!--[if !IE]><!-->
		<a href="http://t.b5m.com/" target="_blank"><img
			src="../images/common/m_24.png" alt="下载帮5淘比价工具" title="下载帮5淘比价工具"
			style="margin-top: 10px;"></a>
		<!--<![endif]-->
		<!--[if IE]>
			<a href="http://t.b5m.com/" target="_blank"><img src="../images/common/m_08.png" alt="下载帮5淘比价工具" title="下载帮5淘比价工具"></a>
		<![endif]-->
	</div>
</div>
<!--nav e-->

<!--banner s-->
<c:if test="${curPageTag!=null && menuCurPageTag!=null}">
	<c:if test="${curPageTag!='index'}">
		<!-- city s-->
		<div class="layout clear-fix city-layout"
			style="position: relative; z-index: 999;">
			<div class="city">
				<div class="current-city-wrap">
					<div class="cityname-wrap">
						<span class="light">&nbsp;</span> <span class="city-name">${searchDto.ipLocate
							}</span>
					</div>
					<div class="arrow-wrap">
						<span class="arrow">&nbsp;</span>
					</div>
				</div>
				<div class="city-outer-wrap">
					<c:forEach var="c" items="${city }" varStatus="s">
						<a href="javascript:void(0);" data-key="${c.key }">${c.value }</a>
						<c:if test="${s.count%6==0 }">
							<br/>
						</c:if>
					</c:forEach>
				</div>
			</div>
			<span class="words"> <c:forEach items="${keyWords }" var="kw">
					<a href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html" target="_blank">${kw.keywords }</a>
				</c:forEach>
			</span>			
		</div>
		<!-- city e-->
	</c:if>
	<div class="layout">
		<div id="banner">
			<ul class="pic">
				<c:forEach items="${taoTopicList }" var="topic" varStatus="status">
					<li><a href="${topic.url1 }" target="_blank"><img
							data-src="${topic.imgUrl }" src="${topic.imgUrl }" ${status.count==1?'onload="this.parentNode.parentNode.parentNode.style.background=\'none\'"':'' } /></a></li>
				</c:forEach>
			</ul>
			<div class="txt">
				<c:forEach items="${taoTopicList }" var="topic" varStatus="status">
					<a href="javascript:;" ${status.count==1?'class="cur"':'' }> <span
						class="text">${topic.keyword1 }</span> <b class="bg"></b> <em
						class="tips"></em>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>
</c:if>
<!--banner e-->
