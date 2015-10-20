<title>产品日志列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce_log/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	
	<label>会员手机号:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="会员手机号"/>
	
	<label>会员姓名:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="会员姓名"/>
	
	<label>商品ID:</label>
	<input type="text" name="produceId" value="${(pager.searchMap.produceId)!''}" placeholder="商品ID"/>
	
	<label>订单编号:</label>
	<input type="text" name="orderNo" value="${(pager.searchMap.orderNo)!''}" placeholder="订单编号"/>
	
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
						<th>会员ID/会员手机号</th>
						<th>商品ID/订单编号</th>
						<th class="center">详 情</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.user.id} / ${u.user.realName} / ${u.user.contact}</td>
						<td>${u.produce.id} / ${u.detail.orderNo}</td>
						<td>用户${u.user.realName}在(${tagUtils.formatDate(u.recordCreateTime)})
							购买了<${u.produce.name}>,
								<#if u.detail.order.state == 0>
									未支付
								<#elseif (u.detail.order.state != -1 && u.detail.order.state != -2 && u.detail.order.state != 0)>
									<span class="label label-sm label-success arrowed">已支付</span>
								</#if>
						</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/user/${u.user.id}/detail${suffix}" alt="查看会员信息">
									<i class="icon-pencil bigger-130"></i> 查看会员信息
								</a>
							</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/produce_log/list" />
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
	if(f.produceId.value) {
		f.pager.value += "$$produceId--" + f.produceId.value;
	}
	if(f.orderNo.value) {
		f.pager.value += "$$orderNo--" + f.orderNo.value;
	}
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"用户积分日志列表", active: true}]);
});
</script>

