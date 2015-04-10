<title>用户列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>序号</th>
						<th>物业类型</th>
						<th>楼盘名称</th>
						<th>区域</th>
						<th>面积</th>
						<th>总价</th>
						<th>推荐奖励</th>
						<th>成交奖励</th>
						<th>协议时间</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as h>
					<tr>
						<td><span class="green center">${h_index+1}</span></td>
						<td><span class="gray center"></span></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td class="hidden-480">
						
						</td>
						<td></td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/adminuser/${h.id}/edit" alt="Edit">
								<i class="icon-pencil bigger-130"></i>
							</a>

							<a class="red" href="${ctx}/admin/adminuser/${h.id}/delete" alt="Delete">
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
										<a href="${ctx}/admin/adminuser/${h.id}/edit" class="tooltip-success" data-rel="tooltip" title="修改" data-original-title="Edit">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>

									<li>
										<a href="${ctx}/admin/adminuser/${h.id}/delete" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
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
		<@h.page pager=pager action="${ctx}/admin/house/list" />
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
	RP.addBreadcrumb([{name:"楼盘管理"}, {name:"楼盘列表", linkUrl:"${ctx}/admin/house/list", active: true}]);
});
</script>

