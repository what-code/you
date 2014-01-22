/**
 * @fileoverview 此文件是前端公用方法、定义。包含常用方法、命名空间、接口定义等,依赖框架：jQuery。 还包含公用方法的事件
 * @version 1.0
 * @author B2C F2E team
 * @public
 */
(function(window, $) {
    var arrProto = Array.prototype;
    var slice = arrProto.slice;
    if (!window['console']) {
        //兼容console调试代码，如果觉得没控制台的浏览器alert麻烦，可以设置 .window.console.debug = false;
        window.console = {
            debug: true,
            log: function() {
                this.debug && alert(slice.call(arguments));
            },
            dir: function() {
                if (!this.debug) {
                    return;
                }
                var args = slice.call(arguments);
                for (var k in args) {
                    for (var j in args[k]) {
                        alert([args[k], args[k][j], j]);
                    }
                }
            }
        };
    }
    /**
     * @description 命名空间管理，防止命名污染,具体查看man方法的doc
     *
     */
    window.NS = {
        /**
         * 管理操作的man 函数
         * @param {string} ns  命名空间的完整string
         * @param {object/function/other} fn  可选，如有此参数，则会赋值给第一个参数形成的命名空间
         * @return {object} 返回定义对象object / {}
         * @example window.NS.man('public.tab');
         * @example window.NS.man('public.tab',function(){});
         */
        man: function(ns, fn) {
            if (typeof ns === 'string') {
                ns = ns.split('.');
                var i = 0;
                var len = ns.length - 1;
                var argsLen = arguments.length;
                var root = window.NS;
                while (ns[i]) {
                    root[ns[i]] = (argsLen > 1 && i === len) ? fn : (root[ns[i]] || {});
                    root = root[ns[i]];
                    i++;
                }
                return root;
            }
        }
    };

    /**
     * @description 前端接口定义，所有接口存储在window.INTERFACE全局变量下，具体查看man方法的定义
     */
    window.INTERFACE = window.INTERFACE || {
        length: 0,
        /**
         * @description 前端接口定义，所有接口存储在window.INTERFACE全局变量下,提供man方法管理
         * @namespace window
         * @param {string} type set|get|del
         * @param {string} name 要操作的接口名
         * @param {all} value 接口内容，仅在type为set的时候起效
         * @return {window.INTERFACE}
         * @example window.INTERFACE.man('set','comment',function(){alert('commint')});//这样 其他地方就可以调用到 window.INTERFACE.comment方法了
         */
        man: function(type, name, value) {
            switch (type) {
                case 'set' :
                    if (arguments.length === 3) {
                        this[name] = value;
                        this.length++;
                    }
                    break;
                case 'get' :
                    return this[name];
                    break;
                case 'del' :
                    delete this[name];
                    this.length--;
                    break;
                default:
            }
            return this;
        }
    };
    window.NS.man('functions.supportsCss3');

    /**
     * @description 判断浏览器是否支持某项css3属性
     * @namespace NS.functions.supportsCss3
     * @return {Boolean} function(css3Name){} 返回的function，使用了闭包
     * @example window.NS.functions.supportsCss3('box-shadow');
     * @public
     */
    window.NS.functions.supportsCss3 = (function() {
        var div = document.createElement('div'),
                vendors = 'Khtml O Moz Webkit'.split(' '),
        len = vendors.length;
        /**
         * @param {String} css3AttrName css3的属性名
         * @return {Boolean}
         */
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
    })();

    window.NS.man('public.tab');
    /**
     * @description 实现tab的函数，点击几个标签切换对应的内容，依赖jQuery
     * @namespace NS.public.tab
     * @param {jQuery Object} nav 标签的jq对象，类型为jq的nodeList
     * @param {jQuery Object} content 标签对应内容的jq对象，类型为jq的nodeList
     * @param {String} navClass 标签选中时候的className 可选
     * @param {String} contentClass 内容显示时的className  可选
     * @example window.NS.public.tab($nav.find('>li'),$navCont.find('div'),'nav-hover'.'content-hover',);
     * @example window.NS.public.tab($nav.find('>li'));
     * @public
     */
    window.NS.public.tab = function(nav, content, navClass, contentClass, prev, next, fn) {
        var getPageHtml = function(pageLen) {
            var html = '';
            var i = pageLen - 1;
            while (i >= 0) {
                html = '<a ' + (i == 0 ? 'class="cur"' : '') + ' href="javascript:void(0)">' + (i + 1) + '</a>' + html;
                i--;
            }
            return html;
        };
        nav.each(function(index, item) {
            $(this).css('width', $(this).width()).addClass('slider-nav-fix');
            $(this).on('click.tab', function(e) {
                e.preventDefault();
                if (navClass) {
                    nav.filter('.' + navClass).removeClass(navClass);
                    $(this).addClass(navClass);
                }
                content.filter(':visible').scrollTop(0).stop().hide();
                content.eq(index).hide().fadeIn(800);
                if (fn) {
                    fn.call(this, getPageHtml(Math.ceil(content.eq(index).find('li').length / 4)));
                    $(this).trigger('callback');
                }
            }).find('a').on('click', function(e) {
                e.preventDefault();
            });
            content.scrollTop(0);
        }).eq(0).trigger('click.tab');
        if (prev && prev.length) {
            prev.on('click', function(e) {
                e.preventDefault();
                var navCur = navClass ? nav.filter('.' + navClass) : nav.filter(':visible');
                var newNav = navCur.prev().length ? navCur.prev() : nav.eq(-1);
                newNav.trigger('click.tab');
            });
        }
        if (next && next.length) {
            next.on('click', function(e) {
                e.preventDefault();
                var navCur = navClass ? nav.filter('.' + navClass) : nav.filter(':visible');
                var newNav = nav.index(navCur) <= nav.length - 2 ? navCur.next() : nav.eq(0);
                newNav.trigger('click.tab');
            });
        }
    };

    /*!
     * jQuery placeholder plugin
     *
     * 给不支持placeholder的浏览器增加文本域的placeholder功能，统一为浏览器默认交互的placeholder
     *
     * 使用方法：
     * 参数1  需要设置的元素
     * 参数2  placeholder的className
     * 参数3  为4个方法 分别是获取/失去焦点，显示/隐藏placeholder
     * 测试代码在../../html/placeholder.html
     * $.setPlaceholder($('input.text),'placeholder',{
     *      focus : function(){},
     *      blur  : function(){},
     *      show  : function(){},
     *      hide  : function(){}
     * })
     *
     * 注意：placeholder会优先获取元素data-placeholder属性，如果无此属性或为空，则去获取attr('placeholder')，推荐使用data-placeholder，这样浏览器不会再js加载完成后有颜色位置变化的效果
     * 注意： 如果元素是不可见的，自身或者父级设置了display none，那么在显示的时候再绑定placeholder，否则获取到的offset为0
     */
    //判断是否支持placeholder

    $.setPlaceholder = (function() {
        var _createMask = function(className) {
            var self = $(this).get(0).tagName.toLowerCase();
            var mask = document.createElement(self);//clone的话如果是input[type=password]会无法显示文字
            mask.className = this.className || '';
            mask = $(mask);
            var placeholder = $(this).attr('data-placeholder') || $(this).attr('placeholder');
            $(this).attr('data-placeholder', placeholder);
            try {
                self == 'input' && mask.attr('type', 'input');
                //在ie10的ie7模式下，此行报错（设置为空同样会报错）
                $(this).removeAttr('placeholder');
            } catch (e) {
            }
            //在offsetParent中插入元素，若为body则设置父级为offsetParent，以免改变窗口时候偏移
            var $parent = $(this).offsetParent();
            if ($parent.is('body') || $parent.is('html')) {
                $parent = $(this).parent();
                $parent.css('position', 'relative');
            }
            $parent.append(mask);
            var oParent = $parent.offset();
            var oThis = $(this).offset();
            var pos = {
                left: oThis.left - oParent.left + 'px',
                top: oThis.top - oParent.top + 'px'
            };
            mask.val(placeholder).addClass(className);
            mask.get(0).style.cssText = 'left:' + pos.left + ';top:' + pos.top + ';overflow:hidden;resize:none;outline:none;cursor:text;float:none;margin:0;text-shadow:0px 0px 0px black;border-color:transparent;position:absolute;background:none;';
            mask.attr({
                readonly: 'readonly',
                'class': $(this).attr('class')
            }).show();
            return mask;
        };
        //ie10 ie9 placeholder与其他不同，focus则消失，用js模拟
        var isie = window.navigator.userAgent.indexOf('MSIE') > -1;
        var isie9 = isie && (window.navigator.appVersion.split(";")[1].replace(/[ ]/g, "").replace('MSIE', '') | 0) === 9;
        var useCustomPh = !'placeholder' in document.createElement('input') || window.navigator.userAgent.indexOf('MSIE') >= 0;
        
        var filter = {prev: true, next: true, siblings: true};
        return function(elements, className, fns) {
            className = className || 'placeholder';
            fns = fns || {};
            elements.each(function() {
                var mask;
                var $self = $(this);
                var phIsShow = true;
                //如果设置了placeholder的元素，那么获得它的jq对象
                var labelSelector = $self.attr('data-placeholder-label');
                if (labelSelector) {
                    var _ls = labelSelector.split('|');
                    if (filter[_ls[0]]) { //如果是prev、next、siblings
                        mask = $self[_ls[0]]();
                        mask = _ls[1] ? mask.filter(_ls[1]) : mask;
                    } else {
                        mask = $(labelSelector);
                    }
                    mask = mask.eq(0);//取结果的第一个
                } else if (useCustomPh) {
                    mask = _createMask.call(this, className);
                }
                if (mask && mask.length) {
                    mask.on('mousedown', function(e) {
                        e.preventDefault();
                        $self.focus();
                    });
                }
                //设置显示
                var setStaic = function() {
                    var val = $.trim($self.val());
                    if (phIsShow === true && val !== '') {
                        phIsShow = false;
                        mask && mask.hide();
                        $.isFunction(fns.hide) && fns.hide.call($self.get(0));
                    } else if (phIsShow === false && val === '') {
                        phIsShow = true;
                        mask && mask.show();
                        $.isFunction(fns.show) && fns.show.call($self.get(0));
                    }
                };
                //绑定方法
                if (isie9) {
                    //ie9支持oninput检查value值变化(已经不支持onpropertychange了)，但backspace和delete按键不会触发oninput
                    $self.on('keyup', function(e) {
                        if (e.keyCode == 8 || e.keyCode == 46) {
                            $self.trigger('input');
                        }
                    });
                }
                $self.on('input propertychange', function(e) {
                    if (e.originalEvent && e.originalEvent.propertyName && e.originalEvent.propertyName !== 'value') {
                        return false;
                    }
                    setStaic();
                });
                //挂上focus和blur方法
                $self.on('focus.ph', function() {
                    //在ie7中，会focus到placeholder点击的位置上，以下代码是判断文本域显示了placeholder的时候，focus将光标移到到最前
                    if (phIsShow === true) {
                        try {
                            var textNode = this;
                            var Rng = textNode.createTextRange();
                            Rng.moveStart('character', 0);
                            Rng.collapse(true);
                            Rng.select();
                        } catch (e) {
                        }

                    }
                    $.isFunction(fns.focus) && fns.focus.call($self.get(0));
                });
                $self.on('blur.ph', function() {
                    $.isFunction(fns.blur) && fns.blur.call($self.get(0));
                });
                $self.data('initPlaceholder', true);
                setStaic();
            });

        };
    })();
    //快捷方式
    $.fn.placeholder = function(Options) {
        var _options = $.extend({
            className: 'placeholder'
        }, Options || {});
        $.setPlaceholder($(this), _options.className);
        return this;
    };
})(window, window.jQuery);
(function(window, $) {
    /*
     * 延迟加载功能
     * 在页面元素上设置data-delay='href'
     * href可以是图片 iframe的url 或者javascript方法格式必须是javascript:开头，"()"和“;”可以不加 同时在全局作用域下必须可以找到此方法，调用方式是js中的call(node)，此方法中的this就是需要延迟的node元素
     * demo ../../html/demo-delay.html
     * 页面加载则会获取所有需要延迟的元素
     */

    //页面没有需要延迟加载的元素 不执行以下代码
    $(function() {
        //document ready后再获取需要延迟加载的元素，以免common.js是在head区引入的，取不到元素
        var delayNodes = $('[data-delay]').toArray();
        if (delayNodes.length > 0) {

            //检查第一个data-delay的图片，如果需要显示，那么递归，反之终止操作，解除绑定事件
            var checkFirstNode = function() {
                if (delayNodes.length) {
                    var _node = $(delayNodes[0]);
                    var offsetTop = _node.offset().top;
                    var _wd = {
                        sTop: $(window).scrollTop(),
                        H: $(window).height()
                    };
                    if (offsetTop < _wd.sTop + _wd.H) {
                        var href = _node.attr('data-delay');
                        if (href.indexOf('javascript:') == 0) {
                            //如果是javascript方法，那么call方法
                            href = href.replace(/(javascript:)|(\;)|(\(\))/g, '');
                            $.isFunction(window[href]) && window[href].call(_node.get(0));
                        } else {
                            _node.attr('src', href);
                        }
                        _node.removeAttr('data-delay');
                        delayNodes = delayNodes.slice(1);//去掉第一个，循环调用
                        checkFirstNode();
                    }
                } else {
                    $(window).unbind('scroll.delay resize.delay');
                }
            };
            var _timer = false;
            //类似taobao首页，禁止浏览器记住滚动条位置，每次都从顶部开始加载
            setTimeout(function() {
                $(window).scrollTop(0);
                checkFirstNode();
                $(window).on('scroll.delay resize.delay', function() {
                    clearTimeout(_timer);
                    _timer = setTimeout(function() {
                        checkFirstNode();
                    }, 100);
                });
            }, 100);
        }
    });
    /* END delay function */
    //添加收藏
    $(function() {
        var $J_addFav = $(".J_addFav");
        if (!$J_addFav.length) {
            $J_addFav = $(".topbar a[rel=fav]");
        }
        $J_addFav.length && $J_addFav.on('click', function(e) {
            e.preventDefault();
            var url = encodeURI(window.location.host);
            var title = $('title').html();
            if (window.external) {
                try {
                    window.external.addFavorite(window.location.href, document.title);
                } catch (e) {
                    alert("加入收藏失败，请使用Ctrl+D进行添加");
                }

            } else if (window.sidebar) {
                window.sidebar.addPanel(document.title, window.location.href, "");
            } else {
                alert("加入收藏失败，请使用Ctrl+D进行添加");
            }
        });
    });

    //返回顶部
    var $goTop = $('<a href="javascript:void(0)" class="gotop" title="返回顶部" hidefocus="true"></a>').appendTo(document.body);

    var scroll = true;
    var goTop = {
        isHide: true,
        show: function() {
            if (this.isHide) {
                this.isHide = false;
                $goTop.show().addClass('gotop-show');
            }
        },
        hide: function() {
            if (this.isHide === false) {
                this.isHide = true;
                $goTop.removeClass('gotop-show');
            }

        }
    };//点击返回顶部，隐藏自己，同时返回顶部动画期间不执行onscroll事件
    $goTop.on('click', function(e) {
        e.preventDefault();
        scroll = false;
        goTop.hide();
        $('html,body').animate({scrollTop: 0}, function() {
            scroll = true;
            $goTop.hide();

        });
    });

    var showGotop = function() {
        var t = $(window).scrollTop();
        if (t < 100) {
            goTop.hide();
        } else {
            goTop.show();
        }
    };
    if ($goTop.css('position') === 'fixed') {
        // $goTop.css('right', ($(document.body).width() - 980) / 2 - 60);
        $(window).on('scroll.gotop', function() {
            scroll && showGotop();
        }).trigger('scroll.top');
    } else {
        //ie6不支持fixed，用js来做
        // if ($goTop.css('display') == 'block') {
        //     // $goTop.show().css('top', $(window).height() - 130 + $(window).scrollTop());
        // }
        // $goTop.css('left', ($(document.body).width() - 980) / 2 + 980 + 20).css('right', 'auto');
        var timer = false;
        $(window).on('scroll.gotop', function() {
            if (scroll) {
                clearTimeout(timer);
                timer = setTimeout(function() {
                    var t = $(window).scrollTop();
                    if ($goTop.css('display') == 'none' && $(window).scrollTop() >= 100) {
                        $goTop.show().css('top', $(window).height() - 130 + t);
                    } else {
                        $goTop.css({top: $(window).height() - 130 + t}).fadeIn(300, function() {
                            if ($(window).scrollTop() < 100) {
                                $goTop.hide();
                            } else {
                                $goTop.show();
                            }
                        });
                    }
                }, 300);
            }
        });
    }

})(window, window.jQuery);