<#include "public/top.ftl"/>

<div class="z_main1">
<div class="z_login">
	<div class="z_logintitle">
        <a href="${ctx}">
        <dl>
            <dt><img src="${ctx}/resources/images/login/login.jpg" width="84" height="84"></dt>
            <dd><span>云票票网 </span><em>ypiaopiao.com</em></dd>
        </dl>
       </a>
        <div class="clearfix"></div>
    </div>
    <div class="z_loginmain" style="height:600px;">
        <form action="${ctx}/doRegister${suffix}" method="POST" id="normalForm" onsubmit="return checkNormalForm()">
            <div class="z_loginmain_left" style="height:551px;position:relative;">
                <h3>注册云票票网</h3>
                <#if errorMsg??>
					<div class="hr hr-dotted"></div>
					<div class="text-danger center" style="position:absolute;left:200px;top:67px; color:#f00; font-size:18px;"><i class="icon-remove bigger-110 red"></i>&nbsp;&nbsp;${errorMsg}</div>
				</#if>
                <div id="tab">
                    <div class="tabList">
                        <ul class="clearfix">
                            <li class="cur"><a href="${ctx}/register${suffix}">邮箱注册</a></li>
                           <li><a href="${ctx}/reghone${suffix}">手机注册</a></li>
                        </ul>
                    </div>
                    <form action="${ctx}/doRegister${suffix}" method="POST" role="form"  name="normalForm" id="normalForm"/>
                    <div class="tabCon">
                        <div class="cur">
                            <ul class="z_ul1">
                                <li><span>登录邮箱：</span>
                                		<p><input name="email" id="email" type="text" class="z_input4"  maxlength="30" onblur="checkEmail()"/>
                                		<em id="emailTip">登陆云票票的账号</em></p>
                                </li>
                                <li><span>昵称：</span>
                                		<p><input name="nickName" id="nickName" type="text" class="z_input4"  maxlength="20" onblur="checkNickName()" />
                                		<em id="nickNameTip">对其他用户显示的名称，2-12个字符</em>
                                		</p>
                                	</li>
                                <li><span>登录密码：</span>
                                		<p><input name="pwd" id="pwd" type="password" class="z_input4"  maxlength="20" onblur="checkPwd()"/>
                                		<em id="pwdTip">登录云票票网的密码，6-16个字符</em></p>
                                </li>
                                <li><span>确认密码：</span>
                                		<p><input name="confPwd" id="confPwd" type="password" class="z_input4" maxlength="20" onblur="checkConfPwd()"/>
                                		<em id="confPwdTip">请确认您的登录密码</em></p>
                                </li>
                                <li><span>验证码：</span>
                                		<p style="width:224px;"><input name="code" id="code" type="text" class="z_input3" maxlength="4" onblur="checkCode()"/>
                                		<img style="cursor:pointer;" alt="点击图片刷新验证码" id="imgCode" src="${ctx}/code${suffix}" width="90" height="29" onclick="changCode()"/>
                                		<em id="codTip"></em></p>
                                	</li>
                                <li class="z_li3"><span></span>
                                		<p><input type="checkbox" checked /><label>我已阅读并接受<a href="#">《使用手册》</a></label></p>
                                </li>
                                <li class="z_li4"><span></span><input type="submit" value="注 册" /></li>
                            </ul>
                        </div>
                    </div>
                   </form>
                </div>
            </div>
            <div class="z_loginmain_right" style="height:551px;">
                <h3>我有云票票网账号</h3>
                <a href="${ctx}/login${suffix}">立即登陆</a>
            </div>
        </form>
        <div class="clearfix"></div>
    </div>
</div>	
<#include "public/login_register_foot.ftl"/>



<script>


function changCode(){
	var imgSrc = $("#imgCode");
	var src = imgSrc.attr("src");
	var url = '${ctx}/code${suffix}?d='+new Date().getMilliseconds();
	imgSrc.attr("src",url);

}


function checkEmail(){

	var _email = $("#email").val();
	if(_email == ""  || _email.length == 0){
		$("#emailTip").html("<font color='red'>邮箱不能为空</font>");
		return false;
	}else{
		 var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
   		 if (!reg.test(_email)) {
   			$("#emailTip").html("<font color='red'>邮箱格式不正确，请重新填写!</font>");
      		return  false;
  		}else{
  			//需做ajax 验证
  			$("#emailTip").html("");
  		}
	}	
	return true;
}



function checkNickName(){

	var _nickName = $("#nickName").val();
	if(_nickName == "" || _nickName.length == 0){
		$("#nickNameTip").html("<font color='red'>昵称不能为空</font>");
		return false;
	}else{
		
		//需做ajax 验证
		$("#nickNameTip").html("");
	}
	return true;

}


function checkPwd(){
	var _pwd = $("#pwd").val();
	if(_pwd == "" || _pwd.length == 0){
		$("#pwdTip").html("<font color='red'>密码不能为空</font>");
		return false;
	}else{
		if(_pwd.length <=5){
			$("#pwdTip").html("<font color='red'>密码长度必须大于6位</font>");
			return false;
		}else{
			$("#pwdTip").html("");
		}
	}
	return true;
}


function checkConfPwd(){
	var _confPwd = $("#confPwd").val();
	if( _confPwd == "" || _confPwd.length == 0){
		$("#confPwdTip").html("<font color='red'>请再次输入密码!</font>");
		
		return false;
	}else{
		if($("#pwd").val()  != $("#confPwd").val()){
			$("#confPwdTip").html("<font color='red'>两次密码输入不一致！</font>");
			
			return false;
		}else{
			$("#confPwdTip").html("");
		}
	}
	return true;
}

function checkCode(){
	var _code = $("#code").val();
	if(_code == "" || _code.length ==0 ){
		$("#codTip").html("<font color='red'>请输入验证码！</font>");
			
		return false;
		
	}else{
		if(_code.length !=4){
			$("#codTip").html("<font color='red'>验证码输入错误！</font>");
			
			return false;
		}else{
			$("#codTip").html("");
		}
	}
	return true;
}



function checkNormalForm(){
	var flag = true;
	<!--邮箱验证-->
	flag = checkEmail();
	
	<!--昵称-->
	flag = checkNickName();
	
	<!--password-->
	flag =  checkPwd();
	
	<!--confpwd-->
	flag = checkConfPwd();
	
	<!--code-->
	flag =  checkCode();
	
	return flag;
}
	

</script>