//base Object ext
showPages.prototype.createBaseHtml = function(){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1 ;
	
	if (prevPage < 1) {
		strHtml += '<a>首页</a>';
		strHtml += '<a>上一页</a>';
	} else {
		strHtml += '<a href="' + this.createUrl(1) + '">首页</a>';
		strHtml += '<a href="' + this.createUrl(prevPage) + '">上一页</a>';
	}
	if (this.page != 1) strHtml += '<a>1</a>';
	if (this.page >= 5) strHtml += '<a>...</a>';
	if (this.pageCount > this.page + 2) {
		var endPage = this.page + 2;
	} else {
		var endPage = this.pageCount;
	}
	for (var i = this.page - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == this.page) {
				strHtml += '<a href="' + this.createUrl(1) + '">' + i + '</a>';
			} else {
				if (i != 1 && i != this.pageCount) {
					strHtml += '<a href="' + this.createUrl(i) + '">' + i + '</a>';
				}
			}
		}
	}
	if (this.page + 3 < this.pageCount) strHtml += '<a>...</a>';
	if (this.page != this.pageCount) strHtml += '<a href="' + this.createUrl(this.pageCount) + '">' + this.pageCount + '</a>';
	if (nextPage > this.pageCount) {
		strHtml += '<a>下一页</a>';
		strHtml += '<a>尾页</a>';
	} else {
		strHtml += '<a href="' + this.createUrl(nextPage) + '">下一页</a>';
		strHtml += '<a href="' + this.createUrl(this.pageCount) + '">尾页</a>';
	}

	return strHtml;
}

showPages.prototype.printBaseHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	
	document.write('<div id="pages_' +  this.showTimes + '" class="w_ys"></div>')
	document.getElementById('pages_' +  this.showTimes).innerHTML = this.createBaseHtml(mode);
}