<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>

<!--content begin-->
<div class="content">
	<div class="center clearfix">
		<div class="con-l">
			<div class="dy_t">
				<div class="dy_t1">
                	<div class="dy_t1l">类型:</div>
                    <div class="dy_t1r">
                    	<a href="${ctx}/movie${suffix}?pager=1_" <#if ((pager.searchMap.type)!"0") == "0">class="a1"</#if>>全部</a>
                    	<#assign movieTypes=dicSetting.getParameterMap("movie.type") />
                    	<#list movieTypes?keys as value>
                    		<a href="${ctx}/movie${suffix}?pager=1_type--${value?html}" <#if ((pager.searchMap.type)!"0") == value>class="a1"</#if>>${movieTypes[value]?html}</a>
			            </#list>
                    </div>
                </div>
                <div class="dy_t2">
                	<div class="dy_t1l">版本:</div>
                    <div class="dy_t1r">
                    	<a href="${ctx}/movie${suffix}?pager=1_" <#if ((pager.searchMap.ver)!"0") == "0">class="a1"</#if>>全部</a>
                    	<#assign verTypes=dicSetting.getParameterMap("movie.ver") />
                    	<#list verTypes?keys as value>
                    		<a href="${ctx}/movie${suffix}?pager=1_ver--${value?html}" <#if ((pager.searchMap.ver)!"0") == value>class="a1"</#if>>${verTypes[value]?html}</a>
			            </#list>
                    </div>
                </div>
                <div class="nofl"></div>
			</div>
            
            <!--2015-4-27开始-->
            <div class="tab">
                <ul class="menu dy_sys">
                    <li <#if (pager.searchMap.movieState) == "1">class="p1"</#if>><a href="${ctx}/movie${suffix}?pager=1_movieState--1">正在热映</a></li>
                    <li <#if (pager.searchMap.movieState) != "1">class="p1"</#if>><a href="${ctx}/movie${suffix}?pager=1_movieState--2">即将上映</a></li>
                </ul>
                <div class="con1 dy1_com">
            		<ul class="dyrm_cm">
            		<#list pager.itemList as u>
            			<li>
	                        <div class="dyrm1_l">
	                        	<div class="dyrm1_l2">
	                            	<a href="${ctx}/movie/${u.id}.html" title="${u.name}"><img src="${tagUtils.getFileFullPath(u.icon)}" width="152" height="200"/></a>
	                            </div>
	                        </div>
	                        <div class="dyrm1_r">
	                        	<div class="p1"><a href="${ctx}/movie/${u.id}.html" title="${u.name}">${u.name}</a></div>
	                        	<div class="p2">主演 :<span> 
	                        	<#if u.movieActors?has_content>
	                        	<#list u.movieActors as movieActors>
	                        		${movieActors.actor.name}/
	                        	</#list>
	                        	</#if>
	                        	</if>
	                        	</span></div>
	                        	<div class="p2">类型 :<span> ${dicSetting.getParameterValue("movie.type." + u.type)}</span></div>
	                        	<div class="p2">国家/地区 :<span> ${dicSetting.getParameterValue("movie.countryArea." + u.countryArea)}</span></div>
	                        	<div class="p2">上映日期 :<span> ${tagUtils.formatDate((u.onlineTime)!0, 'yyyy-MM-dd')}</span></div>
	                        	<div class="p4">评分 :<span>${u.mark}</span></div>
	                            <div class="p3"><a href="${ctx}/movie/${u.id}.html">购票</a></div>
	                        </div>
	                    </li>
            		</#list>
                    
                <div class="nofl"></div>
               
                 <@h.page pager=pager action="${ctx}/movie${suffix}" />
                </div>
            </div>
            <!--2015-4-27结束--> 
            
            
                      
            
			<!--y-cinema end-->
		</div>
		<!--con-l end-->
		<#include "../public/common_page_right.ftl"/>
		<!--con-r end-->
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>