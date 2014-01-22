<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站地图-帮5游-旅游特惠网-sitemap</title>
<meta name="keywords" content="旅游网,旅游,帮5游,特惠,国内游,出境游,周边游,酒店预订,帮5买,B5M">
<meta name="description"
	content="帮5游旅游网汇聚全部旅游网特惠信息，帮您精选最优质的旅游团购、特惠产品，包含国内游、出境游、酒店预定等各种服务。帮5买旗下网站：帮5游旅游网。">
<link rel="stylesheet" type="text/css" href="${rootPath}/css/sitemap.css">
</head>
<body>

	<!--头部 s-->
	<c:import url="common/taoHeader.jsp"></c:import>
	<!--头部 e-->
	<div class="sitemap-main w980 mt20">
    <div class="location">
        <a href="${rootPath}">首页</a>
        >
        <span class="blue">网站地图</span>
    </div>
    <div class="tab-h">
        <a  ${siteMapType == "0" ? "class=\"curr\"" : ""} href="${rootPath}/sitemap/0/${dataCityKey[searchDto.ipLocate]}.html">
            国内旅游 
            <em class="arrow"></em>
        </a>
        <a ${siteMapType == "1" ? "class=\"curr\"" : ""} href="${rootPath}/sitemap/1/${dataCityKey[searchDto.ipLocate]}.html">
            出境旅游
            <em class="arrow"></em>
        </a>
        <a ${siteMapType == "2" ? "class=\"curr\"" : ""} href="${rootPath}/sitemap/2/${dataCityKey[searchDto.ipLocate]}.html">
            周边旅游
            <em class="arrow"></em>
        </a>
        <a ${siteMapType == "3" ? "class=\"curr\"" : ""} href="${rootPath}/sitemap/3/${dataCityKey[searchDto.ipLocate]}.html">
            酒店品牌
            <em class="arrow"></em>
        </a>

    </div>
    <dl>
        <dt>
            <span class="blue">◆</span>
            ${searchDto.ipLocate}<c:if test="${siteMapType == '0' || siteMapType == '1'}">出发</c:if><c:if test="${siteMapType == '2'}">周边</c:if><c:if test="${siteMapType == '3'}">酒店</c:if>
        </dt>
        <dd>
            <div class="list-con">
            	<c:forEach var="c" items="${siteList}" varStatus="s">
                	<c:if test="${siteMapType == '0'}">
                		<a href="${rootPath}/pageDetailed_-1_domesticTravel_${c[0]}.html">${c[1]}</a>
                	</c:if>
                	<c:if test="${siteMapType == '1'}">
                		<a href="${rootPath}/pageDetailed_-1_abroadTrave_${c[0]}.html">${c[1]}</a>
                	</c:if>
                	<c:if test="${siteMapType == '2'}">
                		<a href="${rootPath}/pageDetailed_-1_peripheryTravel_${c[0]}.html">${c[1]}</a>
                	</c:if>
                	<c:if test="${siteMapType == '3'}">
                		<a href="${rootPath}/pageDetailed_-1_hotel_${c[0]}.html">${c[1]}</a>
                	</c:if>
             	</c:forEach>
                <!-- <a class="more" href="">查看更多 ></a> -->

            </div>
        </dd>
    </dl>
    
    <dl>
        <dt> <span class="blue">◆</span>
            其它城市</dt>
        <dd>
            <div class="list-con">
            <c:choose>
				<c:when test="${siteMapType == '3'}">
		            <c:forEach var="c" items="${hotelCity}" varStatus="s">
		            	<a href="${rootPath}/sitemap/${siteMapType}/${c.key}.html">${c.value }<c:if test="${siteMapType == '0' || siteMapType == '1'}">出发</c:if><c:if test="${siteMapType == '2'}">周边</c:if><c:if test="${siteMapType == '3'}">酒店</c:if></a>
		            </c:forEach>
	            </c:when>
	            <c:otherwise>
	            	<c:forEach var="c" items="${youCity }" varStatus="s">
		            	<a href="${rootPath}/sitemap/${siteMapType}/${c.key}.html">${c.value }<c:if test="${siteMapType == '0' || siteMapType == '1'}">出发</c:if><c:if test="${siteMapType == '2'}">周边</c:if><c:if test="${siteMapType == '3'}">酒店</c:if></a>
		            </c:forEach>
	            </c:otherwise>
            </c:choose>
            </div>

        </dd>
    </dl>
    
    <dl>
        <dt> <span class="blue">◆</span>
            热门城市</dt>
        <dd>
            <div class="list-con">
            	<c:choose>
					<c:when test="${siteMapType == '3'}">
			            <c:forEach var="c" items="${hotelCity}" varStatus="s">
			            	<a href="${rootPath}/taoPage_${c.key}_hotel">${c.value }</a>
			            </c:forEach>
			        </c:when>
		            <c:otherwise>
		            	 <c:forEach var="c" items="${youCity}" varStatus="s">
			            	<c:if test="${siteMapType == '0'}"><a href="${rootPath}/taoPage_${c.key}_domesticTravel">${c.value }</a></c:if>
			            	<c:if test="${siteMapType == '1'}"><a href="${rootPath}/taoPage_${c.key}_abroadTravel">${c.value }</a></c:if>
			            	<c:if test="${siteMapType == '2'}"><a href="${rootPath}/taoPage_${c.key}_peripheryTravel">${c.value }</a></c:if>
			            </c:forEach>
		            </c:otherwise>
            	</c:choose>
            </div>
        </dd>
    </dl>
</div>
	<!--footer s-->
	<c:import url="common/taoFoot.jsp"></c:import>
	<!--footer e-->
<%-- <script type="text/javascript" src="${rootPath }/js/siteMap.js?t=${today}"></script> --%>
</body>
</html>