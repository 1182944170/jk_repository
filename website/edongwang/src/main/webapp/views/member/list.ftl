<title>用户列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>真实姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th>状态</th>
						<th>是否是负责人</th>
						<th><i class="icon-time bigger-110 hidden-480"></i>审核状态</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as m>
					<tr>
						<td><span class="green center">${m_index+1}</span></td>
						<td><span class="gray center">${m.realName}</span></td>
						<td><#if m.sex == 1>先生<#else >女士</#if>
						</td>
						<td>${m.telNumber}</td>
						<td>
							<#if m.status ==0 >
								<span class="label label-sm label-success arrowed arrowed-righ">激活</span>
							<#else>
								<span class="label label-sm label-warning arrowed arrowed-righ">未激活</span>
							</#if>
						</td>
						<td>
						<#if m.isLeader == 1 >
							<span class="text-warning bigger-110 orange"> 负责人</span>
						<#else>
							普通会员
						</#if>
						</td>
						<td class="hidden-480">
							<#if m.auditStatus == 0>
								<span class="label label-sm label-primary arrowed arrowed-right">正常</span>
							<#elseif  m.auditStatus == 1>
								<span class="label label-lg label-yellow arrowed-in">审核中</span>
							<#else>
								<span class="label label-sm label-success arrowed arrowed-righ">审核通过</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(m.registerDate)}</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/adminuser/${m.id}/edit" alt="Edit">
								<i class="icon-pencil bigger-130"></i>
							</a>

							<a class="red" href="${ctx}/admin/adminuser/${m.id}/delete" alt="Delete">
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
										<a href="${ctx}/admin/adminuser/${m.id}/edit" class="tooltip-success" data-rel="tooltip" title="修改" data-original-title="Edit">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>

									<li>
										<a href="${ctx}/admin/adminuser/${m.id}/delete" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
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
		<@h.page pager=pager action="${ctx}/admin/member/list" />
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
	RP.addBreadcrumb([{name:"会员管理"}, {name:"会员列表", linkUrl:"${ctx}/admin/member/list", active: true}]);
});
</script>

