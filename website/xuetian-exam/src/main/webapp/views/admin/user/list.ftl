<title>用户列表</title>

<form class="form-horizontal" role="form" id="validation-form" method="GET" action="${ctx}/admin/user/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>昵称</th>
						<th>联系</th>
						<th>状态</th>
						<th>注册时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.userName}</span></td>
						<td>${u.nickName}</td>
						<td>${u.contact}</td>
						<td class="center">
							<div class="col-xs-3">
								<label>
									<input name="switch-field-1" class="ace ace-switch ace-switch-4" type="checkbox" onchange="doUserState(this, ${u.id});" <#if u.state == 1>checked=true</#if>>
									<span class="lbl"></span>
								</label>
							</div>
						</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/user/list" />
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

function doUserState(checkbox, uid) {
	var href = ctx + "/admin/user/" + uid;
	if(checkbox.checked) {
		href += "/on"
	} else {
		href += "/off"
	}
	
	window.location.href = href;
}
function fromSearch(f){
	return false;
}

$(document).ready(function() {
	RP.addBreadcrumb([{name:"用户列表"}]);
});
</script>

