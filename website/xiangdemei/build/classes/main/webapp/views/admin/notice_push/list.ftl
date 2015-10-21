<title>推送消息列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">ID</th>
						<th class="center">推送消息标题</th>
						<!-- <th class="center">扩展</th> -->
						<th class="center">类型</th>
						<th class="center">内容</th>
						<th class="center">协议</th>
						<th class="center">时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center"><span class="green center">${u.id}</span></td>
						<td class="center">${u.thumbTitle}</td>
						<!-- <td class="center">${(u.ext)!""}</td> -->
						<td class="center">${dicSetting.getParameterValue("push.pushType." + u.pushType)}</td>
						<td class="center">${u.content}</td>
						<td class="center">${u.url}</td>
						<td class="center">${tagUtils.formatDate(u.recordCreateTime)}</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/notice_push/list${suffix}" />
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
function fromSearch(f) {
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"消息推送列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/notice_push/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增推送消息</span></a>');
});
</script>