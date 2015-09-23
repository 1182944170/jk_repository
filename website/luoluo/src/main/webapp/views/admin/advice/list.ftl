<title>用户管理</title>
<div class="row">
	<div class="col-xs-12">
	<form action="${ctx}/admin/advice/seleNamePhone${suffix}" method="POST">
		<br>
		<a href="${ctx}/admin/advice/${1}/state${suffix}"><b>已处理查询</b></a> &nbsp;&nbsp;&nbsp; <a href="${ctx}/admin/advice/${0}/state${suffix}"><b>未处理查询</b></a>
	</form>
		<div class="table-responsive">
		<br>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户电话</th>
						<th>意见信息</th>
						<th>状态</th>
						<th></th>
						
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
			<#list pager.itemList as u>
						<td><span class="gray center">${u.id!}</span></td>
						<td><span class="gray center">${u.userphone!}</span></td>
						<th><span class="gray center">${u.comments!}</span></th>
						<td>
						<#if u.type == 0 >
						<span class="gray center" style="color:red"><b>未处理</b></span>
						
						</#if>
						<#if u.type == 1 >
						<span class="gray center" style="color:Green"><b>已处理</b></span>
						</#if>
						</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/advice/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>
								<#if u.type == 0 >
								
								<a class="red" href="${ctx}/admin/advice/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定允许用户么?');" alt="Delete">
									<img src="${ctx}/resources/images/jinzhi.jpg" width="15" height="15" alt="" />
								</a>
								</#if>
								<#if u.type == 1 >
								<a class="red" href="${ctx}/admin/advice/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定禁止用户么?');" alt="Delete">
									<img src="${ctx}/resources/images/zhengque.jpg" width="15" height="15" alt="" />
								</a>
								</#if>
								
			
							</div>
	
							<!--<div class="visible-xs visible-sm hidden-md hidden-lg">
								<div class="inline position-relative">
									<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
										<i class="icon-caret-down icon-only bigger-120"></i>
									</button>
									<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
										<li>
											<a href="${ctx}/admin/cal/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
	
										<li>
											<a href="${ctx}/admin/cal/delete${suffix}" onclick="return confirm('你确定禁止用户么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
												<span class="red">
													<i class="icon-trash bigger-120"></i>
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/cal/delete${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
												<span class="red">
													<i class="icon-trash bigger-120"></i>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>-->
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/user/list" />
		
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${infoMsg}</span>
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"用户", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;);
	});
</script>
