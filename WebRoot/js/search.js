$(function() {
	$('.travel').on('click', function(e) {
		var keyWords = $(this).siblings('input').val();
		keyWords = trim(keyWords);
		keyWords = getJsParams(keyWords);
		var city = $('input#travel_city').val();
		if (city == '') {
			return;
		}
		if (keyWords == '') {
			location.href = "/list_all_0_0_0_0_" + city + "_1.html";
		} else {
			location.href = "/searcher?keyword=" + encodeURIComponent(keyWords) + "&type=0&city="+city+"&page=1";
		}
	});
	
	//直接翻页事件 add by duanyu
	$('#go_page').on('click', function(e) {
		e.preventDefault();
		var temp = $('#page_num').val();
		if(trim(temp) == ""){
			return;
		}
		goToPage();
	});
	
	$('#page_num').on('keyup', function(e) {
		var temp = $('#page_num').val();
		if(trim(temp) != ""){
			if(isNaN(parseInt(temp,'10'))){
				$('#page_num').val(1);
			}else{
				$('#page_num').val(parseInt(temp,'10'));
			}
		}else{
			return;
		}
		var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) { //enter
	    	goToPage();
	    }
	});
	
	//酒店搜索事件 add by duanyu
	$('#J_tab_inner .bt-youSearch').on('click', hotelSearch);
	
	//攻略搜索事件
	$('#noteSearch .bt-youSearch').on('click', noteSearch);
	
	//搜索框的回车事件
	$('#trip_keyword').on('keyup',function(e){
		setTimeout(function(){
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) { //enter
		    	$('#btn_strip').click();
		    }
		},300);
	});
	
	$('#hotelKeyWord0').on('keyup',function(e){
		setTimeout(function(){
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) { //enter
		    	$('#btYouSearch0').click();
		    }
		},300);
	});
	
	$('#hotelKeyWord1').on('keyup',function(e){
		setTimeout(function(){
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) { //enter
		    	$('#btYouSearch1').click();
		    }
		},300);
	});
	
	$('#noteKeyWord').on('keyup',function(e){
		setTimeout(function(){
			var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) { //enter
		    	noteSearch();
		    }
		},300);
	});
	
	$('.you-start-city > a').on('click',function(e){
		var city = $('#travel_city').val();
		var keyword = $(this).html();
		var url = "/searcher?keyword=" + keyword + "&type=0&city=" + city + "&page=1";
		location.href = url;
	});
});

//酒店搜索
function hotelSearch(){
	//海外酒店
	var city = $('#hidden_city1').val();
	var level = $('#hidden_level1').val();
	var dateStart = $('#hotelStart1').val();
	var dateEnd = $('#hotelEnd1').val();
	var keyword = $('#hotelKeyWord1').val();
	var hwOrNot = 0;
	//国内酒店
	if($(this).attr('id') == 'btYouSearch0'){
		city = $('#hidden_city0').val();
		level = $('#hidden_level0').val();
		dateStart = $('#hotelStart0').val();
		dateEnd = $('#hotelEnd0').val();
		keyword = $('#hotelKeyWord0').val();
	}else{
		hwOrNot = 1;
		if(trim(city) == ''){
			alert("提示:请选择目的地!");
			return;
		}
	}
	
	if(trim(city) != "" || trim(keyword) != ""){
		keyword = encodeURI(keyword);
		var tempUrl = "/searcher?keyword=" + keyword + "&type=1&city=" + city + "&hs=" + dateStart + "&he=" + dateEnd + "&hl=" + level + "&hw=" + hwOrNot + "&page=1";
		var url = $('#rootPath').val() + tempUrl;
		location.href = url;
	}
}

//攻略搜索
function noteSearch(){
	var keyword = $('#noteKeyWord').val();
	var city = $('#hidden_city0').val();
	if(trim(keyword) != ""){
		keyword = encodeURI(keyword);
		var tempUrl = "/searcher?keyword=" + keyword + "&type=2&city=" + city + "&page=1";
		var url = $('#rootPath').val() + tempUrl;
		location.href = url;
	}
}

//GO 的跳转动作
function goToPage(){
	var page = parseInt($('#page_num').val());
	var url = $('#pager_text').val();
	var total = parseInt($('#totalPage').val());
	if(total < page){
		return;
	}
	if(url.indexOf('list') != -1){
		url = url + page + ".html";
	}else{
		url = url + page;
	}
	location.href = url;
}

/*
 * 去掉字符串前后空格 @param keyWord 没转换前字符串 @return 去掉前后空格后的字符串
 */
function trim(keyWord) {
	var regex = /^\s*|\s*$/g;
	var trimstr = keyWord.replace(regex, "");
	return trimstr;
}

// 获得转义的js参数
function getJsParams(str) {
	var reg = /[']/g;
	var rep = str.replace(reg, "\\'");
	rep = rep.replace(/\&/g, "&amp;");
	var reg = /["]/g;
	var repStr = rep.replace(reg, "&quot;");
	return repStr;
}