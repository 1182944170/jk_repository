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
	                            	<img src="${tagUtils.getFileFullPath(u.icon)}" width="152" height="200"/>
	                            </div>
	                        </div>
	                        <div class="dyrm1_r">
	                        	<div class="p1"><a href="${ctx}/movie/${u.id}/view.html" title="${u.name}">${u.name}</a></div>
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
	                            <div class="p3"><a href="${ctx}/movie/${u.id}/view.html">购票</a></div>
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
		<div class="con-r">
			<#include "../notice/notice_thumb.ftl"/>
			<div class="hot-column">
				<div class="tab-title-box02">
					<span class="title-blue">热映TOP排行</span>
				</div>
				<ul class="hot-list">
					<li class="hot-fir">
						<a>
							<div class="hot-img-box"><img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="hot-img"></div>
							<dl class="hot-infor">
								<dt>忍者神龟</dt>
								<dd>主演：梅根·福克斯/梅根·福克斯/</dd>
								<dd>类型：动作/科幻</dd>
								<dd>上映时间：10月31日</dd>
							</dl>
						</a>
					</li>
					<li><a><i>2</i>一个人的武林</a><span class="hot-score"><span class="hot-num">7.7</span>分</span></li>
					<li><a><i>3</i>一个人的武林<span class="hot-score"><span class="hot-num">7.7</span>分</span></a></li>
					<li><a><i>4</i>一个人的武林<span class="hot-score"><span class="hot-num">7.7</span>分</span></a></li>
					<li><a><i>5</i>一个人的武林<span class="hot-score"><span class="hot-num">7.7</span>分</span></a></li>
					<li class="last-li"><a><i>6</i>一个人的武林<span class="hot-score"><span class="hot-num">7.7</span>分</span></a></li>
				</ul>
			</div>
			<!--hot-column end-->
			<div class="comment-column">
				<div class="tab-title-box02">
					<span class="title-blue">精彩点评</span>
				</div>
				<ul class="comment-list">
					<li>
						<a>
							<div class="comment-infor">
								<div class="comment-img-box">
									<img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="comment-img">
								</div>
								<span>晃着晃着</span>
							</div>
							<span class="comment-talk">《沉睡魔咒》先吐槽下，在环球港海上国际影城看的，屏幕还是暗，摩尔森林的那些景致</span>
						</a>
					</li>
					<li>
						<a>
							<div class="comment-infor">
								<div class="comment-img-box">
									<img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="comment-img">
								</div>
								<span>晃着晃着</span>
							</div>
							<span class="comment-talk">《沉睡魔咒》先吐槽下，在环球港海上国际影城看的，屏幕还是暗，摩尔森林的那些景致</span>
						</a>
					</li>
					<li>
						<a>
							<div class="comment-infor">
								<div class="comment-img-box">
									<img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="comment-img">
								</div>
								<span>晃着晃着</span>
							</div>
							<span class="comment-talk">《沉睡魔咒》先吐槽下，在环球港海上国际影城看的，屏幕还是暗，摩尔森林的那些景致</span>
						</a>
					</li>
					<li>
						<a>
							<div class="comment-infor">
								<div class="comment-img-box">
									<img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="comment-img">
								</div>
								<span>晃着晃着</span>
							</div>
							<span class="comment-talk">《沉睡魔咒》先吐槽下，在环球港海上国际影城看的，屏幕还是暗，摩尔森林的那些景致</span>
						</a>
					</li>
					<li>
						<a>
							<div class="comment-infor">
								<div class="comment-img-box">
									<img src="${ctx}/resources/images/demoimg/hot_img.jpg" class="comment-img">
								</div>
								<span>晃着晃着</span>
							</div>
							<span class="comment-talk">《沉睡魔咒》先吐槽下，在环球港海上国际影城看的，屏幕还是暗，摩尔森林的那些景致</span>
						</a>
					</li>
				</ul>
			</div>
			<!--comment-column end-->
		</div>
		<!--con-r end-->
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>