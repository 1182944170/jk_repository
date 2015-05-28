$(function(){

	$li1 = $(".apply_nav .apply_array");
	$window1 = $(".apply .apply_w");
	$left1 = $(".apply .img_l");
	$right1 = $(".apply .img_r");
	
	$window1.css("width", $li1.length*186);

	var lc1 = 0;
	var rc1 = $li1.length-5;
	
	$left1.click(function(){
		if (lc1 < 1) {
			alert("已经是最后一张了");
			return;
		}
		lc1--;
		rc1++;
		$window1.animate({left:'+=186px'}, 1000);
	});

	$right1.click(function(){
		if (rc1 < 1){
			alert("已经是最后一张了");
			return;
		}
		lc1++;
		rc1--;
		$window1.animate({left:'-=186px'}, 1000);
	});

})
