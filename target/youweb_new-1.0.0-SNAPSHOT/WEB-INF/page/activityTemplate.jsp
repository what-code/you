<%@page import="com.b5m.tao.common.GlobalWebCfg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%
  String ucenterHttpUrl = GlobalWebCfg.getHttpUrl(request);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
    <title>${topicMain.name }-酒店指南-帮5买</title>
	<meta content="周边游,酒店预订,帮5买,B5M" name="keywords"/>
	<meta content="帮5游旅游网汇聚全部旅游网特惠信息，帮您精选最优质的旅游团购、特惠产品，包含国内游、出境游、酒店预定等各种服务。帮5买旗下网站：帮5游旅游网。" name="description"/>
    <link rel="stylesheet" href="http://staticcdn.b5m.com/static/css/common/common.css">
    <link rel="stylesheet" href="${rootPath}/css/activityTemplate.css"/>
    <%-- <c:import url="common/youCssHeader.jsp"></c:import> --%>
	<base target="_blank">
</head>
<body>
<div class="topbar">
    <div class="topbar-inner">
        <div class="topbar-nav">
            <a class="home" href="http://www.b5m.com">帮5买首页</a>
            <span class="line"></span>
            <a class="buy" href="http://hao.b5m.com">购物导航</a>
            <span class="line"></span>
            <a class="tao" href="http://t.b5m.com">帮5淘</a>
            <span class="line"></span>
            <a class="app" href="http://app.b5m.com">手机帮5买</a>
        </div>
        <c:choose>
			<c:when
				test="${user_session_login == 'true' && user_session_login != null}">
				<input type="hidden" id="isLogin" name="isLogin" value="true" />
				<!-- after login -->
				<div class="topbar-user topbar-user-login">
					<span class="hi">你好，</span> <span data-hover="user-hover"
						class="user"> <a id="b5muser" target="_self" href="<%=ucenterHttpUrl%>user/user/index.htm">${user_session_show_name}</a>
						<em class="arr"></em> <span class="user-link"> <a href="<%=ucenterHttpUrl%>user/user/index.htm">积分</a>
							<a href="<%=ucenterHttpUrl%>user/user/logout.htm">退出</a>
					</span>
					</span> <span class="line"></span> <a id="J_addFav" rel="fav" target="_self"
						href="javascript:AddFavorite();">收藏本站</a> <span class="line"></span>
					<div data-hover="topbar-more-hover" class="topbar-more">
						<a class="more" target="_self" href="javascript:void(0)">网站导航<em
							class="arr"></em></a>
						<div class="topbar-prod">
							<div class="item">
								<a target="_blank" href="http://www.b5m.com">帮5买</a>
								<a target="_blank" href="${rootPath }">淘特价</a>
								<a target="_blank" href="http://you.b5m.com">帮5游</a>
								<a target="_blank" href="http://guang.b5m.com">帮5逛</a>
								<a target="_blank" href="http://korea.b5m.com">韩国馆</a>
								<a target="_blank" href="http://tuan.b5m.com/">帮团购</a>
<!-- 								<a target="_blank" href="http://old.b5m.com/o/ticket">票务</a> -->
							</div>
							<div class="item">
								<a target="_blank" href="http://t.b5m.com/" class="h_icon b5t">帮5淘</a>
								<a target="_blank" href="http://hao.b5m.com/" class="h_icon gwdh">购物导航</a>
								<a target="_blank" href="http://app.b5m.com/" class="h_icon sjb5m">手机帮5买</a>
							</div>
							<div class="item weixin">
								<img src="http://cdn.bang5mai.com/upload/web/public/app/header/images/new_header/weixin_b5m.png" alt="" /><span>扫二维码，加帮5买微信好友</span>
							</div>
						</div>
					</div>
				</div>
				<!-- /after login -->
			</c:when>
			<c:otherwise>
				<input type="hidden" id="isLogin" name="isLogin" value="false" />
				<!-- before login -->
				<div class="topbar-user">
		            <a href="<%=ucenterHttpUrl%>forward.htm?method=/user/user/login" target="_self">登录</a>
		            <a href="<%=ucenterHttpUrl%>forward.htm?method=/user/info/register&userType=6" target="_self">免费注册</a>
		            <a href="javascript:void(0)" rel="fav" target="_self">收藏本站</a>
		            <span class="line"></span>
		            <div class="topbar-more" data-hover="topbar-more-hover">
		                <a href="javascript:void(0)" target="_self" class="more">网站导航<em class="arr"></em></a>
		                <div class="topbar-prod">
		                    <div class="item">
		                    	<a href="http://www.b5m.com" target="_blank">帮5买</a>
		                    	<a href="${rootPath }" target="_blank">淘特价</a>
		                    	<a href="http://you.b5m.com" target="_blank">帮5游</a>
								<a target="_blank" href="http://guang.b5m.com">帮5逛</a>
		                    	<a href="http://korea.b5m.com" target="_blank">韩国馆</a>
		                    	<a href="http://tuan.b5m.com/" target="_blank">帮团购</a>
