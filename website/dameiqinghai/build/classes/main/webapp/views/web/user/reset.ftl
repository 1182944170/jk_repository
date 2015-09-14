<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>忘记密码</title>
<link type="text/css" href="${ctx}/resources/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/style.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/bootstrap.min.css" rel="stylesheet" />
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
    <!-- <div class="w_search fr">
    	<form>
        	<input type="text" placeholder="如：甜醅  酿皮" />
            <button><img src="${ctx}/resources/images/w_search.png" width="22" height="22" alt="" /></button>
        </form>
    </div> -->
</div>
<!--登录-->
<div class="mm_denglu">
    <div class="w_main">
        <div class="mm_dengli_left fl">
            <img src="${ctx}/resources/images/mm_denglu_img.png">
        </div>
        <div class="mm_dengli_right fr">
            <h6>重置密码</h6>
            
            <form action="${ctx}/reset_pwd${suffix}" meyhod="POST" onsubmit="return commitSubmit()">
            	<input type="hidden" name="telphone" id="telphone" value="${telphone!}">
                <ul>
                    <li class="mm_li01"><input type="password" id="password" name="password" class="mm_input4" placeholder="请输入您的新密码"></li>
                    <li class="mm_li01"><input type="password" id="qpassword" name="qpassword" class="mm_input4" placeholder="请核对您的新密码"></li>
                    <li><input type="submit" class="w_xyb" value="完成" onclick="return check(this.form)"></li>
                    <li class="mm_kuaijie">
                        <span>使用其他账号登录：</span>
                        <a href="#"></a>
                        <a href="#"></a>
                        <a href="#"></a>
                    </li>
                </ul>
            </form>
            
        </div>
    </div>
</div>

<script>

	function commitSubmit(){
		var password = $("#password").val();
		var qpassword = $("#qpassword").val();
		
		if(password==""){
			alert("请输入密码!");
			return false;
		}
		
		if(password==""){
			alert("请输入确认密码!");
			return false;
		}
		
		if(password != qpassword){
			alert("两次密码不一致!");
			return false;
		}
		
		return true;
	}

</script>

<!-- 底部 -->
<div class="mm_footer1">
    <p>旅行社业务经营许可证编号：L-BJ-CJ00144  旅行社业务经营许可证编号：L-SH-00321  公司地址：杭州市拱墅区祥茂路2号1号楼B座2楼</p>
</div>
</body>
</html>
