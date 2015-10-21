<title>商品列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/goods/${pager.searchMap.typeUrlName}/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>商户名称:</label>
	<input type="text" name="shopName" value="${(pager.searchMap.shopName)!''}" placeholder="商户名称"/>
	
	<label>商品名称:</label>
	<input type="text" name="name" value="${(pager.searchMap.name)!''}" placeholder="商品名称"/>
	
	<label>商品状态:</label>
	<@ace.formSingleSelect options=ace.commonStateOptions checkValue=(pager.searchMap.state?number)!-1 name="state" listKey="value" listValue="valueString"/>
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>

<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">序号</th>
						<th class="center">商户名称</th>
						<th class="center">商品名称</th>
						<th class="center">单价</th>
						<#if type == 3>
							<th class="center">积分</th>
						</#if>
						<th class="center">总库存</th>
						<th class="center">总销量</th>
						<th class="center">销售总金额</th>
						<#if type == 3>
							<th class="center">积分消耗总计</th>
						</#if>
						<th class="center">创建时间</th>
						<th class="center">开始时间</th>
						<th class="center">结算时间</th>
						<th class="center">状态</th>
						<th class="center"></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center">${u.id}</td>
						<td class="center">${u.shopName}</td>
						<td class="center">${u.name!}</td>
						<td class="center">${u.discountMoney}</td>
						<#if type == 3>
						<td class="center">${u.score}</td>
						</#if>
						<td class="center">${u.realStockCount}</td>
						<td class="center">${u.salesNumber}</td>
						<td class="center">${u.totalMoney}</td>
						<#if type == 3>
							<th class="center">${u.totalScore}</th>
						</#if>
						<td class="center">${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td class="center">${tagUtils.formatDate(u.startTime, "yyyy-MM-dd HH:mm")}</td>
						<td class="center">${tagUtils.formatDate(u.endTime, "yyyy-MM-dd HH:mm")}</td>
						
						<td class="center">
						<#if u.state == 0>
							<span class="label label-sm label-warning arrowed">禁用状态</span>
						<#elseif u.state == 1>
							<span class="label label-sm label-success arrowed">正常状态</span>
						<#else>
						</#if>
						</td>
						<td class="center">
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/goods/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>编辑
							</a>
							<a class="green" href="${ctx}/admin/goods/${u.id}/edit_spec${suffix}" alt="入库">
								<i class="icon-pencil bigger-130"></i>入库
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="${ctx}/admin/goods/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
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
		<@h.page pager=pager action="${ctx}/admin/goods/${pager.searchMap.typeUrlName}/list${suffix}" />
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
	if(f.shopName.value) {
		f.pager.value += "$$shopName--" + f.shopName.value;
	}
	if(f.name.value) {
		f.pager.value += "$$name--" + f.name.value;
	}
	
	if(f.state.value) {
		f.pager.value += "$$state--" + f.state.value;
	}
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"商品列表", active: true}]);
	<#if type == 1>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/goods/add?type=1"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增普通商品</span></a>');
	<#elseif type == 2>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/goods/add?type=2"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增定制商品</span></a>');
	<#elseif type == 3>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/goods/add?type=3"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增积分商品</span></a>');
	<#elseif type == 4>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/goods/add?type=4"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增项目</span></a>');
	</#if>
});
</script>