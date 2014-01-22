$(function() {
	$('.floor-you-trip ul.fl a').on('click', function(e) {
		e.preventDefault();
		var cityId = $(this).attr('data-city');
		var type = $('.floor-you-trip div.fr a.current').attr('data-type');
		if (undefined != cityId && type != undefined) {
			$(this).parents("ul").find('.current').removeClass();
			var $par = $(this).parent();
			$par.removeClass();
			$par.addClass('current');
			indexPostAjax('/index-travel.html', type, cityId, 'travel');
		} else {
			location.href = $(this).attr('href');
		}
	});

	$('.floor-you-trip div.fr a').on('click', function(e) {
		e.preventDefault();
		var cityId = $('.floor-you-trip ul.fl li.current a').attr('data-city');
		var type = $(this).attr('data-type');
		if (undefined != cityId && type != undefined) {
			$(this).parents("div.fr").find('.current').removeClass();
			$(this).removeClass();
			$(this).addClass('current');
			indexPostAjax('/index-travel.html', type, cityId, 'travel');
		} else {
			location.href = $(this).attr('href');
		}
	});

	$('.floor-you-hotel ul.fl a').on('click', function(e) {
		e.preventDefault();
		var cityId = $(this).attr('data-city');
		if (undefined != cityId) {
			$(this).parents("ul").find('.current').removeClass();
			var $par = $(this).parent();
			$par.removeClass();
			$par.addClass('current');
			indexPostAjax('/index-hotel.html', '', cityId, 'hotel');
		} else {
			location.href = $(this).attr('href');
		}
	});

	var indexPostAjax = function(url, type, cityId, post) {
		$.ajax({
			url : url,
			type : 'post',
			dataType : 'json',
			data : {
				selectedCityId : cityId,
				ajaxType : type
			}
		}).done(function(data) {
			if (post == 'hotel') {
				$('.hotel-list').empty();
				var obj = eval(data.all);
				$.each(obj, function(i, n) {
					$('.hotel-list').append(hotelTag(n));
				});
			} else {
				$('.trip-list').empty();
				var obj = eval(data.all);
				$.each(obj, function(i, n) {
					$('.trip-list').append(travelTag(n));
				});
			}
		});
	};
	//热门城市初始化
	//indexHotCity();
	//热门旅游类型初始化
	//indexHotType();
	//旅游初始化
	//indexPostAjax('/index-travel.html', 0, -1, 'travel');
	//酒店初始化
	//indexPostAjax('/index-hotel.html', '', -1, 'hotel');
});

//热门城市初始化
function indexHotCity(){
	var url = "/indexHotCity.html";
	var html = '<ul class="cfx">';
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
	}).done(function(data) {
		$.each(data,function(i, item) {
			var clazz = '';
			if(i % 5 == 0 && i > 0){
				clazz = 'class="last"';
			}
			var temp = ' <li ' + clazz + '> ' +
            	'<a target="_blank" href="' + item.cityUrl + '">' + 
            	'<img src="' + item.imageUrl + '" alt="" title="" width="131" height="131" onerror="this.src=http://localhost:8080/images/you/120x76.jpg"/>' + 
            	'<span class="c1">' + item.cityName + '</span>' +
            	'</a>' +
            	'</li>';
			html = html + temp;
		});
		//html = html + '</ul>';
		$('.you-hot-city').html(html);
	});
};

//热门城市初始化
function indexHotType(){
	var url = "/indexHotType.html";
	var html = '';
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
	}).done(function(data) {
		$.each(data,function(i, item) {
			var clazz = '';
			if(i == 0){
				clazz = 'class="fl"';
			}else{
				clazz = 'class="fr"';
			}
			var temp = '<a target="_blank" href="' + item.cityUrl + '" ' + clazz + '>' + 
            '<img src="' + item.imageUrl + '" alt="" title=""  onerror="this.src=http://localhost:8080/images/you/120x76.jpg"/>' + 
            '<span class="c-pink">' + item.cityName + '</span></a>';
			html = html + temp;
		});
		//alert(html);
		$('#you-hot-type-div').html(html);
	});
};

var travelTag = function(data) {
	return '<li><a href="/detail_0_'
			+ data.id
			+ '.html" target="_blank"><img src="'
			+ data.imgurl
			+ '" alt="" title="" width="171" height="108" onerror="this.src=\'..\/images\/you\/120x76.jpg\';"/><span><i class="iprice">￥'
			+ data.salesPrice + '</i></span></a><p><a href="/detail_0_' + data.id + '.html" target="_blank">' + data.name + '</a></p></li>';
};

var hotelTag = function(data) {
	return '<li><a href="/detail_1_'
			+ data.id
			+ '.html" class="hotel-img fl" target="_blank"><img src="'
			+ data.imgurl
			+ '" alt="" title="" width="120" height="76" onerror="this.src=\'..\/images\/you\/120x76.jpg\';"/></a><div class="hotel-info fr"><h6><a href="/detail_1_'
			+ data.id
			+ '.html" target="_blank">'
			+ data.name
			+ '</a></h6><p class="hotel-star"><i class="star"><i class="star-inner" style="width:'
			+ data.levelInfo * 20
			+ '%;"></i></i></p><p class="hotel-address"><a href="/detail_1_'+ data.id + '.html" target="_blank">'
			+ data.district + '</a></p><span class="pos-price">￥<em>'
			+ data.salesPrice + '</em>起</span></div></li>';
};
