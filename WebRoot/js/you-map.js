var mapData = [
	{point:"116.422792|40.009471",title:"浦东南路",price:"888",addr:"浦东南路1008号",star:"四星级/高档",pic:"http://placehold.it/50x50",icon:"../../images/you/map/1.png",size:"25|32",url:"wwww.b5m.com"},
	{point:"116.484289|39.97936",title:"莘庄路",price:"222",addr:"浦东南路1008号",star:"三星级/高档",pic:"http://placehold.it/50x50",icon:"../../images/you/map/2.png",size:"25|32",url:"wwww.b5m.com"},
	{point:"116.454494|39.964011",title:"杨思路8689号",price:"881118",addr:"丰县华山1008号",star:"五星级/高档",pic:"http://placehold.it/50x50",icon:"../../images/you/map/3.png",size:"25|32",url:"wwww.b5m.com"}
];

var centerpoint = {lng:"116.404",lat:"39.915"};

// 初始化地图
function initMap(point,mapData){
	//获取地图容器
	var _map = new BMap.Map("map-container"),
		_centerpoint = new BMap.Point(point.lng,point.lat),
		openInfoWinFuns = [],
		_circle;
	// 初始化地图
	_map.centerAndZoom(_centerpoint, 16);
	_centermarker = new BMap.Marker(_centerpoint);
	_map.addOverlay(_centermarker);

    //添加信息点
	for(i in mapData){
		var _json = mapData[i];
		//添加标注点
		var openInfoWindow = addMarker(_map,_json);
		openInfoWinFuns.push(openInfoWindow);
	}
	//添加控件
	addControl(_map);

	$('#J_addr_list').find('li').each(function(i){
		var $this = $(this);
		$this.click(function() {
			openInfoWinFuns[i]();
		});
	});
}
// 添加标注
function addMarker(map,json){
	//获取点坐标
	var _point = createPoint(json),
		_icon = createIcon(json),
		_marker = new BMap.Marker(_point,{
			icon:_icon
		});
		map.addOverlay(_marker);
	//添加信息窗口
	var _iw = createInfoWin(json);
    var openInfoWindow = function(){
    	_marker.openInfoWindow(_iw);
    }
    _marker.addEventListener("click",openInfoWindow);
    return openInfoWindow;
}

//获取坐标
function createPoint(json){
	var arrpoint = json.point.split("|")
		//经度 
		lng = arrpoint[0],
		//纬度
		lat = arrpoint[1];
	return _point = new BMap.Point(lng,lat);
}
//创建InfoWindow
function createInfoWin(json){
	var	title = json.title,
		price = json.price,
		addr = json.addr,
		star = json.star,
		pic = json.pic,
		url = json.url,
		_html = "<div class='detail cf'>"
		_html += "<a href='" + url + "' target='blank'><img src=" + pic + " class='l' alt='' width='50' height='50'></a><div class='txt'>";
        _html += "<a href='" + url + "' target='bkank' class='title'><span>" + title + "</span></a><strong class='price'>&yen;" + price + "</strong>";
		_html += "<p>地址：<span class='cl-438'>" + addr + "</a></p>";
		_html += "<p>星级：<span class='cl-666'>" + star + "</span></p>";
		_html += "</div></div>"
		infowin = new BMap.InfoWindow(_html);
	return infowin;
}
//创建标注图片
function createIcon(json){
	var _icon_pic = json.icon,
		_size = json.size.split("|"),
		_w = _size[0],
		_h = _size[1],
		_icon = new BMap.Icon(_icon_pic,new BMap.Size(_w,_h),{
			infoWindowAnchor:new BMap.Size(10,0)
		});
	return _icon;
}

//添加控件
function addControl(map){
	var _map = map;
	// 添加滚轮缩放
    _map.enableScrollWheelZoom();
    //添加默认缩放平移控件
    _map.addControl(new BMap.NavigationControl());
}

//选择城市
function Switch(obj){
	this.obj = obj;
}
Switch.prototype.showCity = function(){
	if(!this.obj) return;
	var $obj = $("." + this.obj);
	$obj.css({zIndex:2}).stop(true,true).animate({
		top:32,
		opacity:1
	},400);
}
Switch.prototype.hideCity = function(){
	if(!this.obj) return;
	var $obj = $("." + this.obj);
	$obj.stop(true,true).animate({
		top:52,
		opacity:0,
		zIndex:-1
	},400);
}

$('.select-list').on('click',function(e){
	var $this = $(this),
		$city = $('#J-checked-city'),
		$close = $this.find('.close'),
		switchCity = new Switch("cityselect"),
		$cityItem = $this.find('.cityselect-ul').find('a');
	//隐藏区域选择
	$('.hotel-tip').hide();
	switchCity.showCity();
	//点击关闭按钮
	$close.on('click',function(e){
		switchCity.hideCity();
		e.stopPropagation();
	});
	e.stopPropagation();
	$cityItem.on('click',function(e){
		var _this = $(this),
			txt = _this.text(),
			city_id = _this.data('city');
			console.log(city_id);
		$city.text(txt);
		$('#map_city').val(city_id);
		switchCity.hideCity();
		e.stopPropagation();
	});
	$(document).on('click',function(){
		switchCity.hideCity();
	});
});

