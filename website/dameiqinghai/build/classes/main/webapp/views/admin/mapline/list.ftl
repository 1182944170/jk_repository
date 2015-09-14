<title>线路列表</title>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>标题</th>
						<th>推荐理由</th>
						<th>出发地点</th>
						<th>目的地点</th>
						<th>预算费用</th>
						<th>是否推荐</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list maplinePager.itemList as u>
					<tr>
						<td>${u.id}</span></td>
						<td>${u.title!}</span></td>
						<td>${u.commendCause!}</span></td>
						<td>${u.goAddress!}</td>
						<td>${u.toAddress!}</td>
						<td>${u.budget!}</td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/mapline/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/mapline/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/mapline/edit-${u.id}${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>

								<a class="red" href="${ctx}/admin/mapline/delete-${u.id}${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
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
	RP.addBreadcrumb([{name:"基础配置"}, {name:"线路管理", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/mapline/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加线路</span></a>');
});
</script>

