if (!Object.create) {
	Object.create = function (o) {
		function F() {}
		F.prototype = o;
		return new F();
	};
}

String.prototype.replaceAll = function (reallyDo, replaceWith, ignoreCase) {
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi" : "g")), replaceWith);
	} else {
		return this.replace(reallyDo, replaceWith);
	}
};
/*判断该值是否是数字类型*/
String.prototype.isNumber = function () {
	if (!/^\d+(\.\d+)?$/.test(this)) {
		return false;
	}
	try {
		return parseFloat(this) == this;
	}catch (ex) {
		return false;
	}
};
/*去掉俩段的空格*/
String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.endWith=function(oString){
  var   reg= new RegExp(oString+"$");   
  return   reg.test(this);
}

/**非空判断*/
function isNotBlank(o){
	return (o && o.trim() != '') ;
}
function isBlank(o){
	return !isNotBlank(o) ;
}


//宏定义
RP = {};
RP.B = {};
B = RP.B;