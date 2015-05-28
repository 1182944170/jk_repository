<title>人体穿刺</title>
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
        	<h6>人体穿刺</h6>
            <span>
            	<a href="index${suffix}">首页</a>&gt;<a href="#">人体穿刺</a>
            </span>
        </div>
        <div class="piaochun clearfix">
        	<ul>
        		<@datangwenshen cmd="get_pic_list" source=3 type=1 pagerString="1_" pageSize="9">
	        		<#list m_pager.itemList as u>
	                    <li><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" width="100%"/></li>
	        		</#list>
		        </@datangwenshen>
            </ul>
        </div>
    </div>
</div>