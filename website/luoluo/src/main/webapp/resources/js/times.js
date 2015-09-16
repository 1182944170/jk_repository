function initTimes(objclass){
	$("."+objclass).each(function(){	
		var oldtimes = $(this).text();//后台传来的时间戳  时间毫秒/1000
		oldtimes =oldtimes*1000;//后台传来的时间戳格式化毫秒
		var dd= new Date();
		dd=dd.getTime();//当前时间
		var times =dd-oldtimes;//时间差毫秒  	
		var days=Math.floor(times/(24*3600*1000))
		//计算出小时数
		var leave1=times%(24*3600*1000)    //计算天数后剩余的毫秒数
		var hours=Math.floor(leave1/(3600*1000))
		//计算相差分钟数
		var leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
		var minutes=Math.floor(leave2/(60*1000))
		//计算相差秒数
		var leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
		var seconds=Math.round(leave3/1000)
		
		var dayval ="天";
		var hourval ="小时" ;
		var minval = "分钟";
		var html ="";
		var flag = false;
		if(days > 0){ html += days +""+dayval
			$(this).html(html+"前");
			flag = true;
		}
		else if(!flag && hours >0){
			html += hours +""+hourval
			$(this).html(html+"前");
			flag = true;
		}
		else if(!flag && minutes >0){
			html += minutes +""+minval
			$(this).html(html+"前");
			flag = true;
		}else if(!flag && seconds>0){
			$(this).html("刚刚");
		}
	});
}