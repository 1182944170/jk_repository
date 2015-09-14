<div class="w_picture">
    	<h5><img src="${ctx}/resources/images/w_ico01.png" alt="" /><a href="${ctx}/photo${suffix}">画册</a></h5>    
		<#list spotsPhotoList as u>    
	        <dl>
	        	<dt><a href="${ctx}/photo/list-${u.spots.id}${suffix}">
	        		<img src="${tagUtils.getFileFullPath(u.cover!)}" width="218" height="211" alt="" /></a></dt>
	            <dd><a href="${ctx}/photo/list-${u.id}${suffix}">${u.photoName!}</a></dd>
	        </dl>
    	</#list>
    </div>