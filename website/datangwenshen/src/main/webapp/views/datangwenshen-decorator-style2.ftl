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
		<sitemesh:write property='head'/>
		<link rel="stylesheet" href="${ctx}/resources/${webSiteStyle}/css/reset.css" type="text/css" />
		<link rel="stylesheet" href="${ctx}/resources/${webSiteStyle}/css/main.css" type="text/css" />
		
		<script src="${ctx}/resources/js/showpages.js"></script>
		<script src="${ctx}/resources/style2/js/showNews.js"></script>
	</head>

	<body>
		<!--header-->
		<div class="header">
			<div class="logo fl"><img src="${ctx}/resources/${webSiteStyle}/images/logo.png" width="251" height="69" /></div>
		    <div class="header_right fr">
		    	<div class="shoucang">
		        	<a href="#" class="index">设为首页</a>
		            <a href="#" class="shou">加为收藏</a>
		        </div>
		        <div class="tel">联系电话：13175009566</div>
		    </div>
		</div>
		<!--nav-->
		<div class="nav">
			<ul>
		    	<li><a href="${ctx}/index${suffix}"><span>网站首页</span><i>Home</i></a></li>
		        <li><a href="${ctx}/about${suffix}"><span>关于本店</span><i>About us</i></a></li>
		        <li><a href="${ctx}/work${suffix}"><span>大唐作品</span><i>Works</i></a></li>
		        <li><a href="${ctx}/material${suffix}"><span>国际大师作品</span><i>Master works</i></a></li>
		        <li><a href="${ctx}/wash${suffix}"><span>洗纹身</span><i>wash tattoo</i></a></li>
		        <li><a href="${ctx}/news${suffix}"><span>纹身资讯</span><i>News</i></a></li>
		        <li><a href="${ctx}/piercing${suffix}"><span>纹绣穿刺</span><i>Piercing</i></a></li>
		        <li><a href="${ctx}/train${suffix}"><span>培训学员</span><i>Wash tattoo</i></a></li>
		        <li><a href="${ctx}/contact${suffix}"><span>联系我们</span><i>Contact us</i></a></li>
		    </ul>
		</div>
		
		<sitemesh:write property='body'/>
		
		<!--footer-->
		<div class="footer">
			<div class="footer_mid">
		    	<div class="footer_left fl">
		        	<p>版权所有：大唐纹身店，营业时间 ：上午10：30--22：30     邮箱：dtwenshen@qq.com</p> 
					<p>地址：杭州市香积寺路319号（大关钱江市场南门对面公交站台）     电话：18257178988 13175009566， QQ：1922857868</p>
					<p>浙ICP备11054481号     网站技术支持：杭州米凡广告</p>
		        </div>
		        <div class="footer_right fr"><img src="${ctx}/resources/${webSiteStyle}/images/weixin.png" width="68" height="68" /></div>
		    </div>
		</div>
	</body>
</html>