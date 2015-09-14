<div class="w_youj">
    	<div class="w_yjcon">
        	<div class="w_yjgz">
            	<a href="${ctx}/travel/detail${suffix}">
                	<img src="${ctx}/resources/images/w_ico02.png" width="35" height="35" alt="" />
                    <em>游记</em>
                </a>
            </div>
            <div class="w_ycon">
            	<dl>
					<#list travelList as u>
						<dt>
						<a href="${ctx}/travel/detail-${u.id}${suffix}"><img src="${tagUtils.getFileFullPath(u.cover!)}" width="328" height="421" alt="" /></a>
						<h5><a href="${ctx}/travel/detail${suffix}">${u.title!}</a></h5>
						<p>${u.introduction!}</p>
						</dt>
					</#list>
                </dl>
            </div>
        </div>
    </div>
