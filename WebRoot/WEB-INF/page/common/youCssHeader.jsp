<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/css/common/common.css?t=${today}">

<c:choose>
	<c:when test="${fromType == 'detail' || fromType == 'noResult'}">
		<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/css/you/you-v2.css?t=${today}">
	</c:when>
	<c:otherwise>
		<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/css/you/you-v3.css?t=${today}">
	</c:otherwise>
</c:choose>
<%-- <link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/static/public/jquery-ui/jquery-ui.css?t=${today}"> --%>
<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/css/you/jquery.ui.datepicker.css?t=${today}">
