<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>
<!--content begin-->
<div class="content">
	<div class="center clearfix">
		<div class="con-l">
		${notice.title} 发布与 ${tagUtils.formatDate(notice.recoreCreateTime)}
		<h2 />
		
		${notice.content}
		</div>
		
		<!--con-l end-->
		<#include "../public/common_page_right.ftl"/>
		<!--con-r end-->
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>