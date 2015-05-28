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
		
		<link type="text/css" href="${ctx}/resources/style1/css/reset.css" rel="stylesheet" />
		<link type="text/css" href="${ctx}/resources/style1/css/style.css" rel="stylesheet" />
		<!--tab-->
		<script type="text/javascript" src="${ctx}/resources/style1/js/jquery1.42.min.js"></script>
		<script type="text/javascript" src="${ctx}/resources/style1/js/jquery.SuperSlide.2.1.js"></script>
		<!--tab-->
		<script src="${ctx}/resources/js/showpages.js"></script>
		<script src="${ctx}/resources/style1/js/showpages_ext.js"></script>
		<script>
		ctx = '${ctx}';
		</script>
	</head>

	<body>
		<!--头部-->
		<div class="top"></div>
		<div class="header clearfix">
		    <div class="logo fl"><img src="${ctx}/resources/style1/images/logo.png" alt="" /></div>
		    <div class="ten fl"><img src="${ctx}/resources/style1/images/ten.jpg" alt="" /></div>
		    <div class="phone fr">
		        <a href="#">加入收藏</a>
		        <span>联系电话：13175009566</span>
		    </div>
		</div>
		<!--banner-->
		<div class="banner">
		    <div class="ban"><img src="${ctx}/resources/style1/images/banner.jpg" width="820" height="368" alt="" /></div>
		</div>
		<!--nav-->
		<div class="nav">
		    <a href="${ctx}/index${suffix}"><em>网站首页</em><strong>Home</strong></a>
		    <a href="${ctx}/about${suffix}"><em>关于本店</em><strong>About us</strong></a>
		    <a href="${ctx}/work${suffix}"><em>大唐作品</em><strong>Works</strong></a>
		    <a href="${ctx}/material${suffix}"><em>国际大师作品</em><strong>Master works</strong></a>
		    <a href="${ctx}/wash${suffix}"><em>洗纹身</em><strong>Wash tattoo</strong></a>
		    <a href="${ctx}/news${suffix}"><em>纹身资讯</em><strong>News</strong></a>
		    <a href="${ctx}/piercing${suffix}"><em>纹绣穿刺</em><strong>Piercing</strong></a>
		    <a href="${ctx}/train${suffix}"><em>培训学员</em><strong>Train</strong></a>
		    <a href="${ctx}/contact${suffix}"><em>联系我们</em><strong>Contact us</strong></a>
		</div>
		
		<sitemesh:write property='body'/>
	
		<!--qq悬浮窗-->
		<script src='${ctx}/resources/style1/js/jqqonline.js'></script>
		
		<!-- Baidu Button BEGIN -->
		<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=0&amp;pos=right&amp;uid=0" ></script>
		<script type="text/javascript" id="bdshell_js"></script>
		<script type="text/javascript">
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
		</script>
		<!-- Baidu Button END -->
				
		<!--底部-->
		<div class="footer">
		<div class="foot">
		    <p>版权所有：大唐纹身店，营业时间 ：上午10：30--22：30 </p>
		    <p>邮箱：dtwenshen@qq.com</p>
		    <p>电话：18257178988/13175009566， QQ：1922857868 </p>
		    <p>地址一：杭州下城区凤起路481号    地址一：杭州市拱墅区香积寺路319号</p>
		</div>
		</div>
		
		<!--qq悬浮窗-->
		<script src='${ctx}/resources/style1/js/jqqonline.js'></script>
		<!-- Baidu Button BEGIN -->
		<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=0&amp;pos=right&amp;uid=0" ></script>
		<script type="text/javascript" id="bdshell_js"></script>
	</body>
</html>