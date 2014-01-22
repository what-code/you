<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商圈-地标-帮5游-旅游特惠网</title>
<meta name="keywords" content="旅游网,旅游,帮5游,特惠,国内游,出境游,周边游,酒店预订,帮5买,B5M">
<meta name="description"
	content="帮5游旅游网汇聚全部旅游网特惠信息，帮您精选最优质的旅游团购、特惠产品，包含国内游、出境游、酒店预定等各种服务。帮5买旗下网站：帮5游旅游网。">
</head>
<body>

	<!--头部 s-->
	<c:import url="common/youHeader.jsp"></c:import>
	<!--头部 e-->
	<%-- ------------>${curPageTag} --%>
	<div class="w980 col-search mt20">
	   <div class="col-sub">
	       <ul class="sub-ad">
	       	   <c:forEach var="notes" items="${searchNotes }" varStatus="s">
	       	   		<c:if test="${s.count<=2 }">
		       	   		<li>
			               <a href="${rootPath }/notesDetail_${dataCityKey[searchDto.ipLocate] }_${notes.id }.html" target="_blank" class="pic"><img src="${notes.b5mImg }" style="width:50px;height:50px;" alt="${notes.name }"/></a>
			               <p>
			                   <a href="${rootPath }/notesDetail_${dataCityKey[searchDto.ipLocate] }_${notes.id }.html" target="_blank" class="tit">${notes.name }</a>
			                   <span>${notes.contentMini}</span>
			               </p>
			           	</li>
	       	   		</c:if>
	       	   		<c:if test="${s.count>2&&s.count<=4 }">
		       	   		<li>
			               <p>
			                   <a href="${rootPath }/notesDetail_${dataCityKey[searchDto.ipLocate] }_${notes.id }.html" target="_blank" class="tit">${notes.name }</a>
			                   <span>${notes.contentMini }</span>
			               </p>
			            </li>
	       	   		</c:if>
	       	   </c:forEach>
	       </ul>
	       <ul class="sub-news">
	       	   <c:forEach var="notes" items="${searchNotes }" varStatus="s">
	       	   		<c:if test="${s.count>4 }">
	       	   			<li><em></em><a href="${rootPath }/notesDetail_${dataCityKey[searchDto.ipLocate] }_${notes.id }.html" target="_blank">${notes.name }</a></li>
	       	   		</c:if>
	       	   </c:forEach>
	       </ul>
	   </div>
	   
	   <div class="col-main you-addr">
	   			<c:if test="${fn:length(hk2.le0) > 0}">
	                <span class="addr-tit">商区</span>
	                <c:forEach var="ht" items="${hk2.le0}" varStatus="s">
		                <dl>
		                    <dt>${ht.item1}</dt>
		                    <dd>
			                    <c:forEach var="ht1" items="${ht.arr}" varStatus="s1">
			                    	<%-- <a href="${rootPath }/taoPage_${searchDto.selectedCityId}_hotelSearchresult_${ht1}_1_map">${ht1}</a> --%>
			                    	<a href="${rootPath }/getHotelsForMap.html?selectedCityId=${searchDto.selectedCityId}&keywords=${ht1}">${ht1}</a>
			                    	<c:if test="${s1.index < (fn:length(ht.arr)-1)}">
			                    		<span>|</span>
			                    	</c:if>
			                    </c:forEach>
		                    </dd>
		                </dl>
	                </c:forEach>
                </c:if>
                
                <c:if test="${fn:length(hk3.le0) > 0}">
	                <span class="addr-tit">地标</span>
	                <c:forEach var="ht" items="${hk3.le0}" varStatus="s">
		                <dl>
		                    <dt>${ht.item1}</dt>
		                    <dd>
			                    <c:forEach var="ht1" items="${ht.arr}" varStatus="s1">
			                    	<%-- <a href="${rootPath }/taoPage_${searchDto.selectedCityId}_hotelSearchresult_${ht1}_1_map">${ht1}</a> --%>
			                    	<a href="${rootPath }/getHotelsForMap.html?selectedCityId=${searchDto.selectedCityId}&keywords=${ht1}">${ht1}</a>
			                    	<c:if test="${s1.index < (fn:length(ht.arr)-1)}">
			                    		<span>|</span>
			                    	</c:if>
			                    </c:forEach>
		                    </dd>
		                </dl>
	                </c:forEach>
                </c:if>
                <%-- <span class="addr-tit">地铁</span>
                <c:forEach var="ht" items="${hk4.le0}" varStatus="s">
	                <dl>
	                    <dt>${ht.item1}</dt>
	                    <dd>
		                    <c:forEach var="ht1" items="${ht.arr}" varStatus="s1">
		                    	<a href="#">${ht1}</a>
		                    	<c:if test="${s1.index < (fn:length(ht.arr)-1)}">
		                    		<span>|</span>
		                    	</c:if>
		                    </c:forEach>
	                    </dd>
	                </dl>
                </c:forEach> --%>
                
                <c:if test="${fn:length(hk5.le0) > 0}">
	                <span class="addr-tit">大学</span>
	                <c:forEach var="ht" items="${hk5.le0}" varStatus="s">
		                <dl>
		                    <dt>${ht.item1}</dt>
		                    <dd>
			                    <c:forEach var="ht1" items="${ht.arr}" varStatus="s1">
			                    	<%-- <a href="${rootPath }/taoPage_${searchDto.selectedCityId}_hotelSearchresult_${ht1}_1_map">${ht1}</a> --%>
			                    	<a href="${rootPath }/getHotelsForMap.html?selectedCityId=${searchDto.selectedCityId}&keywords=${ht1}">${ht1}</a>
			                    	<c:if test="${s1.index < (fn:length(ht.arr)-1)}">
			                    		<span>|</span>
			                    	</c:if>
			                    </c:forEach>
		                    </dd>
		                </dl>
	                </c:forEach>
                </c:if>
            </div>
		<!--page s-->
		<c:import url="common/tao_page_no_bar.jsp"></c:import>
		<!--page e-->
	</div>
	
	<input  type="hidden" name="page_mode" id="page_mode" value="search"/>
	<!--footer s-->
	<c:import url="common/youFoot.jsp"></c:import>
	<!--footer e-->
</body>
</html>