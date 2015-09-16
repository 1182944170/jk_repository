<title>主办方管理</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>领队姓名</th>
						<th>领队电话</th>
						<th>领队信息</th>
				<#if se??>
						<th>公司负责手机</th>
						<th>公司电话</th>
						<th>公司负责人信息</th>
						<th>公司信息</th>
				</#if>		
						<th>类型</th>
						<th>注册时间</th>
						
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						 <@showprive cmd="get_showprive_list" type="${u.userid!}" pagerString="1_" pageSize="50">
                			<#list m_pager.itemList as f>
								<td><span class="gray center">${f.name!}</span></td>
						    </#list>
  						</@showprive>
							<td><span class="gray center">${u.user_phone!}</span></td>
					        <td><span class="gray center">${u.user_information!}</span></td>
				        <#if u.type==2>
					        <td><span class="gray center">${u.user_telephone}</span></td>
							<td><span class="gray center">${u.telephone!}</span></td>
							<td><span class="gray center">${u.responsibility!}</span></td>
					        <td><span class="gray center">${u.ent_introduction!}</span></td>
				        </#if>
				        <#if u.type==1>
				        	<td><span class="gray center">个人</span></td>
				        </#if>
				        <#if u.type==2>
				        	<td><span class="gray center">公司</span></td>
				        </#if>
				         	<td><span class="gray center">${u.activityTime!}</span></td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/spons/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/spons/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
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
											<a href="${ctx}/admin/spons/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/spons/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
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
		<@h.page pager=pager action="${ctx}/admin/good/list" />
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"活动添加", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/good/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加商品管理</span></a>');
	});
</script>
