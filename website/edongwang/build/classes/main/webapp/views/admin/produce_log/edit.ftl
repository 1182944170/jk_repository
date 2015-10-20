<title><#if produceLog??>编辑<#else>新增</#if>商品记录</title>
<div class="page-header">
	<h1>
		<#if produceLog??>编辑<#else>新增</#if>商品记录
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce_log/dosave${suffix}">
<#if produceLog??>
	<input type="hidden" name="id" value="${produceLog.id}" />
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">商品信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				商品名称:${produceLog.produce.name} <br/>
				价格:${produceLog.produce.nowPrice} / RMB <br/>
				类型:${dicSetting.getParameterValue("produce.type." + produceLog.produce.type)} 商品 <br/>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">用户信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				用户名:${produceLog.user.realName}  <br/>
				联系电话:${produceLog.user.contact}  <br/>
				省区: ${commonTag.getCountyPath(produceLog.address.areaCode)}  <br/>
				详细地址: ${produceLog.address.address}
			</span>
		</div>
	</div>
</div>

<#if produceLog.sendShopState==0 || produceLog.sendShopState==2>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
			<#assign _stateOptions = [{"value": 1, "valueString":"处理成功","labClass":"red","inputClass":"ace"}, {"value": -1, "valueString":"处理失败","labClass":"red","inputClass":"ace"}]/>
			<@ace.radioGroup options=_stateOptions checkValue=(produceLog.sendShopState)!-1 name="sendShopState" isWrap=false/>
			</div>
		</div>
	</div>
<#else>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="verifyRemark">处理信息:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-80">
					发货状态:<#if produceLog.sendShopState == 0>
							<span class="label label-sm label-success arrowed">未处理</span>
						<#elseif produceLog.sendShopState == 1>
							<span class="label label-sm label-success arrowed">处理成功</span>
						<#elseif produceLog.sendShopState == 2>
							<span class="label label-sm label-success arrowed">已中奖待处理</span>
						<#else>
							<span class="label label-sm label-warning arrowed">处理失败</span>
						</#if>
				</span>
			</div>
		</div>
	</div>
</#if>

<#if produceLog.sendShopState==0 || produceLog.sendShopState==2>
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
	RP.addBreadcrumb([{name:"<#if produceLog??>编辑<#else>新增</#if>商品记录",  active: true}]);
	
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