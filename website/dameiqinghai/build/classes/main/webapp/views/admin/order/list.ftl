<title>申请列表</title>
<script>
	function doSearch(){
		alert("查询");
	}
	$(document).ready(function() {
		RP.addBreadcrumb([{name:"列表"}]);
	});
</script>
<style>
.label-common{display: inline-block;}
.label-redtype{background-color:#FF6347}.label-redtype[href]:hover,.label-redtype[href]:focus{background-color:#FF6347}
 .label-yellowtype{background-color:#FFFF00}.label-yellowtype[href]:hover,.label-yellowtype[href]:focus{background-color:#FFFF00}
 .label-bluetype{background-color:#1E90FF}.label-bluetype[href]:hover,.label-bluetype[href]:focus{background-color:#00BFFF}
 .label-darktype{background-color:#A9A9A9}.label-darktype[href]:hover,.label-darktype[href]:focus{background-color:#C0C0C0}
</style>
<table>
	<tr>
	<th>
	模糊查询 : 
	<input type="text" class="width:180px;height:20px;"/><span><img src="${ctx}/resources/images/search.gif" onclick="doSearch();"></span>	
	</th>
	</tr>
</table><p></p>

<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>订单编号</th>
						<th>订单类型</th>
						<th>支付状态</th>
						<th>商品ID</th>
						<th>订购数量</th>
						<th>用户ID</th>
						<th>总星级</th>
						<th>评价内容</th>
						<th>评价次数</th>
						<th>下单时间</th>
						<th>快递类型</th>
						<th>快递单号</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				 
				<#list orderPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.orderpageNo!}</span></td>
						<td>${dicSetting.getParameterValue("order.orderpageType."+u.orderpageType)!}</td>
						<td>
							<#if u.type == 1>
								<span class="label label-warning label-sm arrowed">${dicSetting.getParameterValue("order.type."+u.type)!}</span>
							<#elseif u.type == 2>
								<span class="label-common label-sm label-bluetype arrowed">${dicSetting.getParameterValue("order.type."+u.type)!}</span>
							<#elseif u.type == 3>
								<span class="label-common label-sm label-yellowtype arrowed">${dicSetting.getParameterValue("order.type."+u.type)!}</span>
							<#else>
								<span class="label-common label-sm label-darktype arrowed">${dicSetting.getParameterValue("order.type."+u.type)!}</span>
							</#if>
						</td>
						<td><span class="green center">${u.supplierId!}</span></td>
						<td><span class="green center">${u.commentTotal!}</span></td>
						<td><span class="green center">${u.userId!}</span></td>
						<td><span class="green center">${u.total!}</span></td>
						<td><span class="green center">${u.content!}</span></td>
						<td><span class="green center">${orderPager.totalCount!}</span></td>
						<td><span class="green center">${tagUtils.formatDate(u.createTime)!}</span></td>
						<td><span class="green center">${u.expressType!}</span></td>
						<td><span class="green center">${u.expressNo!}</span></td>
						<td><span class="green center">${u.memo!}</span></td>
					</tr>
				</#list>
				
				<div class="hr hr-18 dotted hr-double"></div>
				
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		
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


