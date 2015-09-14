<title>修改密码</title>

<#include "../public/top.ftl">

<!--内容-->
<script type="text/javascript" src="${ctx}/resources/js/sdmenu.js"></script>
<script type="text/javascript">
var myMenu;
window.onload = function() {
	myMenu = new SDMenu("my_menu");
	myMenu.init();
};
</script>
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "public.ftl">               
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>修改密码</em></h5>
                    <form action="${ctx}/user/update_pwd${suffix}" method="POST" onsubmit="return commitSubmit()">
                    	<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}">
                        <div class="fabu_youji">                            
                            <ul>
                                <li><label>原密码：</label><input type="text" id="oldPwd" name="oldPwd" class="c_btn"/><em>请输入当前您使用的密码</em></li>
                                <li><label>新密码：</label><input type="text" id="nPwd" name="nPwd" value="" class="c_btn" /><em>6-18位，区分大小写，请使用字母、数字，不含特殊符号</em></li>
                                <li><label>确认密码：</label><input type="text" id="qPwd" name="qPwd" class="c_btn" /><em>再次确认您的新密码</em></li>
                            </ul>   
                            <div class="xiug_btn">
                                <input type="submit" value="确定修改" onclick="return check(this.form)"/>
                            </div>     
                        </div>                    
                    </form>
                </div> 
            </div>
        </div>
    </div>
</div>

<#include "../public/foot.ftl">

<script>
	
	function commitSubmit(){
		var oldPwd = $("#oldPwd").val();
		var nPwd = $("#nPwd").val();
		var qPwd = $("#qPwd").val();
		
		if(oldPwd==""){
			alert("原密码不能为空");
			return false;
		}
		
		if(nPwd==""){
			alert("请输新密码");
			return false;
		}
		
		if(qPwd==""){
			alert("请输入确认密码");
			return false;
		}
		
		if(nPwd != qPwd){
			alert("两次密码不一致");
			return false;
		}
		return true;
	}

</script>

<script src="${ctx}/resources/js/touxiang/cropper.js"></script>
<script src="${ctx}/resources/js/touxiang/main.js"></script>