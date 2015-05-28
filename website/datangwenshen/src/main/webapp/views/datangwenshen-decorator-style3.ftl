<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title><sitemesh:write property='title'/></title>
		<meta name="keywords" content="<sitemesh:write property='keywords'/>" />
		<meta name="description" content="<sitemesh:write property='description'/>" />
		<link href="${ctx}/resources/favicon.ico" rel="shortcut icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="icon" type="image/x-icon" />
		<link href="${ctx}/resources/favicon.ico" rel="bookmark" type="image/x-icon" />
		
		<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/reset.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery1.42.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery.SuperSlide.2.1.js"></script>
		<sitemesh:write property='head'/>
		<script src="${ctx}/resources/js/showpages.js"></script>
		<script src="${ctx}/resources/style3/js/showpages_ext.js"></script>
	
	</head>

	<body>
	<div class="header">
    <div class="head"><img src="${ctx}/resources/${webSiteStyle}/images/logo.png" width="1030" height="222" alt="" /></div>
</div>
<div class="focusBox">
      <ul class="pic">
              <li><a href="#" target="_blank"><img src="${ctx}/resources/${webSiteStyle}/images/banner02.jpg"/></a></li>
              <li><a href="#" target="_blank"><img src="${ctx}/resources/${webSiteStyle}/images/banner01.jpg"/></a></li>
              <li><a href="#" target="_blank"><img src="${ctx}/resources/${webSiteStyle}/images/banner03.jpg"/></a></li>
      </ul>
      <a class="prev" href="javascript:void(0)"></a>
      <a class="next" href="javascript:void(0)"></a>
      <ul class="hd">
          <li></li>
          <li></li>
          <li></li>
      </ul>
      <script type="text/javascript">
      	ctx = '${ctx}';
		/*鼠标移过，左右按钮显示*/
		jQuery(".focusBox").hover(function(){ jQuery(this).find(".prev,.next").stop(true,true).fadeTo("show",0.2) },function(){ jQuery(this).find(".prev,.next").fadeOut() });
		/*SuperSlide图片切换*/
		jQuery(".focusBox").slide({ mainCell:".pic",effect:"fold", autoPlay:true, delayTime:600, trigger:"click"});
	  </script>
</div>
<!--nav-->
<div class="navall">
    <div class="nav">
         <a href="${ctx}/index.html">网站首页<em>Home</em></a>
         <a href="${ctx}/about.html">关于本店<em>About us</em></a>
         <a href="${ctx}/work.html">大唐作品<em>Works</em></a>
         <a href="${ctx}/material.html">国际大师作品<em>Master works</em></a>
         <a href="${ctx}/wash.html">洗纹身<em>Wash tattoo</em></a>
         <a href="${ctx}/news.html">纹身资讯<em>News</em></a>
         <a href="${ctx}/piercing.html">纹绣穿刺<em>Piercing</em></a>
         <a href="${ctx}/train.html">培训学员<em>Train</em></a>
         <a href="${ctx}/contact.html">联系我们<em>Contact us</em></a>
    </div>
</div>
<sitemesh:write property='body'/>
	<div class="footer">
    <div class="foot">
        <p>联系我们</p>
        <p>邮箱：dtwenshen@qq.com</p>
        <p>微信手机号：13175009566</p>
        <p>版权所有：大唐纹身店，营业时间 ：上午10：30--22：30</p>
        <p>电话：18257178988 13175009566， QQ：1922857868</p>
    </div>
</div>
<!--qq悬浮窗-->
<script src="${ctx}/resources/${webSiteStyle}/js/jqqonline.js"></script>	
	
<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=0&amp;pos=right&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
</script>
<!-- Baidu Button END -->
	</body>
</html>