<!-- 		                    	<a href="http://old.b5m.com/o/ticket" target="_blank">票务</a> -->
		                    </div>
		                    <div class="item">
		                    	<a class="h_icon b5t" href="http://t.b5m.com/" target="_blank">帮5淘</a>
		                    	<a class="h_icon gwdh" href="http://hao.b5m.com/" target="_blank">购物导航</a>
		                    	<a class="h_icon sjb5m" href="http://app.b5m.com/" target="_blank">手机帮5买</a>
		                    </div>
		                    <div class="item weixin"><img alt="" src="http://cdn.bang5mai.com/upload/web/public/app/header/images/new_header/weixin_b5m.png"><span>扫二维码，加帮5买微信好友</span></div>
		                </div>
		            </div>
		        </div>
				<!-- /before login -->
			</c:otherwise>
		</c:choose>
    </div>
</div>
<div class="header">
    <div class="wp">
        <h1>
            <a href="http://www.b5m.com/">
                <img src="http://staticcdn.b5m.com/static/images/www/logo.png" alt="帮5买"></a>
        </h1>
        <ul class="list-h clear-fix">
              <li class="cur"><a target="_self" href="http://www.b5m.com">首页</a></li>
                <li><a href="http://zdm.b5m.com">值得买</a></li>
                <li><a href="http://tejia.b5m.com">淘特价</a></li>
                <li><a href="http://haiwai.b5m.com">海外馆</a> <i class="new">新产品</i></li>
                <li><a href="http://you.b5m.com">帮5游</a></li>
                <li><a href="http://tuan.b5m.com">帮5团</a></li>
                <li><a href="http://guang.b5m.com">帮5逛</a><i class="new">新产品</i></li>
                <li><a href="http://she.b5m.com">帮社区</a></li>
                <li><a href="http://t.b5m.com">帮5淘</a></li>
        </ul>
    </div>
    <div class="search-bar">
        <div class="wp">
	        <div class="all-cate all-cate-none"></div>
	        <div class="search-tools">
	        	<form id='frm1' name='frm1' target="_blank" onsubmit="return searchfrm(this,this.id)" method="post" class="search-form">
	            	<input type='hidden' id='urlfrm1' value='http://s.b5m.com/search/s/___image________________[##].html'>
						<span class="header-search-content">
							<label for="帮5搜">搜索从这里开始</label>
							<input id="query" type="text" class="header-search-key J_search"  x-webkit-speech=""  autocomplete="off" placeholder="快速搜索 全网比价 请输入您想买的商品 ">
							<a href="javascript:void(0);" target='_self' onclick="searchfrm(document.frm1,'frm1');if(document.getElementById('query').value!='')document.frm1.submit();" class="header-rearch-submit">帮5搜</a>
						</span>
	            </form>
	            <div id="downList">
	                <div class="downList">
	                    <ul id="list"></ul>
	                </div>
	            </div>
	        </div>
    	</div>
    </div>
