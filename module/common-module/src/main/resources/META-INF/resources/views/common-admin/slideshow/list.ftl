<title>幻灯片列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>缩略图</th>
						<th>标题</th>
						<th>链接地址</th>
						<th>类型</th>
						<th>排列</th>
						<th><i class="icon-time bigger-110 hidden-480"></i> 状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list list as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><img src="${tagUtils.getFileFullPath(u.icon)}" alt="${u.icon}" width=100 /></td>
						<td>${u.title}</td>
						<td>${u.url}</td>
						<td>${dicSetting.getParameterValue("slideshow.type." + u.type)}</td>
						<td>${u.sortIndex}</td>
						<td class="hidden-480">
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常状态</span>
							<#else>
								<span class="label label-sm label-warning arrowed">禁用状态</span>
							</#if>
						</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/common/slideshow/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
							<a class="red" href="${ctx}/admin/common/slideshow/${u.id}/delete${suffix}" alt="Delete">
								<i class="icon-trash bigger-130"></i> 
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/common/slideshow/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
									<li>
										<a href="${ctx}/admin/common/slideshow/${u.id}/delete${suffix}" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
											<span class="red">
												<i class="icon-trash bigger-120"></i>
											</span>
										</a>
									</li>
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
	RP.addBreadcrumb([{name:"基础配置"}, {name:"幻灯片列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/common/slideshow/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增幻灯片</span></a>');
});
</script>