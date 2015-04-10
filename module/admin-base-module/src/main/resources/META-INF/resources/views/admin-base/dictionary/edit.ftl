<title><#if dictionary??>编辑<#else>新增</#if>字典</title>
<div class="page-header">
	<h1>
		<#if dictionary??>编辑<#else>新增</#if>字典
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/dictionary/dosave">
<fieldset>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="variable">variable:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="variable" id="variable" value="${(dictionary.variable)!''}" <#if dictionary??> readonly </#if> class="form-control" placeholder="Variable"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="value">Value:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="value" id="value" value="${(dictionary.value)!''}" class="form-control" placeholder="Value"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">可修改:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonOptions checkValue=(dictionary.canUpdate)!-1 name="canUpdate" isWrap=false/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">可删除:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonOptions checkValue=(dictionary.canDelete)!-1 name="canDelete" isWrap=false/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">Role Group:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign dictionaryTypeOptions = [{"value": "list", "valueString":"list"}, {"value": "map", "valueString":"map"}]/>
		<@ace.formSingleSelect options=dictionaryTypeOptions checkValue=(dictionary.type)!"-1" name="type" listKey="value" listValue="valueString"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">排序:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<input type="text" name="sortIndex" id="sortIndex" value="${(dictionary.sortIndex)!''}" class="form-control" placeholder="SortIndex"/>
			<i class="icon-phone"></i>
			</span>
		</div>
	</div>
</div>

<div class="col-md-offset-3 col-md-9">
	<button class="btn btn-info" type="submit">
		<i class="icon-ok bigger-110"></i>
		Submit
	</button>
	&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
	<button class="btn" type="reset">
		<i class="icon-undo bigger-110"></i>
		Reset
	</button>
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
	RP.addBreadcrumb([{name:"后台基础配置"}, {name:"<#if dictionary??>编辑<#else>新增</#if>字典",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			variable: {
				required: true
			},
			value: {
				required: true
			},
			type: {
				required: true
			},
			canUpdate:{
				required: true,
			},
			
			canDelete: {
				required: true,
			},
			sortIndex: {
				required: true,
				number:true
			}
		},
	
		messages: {
			variable: {
				required: "Please provide a valid variable."
			},
			value: {
				required: "Please specify a value."
			},
			type: {
				required: "Please specify a type.",
			},
			
			canUpdate: {
				required: "Please specify a canUpdate.",
			},
			canDelete: {
				required: "Please specify a canDelete.",
			},
			sortIndex: {
				required: "Please specify a sortIndex.",
			}
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