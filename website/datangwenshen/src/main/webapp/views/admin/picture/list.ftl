<title>大唐图库</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>图片名称</th>
						<th>类型</th>
						<th>来源</th>
						<th>地址</th>
						<th>状态</th>
						<th>时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.name}</span></td>
						<td>${dicSetting.getParameterValue("picture.source."+u.source+".type." + u.type)}</td>
						<td>${dicSetting.getParameterValue("picture.sourceType."+u.source)}</td>
						<td><img src="${tagUtils.getFileFullPath(u.address)}" width=100/></td>
						<td class="hidden-480">
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">显示状态</span>
							<#else>
								<span class="label label-sm label-warning arrowed">隐藏状态</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.date)}</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/picture/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/picture/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
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
											<a href="${ctx}/admin/picture/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
	
										<li>
											<a href="${ctx}/admin/picture/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
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
		<@h.page pager=pager action="${ctx}/admin/picture/list" />
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"大唐图片列表", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/picture/add${suffix}?source=1"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增大唐图片</span></a>');
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/picture/add${suffix}?source=2"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增网络图片</span></a>');
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/picture/add${suffix}?source=3"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增穿刺图片</span></a>');
	});
</script>
