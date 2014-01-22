(function($){
    //tab切换
    var tabOuter = $('#J_tab'),
        tabInner = $('#J_tab_inner'),
        o_tab = tabOuter.find('.layout-youSearch-menu li'),
        tabClass  = 'tabCur',
        o_tabFrame = tabOuter.find('.tabCont'),
        i_tab = tabInner.find('.tab-nav span'),
        i_tabFrame = tabInner.find('.layout-youSearch-hotel'),
        tabEvent = 'click',
        youType = parseInt($('#youType').val()),
        hotelType = parseInt($('#hotelType').val());
    if (isNaN(youType)) {
        youType = 0;
    } else if (youType < 0) {
        youType = 0;
    } else if (youType > 2) {
        youType = 2;
    }
    if (isNaN(hotelType)) {
        hotelType = 0;
    } else if (hotelType < 0) {
        hotelType = 0;
    } else if (youType > 2) {
        hotelType = 2;
    }
    tabOuter.tabSwitch({
        tab: o_tab,
        tabClass: tabClass,
        tabFrame: o_tabFrame,
        tabEvent: tabEvent,
        tabDefault: youType
    });
    tabInner.tabSwitch({
        tab: i_tab,
        tabClass: tabClass,
        tabFrame: i_tabFrame,
        tabEvent: tabEvent,
        tabDefault: hotelType
    });
    var _city = $('.layout-cityCountryPop');
    _city.each(function(){
        var _self = $(this),
            _tab = _self.find('.nav-citypop li'),
            _tabFrame = _self.find('.tabCont');
        _self.tabSwitch({
            tab: _tab,
            tabClass: tabClass,
            tabFrame: _tabFrame,
            tabEvent: tabEvent
        });
    });
    //攻略->更多
    var _guide = $('.trigger-more');
    _guide.each(function(){
       var _self = $(this),
           _guide_tit = _self.find('.trigger-more-list');
       _self.on({
           mouseenter: function(){
               _self.addClass('trigger-more-cur');
               _guide_tit.show();
           },
           mouseleave: function(){
               _self.removeClass('trigger-more-cur');
               _guide_tit.hide();
           }
       });
    });
    $('#J_morecity').mouseenter(function(){
        var _self = $(this);
        if (!_self.children().hasClass('.layout-cityCountryPop')){
            var _html = city_detail.eq(1).clone();
            _self.append(_html);
            var morecity = _self.find('.layout-cityCountryPop');
            morecity.show().css({
                top: '100%',
                right: -3,
                left: ''
            });
            morecity.find('.ico-close-cityPop').hide();
            var _tab = morecity.find('.nav-citypop li'),
                _tabFrame = morecity.find('.tabCont');
            morecity.tabSwitch({
                tab: _tab,
                tabClass: tabClass,
                tabFrame: _tabFrame,
                tabEvent: tabEvent
            });
        }
    }).mouseleave(function(){
        var _self = $(this);
        _self.find('.layout-cityCountryPop').detach();
    });
    //城市选择
    function showCity(elem,city_detail){
        elem.click(function(e){
            e.stopPropagation();
        });
        elem.focus(function(){
            var _height = elem.innerHeight(),
                _borderTop = parseInt(elem.css('border-top-width')),
                _top = elem.offset().top,
                _left = elem.offset().left;
            elem.select();
            $('#city-content').append(city_detail);
            var city = $('#city-content').find('.layout-cityCountryPop');
            city.show().css({
                top:_top + _height + _borderTop,
                left:_left
            }).find('.tabCont a').click(function(e){
                e.preventDefault();
                var _data = $(this).attr('data'),
                    _arr = [];
                    /*_start = _data.indexOf('|'),
                    _end = _data.indexOf('|',_start+1),
                    _str = _data.substring(_start+1,_end);*/
                _arr = _data.split('|');

                elem.val(_arr[0]);
                elem.parent().find('.hidden_city').val(_arr[1]);
                city.remove();
                _arr = null;
            });
            city.on('click', function(e){
                e.stopPropagation();
            });
            $(document).one('click', function(){
                city.remove();
            });
            city.find('.ico-close-cityPop').on('click', function(){
                city.remove();
            });
        });
    }
    var city_detail = $('.layout-cityCountryPop');
    showCity($('.J_city_cn'),city_detail.eq(1));
    showCity($('.J_city_en'),city_detail.eq(0));
    showCity($('.you-start'),city_detail.eq(1));
    //搜索历史
    $('.youSearch-item-history').on('click',function(e){
        e.stopPropagation();
        var _self = $(this);
        var slt_list = _self.find('.trigger-slt-list');
        if (slt_list.find('u').length) {
            _self.toggleClass('trigger-slt-cur');
            slt_list.stop(true,true).toggle();
            slt_list.on({
                mouseenter: function(){
                    $(this).addClass('sltCur');
                },
                mouseleave: function(){
                    $(this).removeClass('sltCur');
                },
                click: function(e){
                    e.stopPropagation();
                    var _val = $(this).html();
                    $('.J_city_cn').val(_val);
                    slt_list.hide();
                    _self.removeClass('trigger-slt-cur');
                }
            },'u');
        }
        $(document).one('click', function(){
            slt_list.hide();
            _self.removeClass('trigger-slt-cur');
        });
    });
    //酒店级别
    var hotelIndex, hotelId,
        hotelStar = $('#hotelStar').val();
    if (hotelStar === '') {
        hotelIndex = 0;
        hotelId = '';
    } else {
        hotelStar = parseInt($('#hotelStar').val());
        if (isNaN(hotelStar)) {
            hotelStar = 0;
        }
        switch (hotelStar) {
            case 5:
                hotelIndex = 1;
                hotelId = 5;
                break;
            case 4:
                hotelIndex = 2;
                hotelId = 4;
                break;
            case 3:
                hotelIndex = 3;
                hotelId = 3;
                break;
            case 2:
            case 1:
            case 0:
                hotelIndex = 4;
                hotelId = 0;
                break;
            default :
                hotelIndex = 0;
                hotelId = '';
        }
    }
    $('.select-yousearch').each(function(){
        var _self = $(this),
            _u = _self.find('.trigger-slt-list u').eq(hotelIndex),
            _html = _u.html(),
            _id = _u.attr('data-id');
        _self.find('em').html(_html);
        if (hotelId === '') {
            _self.find('.hidden-level').val('');
        } else {
            _self.find('.hidden-level').val(_id);
        }
    });
    $('.select-yousearch').on('click', function(e){
        e.stopPropagation();
        var _self = $(this),
            slt_list = _self.find('.trigger-slt-list'),
            _em = _self.find('em');
        _self.find('.trigger-slt-list').stop(true,true).toggle();
        slt_list.on({
            mouseenter: function(){
                $(this).addClass('sltCur');
            },
            mouseleave: function(){
                $(this).removeClass('sltCur');
            },
            click: function(e){
                e.stopPropagation();
                var _val = $(this).html(),
                    _data_id = $(this).attr('data-id');
                _em.html(_val);
                _self.find('.hidden-level').val(_data_id);
                slt_list.hide();
            }
        },'u');
        $(document).one('click', function(){
            slt_list.hide();
        });
    });
    //选择日期
    $( ".dateStart").each(function(i){
        $( ".dateStart").eq(i).datepicker({
            onClose: function( selectedDate ) {
                $( ".dateEnd").eq(i).datepicker( "option", "minDate", selectedDate, "yyyy-mm-dd" );
            }
        });
        $( ".dateEnd").eq(i).datepicker({
            onClose: function( selectedDate ) {
                $( ".dateStart" ).eq(i).datepicker( "option", "maxDate", selectedDate, "yyyy-mm-dd" );
            }
        });
    });
    $('.hasDatepicker').focus(function(){
        $(this).select();
    });
})(jQuery);