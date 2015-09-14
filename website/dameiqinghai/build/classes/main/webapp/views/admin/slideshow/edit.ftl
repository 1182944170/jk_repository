<title><#if slideshow??>编辑<#else>新增</#if>广告</title>
<div class="page-header">
	<h1>
		<#if slideshow??>编辑<#else>新增</#if>广告
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/slideshow/dosave" enctype="multipart/form-data">
<#if slideshow??>
	<input type="hidden" name="id" value="${slideshow.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">广告标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="title" id="title" value="${(slideshow.title)!''}" class="form-control" placeholder="广告标题"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">广告ICON:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" id="id-input-file-2">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写<p>
				<lable class="red">图片大小: 首页 (宽)1080px（高）490px , 左下角：（宽）778px(高)230px</label>
			</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="content">广告内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(slideshow.url)!''}" instanceName="url" inputName="url" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类型:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign slideshowTypes=dicSetting.getParameterMap("slideshow.type") />
		<@ace.formSingleSelect options=slideshowTypes checkValue=(slideshow.type)!-1 name="type" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="sortIndex">排序:</label>
	<div class="col-xs-12 col-sm-9 ace-spinner" >
		<div class="input-group  width-20">
			<input type="text" name="sortIndex" id="sortIndex" value="${(slideshow.sortIndex)!'1'}" class="input-mini spinner-input form-control" id="spinner1" maxlength="3">
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
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(slideshow.state)!-1 name="state" isWrap=true/>
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
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if slideshow??>编辑<#else>新增</#if>广告",  active: true}]);
	$('#id-input-file-2').ace_file_input({
		no_file:'没图片 ...',
		btn_choose:'请选择图片',
		btn_change:'重新选择图片',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			title: {
				required: true
			},
			url:{
				required: true
			},
			type: {
				required: true
			},
			state: {
				required: true
			},
			sortIndex:{
				required: true,
				number:true
			}
		},
	
		messages: {
			title: {
				required: "请输入广告标题."
			},
			state: {
				required: "请选择广告状态."
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