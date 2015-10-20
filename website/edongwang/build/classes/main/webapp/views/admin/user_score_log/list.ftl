<title>用户积分日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_score_log/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
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
						<th>ID</th>
						<th>会员ID/会员姓名/会员手机号</th>
						<th>积分</th>
						<th>类型/备注</th>
						<th>时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.userId}/${(u.user.realName)!""}/${(u.user.contact)!""}</td>
						<td>${u.score}</td>
						<td>${u.type}/${u.remark}</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/user_score_log/list" />
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
$(document).ready(function(){
	RP.addBreadcrumb([{name:"用户积分日志列表", active: true}]);
});
</script>

