<title>申请成功</title>

<#include "../../public/top.ftl">
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "../public.ftl">             
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>申请美食</em></h5>
                    <div class="sqcg">
                    	<span><img src="${ctx}/resources/images/shenqok.png" /><em>您申请的<b>${dicSetting.getParameterValue("user.type."+supplier.type)!}商户</b>资料已提交，请耐心等待结果</em></span>
                        <span>我们将在2015-07-10  24:00之前审核完毕，审核完毕后，我们会通过短信方式通知您。</span>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">