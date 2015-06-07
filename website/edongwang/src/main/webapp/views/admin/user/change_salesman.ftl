<title>修改二级会员认证信息</title>
<div class="page-header">
	<h1>
		修改二级会员认证信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user/${user.id}/change_salesman${suffix}" enctype="multipart/form-data">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">该用户认证信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			认证楼盘ID：${user.userSalesman.house.id}, </br>
			认证楼盘: ${user.userSalesman.house.name},</br>
			认证时间: ${tagUtils.formatDate(user.userSalesman.recordCreateTime)},</br>
			认证图片: <img src="${tagUtils.getFileFullPath(user.userSalesman.credentialsImg)}" width=150/>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="newHouseId">请输入认证楼盘ID:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="newHouseId" id="newHouseId" value="" class="form-control" placeholder="请输入认证楼盘ID"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="credentialsImgFile">上传认证图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="ace-file-input width-50">
			<input type="file" name="credentialsImgFile" id="credentialsImgFile">
		</div>
		<small>* 上传认证图片</small>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"修改二级会员认证信息",  active: true}]);
	
	$('[id=credentialsImgFile]').ace_file_input({
		no_file:'没有选择图片 ...',
		btn_choose:'选择图片',
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
			newHouseId: {
				required: true,
				number:true
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