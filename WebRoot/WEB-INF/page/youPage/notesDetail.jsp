<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="../common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${detail.name }_帮5游</title>
<meta name="keywords" content="${detail.name }">
<meta name="description" content="${searchDto.selectedCity }旅游攻略：${fn:substring(detail.contentMini, 0, 19)}">
</head>
<body>

	<!--头部 s-->
	<c:import url="../common/youHeader4Detail.jsp"></c:import>
	<!--头部 e-->

	<div class="crumb clearfix w980">
        <div class="inner">
            <a href="${rootPath }">帮5游</a> <b>&gt;</b>
            <a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html">攻略</a> <b>&gt;</b>
            <span>
            		<c:choose>
							<c:when test="${fn:length(detail.name) > 33}">
								${fn:substring(detail.name, 0, 32)}
							</c:when>
							<c:otherwise>
								${detail.name }
							</c:otherwise>
					</c:choose>
            </span>
        </div>
    </div>

    <div class="w980 mt20 clearfix">
        <div class="col-sub col-sub-news">
            <div class="collect">
                <a href="javascript:void(0)" id="J_addFav01" class="icon-you" rel="fav" target="_self">收藏</a>
                <a href="javascript:((function(){var title=encodeURIComponent('${detail.name }-B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游、酒店价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游。@帮5买，地址：')+window.location.href;var link=encodeURIComponent('${rootPath}');window.open('http://v.t.sina.com.cn/share/share.php?appkey=3222906970&title='+title+'&url='+link);})())"
					class="icon-you share" title="分享">分享</a>
            </div>
            <h4 class="tit">
                <span class="l">热门景点</span>
                <a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html" class="more" target="_blank">更多&gt;</a>
            </h4>
            <ul class="sub-ad">
                <c:forEach var="notes" items="${searchNotes }" varStatus="s">
	       	   		<c:if test="${s.count<=4 }">
		       	   		<li>
			               <a href="${rootPath}/detail_2_${notes.id }.html" class="pic" target="_blank"><img src="${notes.b5mImg }" alt="${notes.name }" style="width:50px;height:50px;"/></a>
			               <p>
			                   <a href="${rootPath}/detail_2_${notes.id }.html" class="tit" target="_blank">${notes.name }</a>
			                   <span>${notes.contentMini }</span>
			               </p>
			           	</li>
	       	   		</c:if>
	       	   		<c:if test="${s.count>4&&s.count<=8 }">
		       	   		<li>
			               <p>
			                   <a href="${rootPath}/detail_2_${notes.id }.html" class="tit" target="_blank">${notes.name }</a>
			                   <span>${notes.contentMini }</span>
			               </p>
			            </li>
	       	   		</c:if>
	       	   </c:forEach>
            </ul>

            <h4 class="tit tit-hotal" ${fn:length(searchHotel) > 0 ? "":"style='display:none'"}>
                <span class="l">热门酒店</span>
                <a href="${rootPath}/list_1_${searchDto.selectedCityId}_1.html" class="more" target="_blank">更多&gt;</a>
            </h4>
            <ul class="col-sub-hotal" ${fn:length(searchHotel) > 0 ? "":"style='display:none'"}>
           		<c:forEach var="hotel" items="${searchHotel }" varStatus="s">
	                <li>
	                    <span class="tit"><a href="${rootPath}/detail_1_${hotel.id }.html" rel="nofollow" class="l" target="_blank">${hotel.name }</a><em class="r">&yen;${hotel.salesPrice }</em></span>
	                    <a href="${rootPath}/detail_1_${hotel.id }.html" class="l" rel="nofollow" target="_blank"><img src="${hotel.imgurl }" alt="${hotel.name }"  width="50" height="50"/></a>
	                    <p>
	                    	<c:choose>
								<c:when test="${fn:length(hotel.buildings) > 27}">
									推荐理由：${fn:substring(hotel.buildings, 0, 26)}...
								</c:when>
								<c:otherwise>
									推荐理由：${hotel.buildings }
								</c:otherwise>
							</c:choose>
	                    </p>
	                </li>
                </c:forEach>
            </ul>
        </div>
        
        <div class="col-main">
            <div class="news-detail cf">
	        	<div class="news-detail-tit">
	                <h3 class="l tit">
	                 <c:choose>
							<c:when test="${fn:length(detail.name) > 33}">
								${fn:substring(detail.name, 0, 32)}...
							</c:when>
							<c:otherwise>
								${detail.name }
							</c:otherwise>
					</c:choose>
	                </h3>
	                <a href="javascript:void(0)" class="up r" rel="fav" target="_self">${detail.totalClick }<em class="icon-you">顶</em></a>
	            </div>
	            <div class="news-toolbar clearfix">
	                <span class="author">${detail.author }</span>
	                <span class="time l">${detail.publishTime }</span>
	            </div>
	            <div class="text">
	                ${detail.content }
	            </div>
            </div>
        </div>
    </div>
	
	<!--footer s-->
	<c:import url="../common/youFoot.jsp"></c:import>
	<!--footer e-->

</body>
</html>