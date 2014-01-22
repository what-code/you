var currentPageNo = 1;
var totalPages = 1;
var mapCity;
var mapMode;
var pageMode;
var mapKeyword;
var noResultJson = $('#defaultJson').val();

var tempCityCenter = $('#cityCenterPoints').val();
tempCityCenter = tempCityCenter.split("#");
var cityCenter = jQuery.parseJSON('{"lng":"'
		+ tempCityCenter[2] + '","lat":"' + tempCityCenter[1]
		+ '"}');
try {
	currentPageNo = $('#page_curr_no').val();
} catch (e) {
}

function updateCityCenter(){
	tempCityCenter = $('#cityCenterPoints').val();
	tempCityCenter = tempCityCenter.split("#");
	cityCenter = jQuery.parseJSON('{"lng":"'
			+ tempCityCenter[2] + '","lat":"' + tempCityCenter[1]
			+ '"}');
}

// 分页信息
function pageInfo(totalPages, currentPageNo) {
	currentPageNo = parseInt(currentPageNo, '10');
	var pageData = '<a href="javascript:void(0)" id="page_no_pre">上一页</a>';
	//总页数是否大于3
	if (totalPages > 3) {
		//是否是第一页
		if (currentPageNo > 1) {
			// 最后一页
			if ((currentPageNo + 1) > totalPages) {
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ (currentPageNo - 2) + '">' + (currentPageNo - 2)
						+ '</a>';
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ (currentPageNo - 1) + '">' + (currentPageNo - 1)
						+ '</a>';
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ currentPageNo + '" class="cur">' + currentPageNo
						+ '</a>';
			} else {
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ (currentPageNo - 1) + '">' + (currentPageNo - 1)
						+ '</a>';
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ currentPageNo + '" class="cur">' + currentPageNo
						+ '</a>';
				pageData = pageData
						+ '<a href="javascript:void(0)" id="page_no_'
						+ (currentPageNo + 1) + '">' + (currentPageNo + 1)
						+ '</a>';
			}
		} else {
			pageData = pageData
					+ '<a href="javascript:void(0)" id="page_no_1" class="cur">1</a>';
			pageData = pageData
					+ '<a href="javascript:void(0)" id="page_no_2">2</a>';
			pageData = pageData
					+ '<a href="javascript:void(0)" id="page_no_3">3</a>';
		}
	} else {
		if (totalPages > 0) {
			for ( var i = 1; i <= totalPages; i++) {
				if (currentPageNo == i) {
					pageData = pageData
							+ '<a href="javascript:void(0)" id="page_no_' + i
							+ '" class="cur">' + i + '</a>';
				} else {
					pageData = pageData
							+ '<a href="javascript:void(0)" id="page_no_' + i
							+ '">' + i + '</a>';
				}

			}
		}
	}
	if (totalPages > 0) {
		pageData = pageData
				+ '<a href="javascript:void(0)" id="page_no_next">下一页</a>';
	}
	return pageData;
}

// 绑定分页事件 及星级事件
function bindPageEvent() {
	$("#page_mt20 > a").each(
			function(e) {
				if ($(this).attr('id') != 'page_no_pre'
						&& $(this).attr('id') != 'page_no_next') {
					$(this).on('click', function() {
						var tempId = $(this).attr('id');
						currentPageNo = $(this).html();
						// 清空分页信息
						$('#page_mt20').html('');
						// 初始化数据及分页
						initHotelMapData();
						// 修改分页样式
						$("#page_mt20 > a").each(function(e) {
							if ($(this).attr('id') == tempId) {
								$(this).attr("class", "cur");
							} else {
								$(this).attr("class", "");
							}
						});
					});
				}

				// 上一页
				if ($(this).attr('id') == 'page_no_pre') {
					$(this).on('click', function() {
						currentPageNo = parseInt(currentPageNo, '10') - 1;
						initHotelMapData();
					});
				}
				// 下一页
				if ($(this).attr('id') == 'page_no_next') {
					$(this).on('click', function() {
						currentPageNo = parseInt(currentPageNo, '10') + 1;
						initHotelMapData();
					});
				}
			});

	// 星级事件
	$(".check-list list-h cf > li").each(function(e) {
		$(this).on('click', function() {

		});
	});
}

