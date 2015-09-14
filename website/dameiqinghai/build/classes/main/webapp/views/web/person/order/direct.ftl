<title>全部订单</title>

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
                <div class="w_perdingd">
                    <dl>
                        <dt>
                            <div class="dd_01 fl">订单编号</div>
                            <div class="dd_02 fl">产品信息</div>
                            <div class="dd_03 fl">数量</div>
                            <div class="dd_04 fl">订单金额</div>
                            <div class="dd_05 fl">订单状态</div>
                        </dt>
                        <#list orderPagePager.itemList as u>
                        <dd>
                        	<div class="dd_01 fl">12546854654224</div>
                            <div class="dd_02 fl">
                            	<span><a href="#"><img src="${ctx}/resources/images/w_hpic02.jpg" width="82" height="82" alt="" /></a></span>
                                <span>
                                	<h6><a href="#">诱人蟹子</a></h6>
                                    <p>健康高档，值得你来店品尝，这里环境清幽，让你舒心一刻</p>
                                    <em>2015-06-17&nbsp;&nbsp;截止日期：2015-07-23</em>
                                </span>
                            </div>
                            <div class="dd_03 fl">1</div>
                            <div class="dd_04 fl"><em>998.00</em> / 元</div>
                            <div class="dd_05 fl">
                            	<span>未付款</span>
                                <a href="${ctx}/order/detail-${u.id}${suffix}">订单详情</a>
                            </div>
                        </dd>
                        </#list>
                    </dl>
                    <!--页数-->
                    <div class="w_ys">
                        <a href="#">上一页</a>
                        <a href="#" class="w_yscur">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#">下一页</a>
                    </div>                
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">