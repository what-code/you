$(function(){
	function AutoSwitch(obj)
	{
		var oParent = $(obj),
			aLi = oParent.find('.tab-hd li'),
			oBd = oParent.find('.tab-bd-container'),
			aBoxLen = oBd.find('.tab-pannel').length,
			oBoxWidth = parseInt(oParent.find('.tab-pannel').outerWidth(),10),
			allWidth = aBoxLen*oBoxWidth +'px',
			timer = null,
			iNow = 0;

		oBd.css({'width':allWidth});

		aLi.click(function(){
			clearInterval(timer);
			iNow = $(this).index();
			tab();
		});

		function tab()
		{
			aLi.removeClass('cur');
			aLi.eq(iNow).addClass('cur');
			if(aLi !==1) oBd.animate({'left':-oBoxWidth*iNow});
		}

		function timerInner()
		{
			iNow++;
			if(iNow == aLi.size())
			{
				iNow = 0;
			}
			tab();
		}

		timer = setInterval(timerInner,5000);

		oParent.hover(function(){
			clearInterval(timer);
		},function(){
			timer = setInterval(timerInner,5000);
		});

	}
	
	$('.tankuang').each(function(){
//		$(this).attr('href','/toDialogLogin.do?showUrl='+encodeURIComponent($(this).attr('clickUrl'))+'&url='+encodeURIComponent($(this).attr('sourceUrl')));
		$(this).attr('href',$(this).attr('sourceUrl'));
	});
	
	funClickTotal();
	
});

var countPageNumber = typeof countPageNumber === 'undefined'?'':countPageNumber;
//商品分类显示
function loadWare(urlWare,flag,curPageTag){
	$.ajax({
		url : urlWare,
		type : 'get',
		dataType:'json'
	})
	.done(function(data){
		$('.filter-page').remove();
		$(".home-mod").remove();
		$(".page").remove();
		if(curPageTag!='taoTomorrowPreview'){
			$('#wrap-all').html(data);
		}else{
			$('.col-main').html(data);
		}
	});
}

function funClickTotal(){
	var data_cur = $('#curPageTag').val();
	if(data_cur=='hotelSearchresult' || data_cur=='searchresult' || data_cur==''){
		mousedownModAll(data_cur);
	}else{
		mousedownMod(data_cur);
	}
}

function mousedownModAll(data_cur){
	//点击商品后抢购数+1
	$('body').on( "mousedown",'.mod > h3 a,.mod > .pic a,.mod > .opt a', function(e) {
		if (e.which != 3) {
			var url = "";
			if(data_cur!='hotelSearchresult' || data_cur!='hotel' || data_cur!='index'){
				url = "/youTotalClick.do?t="+ new Date().getTime();
			}else if(data_cur!='index'){
				url = "/hotelTotalClick.do?t="+ new Date().getTime();
			}else if(data_cur=="index"){
				url = "index";
			}
			var id = $(this).parents('div.mod').attr('data-id');
			$.ajax({
				type : "post",
				url : url,
				data : {
					"clikcId" : id
				}
			});
		}
	});
}

function mousedownMod(data_cur){
	//点击商品后抢购数+1
	$('body').on( "mousedown",'.mod > .pic a,.mod > .opt a', function(e) {
		if (e.which != 3) {
			var url = "";
			var id = $(this).parents('div.mod').attr('data-id');
			var data_tag = $(this).parents('div.mod').attr('data-tag');
			
			if(data_cur!='hotelSearchresult'&&data_cur!='hotel'&&data_cur!="index"){
				url = "/youTotalClick.do?t="+ new Date().getTime();
			}else if(data_cur!="index"){
				url = "/hotelTotalClick.do?t="+ new Date().getTime();
			}else if(data_cur=="index"&& typeof data_tag!="undefined"&& data_tag=="hotel"){
				url = "/hotelTotalClick.do?t="+ new Date().getTime();
			}else{
				url = "/youTotalClick.do?t="+ new Date().getTime();
			}
			
			$.ajax({
				type : "post",
				url : url,
				data : {
					"clikcId" : id
				}
			});
		}
	});
}
