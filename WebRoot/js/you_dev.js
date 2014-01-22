(function() {
	try{
    //酒店的筛选条件
    var ul_hotel_price = $("#ul_hotel_price");
    var ul_hotel_star = $("#ul_hotel_star");
    var hotel_bt_price_ok = $("#hotel_bt_price_ok");
    var hotel_price = $("#hotel_price");
    ul_hotel_price.find('li').on('click', function(e) {
    	$("#hotel_price").val($(this).attr('rv'));
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    });
    ul_hotel_star.find('li').on('click', function(e) {
    	$("#hotel_star").val($(this).attr('rv'));
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    });
    
    hotel_bt_price_ok.on('click', function(e) {
    	if(parseInt($('#hotel_price_s').val(),'10') > 999999){
			$('#hotel_price_s').val(0);
    	}
		if(parseInt($('#hotel_price_e').val(),'10') > 999999){
			$('#hotel_price_e').val(999999);
    	}
    	var sp = $("#hotel_price_s").val();
    	var ep = $("#hotel_price_e").val();
    	
    	if(sp == '' && ep == ''){
    		return;
    	}
    	
    	if(parseInt($('#hotel_price_s').val(),'10') > parseInt($('#hotel_price_e').val(),'10')){
    		$('#hotel_price_s').val(ep);
    		$('#hotel_price_e').val(sp);
    	}
    	var temp = $('#hotel_price_s').val() + "-" + $('#hotel_price_e').val();
    	hotel_price.val(temp);
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    });
    
    //trip的筛选条件
    var bt_days_ok = $("#bt_days_ok");
    var bt_price_ok = $("#bt_price_ok");
    bt_days_ok.on('click', function(e) {
    	if(parseInt($('#text_days').val(),'10') > 999){
    		$('#text_days').val(999);
    	}
    	$("#days").val($("#text_days").val());
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    });
    
    bt_price_ok.on('click', function(e) {
    	if(parseInt($('#price_start').val(),'10') > 999999){
			$('#price_start').val(0);
    	}
		if(parseInt($('#price_end').val(),'10') > 999999){
			$('#price_end').val(999999);
    	}
		var price_start = parseInt($('#price_start').val(),'10');
    	var price_end = parseInt($('#price_end').val(),'10');
    	
    	if($('#price_start').val() =='' && $('#price_end').val() ==''){
    		return;
    	}
    	if(price_start > price_end){
    		$('#price_start').val(price_end);
    		$('#price_end').val(price_start);
    	}
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    });
    
    //搜索框事件绑定
    var header_submit = $("#header-rearch-submit");
    header_submit.on('click', function(e) {
    	var header_text = $("#header-search-key").val();
    	var sflag = $("#from_page_name").val();
    	var city = $("#noresultSelectedCityId").val();
    	//alert(sflag);
    	if(sflag == 'searchresult'){
    		window.location.href="/taoPage_" + city + "_searchresult_" + header_text + "_1__ASC_list";
    	}
    	
    	if(sflag == 'domesticTravel' || sflag == 'abroadTravel' || sflag == 'peripheryTravel'){
    		window.location.href="/taoPage_" + city + "_" + sflag + "_" + header_text + "_1_list";
    	}
    	
    	if(sflag == 'hotelSearchresult'){
    		window.location.href="taoPage_" + city + "_hotelSearchresult_" + header_text + "_1__ASC_list";
    	}
    	
    	if(sflag == 'noteSearchresult'){
    		window.location.href="/taoPage_" + city + "_noteSearchresult_" + header_text + "_1_hotNotes_list";
    	}
    	
    });
    
    //日期改变事件
    $('#J_date1').on('change',function(){
    	$("#currPageNo").val(1);
    	$("#J_order").submit();
    	
        var temp1 = $('#J_date1').val();
        var temp2 = $('#J_date2').val();
        if(temp1 != "" && temp2 != ""){
        	var date1 = temp1.split('/')[2] + temp1.split('/')[0] + temp1.split('/')[1];
            var date2 = temp2.split('/')[2] + temp2.split('/')[0] + temp2.split('/')[1];
            if(temp1 != undefined && temp2!= undefined && date2 >= date1){
            	$("#currPageNo").val(1);
            	$("#J_order").submit();
            }
        }
    });

    $('#J_date2').on('change',function(){
    	var temp1 = $('#J_date1').val();
        var temp2 = $('#J_date2').val();
        if(temp1 != "" && temp2 != ""){
	        var date1 = temp1.split('/')[2] + temp1.split('/')[0] + temp1.split('/')[1];
	        var date2 = temp2.split('/')[2] + temp2.split('/')[0] + temp2.split('/')[1];
	        if(temp1 != undefined && temp2!= undefined && date2 >= date1){
	        	$("#currPageNo").val(1);
	        	$("#J_order").submit();
	        }
        }
    });
    
    //数字校验
    $('#text_days').on('keyup',function(e){
		this.value = this.value.replace(/\D/g,"");
		if(parseInt($('#text_days').val(),'10') > 999){
    		$('#text_days').val(999);
    	}
	});
    
    $('#price_start').on('keyup',function(e){
		this.value = this.value.replace(/\D/g,"");
	});
    
    $('#price_end').on('keyup',function(e){
		this.value = this.value.replace(/\D/g,"");
	});
    
    $('#hotel_price_s').on('keyup',function(e){
		this.value = this.value.replace(/\D/g,"");
	});
    
    $('#hotel_price_e').on('keyup',function(e){
		this.value = this.value.replace(/\D/g,"");
	});
    
    //地图模式
    $('#sub_btn_map').on('click',function(e){
    	var keyWord = $('#hotel_keyword_text').val();
    	var keyWord1 = $('#keyword_inner_text').val();
    	var city = $('#hotelSelectedCityId').val();
    	var city1 = $('#innerSelectedCityId').val();
    	try{
    		keyWord = keyWord.replace(/\s/g,"");
    		keyWord1 = keyWord.replace(/\s/g,"");
    	}catch(e){}
    	
    	//全部的时候
    	if(city == '-1' || city1 == '-1'){
    		alert("请选择城市！");
    		return;
    	}else{
    		//alert("is--->" + isKeyWordInList(keyWord));
    		//true 表示没有在下拉列表中
    		if((keyWord != "" && keyWord != undefined) && city != '-1'){
    			$('#page_mode_map').val("map");
    			$('#hotel_search_form').attr('action','getHotelsForMap.html');
    			$('#hotel_search_form').submit();
    		}
    		
    		if(((keyWord1 != "" && keyWord1 != undefined) && city1 != -1)){
    			$('#page_mode_map').val("map");
    			$('#inner_search_form').attr('action','getHotelsForMap.html');
    			//if(city1 != -1){
    			//$('#inner_search_form').attr('action','getHotelsForMap.html');
    			//}
    			$('#inner_search_form').submit();
    		}
    	}
    });
	}catch(e){}
	
	$('#go_page').on('click', function(e) {
		e.preventDefault();
		goToPage();
	});
	
	$('#page_num').on('keyup', function(e) {
		var temp = $('#page_num').val();
		$('#page_num').val(parseInt(temp,'10'));
		var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) { // enter 键
	    	goToPage();
	    }
	});
})();

