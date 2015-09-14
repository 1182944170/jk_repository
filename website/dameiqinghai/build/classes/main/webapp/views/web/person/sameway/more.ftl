<div class="w_tymid">
		<#list wayList as u>
    		<div class="w_tycon">
	        	<div class="w_tycontu">
	            	<a href="${ctx}/sameway/detail-${u.id}${suffix}" class="wayListClass"><img src="${ctx}/resources/images/w_pic05.jpg" width="283" height="188" alt="" /></a>
	                <div class="w_tucon">
	                	<p>15<em>天</em></p>
	                    <span><em>${tagUtils.formatDate(u.createTime)!}</em><i>${u.rentType!}</i></span>
	                </div>
	            </div>
	            <div class="w_zicon">
	                <p><a href="${ctx}/sameway/detail-${u.id}${suffix}">${u.toAddress!}</a></p>
	                <span><img src="${tagUtils.getFileFullPath(u.user.photo)!}" width="28" height="29" alt="" /><em>${u.user.nickName}</em><a href="##">我要报名</a></span>
	            </div>
        	</div>
    	</#list>
    </div>