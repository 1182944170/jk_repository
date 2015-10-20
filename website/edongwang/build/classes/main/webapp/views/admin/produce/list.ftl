<title>产品商品列表</title>
<#if type == 1>
	<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce/list" onsubmit="return fromSearch(this)">
<#elseif type == 2>
	<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce/dollar_produce_list" onsubmit="return fromSearch(this)">
<#elseif type ==3>
	<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce/second_produce_list" onsubmit="return fromSearch(this)">
</#if>	

	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="type" value="${type}"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>商品名称:</label>
	<input type="text" name="name" value="${(pager.searchMap.name)!''}" placeholder="商品名称"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<#if type ==3>
<br/>
<table>
	<tr>
		<th width="72px;" class="center"><a href="${ctx}/admin/produce/second_produce_list${suffix}">所有活动</a></th>
		<th width="72px;" class="center"><a href="${ctx}/admin/produce/second_produce_list${suffix}?state=3">未开始</a></th>
		<th width="72px;" class="center"><a href="${ctx}/admin/produce/second_produce_list${suffix}?state=4">进行中</a></th>
		<th width="72px;" class="center"><a href="${ctx}/admin/produce/second_produce_list${suffix}?state=5">已结束</a></th>
	</tr>
</table>
</#if>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">ID</th>
						<th class="center">商品名称</th>
						<th class="center">真实库存 / 虚假库存 / 已销售 (件)</th>
						<th class="center">原价 / 现价 / 运费 (元)</th>
						<#if type == 3>
							<th class="center">开始秒杀时间</th>
							<th class="center">结束秒杀时间</th>
							<th class="center">活动状态</th>
						</#if>
						<#if type != 3>
							<th class="center">状态</th>
						</#if>
						<th class="center">创建时间</th>
						<th class="center"></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center"><span class="green center">${u.id}</span></td>
						<td>${u.name}</td>
						<td >${u.realStock} / ${u.fakeStock}/ ${u.saleNumber} </td>
						<td >${u.oldPrice}/ ${u.nowPrice} / <#if u.cartage == 0> <span class="label label-sm label-success arrowed">免运费</span> <#else> ${u.cartage} </#if></td>
						<#if type == 3>
							<td>${tagUtils.formatDate(u.beginSecondTime)}</td>
							<td>${tagUtils.formatDate(u.endSecondTime)}</td>
							<td> 
								<#if (currTime > u.endSecondTime)>
									<span class="label label-sm label-warning arrowed">已结束</span>
								<#elseif (currTime > u.beginSecondTime) && (currTime < u.endSecondTime)>
									<span class="label label-sm label-success arrowed">进行中</span>
								<#else>
									<span class="label label-sm label-warning arrowed">未开始</span>
								</#if>
							</td>
						</#if>
						<#if type != 3>
							<td class="center">
								<#if u.state == 0>
									<span class="label label-sm label-warning arrowed">禁用状态</span>
								<#elseif u.state == 1>
									<span class="label label-sm label-success arrowed">正常状态</span>
								<#else>
								</#if>
							</td>
						</#if>
						<td >${tagUtils.formatDate(u.createTime)}</td>
						<td class="center">
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="${ctx}/admin/produce/${u.id}/edit${suffix}" alt="编辑">
								<i class="icon-pencil bigger-130"></i>编辑
							</a>
							
							<#if u.type == 1>
								<a class="green" href="${ctx}/admin/produce_log/list${suffix}?pager=1_produceId--${u.id}" alt="查看日志">
									<i class="icon-user bigger-130"></i>查看日志
								</a>
							<#elseif u.type == 2>
								<a class="green" href="${ctx}/admin/produce_log/second_produce_log${suffix}?pager=1_produceId--${u.id}" alt="查看日志">
									<i class="icon-user bigger-130"></i>查看日志
								</a>
							<#elseif u.type == 3>
								<a class="green" href="${ctx}/admin/produce_log/dollar_produce_log${suffix}?pager=1_produceId--${u.id}" alt="查看日志">
									<i class="icon-user bigger-130"></i>查看日志
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
										<a href="${ctx}/admin/produce/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
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
		<@h.page pager=pager action="${ctx}/admin/produce/list" />
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
	if(f.name.value) {
		f.pager.value += "$$name--" + f.name.value;
	}
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"产品商品列表", active: true}]);
	<#if type == 1>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/produce/add${suffix}?type=1"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增普通商品</span></a>');
	<#elseif type == 2>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/produce/add${suffix}?type=2"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增一元商品</span></a>');
	<#elseif type ==3>
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/produce/add${suffix}?type=3"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增秒杀商品</span></a>');
	</#if>
});
</script>

