<title>推荐统计列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/recommend_stat/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>排序:</label>
	<#assign orderOptions = [
		{"value": "totalCount desc", "valueString":"推荐次数"}, 
		{"value": "invalidCount desc", "valueString":"无效次数"}, 
		{"value": "effectiveCount desc", "valueString":"有效次数"}
	]/>
	<@ace.formSingleSelect options=orderOptions checkValue=(pager.searchMap.order)!'' name="order" listKey="value" listValue="valueString"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>接单人姓名</th>
						<th>联系方式</th>
						<th>推荐次数</th>
						<th>A/B/C/D</th>
						<th>无效次数</th>
						<th>有效次数</th>
						<th>成交套数</th>
						<th>成交总面积</th>
						<th>成交总金额</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td>${u.user.realName}</td>
						<td>${u.user.contact}</td>
						<td>${u.totalCount}</td>
						<td>${u.aCount}/${u.bCount}/${u.cCount}/${u.dCount}</td>
						<td>${u.invalidCount}</td>
						<td>${u.effectiveCount}</td>
						<td>${u.dealHouseCount}</td>
						<td>${u.totalSurface}㎡</td>
						<td>${u.totalDealPrice}元</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/recommend_stat/list" />
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
	if(f.order.value) {
		f.pager.value += "$$order--" + f.order.value;
	}
	
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"推荐统计列表", active: true}]);
});
</script>

