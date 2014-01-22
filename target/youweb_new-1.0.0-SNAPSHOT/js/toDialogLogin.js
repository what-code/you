var callbackIsEamilUse = {
	resultData : true,
	requestP : function(value, type) {
		$.ajax({
			type : 'post',
			dataType : "jsonp",
			data : type + "=" + value + "&jsonpCallback=successIsEamilUse",
			async : false,
			url : rootPath + '/user/info/data/isNameOrEmailUse.do'
		});
	},
	resultFunction : function() {
		return this.resultData;
	}
};
var successIsEamilUse = function(data) {
	callbackIsEamilUse.resultData = eval(data).code == 0 ? true : false;
};
$(function(){
	
	var $loginForm = $('#loginForm');
	var loginValidate;
	jQuery.validator.addMethod("codeLength", function(value, element) {
		return value.length == 4?true:false;
	}, "验证码长度必须4位");
	jQuery.validator.addMethod("isEmail", function(value, element) {
		callbackIsEamilUse.requestP(value,'email');
		return callbackIsEamilUse.resultFunction();
	}, "邮箱已经被占用，请更换邮箱");
	jQuery.validator.addMethod("isUName", function(value, element) {
		callbackIsEamilUse.requestP(value,'userName');
		return callbackIsEamilUse.resultFunction();
	}, "用户名已使用，请更换用户名");
	
	loginValidate = $loginForm.validate({
			rules:{
				userName:{
					required: true,
					rangelength: [4,15],
					isUName: true
				},
				email:{
					required: true,
					email: true
				},
				password:{
					required: true,
					rangelength:[6,15]
				},
				code:{
					required: true,
					codeLength:true
				}
			},
			messages:{
				userName:{
					required: "用户名不能为空",
					rangelength: "用户名长度必须介于{0}和{1}之间"
					
				},
				email:{
					required: "Email不能为空",
					email: "邮箱地址格式不正确"
					
				},
				password:{
					required: "密码不能为空",
					rangelength: "密码长度必须介于{0}和{1}之间"
				},
				code:{
					required: "验证码不能为空"
				}
			},
			 errorElement: "span",
			 errorClass:'text_error',
			 errorPlacement: function(error, element) {
				 $('#serror').empty();
				 element.attr('class','text text_error');
				 var errorBox = element.parent().parent().find('.errorBox').empty();
				 errorBox.css('display','inline');
				 errorBox.append('<img class="ui-icon ui-icon-error" src="'+rootPath+'/images/ucenter/blank.png" />&nbsp;'+error.html());
			 },
			 success: function(label,d) {
					var parent = $(d).parent().parent().find('.errorBox').empty();
					parent.css('display','none');
					/*if($(d).attr('name') != 'code')
						parent.append('<img class="ui-icon ui-icon-right" src="'+rootPath+'/images/ucenter/blank.png">');*/
			},
			onfocusout: function(element) { $(element).valid(); }			
		});
	
	
	$('#refreshCode').click(function(){
		refreshCode();
	});
	$('#login_btn').click(function(e){
		e.preventDefault();
		submitHandler();
	});
	$('#register_btn').click(function(e){
		e.preventDefault();
		submitHandler();
	});
	$('.close_dialog').click(function(e){
		e.preventDefault();
		 closeDialog();
	});
	$('input[name="code"],input[name="password"]').keydown(function(event){
		if(event.keyCode == 13)
			submitHandler();
	});
	initData();
	function submitHandler(){
		initData();
		if($loginForm.valid()){
			
			$.ajax({
				type : "post",
				dataType : "jsonp",
				url : $loginForm.attr('action'),
				data : $loginForm.serialize()
						+ "&jsonpCallback=success1",
				async : false
			});
		}
	}
	
	function initData(){
		 $('#serror').empty();
		$loginForm.find('.errorBox').empty().css('display','none');
		$loginForm.find('input').each(function(){
			$(this).removeClass('text_error');
		});
		var type = $('.login_tab > .cur').attr('data-type');
		if(type == 'login'){
			$loginForm.attr('action',rootPath+'/user/user/data/login.htm');
			$loginForm.find('input[name="email"]').rules("remove", "isEmail");
			$('#verify').css("display","none");
			$('.login-form').css('margin-top',35);
			$('#uNameRow').hide();
		}else if(type == 'register'){
			$loginForm.attr('action',rootPath+'/user/info/data/register.htm');
			$loginForm.find('input[name="email"]').rules("add", {
				'isEmail':true
			});
			$('#verify').css("display","block");
			$('.login-form').css('margin-top',10);
			$('#uNameRow').show();
		}else{
			alert('出错啦！！');
		}
	}
	
	$(".login_tab a").bind('click', function(e) {
	    e.preventDefault();
	    var parent = $(this).parent();
	    if (parent.hasClass('cur')) {
	        return false;
	    }
	    parent.parent().find('.cur').removeClass();
	    parent.addClass('cur');
	    $('.submit_rows a').hide();
	    $("#" + parent.attr('data-type') + '_btn').show();
	    
	    if(parent.attr('data-type') == "register"){
	    	$('.login').addClass('register');
	    	$('.login_tab_cont').css('height','225px');
	    }else{
	    	$('.login').removeClass('register');
	    	$('.login_tab_cont').css('height','207px');
	    }
	    
	    initData();
	});
});

function closeDialog(){
	var redirectUrl = $('#dialog_redirectUrl').val();
	if(redirectUrl == null || redirectUrl == undefined || redirectUrl == ''){
		window.location.reload(true);
	}else{
		window.location =  redirectUrl;
	}
}

function refreshCode(){
	$('#code').attr('src',rootPath+'/validateCode.do?f='+Math.random());
}

var success1 = function(datas) {
	var data = eval(datas);
	if(data.ok){
		if (data.code == '10011') {
			var count = 0;
			var $scripts = $('<span>' + data.data + '</span>').find('script');
			$scripts.each(function() {
				$.getScript($(this).attr('src'), function() {
					count = count + 1;
				});
			});
			var si = setInterval(function() {
				if (count == $scripts.size()) {
					window.location.href = $('#dialog_redirectUrl').val();
					clearInterval(si);
				}
			}, 500);
		} else if (data.code == '10012') {
			window.location.href = data.data + '&loginReferer=' + encodeURIComponent($('#dialog_redirectUrl').val());
		} else {
			closeDialog(); 
		}
	}else{
		$.ajax({
			type:'post',
			dataType : "jsonp",
			url:rootPath+'/user/user/data/loginNum.do',
			data : "email="+$('input[name="email"]').val()
					+ "&jsonpCallback=successNum",
			async : false
		});
		$('#serror').empty().append('<img src="'+rootPath+'/images/ucenter/blank.png" class="ui-icon ui-icon-error">&nbsp;'+data.data);
		refreshCode();
	}
};

var successNum = function(datas){
	var data = eval(datas);
	var ok = data.ok;
	var num = data.data;
	if(ok && parseInt(num) < 3){
		$('#verify').css("display","none");
		$('.login-form').css('margin-top',35);
	}else{
		$('#verify').css("display","block");
		$('.login-form').css('margin-top',10);
	}
};