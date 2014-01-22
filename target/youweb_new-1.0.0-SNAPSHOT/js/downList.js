/*b5m.js start*/
// JavaScript Document
var newtrue=true;
var dl_op;
function addCssByLink(url){
	
	if(newtrue&&!document.getElementById("newlink")){
		var doc=document;
		var link=doc.createElement("link");
		link.setAttribute("rel", "stylesheet");
		link.setAttribute("type", "text/css");
		link.setAttribute("href", url);
		link.id="newlink";
		document.body.appendChild(link);
		newtrue=false;
	};
};

function label(labelId){
	labelId = labelId?labelId:"label";
	var _LABEL = document.getElementById(labelId);
	if(!_LABEL)return false;
	var _LABEL_VALUE = _LABEL.innerHTML;	
	switch (_LABEL_VALUE)
		{
			case '购物':
//				_LABEL.innerHTML = '输入想买的东西，购物享优惠';
				_LABEL.innerHTML = '快速搜索 全网比价';
//				_LABEL.innerHTML = '网购从这里开始 搜商品 搜商城';
				break;	
				
			case '逛街':
				_LABEL.innerHTML = '输入关键词，发现购物最流行';
				break;
				
			case '团购':
				_LABEL.innerHTML = '发现附近的团购';
				break;
				
			case '搜票':
				_LABEL.innerHTML = '找最近的演出，轻松在线购';
				break;
			
			case '专题':
				_LABEL.innerHTML = '输入关键字，寻找您喜欢的专题';
				break;
				
			case '品牌':
				_LABEL.innerHTML = '输入品牌名称，发现大牌商品';
				break;

			case '色界':
				_LABEL.innerHTML = '在27,640,365张色界美图中搜索~';
				break;

			case '商品':
//				_LABEL.innerHTML = '输入想买的东西 购物享优惠';
				_LABEL.innerHTML = '快速搜索 全网比价';
				break;
				
			default:
//				_LABEL.innerHTML ='输入想买的东西，购物享优惠';
				_LABEL.innerHTML = '快速搜索 全网比价';
//				_LABEL.innerHTML = '网购从这里开始 搜商品 搜商城';
				break;
		}
};
/*b5m.js end*/

/*jquery.cookie.js start*/
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        var path = options.path ? '; path=' + options.path : '';
        var domain = options.domain ? '; domain=' + options.domain : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
/*jquery.cookie.js end */

/*common.js start*/
//JavaScript Document
/*
 * 去掉字符串前后空格
 * @param keyWord 没转换前字符串
 * @return 去掉前后空格后的字符串
 */
function trim(keyWord){
	var regex=/^\s*|\s*$/g;
	var trimstr=keyWord.replace(regex,"");
	return trimstr;
}

