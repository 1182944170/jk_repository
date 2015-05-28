<title>收费标准</title>
<!--banner-->
<div class="banner" style="margin-top:-16px"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
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
        	<h6>收费标准</h6>
            <span>
            	<a href="index.html">首页</a>&gt;<a href="#">收费标准</a>
            </span>
        </div>
        <div class="right_con">
        	<@common cmd="help_by_aliasesTitle" aliasesTitle="price">
            	${help.context}
	        </@common>
        </div>
    </div>
</div>