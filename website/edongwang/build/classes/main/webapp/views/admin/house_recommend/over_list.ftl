<title>结佣列表列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/house_recommend/over_list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	
	<label>抢单人:</label>
	<input type="text" name="acceptSalesmanName" value="${(pager.searchMap.acceptSalesmanName)!''}" placeholder="接单人"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<table>
	<tr>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/over_list${suffix}" class="label label-sm label-success arrowed"> 查看所有 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/over_list${suffix}?state=2" class="label label-sm label-warning arrowed"> 处理中 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/over_list${suffix}?state=1" class="label label-sm label-warning arrowed"> 未处理 </a> </th>
		<th width="100px"> <a href="${ctx}/admin/house_recommend/over_list${suffix}?state=8" class="label label-sm label-success arrowed"> 完结 </a> </th>
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
						<th>成交时间</th>
						<th>推荐人信息</th>
						<th>成交客户</th>
						<th>抢单人</th>
						<th>成交楼盘</th>
						<th>佣金</th>
						<th>成交奖励</th>
						<th>状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${tagUtils.formatDate(gsonUtils.getLong(u.progresses[2].extJson, "dealTime") )}</td>
						<td> ${u.recommendUser.realName!} </br>${u.recommendUser.contact} </br></td>
						<td> ${u.customerName} <br/>  ${u.contact}
						</td>
						<td> ${u.acceptSalesman.realName!} ${u.acceptSalesman.contact} </br>
						<#if userBankCards[u_index]??>
							银行名称：${userBankCards[u_index].cfgBank.name} 
							账户名：${userBankCards[u_index].name} 
							账号：${userBankCards[u_index].account}  </br>
							开户行：${userBankCards[u_index].address}
						</#if>
						</td>
						<td> ${u.house.name}</td>
						<td> ${u.house.commissionString}</td>
						<td> ${u.house.bargainPrice} 元</td>
						
						<td><#if u.state==1>
							<span class="label label-sm label-warning arrowed">该推荐未被接单</span>
						<#elseif u.state==2>
							<span class="label label-sm label-warning arrowed">处理中</span>
						<#else>
							<span class="label label-sm label-success arrowed">完结状态</span>
						</#if></td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<#if u.state==2 && u.progresses?has_content && u.progresses?size == 3>
								<a class="green" href="${ctx}/admin/house_recommend/${u.id}/over${suffix}" onclick="if(!confirm('确认完结么?')){return false;}" alt="完结">
									<i class="icon-pencil bigger-130"></i> 完结
								</a>
							</#if>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
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
		<@h.page pager=pager action="${ctx}/admin/house_recommend/over_list${suffix}" />
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
	if(f.acceptSalesmanName.value) {
		f.pager.value += "$$acceptSalesmanName--" + f.acceptSalesmanName.value;
	}
	
	return true;
}
$(document).ready(function(){ 
	RP.addBreadcrumb([{name:"结佣列表管理"}, {name:"结佣列表列表", active: true}]);
});
</script>

