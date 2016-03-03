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
<script>
$(function (){
	var num = 0;
	<#list pager.itemList as u>
		$(".ff").each(function(i){
			if(i==${u_index}){
				fmt =parseFloat("${pager.itemList[u_index].actualamount}");
				num +=fmt;
				return;
			}
		})
	</#list>
	$("#Allactualamount").html(num.toFixed(2));
})
</script>
<script>
$(function (){
	var num = 0;
	<#list pager.itemList as u>
		$(".ff").each(function(i){
			if(i==${u_index}){
				fmt =parseFloat("${pager.itemList[u_index].counterFee}");
				num +=fmt;
				return;
			}
		})
	</#list>
	$("#AllcounterFee").html(num.toFixed(2));
})
</script>


</h5>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th>订单号</th>
						<th>活动名称</th>
						<th>支付金额</th>
						<th>实际费用</th>
						<th>手续费</th>
						<th>订单状态</th>
						<th>订单时间</th>
						<th></th>
						
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr class="ff">
						<td><span class="gray center">${u.ordernumber!}</span></td>
	
					<@miactivit cmd="get_miactivit_list" type="${u.sponsorld}" pagerString="1_" pageSize="50">
						<#if (s_pager.itemList?size > 0)>
							<#list s_pager.itemList as lo>
								<td><span class="gray center">${lo.activityname}</span></td>
							</#list>
						<#else >
							<td><span class="gray center"> </span></td>
						</#if >
 					</@miactivit>
 					
				        <td>￥<span class="gray center wore">${u.monely!}</span></td>
				          <td>￥<span class="gray center" >${u.actualamount!}</span></td>
				          <td>￥<span class="gray center" >${u.counterFee!}</span></td>
				          <#if u.typeOrder==1>
				          <td><span class="gray center" >提交订单</span></td>
				          <#elseif u.typeOrder==2>
				          <td><span class="gray center" >交易成功</span></td>
				          </#if>
				          <td><span class="gray center" >${tagUtils.formatDate(u.newtime)!}</span></td>
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/actcyitypic/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130">查看订单详情</i>
								</a>
	
							</div>
	
							<div class="visible-xs visible-sm hidden-md hidden-lg">
								<div class="inline position-relative">
									<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
										<i class="icon-caret-down icon-only bigger-120"></i>
									</button>
									<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
										<li>
											<a href="${ctx}/admin/actcyitypic/${u.id}/edit${suffix}" class="tooltip-success" data-rel="tooltip" title="编辑" data-original-title="Edit">
												<span class="green">
													<i class="icon-edit bigger-120"></i>
												</span>
											</a>
										</li>
										<li>
											<a href="${ctx}/admin/actcy/${u.id}/deletUser${suffix}" onclick="return confirm('你确定删除么?');" class="tooltip-error" data-rel="tooltip" title="删除" data-original-title="Delete">
												<span class="red">
													<i class="icon-trash bigger-120"></i>
												</span>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</td>
					
					</tr>
				</#list>
					<tr>
						 <td  colspan="2"><span class="gray center" ></span></td>
				       	  <th >总支付金额：￥<span id="Allmanny"class="gray center red"></span></th>
				          <th>总实际金额：￥<span id="Allactualamount"class="gray center red"></span></th>
				          <th>总手续：￥<span id="AllcounterFee"class="gray center red"></span></th>
				          <td  colspan="3"><span class="gray center" ></span></td>
					</tr>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/actcyitypic/list" />
		
	</div><!-- /span -->
</div><!-- /row -->

