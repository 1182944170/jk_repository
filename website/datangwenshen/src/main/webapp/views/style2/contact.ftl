<title>联系我们</title>
<!--banner-->
<div class="banner"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
<!--main-->
<!--left-->
<div class="main clearfix">
	<div class="left fl">
    	<h2>大唐纹身</h2>
        <#include "common.ftl"/>
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
        	<h6>联系我们</h6>
            <span>
            	<a href="index${suffix}">首页</a>&gt;<a href="#">联系我们</a>
            </span>
        </div>
        <div class="contacta">
        	<div class="news_con">
	        	<@common cmd="help_by_aliasesTitle" aliasesTitle="contacta2">
	            	${help.context}
		        </@common>
                <span><img src="${ctx}/resources/${webSiteStyle}/images/map1.jpg" alt="" /></span>
            </div>
        </div>
    </div>
</div>