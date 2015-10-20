<title>用户列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
	<label>会员姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="会员姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>

<table>
	<tr>
		<th width="100px"> <a href="${ctx}/admin/user/list${suffix}" > 查看所有 </a> </th>
		<th width="150px"> <a href="${ctx}/admin/user/list${suffix}?isSalesman=0" > 查看一级会员 </a> </th>
		<th width="150px"> <a href="${ctx}/admin/user/list${suffix}?state=1&isSalesman=1" > 查看二级会员 </a> </th>
		<th width="150px"> <a href="${ctx}/admin/user/list${suffix}?state=0&isSalesman=0" > 查看二级会员申请 </a> </th>
	</tr>
</table>

<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>会员姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th>状态</th>
						<th>身份</th>
						<th>注册时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as m>
					<tr>
						<td><span class="green center">${m.id}</span></td>
						<td><span class="gray center">${m.realName}</span></td>
						<td><#if m.sex == 1>男<#else>女</#if>
						</td>
						<td>${m.contact}</td>
						<td>
							<div class="col-xs-3">
								<label>
									<input name="switch-field-1" class="ace ace-switch ace-switch-4" type="checkbox" onchange="doUserState(this, ${m.id});" <#if m.state == 1>checked=true</#if>>
									<span class="lbl"></span>
								</label>
							</div>
						</td>
						<td>
						<#if m.isSalesman == 0 >
							<#if m.userSalesman??>
								<#if m.userSalesman.state==0>
									<span class="label label-sm label-warning arrowed arrowed-righ">申请二级会员中...</span>
								<#elseif m.userSalesman.state==-1>
									<span class="label label-sm label-warning arrowed arrowed-righ">申请二级会员失败</span>
								</#if>
								[<a href="${ctx}/admin/usersalesman/${m.userSalesman.userId}/edit${suffix}">查看申请信息</a>]
							<#else>
								一级会员
							</#if>
						<#else>
							<span class="text-warning bigger-110 orange">
							二级会员[所在楼盘:${m.userSalesman.house.name}<#if m.userSalesman.isLeader==1>-该楼盘的负责人</#if>]
							</span>
						</#if>
						</td>
						<td>${tagUtils.formatDate(m.recordCreateTime)}</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user/${m.id}/detail${suffix}" alt="Detail">
								<i class="icon-pencil bigger-130"></i>查 看
							</a>
							<#if m.isSalesman == 1>
								
							</#if>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/user/${m.id}/detail${suffix}" class="tooltip-success" data-rel="tooltip" title="查看" data-original-title="Detail">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
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
		<@h.page pager=pager action="${ctx}/admin/user/list${suffix}" />
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
	if(f.contact.value) {
		f.pager.value += "$$contact--" + f.contact.value;
	}
	if(f.realName.value) {
		f.pager.value += "$$realName--" + f.realName.value;
	}
	return true;
}

function doUserState(checkbox, uid) {
	var href = ctx + "/admin/user/" + uid;
	if(checkbox.checked) {
		href += "/on"
	} else {
		href += "/off"
	}
	
	window.location.href = href;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"会员管理"}, {name:"会员列表", linkUrl:"${ctx}/admin/user/list", active: true}]);
});
</script>

