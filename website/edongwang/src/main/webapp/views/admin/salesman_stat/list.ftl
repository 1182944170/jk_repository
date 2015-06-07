<title>接单统计列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/salesman_stat/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>排序:</label>
	<#assign orderOptions = [
		{"value": "grabCount desc", "valueString":"按照抢单数量"}, 
		{"value": "dealCount desc", "valueString":"按照成交数量"}, 
		{"value": "totalSurface desc", "valueString":"按照成交总面积"}, 
		{"value": "totalDealPrice desc", "valueString":"按照成交总金额"}
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
						<th>序号</th>
						<th>接单人姓名</th>
						<th>联系方式</th>
						<th>所在楼盘</th>
						<th>接单次数</th>
						<th>成交套数</th>
						<th>成交总面积</th>
						<th>成交总金额</th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td>${u.user.realName}</td>
						<td>${u.user.contact}</td>
						<td>${u.house.name}</td>
						<td>${u.grabCount}</td>
						<td>${u.dealCount}</td>
						<td>${u.totalSurface}㎡</td>
						<td>${u.totalDealPrice}元</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/salesman_stat/list" />
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
	RP.addBreadcrumb([{name:"接单统计列表", active: true}]);
});
</script>

