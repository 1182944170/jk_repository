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
<link href="${ctx}/resources/css/reset1.css" type="text/css" rel="stylesheet">
<link href="${ctx}/resources/css/style1.css" type="text/css" rel="stylesheet">
<script src="${ctx}/resources/js/jquery-1.9.1.min.js"></script>
<script language="javascript">
	window.onload = function () {
		var u = navigator.userAgent;
		if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
			$("#upload").removeAttr("href");
			$("#upload").attr("href","${ctx}/resources/apk/luoluo.apk");
		} else if (u.indexOf('iPhone') > -1) {//苹果手机
			
			$("#upload").removeAttr("href");
			$("#upload").attr("href","https://itunes.apple.com/us/app/luo-luo/id1085453339?l=zh&ls=1&mt=8");
		} else if (u.indexOf('Windows Phone') > -1) {//
			$("#upload").removeAttr("href");
			$("#upload").attr("href","${ctx}/resources/apk/luoluo.apk");
		}
	}
</script>

</head>
<body>
<div style="text-align:center">
  <div>
    <em><img src="${ctx}/resources/css/images/erweima.jpg" width="190" height="190" /></em>
  </div>
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
