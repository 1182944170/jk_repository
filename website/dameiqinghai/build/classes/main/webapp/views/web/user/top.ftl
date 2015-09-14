<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title><sitemesh:write property='title'/></title>
<link href="${ctx}/resources/css/select.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${ctx}/resources/js/sdmenu.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/area.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/location.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/select2.js"></script> 
<link type="text/css" href="${ctx}/resources/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
<link rel="icon" href="${ctx}/resources/images/lego.png" type="image/png" /> <#--  加入lego图标  -->

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
</div>