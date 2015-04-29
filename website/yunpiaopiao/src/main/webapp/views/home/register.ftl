<#include "public/top.ftl"/>

<div class="z_main1">
<div class="z_login">
	<div class="z_logintitle">
        <dl>
            <dt><img src="${ctx}/resources/images/login/logo.png" width="84" height="84"></dt>
            <dd><span>云票票网</span><em>ypiaopiao.com</em></dd>
        </dl>
        <div class="clearfix"></div>
    </div>
    <div class="z_loginmain" style="height:600px;">
        <form>
            <div class="z_loginmain_left" style="height:551px;">
                <h3>注册云票票网</h3>
                <div id="tab">
                    <div class="tabList">
                        <ul class="clearfix">
                            <li class="cur"><a href="${ctx}/register${suffix}">邮箱注册</a></li>
                            <li><a href="${ctx}/reghone${suffix}">手机注册</a></li>
                        </ul>
                    </div>
                    <div class="tabCon">
                        <div class="cur">
                            <ul class="z_ul1">
                                <li><span>登录邮箱：</span><p><input type="text" class="z_input4" /><em>登陆云票票的账号</em></p></li>
                                <li><span>昵称：</span><p><input type="text" class="z_input4" /><em>对其他用户显示的名称，2-12个字符</em></p></li>
                                <li><span>登录密码：</span><p><input type="text" class="z_input4" /><em>登录云票票网的密码，6-16个字符</em></p></li>
                                <li><span>确认密码：</span><p><input type="text" class="z_input4" /><em>请确认您的登录密码</em></p></li>
                                <li><span>验证码：</span><p><input type="text" class="z_input3" /><a href="#"><img src="${ctx}/resources/images/login/yan.jpg" width="80" height="29" /></a><em>为了您的账户安全输入验证码，请看不清点击图片刷新</em></li>
                                <li class="z_li3"><span></span><input type="checkbox" checked /><label>我已阅读并接受<a href="#">《使用手册》</a></label></li>
                                <li class="z_li4"><span></span><input type="button" value="注册" /></li>
                            </ul>
                        </div>
                       
                    </div>
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