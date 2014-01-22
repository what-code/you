<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>机票预订查询_帮5游</title>
<meta name="keywords" content="携程网机票预订,机票查询,机票代理,帮5游">
<meta name="description" content="帮5游旅游网机票查询，代理携程网机票预订。一折机票随时有，打折机票惊爆你的腰包。">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:import url="../common/youCssHeader.jsp"></c:import>

</head>
<body>
	<c:import url="../common/youHeader.jsp"></c:import>

	<div id="piaoBox" class="w980 mt20 clear-fix">
		<!-- <iframe
			src='http://union.tieyou.com/gw.php?c=niumowang&s=3711&p=269&r=http%3A%2F%2Fwww.tieyou.com%2Fcode%2Fiframe%2Findex.php%3Fstyle%3Dblue1'
			width='980' height='810' frameborder='0'></iframe> -->
		<iframe id="J_ticket" width="980px" height="810" src="http://e.ctrip.com/flight/eflightonline/searchflights.aspx?allianceid=14235&sid=402576&ouid=000401app" frameborder="0" allowtransparency="true"></iframe>
	</div>

	<c:import url="../common/youFoot.jsp"></c:import>
</body>
</html>
