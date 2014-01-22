<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href="http://v3.jiathis.com/code/css/jiathis_counter.css?t=${today}">
<%-- <script type='text/javascript' src='http://cdn.bang5mai.com/upload/web/public/app/tongji/stat2.min.js?t=${today}'></script> --%>
<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/jquery-1.9.1.min.js?t=${today}"></script>
<script type="text/javascript" src="http://staticcdn.b5m.com/scripts/common/common.js?t=${today}"></script>
<!-- <script type='text/javascript' src="http://staticcdn.b5m.com/static/public/jquery-ui/jquery-ui.js"></script> -->
<script type="text/javascript" src="http://staticcdn.b5m.com/scripts/common/autoFill.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath }/js/search.min.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath }/js/jquery.ui.datepicker.min.js?t=${today}"></script>

<!-- copyright-------------------s-->
<script type='text/javascript' src='http://staticcdn.b5m.com/static/scripts/public/copyright.js?t=${today}'></script>
<!-- copyright-------------------e-->

<!--footer js s-->
<div style="display: none;">
	<script src="http://tajs.qq.com/jiathis.php?uid=1626433&amp;dm=old.b5m.com" charset="utf-8"></script>
	<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?t=${today}"></script>
	<!-- Start Alexa Certify Javascript -->
	<script type="text/javascript">
		_atrk_opts = { atrk_acct:"InfVh1aUXR00ax", domain:"b5m.com",dynamic: true};
		(function() { var as = document.createElement('script'); as.type = 'text/javascript'; as.async = true; as.src = "https://d31qbv1cthcecs.cloudfront.net/atrk.js"; var s = document.getElementsByTagName('script')[0];s.parentNode.insertBefore(as, s); })();
	</script>
	<noscript><img src="https://d5nxst8fruw4z.cloudfront.net/atrk.gif?account=InfVh1aUXR00ax" style="display:none" height="1" width="1" alt="" /></noscript>
	<!-- End Alexa Certify Javascript -->
</div>
<!--footer js e-->
	
