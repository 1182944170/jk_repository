<title>订单详情</title>

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
                <div class="w_xqdingd">
                	<div class="xq_tit">订单详情</div>
                    <div class="xq_con">
                    	<div class="xq_jd">
                        	<span>当前状态：待付款</span>
                            <span>请及时付款，不然要被抢光了</span>
                        </div>
                        <div class="xq_xinx">
                        	<h5>订单信息</h5>
                            <div class="w_xqddbh">
                                <span>订单编号：12546854654224</span>
                                <span>下单时间：2015-06-30  11:54</span>
                            </div>
                        </div>
                        <div class="xq_xinx">
                        	<h5>商品信息</h5>
                            <div class="w_xqddbh">
                                <div class="xq01 fl">
                                	<h6>商品名称</h6>
                                    <p>诱人蟹子</p>
                                </div>
                                <div class="xq02 fl">
                                	<h6>具体地址</h6>
                                    <p>西宁市城东区昆仑东路196号（近德令哈路口）</p>
                                </div>
                                <div class="xq01 fl">
                                	<h6>单价</h6>
                                    <p>￥88.00</p>
                                </div>
                                <div class="xq01 fl">
                                	<h6>数量</h6>
                                    <p>1</p>
                                </div>
                                <div class="xq01 fl">
                                	<h6>支付金额</h6>
                                    <p>￥88.00</p>
                                </div>
                                <div class="xq01 fl">
                                	<h6>商品评分</h6>
                                    <p>诱人蟹子</p>
                                </div>
                            </div>
                        </div>
                        <div class="xq_xinx">
                        	<h5>商品展示</h5>
                            <div class="w_xqddbh">
                                <div class="xq_tu"><img src="${ctx}/resources/images/w_hpic04.jpg" width="216" height="187" alt="" /><img src="${ctx}/resources/images/w_hpic04.jpg" width="216" height="187" alt="" /><img src="${ctx}/resources/images/w_hpic04.jpg" width="216" height="187" alt="" /><img src="${ctx}/resources/images/w_hpic04.jpg" width="216" height="187" alt="" /></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../../public/foot.ftl">