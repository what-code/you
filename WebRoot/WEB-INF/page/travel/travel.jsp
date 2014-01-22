<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<c:choose>
	<c:when test="${fromType == 'list' && searchDto.priceStart == '0' && searchDto.priceEnd == '0' && searchDto.daysMax == '0' && searchDto.daysMini == '0' && searchDto.ajaxType == 'all'}">
		<title>${searchDto.selectedCity}出发旅游线路推荐-帮5游</title>
		<meta name="keywords" content="${searchDto.selectedCity}出发旅游线路">
		<meta name="description" content="帮5买为你推荐当季${searchDto.selectedCity}出发旅游经典线路。">
	</c:when>
	<c:when test="${fn:trim(searchDto.keyWords) == '' && (searchDto.priceEnd != '0' ||  searchDto.daysMini != '0' || searchDto.daysMax != '0' || searchDto.ajaxType != 'all')}">
		<title>${searchDto.selectedCity}${tripType[searchDto.ajaxType]}${searchDto.daysMax == '0' ? '' : searchDto.daysMax}${searchDto.daysMax == '0' ? '' : '日'}${searchDto.daysMini == '7' ? '7日以上' : ''}${searchDto.priceEnd == '0' ? '' : searchDto.priceEnd}${searchDto.priceEnd == '0' ? '' : '元'}${searchDto.priceStart == '3000' ? '3000元以上' : ''}旅游-帮5游</title>
		<meta name="keywords" content="${searchDto.selectedCity}${tripType[searchDto.ajaxType]}${searchDto.daysMax == '0' ? '' : searchDto.daysMax}${searchDto.daysMax == '0' ? '' : '日'}${searchDto.daysMini == '7' ? '7日以上' : ''}${searchDto.priceEnd == '0' ? '' : searchDto.priceEnd}${searchDto.priceEnd == '0' ? '' : '元'}${searchDto.priceStart == '3000' ? '3000元以上' : ''}旅游">
		<meta name="description" content="帮5买为你推荐当季${searchDto.selectedCity}出发旅游经典线路。">
	</c:when>
	<c:otherwise>
		<title>${searchDto.selectedCity}到${searchDto.keyWords}${searchDto.daysMax == '0' ? '' : searchDto.daysMax}${searchDto.daysMax == '0' ? '' : '日'}${searchDto.daysMini == '7' ? '7日以上' : ''}${searchDto.priceEnd == '0' ? '' : searchDto.priceEnd}${searchDto.priceEnd == '0' ? '' : '元'}${searchDto.priceStart == '3000' ? '3000元以上' : ''}-帮5游</title>
		<meta name="keywords" content="${searchDto.selectedCity}到${searchDto.keyWords}${searchDto.daysMax == '0' ? '' : searchDto.daysMax}${searchDto.daysMax == '0' ? '' : '日'}${searchDto.daysMini == '7' ? '7日以上' : ''}${searchDto.priceEnd == '0' ? '' : searchDto.priceEnd}${searchDto.priceEnd == '0' ? '' : '元'}${searchDto.priceStart == '3000' ? '3000元以上' : ''}旅游">
		<meta name="description" content="帮5游旅游网为您推荐${searchDto.keyWords}相关旅游景点、旅游线路、游记攻略等。让您用最实惠的旅游价格，享受到最不一般的旅程。">
	</c:otherwise>
</c:choose>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:import url="../common/youCssHeader.jsp"></c:import>
</head>
<body>
	<!--头部 s-->
	<c:import url="../common/youHeader.jsp"></c:import>
	<!--头部 e-->
	
	<div class="wp layout-add mt20" style="display: none;">
    	<a class="pic" href="" target="_blank"><img src="http://pic.junli.cc/980x35"/></a>
   	</div>
   	<div class="wp mt20 cfx">
    	<div class="you-list-box fl">
           	<c:forEach var="t" items="${pageList.all}" varStatus="status">
	        	<dl>
	            	<dt>
	            		<a href="${rootPath }/detail_0_${t.id }.html" target="_blank" title="${t.name}">
		            		<c:choose>
								<c:when test="${fn:length(t.name) > 36}">
									${fn:substring(t.name, 0, 35)}...
								</c:when>
								<c:otherwise>
									${t.name}
								</c:otherwise>
							</c:choose>
	            		</a>
	            	</dt>
	               	<dd class="pic">
	                	<a href="${rootPath }/detail_0_${t.id }.html" target="_blank"><img src="${t.imgurl }" onerror="this.src='${rootPath }/images/you/120x76.jpg';"/></a>
	               	</dd>
	               	<dd class="descript" title="${t.listRecommendation}">
	               		<c:choose>
							<c:when test="${fn:length(t.listRecommendation) > 36}">
								${fn:substring(t.listRecommendation, 0, 35)}...
							</c:when>
							<c:otherwise>
								${t.listRecommendation}
							</c:otherwise>
						</c:choose>
					</dd>
					<c:if test="${t.groupDate!='' }">
		               	<dd class="info"><span>最近出发班期：<b>
		               	<c:choose>
		               		<c:when test="${fn:length(t.groupDate) > 20}">${fn:substring(t.groupDate,0,20) }...</c:when>
		               		<c:otherwise>${t.groupDate }</c:otherwise>
		               	</c:choose>
		               	</b></span></dd>
					</c:if>
	               	<dd class="price">
	                   <p><s class="ico-rmb">¥</s><b>${t.salesPrice }</b>起</p>
	               </dd>
	        	</dl>
           	</c:forEach>
           	
			<!--分页 s-->
			<c:import url="travel_page_bar.jsp"></c:import>
			<!--分页 e-->
			
       </div>
       <div class="layout-side fr cfx">
             <div class="layout-side-list">
                 <div class="layout-side-list-tit side-you-tuan">
                     <a href="http://tuan.b5m.com/_lvyoujiudian-lvyou_" class="fr" target="_blank">更多></a><h2>旅游团购</h2>
                 </div>
               	 <c:forEach var="tt" items="${tuanTravel}" varStatus="status">
	               	 <dl class="you-side-list">
	                     <dt><a href="${tt.url }" target="_blank">${tt.source }</a></dt>
	                     <dd class="pic">
	                         <a href="${tt.url }" target="_blank"><img src="${tt.img }" onerror="this.src='${rootPath }/images/you/120x76.jpg';"/></a>
	                         <a href="${tt.url }" target="_blank" class="tit">${tt.title }</a>
	                     </dd>
	                     <dd class="price"><p class="ifr"><del><s class="ico-rmb">¥</s>${tt.price }</del><b>${tt.discount }</b>折</p><s class="ico-rmb">¥</s> <em>${tt.salesPrice }</em></dd>
	                     <dd class="sell">
	                         <a href="${tt.url }" target="_blank" class="blue fr">详情></a>已销售<ins>${tt.sold }</ins>
	                     </dd>
	                 </dl>
               	 </c:forEach>
             </div>
       </div>
   </div>
   
	<!--footer s-->
	<c:import url="../common/youFoot.jsp"></c:import>
	<!--footer e-->
</body>
</html>