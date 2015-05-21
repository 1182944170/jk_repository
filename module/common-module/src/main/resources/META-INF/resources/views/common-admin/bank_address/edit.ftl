<title><#if cfgBankAddress??>编辑<#else>新增</#if>银行地址</title>
<div class="page-header">
	<h1>
		<#if cfgBankAddress??>编辑<#else>新增</#if>银行地址
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/common/bank_address/dosave${suffix}">
<#if cfgBankAddress??>
	<input type="hidden" name="id" value="${cfgBankAddress.id}"/>
</#if>
<input type="hidden" name="countyCode" value="${(cfgBankAddress.countyCode)!0}"/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">银行:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.formSingleSelect options=cfgBanks checkValue=(cfgBankAddress.cfgBank.id)!-1 name="cfgBank.id" listKey="id" listValue="name"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">城市:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<select class="width-10" name="provinceSelect" id="provinceSelect"></select>
			<select class="width-10" name="citySelect" id="citySelect"></select>
			<select class="width-10" name="countySelect" id="countySelect"></select>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="address">银行开户地址:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="address" id="address" value="${(cfgBankAddress.address)!''}" class="form-control" placeholder="银行开户地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(cfgBankAddress.state)!-1 name="state" isWrap=true/>
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
var country = Object.create(B.Country);
$(document).ready(function(){
	country.regist4Select(${(cfgBankAddress.countyCode)!-1},"provinceSelect","citySelect","countySelect");
	RP.addBreadcrumb([{name:"题库"}, {name:"<#if cfgBankAddress??>编辑<#else>新增</#if>银行开户地址",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			address: {
				required: true
			},
			"cfgBank.id":{
				required: true
			},
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
		
		submitHandler: function(form) {
			if(!country.getSelectData()) {
				alert("请选择城镇数据!");
				return;
			}
			
			form.countyCode.value = country.getSelectData();
			RP.Form.submitHandler(form);
		}
	});
});
</script>