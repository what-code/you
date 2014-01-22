<%@page import="com.b5m.tao.model.YouGoods"%>
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
<c:import url="common/youCssHeader.jsp"></c:import>
<link rel="shortcut icon" href="${rootPath}/favicon.ico?t=${today}"
	type="image/x-ion" />
<c:if test="${curPageTag=='domesticTravel' }">
	<title>${dataCityKeyId[searchDto.selectedCityId] }驴友网_${dataCityKeyId[searchDto.selectedCityId] }驴友出游特惠_${dataCityKeyId[searchDto.selectedCityId] }驴友出行景点推荐-帮5游</title>
	<meta name="keywords" content="${dataCityKeyId[searchDto.selectedCityId] }旅游网,${dataCityKeyId[searchDto.selectedCityId] }驴友,景点推荐,驴友,特惠,旅游,帮5游,帮5买,B5M">
	<meta name="description"
		content="帮5游${dataCityKeyId[searchDto.selectedCityId] }驴友网是帮5游的${dataCityKeyId[searchDto.selectedCityId] }驴友出行频道，帮助您推荐${dataCityKeyId[searchDto.selectedCityId] }驴友出游常去的旅游地点，汇聚${dataCityKeyId[searchDto.selectedCityId] }出行旅游特惠信息。">
</c:if>
<c:if test="${curPageTag=='abroadTravel' }">
	<title>${dataCityKeyId[searchDto.selectedCityId] }驴友网_${dataCityKeyId[searchDto.selectedCityId] }驴友国外出游特惠_${dataCityKeyId[searchDto.selectedCityId] }驴友境外游景点推荐-帮5游</title>
	<meta name="keywords" content="${dataCityKeyId[searchDto.selectedCityId] }旅游网,${dataCityKeyId[searchDto.selectedCityId] }驴友,国外出游,国外景点推荐,境外游,驴友,特惠,旅游,帮5游,帮5买,B5M">
	<meta name="description"
		content="帮5游${dataCityKeyId[searchDto.selectedCityId] }驴友网之${dataCityKeyId[searchDto.selectedCityId] }驴友国外出游频道，帮助您推荐${dataCityKeyId[searchDto.selectedCityId] }驴友境外游常去的旅游地点，汇聚上海境外游特惠信息。">
</c:if>
<c:if test="${curPageTag=='peripheryTravel' }">
	<title>${dataCityKeyId[searchDto.selectedCityId] }周边旅游_${dataCityKeyId[searchDto.selectedCityId] }周边旅游推荐_${dataCityKeyId[searchDto.selectedCityId] }周边旅游景点推荐_${dataCityKeyId[searchDto.selectedCityId] }周边旅游攻略-帮5游</title>
	<meta name="keywords" content="${dataCityKeyId[searchDto.selectedCityId] }周边旅游,周边旅游,${dataCityKeyId[searchDto.selectedCityId] }周边旅游推荐,${dataCityKeyId[searchDto.selectedCityId] }周边旅游景点推荐,景点推荐,${dataCityKeyId[searchDto.selectedCityId] }周边旅游攻略,旅游网,旅游,帮5游,帮5买,B5M">
	<meta name="description"
		content="帮5游${dataCityKeyId[searchDto.selectedCityId] }周边旅游频道汇聚${dataCityKeyId[searchDto.selectedCityId] }周边旅游景点推荐，${dataCityKeyId[searchDto.selectedCityId] }周边旅游攻略，${dataCityKeyId[searchDto.selectedCityId] }周边旅游景点大全及特惠信息等。精选优质的旅游特惠助您畅享${dataCityKeyId[searchDto.selectedCityId] }周边旅游。">
</c:if>
<c:if test="${curPageTag=='hotel' }">
	<title>${dataCityKeyId[searchDto.selectedCityId] }酒店_${dataCityKeyId[searchDto.selectedCityId] }酒店预订_${dataCityKeyId[searchDto.selectedCityId] }宾馆预订_${dataCityKeyId[searchDto.selectedCityId] }酒店特惠-帮5游</title>
	<meta name="keywords" content="${dataCityKeyId[searchDto.selectedCityId] }酒店,${dataCityKeyId[searchDto.selectedCityId] }酒店预订,${dataCityKeyId[searchDto.selectedCityId] }宾馆预订,${dataCityKeyId[searchDto.selectedCityId] }酒店特惠,${dataCityKeyId[searchDto.selectedCityId] }周边旅游攻略,旅游网,旅游,帮5游,帮5买,B5M">
	<meta name="description"
		content="帮5游${dataCityKeyId[searchDto.selectedCityId] }酒店频道为您提供${dataCityKeyId[searchDto.selectedCityId] }酒店预订，${dataCityKeyId[searchDto.selectedCityId] }酒店查询，${dataCityKeyId[searchDto.selectedCityId] }宾馆预订及${dataCityKeyId[searchDto.selectedCityId] }酒店特惠信息等服务。助您畅游${dataCityKeyId[searchDto.selectedCityId] }的同时住优质实惠的${dataCityKeyId[searchDto.selectedCityId] }酒店。">
