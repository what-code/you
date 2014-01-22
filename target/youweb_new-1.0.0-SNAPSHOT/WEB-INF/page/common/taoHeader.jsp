<%@page import="com.b5m.tao.common.GlobalWebCfg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page session="false"%>

<%
	String ucenterHttpUrl = GlobalWebCfg.getHttpUrl(request);
%>
<input type="hidden" id="curPageTag" name="curPageTag"
	value="${curPageTag }" />
<input type="hidden" id="keyWord" name="keyWord"
	value="${searchDto.keyWords }" />
<input type="hidden" id="hourHidden" name="hourHidden" value="${hour }" />
<input type="hidden" id="rootPath" name="rootPath" value="${rootPath }" />
<input type="hidden" id="nowDate" name="nowDate" value="${nowDate }" />
<input type="hidden" id="menuCurPageTag" name="menuCurPageTag"
	value="${menuCurPageTag }" />
<input type="hidden" id="totalPages" name="totalPages"
	value="${pageList.totalPages}" />
<input type="hidden" id="cityKey" name="cityKey"
	value="${dataCityKey[searchDto.ipLocate] }" />
<input type="hidden" id="auto_fill_chn" name="auto_fill_chn" value="${curPageTag}" />
<input type="hidden" id="auto_fill_server" name="auto_fill_server" value="${autofillServer}" />
<div class="top-hdbanner">
        <a href="http://www.b5m.com/huodong.php" target="_blank">
		<img src="http://staticcdn.b5m.com/static/images/huodong/www/bop-banner.jpg" width="980" height="60" alt="">
        </a>
</div>
<div class="topbar">
	<div class="topbar-inner">
		<div class="topbar-nav">
			<a href="http://www.b5m.com" class="home">帮5买首页</a><span class="line"></span>
			<a href="http://hao.b5m.com" class="buy">购物导航</a><span class="line"></span>
			<a href="http://t.b5m.com/" class="tao">帮5淘</a><span class="line"></span>
			<a href="http://app.b5m.com" class="app">手机帮5买</a>
		</div>
		
		<c:choose>
			<c:when
				test="${user_session_login == 'true' && user_session_login != null}">
				<input type="hidden" id="isLogin" name="isLogin" value="true" />
				<!-- after login -->
				<div class="topbar-user topbar-user-login">
					<span class="hi">你好，</span> <span data-hover="user-hover"
						class="user"> <a id="b5muser" target="_self"
						href="<%=ucenterHttpUrl%>user/user/index.htm">${user_session_show_name}</a>
						<em class="arr"></em> <span class="user-link"> <a
							href="<%=ucenterHttpUrl%>forward.htm?method=/user/trade/common/record/index">帮豆</a> <a
							href="<%=ucenterHttpUrl%>user/user/logout.htm">退出</a>
					</span>
					</span> <span class="line"></span> <a id="J_addFav" rel="fav" target="_self"
						href="javascript:void(0)">收藏本站</a> <span class="line"></span>
					<div data-hover="topbar-more-hover" class="topbar-more">
						<a class="more" target="_self" href="javascript:void(0)">网站导航<em
							class="arr"></em></a>
						<div class="topbar-prod">
							<div class="item">
								<a target="_blank" href="http://www.b5m.com">帮5买</a> <a
									target="_blank" href="http://tejia.b5m.com">淘特价</a> <a
									target="_blank" href="${rootPath }">帮5游</a> <a
									target="_blank" href="http://guang.b5m.com">帮5逛</a> <a
									target="_blank" href="http://korea.b5m.com">韩国馆</a> <a
									target="_blank" href="http://tuan.b5m.com">帮团购</a> 
									<a target="_blank" href="http://piao.b5m.com">帮票务</a>
									<a target="_blank" href="http://haiwai.b5m.com/">海外馆</a>
									<!-- <a target="_blank" href="http://daikuan.b5m.com/xiaofei/?utm_source=b5m">帮5贷</a> -->
							</div>
							<div class="item">
								<a target="_blank" href="http://t.b5m.com/" class="h_icon b5t">帮5淘</a>
								<a target="_blank" href="http://hao.b5m.com/"
									class="h_icon gwdh">购物导航</a> <a target="_blank"
									href="http://app.b5m.com/" class="h_icon sjb5m">手机帮5买</a>
							</div>
							<div class="item weixin">
								<img src="${rootPath}/images/common/weixin_b5m.png" alt="" /><span>扫二维码，加帮5买微信好友</span>
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
					<a href="<%=ucenterHttpUrl%>forward.htm?method=/user/user/login"
						target="_self">登录</a> <a
						href="<%=ucenterHttpUrl%>forward.htm?method=/user/info/register&userType=13"
						target="_self">免费注册</a> <a href="javascript:void(0)" rel="fav"
						target="_self">收藏本站</a> <span class="line"></span>
					<div class="topbar-more" data-hover="topbar-more-hover">
						<a href="javascript:void(0)" target="_self" class="more">网站导航<em
							class="arr"></em></a>
						<div class="topbar-prod">
							<div class="item">
								<a href="http://www.b5m.com" target="_blank">帮5买</a> <a
									href="http://tejia.b5m.com" target="_blank">淘特价</a> <a
									href="${rootPath }" target="_blank">帮5游</a> <a
									href="http://guang.b5m.com" target="_blank">帮5逛</a> <a
									href="http://korea.b5m.com" target="_blank">韩国馆</a> <a
									href="http://tuan.b5m.com" target="_blank">帮团购</a> 
									<a target="_blank" href="http://piao.b5m.com">帮票务</a>
									<a target="_blank" href="http://haiwai.b5m.com/">海外馆</a>
									<!-- <a target="_blank" href="http://daikuan.b5m.com/xiaofei/?utm_source=b5m">帮5贷</a> -->
							</div>
							<div class="item">
								<a class="h_icon b5t" href="http://t.b5m.com/" target="_blank">帮5淘</a>
								<a class="h_icon gwdh" href="http://hao.b5m.com/"
									target="_blank">购物导航</a> <a class="h_icon sjb5m"
									href="http://app.b5m.com/" target="_blank">手机帮5买</a>
							</div>
							<div class="item weixin">
								<img alt=""
									src="http://cdn.bang5mai.com/upload/web/public/app/header/images/new_header/weixin_b5m.png"><span>扫二维码，加帮5买微信好友</span>
							</div>
						</div>
					</div>
				</div>
				<!-- /before login -->
			</c:otherwise>
		</c:choose>
	</div>
