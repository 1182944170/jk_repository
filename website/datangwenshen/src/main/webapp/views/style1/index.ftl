<title>网站首页</title>
<div class="main">
    <!--公司简介-->
    <div class="concentall">
        <div class="conpany fl">
            <div class="tit">
                <h4>公司简介</h4>
                <a href="about${suffix}">更多</a>
            </div>
            <div class="concent">
                 <@common cmd="help_by_aliasesTitle" aliasesTitle="gongsijianjie">
                   ${tagUtils.cutString(help.context, 500)}
                 </@common>
            </div>
        </div>
        <!--新闻中心-->
        <div class="conpany fr">
            <div class="tit">
                <h4>纹身资讯</h4>
                <a href="news${suffix}">更多</a>
            </div>
            <div class="concent">
                <ul>
                   <@common cmd="notice_list" pagerString="1_" pageSize="9">
                    	<#list m_pager.itemList as u>
		                     <li><a href="${ctx}/news/${u.id}/detail${suffix}">${u.title}</a><span>${tagUtils.formatDate(u.recoreCreateTime)}</span></li> 
                    	</#list>
                    </@common>
                </ul>
            </div>
        </div>
    </div>
    <!--视频和价位表-->
    <div class="price">
        <div class="ship fl">
            <h4>洗纹身视频（大唐纹身店）</h4>
             <embed 
					src="http://player.youku.com/player.php/sid/XNTEyOTM1MDQ4/v.swf" 
					allowFullScreen="true" 
					quality="high" 
					width="435" 
					height="313" 
					align="middle" 
					allowScriptAccess="always" type="application/x-shockwave-flash">
					
			</embed>  
                
        </div>
        <div class="jiege fr">
            <h4>大唐纹身价位表</h4>
            <div class="jiawei">
                <div class="jiacon">
                    <ul>
                        <li>
                            <p>大唐纹绣收费标准：</p>
                            <p>纹眉280起；绣眉580起；</p>
                            <p>仿真雕眉1280起；漂唇880起；眼线680起；</p>
                        </li>
                        <li>
                            <p>大唐穿刺收费标准：</p>
                            <p>脐钉100元/个；鼻钉100元/个；唇钉100元/个；</p>
                            <p>舌钉200元/个；胸环180元/个；眉环100元/个；</p>
                            <p>耳骨100元/个；耳垂100元/个（包括316医用钢饰一件）</p>
                        </li>
                        <li>
                            <p>大唐纹身收费标准：</p>
                            <p>小图按起步价300加每平方厘米30元计算，中图20元每<br />平方厘米；大图（花臂，半甲，满背）按难度和大小来店面议。</p>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!--纹身作品-->
    <div class="slideTxtBox">
			<div class="hd">
			    <ul>
                    <li>纹身作品</li>
                    <li>大众纹身</li>
                    <li>情侣纹身</li>
                </ul>
			</div>
			<div class="bd">
				<ul>
		  			<@datangwenshen cmd="get_pic_list" pageSize=8 pagerString="1_" source=1>
						<#if m_pager.itemList?has_content>
						<#list m_pager.itemList as u>
							<li><a href="${ctx}/work/${u.id}/detail${suffix}"><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" /></a></li>
						</#list>
						<#else>
							暂无图片
						</#if>
					</@datangwenshen>
				</ul>
				<ul>
					<@datangwenshen cmd="get_pic_list" pageSize=8 pagerString="1_" source=1 type=8>
						<#if m_pager.itemList?has_content>
						<#list m_pager.itemList as u>
							<li><a href="${ctx}/work/${u.id}/detail${suffix}"><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" /></a></li>
						</#list>
						<#else>
							暂无图片
						</#if>
					</@datangwenshen>
				</ul>
				<ul>
					<@datangwenshen cmd="get_pic_list" pageSize=8 pagerString="1_" source=1 type=5>
						<#if m_pager.itemList?has_content>
						<#list m_pager.itemList as u>
							<li><a href="${ctx}/work/${u.id}/detail${suffix}"><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}" /></a></li>
						</#list>
						<#else>
							暂无图片
						</#if>
					</@datangwenshen>
           
				</ul>
			</div>
            <script type="text/javascript">jQuery(".slideTxtBox").slide();</script>
    </div>
    <!--纹身欣赏-->
    <div class="xinshang">	
        <h4>国外纹身欣赏</h4>
        <div class="picScroll">	
            <ul>
            		 <@datangwenshen cmd="get_pic_list" pageSize=10 pagerString="1_" source=2 >
						<#if m_pager.itemList?has_content>
						<#list m_pager.itemList as u>
							<li><a target="_blank" href="${ctx}/work/${u.id}/detail${suffix}"><img src="${tagUtils.getFileFullPath(u.address)}"  /></a></li>
						</#list>
						<#else>
							暂无图片
						</#if>
					</@datangwenshen>

            </ul>
    
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>
        </div>
		<script type="text/javascript">
    jQuery(".picScroll").slide({ mainCell:"ul", effect:"leftLoop", vis:5, scroll:2,  autoPage:true, switchLoad:"_src" });
        </script>
    </div>
    <!--联系我们-->
   <#include "contact_us_index.ftl">
</div>
