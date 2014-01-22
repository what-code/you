<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="common/youCssHeader.jsp"></c:import>
<title>【${guide.name }旅游攻略】${guide.name }旅游_${guide.name }酒店预订_${guide.name }旅游景点大全_帮5游</title>
<meta name="keywords" content="${guide.name }旅游,${guide.name }旅游路线推荐,${guide.name }旅游报价,去${guide.name }旅游要多少钱"/>
<meta name="description" content="帮5买${guide.name }旅游攻略为您推荐去${guide.name }风光旅游看什么、玩什么、住哪里；最全的线路，最美的景点，最好玩的地方，最实惠的酒店住宿。"/>
<link rel="stylesheet" type="text/css" href="http://staticcdn.b5m.com/static/css/you/load.css?t=${today}">
</head>

<body class="body">

	<!--头部 s-->
	<c:import url="common/youHeader.jsp"></c:import>
	<!--头部 e-->

	<div class="layout">
		<div id="crumb">
			<div class="inner">
				<a href="${rootPath }">帮5游</a> <b>&gt;</b>
				<c:if test="${guide.youType==0 }">
					<a
						href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_domesticTravel">国内游</a>
				</c:if>
				<c:if test="${guide.youType==1 }">
					<a
						href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_abroadTravel">境外游</a>
				</c:if>
				<c:if test="${guide.youType==2 }">
					<a
						href="${rootPath}/taoPage_${dataCityKey[searchDto.ipLocate] }_peripheryTravel">周边游</a>
				</c:if>
				<b>&gt;</b> <span>${guide.city }旅游</span>
			</div>
			<div class="keyWord">
				<c:forEach items="${keyWords }" var="kw">
					<c:choose>
						<c:when test="${kw.guideId!=''&&kw.guideId!=null&&(guide.name!=kw.keywords&&guide.id!=kw.guideId )}">
							<a href="${rootPath}/youGuideDetailed_${dataCityKey[searchDto.ipLocate] }_${kw.guideId }.html" target="_blank" class="ico_hot">${kw.keywords }<s></s></a>
						</c:when>
						<c:when test="${(kw.guideId==''||kw.guideId==null)&&(guide.name!=kw.keywords&&guide.id!=kw.guideId ) }">
							<a href="${kw.url }" target="_blank">${kw.keywords }</a>
						</c:when>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--wrap s-->
	<div class="layout">
		<h3 class="load-h3">
			<span>${guide.name }</span>
		</h3>
		<%-- <div id="load-slider" class="load-slider">
			<div class="slideCont">
				<c:forEach var="gl" items="${fn:split(guide.b5mImgUrls, '#')}">
					<a href="${fn:split(gl, '@')[1]}" class="slider-item" target="_blank"><img src="${fn:split(gl, '@')[0]}" /></a>
				</c:forEach>
			</div>
			<ul class="slideNav">
				<c:forEach var="gl" items="${fn:split(guide.b5mImgUrls, '#')}">
					<li><a href="${fn:split(gl, '@')[1]}" target="_blank"><img src="${fn:split(gl, '@')[0]}" /></a></li>
				</c:forEach>
			</ul>
		</div> --%>
		<div id="load-slider2" class="load-slider2">
			<ul class="tab-cont">
				<li>
					<p class="pic">
						<c:forEach var="gl" items="${fn:split(guide.lookImgUrls, '#')}" varStatus="s">
							<c:if test="${s.count==2}">
								<span>
							</c:if>
							<a href="${fn:split(gl, '@')[2]}" target="_blank"><img src="${fn:split(gl, '@')[1]}" class="${s.count==1?'w1':s.count>3?'w3':'' }"/><s>${fn:split(gl, '@')[0]}</s></a>
							<c:if test="${s.count==3}">
								</span>
							</c:if>
						</c:forEach>
					</p>
					<p class="intro">
						<span class="tit"><strong>${fn:split(guide.lookContent, '@')[0] }</strong><a href="${fn:split(guide.lookContent, '@')[1] }" target="_blank" class="moreIcon">更多&gt;</a></span>
						<a class="txt" href="${fn:split((fn:split(guide.lookImgUrls, '#')[0]), '@')[2]}">
							&nbsp;&nbsp;&nbsp;&nbsp;${fn:split(guide.lookContent, '@')[2] }
						</a>
					</p>
				</li>
				<li style="display:none">
					<p class="pic">
						<c:forEach var="gl" items="${fn:split(guide.playImgUrls, '#')}" varStatus="s">
							<c:if test="${s.count==2}">
								<span>
							</c:if>
							<a href="${fn:split(gl, '@')[2]}" target="_blank"><img src="${fn:split(gl, '@')[1]}" class="${s.count==1?'w1':s.count>3?'w3':'' }"/><s>${fn:split(gl, '@')[0]}</s></a>
							<c:if test="${s.count==3}">
								</span>
							</c:if>
						</c:forEach>
					</p>
					<p class="intro">
						<span class="tit"><strong>${fn:split(guide.playContent, '@')[0] }</strong><a href="${fn:split(guide.playContent, '@')[1] }" target="_blank" class="moreIcon">更多&gt;</a></span>
						<a class="txt" href="${fn:split((fn:split(guide.playImgUrls, '#')[0]), '@')[2]}">
							&nbsp;&nbsp;&nbsp;&nbsp;${fn:split(guide.playContent, '@')[2] }
						</a>
					</p>
				</li>
				<li style="display:none">
					<p class="pic">
						<c:forEach var="gl" items="${fn:split(guide.liveImgUrls, '#')}" varStatus="s">
							<c:if test="${s.count==2}">
								<span>
							</c:if>
							<a href="${fn:split(gl, '@')[2]}" target="_blank"><img src="${fn:split(gl, '@')[1]}" class="${s.count==1?'w1':s.count>3?'w3':'' }"/><s>${fn:split(gl, '@')[0]}</s></a>
							<c:if test="${s.count==3}">
								</span>
							</c:if>
						</c:forEach>
					</p>
					<p class="intro">
						<span class="tit"><strong>${fn:split(guide.liveContent, '@')[0] }</strong><a href="${fn:split(guide.liveContent, '@')[1] }" target="_blank" class="moreIcon">更多&gt;</a></span>
						<a class="txt" href="${fn:split((fn:split(guide.liveImgUrls, '#')[0]), '@')[2]}">
							&nbsp;&nbsp;&nbsp;&nbsp;${fn:split(guide.liveContent, '@')[2] }
						</a>
					</p>
				</li>
			</ul>
			<div class="tab">
				<span class="cur load-slider2-tab1"> <strong>看</strong><em>什么</em>
				</span> <span class="load-slider2-tab2"> <strong>玩</strong><em>什么</em>
				</span> <span class="load-slider2-tab3"> <strong>住</strong><em>什么</em>
				</span>
			</div>
		</div>
		<div class="load-mod">
			<div class="mod-l">
				<h4>
					<strong>${guide.city }游记攻略</strong> <a href="${rootPath}/taoPage_-1_noteSearchresult_${guide.city }_1_hotNotes_search" class="more" target="_blank">更多&gt;</a>
				</h4>
				<ul class="load-news clear-fix">
					<c:forEach var="nl" items="${notesList}">
						<li>
							<a href="${nl.url }" class="pic fl" target="_blank"><img src="${nl.b5mImg }" alt="${nl.name }"/></a>
							<p>
								<a class="tit" href="${nl.url }" target="_blank">
									<c:choose>
										<c:when test="${fn:length(nl.name)>20}">
											${fn:substring(nl.name,0,20) } ···
										</c:when>
										<c:otherwise>
											${nl.name }
										</c:otherwise>
									</c:choose>
								</a> 
								<span class="info">作者：${nl.author }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;时间：${nl.publishTime }</span>
								<span class="intro">
									<c:choose>
										<c:when test="${fn:length(nl.contentMini)>150}">
											${fn:substring(nl.contentMini,0,150) } ···
										</c:when>
										<c:otherwise>
											${nl.contentMini }
										</c:otherwise>
									</c:choose>
								</span>
								<em class="num">浏览<strong>${nl.totalClick }</strong></em>
							</p>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="mod-r load-travel">
				<h4>
					<c:if test="${guide.youType==0 }">
						<strong>国内热门游</strong> <a href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel" class="more" target="_blank">更多&gt;</a>
					</c:if>
					<c:if test="${guide.youType==1 }">
						<strong>出国热门游</strong> <a href="${rootPath}/taoPage_${searchDto.selectedCityId}_abroadTravel" class="more" target="_blank">更多&gt;</a>
					</c:if>
					<c:if test="${guide.youType==2 }">
						<strong>周边热门游</strong> <a href="${rootPath}/taoPage_${searchDto.selectedCityId}_peripheryTravel" class="more" target="_blank">更多&gt;</a>
					</c:if>
				</h4>
				<c:forEach var="gl" items="${youList}" varStatus="status">
					<div class="load-travel-bx" data-id="${gl.id }" data-url="${gl.sourceUrl }"
						data-t-source="${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}">
						<c:choose>
							<c:when
								test="${user_session_login == 'true' && user_session_login != null }">
								<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${currPageTag}_${gl.id }.html" rel="nofollow" class="tit" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】
									${gl.name }</a>
							</c:when>
							<c:otherwise>
								<a class="tankuang tit" href="javascript:void(0);" rel="nofollow" target="_blank" clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${currPageTag}_${gl.id }.html">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】
									${gl.name }</a>
							</c:otherwise>
						</c:choose>
						<div class="pic">
							<c:choose>
								<c:when
									test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${currPageTag}_${gl.id }.html" rel="nofollow" class="pic" target="_blank"><img src="${gl.imgurl }" alt="${gl.name }"></a>
								</c:when>
								<c:otherwise>
									<a class="tankuang pic" href="javascript:void(0);" rel="nofollow" target="_blank"
										clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${currPageTag}_${gl.id }.html"><img src="${gl.imgurl }" alt="${gl.name }"></a>
								</c:otherwise>
							</c:choose>
							<div class="original">
								<p>
									<span>已有<ins>${gl.totalClick }</ins>人抢购</span>
								</p>
							</div>
						</div>
						<div class="price">
							<span>&yen;<em>${gl.salesPrice }</em>起
							</span>
							<c:choose>
								<c:when
									test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${gl.sourceUrl}" rel="nofollow" class="load-sDetail"target="_blank" class="btn">去抢购</a>
								</c:when>
								<c:otherwise>
									<a class="tankuang load-sDetail" href="javascript:void(0);" rel="nofollow"
										target="_blank" clickUrl="${gl.clickUrl}"
										sourceUrl="${gl.sourceUrl}">去抢购</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="mod-l mod-l-recHotels">
				<h4>
					<strong>${guide.city }住宿推荐</strong> <a href="${rootPath}/taoPage_${dataCityKey[guide.city] }_hotel" class="more" target="_blank">更多&gt;</a>
				</h4>
				<c:forEach var="gl" items="${circleList}" varStatus="status">
					<dl>
						<dt>
							<span>${gl.name }</span>
						</dt>
						<dd class="load-recHotels-dDescript">${gl.introduction }</dd>
						<dd class="load-recHotels-box">
							<c:forEach var="hl" items="${gl.hotelList }" varStatus="status">
								<div class="load-recHotels-bx">
									<c:choose>
										<c:when
											test="${user_session_login == 'true' && user_session_login != null }">
											<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${hl.id }.html" rel="nofollow" target="_blank"> <img src="${hl.imgurl }" alt="${hl.name }"/>
												<p><b>
												<c:choose>
													<c:when test="${fn:length(hl.name) > 9}">
														${fn:substring(hl.name, 0, 8)}...
													</c:when>
													<c:otherwise>
														${hl.name }
													</c:otherwise>
												</c:choose>
												</b><ins>${hl.totalClick }</ins>人已赞<span><s>¥</s><del>${hl.salesPrice}</del>起</span></p>
											</a>
										</c:when>
										<c:otherwise>
											<a class="tankuang" href="javascript:void(0);" rel="nofollow" target="_blank" clickUrl="${hl.clickUrl}" sourceUrl="${hl.sourceUrl}">
												<img src="${hl.imgurl }" alt="${hl.name }"/>
												<p><b>
												<c:choose>
													<c:when test="${fn:length(hl.name) > 9}">
														${fn:substring(hl.name, 0, 8)}...
													</c:when>
													<c:otherwise>
														${hl.name }
													</c:otherwise>
												</c:choose>
												</b><ins>${hl.totalClick }</ins>人已赞<span><s>¥</s><del>${hl.salesPrice }</del>起</span></p>
											</a>
										</c:otherwise>
									</c:choose>
									<div class="load-recHotels-descript">
										<c:choose>
											<c:when
												test="${user_session_login == 'true' && user_session_login != null }">
												<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${hl.id }.html" rel="nofollow" class="tit" target="_blank">${hl.name }</a> 
											</c:when>
											<c:otherwise>
												<a class="tankuang tit" href="javascript:void(0);" rel="nofollow"
													target="_blank" clickUrl="${hl.clickUrl}"
													sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${hl.id }.html">${hl.name }</a>
											</c:otherwise>
										</c:choose>
										位于：${gl.name }
										<p class="content">推荐理由：
												<c:choose>
													<c:when test="${fn:length(hl.recommendation) > 61}">
														${fn:substring(hl.recommendation, 0, 60)}...
													</c:when>
													<c:otherwise>
														${hl.recommendation }
													</c:otherwise>
												</c:choose>
										</p>
										<p class="link">
											<span><s>¥</s> <del>${hl.salesPrice }</del>起</span>
											<c:choose>
												<c:when
													test="${user_session_login == 'true' && user_session_login != null }">
													<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${hl.id }.html" rel="nofollow" target="_blank">查看详情></a>
												</c:when>
												<c:otherwise>
													<a class="tankuang btn" href="javascript:void(0);" rel="nofollow"
														target="_blank" clickUrl="${hl.clickUrl}"
														sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${hl.id }.html">查看详情></a>
												</c:otherwise>
											</c:choose>
										</p>
									</div>
								</div>
							</c:forEach>
						</dd>
					</dl>
				</c:forEach>
			</div>
		</div>
	</div>
	<!--wrap e-->

	<!--footer s-->
	<c:import url="common/youFoot.jsp"></c:import>
	<!--footer e-->
	<script type="text/javascript" src="${rootPath}/js/spc_tao.js?t=${today}"></script>
	<script>
		$(function() {
			spaTaoFed.flashAd();
			spaTaoFed.slideInfo();
			spaTaoFed.tabShow('load-slider2');
			spaTaoFed.rollSlide('load-slider');
			spaTaoFed.seePlayLiveHover();
		});
	</script>
</body>
</html>