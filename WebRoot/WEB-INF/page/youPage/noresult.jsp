<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="../common/youCssHeader.jsp"></c:import>
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
	<c:import url="../common/youHeader4Detail.jsp"></c:import>
	<!--头部 e-->
		<div class="search-nothing w980 mt20">
            <h2 class="tip">亲，木有搜到结果</h2>
            <p class="back">
            	<c:choose>
            		<c:when test="${fn:trim(noResultKeyword) != ''}">
            			糟糕，没有找到 "<em>${noResultKeyword}</em>" 相关的产品<br/> <br/> <a href="${rootPath }">去首页看看»</a>
            		</c:when>
            		<c:otherwise>
            			糟糕，没有找到相关的产品<br/> <br/> <a href="${rootPath }">去首页看看»</a>
            		</c:otherwise>
            	</c:choose>
            </p>
        </div>
		
        <div class="w980 col-search mt20 search-empty">
            <div class="search-tit-empty">
                您可能想找：
            </div>
            <c:choose>
            	<c:when test="${pageTag != '2'}">
		            <div class="second-mod clearfix">
		                <div class="module-bd module-bd-search clearfix mar">
		                    <!-- loop -->
		                    <c:forEach var="gl" items="${pageList.all}" varStatus="status">
								<div class="mod" data-id="${gl.id }" data-url="${gl.sourceUrl }"
									data-price="${gl.salesPrice }"
									data-spread="${gl.spread>0?status.count:gl.spread }"
									data-t-source="${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}"
									data-hover="mod-hover">
									<div class="tit">
										<a href="${rootPath}/detail_${searchDto.searchType}_${gl.id}.html" target="_blank">${gl.name}</a>
									</div>
									<div class="pic">
										<a href="${rootPath}/detail_${searchDto.searchType}_${gl.id}.html" target="_blank"><img width="188" height="118" src="${gl.imgurl}" /></a>
										<div class="original">
												已有<strong>${gl.totalClick }</strong>人抢购
										</div>
									</div>
									<div class="opt">
										<span>&yen;<em>${gl.salesPrice }</em>起
										</span>
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
									</div>
								</div>
							</c:forEach>
		                    <!-- /loop -->
		                </div>
		            </div>
            	</c:when>
            	<c:otherwise>
            		 <div class="second-mod clearfix">
			            <ul class="news-ul">
			            	<c:forEach var="gl" items="${pageList.all}" varStatus="status">
			                 <li>
			                     <a class="l" href="${rootPath}/detail_${searchDto.searchType}_${gl.id}.html" target="_blank"><img src="${gl.b5mImg }" alt="${gl.name }" width="216" height="216" /></a>
			                     <p>
			                         <a href="${rootPath}/detail_${searchDto.searchType}_${gl.id}.html" class="tit" target="_blank">
			                         	<c:choose>
												<c:when test="${fn:length(gl.name) > 20}">
													${fn:substring(gl.name, 0, 19)}...
												</c:when>
												<c:otherwise>
													${gl.name }
												</c:otherwise>
										</c:choose>
			                         </a>
			
			                         <span class="intro">
			                             <span class="news-author">${gl.author }</span>&nbsp;&nbsp;<em class="news-time">${gl.publishTime }</em>
			                         </span>
			                         <span class="news-synopsis">
			                             ${gl.contentMini } <a href="${rootPath}/detail_${searchDto.searchType}_${gl.id}.html" target="_blank">》更多内容</a>
			                         </span>
			                         <%-- <span class="contrl">
			                             <a href="#" class="view icon-you">${gl.totalClick }</a><a href="#" class="commit icon-you">${gl.totalClick }</a>
			                         </span> --%>
			                         <a href="#" class="up">${gl.totalClick }<em class="icon-you">顶</em></a>
			                     </p>
			                 </li>
			                </c:forEach>
			            </ul>
			        </div>
            	</c:otherwise>
            </c:choose>
        </div>

	<!--footer s-->
	<c:import url="../common/youFoot.jsp"></c:import>
	<!--footer e-->

</body>
</html>