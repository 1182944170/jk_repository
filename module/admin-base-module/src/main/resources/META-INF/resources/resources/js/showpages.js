/*
showPages v1.1
----------------------
var pg = new showPages('pg');
pg.pageCount = 12; //定义总页数(必要)
pg.argName = 'p';    //定义参数名(可选,缺省为page)
pg.printHtml();        //显示页数


Supported in Internet Explorer, Mozilla Firefox
*/

function showPages(link,pageCount,totalCount,arg,argName,costTime, showTimes) { //初始化属性
	this.link = link;
	this.page = 1;         //当前页数
	this.pageCount = 1;    //总页数
	this.argName = 'pager'; //参数名
	this.showTimes = 1;    //打印次数,如页面出现多个page分页，则new时需指定
	this.totalCount=totalCount;
	this.costTime = costTime;
	this.arg="1_";
	if(pageCount && !isNaN(parseInt(pageCount))){
		this.pageCount = pageCount;
	}
	if(arg)
		this.arg = arg;
	if(showTimes)
		this.showTimes = showTimes;
	if(argName)
		this.argName = argName;
}

showPages.prototype.getPage = function(){ //丛url获得当前页数,如果变量重复只获取最后一个
	if(this.arg){
		this.page = this.arg.split('_')[0];
	}
	this.arg = this.arg.split('_')[1];
	/**
	var args = location.search;
	var reg = new RegExp('[\?&]?' + this.argName + '=([^&]*)[&$]?', 'gi');
	var chk = args.match(reg);
	this.page = RegExp.$1;*/
}
showPages.prototype.checkPages = function(){ //进行当前页数和总页数的验证
	if (isNaN(parseInt(this.page))) this.page = 1;
	if (isNaN(parseInt(this.pageCount))) this.pageCount = 1;
	if (isNaN(parseInt(this.showTimes))) this.showTimes = 1;
	this.page = parseInt(this.page);
	this.pageCount = parseInt(this.pageCount);
	
	if (this.page < 1) this.page = 1;
	if (this.pageCount < 1) this.pageCount = 1;
	if (this.page > this.pageCount) this.page = this.pageCount;
}

showPages.prototype.createHtml = function(mode){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1 ;
	//strHtml += '<span class="count">共' + this.pageCount + '页 &nbsp当前第 <strong>' + this.page + '</strong> 页&nbsp;</span>';
	strHtml += '<span class="number">';
	if (prevPage < 1) {
		strHtml += '<span title="首页" class="disabled">首页</span>&nbsp;';
		strHtml += '<span title="上一页"  class="disabled">上一页</span>&nbsp;';
	} else {
		strHtml += '<span title="首页"><a href="' + this.createUrl(1) + '">首页</a></span>&nbsp;';
		strHtml += '<span title="上一页"><a href="' + this.createUrl(prevPage) + '">上一页</a></span>&nbsp;';
	}
	if (this.page != 1) strHtml += '<span title="Page 1"><a href="' + this.createUrl(1) + '">1</a></span>';
	if (this.page >= 5) strHtml += '<span>...</span>';
	if (this.pageCount > this.page + 2) {
		var endPage = this.page + 2;
	} else {
		var endPage = this.pageCount;
	}
	for (var i = this.page - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == this.page) {
				strHtml += '<span title="Page ' + i + '" class="current">' + i + '</span>';
			} else {
				if (i != 1 && i != this.pageCount) {
					strHtml += '<span title="Page ' + i + '"><a href="' + this.createUrl(i) + '">' + i + '</a></span>';
				}
			}
		}
	}
	if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
	if (this.page != this.pageCount) strHtml += '<span title="Page ' + this.pageCount + '"><a href="' + this.createUrl(this.pageCount) + '">' + this.pageCount + '</a></span>';
	if (nextPage > this.pageCount) {
		strHtml += '&nbsp;<span title="下一页" class="disabled">下一页</span>';
		strHtml += '&nbsp;<span title="尾页"  class="disabled">尾页</span>';
	} else {
		strHtml += '&nbsp;<span title="下一页"><a href="' + this.createUrl(nextPage) + '">下一页</a></span>';
		strHtml += '&nbsp;<span title="尾页"><a href="' + this.createUrl(this.pageCount) + '">尾页</a></span>';
	}
	strHtml += '</span><br />';

	return strHtml;
}


//hongtu ext
showPages.prototype.createHongtTuHtml = function(mode){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1 ;
	
	if (prevPage < 1) {
		strHtml += '<a alt="上一页" class="pre" style="display:none">上一页</a>&nbsp;';
	} else {
		strHtml += '<a href="' + this.createUrl(prevPage) + '">上一页</a>&nbsp;';
	}
	if (this.page != 1) strHtml += '<a href="' + this.createUrl(1) + '" alt="Page 1">1</a>';
	if (this.page >= 5) strHtml += '<span>...</span>';
	if (this.pageCount > this.page + 2) {
		var endPage = this.page + 2;
	} else {
		var endPage = this.pageCount;
	}
	for (var i = this.page - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == this.page) {
				strHtml += '<a alt="Page ' + i + '" class="cur">' + i + '</a>';
			} else {
				if (i != 1 && i != this.pageCount) {
					strHtml += '<a href="' + this.createUrl(i) + '" alt="Page ' + i + '">' + i + '</a>';
				}
			}
		}
	}
	if (this.page + 3 < this.pageCount) strHtml += '<span>...</span>';
	if (this.page != this.pageCount) strHtml += '<a href="' + this.createUrl(this.pageCount) + '" alt="Page ' + this.pageCount + '">' + this.pageCount + '</a>';
	if (nextPage > this.pageCount) {
		strHtml += '<a alt="下一页" class="next" style="display:none">下一页</a>&nbsp;';
	} else {
		strHtml += '<a alt="下一页" class="next" href="' + this.createUrl(nextPage) + '">下一页</a>&nbsp;';
	}
	
	strHtml +='<span>跳转至第<input type="text" id="gotoLink" class="pn">页<input style="width:15px;" onclick=_showPages.gotoLink("gotoLink") value="Go" class="su">共'+this.totalCount+'条，'+this.pageCount+'页</span>'
	return strHtml;
}

