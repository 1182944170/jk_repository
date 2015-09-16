/* jQuery.Spinner V1.0 CopyRight (c) 2014 by:Loyaoo Taobao:http://isseven.taobao.com */
(function($) {
	
	$.fn.Spinner1 = function (opts) {
		var v = $("#inputIds").val();
		v = v.substring(0,v.length-1);
		var arr;
		var mv="";
		var m=mm(v);
		setm(m);
		v = v.indexOf(",") ? v.split(",") : v ;
		var defaults = {value:opts, min:1, len:3, max:999}
		var options = $.extend(defaults, opts)
		var keyCodes = {up:38, down:40}
		return this.each(function(i) {
		    defaults = {value:v[i], min:1, len:3, max:999}
		    options = $.extend(defaults, opts)
		    keyCodes = {up:38, down:40}
			var a = $('<a></a>'); f(a,0,"Increase","");	//减
			var c = $('<a></a>'); f(c,0,"Decrease","");	//加
			var b = $('<input />');
					f(b,1,"Amount");
					cv(0);	//值
			
			$(this).append(a).append(b).append(c);
			a.click(function(){
				cv(-1);
				var price = $(this).parent().parent().parent().find("li").eq(0).find("strong").text();//单价
				var coupon = $(this).parent().parent().parent().find("li").eq(1).find("em").text();//优惠券
				var num = $(this).parent().parent().parent().find(".Amount").val();//数量
                var sum=price*num;//总价
				var sumdiv = $(this).parent().parent().parent().find(".w_zj");
				sumdiv.html(parseFloat(sum).toFixed(2));
				mv="";
				$(".Amount").each(function(i){
					if(i>0) mv +=",";mv +=$(this).val();
				})
				arr=mm(mv);
				setm(arr);
				arr="";
				getSum();
			});
			b.keyup(function(){cv(0)});
			c.click(function(){
				cv(+1);
				var price = $(this).parent().parent().parent().find("li").eq(0).find("strong").text();//单价
				var coupon = $(this).parent().parent().parent().find("li").eq(1).find("em").text();//优惠券
				var num = $(this).parent().parent().parent().find(".Amount").val();//数量
				
				var sum=price*num;//总价
				var sumdiv = $(this).parent().parent().parent().find(".w_zj");
				sumdiv.html(parseFloat(sum).toFixed(2));
				mv="";
				$(".Amount").each(function(i){
					if(i>0) mv +=",";mv +=$(this).val();
				})
				arr=mm(mv);
				setm(arr);
				arr="";
				getSum();
			});
			b.bind('keyup paste change',function(e){
				e.keyCode==keyCodes.up&&cv(+1);
				e.keyCode==keyCodes.down&&cv(-1);
				mv="";
				$(".Amount").each(function(i){
					if(i>0) mv +=",";mv +=$(this).val();
				})
				arr=mm(mv);
				setm(arr);
				arr="";
			});
			
			function cv(n){
				b.val(b.val().replace(/[^\d]/g,''));
				bv=parseInt(b.val()||options.min)+n;
				bv>=options.min&&bv<=options.max&&b.val(bv);
				if(bv>=options.max){
					b.val(options.max);f(a,2,"DisIn","Increase");
				}else{
					f(a,2,"Increase","DisIn")
					}
				if(bv<=options.min){
					b.val(options.min);f(c,2,"DisDe","Decrease");
					}else{
					f(c,2,"Decrease","DisDe");
				}
				
		   }
			
		});

		function f(o,t,a,s){
			t==0&&o.addClass(a).attr("href","javascript:void(0)").append("<i></i>").find("i").append(s);
			t==1&&o.addClass(a).attr({"value":options.value,"autocomplete":"off","maxlength":options.len});
			t==2&&o.addClass(a).removeClass(s);
		}
		function mm(o){
			var flag = false;
			var money="";
			var l = o.indexOf(",")>0 ? o.split(",") : o;
			for(i=0;i<l.length;i++){
				if(flag){
					money +=",";
				}
				money +=l[i];
				flag =!(i==l.length-1);
			}
			return money;
		}
		function getmv(){
			return mv;
		}
	}
})(jQuery);