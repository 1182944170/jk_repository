<title><#if adminRole??>编辑<#else>新增</#if>后台角色</title>
<div class="page-header">
	<h1>
		<#if adminRole??>编辑<#else>新增</#if>后台角色
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/adminrole/dosave">
<input type="hidden" name="roleAdminAuthResArrString"/>
<fieldset>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userName">角色名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(adminRole.name)!''}" class="form-control" placeholder="角色名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="description">描述:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="description" id="description" value="${(adminRole.description)!''}" class="form-control" placeholder="描述"/>
				<i class="icon-edit"></i>
			</span>
		</div>
	</div>
</div>

<#if adminRole??>
	<input type="hidden" name="id" value="${adminRole.id}"/>
<#else>
</#if>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(adminRole.state)!-1 name="state" isWrap=true/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">是否具备全部权限:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptionsYN checkValue=(adminRole.isSuper)!-1 name="isSuper" isWrap=true/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">选择权限:</label>
	<div class="col-xs-12 col-sm-9">
		<@admin_perm cmd="ad_parent_adminauthres_list">
		<div class="clearfix">
			<#list ar_list as vo>
			<div>
			<span class="blue bigger-150">${vo.name}&nbsp;&nbsp;</span>
			<input name="adminRoleCheck" type="checkbox" value="${vo.id}" <@admin_perm cmd="ad_role_has_perm" adminRole=adminRole adminAuthResId=vo.id> checked="checked" </@admin_perm>/>
			<#if vo.children?has_content>
				<div>
					<#list vo.children as voChild>
					<span>
						<span class="green bigger-110">&nbsp;&nbsp;&nbsp;&nbsp;${voChild.name}&nbsp;&nbsp;</span>
						<input name="adminRoleCheck" type="checkbox" value="${voChild.id}" <@admin_perm cmd="ad_role_has_perm" adminRole=adminRole adminAuthResId=voChild.id> checked="checked" </@admin_perm>/>
					</span>
					</#list>
				</div>
			</#if>
			<div>
		</#list>
		</@admin_perm>
		</div>
	</div>
</div>

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
RP.roleDisablePerms = function(){
	var isSuper = $("input[name='isSuper'][type=radio]:checked").val();
	if(isSuper == 1) {
		$("input[name='adminRoleCheck'][type='checkbox']").attr("disabled","disabled");
	} else {
		$("input[name='adminRoleCheck'][type='checkbox']").removeAttr("disabled");
	}
}
$(document).ready(function(){
	RP.addBreadcrumb([{name:"后台基础配置"}, {name:"<#if adminRole??>编辑<#else>新增</#if>后台角色",  active: true}]);
	
	RP.roleDisablePerms();
	$("input[name='isSuper'][type=radio]").bind("click", function(){
		RP.roleDisablePerms();
	});
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			state:{
				required: true,
			},
			isSuper: {
				required: true,
			}
		},
	
		messages: {
			name: {
				required: "Please provide a valid Name."
			},
			state: {
				required: "Please specify a state.",
			},
			
			isSuper: {
				required: "Please specify a isSuper.",
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
		
		submitHandler: function(form) {
			/**
			var isSuper = $("#isSuper").val();
			if(isSuper != 1) {
				var roleAdminAuthResArrString = [];
				$("input[name='adminRoleCheck'][type='checkbox']").each(function(){
					if(this.checked) {
						roleAdminAuthResArrString.push(this.value);
					}
				});
				form.roleAdminAuthResArrString.value = roleAdminAuthResArrString;
			}
			**/
			RP.Form.submitHandler(form);
		}
	});
});
</script>