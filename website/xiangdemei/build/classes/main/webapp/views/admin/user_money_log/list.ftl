<title>用户金额日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_money_log/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>会员ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="会员ID"/>
	
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
	<label>会员姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="会员姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row" width="150%">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover" style="width:130% !important;max-width:130% !important;">
				<thead>
					<tr>
						<th>序号</th>
						<th width="150">时间</th>
						<th>会员ID</th>
						<th width="120">会员姓名</th>
						<th>会员手机号</th>
						<th width="80">金额</th>
						<th width="80">总提现</th>
						<th width="80">当前余额</th>
						<th width="100">类型</th>
						<th width="80">来源会员ID</th>
						<th width="120">来源会员姓名</th>
						<th width="80">来源会员手机号</th>
						<th width="200">商品名称</th>
						<th width="80">购买价格</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td>${u.id}</td>
						<td>${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td>${u.userId}</td>
						<td>${u.user.realName}</td>
						<td>${u.user.contact}</td>
						<td>${u.money}</td>
						<td>${u.usedMoney}</td>
						<td>${u.currMoney}</td>
						<td>${u.remark}</td>
						
						<td>
						<#if u.type == 1 || u.type == 2 || u.type == 3>
							${gsonUtils.getDeepValue(u.ext,"buyUser.userId")!}
						<#elseif u.type==7>
							${gsonUtils.getDeepValue(u.ext,"recommendUserId")!}
						</#if>
						</td>
						<td>
						<#if u.type == 1 || u.type == 2 || u.type == 3>
							${gsonUtils.getDeepValue(u.ext,"buyUser.realName")!}
						<#elseif u.type==7>
							${gsonUtils.getDeepValue(u.ext,"recommendRealName")!}
						</#if>
						</td>
						<td>
						<#if u.type == 1 || u.type == 2 || u.type == 3>
							${gsonUtils.getDeepValue(u.ext,"buyUser.contact")!}
						<#elseif u.type==7>
							${gsonUtils.getDeepValue(u.ext,"recommendContact")!}
						</#if>
						</td>
						<td>
						<#if u.type == 1 || u.type == 2 || u.type == 3>
							${gsonUtils.getDeepValue(u.ext,"goodsName")!}
						</#if>
						</td>
						<td>
						<#if u.type == 1 || u.type == 2 || u.type == 3>
							${gsonUtils.getDeepValue(u.ext,"unitPrice")!} * ${gsonUtils.getDeepValue(u.ext,"num")!}
						</#if>
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
	if(f.userId.value) {
		f.pager.value += "$$userId--" + f.userId.value;
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

