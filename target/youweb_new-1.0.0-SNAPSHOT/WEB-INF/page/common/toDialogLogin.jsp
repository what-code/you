<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="title" content="登录 - 帮5买 - 轻松发现，轻松选择！" />
<title>用户登录&nbsp;-&nbsp;帮5买&nbsp;-&nbsp;轻松发现，轻松选择！</title>
<link rel="stylesheet" type="text/css" href="${rootPath }/css/ucenter.css?t=${today}">
<link rel="stylesheet" type="text/css" href="${rootPath }/css/ucenter_login_dlg.css?t=${today}">
</head>
<body>
	<div class="login_dlg">
		<div class="login_cont">
			<div class="login_top">
			    <div class="logo-tao"></div>
				<a class="close close_dialog" href="javascript:void(0)"></a>
			</div>
			<div class="login_midd">
				<span class="info_text"> 请先登录或注册，会员可在购买后获得一定的丰厚积分返利！<br />
					积分就是<strong>现金</strong>哦！
				</span>
				<ul class="filter-price login_tab">
					<li class="" data-type="register"><a href="javascript:void(0)" hidefocus="true">注册</a></li>
					<li class="cur" data-type="login"><a href="javascript:void(0)" hidefocus="true">登录</a></li>
				</ul>
<!-- 				<a class="login_cancel close_dialog" href="javascript:void(0)">不要返利，直接去购物&gt;</a> -->
				<div class="login_tab_cont">
					<div class="login">
						<div class="login-form login-form-verify" style="margin-top:35px">
							<form method="post" id="loginForm" novalidate="novalidate">
								<input type="hidden" value="6" name="userType">
								<input id="dialog_redirectUrl" type="hidden" value="${redirectUrl}">
								<div id="uNameRow" class="rows" style="display: none;">
									<label>用户名：</label>
									<span class="value">
										<input type="text" value="" maxlength="45" name="userName" class="text" />
									</span>
									<span class="errorBox info" style="display: none;margin-top: 0px;"></span>
								</div>
								<div class="rows">
									<label>邮箱：</label>
									<span class="value">
										<input type="text" value="" maxlength="45" name="email" class="text" />
									</span>
									<span class="errorBox info" style="display: none;margin-top: 0px;"></span>
								</div>
								<div class="rows">
									<label>密码：</label>
									<span class="value">
										<input type="password" name="password" class="text" />
									</span>
									<span class="errorBox info" style="display: none;margin-top: 0px;"></span>
								</div>
								<div id="verify" class="rows rows-verify" style="display: none;">
									<label>验证码：</label>
									<span class="value" style="width: 75px; float: left">
										<input type="text" maxlength="5" name="code" class="text" style="width: 63px;height:30px" />
									</span>
									<img style="width: 70px; height: 35px;border:none;" src="${ucServer}/validateCode.do" class="nums" id="code">
									<span class="btn"><a href="javascript:void(0)" id="refreshCode">换一张</a></span>
									<span class="errorBox info" style="display: none;margin-top: 0px;"></span>
								</div>
								<div class="submit_rows">
									<a href="javascript:void(0);" class="saveBtn" id="login_btn">登录</a>
									<a href="javascript:void(0);" class="saveBtn" id="register_btn" style="display: none">注册</a>
                                   <span class="info" id="serror" style="float:left;margin:8px 0 0 10px;width:170px;overflow:hidden;text-overflow:ellipsis;_display:inline;color:red;"></span>
								</div>
							</form>
						</div>
						<%-- <div class="line"></div>
						<div class="platform-link">
							<a href="${ucServer }/user/user/auth.htm?type=1&refererUrl=${redirectUrl}"><img src="${ucServer}/images/ucenter/platform_icon_qq.png" />QQ登录</a>
							<a href="${ucServer }/user/user/auth.htm?type=2&refererUrl=${redirectUrl}"><img src="${ucServer}/images/ucenter/platform_icon_weibo.png" />微博登录</a>
							<a href="${ucServer }/user/user/auth.htm?type=3&refererUrl=${redirectUrl}"><img src="${ucServer}/images/ucenter/platform_icon_taobao.png" />淘宝登录</a>
						</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="tb-frame">
		<iframe id="frame_yzf" width="100%" scrolling="no" height="100%" frameborder="0" class="iframe" marginheight="0" marginwidth="0" rel="nofollow" src="about:blank"></iframe>
	</div>
	<div class="iframe_mask"></div>
	<script type="text/javascript" src="http://staticcdn.b5m.com/static/scripts/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${rootPath}/js/jquery.validate.min.js?t=${today}"></script>
	<script src="${rootPath}/js/toDialogLogin.js"></script>
	<script type="text/javascript">
		var rootPath = '${ucServer}';
	   
		setTimeout(function(){
		    $('iframe').attr('src','${showUrl}');
		},300);
		$('input.text').focus(function() {
            if (!$(this).hasClass('text_error')) {
                $(this).addClass('text_focus');
            }
        }).blur(function() {
            $(this).removeClass('text_focus');
        });
	</script>
	<!-- Google Analytics -->
	<script>
	  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	  ga('create', 'UA-42463137-1', 'b5m.com');
	  ga('send', 'pageview');
	</script>
	<script type="text/javascript">
	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-27469593-1']);
	  _gaq.push(['_setDomainName', 'b5m.com']);
	  _gaq.push(['_trackPageview']);
	
	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	</script>
	<!-- End Google Analytics -->
	<div style="display: none;">
		<script type="text/javascript" src="http://s17.cnzz.com/stat.php?id=3865108&web_id=3865108"></script>
		<script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
			document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F12f262996fc81e00f6abfc009a515ac9' type='text/javascript'%3E%3C/script%3E"));
		</script>
	</div>
</body>
</html>
