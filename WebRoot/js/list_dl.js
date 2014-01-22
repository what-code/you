$(function() {
	var server = $('#auto_fill_server').val();
	//trip
	if ($('#auto_fill_chn').val() == 'domesticTravel' || $('#auto_fill_chn').val() == 'abroadTravel' || $('#auto_fill_chn').val() == 'peripheryTravel') {
		$('#keyword_inner_text').autoFill(server + '/allAutoFill.htm','tourp',function(){
			$('#inner_search_form').submit();
		});
	}
	
	//hotel
	if ($('#auto_fill_chn').val() == 'hotel' || $('#auto_fill_chn').val() == 'hotelSearchresult') {
		$('#keyword_inner_text').autoFill(server + '/allAutoFill.htm','hotel',function(){
			$('#inner_search_form').submit();
		});
	}
});