</c:if>
<c:if test="${curPageTag=='hotelSearchresult' }">
	<title>B5M帮5游-携程网、艺龙网酒店预订，上海、北京、香港快捷酒店预订一网打尽</title>
	<meta name="keywords" content="B5M帮5游-携程网、艺龙网酒店预订，上海、北京、香港快捷酒店预订一网打尽">
	<meta name="description"
		content="B5M特价酒店为您提供包括洲际、万豪、雅高、喜达屋等国际知名酒店品牌，以及如家、锦江之星、格林豪泰等经济型商务连锁酒店在线预订和电话预订服务。各大知名酒店预订网迅速直达，方便快捷">
</c:if>
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow">
</head>
<body>
	<!--头部 s-->
	<c:import url="common/taoHeader.jsp"></c:import>
	<%-- -------->${curPageTag} --%>
	<!--头部 e-->
	<!--wrap s-->
	<div id="wrap" class="layout">
		<c:if test="${curPageTag == 'searchresult' }">
			<!-- 帮5游的header 页面 结束-->
			<input type="hidden" id="hdnSearchKeyword"
				value="${searchDto.keyWord}" />
			<!--result-num s-->
			<div class="search-result">搜索”${searchDto.keyWords}“,搜索结果如下：</div>
		</c:if>
		<div class="w980 col-search mt20">
            <div class="seaond-mod clearfix">
                <div class="module-bd module-bd-search clearfix mar">
                    <!-- loop -->
					<c:forEach var="gl" items="${pageList.all}" varStatus="status">
						<div class="mod" data-id="${gl.id }" data-url="${gl.sourceUrl }"
							data-price="${gl.salesPrice }"
							data-spread="${gl.spread>0?status.count:gl.spread }"
							data-t-source="${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}"
							data-hover="mod-hover">
							
							<div class="tit">
			                     <c:if test="${curPageTag != 'searchresult' }">
									<c:choose>
										<c:when test="${gl.youTuan == '0'}">
											<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
											${gl.name }</a>
										</c:when>
										<c:otherwise>
											<a href="${gl.sourceUrl}" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】${gl.name }</a>
										</c:otherwise>
									</c:choose>
								</c:if>
								<c:if test="${curPageTag == 'searchresult' }">
									<%-- <c:choose>
										<c:when
											test="${user_session_login == 'true' && user_session_login != null }">
											<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
												${gl.name }</a>
										</c:when>
										<c:otherwise>
											<a class="tankuang" href="javascript:void(0);" rel="nofollow" target="_blank" clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
												${gl.name }</a>
										</c:otherwise>
									</c:choose> --%>
									
									<c:choose>
										<c:when test="${gl.youTuan != '1'}">
											<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html"  target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
												${gl.name }</a>
										</c:when>
										<c:otherwise>
											<a href="${gl.sourceUrl}" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】${gl.name }</a>
										</c:otherwise>
									</c:choose>
								</c:if>
			                 </div>
							<div class="pic">
								<%-- <c:choose>
									<c:when
										test="${user_session_login == 'true' && user_session_login != null }">
										<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" rel="nofollow" target="_blank"><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></a>
									</c:when>
									<c:otherwise>
										<a class="tankuang" href="javascript:void(0);" target="_blank"
											clickUrl="${gl.clickUrl}" rel="nofollow" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html"><span><img  src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></span></a>
									</c:otherwise>
								</c:choose> --%>
								
								<c:choose>
									<c:when
										test="${gl.youTuan != '1'}">
										<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html"  target="_blank"><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></a>
									</c:when>
									<c:otherwise>
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank"><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></a>
									</c:otherwise>
								</c:choose>
								<div class="original">
										已有<strong>${gl.totalClick }</strong>人抢购
								</div>
							</div>
							<div class="opt">
								<span><c:if test="${gl.salesPrice > 0}">&yen;<em>${gl.salesPrice }</em>起</c:if><c:if test="${gl.salesPrice <= 0}"><em>暂无报价</em></c:if></span>
								<%-- <c:choose>
									<c:when
										test="${user_session_login == 'true' && user_session_login != null }">
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
									</c:when>
									<c:otherwise>
										<a class="tankuang btn" href="javascript:void(0);" rel="nofollow"
											target="_blank" clickUrl="${gl.clickUrl}"
											sourceUrl="${gl.sourceUrl}">去抢购</a>
									</c:otherwise>
								</c:choose> --%>
								
								<c:choose>
									<c:when
										test="${gl.youTuan != '1'}">
										<a href="${gl.sourceUrl}"  target="_blank" class="btn">去抢购</a>
									</c:when>
									<c:otherwise>
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<!--page s-->
		<c:import url="common/tao_page_no_bar.jsp"></c:import>
		<!--page e-->
		<c:if
			test="${curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'}">
			<div  style="text-align:center;margin-top:-18px;margin-bottom:10px">
				<script type="text/javascript">
				/*帮5游列表页960*90图片*/
				var cpro_id = "u1422687";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			</div>
		</c:if>
	</div>
	<!--wrap e-->
	<input  type="hidden" name="page_mode" id="page_mode" value="list"/>
	<input type="hidden" id="ajaxType" name="ajaxType01" value="${curPageTag}"/>
	<!--footer s-->
	<c:import url="common/taoFoot.jsp"></c:import>
	<!-- auto fill js s -->
		<%-- <script type="text/javascript" src="${rootPath}/js/list_dl.js?t=${today}"></script> --%>
	<!-- auto fill js e -->
	<!--footer e-->
</body>
</html>
