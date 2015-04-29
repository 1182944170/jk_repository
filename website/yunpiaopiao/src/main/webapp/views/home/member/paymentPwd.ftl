<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>
<!--content begin-->
<div class="z_pbg">
    <div class="z_pmain">
        <#include "../public/member_left.ftl"/>
 		<#include "../public/member_main.ftl"/>
         <div class="z_jiben">
                	<form>
                    	<ul class="z_ul1">
                                <li style="height:26px;"><span>绑定手机：</span><p>13888888888</p></li>
                                <li class="mf_li"><span>校验码：</span><p><input type="text" class="z_input3" /><input type="button" value="获取验证码" class="mf_input"><em>输入手机号接收的6位纯数字</em></p></li>
                                <li><span>支付密码：</span><p><input type="text" class="z_input4" /><em>登陆云票票的账号</em></p></li>
                                <li><span>确认密码：</span><p><input type="text" class="z_input4" /><em>对其他用户显示的名称，2-12个字符</em></p></li>
                                <li class="z_li4"><span></span><input type="button" value="保存" /></li>
                            </ul>
                    </form>
            </div>
      <#include "../public/member_main_foot.ftl"/>
<!--content end-->
<#include "../public/foot.ftl"/>