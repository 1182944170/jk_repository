<title><#if help??>编辑<#else>新增</#if>帮助</title>
<div class="page-header">
	<h1>
		<#if help??>编辑<#else>新增</#if>帮助
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/common/help/dosave${suffix}">
<#if help??>
	<input type="hidden" name="id" value="${help.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">帮助标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="title" id="title" value="${(help.title)!''}" class="form-control" placeholder="帮助标题"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="aliasesTitle">帮助别名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="aliasesTitle" id="aliasesTitle" value="${(help.aliasesTitle)!''}" class="form-control" placeholder="帮助别名"/>
				<i class="icon-user"></i>
				<small>别名请使用唯一标识 /helpcenter/别名.html</small>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="content">内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(help.context)!''}" instanceName="context" inputName="context" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类型:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign helpTypes=dicSetting.getParameterMap("help.type") />
		<@ace.formSingleSelect options=helpTypes checkValue=(help.type)!-1 name="type" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">排序:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text" name="sortIndex" id="sortIndex" value="${(help.sortIndex)!'1'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
			<div class="spinner-buttons input-group-btn btn-group-vertical">
				<button type="button" class="btn spinner-up btn-xs btn-info"><i class="icon-chevron-up"></i></button>
				<button type="button" class="btn spinner-down btn-xs btn-info"><i class="icon-chevron-down"></i></button>
			</div>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(help.state)!-1 name="state" isWrap=false/>
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
	RP.addBreadcrumb([{name:"题库"}, {name:"<#if help??>编辑<#else>新增</#if>帮助",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			type: {
				required: true
			},
			title: {
				required: true
			},
			aliasesTitle: {
				required: true
			},
			context: {
				required: true
			},
			state: {
				required: true
			},
			sortIndex: {
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