<title>修改标签信息</title>
<div class="page-header">
	<h1>
		修改标签信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/lable/dosave${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" enctype="multipart/form-data">

<fieldset>
	<input type="hidden" name="id" value="${user.id}">
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="phone">id:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<input type="text" name="id" readonly id="phone" value="${user.id}" maxlength="20" class="form-control" placeholder="id" onblur="onblurs()"/>
					<i class="icon-user"></i>
				</span>
			</div>
		</div>
	</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">标签:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="label" id="name" value="${user.label}"  maxlength="32" class="form-control" placeholder="标签姓名"/>
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

	$(document).ready(function(){
		RP.addBreadcrumb([{name:"基础设置"}, {name:"新增标签",  active: true}]);
		$('#id-input-file-1').ace_file_input({
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
				nickName: {
					required: true
				},
				phone:{
					required: true,
					number:true
				},
				passWord:{
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