</div>
<div class="header">
	<div class="top">
		<div class="panel">
			<h1 class="logo l">
				<a href="${rootPath }"><img width="198" height="72"
					alt="logo" src="${rootPath}/images/you/logo.jpg"></a>
			</h1>
			<div class="panel-nav r sprite">
				<span>特价旅游</span><span>超值酒店</span><span>省心省钱</span>
			</div>
			
			 <!-- <h1 class="logo l"><a href="#"><img width="198" height="72" alt="logo" src="../../images/you/logo.jpg"></a></h1>
                    <div class="panel-nav r sprite">
                        <span>特价旅游</span><span>超值酒店</span><span>省心省钱</span>
                    </div>

                    country html
              <div class="country-box">
                   <a class="chn" href="http://www.b5m.com" title="帮5买比价网">帮5买</a>
                   <a class="usa" href="javascript:void(0)">美国馆</a>
                   <a class="kor" href="http://korea.b5m.com" title="韩国馆">韩国馆</a>
              </div> -->
		</div>
	</div>
	<div class="nav">
		<div class="panel">
			<ul class="cf f-yh">
				<li class="b5m-home"><a class="icon-you"
					href="http://www.b5m.com"><span>帮5买首页</span></a></li>
				<li ${(curPageTag=="index" || curPageTag=="searchresult" || curPageTag=="sitemap")?"class=\"cur nob\"":" "}><a href="${rootPath }">首页</a></li>
				<li ${curPageTag=="domesticTravel"?"class=\"cur nob\"":" "}><a
					href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel">国内游</a></li>
				<li ${curPageTag=="abroadTravel"?"class=\"cur nob\"":" "}><a
					href="${rootPath}/taoPage_${searchDto.selectedCityId}_abroadTravel">境外游</a></li>
				<li ${curPageTag=="peripheryTravel"?"class=\"cur nob\"":" "}><a
					href="${rootPath}/taoPage_${searchDto.selectedCityId}_peripheryTravel">周边游</a></li>
				<li
					${curPageTag=="hotel"|| curPageTag=="hotelSearchresult" || curPageTag=="ihs" ? " class=\"cur nob last\"":" "}><a
					href="${rootPath}/taoPage_${searchDto.selectedCityId}_hotel">酒店</a></li>
				
				<li class=""><a href="http://www.qiantuan.com" target="_blank">特惠境外游</a></li>
				<li class="last"><a href="http://www.qiantuan.com/ticket/" target="_blank">特价机票</a></li>
			</ul>
		</div>
	</div>
	<!-- <div class="search">
		<div class="panel"></div>
	</div> -->
	<!-- 搜索无结果页 搜索框 s -->
	<c:if test="${curPageTag=='noNoteResult' || curPageTag=='noResult'}">
		<div class="header-search">
            <div class="header-main">
                <span class="header-search-content">
                <!-- <form target="_self" action="" method="post" onsubmit="return searchfrm(this,this.id)" id="frm1"> -->
                	<!-- <input type="hidden" value="http://tejia.b5m.com/taoPage_all_searchresult_[##]" id="urlfrm1"> -->
                    <label for="帮5搜">搜索从这里开始</label>
                        <input id="header-search-key" name="keywords" type="text" placeholder="输入目的地、主题或关键词" class="header-search-key J_search"><input type="submit" value="帮5搜" class="header-rearch-submit" id="header-rearch-submit">
                		<input type="hidden" id="noresultSelectedCityId" name="noresultSelectedCityId" value="${searchDto.selectedCityId}" />
                <!-- </form>  -->
                </span>
                <!-- <span class="header-rearch-hotkey">
                    热门搜索：
                    <a target="_self" href="http://you.b5m.com/taoPage_2_searchresult_厦门_1">厦门</a>
	                <a target="_self" href="http://you.b5m.com/taoPage_2_searchresult_桂林_1">桂林</a>
	                <a target="_self" href="http://you.b5m.com/taoPage_2_searchresult_北京_1">北京</a>           
               	</span>  -->    
            </div>
        </div>
     </c:if>
     <!-- 搜索无结果页 搜索框 e -->
