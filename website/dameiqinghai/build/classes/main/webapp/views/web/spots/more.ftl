<div class="w_jdwl">
    	<div class="w_tab">
			<div class="hd">
            	<h5><a href="##"><img src="${ctx}/resources/images/w_ico03.png" alt="" /></a></h5>
				<ul class="spotsClass">
                	<li onclick="searchByCode(this,'spotsClass');"><input type="hidden" value="hot"/>热门</li>
                </ul>
			</div>
			<div class="bd">
				<div class="w_jd">
                	<div class="w_jdl fl">
                    	<ul>
                    		<#list spotsList as u>
	                        	<li>
	                            	<a href="${ctx}/spots/detail-${u.id}${suffix}"><img src="${tagUtils.getFileFullPath(u.photos!)}" width="218" height="166" alt="" /></a>
	                            	<p><a href="${ctx}/spots/detail-${u.id}${suffix}">${u.spotsName!}</a></p>
	                                <span>${commonTag.getCountyPath(u.areaCode!)}</span>
	                            </li>
                            </#list>
                        </ul>
                    </div>
                    <div class="w_jdr fr">
                    	<a href="##"><img src="${ctx}/resources/images/w_pic09.jpg" width="218" height="434" alt="" /></a>
                        <div class="w_jdcon">
                            <h5><a href="##">青海湖风景</a></h5>
                            <p><a href="##">看油菜花<br />玩转丝绸之路</a></p>
                        </div>
                    </div>
                </div>
				
			</div>
		</div>
    </div>