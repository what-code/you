<%@page import="com.b5m.you.common.GlobalWebCfg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page session="false"%>
<%
	String ucenterHttpUrl = GlobalWebCfg.getHttpUrl(request);
%>
<input type="hidden" id="rootPath" name="rootPath" value="${rootPath }" />
<input type="hidden" id="youType" name="youType" value="${pageTag}" />
<input type="hidden" id="hotelType" name="hotelType" value="${searchDto.hwOrNot}" />
<input type="hidden" id="hotelStar" name="hotelStar" value="${searchDto.hotelStar}" />
<input type="hidden" id="auto_fill_chn" name="auto_fill_chn" value="${curPageTag}" />
<input type="hidden" id="auto_fill_server" name="auto_fill_server" value="${autofillServer}" />

<div class="top-hdbanner">
</div>
<div class="topbar">
</div>
<div class="header">
        <div class="header-box cfx">
            <h1 class="header-logo fl"><a title="帮5游" href="/"><img alt="帮5游" src="http://staticcdn.b5m.com/static/images/you/logo.jpg"></a></h1>
            <div class="header-you-brief fl"></div>
            <!-- country html -->
            <div class="top-country">
                <a class="usa" title="美国馆" href="http://usa.b5m.com" target="_blank">美国馆</a>
                <a class="kor" title="韩国馆" href="http://korea.b5m.com" target="_blank">韩国馆</a>
            </div>
            <!-- country html -->
        </div>
        <div class="header-nav-box">
            <div class="wp">
                <div class="header-nav-back"><a class="zdm-icon" title="帮5买首页" href="http://www.b5m.com/"><u>帮5买首页</u></a><s class="header-nav-back-s2"></s><s></s></div>
                <ul>
                    <li ${pageType == "index" ? "class=\"index cur\"" : "class=\"index\""}><a href="${rootPath}">首页</a></li>
                    <li ${pageType == "travel" || pageTag == "0" ? "class=\"cur\"" : ""}><a href="${rootPath}/travel">旅游</a></li>
                    <li ${pageTag == "1" ? "class=\"cur\"" : ""}><a href="${rootPath}/list_1_${searchDto.selectedCityId}_1.html">酒店</a></li>
                    <li ${pageTag == "2" ? "class=\"cur\"" : ""}><a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html">攻略</a></li>
                    <li ${pageTag == "3" ? "class=\"cur\"" : ""}><a href="${rootPath}/flight.html">机票</a><i class="new"></i></li>
                    <li ${pageTag == "4" ? "class=\"cur\"" : ""}><a href="${rootPath}/train.html">火车票</a><i class="new"></i></li>
                    <li><a href="http://tuan.b5m.com" target="_blank">团购</a></li>
                </ul>
            </div>
        </div>
</div>

