<title>活动管理</title>
<script>
$(function (){
	var num = 0;
	<#list pager.itemList as u>
		$(".ff").each(function(i){
			if(i==${u_index}){
				fmt =parseFloat("${pager.itemList[u_index].monely}");
				num +=fmt;
				return;
			}
		})
	</#list>
	$("#Allmanny").html(num.toFixed(2));
})
</script>

</h5>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th>报名者</th>
						<th>电话</th>
						<th>紧急联系人</th>
						<th>紧急电话</th>
						<th>小孩</th>
						<th>成人</th>
						<th>支付金额</th>
						<th>实际费用</th>
						<th>手续费</th>
						
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr class="ff">
						
						<td><span class="gray center">${u.name!}</span></td>
						<td><span class="gray center">${u.phone!}</span></td>
				        <td><span class="gray center">${u.emergencyname!}</span></td>
				        <td><span class="gray center">${u.emergencyphone!}</span></td>
				        <td><span class="gray center">${u.oldboy!}</span></td>
				        <td><span class="gray center">${u.chindenboy!}</span></td>
				        <td>￥<span class="gray center wore">${u.monely!}</span></td>
				          <td>￥<span class="gray center" >${u.actualamount!}</span></td>
				          <td>￥<span class="gray center" >${u.counterFee!}</span></td>
					</tr>
				</#list>
				<tr>
					<th colspan="10" >活动总金额：￥<span id="Allmanny"class="gray center red"></span></th>
				</tr>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/good/list" />
		
	</div><!-- /span -->
</div><!-- /row -->

