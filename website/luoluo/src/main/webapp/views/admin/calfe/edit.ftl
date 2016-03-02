<title><#if classificationDO??>编辑<#else>新增</#if>分类管理</title>
<div class="page-header">
	<h1>
		<#if classificationDO??>编辑<#else>新增</#if>分类管理
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/cal/dosave${suffix}" enctype="multipart/form-data">
<#if classificationDO??>
	<input type="hidden" name="id" value="${classificationDO.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">分类名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="claName" id="claName" value="${(classificationDO.claName)!''}" maxlength="32" class="form-control" placeholder="分类名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<#--><div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">分类图标:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" id="id-input-file-2">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>-->

<#--<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">分类主图:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="mainFile" id="id-input-file-3">
			</div>
			<small>* 已经存在的，如果不修改则不需要填写</small>
		</div>
	</div>
</div>-->
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">设置手续费:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<input type="text" name="proced" id="proced" value="${(classificationDO.procedures)!''}">%
		</div>
	</div>
</div>

<#--<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">排序:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="seqn" id="seqn" value="${(classificationDO.seqn)!''}" maxlength="32" class="form-control" />
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>-->



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
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if classificationDO??>编辑<#else>新增</#if>分类管理",  active: true}]);
	$('#id-input-file-2').ace_file_input({
		no_file:'没图片 ...',
		btn_choose:'请选择图片',
		btn_change:'重新选择图片',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('#id-input-file-3').ace_file_input({
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
		    proced: {
				required: true
			},
		    claName: {
				required: true
			},
			iconFile: {
				required: true
			},
			mainFile:{
				required: true
			}
		},
	
		messages: {
		    proced: {
				required: "请输入手续费 最低为0."
			},
		    claName: {
				required: "名字不为空"
			},
			iconFile: {
				required: "请输入分类图标."
			},
			mainFile: {
				required: "请选择分类主图."
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
		
		submitHandler: function(form){
			RP.Form.submitHandler(form);
		}
	});
});
</script>