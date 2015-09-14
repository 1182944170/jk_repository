<title> 设置密码 </title>

<#include "top.ftl">

<!--内容-->
<div class="w_main">
    <!--注册-->
    <div class="mm_zhuce">
        <h3>用户注册</h3>
        <form action="${ctx}/user/regist_val_pwd${suffix}" moehod="POST" onsubmit="return commitSubmit()">
        	<input type="hidden" name="telphone" id="telphone" value="${telphone!}">
            <h5><img src="${ctx}/resources/images/mm_zhuce2.png"></h5>
            <ul>
                <li>
                    <label>设置密码：</label>
                    <input type="password" class="mm_input01" id="password" name="password">
                </li>
                <li>
                    <label>再次确认：</label>
                    <input type="password" class="mm_input01" id="qpassword" name="qpassword">
                    <i></i>
                </li>
                <li>
                    <label>&nbsp;</label>
                    <input type="submit" class="mm_input03" value="确认注册" onclick="return check(this.form)">
                </li>
            </ul>
            <#if errorMsg??>
				<div class="hr hr-18 hr-double dotted"></div>
				<h4 class="lighter">
					<i class="icon-hand-right icon-animated-hand-pointer red"></i>
					<span class="pink">${errorMsg!}</span>
				</h4>
			</#if>
        </form>
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

<#include "../public/foot.ftl">