<title>提现列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_take_cash/list" onsubmit="return fromSearch(this)">
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
						<th>提现金额</th>
						<th>银行/开户行</th>
						<th>备注</th>
						<th>状态</th>
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
						<td>${u.name}-${u.account}
						[
						${u.address}-${u.cfgBank.name}
						]
						
						</td>
						<td>${u.remark}</td>
						<td>
						<#if u.state == 0>
							<span class="label label-sm label-warning arrowed">未处理状态</span>
						<#elseif u.state == 1>
							<span class="label label-sm label-success arrowed">处理成功</span>
						<#else>
							<span class="label label-sm label-warning arrowed">处理失败</span>
						</#if>
						</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/user_take_cash/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/user_take_cash/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
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
		<@h.page pager=pager action="${ctx}/admin/user_take_cash/list" />
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
	RP.addBreadcrumb([{name:"用户提现列表", active: true}]);
});
</script>

