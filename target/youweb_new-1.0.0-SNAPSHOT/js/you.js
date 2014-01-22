(function() {
    // 游表单搜索
    $('form.search-form').each(function() {
        var form = $(this);
        var keywords = form.find('input[name=keywords]');
        var placeholder = keywords.data('placeholder');
        form.find('.sub').unbind().click(function(e) {
            e.preventDefault();
            var val = $.trim(keywords.val());
            if (val == '' || val == placeholder) {
                keywords.focus();
                return false;
            }
            form.submit();
        });
    });
    var J_order = $("#J_order");
    var J_orderInput = J_order.find('input[name=order]');
    J_order.find('a[rel]').on('click', function(e) {
    	e.preventDefault();
        J_orderInput.val($(this).attr('rel'));
        J_order.submit();
    });
    
    //游记列表的排序
    var notes_form = $("#notes_news_nav");
    var orderInput = notes_form.find('input[name=order]');
    var orderPage = notes_form.find('input[name=currPageNo]');
    notes_form.find('a[rel]').on('click', function(e) {
    	e.preventDefault();
    	orderInput.val($(this).attr('rel'));
    	orderPage.val('1');
        notes_form.submit();
    });
    
    var $J_citySelect;
    var $selectedEl = null;
    var citySelect = {
        isShow: false,
        show: function() {
            if (this.isShow) {
                return false;
            }
            this.isShow = true;
            $J_citySelect.css({
                marginTop: 20,
                opacity: 0
            }).show().animate({
                marginTop: 0,
                opacity: 1
            });
        },
        hide: function() {
            this.isShow = false;
            $J_citySelect.animate({
                marginTop: 20,
                opacity: 0
            }, function() {
                $(this).hide();
            });
        }
    };
    $("#J_filterCont,#J_secondFiletr").find('[data-local]').on('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        $J_citySelect = $(this).parents('.rows').find('.cityselect');
        citySelect.show();
        $selectedEl = $(this);
    });
    $('.cityselect').on('click', 'a', function(e) {
        e.stopPropagation();
        var self = $(this);
        if ($(this).hasClass('close')) {
            citySelect.hide();
        } else if ($(this).attr('data-city')) {
            $selectedEl.find('span.l').html($(this).html());
            $(this).parents('.rows:first').find('input[name=city]').val($(this).attr('data-city'));
            citySelect.hide();
            self.trigger('jump', $(this).attr('data-city'));
        }
    });
    $('.cityselect').on('click', function(e) {
        e.stopPropagation();
    });
    $(document).on('click', function() {
        citySelect.isShow && citySelect.hide();
    });
    
    //酒店、攻略4个部分切换
    var $J_filterNav = $("#J_filterNav");
    if ($J_filterNav.length) {
        var $J_filterCont = $J_filterNav.next();
        $J_filterNav.find('>li').on('click', function(e) {
            e.preventDefault();
            if (!$(this).find('a').hasClass('cur')) {
                $J_filterNav.find('.cur').removeClass('cur');
                $(this).find('a').addClass('cur');
                var k = $J_filterNav.find('>li').index($(this));
                $J_filterCont.find('>div').hide().eq(k).show();

                var input = $J_filterCont.find('>div').eq(k).find('.filter-text[placeholder]');
                if (!input.data('initPlaceholder')) {
                    input.placeholder({
                        className: 'filter-text-placeholder'
                    }).on('focusin', function() {
                        $(this).addClass('filter-text-focusin');
                    }).on('focusout', function() {
                        $(this).removeClass('filter-text-focusin');
                    });
                }



            }
        });
        var k = $J_filterNav.find('a').index($J_filterNav.find('.cur'));
        $J_filterCont.find('>div').hide().eq(k).show();
        $J_filterCont.find('>div').eq(k).find('.filter-text[placeholder]').placeholder({
            className: 'filter-text-placeholder'
        }).on('focusin', function() {
            $(this).addClass('filter-text-focusin');
        }).on('focusout', function() {
            $(this).removeClass('filter-text-focusin');
        });
        $J_filterCont.find('input[name=keywords]').each(function(index,item){
        	if(index !== k){
        		$(this).val('');
        	}
        });
    }
    $("#J_secondFiletr>input").placeholder({
        className: 'filter-text-placeholder'
    }).on('focusin', function() {
        $(this).addClass('filter-text-focusin');
    }).on('focusout', function() {
        $(this).removeClass('filter-text-focusin');
    });
    //slider分页 和 切换
    var $sliderList = $('.slider');
    $sliderList.length && $('.slider').each(function() {
        var $slider = $(this);
        var $sliderNav = $slider.find('.slider-nav > .slider-nav-li');
        var $sliderCont = $slider.find('.slider-item');

        var getPageHtml = function(moreLink) {
            var pageLen = Math.ceil($sliderCont.filter(':visible').find('li').length / 4);
            var html = '';
            var i = pageLen - 1;
            while (i >= 0) {
                html = '<a ' + (i == 0 ? 'class="cur"' : '') + ' href="javascript:void(0)">' + (i + 1) + '</a>' + html;
                i--;
            }
            //return html + '<a class="more" href="' + moreLink + '">更多&gt;</a>';
            return html;
        };
        window.NS.public.tab($sliderNav, $slider.find('.slider-item'), 'cur', 'slider-item-cur', $slider.find('>.slider-prev'), $slider.find('>.slider-next'), function() {
            //由于内容于程序实现，暂屏蔽
            var link = $(this).find('a').attr('href');
            $slider.find('.slider-page').html(getPageHtml(link));
        });
        var link = $sliderNav.eq(0).find('a').attr('href');
        $slider.find('.slider-page').html(getPageHtml(link));
        $slider.find('.slider-page').on('click', 'a[class!=more]', function(e) {
            e.preventDefault();
            var pageCont = $slider.find('.slider-item:visible');
            var ul = pageCont.find('ul');
            var pageHeight = pageCont.height();
            if (!$(this).hasClass('cur')) {
                var page = ($(this).html() | 0) - 1;
                pageCont.animate({
                    scrollTop: page * pageHeight
                }, 300);
                $(this).addClass('cur').siblings('.cur').removeClass('cur');
            }
        });
    });
    
    
    
    $('#J_pi dd li,#J_ss dd li').filter(':nth-child(even)').addClass('even');
	setSearch();
	searchApr();
	try{
        $('#J_date1').datepicker();
        $('#J_date2').datepicker();
    }catch(e){}
})();

