$(document).bind(
		'click.price',
		function(e) {
			var target = $(e.target);
			if ($('.filter-price-input_on').length
					&& !target.is('.filter-price-input_on')
					&& !target.parents('.filter-price-input_on').length) {
				price_input.removeClass('filter-price-input_on');
			}
		});

function rankHover(obj) {
	var oParent = $(obj);
	var aLi = oParent.find('li');
	aLi.hover(function() {
		$(this).css({
			'background' : '#ffede4'
		});
	}, function() {
		$(this).css({
			'background' : ''
		});
	});
}

function addClassPos() {
	$('#pos a').click(function() {
		$(this).addClass('cur').siblings().removeClass('cur');
	});

	$('.filter-classify a').click(function() {
		$(this).addClass('cur').siblings().removeClass('cur');
	});
}

function startMove() {
	var oPlugTools = $('.plug-tools img');
	oPlugTools.hover(function() {
		oPlugTools.stop(true).animate({
			'margin-top' : '0'
		});

	}, function() {
		oPlugTools.stop(true).animate({
			'margin-top' : '10px'
		});
	});
}

function modHover() {
	$("body").on("mouseenter", '.mod', function() {
		$(this).addClass('cur');
	});
	$("body").on("mouseleave", '.mod', function() {
		$(this).removeClass('cur');
	});
}

$('.logged .per').hover(function() {
	$(this).addClass('hover');
}, function() {
	$(this).removeClass('hover');
});

rankHover('.rank');
addClassPos();
startMove();
// ie6不支持:hover选择器，由jquery添加，
if (typeof document.body.style.maxHeight === "undefined") {
	modHover();
}

$(function() {
//	(function check_win() {
//		var win_w = window.screen.availWidth;
//		if (win_w >= 1400) {
//			$('.w980').addClass('w1200').removeClass('w980');
//		}
//	})();
	$('body').on('keyup', '#page_num', function(e) {
		this.value = this.value.replace(/\D/g, "");
	});
	$('body').on('click', '#go_page', function(e) {
		var val = $('#page_num').val();
		pageEvent(val);
	});
});
