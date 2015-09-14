<title> <#if spotsPhoto??>编辑<#else>新增</#if>景点 </title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/spotsphoto/dosave${suffix}" enctype="multipart/form-data">
<#if spotsPhoto??>
	<input type="hidden" name="id" value="${spotsPhoto.id}"/>
	<input type="hidden" name="spotsId" value="${spotsPhoto.spotsId}"/>
</#if>
<input type="hidden" name="areaCode" value=""/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">景点列表:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<select id="spotsId" name="spotsId">
				<option> 请选择景点 </option>
				<#list spotsList as u>
					<option value="${u.id}"> ${u.spotsName!} </option>				
				</#list>
			</select>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="photoName">画册名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="photoName" id="photoName" value="${(spotsPhoto.photoName)!''}" maxlength="64" class="form-control" placeholder="画册名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coverPath">封面:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="coverPath" id="id-input-file-1">
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
var country = Object.create(B.Country);
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if spotsPhoto??>编辑<#else>新增</#if>画册",  active: true}]);
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
			spotsId: {
				required: true
			},
			photoName: {
					required: true
			},
			cover: {
					required: true
			}
		},
	
		messages: {
			"spotsId":"请选择景点",
			"photoName":"请输入相册名",
			"cover":"请选择封面"
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
		}
	});
});
</script>