// 完善分页信息
function rebuildPageInfo(totalPages, currentPageNo, centerpoint, responseStatus) {
	if (currentPageNo == 1) {
		$("#page_mt20 a:first-child").remove();
	}

	if (currentPageNo == totalPages) {
		$("#page_mt20 a:last-child").remove();
	}

	// 无结果页的推荐
	if (responseStatus == '-1') {
		$('#div_tips_warning').show();
		$('#div_addr_list_noresult').show();
	} else {
		$('#div_addr_list_noresult').hide();
	}

	if (centerpoint.lng == "null" || centerpoint.lat == "null") {
		// mapKeyword = $('#defaultKeyWord').val();
		// $('#map_keyword').val(mapKeyword);
		// initHotelMapData();
	}

	$('#hotel_keyword_text').val(mapKeyword);
}

//初始化无结果页的地图坐标
function initNoresultArr(){
	$("#div_noresult_txt > input").each(function(e){
		
	});
}

/*
 * function isNotKeyWordInList(keyWord){ var flag = true; $(".hotel-trip
 * a").each(function(e){ var temp = $(this).html();
 * if($(this).parent().attr('id') != 'hotel_hotel' && temp == keyWord){ flag =
 * false; return false; } }); return flag; }
 */

// 初始化页面数据
function initHotelMapData() {
	mapCity = $('#map_city').val();
	mapMode = $('#map_mode').val();
	mapKeyword = $('#map_keyword').val();
	pageMode = $('#page_mode_map').val();
	var server = $('#rootPath').val();
	var tempKey = encodeURIComponent(mapKeyword);
	if (currentPageNo == undefined || currentPageNo == '') {
		currentPageNo = 1;
	} else {

	}
	var url = server + "/getHotelsForMapByAjax.html?city=" + mapCity
			+ "&keyword=" + tempKey + "&currPageNo=" + currentPageNo;
			$.ajax({
				type : "GET",
				url : url,
				dataType : "json",
				success : function(result) {
					var mapData = new Array();
					var centerpoint = jQuery.parseJSON('{"lng":"'
							+ result.longitude + '","lat":"' + result.latitude
							+ '"}');
					var listData = "";
					var responseStatus = result.status;
					var inmap  = result.inmap;
					//true 市级，false:街道级别
					var inFalg = true;
					if(inmap == '0' || responseStatus == '-1'){
						inFalg = false;
					}
					
					//城市改变的时候，修改城市的中心坐标
					$('#cityCenterPoints').val(result.cityCenter);
					updateCityCenter();
					
					// page Info------
					// 给总页数 赋值
					totalPages = result.totalPages;

					$("#page_total_no").val(totalPages);
					var pageData = pageInfo(totalPages, currentPageNo);
					$('#page_mt20').html(pageData);
					bindPageEvent();
					rebuildPageInfo(totalPages, currentPageNo, centerpoint,
							responseStatus);
					// 循环列表数据
							$.each(
									result.all,
									function(i, item) {
										var pid = item.id;
										var longitude = item.longitude;
										var latitude = item.latitude;

										var title = item.name;
										var fullTitle = item.name;
										if(title.length > 12){
											title = title.substr(0, 12) + "...";
										}
										
										var price = item.salesPrice;
										var tempPrice = price;

										var pic = item.imgurl;
										var clickUrl = server
												+ "/pageDetailed_-1_hotel_"
												+ pid + ".html";

										if (price == undefined || price < 1) {
											tempPrice = '查看详情';
											price = '查看详情&nbsp;&nbsp;&nbsp;&nbsp;';
										} else {
											tempPrice = '&yen;' + tempPrice
													+ '起';
											price = price + '&nbsp;&nbsp;&nbsp;&nbsp;';
										}
										var addr = item.address;
										var fullAddr = item.address;
										if(addr.length > 12){
											addr = addr.substr(0, 10) + "...";
										}
										if (item.address == null
												|| item.address == 'null') {
											addr = "未知";
										}

										var star = "三星以下";
										if (item.levelInfo == '3') {
											star = "三星";
										} else if (item.levelInfo == '4') {
											star = "四星";
										} else if (item.levelInfo == '5') {
											star = "五星";
										}

										var icon = "../../images/you/map/"
												+ (i + 1) + ".png";
										var size = "25|32";

										var jsonStr = '{"point":"' + longitude
												+ '|' + latitude
												+ '","shortTitle":"' + title
												+ '","fullTitle":"' + fullTitle
												+ '","price":"' + price
												+ '","shortAddr":"' + addr
												+ '","fullAddr":"' + fullAddr
												+ '","star":"' + star
												+ '","pic":"' + pic
												+ '","icon":"' + icon
												+ '","size":"' + size
												+ '","url":"' + clickUrl + '"}';
										var obj = jQuery.parseJSON(jsonStr);
										mapData.push(obj);
										var tempData = '<li class="addr-list__item"><div><a href="'
												+ clickUrl
												+ '" class="pic" target="_blank"><img src="'
												+ pic
												+ '" alt="'
												+ title
												+ '" width="50" height="50" target="_blank"></a><div class="txt"><a href="'
												+ clickUrl
												+ '" class="cl-000" target="_blank">'
												+ title
												+ '</a><p>'
												+ star
												+ '</p><p>位于：<a href="'
												+ clickUrl
												+ '" class="cl-438" target="_blank">'
												+ addr
												+ '</a></p></div></div><i class="icon icon-marker">'
												+ (i + 1)
												+ '</i><div class="price">'
												+ tempPrice + '</div></li>';
										listData = listData + tempData;
									});
					$('#J_addr_list').html(listData);
					
					if(responseStatus == '-1'){
						//无结果页
						initMap(cityCenter,eval(noResultJson),inFalg);
					}else if(inmap == '0'){
						//不在列表中的关键字
						initMap(cityCenter,mapData,inFalg);
					}else{
						initMap(centerpoint,mapData,inFalg);
					}
				},
				error : function(result) {
					// alert('error');
				},
				complete : function(result) {
					// 无中心点的情况
					if (result.longitude == null || result.latitude == null) {
						var defaultKeyWord = $('#defaultKeyWord').val();
						if (mapKeyword == '') {
							// location.href = server + "/taoPage_" + mapCity +
							// "_hotelSearchresult_" + defaultKeyWord +
							// "_1_search";
						}
						// location.href = server + "/taoPage_" + mapCity +
						// "_hotelSearchresult_" + mapKeyword + "_1_search";
					}
				}
			});
}

