<title>字典列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>变量</th>
						<th>值</th>
						<th>类型</th>
						<th><i class="icon-time bigger-110 hidden-480"></i>可修改</th>
						<th><i class="icon-time bigger-110 hidden-480"></i>可删除</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.variable}</span></td>
						<td><span class="gray center">${u.value}</span></td>
						<td><span class="gray center">${u.type}</span></td>
						<td class="hidden-480">
							<#if u.canUpdate == 1>
								<span class="label label-sm label-success arrowed">可以</span>
							<#else>
								<span class="label label-sm label-warning arrowed">不可以</span>
							</#if>
						</td>
						
						<td class="hidden-480">
							<#if u.canDelete == 1>
								<span class="label label-sm label-success arrowed">可以</span>
							<#else>
								<span class="label label-sm label-warning arrowed">不可以</span>
							</#if>
						</td>

						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<#if u.canUpdate == 1>
							<a class="green" href="${ctx}/admin/dictionary/${u.variable}/edit${suffix}" alt="Edit">
								<i class="icon-pencil bigger-130"></i>
							</a>
							</#if>
							
							<#if u.canDelete == 1>
							<a class="red" href="${ctx}/admin/dictionary/${u.variable}/delete${suffix}" alt="Delete">
								<i class="icon-trash bigger-130"></i> 
							</a>
							</#if>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<#if u.canUpdate == 1>
									<li>
										<a href="${ctx}/admin/dictionary/${u.variable}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
									</#if>
									
									<#if u.canDelete == 1>
									<li>
										<a href="${ctx}/admin/dictionary/${u.variable}/delete${suffix}" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
											<span class="red">
												<i class="icon-trash bigger-120"></i>
											</span>
										</a>
									</li>
									</#if>
								</ul>
							</div>
						</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/dictionary/list${suffix}" />
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
	RP.addBreadcrumb([{name:"后台基础配置"}, {name:"字典列表", linkUrl:"${ctx}/admin/dictionary/list${suffix}", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/dictionary/sync${suffix}"><span class="label label-warning arrowed-in arrowed-in arrowed-right">同步字典</span></a>');
});
</script>

