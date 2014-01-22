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
<script type="text/javascript" src="${rootPath}/js/header.js?t=${today}"></script>
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

<!-- 引入公共头部 -->
<script src="http://cdn.bang5mai.com/upload/web/public/app/header/js/head.min.1.0.js"  data-cur='7' data-placeholder="快速搜索 特惠旅游" 
	data-logo-src="${rootPath}/images/tao/logo_tao_24.png,${rootPath}/images/tao/logo_tao_08.png" data-logo-link="${rootPath }" data-logo-alt="帮5游" data-city="${dataCityKey[searchDto.ipLocate] }"
	${curPageTag=='hotel'?'data-hotelKey="1"':'' }>
<div class="sub_nav" id="J_sub_nav">
    <div class="w980 clear_fix">
        <ul class="fl lava">
            <li>
                <a ${curPageTag=="index"?"class=\"sub_cur\"":" "} href="${rootPath}/city/${dataCityKey[searchDto.ipLocate]==null||dataCityKey[searchDto.ipLocate]==''?'':dataCityKey[searchDto.ipLocate] }">帮5游首页</a>
        		<a ${curPageTag=="domesticTravel"?"class=\"sub_cur\"":" "} href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_domesticTravel">国内游</a> 
        		<a ${curPageTag=="abroadTravel"?"class=\"sub_cur\"":" "} href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_abroadTravel">境外游</a> 
        		<a ${curPageTag=="peripheryTravel"?"class=\"sub_cur\"":" "} href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_peripheryTravel">周边游</a>
        		<a ${curPageTag=="hotel"||curPageTag=="hotelSearchresult"?"class=\"sub_cur\"":" "} href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_hotel">酒店</a>
            </li>
        </ul>
    </div>
</div>	
</script>
<!-- 引入公共头部 -->

<!--banner s-->
<c:if test="${curPageTag!=null && menuCurPageTag!=null}">
	<c:if test="${curPageTag!='index'}">
		<!-- city s-->
		<div class="layout clear-fix city-layout  txt-r" style="position: relative;z-index:1">
			<div class="city">
				<div class="current-city-wrap">
					<div class="cityname-wrap">
						<span class="light">&nbsp;</span> 
						<span class="city-name">${searchDto.ipLocate } 
						<c:if test="${curPageTag!='hotel'&&curPageTag!='hotelSearchresult'}">
								<span>出发</span>
							</c:if>
						</span>
					</div>
					<div class="arrow-wrap">
						<span class="arrow">&nbsp;</span>
					</div>
				</div>
				<c:if test="${fn:length(city) > 0 }">
					<div class="city-outer-wrap">
						<c:forEach var="c" items="${city }" varStatus="s">
							<a href="javascript:void(0);" data-key="${c.key }">${c.value }</a>
							<c:if test="${s.count%6==0 }">
								<br/>
							</c:if>
						</c:forEach>
					</div>
				</c:if>
			</div>
			<span class="words wordsNew"> <c:forEach items="${keyWords }" var="kw">
					<c:if test="${kw.guideId!=''&&kw.guideId!=null }">
						<a href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html" target="_blank" class="ico_hot">${kw.keywords }<s></s></a>
					</c:if>
					<c:if test="${kw.guideId==''||kw.guideId==null }">
						<a href="${kw.url }" target="_blank">${kw.keywords }</a>
					</c:if>
				</c:forEach>
			</span>			
		</div>
		<!-- city e-->
	</c:if>
	<%-- <div class="layout">
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
	</div> --%>
</c:if>
<!--banner e-->
