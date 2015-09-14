<title><#if newsinfo??>编辑<#else>发布</#if>资讯</title>
<div class="page-header">
	<h1>
		<#if newsinfo??>编辑<#else>发布</#if>资讯
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/newsinfo/dosave${suffix}" enctype="multipart/form-data">
<#if newsinfo??>
	<input type="hidden" name="id" value="${newsinfo.id}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="caption">资讯名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="caption" id="caption" value="${(newsinfo.caption)!''}" maxlength="64" class="form-control" placeholder="资讯名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="authors">作者:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="authors" id="authors" value="${(newsinfo.authors)!''}" maxlength="64" class="form-control" placeholder="作者"/>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="content">内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(newsinfo.content)!''}" instanceName="content" inputName="content" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
		<small>
			${(dicSetting.getParameterValue("newsinfo.content"))!""}
		</small>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="photosPath">图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="photosPath" id="id-input-file-1">
			</div>
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
		RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if newsinfo??>编辑<#else>发布</#if>资讯",  active: true}]);
		$('#id-input-file-1').ace_file_input({
			no_file:'没文件 ...',
			btn_choose:'请选择文件',
			btn_change:'重新选择文件',
			droppable:false,
			onchange:null,
			thumbnail:false
		});
		
		$('#validation-form').validate({
			errorElement: 'div',
			errorClass: 'help-block',
			focusInvalid: true,
			rules: {
				caption: {
					required: true
				},
				content: {
					required: true
				},
				authors: {
					required: true
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
