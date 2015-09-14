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
            <h6>找回密码</h6>
            <form action="${ctx}/forget_pwd${suffix}" mothod="POST" onsubmit="return commitSubmit()">
                <ul>
                    <li class="mm_li01"><input type="text" id="telphone" name="telphone" onchange="checktels(this)" class="mm_input4" placeholder="请输入您注册时的手机号"><b id="cue" style="color:red"></li>
                    <li class="mm_li01"><input type="text" id="valCode" name="valCode" class="mm_input5" placeholder="请输入验证码"><input type="button" class="sendsms" id="sendsms" name="sendsms" onclick="checkTel(this)" value="获取验证码" /></li>
                    <li><input type="submit" class="w_xyb" value="下一步" onclick="return check(this.form)"></li>
                    <#if errorMsg??>
		            	<li>
							<h4 class="lighter">
								<font size="3" class="classerror">${errorMsg!}</font>
							</h4>
						</li>
					</#if>
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
	function checktels(obj){
		$("#sendsms").removeAttr("disabled");
		var telphone = $("#telphone").val();
		if(!(/\d{3}-?\d{8}|\d{4}-?\d{7}/.test(telphone))){
	        document.getElementById("cue").innerHTML = "请输入正确的手机号码";
	    } else{
			$("#sendsms").removeAttr("disabled");
			document.getElementById("cue").innerHTML = "";
		}
	}
	function checkTel(obj){
		var telphone = $("#telphone").val();
		if(!(/\d{3}-?\d{8}|\d{4}-?\d{7}/.test(telphone))){ 
	        document.getElementById("cue").innerHTML = "请输入正确的手机号码";
	        return false; 
	    } else {
			document.getElementById("cue").innerHTML = "";
		}
		var url = "${ctx}/api/user/sendsms_for_forget_password/"+telphone;
		var param = {};
		$.getJSON(url, param, function(data){
			if(data.succ == true){
				getCheck(obj);
			}else{
				document.getElementById("cue").innerHTML = "该手机号码已被占用 ";
				obj.setAttribute("disabled", true)
				return false;
			}
		});
	}
	function getCheck(obj){
			var telphone = $("#telphone").val();
			var wait = 0;
			if (wait == 0) {
				$.ajax({
			        async : false,
			        cache : false,
			        type : 'POST',
			        dataType :JSON,
			        url : "${ctx}/api/user/val_telphone/"+telphone,
			        error : function(){
			       		alert("验证码发送成功 ，请注意查收");
			       		time(obj);
		        	}
				})
			}
	}
	var wait=60;
	function time(o) {
			if (wait == 0) {
				o.removeAttribute("disabled");			
				o.value="免费获取验证码";
				wait = 60;
			} else {
				o.setAttribute("disabled", true);
				o.value="重新发送(" + wait + ")";
				wait--;
				setTimeout(function() {
					time(o)
				},
				1000)
			}
	}

	function commitSubmit(){
		var telphone = $("#telphone").val();
		var valCode = $("#valCode").val();
		
		if(!(/\d{3}-?\d{8}|\d{4}-?\d{7}/.test(telphone))){
			document.getElementById("cue").innerHTML = "手机号码不能为空";
			return false;
		}
		if(valCode == ""){
			alert("请输入验证码");
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