</div>
<c:if
	test="${curPageTag!='sitemap' && curPageTag!='noNoteResult' && curPageTag!='domesticTravel'&&curPageTag!='abroadTravel'&&curPageTag!='peripheryTravel'&&curPageTag!='hotel'&&curPageTag!='noResult'&&curPageTag!=''&&curPageTag!='notes'&&curPageTag!=null}">
	<!-- 帮5游搜索 -->
	<div class="classify cf w980">
		<div class="r">
			<p class="b5y-intro">
				<strong>为什么选择帮5游</strong> <span class="icon-you">全网旅游特惠</span> <span
					class="icon-you">旅游、酒店、机票一条龙</span> <span class="icon-you">使用还能返帮币</span>
				<span class="icon-you">商家对接认证，安全放心</span>
			</p>
			<div class="b5y-app">
				<div href="#" class="app" data-hover="app-hover">
					帮5游app <em class="icon-you"></em> <em class="arr"></em>
					<div class="app-down">
						<a href="https://itunes.apple.com/cn/app/te-hui-you/id674936138?mt=8" target="_blank">iPhone下载</a> 
						<a href="http://android.myapp.com/android/appdetail.jsp?appid=10104652&actiondetail=0&pageNo=1&clickpos=1&transactionid=1377065504848934&lmid=1022&softname=%E7%89%B9%E6%83%A0%E6%B8%B8" target="_blank">Android下载</a>
					</div>
				</div>
				<a href="http://www.weibo.com " class="weibo" target="_blank">微博<em
					class="icon-you"></em></a>
			</div>
		</div>
		<div class="filter">
			<ul class="l filter-nav" id="J_filterNav">
				<li><a href="javascript:void(0)" hidefocus="true"
					class="icon-you hotel ${curPageTag=='hotel' || curPageTag=='hotelSearchresult' ? 'cur':'' || curPageTag=='index' || curPageTag=='ihs' ? 'cur':'' }">酒店</a></li>
				<li><a href="javascript:void(0)" hidefocus="true"
					class="icon-you holidays ${curPageTag=='searchresult' || curPageTag=='its' ? 'cur':''}">度假</a></li>
				<li><a href="javascript:void(0)" hidefocus="true"
					class="icon-you gonglue ${curPageTag=='noteSearchresult' || curPageTag=='ins' ? 'cur':'' }">攻略</a></li>
			</ul>
			<div class="filter-cont l" id="J_filterCont">

				<!-- 酒店搜索 -->
				<div class="filter-hotel">
					<div class="filter-tit">
						<span class="f-yh">酒店搜索</span>
					</div>
					<div class="rows">
						<form id="hotel_search_form" class="search-form" action="search.html" method="post">
							<label>我想找</label>
							<div class="ui-select local local2" data-local="true">
								<span class="t l" id="hotelCityFind"><%-- ${searchDto.ipLocate } --%>${searchDto.selectedCityId == -1 ? '全部':dataCityKeyId[searchDto.selectedCityId]}</span><em
									class="icon-you r arr"></em>
							</div>
							<input type="text" id="hotel_keyword_text"  name="keywords" class="filter-text l ml10 filter-text2 J-tip" placeholder="" x-webkit-speech value="${searchDto.keyWords}" onkeyup="getAutoFill(event, '${autofillServer}');" onkeydown="selectBykeyEvent(event);" maxlength="20"/> 
							<a class="sub l" href="javascript:void(0);">帮5搜</a><div id="div_sub_btn_map"><a id="sub_btn_map" class="sub l btn-map" href="javascript:void(0);"><em class="ic-map"></em>地图模式</a></div>
							<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate] }" />
							<input type="hidden" id="hotelSelectedCityId" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }" />
							<input type="hidden" name="ajaxType" value="hotelSearchresult" />
							<input type="hidden" name="currPageNo" value="1" />
							<input  type="hidden" name="pageMode" id="page_mode_map" value="${searchDto.pageMode == null ? 'search' : searchDto.pageMode}"/>
						</form>
						<div class="hotel-tip">
								<div id="hotel_biz" class="hotel-cat">
                                    
                                </div>
                                <div id="hotel_bud" class="hotel-cat">
                                    
                                </div>
                                <div id="hotel_dist" class="hotel-cat">
                                 
                                </div>
                                <div id="hotel_metro" class="hotel-cat">
                                    
                                </div>
                                <div id="hotel_uns" class="hotel-cat">
                                    
                                </div>
                                <div id="hotel_hotel" class="hotel-cat">
                                   
                                </div>
                        </div>
						<div class="cityselect" id="J_jumb1">
							<div class="tit">
								<span>热门城市</span><a href="javascript:void(0)" class="close">×</a>
							</div>
							<ul class="cityselect-ul">
								<c:forEach var="c" items="${hotelCity }" varStatus="s">
									<li><a data-city="${c.key }" href="javascript:void(0)">${c.value
											}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<p class="p1">
						<span>热门搜索：</span> 
						<%-- <c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId==''||kw.guideId==null }">
								<a href="${kw.url }" target="_blank">${kw.keywords }</a>
							</c:if>
						</c:forEach> --%>
						<a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_如家快捷_1_search">如家快捷</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_布丁酒店_1_search">布丁酒店</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_桔子酒店_1_search">桔子酒店</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_汉庭_1_search">汉庭</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_公寓酒店_1_search">公寓酒店</a>
					</p>
					<p>
						<span></span>
						<%-- <c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId!=''&&kw.guideId!=null }">
								<a
									href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html"
									target="_blank" class="ico_hot">${kw.keywords }</a>
							</c:if>
						</c:forEach> --%>
					</p>
				</div>
				<!-- /酒店搜索 -->
				
				<!-- 度假搜索 -->
				<div class="filter-holidays">
					<div class="filter-tit">
						<span class="f-yh">度假搜索</span>
					</div>
					<div class="rows">
						<form id="trip_search_form" class="search-form" action="search.html" method="post">
							<label>我想找</label>
							<div class="ui-select local" data-local="true">
								<span class="l"><%-- ${searchDto.ipLocate } --%>${searchDto.selectedCityId == -1 ? '全部':dataCityKeyId[searchDto.selectedCityId]}</span><em
									class="icon-you r arr"></em><span class="r gray">出发</span>
							</div>
							<input type="text" id="trip_keyword_text" name="keywords" class="filter-text l ml10" placeholder="输入目的地、主题或关键词" x-webkit-speech autocomplete="off" value="${searchDto.keyWords }" onkeyup="getAutoFill(event, '${autofillServer}');" onkeydown="selectBykeyEvent(event);"  maxlength="20"/> 
							<a href="javascript:void(0);" class="sub l">帮5搜</a> 
							<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate] }" />
							<input type="hidden" id="tripSelectedCityId" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }" />
							<input type="hidden" name="ajaxType" value="searchresult" /> 
							<input type="hidden" name="currPageNo" value="1" />
						</form>
						<div class="cityselect">
							<div class="tit">
								<span>热门城市</span><a href="javascript:void(0)" class="close">×</a>
							</div>
							<ul class="cityselect-ul">
								<c:forEach var="c" items="${youCity }" varStatus="s">
									<li><a data-city="${c.key }" href="javascript:void(0)">${c.value
											}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<p class="p1">
						<span>热门搜索：</span> 
						<%-- <c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId==''||kw.guideId==null }">
								<a href="${kw.url }" target="_blank">${kw.keywords }</a>
							</c:if>
						</c:forEach> --%>
						<a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_厦门_1_search">厦门</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_桂林_1_search">桂林</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_海南_1_search">海南</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_泰国_1_search">泰国</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_普吉岛_1_search">普吉岛</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_北京_1_search">北京</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_searchresult_上海_1_search">上海</a>
					</p>
					<p>
						<span></span>
						<%-- <c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId!=''&&kw.guideId!=null }">
								<a
									href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html"
									target="_blank" class="ico_hot">${kw.keywords }</a>
							</c:if>
						</c:forEach> --%>
					</p>
				</div>
				<!-- /度假搜索 -->
				
				<!-- 攻略搜索 -->
				<div class="filter-gonglue">
					<div class="filter-tit">
						<span class="f-yh">攻略搜索</span>
					</div>
					<div class="rows">
						<form id="note_search_form" class="search-form" action="search.html" method="post">
							<label>我想找</label>
							<div class="ui-select local" data-local="true">
								<%-- <span class="l">${searchDto.ipLocate}攻略</span><em class="icon-you r arr gray"></em> --%>
								<span class="l">${searchDto.selectedCityId == -1 ? '全部':dataCityKeyId[searchDto.selectedCityId]}</span><em
									class="icon-you r arr"></em>
									<span class="r gray">攻略</span>
							</div>
							<input type="text" id="note_keyword_text" name="keywords" class="filter-text filter-text l ml10" placeholder="请输入城市、景点进行查询。如：伦敦、香港等" x-webkit-speech autocomplete="off" value="${searchDto.keyWords }" onkeyup="getAutoFill(event, '${autofillServer}');" onkeydown="selectBykeyEvent(event);" maxlength="20"/> 
							<a href="javascript:void(0);" class="sub l">帮5搜</a> 
							<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate]}" /> 
							<input type="hidden" id="selectedCityId" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }" />
							<input type="hidden" name="ajaxType" value="noteSearchresult" /> 
							<input type="hidden" name="currPageNo" value="1" />
						</form>
						<div class="cityselect">
							<div class="tit">
								<span>热门城市</span><a href="javascript:void(0)" class="close">×</a>
							</div>
							<ul class="cityselect-ul">
								<c:forEach var="c" items="${notesCity }" varStatus="s">
									<li><a data-city="${c.key }" href="javascript:void(0)">${c.value
											}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<p class="p1">
						<span>热门搜索：</span> 
						<c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId==''||kw.guideId==null }">
								<a href="${kw.url }" target="_blank">${kw.keywords }</a>
							</c:if>
						</c:forEach>
						<c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId!=''&&kw.guideId!=null }">
								<a
									href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html"
									target="_blank">${kw.keywords }</a>
							</c:if>
						</c:forEach>
					</p>
					
				</div>
				<!-- /攻略搜索 -->
			</div>
		</div>
	</div>
	<!-- /帮5游搜索 -->

	<%-- <c:if test="${curPageTag=='searchresult'}">
		<div class="w980 mt20">
			<div class="recommend">
				<div class="l">
					<strong class="tit"><em></em><span>主题推荐</span></strong>
					<p>
						<c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId!=''&&kw.guideId!=null }">
								<a
									href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html"
									target="_blank" class="ico_hot">${kw.keywords }</a>
							</c:if>
						</c:forEach>
					</p>
				</div>
				<div class="l local">
					<strong class="tit"><em></em><span>目的地推荐</span></strong>
					<p>
						<c:forEach items="${keyWords }" var="kw">
							<c:if test="${kw.guideId==''||kw.guideId==null }">
								<a href="${kw.url }" target="_blank">${kw.keywords }</a>
							</c:if>
						</c:forEach>
					</p>
				</div>
			</div>
		</div>
	</c:if> --%>
