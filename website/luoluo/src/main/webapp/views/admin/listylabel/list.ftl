<title>标签管理</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<br>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>标签</th>
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
			<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.label}</span></td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/lable/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/lable/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							
							</div>

						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/cal/list" />
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"标签", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/lable/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加标签</span></a>');
	});
</script>