</div>
<!--内容区 -Start- -->
<div class="main-cuxiao pd56" style="background:url(${topicMain.imgUrl}) no-repeat scroll center 0 ${topicMain.bgcolor}">
    <div class="bnner" style="height:378px"></div>
    <c:forEach var="bar" items="${listBar }" varStatus="s">
	    <div class="column mt4 clear-fix">
	        <div class="cloumn-tit" style="background: url(${bar.imgUrl }) no-repeat;color:${bar.fontcolor}">${bar.title }</div>
	        <div class="column-list mt6">
	            <div class="column-list-box">
		            <c:forEach var="gl" items="${bar.topics}" varStatus="status">
		            	<dl>
		                    <dt><a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${gl.id }.html"
									target="_blank" rel="nofollow">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】
									${gl.name }</a></dt>
		                    <dd class="pic">
		                        <a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${gl.id }.html" target="_blank" rel="nofollow" data-rd="2">
									<img src="${gl.imgurl }" alt="${gl.name }">
		                            <span class="list-price"><span><em>${gl.totalClick }</em>人抢购</span><del>原价：&yen;${gl.salesPrice }</del>
									</span>
		                        </a>
		                    </dd>
		                    <dd class="list-bt">
		                        <a href="${gl.clickUrl }" target="_blank" class="list-bt-buy">去抢购</a>
		                        <span><s>&yen;</s>${gl.salesPrice }</span>
		                    </dd>
		                </dl>
					</c:forEach>
	            </div>
	        </div>
	    </div>
    </c:forEach>
    <%-- <div class="column mt4 clear-fix">
    	<c:forEach var="bar" items="${listBar }" varStatus="s" begin="0" end="0">
	        <div class="cloumn-tit" style="background: url(${bar.imgUrl }) no-repeat;color:${bar.fontcolor}">${fn:indexOf(bar.title,'&nbsp;')>-1||fn:indexOf(bar.title,' ')>-1?'&nbsp;&nbsp;&nbsp;&nbsp;':'' }其他活动推荐</div>
    	</c:forEach>
        <div class="column-add mt6">
            <a href="${rootPath }/jiukuaiyou/jiu.html" target="_blank"><img src="../images/add_1.jpg"/></a>
            <a href="${rootPath }/jiukuaiyou/shijiu.html" target="_blank"><img src="../images/add_2.jpg"/></a>
            <a href="${rootPath }/zhekou.htm" target="_blank"><img src="../images/add_3.jpg"/></a>
            <a href="${rootPath }/jiangjia.htm" target="_blank"><img src="../images/add_4.jpg"/></a>
            <a href="${rootPath }/duihuan" target="_blank"><img src="../images/add_5.jpg"/></a>
            <!--<a href="" target="_blank"><img src="../images/add_6.jpg" width="981" height="132"/></a>-->
        </div>
    </div> --%>
    <!-- <div class="add-box">
        <a target="_blank"><img src="../images/add_6.jpg" width="981" height="132"/></a>
    </div> -->
</div>
<!--内容区 -End- -->
<div class="footer">
    <script type='text/javascript' src='http://staticcdn.b5m.com/static/scripts/public/copyright.js'></script>
    <!-- Start Alexa Certify Javascript -->
	<script type="text/javascript">
	_atrk_opts = { atrk_acct:"InfVh1aUXR00ax", domain:"b5m.com",dynamic: true};
	(function() { var as = document.createElement('script'); as.type = 'text/javascript'; as.async = true; as.src = "https://d31qbv1cthcecs.cloudfront.net/atrk.js"; var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(as, s); })();
	</script>
	<noscript><img src="https://d5nxst8fruw4z.cloudfront.net/atrk.gif?account=InfVh1aUXR00ax" style="display:none" height="1" width="1" alt="" /></noscript>
	<!-- End Alexa Certify Javascript -->
</div>
<!--搜索下拉框 -Start- -->
<input autocomplete="off" type="hidden" id="ajaxCollection" value="b5mp" />
<input autocomplete="off" type="hidden" id="hdnCurIndex" />
<input autocomplete="off" type="hidden" id="hdnKeyTemp" />
<input autocomplete="off" type="hidden" id="hdnTarget" value="http://s.b5m.com/search/s/___image________________%23keyword%23.html ">
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/down_list.js"></script> -->
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/autoFill.js?t=${today}"></script>
<!--搜索下拉框 -Start- -->
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/public/public-v1.js"></script>
<!--搜索下拉框 -End- -->
<!-- <script type="text/javascript" src="http://cdn.bang5mai.com/upload/web/public/app/tongji/spc.min.js"></script>
<script type='text/javascript' src='http://cdn.bang5mai.com/upload/web/public/app/tongji/stat.min.js'></script> -->
<script type='text/javascript' src='http://cdn.bang5mai.com/upload/web/public/app/tongji/stat2.min.js'></script>
<!-- auto fill js s -->
	<script>
          $(function() {
        	  $('#query').autoFill('http://s.b5m.com/allAutoFill.htm','hotel');
          });
    </script>
    <!-- auto fill js e -->
</body>
</html>