<title>${invitationCode.code} 的子邀请码列表</title>

<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="modal-body no-padding">
			<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
				<thead>
					<tr>
						<th>编号</th>
						<th>邀请码</th>
						<th>会员ID</th>
						<th>会员姓名</th>
						<th>手机号码</th>
						<th>状态</th>
					</tr>
				</thead>
				<tbody>
				<#list invitationCode.children as child>
				<tr>
					<td><span class="green center">${child.id}</span></td>
					<td>${child.code}</td>
					<td><#if child.state == 1>${child.registUser.id!}</#if></td>
					<td><#if child.state == 1>${child.registUser.realName!}</#if></td>
					<td><#if child.state == 1>${child.registUser.contact!}</#if></td>
						
					<td>
						<#if child.state == 0>
							<span class="label label-sm label-success arrowed">未被注册</span>
						<#elseif child.state == 1>
							<span class="label label-sm label-warning arrowed">已被注册</span>
						<#else>
							<span class="label label-sm label-warning arrowed">禁用状态</span>
						</#if>
					</td>
				</tr>
				</#list> 
				</tbody>
			</table>
	</div><!-- /.modal-content -->
</div></div>
<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"邀请码列表", linkUrl:"${ctx}/admin/invitation_code/list${suffix}"},{name:"子邀请码列表", active: true}]);
});
</script>