$('#hotel_keyword_text').focus(function() {
	var city = $('#map_city').val();
	if (city == '-1') {
		$('.hotel-tip').hide();
		return;
	}
	initHotelKeyWords();
	$('.hotel-tip').show();
	$('#cityselect_hot_div').css({
		opacity : 0
	});
}).on('keyup', function() {
	if ($(this).val().length) {
		$('.hotel-tip').hide();
	}
});

$('.hotel-tip,#hotel_keyword_text').on('click', function(e) {
	e.stopPropagation();
});

$(document).on('click', function() {
	$('.hotel-tip').hide();
});

$('#map_search_btn').on('click', function() {
	var temp = $('#map_city').val();
	var keyword = $('#hotel_keyword_text').val();
	keyword = keyword.replace(/\s/g, "");
	if (temp != -1 && keyword != "") {
		$('#hotel_search_form').submit();
	}
});


/**
 * 获取字符串的长度,包括汉字和字符
 * @param str
 * @returns {Number}
 */
function getStrLength(str) {
	var l = 0;
	var a = s.split("");
	for ( var i = 0; i < a.length; i++) {
		if (a[i].charCodeAt(0) < 299) {
			l++;
		} else {
			l += 2;
		}
	}
	return l;
}

// 初始化数据
initHotelMapData();