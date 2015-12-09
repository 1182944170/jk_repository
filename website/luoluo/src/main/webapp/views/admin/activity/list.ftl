<title>活动管理</title>
&nbsp;&nbsp;&nbsp;
		<h5><a href="${ctx}/admin/actcy/list${suffix}"><b>全部</b></a>
<#list cal as s>
		&nbsp;&nbsp;&nbsp;
		<a href="${ctx}/admin/actcy/${s.id}/listOlyeone${suffix}"><b>${s.claName}</b></a>
		&nbsp;&nbsp;&nbsp;&nbsp;
</#list>
</h5>
   
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>活动编号</th>
						<th>活动名称</th>
						<th>活动类别</th>
						<th>活动人数</th>
						<th>活动开始时间</th>
						<th>活动结束时间</th>
						<th>申请时间</th>
						<th>是否成功</th>
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span id="green11" class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.activitynumber!}</span></td>
						<td><span class="gray center">${u.activityname!}</span></td>
						 <@mifancut cmd="get_mfcutpic_list" type="${u.activitycategory!}" pagerString="1_" pageSize="50">
                			<#list p_pager.itemList as f>
				        <td><span class="gray center" style="color:Green">${f.claName}</span></td>
				            </#list>
  						</@mifancut>
				        <td><span class="gray center">${u.number!}</span></td>
			
				        <td><span class="gray center">${tagUtils.formatDate(u.starttime!)}</span></td>
				        <td><span class="gray center">${tagUtils.formatDate(u.outtime!)}</span></td>
				   
				        <td><span class="gray center">${tagUtils.formatDate(u.nowforetime!)}</span></td>
				       <#if u.type == 1 >
						<td><span class="gray center" style="color:Green"><b>已审核</b></span></td>
						</#if>
						<#if u.type == 0 >
						<td><span class="gray center" style="color:red"><b>未审核</b></span></td>
						</#if>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/actcy/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130">审核</i>
								</a>
								<a class="green" href="${ctx}/admin/actcy/${u.id}/zle${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130">修改</i>
								</a>
								<a class="red" href="${ctx}/admin/actcyitypic/${u.id}/list${suffix}"  alt="Delete">
									<img src="${ctx}/resources/images/xqing.jpg" width="15" height="15" alt="" />
								</a>
								<a class="red" href="${ctx}/admin/actcy/${u.id}/deletUser${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
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
											<a href="${ctx}/admin/actcy/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/actcy/${u.id}/deletUser${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
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
		<@h.page pager=pager action="${ctx}/admin/actcy/list" />
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"活动", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/actcy/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加活动</span></a>');
	});
</script>
