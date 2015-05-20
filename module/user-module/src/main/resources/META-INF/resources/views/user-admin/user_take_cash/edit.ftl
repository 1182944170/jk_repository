<title><#if userTakeCash??>编辑<#else>新增</#if>提现</title>
<div class="page-header">
	<h1>
		<#if userTakeCash??>编辑<#else>新增</#if>提现
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user_take_cash/dosave${suffix}">
<#if userTakeCash??>
	<input type="hidden" name="id" value="${userTakeCash.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userId">用户ID:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-30">
				<input type="text" name="userId" id="userId" value="${(userTakeCash.userId)!''}" readonly class="form-control" placeholder="用户ID"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="money">提现金额:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-20">
				<input type="text" name="money" id="money" value="${(userTakeCash.money)!''}" readonly class="form-control" placeholder="提现金额"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="money">提现银行信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				${userTakeCash.userBankCard.name}-${userTakeCash.userBankCard.account}
				[
				<#if userTakeCash.userBankCard.cfgBankAddress??>
					${userTakeCash.userBankCard.cfgBankAddress.address}-${commonTag.getCountyPath(userTakeCash.userBankCard.cfgBankAddress.countyCode)}-${userTakeCash.userBankCard.cfgBankAddress.cfgBank.name}
				<#else>
					${userTakeCash.userBankCard.address}-${userTakeCash.userBankCard.cfgBank.name}
				</#if>
				
				]
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">备注:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				<input type="text" name="remark" id="remark" value="${(userTakeCash.remark)!''}" class="form-control" placeholder="备注"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign takeCashStateOptions = [{"value": 1, "valueString":"处理成功","labClass":"red","inputClass":"ace"}, {"value": -1, "valueString":"处理失败","labClass":"red","inputClass":"ace"}]/>
		<@ace.radioGroup options=takeCashStateOptions checkValue=(userTakeCash.state)!-1 name="state" isWrap=false/>
		</div>
	</div>
</div>

<#if userTakeCash.state==0>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="verifyRemark">处理备注:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-80">
					<input type="text" name="verifyRemark" id="verifyRemark" value="${(userTakeCash.verifyRemark)!''}" class="form-control" placeholder="处理备注"/>
					<i class="icon-user"></i>
				</span>
			</div>
		</div>
	</div>
<#else>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="verifyRemark">处理信息:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-80">
					处理备注: ${userTakeCash.verifyRemark} 处理后台用户ID ${userTakeCash.verifyUId}
				</span>
			</div>
		</div>
	</div>
</#if>

<#if userTakeCash.state==0>
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
	RP.addBreadcrumb([{name:"<#if userTakeCash??>编辑<#else>新增</#if>提现",  active: true}]);
	
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