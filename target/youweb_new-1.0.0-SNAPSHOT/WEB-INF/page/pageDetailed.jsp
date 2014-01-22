<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<c:import url="common/youCssHeader.jsp"></c:import>
<c:if test="${menuCurPageTag=='hotel' }">
	<title>【${dataCityKeyId[searchDto.selectedCityId]}酒店】${dataCityKeyId[searchDto.selectedCityId]}酒店预订_${youGoods.name }_帮5游</title>
	<meta name="keywords" content="${dataCityKeyId[searchDto.selectedCityId]}酒店,${dataCityKeyId[searchDto.selectedCityId]}酒店预订_${youGoods.name }">
	<meta name="description"
		content="${dataCityKeyId[searchDto.selectedCityId]}酒店,${youGoods.name }品牌信息，酒店信息，酒店服务和设备列表。">
</c:if>
<c:if test="${menuCurPageTag!='hotel' }">
	<title>【${dataCityKeyId[searchDto.selectedCityId]}旅游】${youGoods.fromCity }到${youGoods.toCity }_${youGoods.name }</title>
	<meta name="keywords" content="${youGoods.name }">
	<meta name="description"
		content="${youGoods.name }，帮5买旅游网帮您轻松选择旅游产品，轻松发现令人惊奇优惠。">
</c:if>
<meta name="robots" content="index, follow">
<meta name="googlebot" content="index, follow">
</head>

