<title>资讯</title>

<#include "public/top.ftl">

<div class="w_main">
    <!-- ad -->
    <div class="mm_zixun_ad">
        <a href=""><img src="${ctx}/resources/images/mm_ad02.jpg"></a>
    </div>
    <div class="clearfix">
        <div class="mm_zixun_left fl">
            <div class="mm_gonglue">
                <div class="mm_zixun_left_title">
                    <a href="##">景点攻略</a>
                </div>
                <div class="mm_gonglue_con">
                    <div class="mm_gonglue_con_left fl">
                        <a href="${ctx}/spots/detail${suffix}">
                            <img src="${ctx}/resources/images/mm_gonglue_img01.jpg">
                            <p>西宁电视塔</p>
                        </a>
                    </div>
                    <div class="mm_gonglue_con_right fl">
                        <div class="mm_gonglue_con_right_top">
                            <a href="${ctx}/spots/detail${suffix}">
                                <img src="${ctx}/resources/images/mm_gonglue_img02.jpg" width="324" height="144">
                                <p>西宁电视塔</p>
                            </a>
                            <a href="${ctx}/spots/detail${suffix}">
                                <img src="${ctx}/resources/images/mm_gonglue_img01.jpg" width="214" height="144">
                                <p>西宁电视塔</p>
                            </a>
                        </div>
                        <div class="mm_gonglue_con_right_bottom">
                            <a href="${ctx}/spots/detail${suffix}">
                                <img src="${ctx}/resources/images/mm_gonglue_img02.jpg" width="174" height="144">
                                <p>西宁电视塔</p>
                            </a>
                            <a href="${ctx}/spots/detail${suffix}">
                                <img src="${ctx}/resources/images/mm_gonglue_img01.jpg" width="174" height="144">
                                <p>西宁电视塔</p>
                            </a>
                            <a href="${ctx}/spots/detail${suffix}">
                                <img src="${ctx}/resources/images/mm_gonglue_img01.jpg" width="174" height="144">
                                <p>西宁电视塔</p>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix">
                <div class="mm_zuixin fl">
                    <div class="mm_zixun_left_title">
                        <a href="##">最新动态</a>
                    </div>
                    <dl>
                        <a href="${ctx}/news/detail${suffix}">
                            <dt><img src="${ctx}/resources/images/mm_zixun_img01.jpg"></dt>
                            <dd>
                                <h6>青海旅游局发布了最新的门票价位</h6>
                                <p>青海生态保护越重要，旅游发展的潜力就越大。游业一定能成为青海经济发展的支柱产业。旅游要谋划好旅游产业发展，掌握全省各地旅游..
                                </p>
                            </dd>
                        </a>
                    </dl>
                    <ul>
                    	<#list newsList1 as u>
	                        <li>
	                            <a href="${ctx}/news/detail-${u.id}${suffix}">${u.caption!}</a>
	                            <span>${tagUtils.formatDate(u.createTime)!}</span>
	                        </li>
                    	</#list>
                    </ul>
                </div>
                <div class="mm_zuixin fr">
                    <div class="mm_zixun_left_title">
                        <a href="${ctx}/news/detail${suffix}">注意事项</a>
                    </div>
                    <dl>
                        <a href="${ctx}/news/detail${suffix}">
                            <dt><img src="${ctx}/resources/images/mm_zixun_img01.jpg" width="100" height="76"></dt>
                            <dd>
                                <h6>青海旅游局发布了最新的门票价位</h6>
                                <p>青海生态保护越重要，旅游发展的潜力就越大。游业一定能成为青海经济发展的支柱产业。旅游要谋划好旅游产业发展，掌握全省各地旅游..
                                </p>
                            </dd>
                        </a>
                    </dl>
                    <ul>
                        <#list newsList2 as u>
	                        <li>
	                            <a href="${ctx}/news/detail-${u.id}${suffix}">${u.caption!}</a>
	                            <span>${tagUtils.formatDate(u.createTime)!}</span>
	                        </li>
                    	</#list>
                    </ul>
                </div>
            </div>
        </div>
        <div class="mm_zixun_right fr">
            <div class="mm_zixun_right_title">
                <a href="##">热门公告</a#>
            </div>
            <div class="mm_zixun_right_con">
                <ul>
                    <#list noticeList as u>
                        <li>
                            <a href="${ctx}/news/detail-${u.id}${suffix}">${u.caption!}</a>
                            <span>${tagUtils.formatDate(u.createTime)!}</span>
                        </li>
                	</#list>
                </ul>
            </div>
            <div class="mm_zixun_right_ad">
                <a href="#">
                    <img src="${ctx}/resources/images/mm_ad03.jpg">
                </a>
            </div>
        </div>
    </div>
    <div class="w_dzlx">
        <a href="#"><img src="${ctx}/resources/images/mm_ad01.jpg"></a>
    </div>
</div>


<#include "public/foot.ftl">