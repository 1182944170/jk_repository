<title> 完成注册 </title>

<#include "top.ftl">

<!--内容-->
<div class="w_main">
    <!--注册-->
    <div class="mm_zhuce">
        <h3>用户注册</h3>
        <form>
            <h5><img src="${ctx}/resources/images/mm_zhuce3.png"></h5>
            <div class="mm_zhuce1">
                <p>恭喜您注册成功</p>
                <span>
                    <a href="${ctx}/user/setting_data${suffix}">填写资料</a>
                    /
                    <a href="${ctx}/index${suffix}">返回首页</a>
                </span>
            </div>
        </form>
    </div>
</div>

<#include "../public/foot.ftl">