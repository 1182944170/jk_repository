<title>用户金额日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_money_log/list" onsubmit="return fromSearch(this)">
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
						<th>Id</th>
						<th>会员ID/会员姓名/会员手机号</th>
						<th>金额</th>
						<th>总使用金额</th>
						<th>当前余额</th>
						<th>类型/备注</th>
						<th>说明</th>
						<th>时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.userId}/${u.user.realName}/${u.user.contact}</td>
						<td>${u.money}</td>
						<td>${u.usedMoney}</td>
						<td>${u.currMoney}</td>
						<td>${u.type}/${u.remark}</td>
						<td>${(u.getExtJsonValue('remark'))!""}</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>

						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
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
		<@h.page pager=pager action="${ctx}/admin/user_money_log/list" />
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
	RP.addBreadcrumb([{name:"用户金额日志列表", active: true}]);
});
</script>

