<title>商品入库日志</title>
<!--
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/goods/goods_spec_detail_log/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<!--<label>会员ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="会员ID"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
-->
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">ID</th>
						<th class="center">商品名称</th>
						<th class="center">商品规格</th>
						<th class="center">入库数量</th>
						<th class="center">入库编码</th>
						<th class="center">后台操作ID</th>
						<th class="center">创建时间</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center"><span class="green center">${u.id}</span></td>
						<td class="center">${u.goods.name}</td>
						<td class="center">${u.goodsSpecDetail.levelKey}</td>
						<td class="center">${u.virtualStockCount}</td>
						<td class="center">${u.logisticsNo}</td>
						<td class="center">${u.operAdminUserId}</td>
						<td class="center">${tagUtils.formatDate(u.recordCreateTime)}</td>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/goods_spec_detail_log/list${suffix}" />
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
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"商品入库日志", active: true}]);
});
</script>