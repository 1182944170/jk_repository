<title>招聘列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>招聘名称</th>
						<th>工作地区</th>
						<th>工作岗位</th>
						<th>岗位人数</th>
						<th>年龄要求</th>
						<th>经验要求</th>
						<th>学历要求</th>
						<!--<th>职位性质</th>-->
						<th>职位月薪</th>
						<th>联系电话</th>
						<th>佣金提点</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.name}</td>
						<td>${u.workAddress}</td>
						<td>${u.remark}</td>
						<td>${u.countString}</td>
						<td>${u.ageTypeString}</td>
						<td>${dicSetting.getParameterValue("job.expType." + u.expType)}</td>
						<td>${dicSetting.getParameterValue("job.eduType." + u.eduType)}</td>
						<!--<td>${dicSetting.getParameterValue("job.jobType." + u.jobType)}</td>-->
						<td>${u.money}</td>
						<td>${u.contact}</td>
						<td>${u.commissionPercent}</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/job/${u.id}/edit" alt="Edit">
								<i class="icon-pencil bigger-130"></i>
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/job/${u.id}/edit" class="tooltip-success" data-rel="tooltip" title="修改" data-original-title="Edit">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
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
		<@h.page pager=pager action="${ctx}/admin/job/list" />
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
	RP.addBreadcrumb([{name:"招聘管理"}, {name:"招聘列表", linkUrl:"${ctx}/admin/job/list", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/job/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增招聘</span></a>');
});
</script>

