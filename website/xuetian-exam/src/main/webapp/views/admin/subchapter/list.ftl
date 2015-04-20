<title>章节[${chapter.name}-> 试卷列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>专业名称</th>
						<th>简写</th>
						<th>总时间(分)</th>
						<th>总数量</th>
						<th>总分数</th>
						<th>及格分数</th>
						<th>价格/折扣</th>
						<th><i class="icon-time bigger-110"></i> 状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list list as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.name}</span></td>
						<td>${u.thumb}</td>
						<td>${u.examTime}</td>
						<td>${u.totalSubjectNum}</td>
						<td>${u.totalScore}</td>
						<td>${u.passScore}</td>
						<td><#if u.price == 0>
								<span class="label label-sm label-warning arrowed">Free</span>
							<#else>
								<span class="label label-sm label-success arrowed">${u.price} [折后价 ${u.price * u.discount}]</span>
							</#if>
						</td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常状态</span>
							<#else>
								<span class="label label-sm label-warning arrowed">禁用状态</span>
							</#if>
						</td>

						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/subchapter/${u.id}/edit" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
							
							<a class="green" href="${ctx}/admin/subchapter/${u.id}/subjects" alt="查看试题列表">
								<i class="icon-calendar bigger-130"></i>查看试题列表
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/subchapter/${u.id}/edit" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
									<li>
										<a href="${ctx}/admin/subchapter/${u.id}/subjects" class="tooltip-success" data-rel="tooltip" title="" data-original-title="查看试题列表">
											<span class="green">
												<i class="icon-calendar bigger-120"></i>
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
$(document).ready(function() {
	RP.addBreadcrumb([{name:"题库"}, {name:"${chapter.name}章节", linkUrl:"${ctx}/admin/specialty/${chapter.parent.id}/chapters"},{name:"试卷列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/subchapter/add?chapterId=${chapter.id}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增试卷</span></a>');
});
</script>

