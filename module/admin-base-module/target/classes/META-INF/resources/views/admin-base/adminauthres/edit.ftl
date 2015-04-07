<title><#if adminAuthRes??>编辑<#else>新增</#if>资源映射</title>
<div class="page-header">
	<h1>
		<#if adminAuthRes??>编辑<#else>新增</#if>资源映射
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/adminauthres/dosave">
<#if adminAuthRes??>
	<input type="hidden" name="id" value="${adminAuthRes.id}"/>
<#else>
</#if>

<fieldset>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">Name:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(adminAuthRes.name)!''}" class="form-control" placeholder="Name"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="path">Path:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="block input-icon width-60">
				<textarea name="path" id="path" class="autosize-transition form-control limited" maxlength="500" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 172px;">${(adminAuthRes.path)!''}</textarea>
			</div>
			<span class="buttom">以 ，区分</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">Sort Index:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="sortIndex" id="sortIndex" value="${(adminAuthRes.sortIndex)!''}" class="form-control" placeholder="Sort Index 如 1 或者1.1"/>
				<i class="icon-edit"></i>
			</span>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">父菜单:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@admin_perm cmd="ad_parent_adminauthres_list">
			<@ace.formSingleSelect options=ar_list checkValue=(adminAuthRes.parent.id)!-1 name="parent.id" listKey="id" listValue="name"/>
		</@admin_perm>
		<span class="middle">默认为根目录</span>
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
	RP.addBreadcrumb([{name:"资源映射配置"}, {name:"<#if adminRole??>编辑<#else>新增</#if>资源映射",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			path:{
				required: true,
			},
			sortIndex: {
				required: true,
				number:true
			}
		},
	
		messages: {
			name: {
				required: "Please provide a valid Name."
			},
			path: {
				required: "Please specify a path.",
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