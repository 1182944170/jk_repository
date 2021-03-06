<#assign noticeEditTitle=(dicSetting.getParameterValue("notice.editTitle"))!"通知" />
<title><#if notice??>编辑<#else>新增</#if>${noticeEditTitle}</title>
<div class="page-header">
	<h1>
		<#if notice??>编辑<#else>新增</#if>${noticeEditTitle}
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/common/notice/dosave${suffix}">
<#if notice??>
	<input type="hidden" name="id" value="${notice.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">${noticeEditTitle}标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="title" id="title" value="${(notice.title)!''}" class="form-control" placeholder="${noticeEditTitle}标题"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="content">内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(notice.content)!''}" instanceName="content" inputName="content" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
		<small>
			${(dicSetting.getParameterValue("notice.field.content.tip"))!""}
		</small>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(notice.state)!-1 name="state" isWrap=true/>
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
	RP.addBreadcrumb([{name:"<#if notice??>编辑<#else>新增</#if>${noticeEditTitle}",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			title: {
				required: true
			},
			content: {
				required: true
			},
			state: {
				required: true
			}
		},
	
		messages: {
			title: {
				required: "请输入${noticeEditTitle}标题."
			},
			content: {
				required: "请输入${noticeEditTitle}内容."
			},
			state: {
				required: "请选择${noticeEditTitle}状态."
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