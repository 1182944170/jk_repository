<title><#if orderDetail??>编辑<#else>新增</#if>订单详情</title>
<div class="page-header">
	<h1>
		<#if orderDetail??>编辑<#else>新增</#if>订单详情
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/order/dosave${suffix}">
<#if orderDetail??>
	<input type="hidden" name="detailId" value="${orderDetail.id}" />
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">订单信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				商品名称：${orderDetail.order.produce.name} <br/>
				支付编号：${orderDetail.payNo!} <br/>
				支付类型：${dicSetting.getParameterValue("order.pay.type." + orderDetail.payType)!} <br/>
				价格：${orderDetail.order.produce.nowPrice}元<br/>
				类型：${dicSetting.getParameterValue("produce.type." + orderDetail.order.produce.type)}商品 <br/>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">订单信息：</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				订单编号：${orderDetail.orderNo}  <br/>
				订购数量：${orderDetail.order.totalCount}  <br/>
				支付金额：${orderDetail.order.totalPrice}元 <br/>
				提交时间：${tagUtils.formatDate(orderDetail.order.recordCreateTime)}<br/>
				<#if detail??>
					发货时间：${tagUtils.formatDate(orderDetail.order.loadingTime)}<br/>
					物流公司：${orderDetail.flowFirm}<br/>
					物流单号：${orderDetail.flowNo}
				</#if>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">收货信息：</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				姓名：${orderDetail.order.user.realName}  <br/>
				联系电话：${orderDetail.order.address.contact!}  <br/>
				省区： ${commonTag.getCountyPath(orderDetail.order.address.areaCode)}  <br/>
				详情地址： ${orderDetail.order.address.address}
			</span>
		</div>
	</div>
</div>

<#if orderDetail.order.state==2>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">物流公司：</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input type="text" name="flowFirm" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">物流单号：</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<input type="text" name="flowNo" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态：</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
			<#assign _stateOptions = [{"value": 3, "valueString":"发货成功","labClass":"red","inputClass":"ace"}, {"value": -1, "valueString":"拒绝发货","labClass":"red","inputClass":"ace"}]/>
			<@ace.radioGroup options=_stateOptions checkValue=(orderDetail.order.state)!-1 name="state" isWrap=false/>
			</div>
		</div>
	</div>
<#else>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="verifyRemark">处理信息：</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-80">
					订单状态：
						${dicSetting.getParameterValue("order.state." + orderDetail.order.state)}
				</span>
			</div>
		</div>
	</div>
</#if>

<#if orderDetail.order.state==2>
<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>提  交</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>
	</div>
</div>
</#if>
</fieldset>
</form> 
<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}
		</span>
	</h4>
</#if>

<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"<#if orderDetail??>编辑<#else>新增</#if>订单详情",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			state: {
				required: true
			}
		},
	
		messages: {
		},
	
		invalidHandler: function (event, validator) { //display error alert on form submit   
			RP.Form.invalidHandler(event, validator);
		},
	
		highlight: function (e) {
			RP.Form.highlight(e);
		},
		
		errorPlacement: function (error, element) {
			RP.Form.errorPlacement(error, element);	
		},
	
		success: function (e) {
			RP.Form.success(e);	
		},
		
		submitHandler: function(form) {RP.Form.submitHandler(form);}
	});
});
</script>