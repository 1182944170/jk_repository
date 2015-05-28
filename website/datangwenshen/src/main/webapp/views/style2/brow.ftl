<title>纹眉漂唇</title>
<!--banner-->
<div class="banner"><img src="${ctx}/resources/${webSiteStyle}/images/banner.png" width="1030" height="300" /></div>
<!--main-->
<!--left-->
<div class="main clearfix">
	<div class="left fl">
    	<h2>大唐纹身</h2>
        <#include "common.ftl" />
        <h2>联系我们</h2>
        <dl>
        	<dt><img src="${ctx}/resources/${webSiteStyle}/images/img01.jpg" width="280" height="98" /></dt>
            <dd>电话：18257178988&nbsp;&nbsp;&nbsp;13175009566 Q Q：1922857868 <br />地址：杭州市下城区凤起路481号（武林路与凤起路路口）</dd>
        </dl>
    </div>
    <!--right-->
    <div class="right fr">
    	<div class="fnav">
        	<h6>纹眉漂唇</h6>
            <span>
            	<a href="index${suffix}">首页</a>&gt;<a href="#">纹眉漂唇</a>
            </span>
        </div>
        <div class="piaochun clearfix">
        	<br/>
        	<@datangwenshen cmd="get_pic_list" source=3 type=2 pagerString="1_" pageSize="2">
        		<#list m_pager.itemList as u>
        			<dl>
            			<dt><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}"/></dt>
            		</dl>
        		</#list>
	        </@datangwenshen>
	        
        	<@datangwenshen cmd="get_pic_list" source=3 type=3 pagerString="1_" pageSize="2">
        		<#list m_pager.itemList as u>
        			<dl>
            			<dt><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}"/></dt>
            		</dl>
        		</#list>
	        </@datangwenshen>
        </div>
    </div>
</div>