//获得转义的js参数
function getJsParams(str){
	var reg = /[']/g;
	var str= str.replace(reg,"\\'"); 
	
	str = str.replace(/\&/g, "&amp;"); 
	
	var reg = /["]/g;
	var str= str.replace(reg,"&quot;"); 
	

	return str;

}



 /*
  * 移除树的cookie
  */
function removeTreeCookie(){
 	$.cookie('entityTree', null); //删除一个cookie
 	$.cookie('taxonomyTree', null); //删除一个cookie
 	$.cookie('categoryTree', null); //删除一个cookie
}

/*
 * 追加你的搜索历史
 */
function appendHistoryCookie(Value){
	doAppendHistoryCookie("yourHistory","::~~::",Value,30);
}

function getYourHistory(){
	getCookies('yourHistory');
}

/*
 * 追加你的搜索历史
 */
function doAppendHistoryCookie(cookieName,split,Value,maxSize){

	var maxLength = maxSize;
    if(document.cookie.length > 0){
        var cokValue = getCookies(cookieName);
        if(cokValue != null && cokValue!="") 
        {
        	var index;
        	var hasValue = false; 
        	var strArray = cokValue.split(split);
        	for(var i=0;i<strArray.length;i++){
     			if(trim(strArray[i])==Value){
     				hasValue = true;
     				index = i;
     				break;
     			}  
     			
        	}
        	
        	if(hasValue==false){
     			if(strArray.length==maxLength){
	        		cokValue = "";
	        		for(var i=1;i<strArray.length;i++){
	     				if(cokValue==""){
	     					cokValue = trim(strArray[i]);
	     				}else{
	     					cokValue = cokValue +split+ trim(strArray[i]);
	     				}
	     			}
	        	}
    			setCookies(cookieName,cokValue+split+Value);
     		}else{
     			cokValue = "";
     			for(var i=index;i<strArray.length-1;i++){
     				strArray[i] = strArray[i+1];
     			}
     			strArray[strArray.length-1] = trim(Value);
     			for(i=0;i<strArray.length;i++){
     				if(cokValue==""){
     					cokValue = strArray[i];
     				}else{
     					cokValue = cokValue +split+ strArray[i];
     				}
     			}
     			setCookies(cookieName,cokValue);
     		}
        }else{
        	setCookies(cookieName,Value);
        }  
    }
}

/*
 * 追加你的浏览历史
 */
function appendViewCookie(collection,Value){
   doAppendHistoryCookie("yourViewHistory_"+collection,",",Value,20);
}

/*
 * 追加你的浏览历史
 */
function getViewCookie(){
   return getCookies("yourViewHistory_"+collection);
}

/*
 * 设置cookies，用于我的搜索记录
 */
function setCookies(key,value){
	var domain = $("#cookiePath").val();
	if(domain!=""){
		$.cookie(key,value,{path: '/',domain:domain,expires: 7});  
	}else{
		$.cookie(key,value,{path: '/',expires: 7});  
	}
}

/*
 * 设置cookies，用于我的搜索记录
 */
function getCookies(key){
	return $.cookie(key); 
}


/**
 * 根据数组和分隔符拼装字符串
 * arrString 	数组
 * split		分隔符
 * buildLength	长度
 */
function buildArrToString(arrString,split,buildLength){
 	intLength = parseInt(buildLength);
 	var result = "";
 	for(var index=0; index<intLength; index++) {
 		if(result==""){
 			result = arrString[index];
 		}else{
 			result = result + split + arrString[index];
 		}
 	}	
 	return result;
 }

//判断字符串是否为数字
function isNumber(value)
{
	var re = /^-?[0-9]+\.?[0-9]*$/;   
	var nubmer = String(value);
	if (!re.test(nubmer))
	{
		return false;
	}
	return true;
}

function toJSON(txtOrObj){
	var data=txtOrObj;
	if(typeof data=='string') try{data=eval('('+data+')')}catch(e){return ""};
	var draw=[],last=false,isLast=true,indent=0;
	function notify(name,value,isLast,formObj){
	   if(value&&value.constructor==Array){
			draw.push((formObj?('"'+name+'":'):'')+'[');
			for (var i=0;i<value.length;i++) notify(i,value[i],i==value.length-1,false);
			draw.push(']'+(isLast?'':(',')));
	   }else if(value&&typeof value=='object'){
			draw.push((formObj?('"'+name+'":'):'')+'{');
			var len=0,i=0;
			for(var key in value)len++;
			for(var key in value)notify(key,value[key],++i==len,true);
			draw.push('}'+(isLast?'':(',')));
	   }else{
			if(typeof value=='string')value='"'+value+'"';
			draw.push((formObj?('"'+name+'":'):'')+value+(isLast?'':','));
   		};
	};
	notify('',data,isLast,false);
	return draw.join('');
}

function goTo(id){
	$('html, body').animate({scrollTop:$("#"+id).offset().top});
}

function goToNotAnimate(id){
	var top = $("#"+id).offset().top;
	var left = $("#"+id).offset().left;
	window.scrollTo(left,top);
}

 function addProduceClick(collection,DocId,keyWord,clickOffset,sortField,sortType){
 	var url = "suiProductSender.action";
 	var params = "method=addClick&collectionName="+collection+"&docid="+DocId+"&keyWord="+keyWord+"&clickOffset="+clickOffset+"&sortField="+sortField+"&sortType="+sortType;
 	$.ajax({
        type: "POST",
        url: url,
        data: params,
        dataType:"text",
        async: true,
        success: function(httpObj){
        	   
		},error: function(xmlHttpRequest, textStatus, errorThrow){
			  
		}
    });
 }

function onDoSearch(keyWord){
	appendHistoryCookie(keyWord);
}

function getJumpUrl(nowUrl,addParams,removeParams,addWenhao){
	if(addParams!=null&&addParams!=""){
		var addParamsArray = addParams.split(";;");
		for(var i=0;i<addParamsArray.length;i++){
			var addParam = addParamsArray[i].split("::")[0];
			var addValue = addParamsArray[i].split("::")[1];
			
			var indexStart = nowUrl.indexOf("?"+addParam+"=") >= 0 ? nowUrl.indexOf("?" + addParam + "=") : nowUrl.indexOf("&" + addParam + "=");
			if(indexStart>=0){
				indexStart = indexStart + addParam.length + 2;
				var indexEnd = nowUrl.indexOf("&", indexStart);
				if (indexEnd >= 0) {
					nowUrl = nowUrl.substring(0, indexStart) + addValue + nowUrl.substring(indexEnd);
                } else {
                    nowUrl = nowUrl.substring(0, indexStart) + addValue;
                }
			}else{
				 //如果在url中添加?号
				 if(addWenhao){
				 	if (nowUrl.indexOf("?")>=0){
				 	 	nowUrl =  nowUrl + "&" + addParam + "=" +  addValue;
					 }else{
					 	nowUrl =  nowUrl + "?" + addParam + "=" +  addValue;
					 }
				 }else{
				 	//不添加问号则直接追加&号
				 	nowUrl =  nowUrl + "&" + addParam + "=" +  addValue;
				 }
				 
			}
		}
	}
	
	 // 移除参数
	if(removeParams!=null&&removeParams!=""){
		var removeArrays = removeParams.split(";;");
        for (var i = 0; i < removeArrays.length; i++) {
            var removeParam = removeArrays[i];
            var indexStart = nowUrl.indexOf("?" + removeParam + "=") >= 0 ? nowUrl.indexOf("?" + removeParam + "=") : nowUrl.indexOf("&" + removeParam + "=");
            if (indexStart >= 0) {
                var indexEnd = nowUrl.indexOf("&", indexStart + 1);
                if (indexEnd >= 0) {
                	nowUrl = nowUrl.substring(0, indexStart) + nowUrl.substring(indexEnd);  
                } else {
             	    nowUrl = nowUrl.substring(0, indexStart);
                }
            }
        }
     }
	return nowUrl;
}


function goUrlByATag(url){
	var el = document.createElement("a");
	document.body.appendChild(el);
	el.href=url;
	
	if(el.click) {
        el.click();
    }else{//safari浏览器click事件处理
        try{
            var evt = document.createEvent('Event');
            evt.initEvent('click',true,true);
            el.dispatchEvent(evt);
        }catch(e){//alert(e)
        	
        };       
    }
}


function goUrlInNewWindowByATag(url){
	var el = document.createElement("a");
	document.body.appendChild(el);
	el.href=url;
	el.target='_blank';
	
	if(el.click) {
        el.click();
    }else{//safari浏览器click事件处理
        try{
            var evt = document.createEvent('Event');
            evt.initEvent('click',true,true);
            el.dispatchEvent(evt);
        }catch(e){//alert(e)
        	
        };       
    }
}

//跳转到登录页面
function goToLoginPage(){
	var url = "login.htm?method=sign";
	goUrlByATag(url);
}		

//第三方登录
function initSnsLogin(type){
	var url = "login.htm?method=initSnsLogin&snsType=" + type;
	goUrlByATag(url);	   
}

function getSourcePriceTrend(dataId,showDivId,weight,height){
	
}

function getPriceTrend(dataId,showDivId,weight,height,setting){
	var data = $("#"+dataId).val();
	var xmlData = data.split("##")[0];
	var lastUpdateTime = data.split("##")[1];
	var so = new SWFObject("sui/js/amline.swf", "amline", weight+"px",height+"px", "8", "#FFFFFF");
	so.addVariable("path", "sui/js/");
	so.addVariable("preloader_color", "#000000");
	so.addVariable("settings_file", escape(setting));
	so.addVariable("chart_data",getJsParams(xmlData));
	so.addParam("wmode", "transparent");
	so.write(showDivId);
	if(lastUpdateTime!=""){
	   $("#"+showDivId+" span").html("更新时间:"+lastUpdateTime);
    }
	$("#"+showDivId).show();
    $("#"+showDivId).parent().show();
}

function getPriceTrendByUrl(url,showDivId,weight,height,setting){
	$.ajax({
        type: "POST",
        url: url,
        data: "",
        dataType:"html",
        async:false,
        success: function(httpObj){
    	   if(httpObj!=""){
    		   var so = new SWFObject("sui/js/amline.swf", "amline", weight+"px",height+"px", "8", "#FFFFFF");
    		   so.addVariable("path", "sui/js/");
    		   so.addVariable("preloader_color", "#000000");
    		   so.addVariable("settings_file", escape(setting));
    		   so.addParam("wmode", "transparent");
    		   var result = httpObj.split("##");
    		   so.addVariable("chart_data",getJsParams(result[0]));
    		   so.write(showDivId);
    		   var lastUpdateTime = result[2];
    		   if(lastUpdateTime!=""){
    			   $("#"+showDivId+" span").html("更新时间:"+lastUpdateTime);
    		   }
    		   $("#"+showDivId).show();
    		   $("#"+showDivId).parent().show();
    	   }
		}
	});
}


function addVisit(vu, arg1, arg2) {
	vu = encodeURI(vu);
	var url = "visitCount.action?method=addVisit";
	var params="&dto.url="+vu+"&dto.arg1="+arg1+"&dto.arg2="+arg2;
	$.ajax({
        type: "POST",
        url: url,
        data: params,
        dataType:"html",
        success: function(httpObj){
    	   if(httpObj!=""){
    		   	if(trim(httpObj)=='login_time_out'){
    		   		//popupLogin();
    		   	}else if(trim(httpObj)=='error'){
    		   		//alert("操作失败");
    		   	}else{
    		   		if($("#addCollectionDiv")[0]==null){
    		   			var addDiv=$("<div id='addCollectionDiv'></div>");
    		   			addDiv.appendTo("body");
    		   		}
    		   		$("#addCollectionDiv").html(httpObj);
    		   		showCollctionPage(imgCover,pdcId,obj);
    		   	}
    	   }
		},error: function(xmlHttpRequest, textStatus, errorThrow){
	   		alert("网络连接异常，请稍后再试");
		}
	});

}


/*suiHeader.js start*/
/*
 * 按下左键处理
 */
document.onmousedown = function (event) {
	clearDownListDiv(event);
}; 

// JavaScript Document
var timeObj = null;
var dlInnerHTML = "";
//如果鼠标在autoFill范围外点击且autoFill打开，那么关闭autoFill
function clearDownListDiv(event) {
	event = event || window.event;
	var div = $(dl_op).find('.dr_list')[0] ;
	var sousuotiao =$(dl_op).find('.dl_input')[0];// getByClass(dl_op,"dl_input")[0];
	if (div == null || sousuotiao == null) {
		return;
	};
	var sousuotiaoHeight = $(sousuotiao).height();
	var divLeft = $(div).offset().left;
	var divTop = $(div).offset().top;
	var divRight = divLeft + $(div).width();
	var divBottom = divTop + $(div).height();
	var mouseX = event.pageX ? event.pageX : event.clientX + document.body.scrollLeft - document.body.clientLeft;
	var mouseY = event.pageY ? event.pageY : event.clientY + document.body.scrollTop - document.body.clientTop;
	if (div.style.display != "none") {
		if (mouseX < divLeft || mouseX > divRight || mouseY < (divTop - sousuotiaoHeight) || mouseY > divBottom) {
			showOrHiddenAuto(2);
		}
	}
}
/*
 * 获取autofill数据
 * @param eve 事件对象
 */
function getAutoFill(eve, baseUrl,fn) {
	var _callback = fn;	
    //上下左右和回车键时候，不做ajax调用
	if (eve.keyCode == 40 || eve.keyCode == 38 || eve.keyCode == 37 || eve.keyCode == 39 || eve.keyCode == 13) {
		return false;
	}
		
	sendAutofill("", baseUrl);
}
/*
 * 获取autofill数据
 */
function sendAutofill(keyWord, baseUrl,fn) {
	var dl = $(dl_op).find('.dr_list')[0];//列表	
	var keyWordObj =$(dl_op).find('.dl_input')[0]; 
	var keyWord = $.trim(keyWordObj.value);
	
	if (keyWord != "") {
		if (timeObj != null) {
			clearTimeout(timeObj);
		}
  	   //ajax调用，获取autofill列表  
  	   
  	    keyWord = getJsParams(keyWord);
		timeObj = setTimeout("getAutoFillAjax('" + keyWord + "', '" + baseUrl + "');", 100);
	} else {
		dl.innerHTML = "";
		showOrHiddenAuto(2);
	}
}

//////............viola
function getAutoFillAjax(keyWord, baseUrl) {
	var collectionName =$(dl_op).find('.ajaxCollection')[0].value;//document.getElementById("ajaxCollection").value;//列表		
	var url = baseUrl + "autofill.htm";
	var params = "collectionName=" + collectionName + "&keyWord=" + keyWord;
	var ul = $(dl_op).find('.dr_list')
	//test;
	//ul.html('<li><a>test</a></li>');
	//showOrHiddenAuto(1);
	//return false;
	//test end;
	$.ajax({
		  url: url,
		  async:false,
		  data: params,
		  dataType:"jsonp",  
	      jsonp: 'jsoncallback',		 
		  success: function (httpObj) {
				if (httpObj != "") {
					var html = "";
					$.each(httpObj, function(i, item){
						html += "<li keyword=\""+item.value+"\"><a onclick='seleKey(\""+item.value+"\");' onmouseover='over(\""+i+"\")' onmouseout='out(\""+i+"\")'><span class='result'>"+item.hl_value+"</span></a></li>";
					});
					ul.html("");
					ul.html(html);
					showOrHiddenAuto(1);
				} else {
					showOrHiddenAuto(2);
				}
		  }
		});
}

/*
 * 显示或隐藏推荐区域
 * flg: 0 交替显示和隐藏
 *      1 显示
 *      2 隐藏
 */
function showOrHiddenAuto(flg) {
	var obj =$(dl_op).find('.dr_con')[0];	
	if (flg == 0) {
		if (obj.style.display == "block") {
			obj.style.display = "none";
		} else {
			obj.style.display != "block";
			openAutofill();
		}
	} else {
		if (flg == 1) {
			if (null != obj) {
				obj.style.display = "block";
			}
		} else {
			if (flg == 2) {
				if (null != obj) {
					obj.style.display = "none";
				}
			}
		}
	}
}


/*
 * 按上下键，选择autofill列表的值
 * @param eve 事件对象
 */
function selectBykeyEvent(eve,dom) {
	dl_op = dom;
	var dl = $(dl_op).find('.dr_list')[0];//列表
	var downObj = $(dl_op).find('.dr_con')[0];  
	var qObj = $(dl_op).find('.dl_input')[0];
		
	var hdnKeyTemp = $(dl_op).find('.hdnKeyTemp')[0];//document.getElementById("hdnKeyTemp");
	var hdnIndex =$(dl_op).find('.hdnCurIndex')[0];//document.getElementById("hdnCurIndex");
	
	if (eve.keyCode == 40 && downObj.style.display == "block") {
    //按向上键时移动光标
		var a = dl.getElementsByTagName("a");
		if (a.length > 0) {
			if (hdnIndex.value == "") {
				a[0].style.backgroundColor = "#f2f2f2";
//				a[0].style.color = "#FFFFFF";
				qObj.value = a[0].parentNode.getAttribute("keyword");
				hdnIndex.value = 0;
			} else {
				if (hdnIndex.value < a.length - 1) {
					a[hdnIndex.value].style.backgroundColor = "#FFFFFF";
//					a[hdnIndex.value].style.color = "#3366CC";
					hdnIndex.value = parseInt(hdnIndex.value) + 1;
					a[hdnIndex.value].style.backgroundColor = "#f2f2f2";
//					a[hdnIndex.value].style.color = "#FFFFFF";
					qObj.value = a[hdnIndex.value].parentNode.getAttribute("keyword");
				} else {
					a[hdnIndex.value].style.backgroundColor = "#FFFFFF";
//					a[hdnIndex.value].style.color = "#3366CC";
					hdnIndex.value = "";
					qObj.value = hdnKeyTemp.value;
				}
			}
		}
	} else {
		if (eve.keyCode == 38 && downObj.style.display == "block") {
        //按向下键时移动光标
			var a = dl.getElementsByTagName("a");
			if (a.length > 0) {
				if (hdnIndex.value == "") {
					a[a.length - 1].style.backgroundColor = "#f2f2f2";
//					a[a.length - 1].style.color = "#FFFFFF";
					qObj.value = a[a.length - 1].parentNode.getAttribute("keyword");
					hdnIndex.value = a.length - 1;
				} else {
					if (hdnIndex.value > 0) {
						a[hdnIndex.value].style.backgroundColor = "#FFFFFF";
//						a[hdnIndex.value].style.color = "#3366CC";
						hdnIndex.value = parseInt(hdnIndex.value) - 1;
						a[hdnIndex.value].style.backgroundColor = "#f2f2f2";
//						a[hdnIndex.value].style.color = "#FFFFFF";
						qObj.value = a[hdnIndex.value].parentNode.getAttribute("keyword");
					} else {
						a[hdnIndex.value].style.backgroundColor = "#FFFFFF";
//						a[hdnIndex.value].style.color = "#3366CC";
						hdnIndex.value = "";
						qObj.value = hdnKeyTemp.value;
					}
				}
			}
		} else {
			if (eve.keyCode != 37 && eve.keyCode != 39 && eve.keyCode != 13) {
        //还原当前列表的索引和搜索关键字
				hdnIndex.value = "";
				hdnKeyTemp.value = qObj.value;
			}
		}
	}
	if (!eve) {
		eve = window.event;
	}//火狐中是 window.event
	// if ((eve.keyCode || eve.which) == 13) {
	// 	doSubmit();
	// }
}
/*
 * 清除按键选择的样式
 */
function clearSelected() {
	var  dl = $(dl_op).find('.dr_con')[0];//列表
	var hdnIndex =$(dl_op).find('.hdnCurIndex')[0];//document.getElementById("hdnCurIndex");
	var a = dl.getElementsByTagName("a");
	if (a.length > 0 && hdnIndex.value != "") {
		a[hdnIndex.value].style.backgroundColor = "#FFFFFF";
//		a[hdnIndex.value].style.color = "#3366CC";
		hdnIndex.value = "";
	}
}
/*
 * 当前选择样式
 * @param index 当前索引
 */
function over(index) {
	clearSelected();
	var dl = $(dl_op).find('.dr_con')[0];//dl = document.getElementById("list");//列表
	var hdnIndex = $(dl_op).find('.hdnCurIndex');//document.getElementById("hdnCurIndex");
	var a = dl.getElementsByTagName("a");
	a[index].style.backgroundColor = "#f2f2f2";
//	a[index].style.color = "#FFFFFF";
	hdnIndex.value = index;
}
/*
 * 当前未被选中样式
 * @param index 当前索引
 */
function out(index) {
	var dl = $(dl_op).find('.dr_con')[0];//列表
	var hdnIndex = $(dl_op).find('.hdnCurIndex')[0];//document.getElementById("hdnCurIndex");
	var a = dl.getElementsByTagName("a");
	a[index].style.backgroundColor = "#FFFFFF";
//	a[index].style.color = "#3366CC";
}

/*
 * 选中autofill
 */
function seleKey(string) {
	var qObj = $(dl_op).find('.dl_input')[0];
	qObj.value = string;
	showOrHiddenAuto(2);
	qObj.focus();
	$(dl_op).find('.dl_input').trigger('writein');

	//doSubmit();//提交表单
}

function isKeywordLegal(keyword){
	if(keyword == "%5C")
		return false;
	return true;
}

/**
  * 根据target和collection提交搜索
  */
function goToTarget(target, collection) {
	var keyWord = $(dl_op).find('.dl_input')[0];
	var searchTargetObj = document.getElementById("hdnTarget");
	var ajaxCollectionObj = $(dl_op).find('.ajaxCollection')[0];
	searchTargetObj.value = target;
	ajaxCollectionObj.value = collection;
	doSubmit();
}
/**
  * 设置target和 collection
  */
function setTarget(target, collection) {
	var searchTargetObj = document.getElementById("hdnTarget");
	var ajaxCollectionObj =$(dl_op).find('.ajaxCollection')[0]; //document.getElementById("ajaxCollection");
	searchTargetObj.value = target;
	ajaxCollectionObj.value = collection;
}
/*
 * 通过关键字检索
 */
function goToSearch(keyWord) {
	var keyWordObj =  $(dl_op).find('.dl_input')[0];
	keyWordObj.value = keyWord;
	$(".labelshow").hide();
	doSubmit();
}
/**
 * 退回到分类搜索
 */
function backToCategory(collection, category) {
	var keyWord = trim(document.getElementById("lastSearchKeyword").value);
	$(dl_op).find('.hdnTarget').val("suiSearch.htm");
	$("#hdnParams").val("&categoryValue=" + encodeURIComponent(category));
	goToSearch(keyWord);
}

// JavaScript Document
/*
 * 去掉字符串前后空格
 * @param keyWord 没转换前字符串
 * @return 去掉前后空格后的字符串
 */
function trim(keyWord) {
	var regex = /^\s*|\s*$/g;
	if(typeof(keyWord)!='undefined') {
		var trimstr = keyWord.replace(regex, "");
		return trimstr;
	} else {
		return "";
	}
}

function changeTargetLabel(target, collection) {
	var keyword = $.trim( $(dl_op).find('.dl_input')[0].value); 
	var searchTargetObj = $(dl_op).find('.hdnTarget')[0];//document.getElementById("hdnTarget");
	var ajaxCollectionObj =$(dl_op).find('.ajaxCollection')[0]// document.getElementById("ajaxCollection");
	searchTargetObj.value = target;
	ajaxCollectionObj.value = collection;
}

function replaceStr(str,str1,str2){
	while(true){
		if(str.indexOf(str1)>=0){
			str = str.replace(str1,str2);
		}else{
			return str;
		}
	}
}

function showHelp(){
	if(getCookies("showHelp")==null||getCookies("showHelp")==''){
		$("#needhelp").show();
	}
}

function closeHelp(){
	$("#needhelp").hide();
	setCookies("showHelp","NO");
}


function getByClass(oParent, sClass)
{

 var aEle=oParent.getElementsByTagName('*');
 var aResult=[];
 var i=0;
 
 for(i=0;i<aEle.length;i++)
 {
  if(aEle[i].className==sClass)
  {
   aResult.push(aEle[i]);
  }
 }
 
 return aResult;
}
