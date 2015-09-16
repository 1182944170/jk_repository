<title>分类管理</title>
<div class="row">
	<div class="col-xs-12">
	<form action="${ctx}/admin/user/seleNamePhone${suffix}" method="POST">
		<input type="text" name="nameone"><input type="submit" value="搜索">&nbsp;&nbsp;&nbsp;
		<a href="${ctx}/admin/user/${1}/state${suffix}"><b>允许的用户查询</b></a> &nbsp;&nbsp;&nbsp; <a href="${ctx}/admin/user/${0}/state${suffix}"><b>禁止的用户查询</b></a>
	</form>
		<div class="table-responsive">
		<br>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户姓名</th>
						<th>用户性别</th>
						<th>用户电话</th>
						<th>用户密码</th>
						<th>状态</th>
						<th></th>
						
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
			<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="gray center">${u.name!}</span></td>
						<th>
						<#if u.sex == 1 >
						女
						</#if>
						<#if u.sex == 0 >
						男
						</#if>
						</th>
					
						<td><span class="gray center">${u.phone!}</span></td>
						<th><span class="gray center">${u.password!}</span></th>
						<td>
						<#if u.type == 0 >
						<span class="gray center" style="color:Green"><b>允许</b></span>
						</#if>
						<#if u.type == 1 >
						<span class="gray center" style="color:red"><b>禁止</b></span>
						</#if>
						</td>
				
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/user/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>
								<#if u.type == 1 >
								<a class="red" href="${ctx}/admin/user/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定禁止用户么?');" alt="Delete">
									<img src="${ctx}/resources/images/zhengque.jpg" width="15" height="15" alt="" />
								</a>
								</#if>
								<#if u.type == 0 >
								<a class="red" href="${ctx}/admin/user/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定允许用户么?');" alt="Delete">
									<img src="${ctx}/resources/images/jinzhi.jpg" width="15" height="15" alt="" />
								</a>
								</#if>
								
								<a class="red" href="${ctx}/admin/user/${u.id}/deletUser${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							
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
		<span class="pink"></span>
	</h4>
</#if>

<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink"></span>
	</h4>
</#if>
<script>
	$(document).ready(function(){
		RP.addBreadcrumb([{name:"基础配置"}, {name:"添加用户", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/user/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加分类管理</span></a>');
	});
</script>
