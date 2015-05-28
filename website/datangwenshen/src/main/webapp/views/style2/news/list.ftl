<title>大唐资讯</title>
<!--banner-->
<div class="banner"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
<!--main-->
<!--left-->
<div class="main clearfix">
	<div class="left fl">
    	<h2>大唐纹身</h2>
        <#include "../common.ftl"/>
        <h2>联系我们</h2>
        <dl>
        	<dt><img src="${ctx}/resources/${webSiteStyle}/images/img01.jpg" width="280" height="98" /></dt>
            <@common cmd="help_by_aliasesTitle" aliasesTitle="contact">
            	${help.context}
	        </@common>
        </dl>
    </div>
    <!--right-->
    <div class="right fr">
    	<div class="fnav">
        	<h6>大唐资讯</h6>
            <span>
            	<a href="index.html">首页</a>&gt;<a href="news.html">纹身资讯</a>
            </span>
        </div>
        <div class="clearfix">
        	<ul class="news">
	        	<#list pager.itemList as u>
	            	<li><a href="${ctx}/news/${u.id}/detail${suffix}"><i></i><b>${u.title}</b></a> <em>${tagUtils.formatDate(u.recoreCreateTime)}</em> 
	        	</#list>
             </ul>
        	<@h.page pager=pager action="${ctx}/news${suffix}" />
        </div>
    </div>
</div>