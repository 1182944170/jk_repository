<title>随机生成邀请码</title>
<div class="page-header">
	<h1>
		随机生成邀请码
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/invitation_code/generate_auto${suffix}">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="generateCount">输入需要生成随机码的数量:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="generateCount" id="generateCount" class="form-control" placeholder="随机码的数量"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">是否生成子邀请码:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonOptions checkValue=1 name="isGenerateChild" isWrap=false/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">所属厂家:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@xdm cmd="factory_list">
			<@ace.formSingleSelect options=m_list checkValue=1 name="factoryId" listKey="id" listValue="factoryName"/>
		</@xdm>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="childGenerateCount">子邀请码的数量:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="childGenerateCount" id="childGenerateCount" value="10" class="form-control" placeholder="子邀请码的数量"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
<div class="col-md-offset-3 col-md-9">
	<button class="btn btn-info" type="submit">
		<i class="icon-ok bigger-110"></i>
		提 交
	</button>
	&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
	<button class="btn" type="reset">
		<i class="icon-undo bigger-110"></i>
		重 置
	</button>
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
	RP.addBreadcrumb([{name:"随机生成邀请码",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			generateCount: {
				required: true,
				number:true,
				maxlength: 5
			},
			isGenerateChild: {
				required: true,
				
			},
			childGenerateCount: {
				required: true,
				number:true	
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