<div class="w_tsms">
    	<div class="w_tab">
			<div class="hd">
            	<h5><a href="${ctx}/food${suffix}"><img src="${ctx}/resources/images/w_ico04.png" alt="" /></a></h5>
				<ul class="foodClass">
                	<li onclick="searchByCode(this,'foodClass','foodListClass');"><input type="hidden" value="hot"/>热门</li>
                </ul>
			</div>
			<div class="bd">
                <div class="w_food">
                	<dl>
                    	<dt><a href="${ctx}/food${suffix}"><img src="${ctx}/resources/images/w_pic11.jpg" width="462" height="292" alt="" /></a></dt>
                        <#list foodList as u>
                        <#-- 当前商品所属三级地区-->
                        <dd class="foodListClass">
                        	<a href="${ctx}/food/detail-${u.id}${suffix}"><img  width="218" height="166" alt="" /></a>
                            <h5><a href="##">${u.shops.shopName!}</a></h5>
                            <p>${u.name!}</p>
                            <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
                            <div class="foodListChild"><u>
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
	                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
	                            	${u.star!}分</u>
                            	<i>售出30${u_index}</i>
                        	</div>
	                        <input type="hidden" value="${u.shops.areaCode!}"/>
                        </dd>
                       	</#list> 
                    </dl>                       	
                </div>
			</div>
		</div>
</div>