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
		<title>旅游攻略—帮5游</title>
		<meta name="keywords" content="旅游攻略">
		<meta name="description" content="帮5买旅游攻略频道为您提供最全的旅游攻略信息，最美旅游攻略图片，各种类型旅游攻略大全。">
	</c:when>
	<c:otherwise>
		<title>${searchDto.keyWords}攻略大全_${searchDto.keyWords}景点攻略_帮5游</title>
		<meta name="keywords" content="${searchDto.keyWords}攻略大全,${searchDto.keyWords}景点攻略,帮5游">
		<meta name="description" content="帮5游旅游网为您推荐${searchDto.keyWords}攻略大全，${searchDto.keyWords}景点攻略。让您用最实惠的旅游价格，享受到最不一般的旅程。">
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
		<!-- note list------------------------------s-->
		<div class="hotel-list-box fl guide-list-box">
			<c:forEach var="nl" items="${pageList.all}" varStatus="status">
				<dl>
					<dt>
						<a href="${rootPath}/detail_2_${nl.id}.html" target="_blank" title="${nl.name}">
							<c:choose>
								<c:when test="${fn:length(nl.name) >= 34}">
									${fn:substring(nl.name,0,33)}...
								</c:when>
								<c:otherwise>
									${nl.name}
								</c:otherwise>
							</c:choose>
						</a>
					</dt>
					<dd class="pic">
						<a href="${rootPath}/detail_2_${nl.id}.html" target="_blank" title="${nl.name}"><img width="188" height="118"
							src='${fn:trim(nl.b5mImg) == "" ? "../images/you/188x118.jpg" : nl.b5mImg}'  onerror="this.src='${rootPath }/images/you/188x118.jpg';"/></a>
					</dd>
					
					<dd class="descript">${nl.contentMini}</dd>
				</dl>
			</c:forEach>
			<!--page s-->
			<c:import url="../common/you_page_no_bar.jsp"></c:import>
			<!--page e-->
		</div>
		<!-- note list------------------------------e-->

		<!-- tuan list------------------------------s-->
		<div class="layout-side fr cfx">
			<div class="layout-side-list">
				<div class="layout-side-list-tit side-you-tuan">
					<a href="http://tuan.b5m.com/_lvyoujiudian-lvyou_" class="fr" target="_blank">更多></a>
					<h2>旅游团购</h2>
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
