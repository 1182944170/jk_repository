<title>大唐图库</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名称</th>
						<th>留言内容</th>
						<th>用户电话</th>
						<th>时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pagerMessage.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.name}</span></td>
						<td><span class="green center">${u.content}</span></td>
						<td><span class="gray center">${u.phone}</span></td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</span></td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div>
	
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pagerMessage action="${ctx}/admin/picture/list" />
	</div><!-- /span -->
</div><!-- /row -->




<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础配置"}, {name:"留言列表", active: true}]);
	
});
</script>