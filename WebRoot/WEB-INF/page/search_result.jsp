<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>B5M帮5游-国内、国外旅游攻略、价格、旅游团购一网打尽</title>
<meta name="keywords" content="B5M帮5游-国内,国外旅游攻略,价格一网打尽" />
<meta name="description"
	content="B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游." />
<meta name="robots" content="index, follow" />
<meta name="googlebot" content="index, follow" />
<link rel="shortcut icon" href="${rootPath}/favicon.ico?t=${today}"
	type="image/x-ion" />
</head>

<body>

	<!--头部 s-->
	<c:import url="common/youHeader.jsp"></c:import>
	<!--头部 e-->
	<h2>search result:${youDto.selectedCity}</h2><br>
	<c:forEach var="ht" items="${pageList.all}" varStatus="s">
		${ht.name}<br>
	</c:forEach>
	totalpage-->${pageList.totalPages}
	<!--footer s-->
	<c:import url="common/youFoot.jsp"></c:import>
	<!--footer e-->
</body>
</html>