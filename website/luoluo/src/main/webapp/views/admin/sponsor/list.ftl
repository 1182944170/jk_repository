<title>主办方管理</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						
						<th>真实头像</th>
						<th>领队电话</th>
						
				<#if se??>
						<th>公司姓名</th>
						<th>公司负责手机</th>
						<th>公司电话</th>
						
						<th>公司信息</th>
				</#if>		
						<th>类型</th>
						<th>状态</th>
						<th>注册时间</th>
						
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<input type="hidden" id="toor" value="${u.type}">
						<td><span class="green center">${u.id}</span></td>
						
  						<th><img src="${tagUtils.getFileFullPath(u.userpicture!)}" width="90px" height="40px"/></th>
							<td><span class="gray center">${u.userphone!}</span></td>
					        
				        <#if u.type==2>
							<td><span class="gray center">${u.companyname!}</span></td>
							<td><span class="gray center">${u.usertelephone!}</span></td>
							<td><span class="gray center">${u.telephone!}</span></td>
							
					        <td>
					        	<input type="text" value="${u.entintroduction!}" readonly style="overflow-x:visible;width:210px;border:0px solid #F9F9F9" >
					       	
					        </td>
				        </#if>
				        <#if u.type==1>
				        	<td><span class="gray center">个人</span></td>
				        </#if>
				        <#if u.type==2>
				        	<td><span class="gray center">公司</span></td>
				        </#if>
				       <#if u.typeopp == 1 >
						<td><span class="gray center" style="color:Green"><b>已审核</b></span></td>
						</#if>
						<#if u.typeopp == 0 >
						<td><span class="gray center" style="color:red"><b>未审核</b></span></td>
						</#if>
				        
				        
				         	<td><span class="gray center">${tagUtils.formatDate(u.activityTime)!""}</span></td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/spons/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>
							<#if u.id !=1>
								<a class="red" href="${ctx}/admin/spons/${u.id}/delete${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							<#else>
							<a class="red" href="${ctx}/admin/spons/${u.id}/addlis${suffix}" onclick="return confirm('你确定修改么?');" alt="Edit">
									<i class="icon-trash bigger-130">修改</i>
								</a>
							
							</#if>
							
							
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
		<#list pager.itemList as s>
			<#if s.type==1>
				<@h.page pager=pager action="${ctx}/admin/individuallist/list" />
			</#if>
		</#list>
		<#list pager.itemList as f>
			<#if f.type==2>
				<@h.page pager=pager action="${ctx}/admin/companylist/list" />
			</#if>
		</#list>
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
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/spons/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增<#if se??>企业<#else>个人</#if>管理</span></a>');
	});
</script>