(function(window, $) {
	$('#J_addFav01').on('click',function(e){
		var sURL=window.location;
		var sTitle=document.title;
	    try{
	        window.external.addFavorite(sURL, sTitle);
	    }catch (e){
	        try{
	            window.sidebar.addPanel(sTitle, sURL, "");
	        }catch (e){
	            alert("加入收藏失败，请使用Ctrl+D进行添加.");
	        }
	    }
    });
})(window, window.jQuery);

function searchApr() {
    var $dom = $('#j_s_way .sd,.orderby .sd ');
    $dom.find('input.inp-txt').on('focus', function() {
        $(this).parents('.sd').addClass('sd-on');
    });
    $dom.find('.qd').on('click', function() {

        $(this).parents('.sd').removeClass('sd-on');
    });
    $dom.on('click', function() {
        return false;
    })
    $(document).on('click', function() {
        $dom.removeClass('sd-on');
    });
}


function setSearch() {

    var s = 0;
    var $li = $('#j_s_way dd ul>li');

    $('.s-hd').each(function(index) {

        if ($(this).val()>0) {

            $li.eq(index).find('.rt a').removeClass('act').end().find('input.inp-txt').val('');
            $li.eq(index).find('.rt a').eq(parseInt($(this).val())).addClass('act');
            $('#j_s_way dt .btns').eq(index).css({
                display: 'inline-block'
            }).find('.s-value').text($li.eq(index).find('.rt a').eq(parseInt($(this).val())).text());

            if (index == 1 && $(this).val() > 4) {

                $li.eq(index).find('input.inp-txt').val($(this).val());
                $('#j_s_way dt .btns').eq(index).css({
                    display: 'inline-block'
                }).find('.s-value').text($li.eq(index).find('input.inp-txt').val() + '天');
            }
        }

    });


    $('#j_s_way dd li').each(function(index) {
        //$('#j_s_way dt .btns').eq(index).find('.s-value').text($(this).find('.rt a.act').text());
        $('#j_s_way dt input[type=hidden]').eq(index).val($(this).find('.rt a.act').text());
    });

    $('#j_s_way .has-more .more').on('click', function() {
        if (!s) {
            $(this).text('收起').parent('.has-more').css({
                height: 'auto'
            });
            s = 1;
        } else {
            $(this).text('更多').parent('.has-more').css({
                height: ''
            });
            s = 0;
        }
        return false;
    });

    $('#j_s_way .rt a').on('click', function() {

        if (!$(this).hasClass('act')) {
            var index = $li.index($(this).parents('li'));
            $(this).addClass('act').siblings('a').removeClass('act');
            $li.eq(index).find('input.inp-txt').val('');

            $('#j_s_way dt .btns').eq(index).css({
                display: 'inline-block'
            }).find('.s-value').text($(this).text());
            $('.s-hd').eq(index).val($(this).attr('rv'));
            $(this).css('cursor', 'default');
            //add by duanyu start
            $("#currPageNo").val(1);
            $("#J_order").submit();
            //add by duanyu end
            if ($(this).hasClass('first')) {

                $('#j_s_way dt .btns').eq(index).find('.close').click();

            };

        } else {
            $(this).css('cursor', '');
        }

    });

    $('#j_s_way dt .btns .close').on('click', function() {
        var index = $('#j_s_way dt .btns').index($(this).parent('.btns'));
        $(this).parent('.btns').hide();
        $li.eq(index).find('.rt a').removeClass('act').eq(0).addClass('act').end();
        $li.eq(index).find('input.inp-txt').val('');
        $('.s-hd').eq(index).val(0);
        //add by duanyu start
        $("#currPageNo").val(1);
        $("#J_order").submit();
        //add by duanyu end
    });

    $('#j_s_way li .qd').on('click', function() {
        var index = $li.index($(this).parents('li'));
        if ($(this).siblings('.inp-txt').val().length) {
            $(this).parents('.sd').siblings('a').removeClass('act');
            $('#j_s_way dt .btns').eq(index).css({
                display: 'inline-block'
            }).find('.s-value').text($(this).siblings('.inp-txt').val() + '天');
            $('.s-hd').eq(index).val($(this).siblings('.inp-txt').val());
        }
    });

    /* 	$('#j_s_way li input.inp-txt').val('').on('keyup',function(){
		$(this).siblings('a').removeClass('act');
		if($(this).val().length){
			$('#j_s_way dt .btns').eq($li.index($(this).parents('li'))).css({ display:'inline-block'}).find('.s-value').text($(this).val()+'天');	
		}
	}) */



}