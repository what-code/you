$(function() {
	var server = $('#auto_fill_server').val();
	var city = $("#cityKey").val();
	
	try{
		/*
		//hotel
		$('#hotel_keyword_text').insertDownList(server, ['hotel', '', '', '']).on('writein',function() {
			$('#hotel_search_form').submit();
		});
	
		//trip
		$('#trip_keyword_text').insertDownList(server, ['tourp', '', '', '']).on('writein',function() {
			$('#trip_search_form').submit();
		});
		
		//note
		$('#note_keyword_text').insertDownList(server, ['tourguide', '', '', '']).on('writein',function() {
			$('#note_search_form').submit();
		});
		*/
		//hotel
		$('#hotel_keyword_text').autoFill(server + '/allAutoFill.htm','hotel',function(){
			$('#hotel_search_form').submit();
		});
	
		//trip
		$('#trip_keyword_text').autoFill(server + '/allAutoFill.htm','tourp',function(){
			$('#trip_search_form').submit();
		});
		
		//note
		$('#note_keyword_text').autoFill(server + '/allAutoFill.htm','tourguide',function(){
			$('#note_search_form').submit();
		});
	}catch(e){
		
	}
	
	var sflag = $("#from_page_name").val();
	var sfalg1 = false;
	if(sflag == 'searchresult' || sflag == 'domesticTravel' || sflag == 'abroadTravel' || sflag == 'peripheryTravel'){
		$('#header-search-key').autoFill(server + '/allAutoFill.htm','tourp');
		sfalg1 = true;
	}
	
	if(sflag == 'hotelSearchresult'){
		$('#header-search-key').autoFill(server + '/allAutoFill.htm','hotel');
		sfalg1 = true;
	}
	
	if(sflag == 'noteSearchresult'){
		$('#header-search-key').autoFill(server + '/allAutoFill.htm','tourguide');
		sfalg1 = true;
	}
	
	if(!sfalg1){
		$('#header-search-key').autoFill(server + '/allAutoFill.htm','tourp');
		sfalg1 = true;
	}
});