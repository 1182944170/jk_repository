<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>
<!--content begin-->
<div class="content">
	<div class="center clearfix">
		${notice.title} 发布与 ${tagUtils.formatDate(notice.recoreCreateTime)}
		<h2 />
		
		${notice.content}
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>