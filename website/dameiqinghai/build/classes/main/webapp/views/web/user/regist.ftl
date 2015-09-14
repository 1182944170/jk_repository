<title>手机注册</title>

<#include "top.ftl">

<!--内容-->
<div class="w_main">
    <!--注册-->
    <div class="mm_zhuce">
        <h3>用户注册</h3>
        <form action="${ctx}/user/val_code${suffix}" method="POST" onsubmit="return commitSubmit()">
            <h5><img src="${ctx}/resources/images/mm_zhuce1.png"></h5>
            <ul>
                <li>
                    <label>手机号码：</label>
                    <input type="text" name="telphone" id="telphone" class="mm_input01" onchange="checktels(this)" /> <b id="cue" style="color:red">  </b>
                </li>
                <li>
                    <label>手机验证：</label>
                    <input type="text" class="mm_input04" id="valCode" name="valCode" />
                    <input type="button" id="sendsms" name="sendsms" value="免费获取验证码" onclick="checkTel(this)" />
                </li>
            	<#if infoMsg??>
	            	<li>
						<label>提示：</label>
						<h4 class="lighter">
							<font size="3" class="classerror">${infoMsg}</font>
						</h4>
					</li>
				</#if>
                <li>
                    <label>&nbsp;</label>
                    <input type="checkbox" class="mm_input02" checked>
                    <span>我已阅读并同意遵守《大美青海用户服务协议》</span>
                </li>
                <li>
                    <label>&nbsp;</label>
                    <input type="submit" class="mm_input03" value="下一步" onclick="return check(this.form)">
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
		var url = "${ctx}/api/user/val_checkphone/"+telphone;
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

<#include "../public/foot.ftl">