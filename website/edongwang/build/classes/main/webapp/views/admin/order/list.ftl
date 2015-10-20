<title>订单列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/order/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>用户名称:</label>
	<input type="text" name="realName" value="${(pager.searchMap.realName)!''}" placeholder="用户名称"/>
	
	<label>联系电话:</label>
	<input type="text" name="contact" value="${(pager.searchMap.contact)!''}" placeholder="联系电话"/>
	
	<label>商品ID:</label>
	<input type="text" name="produceId" value="${(pager.searchMap.produceId)!''}" placeholder="商品ID"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form><br/>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>商品ID/商品名/商品类型</th>
						<th>用户ID/用户姓名/手机号</th>
						<th>状态</th>
						<th>时间</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.order.produce.id}/${tagUtils.cutString(u.order.produce.name, 15)}/${dicSetting.getParameterValue("produce.type." + u.order.produce.type)}商品</td>
						<td>${u.order.user.id}/${u.order.user.realName}/${u.order.user.contact}</td>
						<td class="hidden-480">${dicSetting.getParameterValue("order.state." + u.order.state)}</td>
						<td>${tagUtils.formatDate(u.order.recordCreateTime)}</td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/order/${u.order.id}/edit${suffix}" alt="发货处理">
									<i class="icon-pencil bigger-130">发货处理</i>
								</a>
								<a class="green" href="${ctx}/admin/user/${u.order.user.id}/detail${suffix}" alt="查看会员信息">
									<i class="icon-pencil bigger-130"></i> 查看会员信息
								</a>
								<a class="green" href="${ctx}/admin/order/detail${suffix}?orderId=${u.order.id}" alt="查看订单信息">
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
											<a href="${ctx}/admin/order/${u.order.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="发货处理">
												<span class="green">
													<i class="icon-edit bigger-120"></i>发货处理
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/user/${u.order.user.id}/detail${suffix}" class="tooltip-error" data-rel="tooltip" title="" data-original-title="查看会员信息">
												<span class="red">
													<i class="icon-trash bigger-120"></i> 查看会员信息
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/order/detail${suffix}?orderId=${u.order.id}" class="tooltip-error" data-rel="tooltip" title="" data-original-title="查看订单信息">
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
		<@h.page pager=pager action="${ctx}/admin/order/list" />
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
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([ {name:"订单列表", active: true}]);
});
</script>