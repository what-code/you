/**
 *Document:Spc_tao
 *Creat:13-7-29 下午5:51
 *Author:junli.shen@b5m.com
 */
var spaTaoFed = {
    flashAd: function() {
        /*定义变量*/
        var oParent = $('#banner'),
                oPic = oParent.find('.pic'),
                aPic = $(oPic).children().length,
                aA = oParent.find('.txt > a'),
                iNow = 0,
                timer = null;

        $('.pic > li:not(:first-child)').hide();
        /*点击切换*/
        aA.click(function() {
            clearInterval(timer);
            iNow = $(this).index();
            tab();
        });
        /*大图滤镜*/
        function tab() {
            aA.removeClass('cur');
            aA.eq(iNow).addClass('cur');
            $('.pic > li').eq(iNow).fadeIn(1000).siblings().fadeOut(500);
        }
        /*自动轮播*/
        function timerInner() {
            iNow++;
            if (iNow == aA.size())
            {
                iNow = 0;
            }
            tab();
        }
        /*开起定时器为5秒*/
        timer = setInterval(timerInner, 5000);

        /*移到广告位清除定时器，移出开起定时器为5秒*/
        $(oParent).hover(function() {
            clearInterval(timer);
        }, function() {
            timer = setInterval(timerInner, 5000);
        });
    },
    tabShow: function(boxId) {
        var bxId = '#' + boxId
                , tabNavs = $('.tab', bxId).children();
        tabNavs.hover(function() {
            var thisIndex = tabNavs.index(this)
                    , _this = $(this);
            _this.addClass('cur').siblings().removeClass('cur');
            _this.parents(bxId).find('.tab-cont').children().eq(thisIndex).show().siblings().hide();
        });
        tabNavs.first().trigger("mouseover");
    },
    rollSlide: function(boxId) {
        var bxId = "#" + boxId
                , rollBox = $('.slideCont', bxId)
                , rollNav = $('.slideNav', bxId).children()
                , rollNum = rollNav.length
                , showIndex = 0
                , rollWidth = 0
                , autoRoll;
        rollBox.css("width", function() {
            var rollDom = rollBox.children()
                    , rollLength;
            rollWidth = rollDom.first().outerWidth(true);
            rollLength = rollWidth * (rollNum + 2);
            rollDom.first().clone().appendTo(rollBox);
            return rollLength + "px";
        });
        var rollLeft = function() {
            ++showIndex;
            rollBox.stop(true, true).animate({marginLeft: "-" + showIndex * rollWidth + "px"}, 320, function() {
                if (showIndex >= rollNum) {
                    showIndex = 0;
                    rollBox.css("marginLeft", 0);
                }
                rollNav.eq(showIndex).addClass('cur').siblings().removeClass('cur');
            });
        };
        $(bxId).hover(function() {
            clearInterval(autoRoll);
        }, function() {
            autoRoll = setInterval(rollLeft, 2000);
        }).trigger("mouseout");
        rollNav.eq(0).addClass('cur');
        rollNav.hover(function() {
            showIndex = rollNav.index(this) - 1;
            rollLeft();
        });

    },
    //判断浏览器是否支持某css3样
    supports: (function() {
        var div = document.createElement('div'),
                vendors = 'Khtml O Moz Webkit'.split(' '),
                len = vendors.length;
        return function(prop) {
            if (window.ActiveXObject && parseInt(navigator.userAgent.toLowerCase().match(/msie ([\d.]+)/)[1], 10) <= 8) {
                return false;
            }
            if (prop in div.style)
                return true;
            if ('-ms-' + prop in div.style)
                return true;
            prop = prop.replace(/^[a-z]/, function(val) {
                return val.toUpperCase();
            });
            while (len--) {
                if (vendors[len] + prop in div.style) {
                    return true;
                }
            }
            return false;
        };
    })(),
    slideSpeed : 300,
    slideInfo: function() {
        var supportstTansition = this.supports('transition');
        var speed = this.slideSpeed;
        $('.load-recHotels-bx').hover(function() {
            if (supportstTansition) {
                $(this).find('>div').css('bottom', 0);
                $(this).find('>a>p').css('bottom', -33);
            } else {
                $(this).find('>div').stop().animate({bottom: 0}, speed);
                $(this).find('>a>p').stop().animate({bottom: -33}, speed);
            }
        }, function() {
            if (supportstTansition) {
                $(this).find('>div').css('bottom', '100%');
                $(this).find('>a>p').css('bottom', 0);
            } else {
                $(this).find('>div').stop().animate({bottom: '100%'}, speed);
                $(this).find('>a>p').stop().animate({bottom: 0}, speed);
            }

        });
    },
    seePlayLiveHover:function(){
       if(typeof document.body.style.maxHeight !== "undefined")
            return false;
    	$(".pic:first",'#load-slider2').find('a>s').each(function(i){    		
			var thisSTagParent = $(this).parent()
				,thisSize = {width:thisSTagParent.width()-32,height:thisSTagParent.height()-16,"overflow":"hidden"}
				,thisIndex = i;
			$('.pic','#load-slider2').each(function(i){
			   $("a>s",this).eq(thisIndex).css(thisSize);
			});
		});
    	$(".pic",'#load-slider2').find('a').hover(function(){
    		$('s',this).show();
    	},function(){
    		$('s',this).hide();
    	});
    }
};
