<title><#if job??>编辑<#else>新增</#if>招聘</title>
<div class="page-header">
	<h1>
		<#if job??>编辑<#else>新增</#if>招聘
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/job/dosave${suffix}" enctype="multipart/form-data">
<#if job??>
	<input type="hidden" name="id" value="${job.id}"/>
	<input type="hidden" name="recordCreateTime" value="${job.recordCreateTime}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">招聘名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(job.name)!''}" class="form-control" placeholder="招聘名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">招聘备注:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="remark" id="remark" value="${(job.remark)!''}" class="form-control" placeholder="招聘备注"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="workAddress">工作地区:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="workAddress" id="workAddress" value="${(job.workAddress)!''}" class="form-control" placeholder="工作地区"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">年龄要求:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("job.ageType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(job.ageType)!-1 name="ageType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">经验要求:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("job.expType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(job.expType)!-1 name="expType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">学历要求:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("job.eduType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(job.eduType)!-1 name="eduType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">职位月薪:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("job.moneyType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(job.moneyType)!-1 name="moneyType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">工作性质:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("job.jobType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(job.jobType)!-1 name="jobType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contact">联系电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="contact" id="contact" value="${(job.contact)!''}" class="form-control" placeholder="联系电话"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">公司简介:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(job.comRemark)!''}" instanceName="comRemark" inputName="comRemark" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if job??>编辑<#else>新增</#if>招聘",  active: true}]);
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			remark: {
				required: true
			},
			workAddress: {
				required: true
			},
			ageType: {
				required: true
			},
			expType: {
				required: true
			},
			eduType: {
				required: true
			},
			jobType: {
				required: true
			},
			moneyType:{
				required: true
			},
			contact:{
				required: true
			},
			comRemark:{
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
			RP.Form.submitHandler(form);
		}
	});
});
</script>