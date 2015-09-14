<div class="w_dytj">
                <h4><a href="${ctx}/guide/detail${suffix}">推荐导游</a></h4>
                <ul>
                	<#list guideList as u>
	                    <li>
	                        <a href="${ctx}/guide/detail-${u.id}${suffix}" class="guideListClass"><img src="${ctx}/resources/images/w_pic02.jpg" width="50" height="50" alt="" /></a>
	                        <h5><a href="${ctx}/guide/detail-${u.id}${suffix}">${u.guideName!}</a>
	                        	<#if u.sex == 1>
	                        		<img src="${ctx}/resources/images/w_sex2.png" width="10" height="15" alt="" />
	                        	<#else>
	                        		<img src="${ctx}/resources/images/w_sex1.png" width="10" height="15" alt="" />
	                        	</#if>
	                        	<em>￥<i>${u.price!}</i>/天</em></h5>
	                        <p class="guideListChild">
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
	                            <span>5分</span>
	                        </p>
	                    </li>
                    </#list>
                </ul>
             </div>