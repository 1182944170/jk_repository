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
            	<a href="#">首页</a>&gt;<a href="#">大唐资讯</a>
            </span>
        </div>
        <div class="news_detail">
        	<div class="news_title">
            	<h4>${notice.title}</h4>
                <h5><span>来源：大唐纹身        &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;时间：${tagUtils.formatDate(notice.recoreCreateTime)}</span></h5>
            </div>
            <p>
	        	<@common cmd="notice_by_id" aliasesTitle="id">
	            	<font style="padding: 0px; margin: 26px 0px; font-size: 16px; text-indent: 2em; color: rgb(37, 37, 37); font-family: 宋体, sans-serif; line-height: 28px; text-align: justify;">${notice.content}</font>
		        </@common>
		    </p>
        </div>
    </div>
</div>