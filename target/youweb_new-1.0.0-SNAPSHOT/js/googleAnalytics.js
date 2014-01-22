var _gaq=_gaq||[];

    
function googleAnalyticsInit(login){
	if (typeof (login) == "undefined") {
		login = "false";
	}else{
		login = String(login);
	}
	
	_gaq.push(['_setAccount', 'UA-27469593-1']);
	_gaq.push(['_setDomainName', 'b5m.com']);
	_gaq.push(['_addOrganic', 'so.360.cn', 'q']);
	_gaq.push(['_addOrganic', 'so.com', 'q']);
	_gaq.push(['_addOrganic', 'sogou', 'query']);
	_gaq.push(['_addOrganic', 'soso', 'w']);
	_gaq.push(['_addOrganic', 'youdao', 'q']);
	_gaq.push(['_setCustomVar', 1, 'login', login, 2]); 
	_gaq.push(['_trackPageview']);


	var ga = document.createElement("script");
	ga.type = "text/javascript";
	ga.async = true;
	ga.src = ("https:" == document.location.protocol ? "https://ssl": "http://www") + ".google-analytics.com/ga.js";
	var s = document.getElementsByTagName("script")[0];
	s.parentNode.insertBefore(ga, s);
}    

function google_PushInfo(event,page,type,info,price,samePage){
	
	
	var undefinedIndex = 0;
	
	if(typeof (info) == "undefined"){
		undefinedIndex++;
	}
	
	if(typeof (price) == "undefined"){
		undefinedIndex++;
	}else{
		price = String(price);
		var index = price.indexOf('.');
		if(index>0){
			price = price.substring(0,index);
		}
		try {
			price = Number(price);
		} catch (e) {
			price = 0;
		}
	}
	
	
	if(typeof (samePage) == "undefined"){
		undefinedIndex++;
	}
	
	//alert(event+","+page+","+type+","+info+","+price+","+samePage);
	
	if(undefinedIndex==0){
		_gaq.push([event, page,type,info,price,samePage]);
	}else if(undefinedIndex==1){
		_gaq.push([event, page,type,info,price]);
	}else if(undefinedIndex==2){
		_gaq.push([event, page,type,info]);
	}else if(undefinedIndex==3){
		_gaq.push([event, page,type]);
	}
}