//base Object ext
showPages.prototype.createBaseHtml = function(){ //生成html代码
	var strHtml = '', prevPage = this.page - 1, nextPage = this.page + 1 ;
	strHtml += '<p>';
	strHtml +='共&nbsp;<i class="blue">'+this.totalCount+'</i>&nbsp;条记录，&nbsp;共&nbsp;<i class="blue">'+this.pageCount+'&nbsp;</i>页&nbsp;';
	
	if(this.costTime > 0) {
		strHtml += "耗时<i class='red'>" + this.costTime+"</i>&nbsp;毫秒&nbsp;";
	}
	
	if (prevPage < 1) {
		strHtml += '<span title="首页" class="label label-lg label-grey arrowed-right">首页</span>&nbsp;';
		strHtml += '<span title="上一页"  class="label label-lg label-grey arrowed-in arrowed-right">上一页</span>&nbsp;';
	} else {
		strHtml += '<span title="首页" class="label label-lg label-light arrowed-right"><a href="' + this.createUrl(1) + '">首页</a></span>&nbsp;';
		strHtml += '<span title="上一页" class="label label-lg label-light arrowed-in arrowed-right"><a href="' + this.createUrl(prevPage) + '">上一页</a></span>&nbsp;';
	}
	if (this.page != 1) strHtml += '<span title="Page 1" class="label label-lg label-light arrowed-in arrowed-right"><a href="' + this.createUrl(1) + '">1</a></span>';
	if (this.page >= 5) strHtml += '<span class="label label-lg label-light arrowed-in arrowed-right">...</span>';
	if (this.pageCount > this.page + 2) {
		var endPage = this.page + 2;
	} else {
		var endPage = this.pageCount;
	}
	for (var i = this.page - 2; i <= endPage; i++) {
		if (i > 0) {
			if (i == this.page) {
				strHtml += '<span title="Page ' + i + '" class="label label-lg label-primary arrowed-in arrowed-right">' + i + '</span>';
			} else {
				if (i != 1 && i != this.pageCount) {
					strHtml += '<span title="Page ' + i + '" class="label label-lg label-light arrowed-in arrowed-right"><a href="' + this.createUrl(i) + '">' + i + '</a></span>';
				}
			}
		}
	}
	if (this.page + 3 < this.pageCount) strHtml += '<span class="label label-lg label-light arrowed-in arrowed-right">...</span>';
	if (this.page != this.pageCount) strHtml += '<span title="Page ' + this.pageCount + '" class="label label-lg label-light arrowed-in arrowed-right"><a href="' + this.createUrl(this.pageCount) + '">' + this.pageCount + '</a></span>';
	if (nextPage > this.pageCount) {
		strHtml += '&nbsp;<span title="下一页" class="label label-lg label-grey arrowed-in arrowed-right">下一页</span>';
		strHtml += '&nbsp;<span title="尾页"  class="label label-lg label-grey arrowed-in">尾页</span>';
	} else {
		strHtml += '&nbsp;<span title="下一页" class="label label-lg label-light arrowed-in arrowed-right"><a href="' + this.createUrl(nextPage) + '">下一页</a></span>';
		strHtml += '&nbsp;<span title="尾页" class="label label-lg label-light arrowed-in"><a href="' + this.createUrl(this.pageCount) + '">尾页</a></span>';
	}
	strHtml += '</p>';

	return strHtml;
}

showPages.prototype.createUrl = function (page) { //生成页面跳转url
	if (isNaN(parseInt(page))) page = 1;
	if (page < 1) page = 1;
	if (page > this.pageCount) page = this.pageCount;
	
	var f = '?';
	if(this.link.indexOf('?') >= 0) {
		f = '&';
	}
	return this.link + f + this.argName + '=' + page + '_' + this.arg;
}

showPages.prototype.gotoLink = function(id){
	var p = document.getElementById(id).value;
	window.location.href = this.createUrl(p);
}

showPages.prototype.printHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' +  this.showTimes + '" class="manu"></div>');
	document.getElementById('pages_' +  this.showTimes).innerHTML = this.createHtml(mode);
}
showPages.prototype.printImgHtml = function(){
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<span class=sale_turnpage id="pages_img_' +  this.showTimes + '"></span>');
	document.getElementById('pages_img_' +  this.showTimes).innerHTML = this.createImgHtml();
}

showPages.prototype.printHongTuHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	document.write('<div id="pages_' +  this.showTimes + '" class="pagination pnb"></div>');
	document.getElementById('pages_' +  this.showTimes).innerHTML = this.createHongtTuHtml(mode);
}

showPages.prototype.printBaseHtml = function(mode){ //显示html代码
	this.getPage();
	this.checkPages();
	this.showTimes += 1;
	
	document.write('<div id="pages_' +  this.showTimes + '" class="pull-right"></div>');
	document.getElementById('pages_' +  this.showTimes).innerHTML = this.createBaseHtml(mode);
}