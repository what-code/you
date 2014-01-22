(function($) {

	$.fn.insertDownList = function(url, arr, fn) {
		this.each(function() {

			creatDom.call(this, arr);

			this.con = $(this).parent('.dl_con');
			this.dr = this.con.find('.dr_con');



			bindEvent.call(this, url, fn);
			setStyle.call(this);

		});

		function creatDom(arr) {

			$(this).wrap('<div class="dl_con"></div>');
			$(this).addClass('dl_input');

			$(this).parent('.dl_con').append('<div class="dr_con"><ul class="dr_list"></ul></div>');

			if (!$(this).parent().find('.ajaxCollection').length) { //[ajaxCollection,hdnCurIndex,hdnKeyTemp,hdnTarget]

				$(this).after('<input autocomplete="off" type="hidden" class="ajaxCollection"  />\
								<input autocomplete="off" type="hidden" class="hdnCurIndex" />\
								<input autocomplete="off" type="hidden" class="hdnKeyTemp" />\
								<input autocomplete="off" type="hidden" class="hdnTarget" >')
			}
			$(this).parent().find('input[type=hidden]').each(function(index) {
				$(this).val(arr[index]);
			})
		}

		function setStyle() {

			this.con.css({
				position: 'relative',
				float: $(this).css('float'),
				'verticalAlign': $(this).css('verticalAlign'),
				display: 'inline-block'

			});
			this.dr.css({
				position: 'absolute',
				left: $(this).css('marginLeft'),
				top: $(this).outerHeight() + 'px',
				width: $(this).outerWidth() + 'px'
			});

		}

		function bindEvent(url, fn) {
			var _this = this;
			$(this).on({
				keyup: function(e) {
					getAutoFill(e, url, fn);
				},
				keydown: function(e) {
					selectBykeyEvent(e, _this.con.get(0))
				}
			})
		}
		return this;
	}

}(jQuery));