<title>添加系统消息</title>
<div class="page-header">
	<h1>
		添加系统消息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/windens/dosave${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" enctype="multipart/form-data">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="clubId">系统标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name ="syTitle" value=""  maxlength="32" class="form-control" placeholder="系统提示"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">系统内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<textarea rows="10" cols="10" name="sycontent" id="clubName" value=""  maxlength="32" class="form-control"></textarea >
			</span>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="userPicPath">系统图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="mainFile" id="id-input-file-1" value="">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
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
	function onblurs(){
		var phone=$("#phone").val();
			
	    if(!(/^1[3|4|5|8][0-9]\d{4,8}$/.test(phone))){ 
	        document.getElementById("cue").innerHTML = "请输入正确的手机号码";
	        return false; 
	    } else {
			document.getElementById("cue").innerHTML = "";
		}
		
		var url = "${ctx}/api/user/val_phone/"+phone;
		var param = {};
		$.getJSON(url, param, function(data){
			if(data.succ == true){
			
			}else{
				document.getElementById("cue").innerHTML = "该手机号码已被占用 ";
				return false;
			}
		});
	}

	$(document).ready(function(){
		RP.addBreadcrumb([{name:"基础设置"}, {name:"编辑系统消息",  active: true}]);
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
				mainFile:{
					required: true
					
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