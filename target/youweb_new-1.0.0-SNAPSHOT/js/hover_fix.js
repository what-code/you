/**
 * 修正ie6下非a标签不支持:hover伪类，引入文件后会绑定事件
 * 调用方法:
 *   <!--[if IE 6 ]>
 *       <script type="text/javascript" src="../scripts/common/hover_fix.js"></script>
 *   <![endif]-->
 * 同时如果是js创建或者ajax生产的元素，这是可以手动调用，使用方法：
 * 第一个参数是需要绑定的元素，html对象，第二个是data-hover值字符串，也就是hover上去需要修改的class名称，注意判断，因为此window.ATTACH_HOVER方法仅在ie6中存在
 * window.ATTACH_HOVER && window.ATTACH_HOVER(jQuery('.hoverFix'));
 */
(function($) {
    var bindHoverFix = function(elements) {
        if (elements.length) {
            elements.each(function() {
                var className = $(this).attr('data-hover');
                $(this).on('mouseenter.hover', function() {
                    $(this).addClass(className);
                }).on('mouseleave.hover', function() {
                    $(this).removeClass(className);
                });
            });
        }
    };
    $('[data-hover]').each(function() {
        bindHoverFix($(this));
    });
    bindHoverFix($('[data-hover]'));
    return window.ATTACH_HOVER = bindHoverFix;
})(jQuery);