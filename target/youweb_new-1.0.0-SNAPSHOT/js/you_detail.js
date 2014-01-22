$(function() {
    function scrollImg(obj)
    {
        if (!obj)
            return false;
        var oParent = $(obj);
        var oSmaPic = oParent.find('.sma-pic');
        var oSmaPicLi = oSmaPic.find('li');
        var aSmaPicLen = oSmaPicLi.length;
        var oSmaPicLiWidth = parseInt(oSmaPicLi.outerWidth(true), 10);
        var oPrev = oParent.find('#prev');
        var oNext = oParent.find('#next');
        var num = 0;
        var iNow = 0;
        var timer = null;
        var iPage = 4;

        oSmaPic.css({'width': aSmaPicLen * oSmaPicLiWidth + 'px'});

        $('.big-pic > li:not(:first-child)').hide();

        if (aSmaPicLen > iPage)
        {
            oNext.addClass('active');
        }

        var moveTo = function() {

            oSmaPicLi.removeClass('on');
            oSmaPicLi.eq(iNow).addClass('on');
            $('.big-pic > li').eq(iNow).fadeIn(1000).siblings().fadeOut(500);
            num = Math.max(0, iNow - 3);

            if (aSmaPicLen > iPage)
            {
                oSmaPic.stop(true).animate({'left': -num * oSmaPicLiWidth}, 'slow');
            }

            if (aSmaPicLen > iPage)
            {

                if (iNow === 0)
                {
                    oPrev.removeClass('active');
                    oNext.addClass('active');
                }
                else if (iNow == aSmaPicLen - 1)
                {
                    oPrev.addClass('active');
                    oNext.removeClass('active');
                }
                else
                {
                    oPrev.addClass('active');
                    oNext.addClass('active');
                }

            }

        };

        oSmaPicLi.bind('click mouseover', function(e) {
            e.preventDefault();
            iNow = $(this).index();
            moveTo();
        });

        oPrev.add(oNext).click(function(e) {
            e.preventDefault();
            if ($(this).hasClass('active'))
            {
                iNow = this.id == 'prev' ? iNow - 1 : iNow + 1;
                moveTo();
            }
        });

        var autoMove = function() {
            timer = setTimeout(function() {
                if (oNext.hasClass('active'))
                {
                    oNext.trigger('click');
                } else
                {
                    iNow++;
                    if (iNow == aSmaPicLen)
                    {
                        iNow = 0;
                    }
                    moveTo();
                }
                timer = setTimeout(function() {
                    autoMove();
                }, 3000);
            }, 3000);
        };
        autoMove();

        oParent.hover(function() {
            clearTimeout(timer);
        }, function() {
            autoMove();
        });
    }
    scrollImg('#J_carousel');

    if (typeof document.body.style.maxHeight != 'undefined')
    {
        var oPos = $('.fixed-price').parent();
        var disY = $('body').height() - 170;
        var setFixed = function()
        {
            var oWinTop = $(window).scrollTop();
            if (oWinTop > 670 && (oWinTop < disY - $(window).height()) && !oPos.data('fixed'))
            {
                oPos.data('fixed', true).css({
                    position: 'fixed',
                    bottom: '0',
                    zIndex: 999
                });
            } else if ((oWinTop <= 670 || oWinTop >= disY - $(window).height()) && oPos.data('fixed'))
            {
                oPos.removeData('fixed').css({
                    position: 'static'
                });
            }
        };

        $(window).bind('scroll.fixed-pos', function() {
            setFixed();
        });
        $(window).resize(function() {
            oPos.data('fixed');
        });
        setFixed();
    }

    function backTopFun()
    {
        var backToTopEle = $('<div id="back-top"><a href="javascript:void(0);" title="返回顶部"></a></div>').appendTo($('body'));
        backToTopEle.click(function(e) {
            e.preventDefault();
            $('html,body').animate({scrollTop: 0}, 'fast');
        });
        $(window).bind('scroll', function() {
            var st = $(window).scrollTop();
            if (st > 50)
            {
                backToTopEle.show();
            }
            else
            {
                backToTopEle.hide();
            }
        });
    }
    backTopFun();

    mousedownModAll();
});
function mousedownModAll() {
    //点击商品后抢购数+1
    $('body').on("mousedown", '.show-info > .now a,.pic-show > .big-pic a,.fixed-price > .now a', function(e) {
        if (e.which != 3) {
            var menuCur = $("#menuCurPageTag").val();
            var url = "";
            if (menuCur != 'hotel') {
                url = "/youTotalClick.do?t=" + new Date().getTime();
            } else {
                url = "/hotelTotalClick.do?t=" + new Date().getTime();
            }
            var id = $(this).parents('div[data-id]:first').attr('data-id');
            $.ajax({
                type: "post",
                url: url,
                data: {
                    "clikcId": id
                }
            });
        }
    });
}
