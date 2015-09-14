<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title><sitemesh:write property='title'/></title>
<link type="text/css" href="${ctx}/resources/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/select.css"  rel="stylesheet" />
<link rel="icon" href="${ctx}/resources/images/lego.png" type="image/png" /> <#--  加入lego图标  -->
<script type="text/javascript" src="${ctx}/resources/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/area.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/location.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/select2.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/showpages.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/dmqhPager.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/showdate.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/sdmenu.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/tool.js"></script>
</head>

<body>
<!--顶部-->
<div class="w_top">
	<div class="w_topcon">
    	<h5>欢迎来到大美青海旅游网！</h5>
        <span>
        	<#if sessionUser??>
				<a href="${ctx}/user/setting_data${suffix}"><font color="red">进入个人中心</font></a>
				<em></em>
				<a href="${ctx}/exit${suffix}"><font color="red"> 退出 </font></a>
		    <#else>
	          	<a href="${ctx}/login${suffix}">登陆</a>
	            <em></em>
	            <a href="${ctx}/regist${suffix}">注册</a>
		    </#if>
        </span>
    </div>
</div>
<!--头部-->
<div class="w_head">
	<span><img src="${ctx}/resources/images/w_logo.png" width="347" height="61" alt="" /></span>
    <div class="w_search fr">
    	<form>
        	<input type="text" placeholder="如：甜醅  酿皮" />
            <button><img src="${ctx}/resources/images/w_search.png" width="22" height="22" alt="" /></button>
        </form>
    </div>
</div>
<!--导航-->
<div class="w_nav">
	<div class="w_navcon">
    	<a href="${ctx}/index${suffix}">首页</a>
        <a href="${ctx}/map${suffix}">线路</a>
        <a href="${ctx}/way${suffix}">同游</a>
        <a href="${ctx}/photo${suffix}">画册</a>
        <a href="${ctx}/guide${suffix}">导游</a>
        <a href="${ctx}/art${suffix}">艺人</a>
        <a href="${ctx}/food${suffix}">美食</a>
        <a href="${ctx}/hotel${suffix}">酒店</a>
        <a href="#">富媒体</a>
        <a href="${ctx}/news${suffix}">资讯</a>
    </div>
</div>