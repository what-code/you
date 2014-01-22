<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css"
	href="http://v3.jiathis.com/code/css/jiathis_counter.css">
<script type='text/javascript' src='http://cdn.bang5mai.com/upload/web/public/app/tongji/stat2.min.js?t=${today}'></script>
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/common.js?t=${today}"></script>
<script type='text/javascript' src="http://staticcdn.b5m.com/static/public/jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/autoFill.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath}/js/b5t.min.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath}/js/b5tHeader.min.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath }/js/you_dev.min.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath }/js/you_v2.js?t=${today}"></script>
<%-- <script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/you/you_v2.js?t=${today}"></script> --%>
	<!-- auto fill js s -->
		<!-- search -->
		<c:if test="${curPageTag!='noNoteResult' && curPageTag!='domesticTravel'&&curPageTag!='abroadTravel'&&curPageTag!='peripheryTravel'&&curPageTag!='hotel'&&curPageTag!='noResult'&&curPageTag!=''&&curPageTag!='notes'&&curPageTag!=null}">
			<script type="text/javascript" src="${rootPath}/js/search_dl.js?t=${today}"></script>
		</c:if>
		
		<!-- list -->
		<c:if test="${curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'}">
			<script type="text/javascript" src="${rootPath}/js/list_dl.js?t=${today}"></script>
		</c:if>
		
		<!-- note -->
		<c:if test="${curPageTag=='noNoteResult' || curPageTag=='noResult'}">
			<script type="text/javascript" src="${rootPath}/js/search_dl.js?t=${today}"></script>
		</c:if>
	<!-- auto fill js e -->
<!-- <div class="footer">
    <div class="footer-links">
        <a class="item" target="_blank" href="http://old.b5m.com/o/about">关于帮5买</a>|
        <a class="item" target="_blank" href="http://old.b5m.com/o/about/contact">联系我们</a>|
        <a class="item" target="_blank" href="http://old.b5m.com/o/about/business">合作商家</a>|
        <a class="item" target="_blank" href="http://old.b5m.com/o/about/links">友情链接</a>|
        <a class="item" target="_blank" href="http://old.b5m.com/o/about/job">加入我们</a>|
        <a class="item" target="_blank" href="http://app.b5m.com/index.html">移动应用</a>|
        <a class="item" target="_blank" href="http://www.b5m.com/sitemap.html">站点地图</a>
    </div>
    
    <div class="footer-copyright">Copyright © 2011-2013 B5M.COM. All rights reserved. Powered by B5Msoft Co.,Ltd. 
	<br>增值电信业务经营许可证：沪B2-20130065号 | 中华人民共和国互联网药品信息服务资格证书（沪）-非经营性-2013-0034</div>
    
	<div class="footer-public">
        <a target="_blank" href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120620142952978"><img src="http://staticcdn.b5m.com/static/images/common/shgs.gif" alt="上海工商"></a> 
        <a target="_blank" href="http://www.sgs.gov.cn/lz/licenseLink.do?method=licenceView&amp;entyId=20120620142952978"><img src="http://staticcdn.b5m.com/static/images/common/cxwz.png" alt="诚信网站"></a>
        <a target="_blank" href="http://www.315online.com.cn/member/315120051.html"><img src="http://staticcdn.b5m.com/static/images/common/315.gif" alt="网站交易保障中心安全网购门户"></a>  
    </div>
</div> -->
<script type='text/javascript' src='http://staticcdn.b5m.com/static/scripts/public/copyright.js'></script>
<!--footer js s-->
<div style="display: none;">
	<script
		src="http://tajs.qq.com/jiathis.php?uid=1626433&amp;dm=old.b5m.com"
		charset="utf-8"></script>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js"></script>
	
	<!-- Start Alexa Certify Javascript -->
	<script type="text/javascript">
	_atrk_opts = { atrk_acct:"InfVh1aUXR00ax", domain:"b5m.com",dynamic: true};
	(function() { var as = document.createElement('script'); as.type = 'text/javascript'; as.async = true; as.src = "https://d31qbv1cthcecs.cloudfront.net/atrk.js"; var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(as, s); })();
	</script>
	<noscript><img src="https://d5nxst8fruw4z.cloudfront.net/atrk.gif?account=InfVh1aUXR00ax" style="display:none" height="1" width="1" alt="" /></noscript>
	<!-- End Alexa Certify Javascript -->
</div>
<!--footer js e-->
