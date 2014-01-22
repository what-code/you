<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.b5m.tao.dto.YouSearchDto"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${searchDto.ipLocate }旅游攻略_${searchDto.ipLocate }周边旅游攻略_${searchDto.ipLocate }游记_${searchDto.ipLocate }旅游攻略大全_帮5游</title>
<meta name="keywords" content="${searchDto.ipLocate }旅游景点大全,${searchDto.ipLocate }旅游价格,${searchDto.ipLocate }旅游攻略2013,${searchDto.ipLocate }周边旅游景点">
<meta name="description" content="帮5游上海旅游攻略、上海旅游攻略大全，这里有万千网友为您分享的游记攻略，旅游景点介绍，新奇的旅游线路。让您用最实惠的旅游价格，享受到最不一般的旅程。">
</head>
<body>

	<!--头部 s-->
	<c:import url="common/taoHeader.jsp"></c:import>
	<!--头部 e-->

	<%-- <div class="bread cf w980"><a href="${rootPath }">帮5游</a>&nbsp;&gt;&nbsp<a href="second.html">${searchDto.ipLocate }攻略</a> --%>
	<%-- <div class="w980 clear-fix">
	<div class="crumb clearfix">
	        <div class="inner">
	            <a href="${rootPath }">帮5游</a> <b>&gt;</b>
				<a href="#">旅游攻略</a><b>&gt;</b>
	        </div>
	</div>
	</div> --%>
    <div class="w980 mt20 clearfix">
        <div class="col-sub col-sub-news">
        	<c:if test="${fn:length(youList) > 0}">
            <h4 class="tit">
                <span class="l">热门旅游</span>
                <a href="${rootPath}/taoPage_${searchDto.selectedCityId}_domesticTravel" class="more">更多></a>
            </h4>
            <c:forEach var="gl" items="${youList}" varStatus="status">
				<div class="mod" data-id="${gl.id }" data-url="${gl.sourceUrl }"
					data-hover="mod-hover">
					<div class="tit">
	                   <c:if test="${curPageTag != 'searchresult' }">
							<a href="${rootPath}/pageDetailed_${dataCityKey[searchDto.ipLocate] }_searchresult_${gl.id }.html" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
								${gl.name }</a>
						</c:if>
						<c:if test="${curPageTag == 'searchresult' }">
							<c:choose>
								<c:when
									test="${user_session_login == 'true' && user_session_login != null }">
									<a href="${rootPath}/pageDetailed_${dataCityKey[searchDto.ipLocate] }_searchresult_${gl.id }.html" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
										${gl.name }</a>
								</c:when>
								<c:otherwise>
									<a class="tankuang" href="javascript:void(0);" target="_blank" rel="nofollow" clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${dataCityKey[searchDto.ipLocate] }_searchresult_${gl.id }.html">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
										${gl.name }</a>
								</c:otherwise>
							</c:choose>
						</c:if>
	               </div>
					<div class="pic">
						<c:choose>
							<c:when
								test="${user_session_login == 'true' && user_session_login != null }">
								<a href="${rootPath}/pageDetailed_${dataCityKey[searchDto.ipLocate] }_searchresult_${gl.id }.html" rel="nofollow" target="_blank"><img src="${gl.imgurl }" alt="${gl.name }"></a>
							</c:when>
							<c:otherwise>
								<a class="tankuang" href="javascript:void(0);" target="_blank"
									clickUrl="${gl.clickUrl}" rel="nofollow" sourceUrl="${rootPath}/pageDetailed_${dataCityKey[searchDto.ipLocate] }_searchresult_${gl.id }.html"><span><img src="${gl.imgurl }" alt="${gl.name }"></span></a>
							</c:otherwise>
						</c:choose>
						<div class="original">
								已有<strong>${gl.totalClick }</strong>人抢购
						</div>
					</div>
					<div class="opt">
						<span>&yen;<em>${gl.salesPrice }</em>起
						</span>
						<c:choose>
							<c:when
								test="${user_session_login == 'true' && user_session_login != null }">
								<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
							</c:when>
							<c:otherwise>
								<a class="tankuang btn" rel="nofollow" href="javascript:void(0);"
									target="_blank" clickUrl="${gl.clickUrl}"
									sourceUrl="${gl.sourceUrl}">去抢购</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
			</c:if>
			
			<c:if test="${fn:length(hotelList) > 0}">
            <h4 class="tit tit-hotal">
                <span class="l">热门酒店</span>
                <a href="${rootPath}/taoPage_${searchDto.selectedCityId}_hotel" class="more">更多></a>
            </h4>
            <ul class="col-sub-hotal">
            	<c:forEach var="gl" items="${hotelList}" varStatus="status">
                 <li>
                     <span class="tit"><a rel="nofollow" href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${gl.id }.html" class="l" target="_blank">
                     <c:choose>
									<c:when test="${fn:length(gl.name) > 12}">
										${fn:substring(gl.name, 0, 11)}...
									</c:when>
									<c:otherwise>
										${gl.name }
									</c:otherwise>
							</c:choose>
                    	</a><em class="r">&yen;${gl.salesPrice }</em></span>
                     <a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_hotel_${gl.id }.html" rel="nofollow" class="l" target="_blank"><img src="${gl.imgurl }" alt="${gl.name }" width="50" height="50" /></a>
                     <p>
                            <!-- 位于：<a href="#">解放碑商圈</a><br /> -->
                            推荐理由：${fn:substring(gl.buildings, 0, 19)}...
                        </p>
                 </li>
                </c:forEach>
            </ul>
            </c:if>
        </div>
        <div class="col-main clearfix">
        	<form id="notes_news_nav" class="order-form" action="search.html" method="post" target="_self">
	            <div class="news-nav">
	                <a href="javascript:void(0)" class="${(searchDto.order=='hotNotes' || fn:trim(searchDto.order)=='') ? 'cur':''}"  rel="hotNotes">最热游记<em class="arr"></em></a>
	                <a href="javascript:void(0)" class="${searchDto.order=='newNotes' ? 'cur':''}" rel="newNotes">最新游记<em class="arr"></em></a>
	                <input type="hidden" name="keywords" value="${searchDto.keyWords }" />
					<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate] }" />
					<input type="hidden" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }" />
					<input type="hidden" name="ajaxType" value="noteSearchresult" />
					<input type="hidden" name="currPageNo" value="${searchDto.currPageNo }" />
                    <input type="hidden" name="order" value="${fn:trim(searchDto.order)=='' ? 'hotNotes':searchDto.order}" />
                    <input  type="hidden" name="page_mode" id="page_mode" value="search"/>
	            </div>
            </form>
            <ul class="news-ul">
            	<c:forEach var="gl" items="${pageList.all }" varStatus="status">
                 <li>
                     <a class="l" href="${rootPath}/notesDetail_${dataCityKey[searchDto.ipLocate] }_${gl.id}.html" target="_blank"><img src="${gl.b5mImg }" alt="${gl.name }"/></a>
                     <p>
                         <a href="${rootPath}/notesDetail_${dataCityKey[searchDto.ipLocate] }_${gl.id}.html" class="tit" target="_blank">
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
                              <c:choose>
									<c:when test="${fn:length(gl.contentMini) > 160}">
										${fn:substring(gl.contentMini, 0, 159)}...
									</c:when>
									<c:otherwise>
										${gl.contentMini }
									</c:otherwise>
							</c:choose>
                             <a href="${rootPath}/notesDetail_${dataCityKey[searchDto.ipLocate] }_${gl.id}.html">》更多内容</a>
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
        <!--page s-->
			<c:import url="common/tao_page_no_bar.jsp"></c:import>
			<!--page e-->
    </div>
	<!--footer s-->
	<c:import url="common/taoFoot.jsp"></c:import>
	<!--footer e-->
</body>
</html>