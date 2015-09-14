<title>资讯详情</title>

<#include "../public/top.ftl">


<div class="w_main">
    <div class="mm_zixun_detail">
        <div class="mm_mianbaoxie1">
            <a href="${ctx}/index${suffix}">首页</a>&gt;
            <a href="${ctx}/news${suffix}">资讯</a>&gt;
            <a>资讯详情</a>
        </div>
        <div class="mm_zixun_detail_con">
            <div class="mm_zixun_detail_title">
                <h2>${newsInfo.caption!} </h2>
                <span>时间：${tagUtils.formatDate(newsInfo.createTime)!}    作者：${newsInfo.authors!}    来源：青海省旅游局</span>
            </div>
            <div>
                <p>
                	${newsInfo.content!}
                </p>
                <#if "${newsInfo.photos}"?length gt 1>
               		<img src="${tagUtils.getFileFullPath(newsInfo.photos)!}">
               	</#if>
            </div>
            <div class="mm_fanye">
                <ol>
                    <li>
                        <span>上一篇：</span>
                        <a href="#">总有一个理由，让你动身来青海！</a>
                    </li>
                    <li>
                        <span>下一篇：</span>
                        <a href="#">青海省旅游局重点推荐精品旅游产品线路获奖结果出炉啦！</a>
                    </li>
                </ol>
            </div>
        </div>
    </div>
</div>


<#include "../public/foot.ftl">