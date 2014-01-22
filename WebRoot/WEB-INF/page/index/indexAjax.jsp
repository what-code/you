<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>帮5游旅游网_专业旅游网</title>
<meta name="keywords" content="旅游导航，旅游网" />
<meta name="description" content="帮5游旅游网 - 帮您找最美的旅游地，得到最舒心的旅游体验。" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<c:import url="../common/youCssHeader.jsp"></c:import>
<link rel="canonical" href="http://www.b5m.com/"/>
</head>
<body>
	<!--头部 s-->
	<c:import url="../common/youHeader.jsp"></c:import>
	<!--头部 e-->
	
    <div class="wp mt20 you-hot">
        <h2 class="cfx"><i class="icon-city"></i>当季热门旅游<span>城市</span></h2>
        <div class="you-hot-city">
        	
        </div>
    </div>
    <div class="wp mt20 you-hot">
        <h2 class="cfx"><i class="icon-type"></i>当季热门旅游<span>类型</span></h2>
        <div class="you-hot-type cfx" id="you-hot-type-div">
        	
        </div>
    </div>
    <div class="wp mt20">
        <div class="floor-tit floor-tit-trip">
            <span class="floor-tit-more">
                <a href="${rootPath }/travel" target="_blank">更多&gt;</a>
            </span>
            <span class="floor-tit-num">1F</span>
            <h3>
                <span>旅游</span>
            </h3>
        </div>
    </div>
    <div class="wp mt20 floor-you-trip cfx">
        <div class="you-list-box fl">
            <div class="floor-tab cfx">
                <ul class="fl">
                    <li class="current"><a href="javascript:void(0);" data-city="-1">精选</a></li>
                    <li><a href="javascript:void(0);" data-city="2">上海</a></li>
                    <li><a href="javascript:void(0);" data-city="12">南京</a></li>
                    <li><a href="javascript:void(0);" data-city="17">杭州</a></li>
                    <li><a href="javascript:void(0);" data-city="278">合肥</a></li>
                    <li><a href="javascript:void(0);" data-city="1">北京</a></li>
                    <li><a href="javascript:void(0);" data-city="144">济南</a></li>
                    <li class="more"><a href="${rootPath }/travel" target="_blank">更多<i></i></a></li>
                </ul>
                <div class="fr">
                    <a class="current" href="javascript:void(0);" data-type="2">周边旅游</a>
                    <a href="javascript:void(0);" data-type="0">国内旅游</a>
                    <a href="javascript:void(0);" data-type="1">出境旅游</a>
                </div>
            </div>
            <div class="trip-list-wrap">
               <ul class="trip-list">
               </ul> 
            </div>
        </div>
        <div class="layout-side fr cfx">
            <div class="layout-side-list">
                <dl class="you-side-list">
                    <dt>
                        <div class="layout-side-list-tit side-you-tuan">
                            <a class="fr" href="http://tuan.b5m.com/_lvyoujiudian-lvyou_" target="_blank">更多&gt;</a>
                            <h2>旅游团购</h2>
                        </div>
                    </dt>
                    <c:forEach var="tt" items="${tuanTravel}" varStatus="status">
		                    <dd class="pic">
		                        <a href="${tt.url }" target="_blank"><img src="${tt.img }"/></a>
		                        <a href="${tt.url }" target="_blank" class="tit">${tt.title }</a>
		                    </dd>
		                    <dd class="price"><p class="ifr"><del><s class="ico-rmb">¥</s>${tt.price }</del><b>${tt.discount }</b>折</p><s class="ico-rmb">¥</s> <em>${tt.salesPrice }</em></dd>
		                    <dd class="sell">
		                        <a href="${tt.url }" target="_blank" class="blue fr">详情></a>已销售<ins>${tt.sold }</ins>
		                    </dd>
	                    </c:forEach>
                </dl>
            </div>
        </div>
    </div>
    <div class="wp mt20">
        <div class="floor-tit floor-tit-hotel">
            <span class="floor-tit-more">
                <a href="${rootPath}/list_1_${searchDto.selectedCityId}_1.html" target="_blank">更多></a>
            </span>
            <span class="floor-tit-num">2F</span>
            <h3>
                <span>酒店</span>
            </h3>
        </div>
    </div>
    <div class="wp mt20 floor-you-hotel cfx">
        <div class="you-list-box fl">
            <div class="floor-tab cfx">
                <ul class="fl">
                    <li class="current"><a href="javascript:void(0);" data-city="-1">精选</a></li>
                    <li><a href="javascript:void(0);" data-city="2">上海</a></li>
                    <li><a href="javascript:void(0);" data-city="12">南京</a></li>
                    <li><a href="javascript:void(0);" data-city="17">杭州</a></li>
                    <li><a href="javascript:void(0);" data-city="278">合肥</a></li>
                    <li><a href="javascript:void(0);" data-city="1">北京</a></li>
                    <li><a href="javascript:void(0);" data-city="144">济南</a></li>
                    <li class="more"><a href="${rootPath }/list_1_-1_1.html" target="_blank">更多<i></i></a></li>
                </ul>
            </div>
            <div class="hotel-list-wrap">
            	<ul class="hotel-list">
            	</ul>
            </div>
        </div>
        <div class="layout-side fr cfx">
            <div class="layout-side-list">
                <dl class="you-side-list">
                    <dt>
	                    <div class="layout-side-list-tit side-you-tuan">
	                        <a class="fr" href="http://tuan.b5m.com/_lvyoujiudian-jiudian_" target="_blank">更多&gt;</a>
	                        <h2>酒店团购</h2>
	                    </div>
                    </dt>
                	<c:forEach var="th" items="${tuanHotel}" varStatus="status">
	                    <dd class="pic">
	                        <a href="${th.url }" target="_blank"><img src="${th.img }"/></a>
	                        <a href="${th.url }" target="_blank" class="tit">${th.title }</a>
	                    </dd>
	                    <dd class="price"><p class="ifr"><del><s class="ico-rmb">¥</s>${th.price }</del><b>${th.discount }</b>折</p><s class="ico-rmb">¥</s> <em>${th.salesPrice }</em></dd>
	                    <dd class="sell">
	                        <a href="${th.url }" target="_blank" class="blue fr">详情></a>已销售<ins>${th.sold }</ins>
	                    </dd>
                    </c:forEach>
                </dl>
            </div>
        </div>
    </div>
    <div class="wp mt20">
        <div class="floor-tit floor-tit-guide">
            <span class="floor-tit-more">
                <a href="${rootPath}/list_2_${searchDto.selectedCityId}_1.html" target="_blank">更多&gt;</a>
            </span>
            <span class="floor-tit-num">3F</span>
            <h3>
                <span>旅游攻略</span>
            </h3>
        </div>
    </div>
    <div class="wp mt20 floor-you-guide cfx">
        <div class="guide-list">
           	<%-- <c:forEach var="g" items="${guideNotes}" varStatus="status">
	            <a href="${rootPath }/youGuideDetailed_-1_${g.id }.html" class="gd${status.count }" title="${g.name }" target="_blank">
	            	<img src="${g.b5mImg }" alt="" title="" onerror="this.src='${rootPath }/images/you/430x258.jpg';"/>
	            </a>
        	</c:forEach> --%>
        	<a href="${rootPath}/searcher?keyword=%E4%B9%9D%E5%AF%A8%E6%B2%9F&type=2&city=-1&page=1" class="gd1"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/g1.jpg?t=${today}" alt="" title=""/><span>九寨沟</span></a>
        	<a href="${rootPath}/searcher?keyword=%E6%98%86%E6%98%8E&type=2&city=-1&page=1" class="gd2"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/g2.jpg?t=${today}" alt="" title=""/><span>昆明</span></a>
        	<a href="${rootPath}/searcher?keyword=%E5%93%88%E5%B0%94%E6%BB%A8&type=2&city=-1&page=1" class="gd3"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/g3.jpg?t=${today}" alt="" title=""/><span>哈尔滨</span></a>
        	<a href="${rootPath}/searcher?keyword=%E4%B8%8A%E6%B5%B7&type=2&city=-1&page=1" class="gd4"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/t1.jpg?t=${today}" alt="" title=""/><span>上海</span></a>
        	<a href="${rootPath}/searcher?keyword=%E4%B8%89%E4%BA%9A&type=2&city=-1&page=1" class="gd5"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/t2.jpg?t=${today}" alt="" title=""/><span>三亚</span></a>
        	<a href="${rootPath}/searcher?keyword=%E9%A9%AC%E6%9D%A5%E8%A5%BF%E4%BA%9A&type=2&city=-1&page=1" class="gd6"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/t3.jpg?t=${today}" alt="" title=""/><span>马来西亚</span></a>
        	<a href="${rootPath}/searcher?keyword=%E9%A9%AC%E5%B0%94%E4%BB%A3%E5%A4%AB&type=2&city=-1&page=1" class="gd7"  target="_blank"><img src="http://staticcdn.b5m.com/images/you/pic/t4.jpg?t=${today}" alt="" title=""/><span>马尔代夫</span></a>
        </div>
    </div>
    
	<!--footer s-->
	<c:import url="../common/youFoot.jsp"></c:import>
	<!--footer e-->
	<script type="text/javascript" src="${rootPath }/js/index-travel.min.js?t=${today}"></script>
</body>
</html>