<title>管理游记</title>

<#include "../../public/top.ftl">

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
                <#include "../public.ftl">
            </div>
            <div class="w_personr fr">
            	<div class="perconxq">
                	<h5><em>管理游记</em></h5>
                    <div class="gl_yjgl">
                        <dl>
                            <dt>
                                <div class="gl_01 fl">发表日期</div>
                                <div class="gl_02 fl">标题</div>
                                <div class="gl_03 fl">景区简介</div>
                                <div class="gl_04 fl">操作</div>
                            </dt>
                            <#list travelPager.itemList as u>
	                            <dd>
	                                <div class="gl_01 fl">${tagUtils.formatDate(u.createTime)!}</div>
	                                <div class="gl_02 fl"><a href="${ctx}/travel/detail-${u.id}${suffix}">${u.title!}</a></div>
	                                <div class="gl_03 fl">${u.introduction!}</div>
	                                <div class="gl_04 fl"><span><a href="${ctx}/travel/edit-${u.id}${suffix}">编辑</a></span><em><a href="${ctx}/travel/delete-${u.id}${suffix}"></a></em></div>
	                            </dd>
                            </#list>
                        </dl>
                        <!--页数-->
                        <div class="w_ys">
                            <script>
	                    	new showPages('${ctx}/travel/direct${suffix}','${travelPager.totalPages?c}','${travelPager.totalCount?c}','${travelPager.pagerWebString}').printBaseHtml();
						</script>
                        </div>                
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">