<c:if test="${pageTag != '3' && pageTag != '4'}">
<div class="wp mt20 cfx">
     <div class="layout-youSearch fl J_tab" id="J_tab">
         <ul class="layout-youSearch-menu tab-nav">
         	<li><s class="ico ico-ys-travel"></s>旅游</li>
             <li><s class="ico ico-ys-hotel"></s>酒店</li>
             <li><s class="ico ico-ys-strategy"></s>攻略</li>
         </ul>
            <!-- 度假-------------------s-->
	         <ul class="layout-youSearch-travel tabCont">
	             <li class="you-start-city"><label>出发</label><input type="text" value="${(pageType == 'travel' || pageType == 'index') ? searchDto.selectedCity : '' }" class="you-start" readonly="readonly"/><input type="hidden" id="travel_city" value="${(pageType == 'travel' || pageType == 'index') ? searchDto.selectedCityId : '-1'}" class="hidden_city" /><span>热门景点：</span><a href="javascript:void(0)">东方明珠</a><a href="javascript:void(0)">故宫</a><a href="javascript:void(0)">九寨沟</a><a href="javascript:void(0)">三亚</a></li>
	             <li><label>想去</label><input type="text" value="${(pageType == 'travel' || pageType == 'index') ? searchDto.keyWords : '' }" class="w400 J_autofill" id="trip_keyword" autofocus="autofocus"/><span class="bt-youSearch travel" id="btn_strip">搜 索</span></li>
	             <li>
	             	<span>价格：</span>
	             	<a ${searchDto.priceStart=='0'&&searchDto.priceEnd=='0'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_0_0_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">全部</a>
	             	<a ${searchDto.priceStart=='0'&&searchDto.priceEnd=='200'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_0_200_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">200元以下</a>
	             	<a ${searchDto.priceStart=='200'&&searchDto.priceEnd=='500'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_200_500_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">200-500元</a>
	             	<a ${searchDto.priceStart=='500'&&searchDto.priceEnd=='1000'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_500_1000_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">500-1000元</a>
	             	<a ${searchDto.priceStart=='1000'&&searchDto.priceEnd=='3000'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_1000_3000_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">1000-3000元</a>
	             	<a ${searchDto.priceStart=='3000'&&searchDto.priceEnd=='0'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_3000_0_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">3000元以上</a>
	             </li>
	             <li>
	             	<span>时间：</span>
	             	<a ${searchDto.daysMini=='0'&&searchDto.daysMax=='0'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_0_0_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">全部</a>
	             	<a ${searchDto.daysMini=='0'&&searchDto.daysMax=='1'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_0_1_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">1日</a>
	             	<a ${searchDto.daysMini=='2'&&searchDto.daysMax=='3'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_2_3_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">2-3日</a>
	             	<a ${searchDto.daysMini=='3'&&searchDto.daysMax=='7'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_3_7_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">3-7日</a>
	             	<a ${searchDto.daysMini=='7'&&searchDto.daysMax=='0'?'class="current"':'' } href="${rootPath}/list_${searchDto.ajaxType }${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_7_0_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">7日以上</a>
	             </li>
	             <li>
	             	<span>类型：</span>
	             	<a ${searchDto.ajaxType=='all'?'class="current"':'' } href="${rootPath}/list_all${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">全部</a>
	             	<a ${searchDto.ajaxType=='periphery'?'class="current"':'' } href="${rootPath}/list_periphery${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">周边游</a>
	             	<a ${searchDto.ajaxType=='domestic'?'class="current"':'' } href="${rootPath}/list_domestic${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">国内游</a>
	             	<a ${searchDto.ajaxType=='abroad'?'class="current"':'' } href="${rootPath}/list_abroad${searchDto.keyWords!=''?'_':'' }${searchDto.keyWords!=''?searchDto.keyWords:'' }_${searchDto.daysMini }_${searchDto.daysMax }_${searchDto.priceStart }_${searchDto.priceEnd }_${searchDto.selectedCityId }_${searchDto.currPageNo }.html">境外游</a>
	             </li>
	         </ul>
         	<!-- 度假-------------------e-->
         	
         	<!-- 酒店-------------------s-->
         <div class="tabCont" id="J_tab_inner">
                <div class="layout-youSearch-tit  tab-nav pz"><span>国内酒店</span><span>海外酒店</span></div>
                <ul class="layout-youSearch-hotel">
                    <li class="youSearch-item-inCity">
                        <label>入住城市</label><input class="youSearch-hotel-input-name w232 J_city_cn" type="text" value='${searchDto.hwOrNot == "0" ? searchDto.selectedCity : ""}' id="hotelCity0" readonly="readonly"/><input type="hidden" value='${searchDto.hwOrNot == "0" ? searchDto.selectedCityId : ""}' class="hidden_city" id="hidden_city0"/>
                    </li>
                    <li class="ml18 pl58">
                        <label>酒店级别</label>
                        <div class="youSearch-item-hotelgrade boxInline ml18">
                            <span class="select-yousearch trigger-slt"><!--trigger-slt-cur-->
                                <em>不限</em>
                                <input type="hidden" value="" class="hidden-level" id="hidden_level0"/>
                                <s class="slt-single2"></s>
                                <s class="trigger-slt-list" style="display: none;">
                                    <u data-id="">不限</u>
                                    <u data-id="5">五星级/豪华</u>
                                    <u data-id="4">四星级/高档</u>
                                    <u data-id="3">三星级/舒适</u>
                                    <u data-id="0">二星级以下/经济</u>
                                </s>
                            </span>
                        </div>
                    </li>
                    <li class="pr26"><label>入住日期</label><input type="text"  value="${fn:trim(searchDto.hotelBeginDate) == '' ? defaultToday : searchDto.hotelBeginDate}"  class="w168 dateStart" id="hotelStart0" readonly="readonly"/></li>
                    <li><label>退房日期</label><input type="text"  value="${fn:trim(searchDto.hotelEndDate)  == '' ? defaultTomorrow : searchDto.hotelEndDate}"  class="w168 dateEnd" id="hotelEnd0" readonly="readonly"/></li>
                    <li><!-- <span class="bt-youSearch bt-mapsearchbt ifr">地图搜索<u></u></span> --><label>关键词</label><input  type="text" value='${(searchDto.hwOrNot == "0" && pageTag == "1") ? searchDto.keyWords : "" }' class="w270 J_autofill" id="hotelKeyWord0" autofocus="autofocus"/><span class="bt-youSearch" id="btYouSearch0">搜 索</span></li>
                </ul>
                <ul class="layout-youSearch-hotel layout-youSearch-hotelhw" style="display:none;">
                    <li>
                        <label>目的地</label><input class="youSearch-hotel-input-name w232 J_city_en" type="text" value='${searchDto.hwOrNot == "1" ? searchDto.selectedCity : ""}' id="hotelCity1" readonly="readonly"/><input type="hidden" value='${searchDto.hwOrNot == "1" ? searchDto.selectedCityId : ""}' class="hidden_city" id="hidden_city1"/>
                    </li>
                    <li class="ml18 pl58">
                        <label>酒店级别</label>
                        <div class="youSearch-item-hotelgrade boxInline ml18">
                            <span class="select-yousearch trigger-slt">
                                <em>不限</em>
                                <input type="hidden" value="" class="hidden-level" id="hidden_level1"/>
                                <s class="slt-single2"></s>
                                <s class="trigger-slt-list" style="display: none;">
                                    <u data-id="">不限</u>
                                    <u data-id="5">五星级/豪华</u>
                                    <u data-id="4">四星级/高档</u>
                                    <u data-id="3">三星级/舒适</u>
                                    <u data-id="0">二星级以下/经济</u>
                                </s>
                            </span>
                        </div>
                    </li>
                    <li class="pr26">
                        <label>入住日期</label><input type="text"  value="${fn:trim(searchDto.hotelBeginDate) == '' ? defaultToday : searchDto.hotelBeginDate}"  class="w168 dateStart" id="hotelStart1" readonly="readonly"/>
                    </li>
                    <li><input type="text"  value="${fn:trim(searchDto.hotelEndDate)  == '' ? defaultTomorrow : searchDto.hotelEndDate}"  class="w168 dateEnd" id="hotelEnd1" readonly="readonly"/><label>退房日期</label></li>
                    <li><label>关键词</label><input  type="text" value='${(searchDto.hwOrNot == "1" && pageTag == "1") ? searchDto.keyWords : "" }' class="w400 J_autofill" id="hotelKeyWord1" autofocus="autofocus"/><span class="bt-youSearch" id="btYouSearch1">搜 索</span></li>
                </ul>
            </div>
            <!-- 酒店-------------------s-->
             
         	<!-- 攻略-------------------s-->
	         <ul class="layout-youSearch-strategy tabCont" id="noteSearch">
	             <li>
	                 <!-- <div class="trigger-more fr">trigger-more-cur
	                     <span class="trigger-more-tit">更多</span>
	                     <u class="slt-single"></u>
	                 </div> -->
	                 <span>国内攻略：</span><a href="${rootPath}/searcher?keyword=昆明&type=2&city=-1&page=1">云南</a><a href="${rootPath}/searcher?keyword=杭州&type=2&city=-1&page=1">浙江</a><a href="${rootPath}/searcher?keyword=北京&type=2&city=-1&page=1">北京</a><a href="${rootPath}/searcher?keyword=成都&type=2&city=-1&page=1">四川</a><a href="${rootPath}/searcher?keyword=西藏&type=2&city=-1&page=1">西藏</a><a href="${rootPath}/searcher?keyword=贵州&type=2&city=-1&page=1">贵州</a><a href="${rootPath}/searcher?keyword=南京&type=2&city=-1&page=1">江苏</a><a href="${rootPath}/searcher?keyword=内蒙&type=2&city=-1&page=1">内蒙古</a><a href="${rootPath}/searcher?keyword=广西&type=2&city=-1&page=1">广西</a>
	             </li>
	             <li><span>国外攻略：</span><a href="${rootPath}/searcher?keyword=巴黎伦敦&type=2&city=-1&page=1">欧洲</a><a href="${rootPath}/searcher?keyword=新加坡&type=2&city=-1&page=1">东南亚</a><a href="${rootPath}/searcher?keyword=美国&type=2&city=-1&page=1">北美洲</a><a href="${rootPath}/searcher?keyword=印度&type=2&city=-1&page=1">南亚</a><a href="${rootPath}/searcher?keyword=日本&type=2&city=-1&page=1">东亚</a><a href="${rootPath}/searcher?keyword=南非&type=2&city=-1&page=1">非洲</a><a href="${rootPath}/searcher?keyword=悉尼&type=2&city=-1&page=1">大洋洲</a><a href="${rootPath}/searcher?keyword=地中海&type=2&city=-1&page=1">西亚</a></li>
	             <li><input type="text"  class="w400 J_autofill" id="noteKeyWord" value="${pageTag == '2' ? searchDto.keyWords : '' }"/><span class="bt-youSearch">搜 索</span></li>
	         </ul>
	        <!-- 攻略-------------------e-->
     </div>
     <div class="fr layout-add bd">
        <%-- <a href="http://tuan.b5m.com" target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/B-250-180.jpg?t=${today}"/></a> --%>
		<!-- 广告位：帮5游右上方广告位_首页_频道_250*180 -->
		<script type="text/javascript" >BAIDU_CLB_SLOT_ID = "850019";</script>
		<script type="text/javascript" src="http://cbjs.baidu.com/js/o.js"></script>
     </div>
 </div>
 </c:if>