<!--城市列表 -------------s--->
<c:if test="${fromType != 'detail'}">
<div id="city-content"></div>
<div class="layout-cityCountryPop" style="display:none;">
        <small>支持中文/拼音/简拼输入</small>
        <ul class="nav-citypop cfx"><li class="tabCur">热门</li><li>亚洲</li><li>美洲</li><li>欧洲</li><li>大洋洲</li><li>非洲</li></ul>
        <div class="tabCont list-countrypop show">
        	<a data="全部|-1" href="javascript:void(0);">全部</a>
            <a data="新加坡，新加坡|73" href="javascript:void(0);">新加坡</a>
            <a data="首尔，首尔特别市，韩国|274" href="javascript:void(0);">首尔</a>
            <a data="曼谷，泰国|359" href="javascript:void(0);">曼谷</a>
            <a data="普吉岛，普吉府，泰国|725" href="javascript:void(0);">普吉岛</a>
            <a data="东京，东京都，日本|228" href="javascript:void(0);">东京</a>
            <a data="吉隆坡，雪兰莪州，马来西亚|315" href="javascript:void(0);">吉隆坡</a>
            <a data="清迈，泰国|623" href="javascript:void(0);">清迈</a>
            <a data="巴厘岛，印度尼西亚|723" href="javascript:void(0);">巴厘岛</a>
            <a data="哥打京那巴鲁，沙巴州，马来西亚|1393" href="javascript:void(0);" title="哥打京那巴鲁">哥打京那巴鲁</a>
            <a data="济州岛，韩国|737" href="javascript:void(0);">济州岛</a>
            <a data="兰卡威，吉打州，马来西亚|1225" href="javascript:void(0);">兰卡威</a>
            <a data="芭堤雅，春武里府，泰国|622" href="javascript:void(0);">芭堤雅</a>
            <!-- <a data="大阪，日本|219" href="javascript:void(0);">大阪</a> -->
            <a data="苏梅岛，素叻他尼，泰国|1229" href="javascript:void(0);">苏梅岛</a>
            <a data="巴黎，法兰西岛大区，法国|192" href="javascript:void(0);">巴黎</a>
            <a data="胡志明市，越南|301" href="javascript:void(0);">胡志明市</a>
            <a data="暹粒，柬埔寨|1369" href="javascript:void(0);">暹粒</a>
            <a data="悉尼，新南威尔士，澳大利亚|501" href="javascript:void(0);">悉尼</a>
            <a data="纽约，纽约州，美国|633" href="javascript:void(0);">纽约</a>
            <a data="长滩岛，菲律宾|1391" href="javascript:void(0);">长滩岛</a>
            <a data="洛杉矶，加利福尼亚州，美国|347" href="javascript:void(0);">洛杉矶</a>
            <!--<div class="list-cityCountryPop-news cfx">
                <h3>热门机场</h3>
                <a data="Seoul|仁川国际机场，韩国|3357|s|airport" href="javascript:void(0);">仁川国际机场</a><a data="Singapore|新加坡樟宜国际机场，新加坡|3013|s|airport" href="javascript:void(0);" title="新加坡樟宜国际机场">新加坡樟宜国际机场</a><a data="Bangkok|素万那普国际机场，泰国|17048|s|airport" href="javascript:void(0);">素万那普国际机场</a><a data="Kuala Lumpur|吉隆坡国际机场，马来西亚|5313|s|airport" href="javascript:void(0);">吉隆坡国际机场</a><a data="Bali|伍拉·赖国际机场，印度尼西亚|5847|s|airport" href="javascript:void(0);">伍拉·赖国际机场</a><a data="Tokyo|成田机场，日本|3306|s|airport" href="javascript:void(0);">成田机场</a><a data="Siem Reap|暹粒-吴哥国际机场，柬埔寨|3756|s|airport" href="javascript:void(0);" title="暹粒-吴哥国际机场">暹粒-吴哥国际机场</a><a data="Paris|巴黎夏尔·戴高乐机场，法国|3234|s|airport" href="javascript:void(0);" title="巴黎夏尔·戴高乐机场">巴黎夏尔·戴高乐机场</a><a data="London|伦敦希思罗机场，英国|7619|s|airport" href="javascript:void(0);">伦敦希思罗机场</a>
            </div>-->
        </div>
        <div class="tabCont list-citypop">
            <a href="javascript:void(0);" data="巴厘岛|723">巴厘岛</a>
            <a href="javascript:void(0);" data="芭堤雅|622">芭堤雅</a>
            <a href="javascript:void(0);" data="长滩岛|1391">长滩岛</a>
            <a href="javascript:void(0);" data="大阪|219">大阪</a>
            <a href="javascript:void(0);" data="东京|228">东京</a>
            <a href="javascript:void(0);" data="哥打京那巴鲁|1393">哥打京那巴鲁</a>
            <a href="javascript:void(0);" data="胡志明市|301">胡志明市</a>
            <a href="javascript:void(0);" data="吉隆坡|315">吉隆坡</a>
            <a href="javascript:void(0);" data="济州岛|737">济州岛</a>
            <a href="javascript:void(0);" data="兰卡威|1225">兰卡威</a>
            <a href="javascript:void(0);" data="曼谷|359">曼谷</a>
            <a href="javascript:void(0);" data="皮皮岛|1228">皮皮岛</a>
            <a href="javascript:void(0);" data="普吉岛|725">普吉岛</a>
            <a href="javascript:void(0);" data="清迈|623">清迈</a>
            <a href="javascript:void(0);" data="首尔|274">首尔</a>
            <a href="javascript:void(0);" data="苏梅岛|1229">苏梅岛</a>
            <a href="javascript:void(0);" data="暹粒|1369">暹粒</a>
            <a href="javascript:void(0);" data="新加坡|73">新加坡</a>
        </div>
        <div class="tabCont list-citypop">
            <a href="javascript:void(0);" data="奥兰多|1187">奥兰多</a>
            <a href="javascript:void(0);" data="波士顿|703">波士顿</a>
            <a href="javascript:void(0);" data="多伦多|461">多伦多</a>
            <a href="javascript:void(0);" data="关岛|753">关岛</a>
            <a href="javascript:void(0);" data="华盛顿|676">华盛顿</a>
            <a href="javascript:void(0);" data="旧金山|313">旧金山</a>
            <a href="javascript:void(0);" data="拉斯维加斯|675">拉斯维加斯</a>
            <a href="javascript:void(0);" data="洛杉矶|347">洛杉矶</a>
            <a href="javascript:void(0);" data="蒙特利尔|759">蒙特利尔</a>
            <a href="javascript:void(0);" data="墨西哥城|691">墨西哥城</a>
            <a href="javascript:void(0);" data="纽约|633">纽约</a>
            <a href="javascript:void(0);" data="塞班岛|4081">塞班岛</a>
            <a href="javascript:void(0);" data="圣保罗|415">圣保罗</a>
            <a href="javascript:void(0);" data="圣地亚哥|698">圣地亚哥</a>
            <a href="javascript:void(0);" data="温哥华|476">温哥华</a>
            <a href="javascript:void(0);" data="西雅图|511">西雅图</a>
            <a href="javascript:void(0);" data="夏威夷-欧胡岛|3783">夏威夷-欧胡岛</a>
            <a href="javascript:void(0);" data="芝加哥|549">芝加哥</a>
        </div>
        <div class="tabCont list-citypop">
            <a href="javascript:void(0);" data="阿姆斯特丹|176">阿姆斯特丹</a>
            <a href="javascript:void(0);" data="巴黎|192">巴黎</a>
            <a href="javascript:void(0);" data="巴塞罗那|707">巴塞罗那</a>
            <a href="javascript:void(0);" data="柏林|193">柏林</a>
            <a href="javascript:void(0);" data="东伦敦|4209">东伦敦</a>
            <a href="javascript:void(0);" data="法兰克福|250">法兰克福</a>
            <a href="javascript:void(0);" data="佛罗伦萨|687">佛罗伦萨</a>
            <a href="javascript:void(0);" data="罗马|343">罗马</a>
            <a href="javascript:void(0);" data="马德里|357">马德里</a>
            <a href="javascript:void(0);" data="米兰|361">米兰</a>
            <a href="javascript:void(0);" data="莫斯科|366">莫斯科</a>
            <a href="javascript:void(0);" data="慕尼黑|363">慕尼黑</a>
            <a href="javascript:void(0);" data="圣彼得堡|798">圣彼得堡</a>
            <a href="javascript:void(0);" data="斯德哥尔摩|420">斯德哥尔摩</a>
            <a href="javascript:void(0);" data="苏黎世|434">苏黎世</a>
            <a href="javascript:void(0);" data="威尼斯|688">威尼斯</a>
            <a href="javascript:void(0);" data="维也纳|651">维也纳</a>
        </div>
        <div class="tabCont list-citypop">
            <a href="javascript:void(0);" data="基督城|727">基督城</a>
            <a href="javascript:void(0);" data="阿德莱德|1243">阿德莱德</a>
            <a href="javascript:void(0);" data="奥克兰|678">奥克兰</a>
            <a href="javascript:void(0);" data="布里斯班|680">布里斯班</a>
            <a href="javascript:void(0);" data="达尼丁|1297">达尼丁</a>
            <a href="javascript:void(0);" data="皇后镇|3860">皇后镇</a>
            <a href="javascript:void(0);" data="黄金海岸|1210">黄金海岸</a>
            <a href="javascript:void(0);" data="惠灵顿|843">惠灵顿</a>
            <a href="javascript:void(0);" data="霍巴特|1446">霍巴特</a>
            <a href="javascript:void(0);" data="凯恩斯|728">凯恩斯</a>
            <a href="javascript:void(0);" data="堪培拉|679">堪培拉</a>
            <a href="javascript:void(0);" data="坎贝尔镇|5011">坎贝尔镇</a>
            <a href="javascript:void(0);" data="罗托鲁瓦|1807">罗托鲁瓦</a>
            <a href="javascript:void(0);" data="墨尔本|358">墨尔本</a>
            <a href="javascript:void(0);" data="珀斯|681">珀斯</a>
            <a href="javascript:void(0);" data="悉尼|501">悉尼</a>
        </div>
        <div class="tabCont list-citypop">
            <a href="javascript:void(0);" data="比勒陀利亚|848">比勒陀利亚</a>
            <a href="javascript:void(0);" data="达累斯萨拉姆|814">达累斯萨拉姆</a>
            <a href="javascript:void(0);" data="德班|1278">德班</a>
            <a href="javascript:void(0);" data="赫尔格达|3471">赫尔格达</a>
            <a href="javascript:void(0);" data="卡萨布兰卡|809">卡萨布兰卡</a>
            <a href="javascript:void(0);" data="开罗|332">开罗</a>
            <a href="javascript:void(0);" data="开普敦|683">开普敦</a>
            <a href="javascript:void(0);" data="卢克索|730">卢克索</a>
            <a href="javascript:void(0);" data="卢萨卡|816">卢萨卡</a>
            <a href="javascript:void(0);" data="马拉喀什|1360">马拉喀什</a>
            <a href="javascript:void(0);" data="毛里求斯|785">毛里求斯</a>
            <a href="javascript:void(0);" data="内罗毕|825">内罗毕</a>
            <a href="javascript:void(0);" data="沙姆沙伊赫湾 |3472">沙姆沙伊赫湾 </a>
            <a href="javascript:void(0);" data="突尼斯|1280">突尼斯</a>
            <a href="javascript:void(0);" data="亚历山大|1489">亚历山大</a>
            <a href="javascript:void(0);" data="约翰内斯堡|684">约翰内斯堡</a>
        </div>
        <u class="ico-close-cityPop"></u>
    </div>
    <div class="layout-cityCountryPop r0" style="display:none;">
        <small>支持中文/拼音/简拼输入</small>
        <ul class="nav-citypop cfx"><li class="tabCur">热门</li><li>ABCD</li><li>EFGH</li><li>JKLM</li><li>NOPQRS</li><li>TUVWX</li><li>YZ</li></ul>
        <div class="tabCont list-citypop show">
        	<a data="全部|-1" href="javascript:void(0);">全部</a>
            <a data="北京|1" href="javascript:void(0);">北京</a>
            <a data="上海|2" href="javascript:void(0);">上海</a>
            <a data="天津|3" href="javascript:void(0);">天津</a>
            <a data="重庆|4" href="javascript:void(0);">重庆</a>
            <a data="大连|6" href="javascript:void(0);">大连</a>
            <a data="青岛|7" href="javascript:void(0);">青岛</a>
            <a data="西安|10" href="javascript:void(0);">西安</a>
            <a data="南京|12" href="javascript:void(0);">南京</a>
            <a data="苏州|14" href="javascript:void(0);">苏州</a>
            <a data="杭州|17" href="javascript:void(0);">杭州</a>
            <a data="厦门|25" href="javascript:void(0);">厦门</a>
            <a data="成都|28" href="javascript:void(0);">成都</a>
            <a data="深圳|30" href="javascript:void(0);">深圳</a>
            <a data="广州|32" href="javascript:void(0);">广州</a>
            <a data="三亚|43" href="javascript:void(0);">三亚</a>
            <a data="香港|58" href="javascript:void(0);">香港</a>
            <a data="济南|144" href="javascript:void(0);">济南</a>
            <a data="宁波|375" href="javascript:void(0);">宁波</a>
            <a data="沈阳|451" href="javascript:void(0);">沈阳</a>
            <a data="武汉|477" href="javascript:void(0);">武汉</a>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>A</s>
                <a href="javascript:void(0);" data="阿坝|21958">阿坝</a>
                <a href="javascript:void(0);" data="阿克苏|21379">阿克苏</a>
                <a href="javascript:void(0);" data="阿拉善|21863">阿拉善</a>
                <a href="javascript:void(0);" data="阿勒泰|21951">阿勒泰</a>
                <a href="javascript:void(0);" data="阿里|97">阿里</a>
                <a href="javascript:void(0);" data="安康|171">安康</a>
                <a href="javascript:void(0);" data="安庆|2310">安庆</a>
                <a href="javascript:void(0);" data="鞍山|178">鞍山</a>
                <a href="javascript:void(0);" data="安顺|179">安顺</a>
                <a href="javascript:void(0);" data="安阳|21877">安阳</a>
                <a href="javascript:void(0);" data="澳门|59">澳门</a>
            </p>
            <p>
                <s>B</s>
                <a href="javascript:void(0);" data="白城|1116">白城</a>
                <a href="javascript:void(0);" data="百色|1140">百色</a>
                <a href="javascript:void(0);" data="白沙|21025">白沙</a>
                <a href="javascript:void(0);" data="白山|3886">白山</a>
                <a href="javascript:void(0);" data="白银|1541">白银</a>
                <a href="javascript:void(0);" data="保定|185">保定</a>
                <a href="javascript:void(0);" data="宝鸡|112">宝鸡</a>
                <a href="javascript:void(0);" data="保山|197">保山</a>
                <a href="javascript:void(0);" data="保亭|54">保亭</a>
                <a href="javascript:void(0);" data="包头|141">包头</a>
                <a href="javascript:void(0);" data="巴彦淖尔|3887">巴彦淖尔</a>
                <a href="javascript:void(0);" data="巴中|3966">巴中</a>
                <a href="javascript:void(0);" data="北海|189">北海</a>
                <a href="javascript:void(0);" data="北京|1">北京</a>
                <a href="javascript:void(0);" data="蚌埠|182">蚌埠</a>
                <a href="javascript:void(0);" data="本溪|1155">本溪</a>
                <a href="javascript:void(0);" data="毕节|3225">毕节</a>
                <a href="javascript:void(0);" data="滨州|1820">滨州</a>
            </p>
            <p>
                <s>C</s>
                <a href="javascript:void(0);" data="昌江|56">昌江</a>
                <a href="javascript:void(0);" data="沧州|216">沧州</a>
                <a href="javascript:void(0);" data="长春|2754">长春</a>
                <a href="javascript:void(0);" data="常德|201">常德</a>
                <a href="javascript:void(0);" data="昌都|575">昌都</a>
                <a href="javascript:void(0);" data="长沙|206">长沙</a>
                <a href="javascript:void(0);" data="长治|137">长治</a>
                <a href="javascript:void(0);" data="常州|213">常州</a>
                <a href="javascript:void(0);" data="朝阳|211">朝阳</a>
                <a href="javascript:void(0);" data="潮州|215">潮州</a>
                <a href="javascript:void(0);" data="承德|562">承德</a>
                <a href="javascript:void(0);" data="成都|28">成都</a>
                <a href="javascript:void(0);" data="澄迈|20836">澄迈</a>
                <a href="javascript:void(0);" data="郴州|612">郴州</a>
                <a href="javascript:void(0);" data="赤峰|3875">赤峰</a>
                <a href="javascript:void(0);" data="池州|218">池州</a>
                <a href="javascript:void(0);" data="重庆|4">重庆</a>
                <a href="javascript:void(0);" data="崇左|1896">崇左</a>
                <a href="javascript:void(0);" data="滁州|214">滁州</a>
            </p>
            <p>
                <s>D</s>
                <a href="javascript:void(0);" data="大连|6">大连</a>
                <a href="javascript:void(0);" data="儋州|57">儋州</a>
                <a href="javascript:void(0);" data="大庆|231">大庆</a>
                <a href="javascript:void(0);" data="大同|136">大同</a>
                <a href="javascript:void(0);" data="大兴安岭|7663">大兴安岭</a>
                <a href="javascript:void(0);" data="达州|1233">达州</a>
                <a href="javascript:void(0);" data="德阳|237">德阳</a>
                <a href="javascript:void(0);" data="德州|1370">德州</a>
                <a href="javascript:void(0);" data="定安|50">定安</a>
                <a href="javascript:void(0);" data="定西|1021">定西</a>
                <a href="javascript:void(0);" data="迪庆|93">迪庆</a>
                <a href="javascript:void(0);" data="东方|48">东方</a>
                <a href="javascript:void(0);" data="东莞|223">东莞</a>
                <a href="javascript:void(0);" data="东营|236">东营</a>
            </p>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>E</s>
                <a href="javascript:void(0);" data="鄂尔多斯|3976">鄂尔多斯</a>
                <a href="javascript:void(0);" data="恩施|245">恩施</a>
                <a href="javascript:void(0);" data="鄂州|992">鄂州</a>
            </p>
            <p>
                <s>F</s>
                <a href="javascript:void(0);" data="防城港|1677">防城港</a>
                <a href="javascript:void(0);" data="佛山|251">佛山</a>
                <a href="javascript:void(0);" data="抚顺|252">抚顺</a>
                <a href="javascript:void(0);" data="阜新|254">阜新</a>
                <a href="javascript:void(0);" data="阜阳|257">阜阳</a>
                <a href="javascript:void(0);" data="抚州|3884">抚州</a>
                <a href="javascript:void(0);" data="福州|258">福州</a>
            </p>
            <p>
                <s>G</s>
                <a href="javascript:void(0);" data="赣州|268">赣州</a>
                <a href="javascript:void(0);" data="广安|1100">广安</a>
                <a href="javascript:void(0);" data="广元|6869">广元</a>
                <a href="javascript:void(0);" data="广州|32">广州</a>
                <a href="javascript:void(0);" data="贵港|1518">贵港</a>
                <a href="javascript:void(0);" data="桂林|33">桂林</a>
                <a href="javascript:void(0);" data="贵阳|38">贵阳</a>
                <a href="javascript:void(0);" data="固原|321">固原</a>
            </p>
            <p>
                <s>H</s>
                <a href="javascript:void(0);" data="哈尔滨|5">哈尔滨</a>
                <a href="javascript:void(0);" data="海北|7807">海北</a>
                <a href="javascript:void(0);" data="海东|7752">海东</a>
                <a href="javascript:void(0);" data="海口|42">海口</a>
                <a href="javascript:void(0);" data="海南|7794">海南</a>
                <a href="javascript:void(0);" data="邯郸|21517">邯郸</a>
                <a href="javascript:void(0);" data="杭州|17">杭州</a>
                <a href="javascript:void(0);" data="汉中|129">汉中</a>
                <a href="javascript:void(0);" data="鹤壁|951">鹤壁</a>
                <a href="javascript:void(0);" data="河池|3969">河池</a>
                <a href="javascript:void(0);" data="合肥|278">合肥</a>
                <a href="javascript:void(0);" data="鹤岗|1611">鹤岗</a>
                <a href="javascript:void(0);" data="黑河|281">黑河</a>
                <a href="javascript:void(0);" data="衡水|290">衡水</a>
                <a href="javascript:void(0);" data="衡阳|297">衡阳</a>
                <a href="javascript:void(0);" data="河源|693">河源</a>
                <a href="javascript:void(0);" data="菏泽|1074">菏泽</a>
                <a href="javascript:void(0);" data="贺州|4146">贺州</a>
                <a href="javascript:void(0);" data="香港|58">香港</a>
                <a href="javascript:void(0);" data="淮安|577">淮安</a>
                <a href="javascript:void(0);" data="淮北|272">淮北</a>
                <a href="javascript:void(0);" data="怀化|282">怀化</a>
                <a href="javascript:void(0);" data="淮南|287">淮南</a>
                <a href="javascript:void(0);" data="黄冈|3885">黄冈</a>
                <a href="javascript:void(0);" data="黄南|7802">黄南</a>
                <a href="javascript:void(0);" data="黄山|23">黄山</a>
                <a href="javascript:void(0);" data="黄石|292">黄石</a>
                <a href="javascript:void(0);" data="呼和浩特|103">呼和浩特</a>
                <a href="javascript:void(0);" data="惠州|299">惠州</a>
                <a href="javascript:void(0);" data="葫芦岛|1050">葫芦岛</a>
                <a href="javascript:void(0);" data="呼伦贝尔|4255">呼伦贝尔</a>
                <a href="javascript:void(0);" data="湖州|86">湖州</a>
            </p>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>J</s>
                <a href="javascript:void(0);" data="佳木斯|2960">佳木斯</a>
                <a href="javascript:void(0);" data="吉安|21666">吉安</a>
                <a href="javascript:void(0);" data="江门|316">江门</a>
                <a href="javascript:void(0);" data="焦作|1093">焦作</a>
                <a href="javascript:void(0);" data="嘉兴|571">嘉兴</a>
                <a href="javascript:void(0);" data="嘉峪关|326">嘉峪关</a>
                <a href="javascript:void(0);" data="揭阳|956">揭阳</a>
                <a href="javascript:void(0);" data="吉林|159">吉林</a>
                <a href="javascript:void(0);" data="济南|144">济南</a>
                <a href="javascript:void(0);" data="金昌|1158">金昌</a>
                <a href="javascript:void(0);" data="晋城|1092">晋城</a>
                <a href="javascript:void(0);" data="景德镇|305">景德镇</a>
                <a href="javascript:void(0);" data="荆门|1121">荆门</a>
                <a href="javascript:void(0);" data="荆州|328">荆州</a>
                <a href="javascript:void(0);" data="金华|308">金华</a>
                <a href="javascript:void(0);" data="济宁|318">济宁</a>
                <a href="javascript:void(0);" data="晋中|1453">晋中</a>
                <a href="javascript:void(0);" data="锦州|327">锦州</a>
                <a href="javascript:void(0);" data="九江|21135">九江</a>
                <a href="javascript:void(0);" data="酒泉|662">酒泉</a>
                <a href="javascript:void(0);" data="鸡西|157">鸡西</a>
                <a href="javascript:void(0);" data="济源|1454">济源</a>
            </p>
            <p>
                <s>K</s>
                <a href="javascript:void(0);" data="开封|331">开封</a>
                <a href="javascript:void(0);" data="克拉玛依|166">克拉玛依</a>
                <a href="javascript:void(0);" data="昆明|34">昆明</a>
            </p>
            <p>
                <s>L</s>
                <a href="javascript:void(0);" data="来宾|1892">来宾</a>
                <a href="javascript:void(0);" data="莱芜|1452">莱芜</a>
                <a href="javascript:void(0);" data="廊坊|340">廊坊</a>
                <a href="javascript:void(0);" data="兰州|100">兰州</a>
                <a href="javascript:void(0);" data="拉萨|41">拉萨</a>
                <a href="javascript:void(0);" data="乐东|49">乐东</a>
                <a href="javascript:void(0);" data="乐山|345">乐山</a>
                <a href="javascript:void(0);" data="凉山|7537">凉山</a>
                <a href="javascript:void(0);" data="连云港|353">连云港</a>
                <a href="javascript:void(0);" data="聊城|1071">聊城</a>
                <a href="javascript:void(0);" data="辽阳|351">辽阳</a>
                <a href="javascript:void(0);" data="辽源|352">辽源</a>
                <a href="javascript:void(0);" data="丽江|37">丽江</a>
                <a href="javascript:void(0);" data="临沧|1236">临沧</a>
                <a href="javascript:void(0);" data="临汾|139">临汾</a>
                <a href="javascript:void(0);" data="临高|20868">临高</a>
                <a href="javascript:void(0);" data="临沂|569">临沂</a>
                <a href="javascript:void(0);" data="林芝|108">林芝</a>
                <a href="javascript:void(0);" data="丽水|346">丽水</a>
                <a href="javascript:void(0);" data="六盘水|605">六盘水</a>
                <a href="javascript:void(0);" data="柳州|354">柳州</a>
                <a href="javascript:void(0);" data="陇南|7707">陇南</a>
                <a href="javascript:void(0);" data="龙岩|348">龙岩</a>
                <a href="javascript:void(0);" data="娄底|918">娄底</a>
                <a href="javascript:void(0);" data="六安|1705">六安</a>
                <a href="javascript:void(0);" data="漯河|1088">漯河</a>
                <a href="javascript:void(0);" data="洛阳|350">洛阳</a>
                <a href="javascript:void(0);" data="泸州|355">泸州</a>
                <a href="javascript:void(0);" data="吕梁|7631">吕梁</a>
            </p>
            <p>
                <s>M</s>
                <a href="javascript:void(0);" data="马鞍山|1024">马鞍山</a>
                <a href="javascript:void(0);" data="澳门|59">澳门</a>
                <a href="javascript:void(0);" data="茂名|1105">茂名</a>
                <a href="javascript:void(0);" data="眉山|1148">眉山</a>
                <a href="javascript:void(0);" data="梅州|3053">梅州</a>
                <a href="javascript:void(0);" data="绵阳|370">绵阳</a>
                <a href="javascript:void(0);" data="牡丹江|150">牡丹江</a>
            </p>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>N</s>
                <a href="javascript:void(0);" data="南昌|376">南昌</a>
                <a href="javascript:void(0);" data="南充|377">南充</a>
                <a href="javascript:void(0);" data="南京|12">南京</a>
                <a href="javascript:void(0);" data="南宁|380">南宁</a>
                <a href="javascript:void(0);" data="南平|606">南平</a>
                <a href="javascript:void(0);" data="南通|82">南通</a>
                <a href="javascript:void(0);" data="南阳|385">南阳</a>
                <a href="javascript:void(0);" data="那曲|3839">那曲</a>
                <a href="javascript:void(0);" data="内江|1597">内江</a>
                <a href="javascript:void(0);" data="宁波|375">宁波</a>
                <a href="javascript:void(0);" data="宁德|378">宁德</a>
            </p>
            <p>
                <s>P</s>
                <a href="javascript:void(0);" data="盘锦|387">盘锦</a>
                <a href="javascript:void(0);" data="攀枝花|5542">攀枝花</a>
                <a href="javascript:void(0);" data="平顶山|3222">平顶山</a>
                <a href="javascript:void(0);" data="平凉|388">平凉</a>
                <a href="javascript:void(0);" data="萍乡|1840">萍乡</a>
                <a href="javascript:void(0);" data="普洱|3996">普洱</a>
                <a href="javascript:void(0);" data="莆田|667">莆田</a>
                <a href="javascript:void(0);" data="濮阳|1232">濮阳</a>
            </p>
            <p>
                <s>Q</s>
                <a href="javascript:void(0);" data="青岛|7">青岛</a>
                <a href="javascript:void(0);" data="庆阳|404">庆阳</a>
                <a href="javascript:void(0);" data="清远|1422">清远</a>
                <a href="javascript:void(0);" data="秦皇岛|147">秦皇岛</a>
                <a href="javascript:void(0);" data="钦州|1899">钦州</a>
                <a href="javascript:void(0);" data="琼海|52">琼海</a>
                <a href="javascript:void(0);" data="琼中|53">琼中</a>
                <a href="javascript:void(0);" data="齐齐哈尔|149">齐齐哈尔</a>
                <a href="javascript:void(0);" data="七台河|1599">七台河</a>
                <a href="javascript:void(0);" data="泉州|406">泉州</a>
                <a href="javascript:void(0);" data="曲靖|985">曲靖</a>
                <a href="javascript:void(0);" data="衢州|407">衢州</a>
            </p>
            <p>
                <s>R</s>
                <a href="javascript:void(0);" data="日喀则|92">日喀则</a>
                <a href="javascript:void(0);" data="日照|1106">日照</a>
            </p>
            <p>
                <s>S</s>
                <a href="javascript:void(0);" data="三门峡|436">三门峡</a>
                <a href="javascript:void(0);" data="三明|437">三明</a>
                <a href="javascript:void(0);" data="三亚|43">三亚</a>
                <a href="javascript:void(0);" data="上海|2">上海</a>
                <a href="javascript:void(0);" data="商洛|7551">商洛</a>
                <a href="javascript:void(0);" data="商丘|441">商丘</a>
                <a href="javascript:void(0);" data="上饶|411">上饶</a>
                <a href="javascript:void(0);" data="山南|439">山南</a>
                <a href="javascript:void(0);" data="汕头|447">汕头</a>
                <a href="javascript:void(0);" data="汕尾|1436">汕尾</a>
                <a href="javascript:void(0);" data="韶关|422">韶关</a>
                <a href="javascript:void(0);" data="绍兴|22">绍兴</a>
                <a href="javascript:void(0);" data="邵阳|1111">邵阳</a>
                <a href="javascript:void(0);" data="沈阳|451">沈阳</a>
                <a href="javascript:void(0);" data="深圳|30">深圳</a>
                <a href="javascript:void(0);" data="石家庄|428">石家庄</a>
                <a href="javascript:void(0);" data="十堰|452">十堰</a>
                <a href="javascript:void(0);" data="石嘴山|4216">石嘴山</a>
                <a href="javascript:void(0);" data="双鸭山|1617">双鸭山</a>
                <a href="javascript:void(0);" data="朔州|1317">朔州</a>
                <a href="javascript:void(0);" data="四平|440">四平</a>
                <a href="javascript:void(0);" data="松原|1303">松原</a>
                <a href="javascript:void(0);" data="绥化|1128">绥化</a>
                <a href="javascript:void(0);" data="遂宁|1371">遂宁</a>
                <a href="javascript:void(0);" data="随州|1117">随州</a>
                <a href="javascript:void(0);" data="宿迁|1472">宿迁</a>
                <a href="javascript:void(0);" data="宿州|521">宿州</a>
                <a href="javascript:void(0);" data="苏州|14">苏州</a>
            </p>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>T</s>
                <a href="javascript:void(0);" data="塔城|455">塔城</a>
                <a href="javascript:void(0);" data="泰安|454">泰安</a>
                <a href="javascript:void(0);" data="太原|105">太原</a>
                <a href="javascript:void(0);" data="台州|578">台州</a>
                <a href="javascript:void(0);" data="泰州|579">泰州</a>
                <a href="javascript:void(0);" data="唐山|468">唐山</a>
                <a href="javascript:void(0);" data="天津|3">天津</a>
                <a href="javascript:void(0);" data="天水|464">天水</a>
                <a href="javascript:void(0);" data="铁岭|1048">铁岭</a>
                <a href="javascript:void(0);" data="铜川|118">铜川</a>
                <a href="javascript:void(0);" data="通化|456">通化</a>
                <a href="javascript:void(0);" data="通辽|458">通辽</a>
                <a href="javascript:void(0);" data="铜陵|459">铜陵</a>
                <a href="javascript:void(0);" data="铜仁|1227">铜仁</a>
                <a href="javascript:void(0);" data="屯昌|47">屯昌</a>
            </p>
            <p>
                <s>W</s>
                <a href="javascript:void(0);" data="万宁|45">万宁</a>
                <a href="javascript:void(0);" data="潍坊|475">潍坊</a>
                <a href="javascript:void(0);" data="威海|479">威海</a>
                <a href="javascript:void(0);" data="渭南|1030">渭南</a>
                <a href="javascript:void(0);" data="文昌|44">文昌</a>
                <a href="javascript:void(0);" data="文山|20963">文山</a>
                <a href="javascript:void(0);" data="温州|491">温州</a>
                <a href="javascript:void(0);" data="乌海|1133">乌海</a>
                <a href="javascript:void(0);" data="武汉|477">武汉</a>
                <a href="javascript:void(0);" data="芜湖|478">芜湖</a>
                <a href="javascript:void(0);" data="乌兰察布|7518">乌兰察布</a>
                <a href="javascript:void(0);" data="乌鲁木齐|39">乌鲁木齐</a>
                <a href="javascript:void(0);" data="武威|664">武威</a>
                <a href="javascript:void(0);" data="无锡|13">无锡</a>
                <a href="javascript:void(0);" data="五指山|46">五指山</a>
                <a href="javascript:void(0);" data="吴忠|7587">吴忠</a>
                <a href="javascript:void(0);" data="梧州|492">梧州</a>
            </p>
            <p>
                <s>X</s>
                <a href="javascript:void(0);" data="厦门|25">厦门</a>
                <a href="javascript:void(0);" data="西安|10">西安</a>
                <a href="javascript:void(0);" data="香港|58">香港</a>
                <a href="javascript:void(0);" data="湘潭|598">湘潭</a>
                <a href="javascript:void(0);" data="襄阳|6430">襄阳</a>
                <a href="javascript:void(0);" data="咸宁|937">咸宁</a>
                <a href="javascript:void(0);" data="咸阳|111">咸阳</a>
                <a href="javascript:void(0);" data="孝感|1490">孝感</a>
                <a href="javascript:void(0);" data="邢台|947">邢台</a>
                <a href="javascript:void(0);" data="西宁|124">西宁</a>
                <a href="javascript:void(0);" data="新乡|507">新乡</a>
                <a href="javascript:void(0);" data="信阳|510">信阳</a>
                <a href="javascript:void(0);" data="新余|603">新余</a>
                <a href="javascript:void(0);" data="忻州|513">忻州</a>
                <a href="javascript:void(0);" data="西双版纳|35">西双版纳</a>
                <a href="javascript:void(0);" data="宣城|1006">宣城</a>
                <a href="javascript:void(0);" data="许昌|1094">许昌</a>
                <a href="javascript:void(0);" data="徐州|512">徐州</a>
            </p>
        </div>
        <div class="tabCont list-citypop">
            <p>
                <s>Y</s>
                <a href="javascript:void(0);" data="雅安|3277">雅安</a>
                <a href="javascript:void(0);" data="延安|110">延安</a>
                <a href="javascript:void(0);" data="盐城|1200">盐城</a>
                <a href="javascript:void(0);" data="阳江|692">阳江</a>
                <a href="javascript:void(0);" data="阳泉|907">阳泉</a>
                <a href="javascript:void(0);" data="扬州|15">扬州</a>
                <a href="javascript:void(0);" data="烟台|533">烟台</a>
                <a href="javascript:void(0);" data="宜宾|514">宜宾</a>
                <a href="javascript:void(0);" data="宜昌|515">宜昌</a>
                <a href="javascript:void(0);" data="银川|99">银川</a>
                <a href="javascript:void(0);" data="营口|1300">营口</a>
                <a href="javascript:void(0);" data="鹰潭|534">鹰潭</a>
                <a href="javascript:void(0);" data="益阳|1125">益阳</a>
                <a href="javascript:void(0);" data="永州|970">永州</a>
                <a href="javascript:void(0);" data="岳阳|539">岳阳</a>
                <a href="javascript:void(0);" data="榆林|527">榆林</a>
                <a href="javascript:void(0);" data="玉林|1113">玉林</a>
                <a href="javascript:void(0);" data="运城|140">运城</a>
                <a href="javascript:void(0);" data="云浮|3933">云浮</a>
                <a href="javascript:void(0);" data="玉溪|186">玉溪</a>
            </p>
            <p>
                <s>Z</s>
                <a href="javascript:void(0);" data="枣庄|614">枣庄</a>
                <a href="javascript:void(0);" data="张家界|27">张家界</a>
                <a href="javascript:void(0);" data="张家口|550">张家口</a>
                <a href="javascript:void(0);" data="张掖|663">张掖</a>
                <a href="javascript:void(0);" data="漳州|560">漳州</a>
                <a href="javascript:void(0);" data="湛江|547">湛江</a>
                <a href="javascript:void(0);" data="肇庆|552">肇庆</a>
                <a href="javascript:void(0);" data="昭通|555">昭通</a>
                <a href="javascript:void(0);" data="郑州|559">郑州</a>
                <a href="javascript:void(0);" data="镇江|16">镇江</a>
                <a href="javascript:void(0);" data="中山|553">中山</a>
                <a href="javascript:void(0);" data="中卫|556">中卫</a>
                <a href="javascript:void(0);" data="周口|3221">周口</a>
                <a href="javascript:void(0);" data="舟山|19">舟山</a>
                <a href="javascript:void(0);" data="珠海|31">珠海</a>
                <a href="javascript:void(0);" data="驻马店|551">驻马店</a>
                <a href="javascript:void(0);" data="株洲|601">株洲</a>
                <a href="javascript:void(0);" data="淄博|542">淄博</a>
                <a href="javascript:void(0);" data="自贡|544">自贡</a>
                <a href="javascript:void(0);" data="资阳|1560">资阳</a>
                <a href="javascript:void(0);" data="遵义|558">遵义</a>
            </p>
        </div>
        <u class="ico-close-cityPop"></u>
    </div>
</c:if>
<!--城市列表 -------------e--->
<script type="text/javascript" src="http://staticcdn.b5m.com/scripts/you/tabSwitch.js?t=${today}"></script>
<script type="text/javascript" src="http://staticcdn.b5m.com/scripts/you/you_v2.js?t=${today}"></script>
<%-- <script type="text/javascript" src="${rootPath }/js/tabSwitch.js?t=${today}"></script>
<script type="text/javascript" src="${rootPath }/js/you_v2.js?t=${today}"></script> --%>