//GO 的跳转动作
function goToPage(){
	var page = $('#page_num').val();
	var url = $('#pager_text').val();
	if(url.indexOf('list') != -1){
		url = url + page + ".html";
	}else{
		url = url + page;
	}
	location.href = url;
}

//初始化酒店关键字
function initHotelKeyWords(){
	//清空关键字
	$('#hotel_keyword_text').val("");
	var rootPath = $('#rootPath').val();
	var city = $('#hotelSelectedCityId').val();
	var city1 = $('#innerSelectedCityId').val();
	var city2 = $('#map_city').val();
	var finalCity = city;
	/*var tag = $('#curPageTag').val();
	var tag1 = $('#curMapTag').val();
	if(tag == 'hotel'){
		city = $('#innerSelectedCityId').val();
	}
	if(tag1 == 'hotelmap'){
		city = $('#map_city').val();
	}
	
	if(city == undefined || city == ""){
		city = 2;
	}*/
	var tag = "";
	//内页的city
	if(city1 != undefined && city1 != ""){
		finalCity = city1;
		tag = "hotel";
	}
	
	if(city2 != undefined && city2 != ""){
		finalCity = city2;
	}
	
	var moreUrl = rootPath + "/address.html?city=" + finalCity;
	var map_url = $("#rootPath").val() + "/mapKeyWorkList.html?city=" + finalCity;
	$.ajax({
        type: "GET",
        url: map_url,
        dataType:"json",
        success: function(httpObj){
        	var hotel_uns = httpObj.uns.split("#");
        	var uns_html = "<strong>大学</strong>";
        	
        	var hotel_metro = httpObj.metro.split("#");
        	var metro_html = "<strong>地铁</strong>";
        	
        	var hotel_hotel = httpObj.hotel.split("#");
        	var hotel_html = "<strong>酒店</strong>";
        	
        	var hotel_bud = httpObj.bud.split("#");
        	var bud_html = "<strong>地标</strong>";
        	
        	var hotel_biz = httpObj.biz.split("#");
        	var biz_html = "<strong>商区</strong>";
        	
        	var hotel_dist = httpObj.dist.split("#");
        	var dist_html = "<strong>行政区</strong>";
        	
        	var showOrNot = 'block';
        	
        	if(httpObj.uns == "" && httpObj.bud == "" && httpObj.bud == ""){
        		showOrNot = 'none';
        	}
        	
        	//uns
        	for(var i = 0;i < 10;i++){
        		var item = hotel_uns[i];
        		if(item != ""){
        			uns_html = uns_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            }
        	uns_html = uns_html.substr(0,uns_html.length-1) + "<span><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	//metro
        	$.each(hotel_metro, function(i, item) {
        		if(item != ""){
        			metro_html = metro_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            });
        	metro_html = metro_html.substr(0,metro_html.length-1) + "<span><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	//hotel
        	$.each(hotel_hotel, function(i, item) {
        		if(item != ""){
        			hotel_html = hotel_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            });
        	hotel_html = hotel_html.substr(0,hotel_html.length-1) + "<span style='display:" + showOrNot + "'><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	//bud
        	$.each(hotel_bud, function(i, item) {
        		if(item != ""){
        			bud_html = bud_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            });
        	bud_html = bud_html.substr(0,bud_html.length-1) + "<span><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	//biz
        	$.each(hotel_biz, function(i, item) {
        		if(item != ""){
        			biz_html = biz_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            });
        	biz_html = biz_html.substr(0,biz_html.length-1) + "<span><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	//dist
        	$.each(hotel_dist, function(i, item) {
        		if(item != ""){
        			dist_html = dist_html + "<a href=\"javascript:void(0)\">" + item + "</a>|";
        		}
            });
        	dist_html = dist_html.substr(0,dist_html.length-1) + "<span style='display:" + showOrNot + "'><a href=\"" + moreUrl + "\">更多></a></span>";
        	
        	if(httpObj.uns != ""){
        		$("#hotel_uns").html(uns_html);
        		$("#hotel_uns").show();
        	}else{
        		$("#hotel_uns").hide();
        	}
        	
        	/*
        	if(httpObj.metro != ""){
        		$("#hotel_metro").html(metro_html);
        		$("#hotel_metro").show();
        	}else{
        		$("#hotel_metro").hide();
        	}
        	*/
        	$("#hotel_metro").hide();
        	
        	if(httpObj.hotel != ""){
        		$("#hotel_hotel").html(hotel_html);
        	}
        	
        	if(httpObj.bud != ""){
        		$("#hotel_bud").html(bud_html);
        		$("#hotel_bud").show();
        	}else{
        		$("#hotel_bud").hide();
        	}
        	
        	if(httpObj.biz != ""){
        		$("#hotel_biz").html(biz_html);
        		$("#hotel_biz").show();
        	}else{
        		$("#hotel_biz").hide();
        	}
        	
        	if(httpObj.dist != ""){
        		$("#hotel_dist").html(dist_html);
        		$("#hotel_dist").show();
        	}else{
        		$("#hotel_dist").hide();
        	}
        	
        	$(".hotel-cat > a").each(function(e){
            	$(this).on('click',function(){
            		$('#hotel_keyword_text').val($(this).html());
            		$('#page_mode_map').val("map");
            		if($(this).parent().attr('id') == 'hotel_metro'){
            			//$('#page_mode_map').val("metro");
            		}
            		if($(this).parent().attr('id') == 'hotel_hotel'){
            			$('#page_mode_map').val("search");
            		}
            		if(tag == 'hotel'){
            			$('#keyword_inner_text').val($(this).html());
            			if($('#page_mode_map').val() != "search"){
            				$('#inner_search_form').attr('action','getHotelsForMap.html');
            			}
            			$('#inner_search_form').submit();
            		}else{
            			if($('#page_mode_map').val() != "search"){
            				$('#hotel_search_form').attr('action','getHotelsForMap.html');
            			}
            			$('#hotel_search_form').submit();
            		}
            		$('.hotel-tip').hide();
            	});
            });
		}
    });
}

function initHotelsForMap(){
	
}