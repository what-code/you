<%@page import="com.b5m.you.model.YouGoods"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:choose>
	<c:when test="${fromType == 'list'}">
		<title>${searchDto.selectedCity}酒店查询_${searchDto.selectedCity}酒店预订-帮5游</title>
		<meta name="keywords" content="${searchDto.selectedCity}酒店查询_${searchDto.selectedCity}酒店预订">
		<meta name="description" content="帮5游酒店频道为您提供${searchDto.selectedCity}酒店预订，${searchDto.selectedCity}酒店查询，${searchDto.selectedCity}酒店预订及酒店特惠信息等服务。">
	</c:when>
	<c:otherwise>
		<title>${searchDto.selectedCity}${searchDto.keyWords}${searchDto.hotelBeginDate}入住${searchDto.hotelStar == '0' ? '' : searchDto.hotelStar}${searchDto.hotelStar == '0' ? '' : '星'}酒店查询</title>
		<meta name="keywords" content="${searchDto.selectedCity}${searchDto.keyWords}${searchDto.hotelBeginDate}入住${searchDto.hotelStar == '0' ? '' : searchDto.hotelStar}${searchDto.hotelStar == '0' ? '' : '星'}酒店查询">
		<meta name="description" content="帮5游旅游网为您推荐搜索词酒店大全，${searchDto.keyWords}酒店预订等。让您用最实惠的旅游价格，享受到最不一般的旅程。">
	</c:otherwise>
</c:choose>
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow">
<c:import url="../common/youCssHeader.jsp"></c:import>
<link rel="shortcut icon" href="${rootPath}/favicon.ico?t=${today}" type="image/x-ion" />
</head>

<body>
	<!--头部 s-->
	<c:import url="../common/youHeader.jsp"></c:import>
	<!--头部 e-->
	
	<!--预留广告位 ------------------------------s-->
	<!-- <div class="wp layout-add mt20">
		<a class="pic" href="" target="_blank"><img
			src="http://pic.junli.cc/980x35" /></a>
	</div> -->
	<!--预留广告位------------------------------e-->

	<!-- 正文部分------------------------------s-->
	<div class="wp mt20 cfx">
		<!-- hotel list------------------------------s-->
		<div class="hotel-list-box fl">
			<c:forEach var="ht" items="${pageList.all}" varStatus="status">
				<dl>
					<dt>
						<a href="${rootPath}/detail_1_${ht.id}.html" target="_blank" title="${ht.name}">
							<c:choose>
								<c:when test="${fn:length(ht.name) >= 34}">
									${fn:substring(ht.name,0,33)}...
								</c:when>
								<c:otherwise>
									${ht.name}
								</c:otherwise>
							</c:choose>
						</a>
					</dt>
					<dd class="pic">
						<a href="${rootPath}/detail_1_${ht.id}.html" target="_blank" title="${ht.name}"><img width="188" height="118" 
						src='${fn:trim(ht.imgurl) == "" ? "../images/you/188x118.jpg" : ht.imgurl}' onerror="this.src='${rootPath }/images/you/188x118.jpg';"/></a>
					</dd>
					
					<dd class="descript">${ht.brandInfo}</dd>
					<dd class="price">
						<p>
						<c:if test="${ht.salesPrice > 0}">
							<s class="ico-rmb">¥</s><b>${ht.salesPrice}</b>起
						</c:if>
						<c:if test="${ht.salesPrice <= 0}">
							<s class="ico-rmb"></s><b>暂无报价</b>
						</c:if>
						</p>
					</dd>
				</dl>
			</c:forEach>
			
			<!--page s-->
			<c:import url="../common/you_page_no_bar.jsp"></c:import>
			<!--page e-->
		</div>
		<!-- hotel list------------------------------e-->

		<!-- tuan list------------------------------s-->
		<div class="layout-side fr cfx">
			<div class="layout-side-list">
				<div class="layout-side-list-tit side-you-tuan">
					<a href="http://tuan.b5m.com/_lvyoujiudian-jiudian_" class="fr" target="_blank">更多></a>
					<h2>酒店团购</h2>
				</div>
				<c:forEach var="tuan" items="${tuanList}" varStatus="status">
					<dl class="you-side-list">
						<dt>
							<a href="${tuan.sourceUrl}" target="_blank">${tuan.source}</a>
						</dt>
						<dd class="pic">
							<a href="${tuan.url}" target="_blank"><img
								src="${tuan.img}" /></a> <a href="${tuan.url}"
								target="_blank" class="tit">${tuan.title}</a>
						</dd>
						<dd class="price">
							<p class="ifr">
								<del>
									<s class="ico-rmb">¥</s>${tuan.price}
								</del>
								<b>${tuan.discount}</b>折
							</p>
							<s class="ico-rmb">¥</s> <em>${tuan.salesPrice}</em>
						</dd>
						<dd class="sell">
							<a href="${tuan.url}" target="_blank" class="blue fr">详情></a>已销售
							<ins>${tuan.sold}</ins>
						</dd>
					</dl>
				</c:forEach>
			</div>
		</div>
		<!-- tuan list------------------------------e-->
	</div>
	<!-- 正文部分------------------------------e-->
	
	<!--footer s-->
	<c:import url="../common/youFoot.jsp"></c:import>
	<!--footer e-->
</body>
</html>
