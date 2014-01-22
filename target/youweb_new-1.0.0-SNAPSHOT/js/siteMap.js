$.fn.hideList = function(child, maxRowNum, colNum) {
	return this.each(function() {
		var listNum = $(this).find(child).size();
		if (listNum <= maxRowNum * colNum) {
			return;
		}

		var vCNum = maxRowNum * colNum - 2;
		var more, hideMore = true,
			_this = this;
		var list_hiden = $(this).find(child).filter(':gt(' + vCNum + ')').hide();

		more = $(this).find(child).eq(0).clone().html('查看更多>').addClass('more');
		$(this).append(more);
		more.on('click', function() {

			toggleMore();
			return false;
		});

		function toggleMore() {
			if (hideMore) {
				list_hiden.show();
				more.html('收起>');
				hideMore = false;
			} else {

				list_hiden.hide();
				more.html('查看更多>');
				hideMore = true;
			}

		}
	});
};

$(function() {
	$('.list-con').hideList('a', 5, 6);
});
