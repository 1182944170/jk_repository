<title>资源映射列表</title>
<div class="row">
	<div class="col-xs-12">
		<h3 class="header smaller lighter green">资源映射列表</h3>
		<#list list as vo>
			<div>
			<span class="blue bigger-150">${vo.name}&nbsp;&nbsp;</span>
			<div class="btn-group">
				<a class="green" href="${ctx}/admin/adminauthres/${vo.id}/edit" alt="Edit">
					<i class="icon-pencil bigger-130"></i>
				</a>

				<a class="red" href="${ctx}/admin/adminauthres/${vo.id}/delete" alt="Delete">
					<i class="icon-trash bigger-130"></i>
				</a>
			</div>
			<#if vo.children?has_content>
				<div>
					<#list vo.children as voChild>
					<span>
						<span class="green bigger-110">&nbsp;&nbsp;&nbsp;&nbsp;${voChild.name}&nbsp;&nbsp;</span>
						<a class="green" href="${ctx}/admin/adminauthres/${voChild.id}/edit" alt="Edit">
							<i class="icon-pencil bigger-130"></i>
						</a>
		
						<a class="red" href="${ctx}/admin/adminauthres/${voChild.id}/delete" alt="Delete">
							<i class="icon-trash bigger-130"></i>
						</a>
					</span>
					</#list>
				</div>
			</#if>
			<div>
			<div class="hr hr-18 hr-double dotted"></div>
		</#list>
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}</span>
	</h4>
</#if>

<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink">${infoMsg}</span>
	</h4>
</#if>
<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"资源映射配置"}, {name:"资源映射列表", linkUrl:"${ctx}/admin/adminauthres/list", active: true}]);
});
</script>

