<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width = device-width, initial-scale = 1.0, minimum-scale = 1.0, maximum-scale = 1.0, user-scalable = no"/>
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta name="keywords" content="" /> 
<meta name="description" content="" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>首页</title>
<link href="${ctx}/resources/css/reset.css" type="text/css" rel="stylesheet">
<link href="${ctx}/resources/css/style.css" type="text/css" rel="stylesheet">
<script language="javascript">
	window.onload = function () {
		var u = navigator.userAgent;
		if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
			$("#upload").removeAttr("href");
			$("#upload").attr("href","http://zhushou.360.cn/detail/index/soft_id/3068740?recrefer=SE_D_e房宝");
		} else if (u.indexOf('iPhone') > -1) {//苹果手机
			$("#upload").removeAttr("href");
			$("#upload").attr("href","https://itunes.apple.com/cn/app/e-fang-bao/id1011866639?mt=8");
		} else if (u.indexOf('Windows Phone') > -1) {//
			$("#upload").removeAttr("href");
			$("#upload").attr("href","http://zhushou.360.cn/detail/index/soft_id/3068740?recrefer=SE_D_e房宝");
		}
	}
</script>

</head>

<body>
<!--banner-->
<div class="swiper-container">
    <div class="swiper-wrapper">
    	<#list slideList as u>
    		<div class="swiper-slide blue-slide"><a href="#"><img src="${tagUtils.getFileFullPath(u.icon!)}"></a></div>
    	</#list>
    </div>
    <div class="swiper-pagination"></div>
</div>
<!--文字-->
<div class="mm_wenzi">
    <p><@common cmd="help_by_aliasesTitle" aliasesTitle="download">
            	${help.context}
	        </@common></p>
</div>
<!--二维码-->
<div class="mm_er clearfix">
    <dl>
        <dt><img src="${ctx}/resources/images/android.png" /></dt>
        <dd>ios下载</dd>
    </dl>
    <dl>
        <dt><img src="${ctx}/resources/images/ios.png" /></dt>
        <dd>ios下载</dd>
    </dl>
</div>
<div class="mm_an"><a href="##" id="upload">立即安装</a></div>
</body>
</html>
<script src="${ctx}/resources/js/swiper.min.js"></script>
<script type="text/javascript">
    var mySwiper = new Swiper('.swiper-container',{
        pagination : '.swiper-pagination',
    })
</script>
