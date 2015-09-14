<title>联系我们</title>

<#include "../public/top.ftl">

<!--关于大美-->
<div class="mm_about">
    <div class="w_main mm_aboutcon clearfix">
        <div class="mm_mianbaoxie">
            <a href="${ctx}/index${suffix}">首页</a>&gt;
            <a href="${ctx}/contact/about${suffix}">关于大美青海</a>&gt;
            <a>联系我们</>
        </div>
        <#include "../public/contact.ftl">
        <div class="mm_about_right fr">
            <div class="mm_about_right_title"><h5>联系我们</h5></div>
            <div class="mm_about_right_con">
                <div class="mm_api"><img src="${ctx}/resources/images/mm_img02.jpg"></div>
       			 <#include "../public/contact_address.ftl">
            </div>
        </div>
    </div>
</div>

<#include "../public/foot.ftl">