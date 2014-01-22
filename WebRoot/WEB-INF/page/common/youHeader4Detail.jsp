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
<input type="hidden" id="auto_fill_chn" name="auto_fill_chn" value="${curPageTag}" />
<input type="hidden" id="auto_fill_server" name="auto_fill_server" value="${autofillServer}" />

<div class="top-hdbanner">
</div>
<div class="topbar">
</div>
<div class="header">
        <!-- <div class="top">
            <div class="panel">
                <h1 class="logo l"><a href="#"><img width="198" height="72" alt="logo" src="../../images/you/logo.jpg"></a></h1>
                <div class="panel-nav r sprite">
                    <span>特价旅游</span><span>超值酒店</span><span>省心省钱</span>
                </div>
                country html
                <div class="country-box">
                    <a class="chn" href="http://www.b5m.com" target="_blank">帮5买</a>
                    <a class="usa" href="javascript:void(0)">美国馆</a>
                    <a class="kor" href="http://korea.b5m.com" target="_blank">韩国馆</a>
                </div>
                country html
            </div>
        </div> -->
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
        <%-- <div class="nav cfx">
            <div class="panel">
                <ul class="cf f-yh">
                    <!-- <li class="b5m-home"><a href="http://www.b5m.com" class="icon-you"><span>帮5买首页</span></a></li> -->
                    <li ${pageType == "index" ? "class=\"nob cur\"" : ""}><a href="${rootPath}">首页</a></li>
                    <li ${pageType == "travel" || pageTag == "0" ? "class=\"nob cur\"" : ""}><a href="${rootPath}/travel">旅游</a></li>
                    <li ${pageTag == "1" ? "class=\"nob cur\"" : ""}><a href="${rootPath}/list_1_${searchDto.selectedCityId}_1.html">酒店</a></li>
                    <li ${pageTag == "2" ? "class=\"nob cur\"" : ""}><a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html">攻略</a></li>
                    <li ${pageTag == "3" ? "class=\"nob cur\"" : ""}><a href="${rootPath}/flight.html">机票</a><i class="new"></i></li>
                    <li ${pageTag == "4" ? "class=\"nob cur\"" : ""}><a href="${rootPath}/train.html">火车票</a><i class="new"></i></li>
                    <li class="last"><a href="http://tuan.b5m.com" target="_blank">团购</a></li>
                </ul>
            </div>
        </div> --%>
        <div class="header-nav-box">
            <div class="wp">
                <div class="header-nav-back"><a class="zdm-icon" title="帮5买首页" href="http://www.b5m.com/"><u>帮5买首页</u></a><s class="header-nav-back-s2"></s><s></s></div>
                <ul>
                    <!-- <li class="index cur"><a href="" target="_self">首页</a></li>
                    <li><a href="" target="_self">旅游</a></li>
                    <li><a href="" target="_self">酒店</a></li>
                    <li><a href="" target="_self">攻略</a></li>
                    <li><a href="" target="_self">机票</a><i class="new"></i></li>
                    <li><a href="" target="_self">火车票</a><i class="new"></i></li>
                    <li><a href="" target="_self">团购</a></li> -->
                    
                    <li ${pageType == "index" ? "class=\"index cur\"" : "class=\"index\""}><a href="${rootPath}">首页</a></li>
                    <li ${pageType == "travel" || pageTag == "0" ? "class=\"cur\"" : ""}><a href="${rootPath}/travel">旅游</a></li>
                    <li ${pageTag == "1" ? "class=\"cur\"" : ""}><a href="${rootPath}/list_1_${searchDto.selectedCityId}_1.html">酒店</a></li>
                    <li ${pageTag == "2" ? "class=\"cur\"" : ""}><a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html">攻略</a></li>
                    <li ${pageTag == "3" ? "class=\"cur\"" : ""}><a href="${rootPath}/flight.html">机票</a><i class="new"></i></li>
                    <li ${pageTag == "4" ? "class=\"cur\"" : ""}><a href="${rootPath}/train.html">火车票</a><i class="new"></i></li>
                    <li class="last"><a href="http://tuan.b5m.com" target="_blank">团购</a></li>
                </ul>
            </div>
        </div>
</div>