<title>关于本店</title>
<!--banner-->
<div class="banner" style="margin-top:-16px"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
<!--main-->
<!--left--><div class="main clearfix">
	<div class="left fl">
    	<h2>大唐纹身</h2>
        <#include "../common.ftl">
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
        	<h6>关于本店</h6>
            <span>
            	<a href="index${suffix}">首页</a>&gt;<a href="about${suffix}">关于本站</a>
            </span>
        </div>
        <div class="about clearfix">
            <h3>大唐风采</h3>
            <ul>
            	<li><img src="${ctx}/resources/${webSiteStyle}/images/pic01.jpg" width="223" height="270" /></li>
                <li><img src="${ctx}/resources/${webSiteStyle}/images/pic02.jpg" width="223" height="270" /></li>
                <li><img src="${ctx}/resources/${webSiteStyle}/images/pic03.jpg" width="223" height="270" /></li>
            </ul>
            <h3>大唐团队</h3>
            <ul>
            	<li><img src="${ctx}/resources/${webSiteStyle}/images/peo01.png" width="223" height="270" /></li>
                <li><img src="${ctx}/resources/${webSiteStyle}/images/peo02.png" width="223" height="270" /></li>
                <li><img src="${ctx}/resources/${webSiteStyle}/images/peo03.png" width="223" height="270" /></li>
            </ul>
        </div>
    </div>
</div>
