<title><#if userSalesman??>编辑<#else>新增</#if>申请业务员信息</title>
<div class="page-header">
	<h1>
		<#if userSalesman??>编辑<#else>新增</#if>申请业务员信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/usersalesman/dosave${suffix}">
<#if userSalesman??>
	<input type="hidden" name="userId" value="${userSalesman.userId}"/>
	<input type="hidden" name="credentialsImg" value="${userSalesman.credentialsImg}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">申请业务员图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<img src="${tagUtils.getFileFullPath(userSalesman.credentialsImg)}" />
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(userSalesman.state)!-1 name="state" isWrap=true/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="houseName">楼盘名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="houseName" id="houseName" value="${(userSalesman.house.name)!''}" class="form-control" placeholder="楼盘名字"/>
				<i class="icon-user"></i>
			</span>
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
$(userSalesman).ready(function(){
	RP.addBreadcrumb([{name:"题库"}, {name:"<#if userSalesman??>编辑<#else>新增</#if>申请业务员信息",  active: true}]);
	
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