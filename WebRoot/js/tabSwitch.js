/**
 *
 */
;(function($){
    $.fn.tabSwitch = function(options){
        options = $.extend({
            tab: '#tab li',
            tabClass: 'current',
            tabFrame: '.tab-content',
            tabEvent: 'hover',
            tabDefault: 0
        }, options);
        var _btnTab = $(options.tab),
            tabClass = options.tabClass,
            _tabFrame = $(options.tabFrame),
            tabEvent = options.tabEvent,
            tabDefault = options.tabDefault,
            intervalId,
            curLi;
        _btnTab.removeClass(tabClass).eq(tabDefault).addClass(tabClass);
        _tabFrame.hide().eq(tabDefault).show();
        if (tabEvent == 'hover') {
            _btnTab.on({
                mouseenter: function(){
                    curLi = $(this);
                    intervalId = setInterval(onSwitch, 150);
                },
                mouseleave: function(){
                    clearInterval(intervalId);
                }
            });
        } else if (tabEvent == 'click') {
            _btnTab.on('click',function(){
                curLi = $(this);
                onSwitch();
            });
        }
        function onSwitch(){
            _tabFrame.hide();
            _tabFrame.eq(_btnTab.index(curLi)).show();

            _btnTab.removeClass(tabClass);
            curLi.addClass(tabClass);
        }
    };
})(jQuery);