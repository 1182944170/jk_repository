<title>邀请码列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/invitation_code/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>厂家:</label>
	<@ace.formSingleSelect options=factoryList checkValue=(pager.searchMap.factoryId?number)!-1 name="factoryId" listKey="id" listValue="factoryName"/>
	
	
	<label>会员ID:</label>
	<input type="text" name="registUserId" value="${(pager.searchMap.registUserId)!''}" placeholder="会员ID"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>商圈编号</th>
						<th>邀请码</th>
						<th>所属厂家</th>
						<th>会员ID</th>
						<th>会员姓名</th>
						<th>手机号码</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.code}</td>
						<td>${(u.factory.factoryName)!}</td>
						<td><#if u.state == 1>${u.registUser.id!}</#if></td>
						<td><#if u.state == 1>${u.registUser.realName!}</#if></td>
						<td><#if u.state == 1>${u.registUser.contact!}</#if></td>
						<td>
							<#if u.state == 0>
								<span class="label label-sm label-success arrowed">未被注册</span>
							<#elseif u.state == 1>
								<span class="label label-sm label-warning arrowed">已被注册</span>
							<#else>
								<span class="label label-sm label-warning arrowed">禁用状态</span>
							</#if>
						</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/invitation_code/${u.code}/children${suffix}" alt="查看子邀请码">
								<i class="icon-pencil bigger-130"></i>查看子圈子
							</a>
							
							<a class="green" href="${ctx}/admin/invitation_code/${u.code}/change_factory${suffix}" alt="更换厂家">
								<i class="icon-pencil bigger-130"></i>更换厂家
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a class="green" href="${ctx}/admin/invitation_code/${u.code}/children${suffix}" alt="查看子邀请码">
											<i class="icon-pencil bigger-130"></i>查看子圈子
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
		<@h.page pager=pager action="${ctx}/admin/invitation_code/list${suffix}" />
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

function fromSearch(f){
	if(f.factoryId.value) {
		f.pager.value += "$$factoryId--" + f.factoryId.value;
	}
	if(f.registUserId.value) {
		f.pager.value += "$$registUserId--" + f.registUserId.value;
	}
	return true;
}


$(document).ready(function(){
	RP.addBreadcrumb([{name:"邀请码列表", linkUrl:"${ctx}/admin/invitation_code/list${suffix}", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/invitation_code/generate_auto${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">随机生成邀请码</span></a>');
});
</script>

