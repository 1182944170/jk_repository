<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>
<!--content begin-->
<div class="z_pbg">
    <div class="z_pmain">
        <#include "../public/member_left.ftl"/>
 		<#include "../public/member_main.ftl"/>
        <div class="z_jiben">
        	<form>
            	<ul class="z_jiben1">
                	<li><span>昵称：</span><input type="text" /></li>
                    <li><span>生日：</span><input type="text" /></li>
                    <li><span>姓名：</span><input type="text" /></li>
                    <li><span>身份证号：</span><input type="text" /></li>
                    <li><span class="float-l">性别：</span><input type="radio" checked name="1" class="z_input05" /><label>保密</label><input type="radio" name="1" class="z_input05" /><label>男</label><input type="radio" name="1" class="z_input05" /><label>女</label></li>
                    <li><span>手机号：</span><input type="text" /></li>
                </ul>
                <input type="button" value="保存" class="z_input06" />
            </form>
        </div>
        
      <#include "../public/member_main_foot.ftl"/>
<!--content end-->
<#include "../public/foot.ftl"/>