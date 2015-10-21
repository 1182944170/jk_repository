<title>订单列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/order/${pager.searchMap.typeUrlName}/list${suffix}" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<label>订单编号:</label>
	<input type="text" name="orderId" value="${(pager.searchMap.orderId)!''}" size="24" placeholder="订单编号"/>
	<label>会员ID:</label>
	<input type="text" name="userId" value="${(pager.searchMap.userId)!''}" placeholder="会员ID"/>
	<label>商户名称:</label>
	<input type="text" name="goodsShopName" value="${(pager.searchMap.goodsShopName)!''}" placeholder="商户名称"/>
	<label>商品名称:</label>
	<input type="text" name="goodsName" value="${(pager.searchMap.goodsName)!''}" placeholder="商品名称"/>
	<br/>
	<br/>
	<label>时间:</label>
	<input type="text" name="startTime" value="${pager.searchMap.startTime!}" size="30" onfocus="WdatePicker({skin:'blue',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="height:31px !important;"/>&nbsp;到
	<input type="text" name="endTime" value="${pager.searchMap.endTime!}" size="30" onfocus="WdatePicker({skin:'blue',dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="height:31px !important;"/>

	<label>状态:</label>
	<#if type == 4>
		<#assign orderItemProgressOptions = [
				{"value": 0, "valueString":"待付款","labClass":"red","inputClass":"ace"}, 
				{"value": 1, "valueString":"待体验","labClass":"red","inputClass":"ace"}, 
				{"value": 2, "valueString":"待评价","labClass":"red","inputClass":"ace"}, 
				{"value": 3, "valueString":"已经评价","labClass":"red","inputClass":"ace"}
			] />
	<#else>
		<#assign orderItemProgressOptions = [
				{"value": 0, "valueString":"待付款","labClass":"red","inputClass":"ace"}, 
				{"value": 1, "valueString":"待发货","labClass":"red","inputClass":"ace"}, 
				{"value": 2, "valueString":"待确认收获","labClass":"red","inputClass":"ace"}, 
				{"value": 3, "valueString":"待评价","labClass":"red","inputClass":"ace"}, 
				{"value": 4, "valueString":"已经评价","labClass":"red","inputClass":"ace"}
			] />
	</#if>
	
				
	<@ace.formSingleSelect options=orderItemProgressOptions checkValue=(pager.searchMap.currProgress?number)!-1 name="currProgress" listKey="value" listValue="valueString"/>
	
	
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>

<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover" <#if type == 1 || type == 2> style="width:130% !important;max-width:130% !important;" </#if>>
				<thead>
					<tr>
						<th class="center">编号</th>
						<th class="center">会员ID</th>
						<th class="center">商户名称</th>
						<th class="center">商品名称</th>
						<th class="center">单价</th>
						<th class="center">数量</th>
						<th class="center">总金额</th>
						<#if type == 3>
						<th class="center">总积分</th>
						</#if>
						<th class="center red">支付来源</th>
						<th class="center" width="100">交易时间</th>
						<th class="center">状态</th>
						<th class="center" width="200">订单流程</th>
						<#if type == 1 || type == 2>
							<th class="center">一级ID/金额</th>
							<th class="center">二级ID/金额</th>
							<th class="center">原始级ID/金额</th>
						</#if>
						
						<th class="center"></th>
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td class="center">${u.orderId}</td>
						<td class="center">${u.order.userId}</td>
						<td class="center">${u.goods.shopName}</td>
						<td class="center">${u.goods.name}</td>
						<td class="center">${u.unitPrice}</td>
						<td class="center">${u.num}</td>
						<td class="center">${u.num * u.unitPrice}</td>
						<#if type == 3>
						<td class="center">${u.num * u.score}</td>
						</#if>
						
						<td class="center red">${gsonUtils.getDeepValue(u.order.ext, "payChannelValue")}</td>
						<td class="center"> ${tagUtils.formatDate(u.recordCreateTime)}</td>
						<td class="center">
							<#if u.state == 0>
								<span class="label label-sm label-success arrowed">处理中</span>
							<#elseif u.state == 1>
								<span class="label label-sm label-success arrowed">交易成功</span>
							<#else>
								<span class="label label-sm label-warning arrowed">交易失败</span>
							</#if>
						</td>
						
						<td>
							<#if u.goods.type == 4>
								<#include "project_shipments.ftl">
							<#else>
								<#include "common_shipments.ftl">
							</#if>
						</td>
						<#if type == 1 || type == 2>
							<td class="center">
								<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 0>
								${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.oneLevelUserId")!}/${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.oneLevelMoney")!}
								</#if>
							</td>
							<td class="center">
								<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 0>
								${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.twoLevelUserId")!}/${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.twoLevelMoney")!}
								</#if>
							</td>
							<td class="center">
								<#if u.orderItemShipments?has_content && u.orderItemShipments?size gt 0>
								${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.rootLevelUserId")!}/${gsonUtils.getDeepValue(u.orderItemShipments[0].ext, "orderBonus.rootLevelMoney")!}
								</#if>
							</td>
						</#if>
						
						<td class="center">
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<#if u.goods.type == 4>
							<#else>
								<#if u.orderItemShipments?has_content && u.orderItemShipments?size == 1>
									<a class="green" href="${ctx}/admin/order/${u.orderId}/order_item/${u.id}/send${suffix}" alt="发货">
										<i class="icon-pencil bigger-130"></i>发货
									</a>
								</#if>
							</#if>
						</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/order/${pager.searchMap.typeUrlName}/list${suffix}" />
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
	if(f.orderId.value) {
		f.pager.value += "$$orderId--" + f.orderId.value;
	}
	
	if(f.userId.value) {
		f.pager.value += "$$userId--" + f.userId.value;
	}
	
	if(f.goodsShopName.value) {
		f.pager.value += "$$goodsShopName--" + f.goodsShopName.value;
	}
	
	if(f.goodsName.value) {
		f.pager.value += "$$goodsName--" + f.goodsName.value;
	}
	
	if(f.currProgress.value != -1) {
		f.pager.value += "$$currProgress--" + f.currProgress.value;
	}
	
	if(isNotBlank(f.startTime.value) ){
		f.pager.value += '$$startTime--' + f.startTime.value ;
	}
	
	if(isNotBlank(f.endTime.value) ){
		f.pager.value += '$$endTime--' + f.endTime.value;
	}
	
	return true;
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"订单列表", active: true}]);
});
</script>