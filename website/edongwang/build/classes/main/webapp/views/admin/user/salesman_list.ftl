<title>用户列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user/salesman_list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>楼盘名称:</label>
	<input type="text" name="houseName" value="${(pager.searchMap.houseName)!''}" placeholder="楼盘名称"/>
	
	<label>会员姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="会员姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>楼盘名称</th>
						<th>楼盘人员姓名</th>
						<th>性别</th>
						<th>手机号</th>
						<th>状态</th>
						<th>身份(是否楼盘负责人)</th>
						<th>注册时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as m>
					<tr>
						<td><span class="green center">${m.userSalesman.house.name}</span></td>
						<td><span class="gray center">${m.realName}</span></td>
						<td><#if m.sex == 1>男<#else>女</#if></td>
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
							<div class="col-xs-3">
								<label>
									<input name="switch-field-1" class="ace ace-switch ace-switch-4" type="checkbox" onchange="doUserLeaderState(this, ${m.id});" <#if m.userSalesman.isLeader == 1>checked=true</#if>>
									<span class="lbl"></span>
								</label>
							</div>
						</td>
						<td>${tagUtils.formatDate(m.recordCreateTime)}</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/user/salesman_list" />
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
	if(f.houseName.value) {
		f.pager.value += "$$houseName--" + f.houseName.value;
	}
	if(f.realName.value) {
		f.pager.value += "$$realName--" + f.realName.value;
	}
	return true;
}

function doUserState(checkbox, uid) {
	var href = ctx + "/admin/user/" + uid;
	if(checkbox.checked) {
		href += "/on?redirect=/admin/user/salesman_list"
	} else {
		href += "/off?redirect=/admin/user/salesman_list"
	}
	
	window.location.href = href;
}

function doUserLeaderState(checkbox, uid) {
	var href = ctx + "/admin/user/" + uid;
	if(checkbox.checked) {
		href += "/on_leader?redirect=/admin/user/salesman_list"
	} else {
		href += "/off_leader?redirect=/admin/user/salesman_list"
	}
	
	window.location.href = href;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"会员管理"}, {name:"会员列表", linkUrl:"${ctx}/admin/user/salesman_list", active: true}]);
});
</script>

