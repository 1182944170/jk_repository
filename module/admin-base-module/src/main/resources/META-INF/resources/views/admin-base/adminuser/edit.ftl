<title><#if adminUser??>编辑<#else>新增</#if>后台用户</title>
<div class="page-header">
	<h1>
		<#if adminUser??>编辑<#else>新增</#if>后台用户
		<small>
			<i class="icon-double-angle-right"></i>
			
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/adminuser/dosave">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userName">Username:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="userName" id="userName" value="${(adminUser.userName)!''}" <#if adminUser??> readonly </#if> class="form-control" placeholder="Username"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<#if adminUser??>
	<input type="hidden" name="id" value="${adminUser.id}"/>
<#else>

	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="pwd">Password:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<input type="password" name="pwd" id="pwd" class="form-control" placeholder="Password"/>
					<i class="icon-lock"></i>
				</span>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="confirmPwd">Confirm Password:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
				<input type="password" name="confirmPwd" id="confirmPwd" class="form-control" placeholder="Confirm Password"/>
				<i class="icon-lock"></i>
				</span>
			</div>
		</div>
	</div>
</#if>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">State:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(adminUser.state)!-1 name="state" isWrap=true/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">Role Group:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@admin_perm cmd="ad_role_list">
			<@ace.formSingleSelect options=r_list checkValue=(adminUser.adminRole.id)!-1 name="adminRole.id" listKey="id" listValue="name"/>
		</@admin_perm>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contact">Contact:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<input type="text" name="contact" id="contact" value="${(adminUser.contact)!''}" class="form-control" placeholder="Contact"/>
			<i class="icon-phone"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="email">Email:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<input type="text" name="email" id="email" value="${(adminUser.email)!''}" class="form-control" placeholder="Email"/>
			<i class="icon-key"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
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
</div>

<!--
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">Role Group:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@fck value="sfsdfsdfsd" instanceName="dsfsdhkfhsdkfh" inputName="news.content" height="300px;" SkinPath="skins/office2003/">
	    	${fck_body}
	    </@fck>
		</div>
	</div>
</div> -->
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

<#if adminUser??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer blue"></i>
		<span class="pink">编辑用户如果需要重置用户密码，请点击 <a href="${ctx}/admin/adminuser/${adminUser.id}/resetpwd">重置密码</a> !
		</span>
	</h4>
</#if>

<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"后台基础配置"}, {name:"<#if adminUser??>编辑<#else>新增</#if>后台用户",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			userName: {
				required: true
			},
			pwd: {
				required: true,
				minlength: 5
			},
			confirmPwd: {
				required: true,
				minlength: 5,
				equalTo: "#pwd"
			},
			state:{
				required: true,
			},
			
			"adminRole.id": {
				required: true,
			},
			contact: {
				required: true
			},
			email: {
				required: true,
				email: true
			},
		},
	
		messages: {
			userName: {
				required: "Please provide a valid userName."
			},
			pwd: {
				required: "Please specify a password.",
				minlength: "Please specify a secure password."
			},
			state: {
				required: "Please specify a state.",
			},
			
			"adminRole.id": {
				required: "Please specify a Role.",
			},
			
			contact: {
				required: "Please specify a contact.",
			},
			email: {
				required: "Please specify a email.",
			},
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