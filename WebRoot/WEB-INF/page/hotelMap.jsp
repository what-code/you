<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商圈-地标-帮5游-旅游特惠网</title>
<meta name="keywords" content="旅游网,旅游,帮5游,特惠,国内游,出境游,周边游,酒店预订,帮5买,B5M">
<meta name="description"
	content="帮5游旅游网汇聚全部旅游网特惠信息，帮您精选最优质的旅游团购、特惠产品，包含国内游、出境游、酒店预定等各种服务。帮5买旗下网站：帮5游旅游网。">
<input type="hidden" id="rootPath" name="rootPath" value="${rootPath }" />
<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/static/css/you/you-map.css">
</head>

<body>

	<!--头部 s-->
	<%-- <c:import url="common/youHeader.jsp"></c:import> --%>
	<!--头部 e-->
	<div class="header">
		<div class="wraper cf">
			<h1 class="logo l"><a href="${rootPath }"><img src="http://staticcdn.b5m.com/static/images/you/logo.png"  alt="帮5游"></a></h1>
			<div class="search-box l mt20">
				<form action="getHotelsForMap.html" method="post" class="search-box__form" id="hotel_search_form">
					<div class="l select-list">
						<span id="J-checked-city" class="checked-city">${mapCity == -1 ? '全部':dataCityKeyId[mapCity]}</span><i class="icon icon-arrow"></i>
						<div class="cityselect" id="cityselect_hot_div">
	                        <div class="tit">
	                        	<span>热门城市</span><em>请选择城市名称</em><a target="_self" href="javascript:void(0)" class="close">×</a>
	                        </div>
	                        <ul class="cityselect-ul cf" >
	                        	<c:forEach var="c" items="${hotelCity }" varStatus="s">
									<li><a data-city="${c.key }" href="javascript:void(0)">${c.value}</a></li>
								</c:forEach>
	                        </ul>
                    	</div>
					</div>
					<div class="l select-area" >
						<span class="search-box__input"><input class="search-box__keys" type="text" name="keywords" id="hotel_keyword_text"></span>
						<ul class="search-box__tab list-h">
							<!-- <li class="cur">搜索</li>
							<li>搜索</li>
							<li>搜索</li> -->
						</ul>
						<div class="hotel-tip">
                            <div id="hotel_biz" class="hotel-cat"></div>
                            <div id="hotel_bud" class="hotel-cat"></div>
                            <div id="hotel_dist" class="hotel-cat"></div>
                            <div id="hotel_metro" class="hotel-cat" style="display: none;"></div>
                            <div id="hotel_uns" class="hotel-cat"></div>
                            <div id="hotel_hotel" class="hotel-cat" style="display:none"></div>
                    	</div>
					</div>
					<div class="search-box__btn l"><input type="button" id="map_search_btn"></div>
					<input type="hidden" id="map_keyword"  value="${mapKeyword}">
					<input type="hidden" id="map_mode" name="" value="${mapMode}">
					<input type="hidden" id="map_city" name="selectedCityId" value="${mapCity}">
				</form>
			</div>
		</div>
	</div>

	
	<div class="map cf">
		<div class="map__sider l">
			<!-- <div class="map__sider__top">
				<ul class="rim-area list-h l">
					<li class="cur">周边酒店</li>
					<li>周边景点</li>
				</ul>
			</div> -->
			<div class="map__sider__body">
				<!-- <ul class="check-list list-h cf">
					<li><i class="icon icon-checked"></i><span>全部</span></li>
					<li><i class="icon icon-checked"></i><span>三星以下</span></li>
					<li><i class="icon icon-check"></i><span>三星</span></li>
					<li><i class="icon icon-check"></i><span>四星</span></li>
					<li><i class="icon icon-check"></i><span>五星</span></li>
				</ul> -->
				<div id="div_tips_warning" style="display:none">
					<p class="tips tips-warning">没有搜索到“${mapKeyword}”相关的酒店，为您推荐如下：</p>
				</div>
				<div id="div_addr_list_noresult" style="display:none">
					<ul class="addr-list" id="J_addr_list_noresult">
						<c:forEach var="hl" items="${hotelList}" varStatus="s">
							<li class="addr-list__item">
								<div>
									<a href="${rootPath}/pageDetailed_-1_hotel_${hl.id}.html" class="pic"><img src="${hl.imgurl}" width="50" height="50" alt=""></a>
									<div class="txt" id="div_noresult_txt">
										<c:if test="${fn:length(hl.name) > 12}">
											<a href="${rootPath}/pageDetailed_-1_hotel_${hl.id}.html" class="cl-000">${fn:substring(hl.name,0,12)}...</a>
										</c:if>
										<c:if test="${fn:length(hl.name) <= 12}">
											<a href="${rootPath}/pageDetailed_-1_hotel_${hl.id}.html" class="cl-000">${hl.name}</a>
										</c:if>
										<p>${dataHotelsChn[hl.levelInfo]}</p>
										<c:if test="${fn:length(hl.address) >= 11}">
											<p>位于：<a href="${rootPath}/pageDetailed_-1_hotel_${hl.id}.html" class="cl-438">${fn:substring(hl.address,0,10)}...</a></p>
										</c:if>
										
										<c:if test="${fn:length(hl.address) < 11}">
											<p>位于：<a href="${rootPath}/pageDetailed_-1_hotel_${hl.id}.html" class="cl-438">${(hl.address == null || hl.address == 'null' || hl.address == '') ? '未知' : hl.address}</a></p>
										</c:if>
										<input type="hidden" value="${hl.latitude}#${hl.longitude}"/>
									</div>
								</div>
								<i class="icon icon-marker">${s.count}</i>
								<div class="price">&yen;${hl.salesPrice < 1 ? '查看详情':hl.salesPrice}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div id="div_addr_list_result">
				<ul class="addr-list" id="J_addr_list">
					
				</ul>
				</div>
				<div class="page mt20" id="page_mt20">
					<!-- <a href="#">上一页</a>
					<a href="#" class="cur">1</a>
					<a href="#">2</a>
					<a href="#">3</a>
					<a href="#">下一页</a> -->
				</div>
			</div>
		</div>
		<div class="map__main" id="map-container">
			<span>正在加载。。。</span>
		</div>
	</div>

	
	<!-- <div class="detail cf">
		<img src="http://placehold.it/50x50" class="l" alt="" width="50" height="50">
		<div class="txt">
			<span>浦东南路</span><strong class="price">&yen;888</strong>
			<p>地址：<span class="cl-438">浦东南路1118号</a></p>
			<p>星级：<span class="cl-666">四星级/高档</span></p>
		</div>
	</div> -->
	<!--footer s-->
	<%-- <c:import url="common/youFoot.jsp"></c:import> --%>
	<!--footer e-->
	
	<input type="hidden" id="page_total_no" value="1">
	<input type="hidden" id="page_curr_no" value="1">
	<input type="hidden" id="curMapTag" value="hotelmap">
	<input type="hidden" id="defaultKeyWord" value="${defaultKeyWord}">
	<input type="hidden" id="page_mode_map" value="${searchDto.pageMode}">
	<input type="hidden" id="defaultJson" value='${defaultJson}'>
	<input type="hidden" id="cityCenterPoints" value="${cityCenterPoints}">
	
	<script src="http://staticcdn.b5m.com/static/scripts/common/jquery-1.9.1.min.js?t=${today}"></script>
	<script src="http://api.map.baidu.com/api?v=1.4"></script>
	<script type="text/javascript" src="${rootPath }/js/you_dev.js?t=${today}"></script>
	<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/you/you-map.js?t=${today}"></script>
	<%-- <script src="${rootPath }/js/you-map.js?t=${today}"></script> --%>
	<script type="text/javascript" src="${rootPath }/js/you_map_dev.js?t=${today}"></script>
	<script>
		/* try{
			//head.js("${rootPath }/js/jquery-1.9.1.min.js","http://staticcdn.b5m.com/static/scripts/you/you-map.js",initHotelMapData);
		}catch(e){}
		initHotelMapData(); */
	</script>
</body>
</html>