<body>

	<!--头部 s-->
	<c:import url="common/taoHeader.jsp"></c:import>
	<!--头部 e-->
  <%-- ------------>${menuCurPageTag}----->${youGoods.youType} --%>
	<!--detail s-->
	<div class="w980 clear-fix">
	   	<div  style="text-align:center;margin-top:10px;">
	   		<script type="text/javascript">
			/*帮5游详情页-01*/
			var cpro_id = "u1399597";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
	   	</div>
	    <div class="crumb clearfix">
	        <div class="inner">
	            <a href="${rootPath }">帮5游</a> <b>&gt;</b>
	            <c:if test="${menuCurPageTag=='index' }">
					<a href="${rootPath }">首页</a>
				</c:if>
				<c:if test="${menuCurPageTag!='hotel' }">
					<c:if test="${menuCurPageTag=='domesticTravel'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel">国内游</a>
					</c:if>
					<c:if test="${menuCurPageTag=='abroadTravel'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_abroadTravel">境外游</a>
					</c:if>
					<c:if test="${menuCurPageTag=='peripheryTravel'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_peripheryTravel">周边游</a>
					</c:if>
				</c:if>
				<c:if test="${menuCurPageTag=='searchresult' }">
					<c:if test="${youGoods.youType=='0'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel">国内游</a>
					</c:if>
					<c:if test="${youGoods.youType=='1'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_abroadTravel">境外游</a>
					</c:if>
					<c:if test="${youGoods.youType=='2'}">
						<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_peripheryTravel">周边游</a>
					</c:if>
				</c:if>
				<c:if test="${menuCurPageTag=='hotel' }">
					<a href="${rootPath}/taoPage_${searchDto.selectedCityId}_hotel">酒店</a>
				</c:if>
				<b>&gt;</b> <span>${youGoods.name }</span>
	        </div>
	    </div>
	    <!--s-->
	    <div class="focus-area mt10">
	        <div data-id="${youGoods.id }" id="J_carousel" class="pic-show">
	            <ul class="big-pic">
					<c:forEach var="gl" items="${fn:split(youGoods.b5mImgUrls, ',')}" varStatus="status">
						<li>
							<c:choose>
								<c:when test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${youGoods.sourceUrl }" rel="nofollow" target="_blank"><img src="${gl }" alt="${youGoods.name}"></a>
								</c:when>
								<c:otherwise>
									<a class="tankuang" href="javascript:void(0);" rel="nofollow" target="_blank"
										clickUrl="${youGoods.clickUrl}" sourceUrl="${youGoods.sourceUrl}"><img src="${gl }" alt="${youGoods.name}"></a>
								</c:otherwise>
							</c:choose>
						</li>
					</c:forEach>
	            </ul>
	            <div class="sma-pic-wrap">
	                <ul class="sma-pic">
	                	<c:forEach var="gl" items="${fn:split(youGoods.b5mImgUrls, ',')}" varStatus="status">
							<li ${status.count==1?"class='on'":'' }>
								<a href="#" target="_blank"><img src="${gl }"></a>
							</li>
						</c:forEach>
	                </ul>
	            </div>
	            <a id="prev" href="javascript:;" class="prev">prev</a> <a id="next" href="javascript:;" class="next">next</a>
	        </div>
	
	
			<c:choose>
				<c:when test="${menuCurPageTag!='hotel' }">
					<div class="show-info" data-id="${youGoods.id }">
						<h1 class="title">${youGoods.name }</h1>
						<ul class="list">
							<li><label>出发城市</label>${youGoods.fromCity }</li>
							<li><label>目的城市</label>${youGoods.toCity }</li>
							<c:if test="${!fn:contains(youGoods.timePlan,'天')}">
								<li><label>时间安排</label>${youGoods.timePlan}天</li>
							</c:if>
							<c:if test="${fn:contains(youGoods.timePlan,'天')}">
								<li><label>时间安排</label>${youGoods.timePlan}</li>
							</c:if>
							<c:if test="${!fn:contains(youGoods.groupDate,'天天出发')}">
								<li><label>发团日期</label>${youGoods.groupDate}</li>
							</c:if>
							<c:if test="${fn:contains(youGoods.groupDate,'天天出发')}">
								<li><label>发团日期</label>天天出发</li>
							</c:if>
							
						</ul>
						<div class="old">
							<span class="price"><label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>${youGoods.hotelTransform
								}</span> <span class="num">已有<b>${youGoods.totalClick }</b>人购买
							</span>
						</div>
						<div class="now">
							<span class="price"><c:if test="${youGoods.salesPrice > 0}">特惠价<strong><em>&yen;</em>${youGoods.salesPrice }</strong>起 / 人</c:if><c:if test="${youGoods.salesPrice <= 0}"><strong>暂无报价</strong></c:if></span>
							<a class="tankuang btn" clickUrl="${youGoods.clickUrl}" rel="nofollow" sourceUrl="${youGoods.sourceUrl}" target="_blank">开始预订</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="show-info" data-id="${youGoods.id }">
						<h1 class="title">${youGoods.name }</h1>
						<ul class="list">
							<li><label>酒店品牌</label>${youGoods.brand }</li>
							<li><label>所在城市</label>${youGoods.city }</li>
							<c:if test="${youGoods.district!=null && youGoods.district!='' }">
								<li><label>所在区县</label>${youGoods.district }</li>
							</c:if>
							<c:if test="${youGoods.district == null || youGoods.district =='' }">
								<li><label>所在区县</label>未知</li>
							</c:if>
							<c:if test="${youGoods.buildings!=null && fn:trim(youGoods.buildings) != ''}">
								<li><label>周边地标</label>
								<c:choose>
										<c:when test="${fn:length(youGoods.buildings) > 36}">
											${fn:substring(youGoods.buildings, 0, 35)}...
										</c:when>
										<c:otherwise>
											${youGoods.buildings}
										</c:otherwise>
								</c:choose>
								<%-- ${fn:split(fn:split(youGoods.buildings,'<br>')[0],'；')[0] } --%></li>
							</c:if>
							<c:if test="${youGoods.buildings==null || fn:trim(youGoods.buildings) == '' }">
								<li><label>周边地标</label>暂无</li>
							</c:if>
							<c:if test="${youGoods.levelInfo!=null && fn:trim(youGoods.levelInfo)!='' }">
								<li><label>评级信息</label>
									<c:choose>
										<c:when test="${fn:indexOf(youGoods.levelInfo,'星') == -1}">
											${youGoods.levelInfo}星
										</c:when>
										<c:otherwise>
											${youGoods.levelInfo}
										</c:otherwise>
								</c:choose>
								</li>
							</c:if>
							<c:if test="${youGoods.levelInfo==null || fn:trim(youGoods.levelInfo)=='' }">
								<li><label>评级信息</label>未知</li>
							</c:if>
						</ul>
						<br /> <br />
						<div class="now">
							<span class="price"><c:if test="${youGoods.salesPrice > 0}">特惠价<strong><em>&yen;</em>${youGoods.salesPrice }</strong>起 / 人</c:if><c:if test="${youGoods.salesPrice<=0}"><strong>暂无报价</strong></c:if></span>
							<c:if test="${gl.rebateFlag=='1' }">
								<span class="flag-rebate">返利</span>
							</c:if>
							<a class="tankuang btn" clickUrl="${youGoods.clickUrl}" rel="nofollow" sourceUrl="${youGoods.sourceUrl}" target="_blank">开始预订</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
	
			<!--s-->
			<div class="focus-share">
				<label>分享给朋友：</label> <a
					href="javascript:((function(){var title=encodeURIComponent('${youGoods.name }-B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游、酒店价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游。@帮5买，地址：')+window.location.href;var link=encodeURIComponent('${rootPath}');window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?title='+title+'&url='+link);})())"
					class="qq" title="qq空间"></a> <a
					href="javascript:((function(){var title=encodeURIComponent('${youGoods.name }-B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游、酒店价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游。@帮5买，地址：')+window.location.href;var link=encodeURIComponent('${rootPath}');window.open('http://v.t.sina.com.cn/share/share.php?appkey=3222906970&title='+title+'&url='+link);})())"
					class="wb" title="新浪微博"></a> <a
					href="javascript:((function(){var title=encodeURIComponent('${youGoods.name }-B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游、酒店价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游。@帮5买，地址：')+window.location.href;var link=encodeURIComponent('${rootPath}');window.open('http://share.v.t.qq.com/index.php?c=share&a=index&title='+title+'&url='+link);})())"
					class="tx" title="腾讯微博"></a> <a
					href="javascript:((function(){var title=encodeURIComponent('${youGoods.name }-B5M帮5游为您提供云南、香港、台湾、韩国、日本、法国旅游、酒店价格攻略；携程、驴妈妈、途牛各大旅游网旅游价格一览，让您享受超值特惠旅游。@帮5买，地址：')+window.location.href;var link=encodeURIComponent('${rootPath}');window.open('http://widget.renren.com/dialog/share?title='+title+'&url='+link);})())"
					class="renren" title="人人网"></a>
			</div>
			<!--e-->
			
			
			<c:choose>
				<c:when test="${menuCurPageTag!='hotel' }">
					<!--s-->
					<div class="focus-notes">
						<strong>推荐理由</strong><br> ${youGoods.recommendation}
					</div>
					<!--e-->
				</c:when>
				<c:otherwise>
					<!--s-->
					<div class="focus-desc brand-info">
						<strong>品牌信息</strong><br> ${youGoods.brandInfo }
					</div>
					<!--e-->
					<!--s-->
					<div class="focus-desc hotel-info">
						<strong>酒店信息</strong><br> 
						<span>建造：${youGoods.buildTime }</span> 
						<span>酒店信息上次更新时间：${youGoods.lastUpTime }</span>
						<span>地理位置经度纬度：${youGoods.locationData }</span> 
						<span>常用酒店标签和分类：${youGoods.categoryLable }</span> 
						<span>酒店状态：${youGoods.status }</span>
					</div>
					<!--e-->
					<!--s-->
					<div class="focus-desc hotel-facility">
						<strong>酒店服务和设备列表</strong><br>
						<table class="facility-table">
							<c:if test="${youGoods.compFacility!=''&& youGoods.compFacility!=null}">
								<tr>
									<td class="col1"><span class="facility-title">综合设施</span></td>
									<td><span>${youGoods.compFacility }</span></td>
								</tr>
							</c:if>
							<c:if test="${youGoods.sportFacility!=''&& youGoods.sportFacility!=null}">
								<tr>
									<td class="col1"><span class="facility-title">活动设施</span></td>
									<td><span>${youGoods.sportFacility }</span></td>
								</tr>
							</c:if>
							<tr>
								<td class="col1"><span class="facility-title">服务项目</span></td>
								<td><span>${youGoods.serviceProject }</span></td>
							</tr>
							<c:if test="${youGoods.roomFacility!=''&& youGoods.roomFacility!=null}">
								<tr>
									<td class="col1"><span class="facility-title">客房设施</span></td>
									<td><span>${youGoods.roomFacility }</span></td>
								</tr>
							</c:if>
						</table>
					</div>
					<!--e-->
					<!--s-->
					<div class="focus-desc hotel-room">
						<strong>房型预定</strong><br>
					</div>
					<!--e-->
				</c:otherwise>
			</c:choose>
	    </div>
	    <c:if test="${menuCurPageTag=='hotel' }">
			<!--room s-->
			<div id="room" class="clear-fix">
				<div class="room-outer-wrap">
					<table>
						<tr class="head-tr">
							<td class="col1">&nbsp;</td>
							<td class="col2">
								<div class="bed-item">
									<div class="bed-info">返利</div>
									<!--<div class="bed-info">床型</div>-->
									<div class="bed-info">早餐</div>
									<div class="bed-info">房价（含服务费）</div>		
								</div>	
							</td>
						</tr>
						<c:forEach var="gl" items="${hotelDetailList}" varStatus="status">
							<tr class="no-top-border">
								<td class="bed-type"><img src="${gl.imgurl }" style="display:${gl.imgurl==''||gl.imgurl==null?'none':'' }" class="bed-type-img" alt="${gl.roomType }">${gl.roomType }</td>
								<td class="bed-list">
									<div class="bed-item bed-item-no-bottom-border">
										<div class="bed-info">
											<c:if test="${gl.rebateFlag=='1' }">
												<span class="rebate">返利</span>
											</c:if>
										</div>
										<c:choose>
											<c:when test="${gl.breakfast }">
												<div class="bed-info">含早</div>
											</c:when>
											<c:otherwise>
												<div class="bed-info">无早</div>
											</c:otherwise>
										</c:choose>
										<div class="bed-info">
											￥<span class="red-light">${gl.price }</span>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<!--room e-->
		</c:if>
	</div>
	<!--detail e-->
	<div style="text-align:center;margin-top:10px;">
			<script type="text/javascript">
			/*帮5游详情02文字*/
			var cpro_id = "u1400323";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
	</div>
	<!--wrap s-->
	<div class="w980 wrap-detail">
       <div class="module">
           <div class="module-hd clearfix">
				<h2>您可能喜欢</h2>
				<c:if test="${menuCurPageTag=='domesticTravel' || (menuCurPageTag=='searchresult' && youGoods.youType=='0')}">
					<b class="t1"></b>
					<a class="more" href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel"
						target="_blank">更多<span>&gt;</span></a>
				</c:if>
				<c:if test="${menuCurPageTag=='abroadTravel'  || (menuCurPageTag=='searchresult' && youGoods.youType=='1')}">
					<b class="t2"></b>
					<a class="more" href="${rootPath}/taoPage_${searchDto.selectedCityId}_abroadTravel" target="_blank">更多<span>&gt;</span></a>
				</c:if>
				<c:if test="${menuCurPageTag=='peripheryTravel'  || (menuCurPageTag=='searchresult' && youGoods.youType=='2')}">
					<b class="t3"></b>
					<a class="more" href="${rootPath}/taoPage_${searchDto.selectedCityId}_peripheryTravel"
						target="_blank">更多<span>&gt;</span></a>
				</c:if>
				<c:if test="${menuCurPageTag=='hotel' }">
					<b class="t4"></b>
					<a class="more" href="${rootPath}/taoPage_${searchDto.selectedCityId}_hotel"
						target="_blank">更多<span>&gt;</span></a>
				</c:if>
			</div>
			<div class="module-bd module-bd-detail clearfix">
				<c:forEach var="gl" items="${pageList.all}" varStatus="status">
					<div class="mod" data-id="${gl.id }" data-url="${gl.sourceUrl }"
						data-price="${gl.salesPrice }"
						data-spread="${gl.spread>0?status.count:gl.spread }"
						data-t-source="${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}">
						<div class="tit">
							<c:choose>
								<c:when test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${menuCurPageTag}_${gl.id }.html" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】   ${gl.name }</a>
								</c:when>
								<c:otherwise>
									<a class="tankuang" href="javascript:void(0);" target="_blank"
										clickUrl="${gl.clickUrl}"  rel="nofollow" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${menuCurPageTag}_${gl.id }.html">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】  ${gl.name }</a>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="pic">
							<c:choose>
								<c:when
									test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${menuCurPageTag}_${gl.id }.html" rel="nofollow" target="_blank"><span><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></span></a>
								</c:when>
								<c:otherwise>
									<a class="tankuang" href="javascript:void(0);" target="_blank"
										clickUrl="${gl.clickUrl}" rel="nofollow" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${menuCurPageTag}_${gl.id }.html"><span><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></span></a>
								</c:otherwise>
							</c:choose>
							<div class="original">
								<p>
									已有<b>${gl.totalClick }</b>人抢购
								</p>
							</div>
						</div>
						<div class="opt">
							<span>&yen;<c:if test="${gl.salesPrice > 0 }"><em>${gl.salesPrice }</em>起</c:if><c:if test="${gl.salesPrice <= 0 }"><em>暂无报价</em></c:if>
							</span>
							<c:choose>
								<c:when
									test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
								</c:when>
								<c:otherwise>
									<a class="tankuang btn" href="javascript:void(0);" rel="nofollow"
										target="_blank" clickUrl="${gl.clickUrl}"
										sourceUrl="${gl.sourceUrl}">去抢购</a>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div style="text-align:center;margin-top:10px;">
			<script type="text/javascript">
			/*帮5游详情页-03*/
			var cpro_id = "u1399610";
			</script>
			<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
		</div>
		<div class="fixed-pos">
			<div class="fixed-price" data-id="${youGoods.id }">
				<div class="old">
					<span class="num">已有<b>${youGoods.totalClick }</b>人购买</span>
				</div>
				<div class="now">
					<span class="price"><c:if test="${youGoods.salesPrice > 0}"><strong><em>&yen;</em>${youGoods.salesPrice }</strong>起 / 人</c:if><c:if test="${youGoods.salesPrice <= 0}"><strong>暂无报价</strong></c:if></span>
					<c:if test="${gl.rebateFlag=='1' }">
						<span class="flag-rebate">返利</span>
					</c:if>
					<a class="tankuang btn" clickUrl="${youGoods.clickUrl}" rel="nofollow" sourceUrl="${youGoods.sourceUrl}" target="_blank">开始预订</a>
				</div>
			</div>
		</div>
	</div>
	<!--wrap e-->

	<!--footer s-->
	<c:import url="common/taoFoot.jsp"></c:import>
	<!--footer e-->

	<script type="text/javascript" src="${rootPath}/js/you_detail.js?t=${today}"></script>
	
	<div id="baidu_button">
		<!-- Baidu Button BEGIN -->
		<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=6&amp;pos=right&amp;uid=0" ></script>
		<script type="text/javascript" id="bdshell_js"></script>
		<script type="text/javascript">
		var bds_config={"bdTop":379};
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
		</script>
		<!-- Baidu Button END -->
	</div>
</body>
</html>