</c:if>

<c:if
	test="${curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'}">
	<div  style="text-align:center;margin-top:10px;margin-bottom:-10px">
		<script type="text/javascript">
		/*帮5游列表页980*70色块*/
		var cpro_id = "u1422686";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
	</div>
	<!-- 帮5游搜索 -->
	<div class="crumb clearfix w980">
		<div class="inner">
			<a href="${rootPath }">帮5游</a> <b>&gt;</b>
			<c:if test="${curPageTag=='domesticTravel' }">
				<span>国内游</span>
			</c:if>
			<c:if test="${curPageTag=='abroadTravel' }">
				<span>境外游</span>
			</c:if>
			<c:if test="${curPageTag=='peripheryTravel' }">
				<span>周边游</span>
			</c:if>
			<c:if test="${curPageTag=='hotel' }">
				<span>酒店</span>
			</c:if>
		</div>
	</div>
	<div class="w980 cf">
		<div class="rows filter-cont filter-second clearfix"
			id="J_secondFiletr">
			<form id="inner_search_form" class="search-form" action="search.html" method="post">
				<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate] }" /> 
				<%-- <input type="hidden" name="ajaxType" value="${curPageTag=='hotel'?'hotelSearchresult':'searchresult' }" /> --%>
				<c:if test="${curPageTag=='hotel'}">
					<input type="hidden" name="ajaxType" value="hotelSearchresult" />
				</c:if>
				<c:if test="${curPageTag!='hotel'}">
					<input type="hidden" name="ajaxType" value="${curPageTag}" />
				</c:if>
				
				<input type="hidden" name="currPageNo" value="1" />
				<div data-local="true"
					class="ui-select ${curPageTag=='hotel'?'local2':'local' }">
					<span class="l"><%-- ${searchDto.ipLocate } --%>${searchDto.selectedCityId == -1 ? '全部':dataCityKeyId[searchDto.selectedCityId]}</span><em
						class="icon-you r arr"></em>
					<c:if test="${curPageTag!='hotel' && curPageTag!='hotelSearchresult'}">
						<span class="r gray">出发</span>
					</c:if>
				</div>
				<!-- 内页搜索框 s -->
				<input type="text" id="keyword_inner_text" name="keywords" autocomplete="off" x-webkit-speech="" placeholder="请输入目的地、主题或关键词" class="filter-text l ml10 ${curPageTag=='hotel' ? 'filter-text-short':''} J-tip" value="${searchDto.keyWords }"  maxlength="20">
				<a class="sub l" href="javascript:void(0);">帮5搜</a>
				<c:if test="${curPageTag=='hotel' }">
					<div id="div_sub_btn_map"><a id="sub_btn_map" class="sub l btn-map" href="#"> <em class="ic-map"></em>地图模式</a></div>
					<div class="hotel-tip" id="div_hotel_tip">
									<div id="hotel_biz" class="hotel-cat">
	                                    
	                                </div>
	                                <div id="hotel_bud" class="hotel-cat">
	                                    
	                                </div>
	                                <div id="hotel_dist" class="hotel-cat">
	                                 
	                                </div>
	                                <div id="hotel_metro" class="hotel-cat">
	                                    
	                                </div>
	                                <div id="hotel_uns" class="hotel-cat">
	                                    
	                                </div>
	                                <div id="hotel_hotel" class="hotel-cat">
	                                   
	                                </div>
	                </div>
	                <input  type="hidden" name="pageMode" id="page_mode_map" value="list"/>
                </c:if>
				<input type="hidden" id="innerSelectedCityId" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }" />
				<p class="p1 ${curPageTag!='hotel'?'p2':'' }">
					<c:if test="${curPageTag!='peripheryTravel' }">
					<span>热门搜索：</span> 
					</c:if>
					<%-- <c:forEach items="${keyWords }" var="kw">
						<c:if test="${kw.guideId==''||kw.guideId==null }">
							<a href="${kw.url }" target="_blank">${kw.keywords }</a>
						</c:if>
					</c:forEach> --%>
					<c:if test="${curPageTag=='domesticTravel' }">
						<a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_厦门_1_search">厦门</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_桂林_1_search">桂林</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_海南_1_search">海南</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_青岛_1_search">青岛</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_张家界_1_search">张家界</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_北京_1_search">北京</a>
	                    <a href="http://you.b5m.com/taoPage_${searchDto.selectedCityId}_domesticTravel_西藏_1_search">西藏</a>
					</c:if>
					<c:if test="${curPageTag=='abroadTravel' }">
						<a href="http://you.b5m.com/taoPage_-1_abroadTravel_泰国_1_search">泰国</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_普吉岛_1_search">普吉岛</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_韩国_1_search">韩国</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_美国_1_search">美国</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_日本_1_search">日本</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_新加坡_1_search">新加坡</a>
	                    <a href="http://you.b5m.com/taoPage_-1_abroadTravel_长滩岛_1_search">长滩岛</a>
					</c:if>
					<c:if test="${curPageTag=='peripheryTravel' }">
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_北京_1_search">北京</a>
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_上海_1_search">上海</a>
						<a href="http://you.b5m.com/taoPage_-1_peripheryTravel_天津_1_search">天津</a>
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_重庆_1_search">重庆</a>
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_哈尔滨_1_search">哈尔滨</a>
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_青岛_1_search">青岛</a>
	                    <a href="http://you.b5m.com/taoPage_-1_peripheryTravel_西安_1_search">西安</a>
					</c:if>
					<c:if test="${curPageTag=='hotel' }">
						<a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_如家快捷_1_search">如家快捷</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_布丁酒店_1_search">布丁酒店</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_桔子酒店_1_search">桔子酒店</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_汉庭_1_search">汉庭</a>
			            <a href="http://you.b5m.com/taoPage_-1_hotelSearchresult_公寓酒店_1_search">公寓酒店</a>
					</c:if>
				</p>
				<!-- 内页搜索框 e -->
			</form>
			<div class="cityselect" id="J_jumb">
				<div class="tit">
					<span>热门城市：</span><a href="javascript:void(0)" class="close">×</a>
				</div>
				<ul class="cityselect-ul">
					<c:if test="${curPageTag=='hotel' }">
						<c:forEach var="c" items="${hotelCity }" varStatus="s">
							<li><a data-city="${c.key }" href="javascript:void(0)">${c.value
									}</a></li>
						</c:forEach>
					</c:if>
					<c:if test="${curPageTag!='hotel' }">
						<c:forEach var="c" items="${youCity }" varStatus="s">
							<li><a data-city="${c.key }" href="javascript:void(0)">${c.value
									}</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
	<!-- /帮5游搜索 -->
</c:if>
<!-- autofill 开始 -->
		<%-- <input autocomplete="off" type="hidden" id="ajaxCollection" value="tourp" />
		<input autocomplete="off" type="hidden" id="hdnCurIndex" />
		<input autocomplete="off" type="hidden" id="hdnKeyTemp" />
		<input autocomplete="off" type="hidden" id="hdnTarget" value="${rootPath }/taoPage_${searchDto.ipLocate}_hotelSearchresult_%23keyword%23_1_search " />
					
		<div id="downList">
			<div class="downList">
				<ul id="list">
					
				</ul>
			</div>
		</div> --%>
<!-- autofill 结束 -->