  function navBanner(banner_bg_color,nav_color) {
        var banners = $('#J_banner');
        var bg_left = banners.find('.banner-item');
        bg_left.each(function(key) {
            $(this).css('background', banner_bg_color[key][0]);
            $(this).find('.bg-right').css('background', banner_bg_color[key][1]);
        });

        var navs_link = $("#J_banner div.nav a").filter(':not(.last)');
        var nav = $('#J_banner div.nav');
        var time = null;
        var speed = 3000;
        var key = 0;
        var setCur = function(i) {
            key = i;
            navs_link.removeClass('current').find('span.bg').css('background', 'black');
            navs_link.eq(key).addClass('current').find('span.bg').css('background', nav_color[key]);
            navs_link.eq(key).find('span.tips').css('border-bottom-color', nav_color[key]);
            if (bg_left.filter('.cur').length) {
                bg_left.filter('.cur').stop().animate({opacity: 0.4}, 200, function() {
                    $(this).removeClass('cur').hide();
                    bg_left.eq(key).css({opacity: 0.4, display: 'block'}).stop().animate({opacity: 1}, 300, function() {
                        $(this).addClass('cur');
                    });
                });
            } else {
                bg_left.eq(key).css({opacity: 0.4, display: 'block'}).stop().animate({opacity: 1}, 300, function() {
                    $(this).addClass('cur');
                });
            }


            var link = bg_left.eq(key).find('a.r');
            var title = link.attr('title');
            link = link.attr('href');
            nav.find('a.last').attr('href', link).attr('title', title).find(".text").html(title);

        };
        var start = function() {
            navs_link.find('span.text').mouseover(function() {
                if (!$(this).parent().hasClass('current')) {
                    setCur(navs_link.index($(this).parent()));
                }
            });
            var move = function() {
                time = setTimeout(function() {
                    key++;
                    if (key > 3) {
                        key = 0;
                    }
                    setCur(key);
                    setTimeout(function() {
                        move();
                    }, 4000);
                }, 4000);
            };
            $(".banner-warp,.nav").hover(function() {
                clearTimeout(time);
            }, function() {
                move();
            });
            move();
        };

        var image = new Image();
        image.onload = function() {
            bg_left.find('.banner-link > img').eq(0).attr('src', this.src);
            setCur(0, true);
            start();
            $(this).hide().unbind().remove();
        };
        image.src = bg_left.find('.banner-link > img').eq(0).attr('data-src')+'?'+Math.random();
        $(image).appendTo(document.body);
        bg_left.find('.banner-link > img').eq(0).removeAttr('data-src').show();

        bg_left.each(function() {
            $(this).find('.banner-link > img').attr('data-src') && $(this).find('.banner-link > img').attr('src', $(this).find('.banner-link > img').attr('data-src')).removeAttr('data-src');
        });
    }