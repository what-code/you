<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<c:import url="common/youCssHeader.jsp"></c:import>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${searchDto.keyWords}-帮5游-旅游特惠网</title>
<meta name="keywords" content="旅游网,旅游,帮5游,特惠,国内游,出境游,周边游,酒店预订,帮5买,B5M">
<meta name="description"
	content="帮5游旅游网汇聚全部旅游网特惠信息，帮您精选最优质的旅游团购、特惠产品，包含国内游、出境游、酒店预定等各种服务。帮5买旗下网站：帮5游旅游网。">
</head>
<body>

	<!--头部 s-->
	<c:import url="common/youHeader.jsp"></c:import>
	<!--头部 e-->
	<%-- ------------>${user_session_login == 'true' && user_session_login != null } --%>
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
	   
	   <div class="col-main">
		 
		   <form id="J_order" class="order-form" action="search.html" method="post" target="_self">
		    <!--度假搜索选项 s-->
					<c:if test="${curPageTag!='hotel' && curPageTag!='hotelSearchresult' && curPageTag !='domesticTravel' && curPageTag !='abroadTravel' && curPageTag !='peripheryTravel'}">
						<dl class="s-way" id="j_s_way">
							<dt>您已选择：<span class="r-result" id="J_result"><span class="btns">方式：<span class="s-value"></span><em class="close"></em></span><!-- <span class="btns">目的地：<span class="s-value"></span><em class="close"></em></span> --><span class="btns">天数：<span class="s-value"></span><em class="close"></em></span><span class="btns">类型：<span class="s-value"></span><em class="close"></em></span></span>
							</dt>
							<dd>
							<ul>
								<li><span class="tit">方式：</span><div class="rt"><a class="act first" rv="0" href="javascript:void(0);">全部</a><a rv="1" href="javascript:void(0);">跟团游</a><a rv="2" href="javascript:void(0);">自由行</a><a rv="3" href="javascript:void(0);">其他</a></div></li>
								
								<!-- <li style="display:none"><span class="tit">目的地：</span><div class="rt"><a rv="0" class="act first">全部</a><a rv="1">自由行</a><a rv="2">自由行</a><a>自由行</a><a>自行</a><a>自由行</a></div></li> -->
								
								<li><span class="tit">天数：</span><div class="rt"><a class="act first" rv="0" href="javascript:void(0);">全部</a><a rv="1" href="javascript:void(0);">1天</a><a rv="2" href="javascript:void(0);">2天</a><a rv="3" href="javascript:void(0);">3天</a><a rv="4" href="javascript:void(0);">4天</a><a rv="5" href="javascript:void(0);">5天</a><div class="sd"><input type="text" id="text_days" class="inp-txt"><span class="gray">天</span><span class="qd" id="bt_days_ok">确定 </span></div></div></li>
								
								<li class="last has-more"><span class="tit">类型：</span><div class="rt"><a class="act first" rv="0" href="javascript:void(0);">全部</a><a rv="1" href="javascript:void(0);">海岛</a><a rv="2" href="javascript:void(0);">山水</a><a rv="3" href="javascript:void(0);">温泉</a><a rv="4" href="javascript:void(0);">城市</a><a rv="5" href="javascript:void(0);">游乐</a><a rv="6" href="javascript:void(0);">古镇</a><a rv="7" href="javascript:void(0);">港澳台</a><a rv="8" href="javascript:void(0);">邮轮</a><a rv="9" href="javascript:void(0);">漂流</a>
								</div><a class="more">更多</a></li>					
							</ul>
							</dd>
						</dl>
					</c:if>
			<!--度假搜索选项 e-->
			
		   <c:if test="${curPageTag !='domesticTravel' && curPageTag !='abroadTravel' && curPageTag !='peripheryTravel'}">
		   <div class="orderby">
		   		<!-- 城市选择 s -->
				        <div class="orderby-local l" data-hover="orderby-local-hover">
				           <span class="cityname icon-you l">${searchDto.selectedCityId == -1 ? '全部':dataCityKeyId[searchDto.selectedCityId]}</span>
				            <c:if test="${curPageTag!='hotel' && curPageTag!='hotelSearchresult'}">
								 <span class="go l">出发</span>
							</c:if>
				           <em class="arrow l"></em>
				           <c:if test="${curPageTag=='hotelSearchresult' }">
					           <c:if test="${fn:length(hotelCity) > 0 }">
							   	   <div class="city-outer-wrap">
								   		<c:forEach var="c" items="${hotelCity }" varStatus="s">
									   		<a href="javascript:void(0);" data-city="${c.key }">${c.value }</a>
								   			<c:if test="${s.count%6==0 }">
												<br/>
											</c:if>
										</c:forEach>
									</div>
								</c:if>
				           </c:if>
				           <c:if test="${curPageTag!='hotelSearchresult' }">
					           <c:if test="${fn:length(youCity) > 0 }">
							   	   <div class="city-outer-wrap">
								   		<c:forEach var="c" items="${youCity }" varStatus="s">
									   		<a href="javascript:void(0);" data-city="${c.key }">${c.value }</a>
								   			<c:if test="${s.count%6==0 }">
												<br/>
											</c:if>
										</c:forEach>
									</div>
								</c:if>
				           </c:if>
				        </div>
			      <!-- 城市选择 e -->
			      
			      
			        <!--度假搜索选项 s1-->
			        <c:if test="${curPageTag!='hotel' && curPageTag!='hotelSearchresult' && curPageTag !='domesticTravel' && curPageTag !='abroadTravel' && curPageTag !='peripheryTravel'}">
	                    <div class="orderby-filter l orderby-${(searchDto.order=='totalClick' && searchDto.sort == 'DESC') ? 'des' : 'asc'}">
                            <a href="javascript:void(0)" rel="totalClick">销量</a><em class="arrow "></em><em class="arrow arrow2"></em>							
                        </div>
						<div class="orderby-filter l orderby-${(searchDto.order=='salesPrice' && searchDto.sort == 'DESC') ? 'des des2 ' : 'asc des2'}">
                            <div class="prize"><a href="javascript:void(0)" rel="salesPrice">价格</a><em class="arrow"></em><em class="arrow arrow2"></em></div>
							<div class="sd"><input type="text" id="price_start" name="price_start" class="inp-txt first-inp" value="${(searchDto.priceStart==-1 || searchDto.priceStart==0) ? '' : searchDto.priceStart}"> - <input type="text" id="price_end" name="price_end" class="inp-txt" value="${(searchDto.priceEnd==-1 || searchDto.priceEnd==0) ? '' : searchDto.priceEnd}">
                                <p class="bt"><span class="qd" id="bt_price_ok">确定 </span><span class="orange">价格区间</span></p>
                            </div>
                        </div>
						<div class="orderby-filter l orderby-time">
                           出行时间：<input type="text" id="J_date1" name="J_date1" class="inp-txt" value="${searchDto.timeStart}" readonly> <!-- - --> <input type="hidden" id="J_date2" name="J_date2" class="inp-txt" value="${searchDto.timeEnd}" readonly>
                        </div>
                    </c:if>
                    <!--度假搜索选项 e1-->
                    
                    <!-- 酒店搜索条件 s -->
                    <c:if test="${curPageTag=='hotel' || curPageTag=='hotelSearchresult'}">
						<div class="orderby-filter l ${searchDto.sort=='ASC'?'orderby-asc':'orderby-des' }">
						    <a href="javascript:void(0)" rel="salesPrice">价格</a><em class="arrow"></em><em class="arrow arrow2"></em>
						</div>					
						
						<div class="orderby-filter l ${searchDto.sort=='ASC'?'orderby-asc':'orderby-des' }">
						    <a href="javascript:void(0)" rel="totalClick">销量</a><em class="arrow "></em><em class="arrow arrow2"></em>
						</div>
	                    <dl id="J_pi" class="prize-interval s-h" data-hover="ss-hover" >
	                            <dt>
	                            	<!-- <span class="ss-tit">价格区间 :</span> -->
	                            	<span class="sh-tit">价格区间 :</span>
	                            	<span class="ss-value">
	                            	<c:choose>
	                            		<c:when test="${fn:trim(searchDto.hotelPrice)=='0'}">
	                            			<￥150
	                            		</c:when>
	                            		<c:when test="${fn:trim(searchDto.hotelPrice)=='1000'}">
	                            			>￥600
	                            		</c:when>
	                            		<c:otherwise>
	                            			￥${fn:trim(searchDto.hotelPrice)==''?'全部':searchDto.hotelPrice}
	                            		</c:otherwise>
	                            	</c:choose>
	                            	
	                            </span><em class="arrow"></em></dt>
								<dd>
								<ul id="ul_hotel_price">
									<li rv="">全部</li>
									<li rv="0"><￥150</li>
									<li rv="150-300">￥150-300</li>
									<li rv="300-600">￥300-600</li>
									<li rv="1000">>￥600</li>								
								</ul>
								<div class="bt">
									<input type="text" class="inp-txt" id="hotel_price_s"/> - <input type="text" class="inp-txt" id="hotel_price_e"/> <a class="qd" id="hotel_bt_price_ok">确定</a>							
								</div>
								</dd>
	                        </dl>						
							<dl  id="J_ss" class="star-select s-h" data-hover="ss-hover" >
	                            <dt><span class="ss-tit">星级 :</span><span class="ss-value">
	                            	${fn:trim(searchDto.hotelStarChn)==''?'全部':searchDto.hotelStarChn}
	                            </span><em class="arrow"></em></dt>
								<dd>
								<ul id="ul_hotel_star">
									<li rv="">全部</li>
									<li rv="0">三星以下</li>
									<li rv="3">三星</li>
									<li rv="4">四星</li>
									<li rv="5">五星</li>
									<li rv="1000">五星以上</li>								
								</ul>							
								</dd>
	                        </dl>
                      </c:if>
                    <!-- 酒店搜索条件 e -->
                    
					<input type="hidden" name="keywords" value="${searchDto.keyWords }" />
					<input type="hidden" name="city" value="${dataCityKey[searchDto.ipLocate] }" />
					<input type="hidden" id="ajaxType" name="ajaxType" value="${curPageTag }" />
					<input type="hidden" name="currPageNo" id="currPageNo" value="${searchDto.currPageNo }" />
                    <input type="hidden" name="order" value="${searchDto.order }" />
                    <input type="hidden" name="sort" value="${searchDto.sort }" />
                    <input  class="s-hd" type="hidden" name="type0" id="type0" value="${searchDto.type0}"/>
                    <%-- <input class="s-hd" type="hidden" name="destination" id="destination" value="${searchDto.destination}" /> --%>
                    <input  class="s-hd" type="hidden" name="days" id="days" value="${searchDto.days}"/>
                    <input  class="s-hd" type="hidden" name="type1" id="type1" value="${searchDto.type1}"/>
                    <input  type="hidden" name="hotel_price" id="hotel_price" value="${searchDto.hotelPrice}"/>
                    <input  type="hidden" name="hotel_star" id="hotel_star" value="${searchDto.hotelStar}"/>
                    <input type="hidden" id="selectedCityId" name="selectedCityId" value="${searchDto.selectedCityId != -1 ? searchDto.selectedCityId: -1 }"/>
                    <input  type="hidden" name="pageMode" id="page_mode_map" value="${searchDto.pageMode == null ? 'search' : searchDto.pageMode}"/>
		   </div>
		   </c:if>
		   </form>
		   
		   <c:if test="${curPageTag !='domesticTravel' && curPageTag !='abroadTravel' && curPageTag !='peripheryTravel' && fn:length(pageList.all) > 0}">
			   <div class="search-tit icon-you">
			       搜索”${searchDto.keyWords}“,搜索结果如下:
			   </div>
		   </c:if>
		  
		  <c:if test="${fn:length(pageList.all) == 0}">
			   <div class="search-trip-error">没有搜索到结果，请减少过滤条件重新搜索。</div>
		   </c:if>
		  
		   <c:if test="${fn:length(pageList.all) > 0}">
		   <div class="home-mod clearfix">
		       <div class="module-bd module-bd-search clearfix mar">
		           <!-- loop -->
		           <c:forEach var="gl" items="${pageList.all}" varStatus="status">
						<div class="mod" data-id="${gl.id }" data-url="${gl.sourceUrl }"
							data-price="${gl.salesPrice }"
							data-spread="${gl.spread>0?status.count:gl.spread }"
							data-t-source="${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}"
							data-hover="mod-hover">
							
							<div class="tit">
								<%-- <c:choose>
									<c:when
										test="${user_session_login == 'true' && user_session_login != null }">
										<a rel="nofollow" href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
											${gl.name }</a>
									</c:when>
									<c:otherwise>
										<a class="tankuang" rel="nofollow" href="javascript:void(0);" target="_blank" clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
											${gl.name }</a>
									</c:otherwise>
								</c:choose> --%>
								<%-- --->${fn:replace(gl.youTuan," ","") != ""}
								-->${gl.youTuan != 1}
								---->${gl.youTuan} 
								---->${gl.id} 
								--%> 
								<c:choose>
									<c:when
										test="${(fn:replace(gl.youTuan,\" \",\"\") != \"\") && gl.youTuan != 1}">
										<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】 
											${gl.name }</a>
									</c:when>
									<c:otherwise>
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank">【${dataSource[gl.source]!=null?dataSource[gl.source]:gl.source}】${gl.name }</a>
									</c:otherwise>
								</c:choose>
			                 </div>
							<div class="pic">
								<c:choose>
									<%-- <c:when
										test="${user_session_login == 'true' && user_session_login != null }">
										<a href="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html" rel="nofollow" target="_blank"><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></a>
									</c:when>
									<c:otherwise>
										<a class="tankuang" href="javascript:void(0);" rel="nofollow" target="_blank"
											clickUrl="${gl.clickUrl}" sourceUrl="${rootPath}/pageDetailed_${searchDto.selectedCityId}_${curPageTag == 'hotelSearchresult' ? 'hotel' : curPageTag}_${gl.id }.html"><span><img src="http://staticcdn.b5m.com/static/images/common/transparent.png" style="background:url(${gl.imgurl }) no-repeat center center" alt="${gl.name }"></span></a>
									</c:otherwise> --%>
									
									<c:when
										test="${(fn:replace(gl.youTuan,\" \",\"\") != \"\") && gl.youTuan != 1}">
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
										<a class="tankuang btn" rel="nofollow" href="javascript:void(0);"
											target="_blank" clickUrl="${gl.clickUrl}"
											sourceUrl="${gl.sourceUrl}">去抢购</a>
									</c:otherwise>
								</c:choose> --%>
								
								<c:choose>
									<c:when
										test="${(fn:replace(gl.youTuan,\" \",\"\") != \"\") && gl.youTuan != 1}">
										<a href="${gl.sourceUrl}"  target="_blank" class="btn">去抢购</a>
									</c:when>
									<c:otherwise>
										<a href="${gl.sourceUrl}" rel="nofollow" target="_blank" class="btn">去抢购</a>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forEach>
		           <!-- /loop -->
		       </div>
		   </div>
		   </c:if>
		</div>
		<!--page s-->
		<c:import url="common/tao_page_no_bar.jsp"></c:import>
		<!--page e-->
		<c:if
			test="${curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'}">
			<div  style="text-align:center;margin-top:-18px;margin-bottom:-20px">
				<script type="text/javascript">
				/*帮5游列表页960*90图片*/
				var cpro_id = "u1422687";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
			</div>
		</c:if>
	</div>
	
	<input  type="hidden" name="page_mode" id="page_mode" value="search"/>
	<!--footer s-->
	<c:import url="common/youFoot.jsp"></c:import>
	<!--footer e-->
	
	<script type="text/javascript">
		window.onload = function(){
			//alert('QQ');
			var cityName = $('#hotelCityFind').html();
			var cityId = $('#hotelSelectedCityId').val();
			if(cityName == '全部' && cityId != '-1'){
				$('#hotelSelectedCityId').val('-1');
			}
		}
	</script>
</body>
</html>