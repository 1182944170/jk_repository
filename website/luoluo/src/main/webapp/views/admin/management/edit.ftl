<title>订单管理</title>
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
<!--startprint1-->
<link  type="text/css" href="${ctx}/resources/css/css.css" rel=stylesheet>
		<div class="bdbg">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"  class="cont_ab" >
                 <tr >
                    <td width="4" valign="top" style="height:25px;"></td>
                    <td  style="height:30px;" id="mb">
                                                                            订单详细信息
                    </td>
                  </tr>
            </table>
            <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03" >
                <tr>
                   <th colspan="4">
                      <div align="left" ><div class="title_pic"></div>
                      <font class="text2" >订单信息</div>
                   </th>
               </tr>
				<tr>
				   <td width="9%" class="right3">订单编号：</td>
				   <td width="41%">${acp.ordernumber}</td>
				   <td width="9%" class="right3">用户名：</td>
				    <@showprive cmd="get_showprive_list" type="${acp.myld}" pagerString="1_" pageSize="50">
						<#list m_pager.itemList as zz>
				   				<td width="41%">${zz.name}</td>
						</#list>
 					</@showprive>
				</tr>
				<tr>
				<td class="right3">实际金额：</td>
				 	<td>￥${acp.actualamount}</td>
			
					
                    <td width="9%" class="right3">订单状态：</td>
                    <#if acp.typeOrder==1>
                     <td width="41%" >提交成功</td>  
                     <#elseif acp.typeOrder==2>
                      <td width="41%" >交易成功</td>  
                     </#if>
				</tr>
				<tr>
				 <td width="9%" class="right3">手续费：</td>
				   <td width="41%">￥${acp.counterFee}</td>
				   <td width="9%" class="right3">订单提交时间：</td>
				   <td width="41%">${tagUtils.formatDate(acp.newtime)}</td>
				 </tr>
			
				<tr>
				 <td width="9%" class="right3">总金额：</td>
                    <td width="41%" >￥${acp.monely}</td>  
				 </tr>
             </table>
               <br>
             <table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03">
            	<tr>
                    <th colspan="16">
                        <div align="left"><div class="title_pic"></div>
                        <font class="text2">活动详情</font></div>
                    </th>
                </tr>
               
            	<tr>
            		<th width="10%">活动名称</th>
            		<th width="10%">开始时间</th>
            		<th width="10%">结束时间</th>
            		<th width="10%">成人数量</th>
            		<th width="10%">儿童数量</th>
            	
            	</tr>
          
	            <tr>
	            <@miactivit cmd="get_miactivit_list" type="${acp.sponsorld}" pagerString="1_" pageSize="50">
						<#list s_pager.itemList as lo>
				        <td style="text-align:center">${lo.activityname}</td>
				        <td style="text-align:center">${tagUtils.formatDate(lo.starttime)}</td>
				        <td style="text-align:center">${tagUtils.formatDate(lo.outtime)}</td>
				        <td style="text-align:center">${lo.old_expense}</td>
				        <td style="text-align:center">${lo.children_expense}</td>
				        
						</#list>
 					</@miactivit>
	            </tr>
	       
            </table>
            <br>
             <table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03">
            	<tr>
                    <th colspan="16">
                        <div align="left"><div class="title_pic"></div>
                        <font class="text2">支付详情</font></div>
                    </th>
                </tr>
            	<tr>
            		<th width="10%">支付金额</th>
            		<th width="10%">支付时间</th>
            		<th width="10%">支付来源</th>
            	</tr>
	            <tr>
			        <td style="text-align:center">￥${acp.monely}</td>
			        <td style="text-align:center">${tagUtils.formatDate(acp.newtime)}</td>
			        
			        <#if acp.typeMonely==1>
			        <td style="text-align:center">支付宝</td>
			        <#elseif acp.typeMonely==2>
			        <td style="text-align:center">余额宝</td>
			        <#elseif acp.typeMonely==3>
			        <td style="text-align:center">微信</td>
			        </#if>
	            </tr>
            </table>
            <br>
             <table  width="99%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03">
            	<tr>
                    <th colspan="16">
                        <div align="left"><div class="title_pic"></div>
                        <font class="text2">保单详情</font></div>
                    </th>
                </tr>
  
            	<tr>
            		<td>
	                   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03">
	                   		<tr><th>保险姓名</th></tr>
	                   		<#list name as o>
		                   		<tr>
		                   			<td align="center">${o}</td>
		                   		</tr>
	                   		</#list>
	                   </table>
                   </td>
            		<td>
	                   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="list_t_03">
	                   		<tr><th>保险证件</th></tr>
	                   		<#list num as z>
	                   			<tr>
	                   				<td align="center">${z}</td>
	                   			</tr>
	                   		</#list>
	                   </table>
                   </td>
                </tr>
            
            </table>
          
	       <table border="0" cellspacing="0" cellpadding="0"  class="back_menu" align="center" >
	        <tr> 
	        	<td>
		         	<input type=button name='button_export' title='打印1' onclick=preview(1) value=打印页面>&nbsp;&nbsp;
		           <input id="btn2"  type="button" onclick="returnlist();" value="返  回"/>
		         </td>
	        </tr>
	      </table>
     </div>
  <!--endprint1-->   

<script language="javascript">
	function returnlist(){
		window.location.href="${ctx}/admin/actcyitypic/list/";
	}
	
	function  savaexpre(){
     var orderId=$("#oId").val();
	 var url = "${ctx}/admin/order/expre/"+orderId;
    	$("#form1").ajaxSubmit({
			type: "post",
			url: url,
			dataType: "json",
			success: function(data){
        window.location.href="${ctx}/admin/order/orderDetail/"+orderId;
		}
	  });
	}
</script>