<title>订单列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/order/detail${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="orderId" value="${(pager.searchMap.orderId)!''}"/>
	<label>订单编号:</label>
	<input type="text" name="orderNo" value="${(pager.searchMap.orderNo)!''}" placeholder="订单编号"/>

	<label>物流单号:</label>
	<input type="text" name="flowNo" value="${(pager.searchMap.flowNo)!''}" placeholder="物流单号"/>
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
						<th>订单编号</th>
						<th>商品名/类型</th>
						<th>数量 (件)/支付价格 (元)</th>
						<th>用户ID/用户姓名/手机号</th>
						<th>物流公司/物流单号</th>
						<th>发货时间</th>
						<th>状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.orderNo}</span></td>
						<td>${tagUtils.cutString(u.order.produce.name, 15)}/${dicSetting.getParameterValue("produce.type." + u.order.produce.type)}商品</td>
						<td>${u.order.totalCount} / ${u.order.totalPrice}</td>
						<td>${u.order.user.id}/${u.order.user.realName}/${u.order.user.contact}</td>
						<td>${u.flowFirm}/${u.flowNo}</td>
						<td>${tagUtils.formatDate(u.loadingTime)}</td>
						<td>
							${dicSetting.getParameterValue("order.state." + u.order.state)}
						</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/order/${u.order.id}/edit${suffix}" alt="查看订单信息">
									<i class="icon-pencil bigger-130"></i> 查看订单信息
								</a>
							</div>

							<div class="visible-xs visible-sm hidden-md hidden-lg">
								<div class="inline position-relative">
									<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
										<i class="icon-caret-down icon-only bigger-120"></i>
									</button>
									<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
										<li>
											<a href="${ctx}/admin/order/${u.order.id}/edit${suffix}" class="tooltip-error" data-rel="tooltip" title="" data-original-title="查看订单信息">
												<span class="red">
													<i class="icon-pencil bigger-130"></i> 查看订单信息
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
		<@h.page pager=pager action="${ctx}/admin/order/detail" />
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
	// 搜索
	function fromSearch(f){
		if(f.orderNo.value) {
			f.pager.value += "$$orderNo--" + f.orderNo.value;
		}
		
		if(f.flowNo.value) {
			f.pager.value += "$$flowNo--" + f.flowNo.value;
		}
		return true;
	}
	
	$(document).ready(function(){
		RP.addBreadcrumb([ {name:"订单列表", active: true}]);
	});
</script>