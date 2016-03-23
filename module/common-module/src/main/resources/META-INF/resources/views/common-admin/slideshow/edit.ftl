<title><#if slideshow??>编辑<#else>新增</#if>幻灯片</title>
<div class="page-header">
	<h1>
		<#if slideshow??>编辑<#else>新增</#if>幻灯片
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/common/slideshow/dosave" enctype="multipart/form-data">
<#if slideshow??>
	<input type="hidden" name="id" value="${slideshow.id}"/>
	<input type="hidden" name="icon" value="${slideshow.icon}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">幻灯片标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="title" id="title" value="${(slideshow.title)!''}" class="form-control" placeholder="幻灯片标题"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">幻灯片ICON:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" value="${(slideshow.icon)!''}" id="id-input-file-2">
			</div>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">幻灯片URL:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="url" id="url" value="" class="form-control" placeholder="幻灯片URL"/>
				<i class="icon-user"></i>
			</span>
		</div>
		<small class="red">
			${(dicSetting.getParameterValue("slideshow.field.url.tip"))!""}
		</small>
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
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if slideshow??>编辑<#else>新增</#if>幻灯片",  active: true}]);
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
				required: true,
				number:true
			},
			iconFile:{
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
				required: "请输入幻灯片标题."
			},
			iconFile: {
				required: "请输入幻灯片图片."
			},
			state: {
				required: "请选择幻灯片状态."
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
<script>
$(function (){
	<#if slideshow??>
		var o ="${slideshow.icon!}";
		var dt = $(".file-name").attr("data-title");
		$(".file-name").attr("data-title",o);
		$(".file-label").attr("data-title","重新选择文件");
	<#else>
	
	</#if>
})
</script>
