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
<script type="text/javascript">
function preview(oper)
{
if (oper < 10){
bdhtml=window.document.body.innerHTML;	//获取当前页的html代码
sprnstr="<!--startprint"+oper+"-->";	//设置打印开始区域
eprnstr="<!--endprint"+oper+"-->";	//设置打印结束区域
prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
window.document.body.innerHTML=prnhtml;
window.print();
window.document.body.innerHTML=bdhtml;
} else {
window.print();
}
}

function xx(){
window.print();
}
</script>
</h5>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<!--startprint1-->
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
					
						<th>报名者</th>
						<th>电话</th>
						<th>紧急联系人</th>
						<th>紧急电话</th>
						<th>小孩</th>
						<th>成人</th>
						<th>女孩</th>
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
				        <td><span class="gray center">${u.grilexpense!}</span></td>
				        <td>￥<span class="gray center wore">${u.monely!}</span></td>
				          <td>￥<span class="gray center" >${u.actualamount!}</span></td>
				          <td>￥<span class="gray center" >${u.counterFee!}</span></td>
					</tr>
				</#list>
				<tr >
						 <td  colspan="6"><span class="gray center" ></span></td>
				       	  <th >总支付金额：￥<span id="Allmanny"class="gray center red"></span></th>
				          <th>总实际金额：￥<span id="Allactualamount"class="gray center red"></span></th>
				          <th>总手续：￥<span id="AllcounterFee"class="gray center red"></span></th>
				         
					</tr>
				</tbody>
			</table>
			<!--endprint1-->
			<div align="right" >
			 <input type=button name='button_export' title='打印1' onclick=preview(1) value=打印>&nbsp;&nbsp;
			<INPUT  type=button value=返回 onclick="javascript:history.back(-1);" />
			</div>
		</div>
		<!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		
		
	</div><!-- /span -->
</div><!-- /row -->

