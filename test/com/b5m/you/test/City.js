function citys() {
	return [ {
		"city" : "三亚"
	}, {
		"city" : "上海"
	}, {
		"city" : "上饶"
	}, {
		"city" : "东莞"
	}, {
		"city" : "东营"
	}, {
		"city" : "中山"
	}, {
		"city" : "丽江"
	}, {
		"city" : "乌鲁木齐"
	}, {
		"city" : "乐山"
	}, {
		"city" : "九华山"
	}, {
		"city" : "九寨沟"
	}, {
		"city" : "佛山"
	}, {
		"city" : "兰州"
	}, {
		"city" : "凉山州"
	}, {
		"city" : "包头"
	}, {
		"city" : "北京"
	}, {
		"city" : "北海"
	}, {
		"city" : "南京"
	}, {
		"city" : "南宁"
	}, {
		"city" : "南昌"
	}, {
		"city" : "南通"
	}, {
		"city" : "厦门"
	}, {
		"city" : "台州"
	}, {
		"city" : "合肥"
	}, {
		"city" : "吉林"
	}, {
		"city" : "呼伦贝尔"
	}, {
		"city" : "呼和浩特"
	}, {
		"city" : "哈尔滨"
	}, {
		"city" : "唐山"
	}, {
		"city" : "嘉兴"
	}, {
		"city" : "大庆"
	}, {
		"city" : "大理市"
	}, {
		"city" : "大连"
	}, {
		"city" : "天津"
	}, {
		"city" : "太原"
	}, {
		"city" : "威海"
	}, {
		"city" : "宁波"
	}, {
		"city" : "安庆"
	}, {
		"city" : "常州"
	}, {
		"city" : "广元"
	}, {
		"city" : "广州"
	}, {
		"city" : "开封"
	}, {
		"city" : "张家界"
	}, {
		"city" : "徐州"
	}, {
		"city" : "惠州"
	}, {
		"city" : "成都"
	}, {
		"city" : "扬州"
	}, {
		"city" : "拉萨"
	}, {
		"city" : "无锡"
	}, {
		"city" : "昆明"
	}, {
		"city" : "晋中"
	}, {
		"city" : "杭州"
	}, {
		"city" : "柳州"
	}, {
		"city" : "桂林"
	}, {
		"city" : "武汉"
	}, {
		"city" : "汕头"
	}, {
		"city" : "江门"
	}, {
		"city" : "池州"
	}, {
		"city" : "沈阳"
	}, {
		"city" : "泉州"
	}, {
		"city" : "泰安"
	}, {
		"city" : "泰州"
	}, {
		"city" : "济南"
	}, {
		"city" : "济宁"
	}, {
		"city" : "海口"
	}, {
		"city" : "淄博"
	}, {
		"city" : "深圳"
	}, {
		"city" : "清远"
	}, {
		"city" : "温州"
	}, {
		"city" : "湖州"
	}, {
		"city" : "潍坊"
	}, {
		"city" : "澳门"
	}, {
		"city" : "烟台"
	}, {
		"city" : "珠海"
	}, {
		"city" : "石家庄"
	}, {
		"city" : "福州"
	}, {
		"city" : "秦皇岛"
	}, {
		"city" : "绍兴"
	}, {
		"city" : "绵阳"
	}, {
		"city" : "舟山"
	}, {
		"city" : "苏州"
	}, {
		"city" : "西双版纳"
	}, {
		"city" : "西安"
	}, {
		"city" : "贵阳"
	}, {
		"city" : "遂宁"
	}, {
		"city" : "邯郸"
	}, {
		"city" : "郑州"
	}, {
		"city" : "重庆"
	}, {
		"city" : "金华"
	}, {
		"city" : "镇江"
	}, {
		"city" : "长春"
	}, {
		"city" : "长沙"
	}, {
		"city" : "阿坝州"
	}, {
		"city" : "青岛"
	}, {
		"city" : "鞍山"
	}, {
		"city" : "香格里拉"
	}, {
		"city" : "香港"
	}, {
		"city" : "黄山"
	} ];
};

function recordTrackInfoWithType(g, b, n, f) {
	var l = ("https:" == document.location.protocol ? "https://" : "http://")
			+ URLPrefix.tracker + "/related/info.do?1=1";
	var c = {};
	if (g && b) {
		c.infoType = g;
		c.relatedInfo = encodeURIComponent(b) || "";
		c.attachedInfo = encodeURIComponent(n) || "";
		if (document) {
			c.url = document.URL || "";
			c.infoPreviousUrl = document.referrer || ""
		}
		c.ieVersion = ieVersion;
		c.platform = platform;
		if (f) {
			var a = e1.exec(f);
			if (a) {
				c.exField1 = a[0].replace(/exfield1=/i, "").replace(";", "")
			}
			var m = e2.exec(f);
			if (m) {
				c.exField2 = m[0].replace(/exfield2=/i, "").replace(";", "")
			}
			var k = e3.exec(f);
			if (k) {
				c.exField3 = k[0].replace(/exfield3=/i, "").replace(";", "")
			}
			var j = e4.exec(f);
			if (j) {
				c.exField4 = j[0].replace(/exfield4=/i, "").replace(";", "")
			}
			var h = e5.exec(f);
			if (h) {
				c.exField5 = h[0].replace(/exfield5=/i, "").replace(";", "")
			}
		}
		for ( var e in c) {
			l += "&" + e + "=" + encodeURIComponent(c[e])
		}
		var d = new Image(1, 1);
		d.src = l
	}
}
