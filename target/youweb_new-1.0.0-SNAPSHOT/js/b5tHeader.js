function pageEvent(pageNo) {
	var newPageNo = pageNo;
	var reg = /^[1-9][0-9]*$/;
	if(newPageNo == ''){
		return ;
	}else if (!reg.test(newPageNo)) {
		newPageNo = 1;
	}
	var curPageTag = $('#curPageTag').val();
	var keyWord = $('#keyWord').val();
	var totalPages = $('#totalPages').val();
	var locate = $("#cityKey").val();
	var selectedLocate = $("#selectedCityId").val();
	var tripSelectedLocate = $("#tripSelectedCityId").val();
	var hotelSelectedLocate = $("#hotelSelectedCityId").val();
	var innerSelectedCityId = $("#innerSelectedCityId").val();
	
	var order = $("input[name='order']").val();
	var sort = $("input[name='sort']").val();
	
	var pageMode = $("#page_mode").val();
	if(curPageTag == 'hotelSearchresult'){
		var tempMode = $('#page_mode_map').val();
		if(tempMode == 'metro' || tempMode == 'map'){
			pageMode = tempMode;
		}
	}
	
	//trip
	var type0 = $("#type0").val();
	var days = $("#days").val();
	var type1 = $("#type1").val();
	var price_start = $("#price_start").val();
	var price_end = $("#price_end").val();
	var J_date1 = $("#J_date1").val();
	var J_date2 = $("#J_date2").val();
	
	//hotel
	var hotel_price = $("#hotel_price").val();
	var hotel_star = $("#hotel_star").val();
	
	if (parseInt(newPageNo) > parseInt(totalPages)) {
		newPageNo = totalPages;
	}

	var urlWare = '/taoPage_' + locate + '_' + curPageTag + '_';
	//alert(curPageTag);
	//note
	if(curPageTag=='noteSearchresult'){
		urlWare = '/taoPage_' + selectedLocate + '_' + curPageTag + '_';
	}
	//trip
	if(curPageTag=='searchresult'){
		urlWare = '/taoPage_' + tripSelectedLocate + '_' + curPageTag + '_';
	}
	//hotel
	if(curPageTag=='hotelSearchresult'){
		urlWare = '/taoPage_' + hotelSelectedLocate + '_' + curPageTag + '_';
	}
	//inner
	if(curPageTag=='domesticTravel'||curPageTag=='abroadTravel'||curPageTag=='peripheryTravel'||curPageTag=='hotel'){
		urlWare = '/taoPage_' + innerSelectedCityId + '_' + curPageTag + '_';
	}
	
	//if (keyWord != "" && keyWord != null){
	urlWare = urlWare + encodeURIComponent(keyWord) + '_' + newPageNo;
	//}else{
	//	urlWare = urlWare + newPageNo;
	//}
	if((curPageTag=='searchresult'||curPageTag=='hotelSearchresult')&&(order!=undefined&&sort!=undefined)){
		urlWare = urlWare + '_' + order + '_' + sort;
		//trip 条件过滤
		if(!isBlankOrNull(type0) || !isBlankOrNull(days) || !isBlankOrNull(type1) || !isBlankOrNull(price_start) || !isBlankOrNull(price_end) || !isBlankOrNull(J_date1)
				 || !isBlankOrNull(J_date2)){
			urlWare = urlWare + '_' + type0 + '_' + days + '_' + type1 + '_' + price_start + '_' + price_end + '_' + J_date1 + '_' + J_date2;
		}
		//hotel 条件过滤
		if(!isBlankOrNull(hotel_price) || !isBlankOrNull(hotel_star)){
			urlWare = urlWare + '_' + hotel_price + '_' + hotel_star;
		}
	}
	if(curPageTag=='noteSearchresult' && order!=undefined){
		urlWare = urlWare + '_' + order;
	}
	
	//page_mode
	if(pageMode!=undefined){
		urlWare = urlWare + "_" + pageMode;
	}else{
		urlWare = urlWare + "_list";
	}
	
	location.href = urlWare;
}

//判断是否为空
function isBlankOrNull(str){
	if(str != "" && str != null){
		return false;
	}
	return true;
}

$(function() {
	
	var jump = function(cityKey){
		var href = location.href;
		var curPageTag = $("#curPageTag").val();
		if (curPageTag == 'index') {
			href = $("#rootPath").val() + "/city/" + cityKey;
		}else if(curPageTag == 'searchresult'){
			href = $("#rootPath").val() + "/taoPage_" + cityKey + "_domesticTravel";
		} else if(curPageTag == 'hotelSearchresult'){
			href = $("#rootPath").val() + "/taoPage_" + cityKey + "_hotel";
		}else{
			href = $("#rootPath").val() + "/taoPage_" + cityKey + "_" + curPageTag;
		}
		location.href = href;
	};
	$("#J_jumb").bind('jump',function(e,cityKey){
		jump(cityKey);
	});
	$('.city-outer-wrap a[data-city]').click(function(e){
		e.preventDefault();
		jump($(this).attr('data-city'));
	});
	$('#page_num').on('keyup', function(e) {
		this.value = this.value.replace(/\D/g, "");
	});
	$('#go_page').on('click', function(e) {
		e.preventDefault();
		var val = $('#page_num').val();
		pageEvent(val);
	});
});
