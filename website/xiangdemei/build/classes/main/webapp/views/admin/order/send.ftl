<title>发送订单商品</title>
<div class="page-header">
	<h1>
		发送订单商品
		<small class="red">
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/order/${orderItem.orderId}/order_item/${orderItem.id}/send${suffix}" enctype="multipart/form-data">
<fieldset>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">选择物流:</label>
	<div class="col-xs-12 col-sm-9">
	  <div class="clearfix">
	  <#assign logisticsMap=dicSetting.getParameterMap("logistics.cfg") />
	  <@ace.formSingleSelect options=logisticsMap checkValue="SF" name="sendChannel"/>
	  </div>
	</div>
</div>
  
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="orderNo">物流编号:</label>
	<div class="col-xs-12 col-sm-9">
	  <div class="clearfix">
	    <span class="block input-icon width-40">
	      <input type="text" name="sendNo" id="sendNo" value="" class="form-control" placeholder="物流编号"/>
	      <i class="icon-user"></i>
	    </span>
	  </div>
	</div>
</div>
          
<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>提  交</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>
	</div>
</div>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"发送订单商品",  active: true}]);
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				sendChannel: true
			},
			orderNo: {
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
		
		submitHandler: function(form) {
			RP.Form.submitHandler(form);
		}
	});
});
</script>