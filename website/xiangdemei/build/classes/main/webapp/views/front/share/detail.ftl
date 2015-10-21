<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no" />
<title>首页</title>
<link href="${ctx}/resources/css/share_reset.css" rel="stylesheet" type="text/css">
<script src="${ctx}/resources/cssframework/ace/js/jquery-2.0.3.min.js"></script>
<script>
  var countdown=6; 
  var times;

  function Settime() { 
  		var valBtn=$("#sendBtn");
  		var contact = $("#contact").val();
  		if(contact.length != 11){
  			$("#tips").html("手机号码格式错误！");
			return false;
  		}
  		$("#tips").html("");
  		if(countdown == 6){
  			$.ajax({
            	type:"POST",
            	url:"${ctx}/api/sendsms_for_regist?t="+new Date(),
            	data:{contact:$("#contact").val()},
            	datatype: "html",
           		success:function(data){
	    	      
    	        } ,
	         });
  		}
		if (countdown <= 0 ) { 
			valBtn[0].removeAttribute("disabled");    
			valBtn[0].value="获取验证码";
			
			clearTimeout(times);
			countdown = 6;
		} else { 
			valBtn[0].setAttribute("disabled", true); 
			valBtn[0].value="(" + countdown + "s"+")重新发送"; 
			countdown--;
			
			times=setTimeout(Settime,1000)   
		}
		 
		
	} 
	
	
	$(document).ready(function(){
	
		$("#register").click(function(){
		
			var contact = $("#contact").val();
			var invitationUserId = $("#parentId").val();
			var verifyCode = $("#verifyCode").val();
			var password = $("#password").val();
			
			if(contact.length != 11){
				$("#tips").html("手机号码格式错误！");
				return false;
			}
			
			if(verifyCode.length != 6){
				$("#tips").html("验证码错误！");
				return false;
			}
			
						
			if(password.length < 6){
				$("#tips").html("密码太短了！");
				return false;
			}
			
			if(invitationUserId.lenth != 6){
				$("#tips").html("推荐码格式错误！");
				return false;
			}
						
			$("#tips").html("");			
			$.ajax({
            	type:"POST",
            	url:"${ctx}/api/regist?t="+new Date(),
            	data:{contact:contact,invitationUserId:invitationUserId,verifyCode:verifyCode,password:password},
            	datatype: "html",
           		success:function(data){
	    	      if(data.succ){
	    	      	$("#tips").html(" 注册成功！");
	    	      }
    	        } ,
	         });
		})	
	})
</script>
</head>
<body>

	<form>
        <div class="m_top">
            <div class="m_top_id">我的推荐ID：<span>${parentId!}</span></div>
            <a>点击复制ID</a>
            <p>还没下载想De美APP？</p> 
			<p>可以先注册一个账号，下载后即可直接登录。已经注册的用户请飘过~</p>
			<p style="color:#f00" id="tips"></p>
        </div>
        <div class="m_form">
        	<ul>
            	<li><label>手机号</label><div><input type="text"  name="contact" id="contact" maxlength="15" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"></div></li>
                <li><label>验证码</label><div style=" position: relative;"">
                	<input type="text"  id="verifyCode" maxlength="8" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                	<input class="m_input01" type="button" id="sendBtn" onclick="Settime()" value="获取验证码"/></div></li>
                <li><label>密&nbsp;&nbsp;&nbsp;码</label><div><input type="password" id="password"></div></li>
                <li><label>推荐码</label><div><input type="text" id="parentId" value="${parentId!}"></div></li>
                <li><button id="register">完成注册</button></li>
            </ul>
        </div>
        <div class="m_ewm">
        	<h5>扫描二维码或点击下方按钮即可下载想De美APP：</h5>
            <div class="m_erweima clearfix">
            	<div class="m_ios fl">
                	<h6>ios安装</h6>
                    <img src="${iosImg!}" >
                </div>
                <div class="m_and fr">
                	<h6>安卓安装</h6>
                    <img src="${androidImg!}" >
                </div>
                <a href="${appdownurl!}">一键安装</a>
            </div>
        </div>
    </form>
    <div class="m_img">
    <#assign usercfgMap=shareMap['usercfg'][cfgId] />
	<#assign usercfgImgMap=usercfgMap['img'] />
	<#list usercfgImgMap?keys as uKey>
			<img src="${usercfgImgMap[uKey]?html}">
	</#list>
    
    </div>
</body>
</html>