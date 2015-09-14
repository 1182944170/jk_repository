<div class="w_jdjs">
    	<div class="w_tab">
			<div class="hd">
            	<h5><a href="${ctx}/hotel${suffix}"><img src="${ctx}/resources/images/w_ico05.png" alt="" /></a></h5>
				<ul class="hotelClass">
					<li onclick="searchByCode(this,'hotelClass','hotelListClass');"><input type="hidden" value="hot"/>热门</li>
                </ul>
			</div>
			<div class="bd">
                <div class="w_food">
                	<dl>
                    	<dt><a href="${ctx}/hotel${suffix}"><img src="${ctx}/resources/images/w_pic15.jpg" width="462" height="292" alt="" /></a></dt>
                        <#list hotelList as u>
	                        <dd class="hotelListClass">
	                        	<a href="${ctx}/hotel/detail-${u.id}${suffix}"><img  width="218" height="166" alt="" /></a>
	                            <h5><a href="##">${u.shops.shopName!}</a></h5>
	                            <p>${u.name!}</p>
	                            <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                            <div class="hotelListChild"><u>
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pf.png" alt="" />
		                            <img src="${ctx}/resources/images/w_pfh.png" alt="" />
		                            	${u.star!}分</u>
	                            	<i>售出302</i>
                            	</div>
                            	 <input type="hidden" value="${u.shops.areaCode!}"/>
	                        </dd>
                       	</#list>               
                    </dl>                   
                </div>
                
			</div>
		</div>
    </div>