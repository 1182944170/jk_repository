<title>用户金额列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_money/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>用户姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="用户姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>用户ID/用户姓名</th>
						<th>金额</th>
						<th>冻结金额</th>
						<th>总使用金额</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.userId}/${u.user.realName}</span></td>
						<td>${u.money}</td>
						<td>${u.freezeMoney}</td>
						<td>${u.usedMoney}</td>

						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user_money/${u.userId}/transfer${suffix}" alt="转入金额">
								<i class="icon-pencil bigger-130"></i>转入金额
							</a>
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
		<@h.page pager=pager action="${ctx}/admin/user_money/list" />
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
	if(f.realName.value) {
		f.pager.value += "$$realName--" + f.realName.value;
	}
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"用户金额列表", active: true}]);
});
</script>

