<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>登录</title>
<link type="text/css" href="${ctx}/resources/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />
<link rel="icon" href="${ctx}/resources/images/lego.png" type="image/png" /> <#--  加入lego图标  -->
<script type="text/javascript" src="${ctx}/resources/js/jquery-2.0.3.min.js"></script>
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
<!--登录-->
<div class="mm_denglu">
    <div class="w_main">
        <div class="mm_dengli_left fl">
            <img src="${ctx}/resources/images/mm_denglu_img.png">
        </div>
        <div class="mm_dengli_right fr">
            <h6>账号登录</h6>
            <form action="${ctx}/user/user_login${suffix}" method="POST">
                <ul>
                    <li class="mm_li01"><input type="text" class="mm_input04" id="telphone" name="telphone"></li>
                    <li class="mm_li01"><input type="password" class="mm_input05" id="password" name="password"></li>
                    <li class="mm_wangji clearfix">
                        <span>会员账号${infoMsg!}<a href="${ctx}/regist${suffix}">免费注册${errorMsg!}</a></span>
                        <a href="${ctx}/forget${suffix}">忘记密码？</a>
                    </li>
                    <li><input type="submit" class="mm_input06" value="确认登录"></li>
                    <li class="mm_kuaijie">
                        <span>使用其他账号登录：</span>
                        <a href="#"></a>
                        <a href="#"></a>
                        <a href="#"></a>
                    </li>
                </ul>
                <#if errorMsg??>
					<div class="hr hr-18 hr-double dotted"></div>
					<h4 class="lighter">
						<i class="icon-hand-right icon-animated-hand-pointer red"></i>
						<span class="pink">${errorMsg!}</span>
					</h4>
				</#if>
				<#if infoMsg??>
					<div class="hr hr-18 hr-double dotted"></div>
				
					<h4 class="lighter">
						<i class="icon-hand-right icon-animated-hand-pointer green"></i>
						<span class="pink">${infoMsg}</span>
					</h4>
				</#if>
            </form>
        </div>
    </div>
</div>
<!-- 底部 -->
<div class="mm_footer1">
    <p>旅行社业务经营许可证编号：L-BJ-CJ00144  旅行社业务经营许可证编号：L-SH-00321  公司地址：杭州市拱墅区祥茂路2号1号楼B座2楼</p>
</div>
</body>
</html>
