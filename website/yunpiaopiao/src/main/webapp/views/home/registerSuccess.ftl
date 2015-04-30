<#include "public/top.ftl"/>
<body>
<div class="z_main">
<div class="z_login">
	<div class="z_logintitle">
        <dl>
            <dt><img src="${ctx}/resources/images/login/login.jpg" width="84" height="84"></dt>
            <dd><span>云票票网</span><em>ypiaopiao.com</em></dd>
        </dl>
        <div class="clearfix"></div>
    </div>
    <div class="z_loginmain" style="height:499px;">
        <form>
            <div class="z_loginmain_left" style="height:450px;">
                <div class="z_img01"><img src="${ctx}/resources/images//login/img01.png" width="228" height="388" /></div>
            </div>
            <div class="z_loginmain_right" style="height:450px;">
                <div class="z_bang">
                	<h6>您的账号还未绑定手机</h6>
                    <p>绑定手机后您可以找回邮箱及密码，提高账号安全<br>设置支付密码，保护您的资金方便您的购票</p>
                    <ul>
                    	<li><span>绑定手机：</span><div><input type="text" class="z_input4" /><em>登陆云票票的账号</em></div></li>
                        <li><span>校验码：</span><div><input type="text" class="z_input3" /><a href="#"><img src="${ctx}/resources/images//login/huo.jpg" width="61" height="30" /></a><em>输入手机手机接收的6位纯数字</em></div></li>
                        <li class="z_li2"><span><a href="#">完成</a></span><a href="#">跳过</a></li>
                    </ul>
                </div>
            </div>
        </form>
        <div class="clearfix"></div>
    </div>
</div>	
<#include "public/login_register_foot.ftl"/>