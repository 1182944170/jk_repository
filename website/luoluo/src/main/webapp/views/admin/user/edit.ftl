<title>修改用户信息</title>
<div class="page-header">
	<h1>
		查看用户信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/user/dosave${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" enctype="multipart/form-data">

<fieldset>
	<input type="hidden" name="id" value="${user.id}">
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">头像:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<img src="${tagUtils.getFileFullPath(user.namePic!)}">
			</div>
		</div>
	</div>
	<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">账号:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"readonly value="${user.acnumber!}"  maxlength="32" class="form-control" placeholder="账号"/>
				
			</span>
		</div>
	</div>
</div>
	<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">姓名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"readonly value="${user.name!}"  maxlength="32" class="form-control" placeholder="用户姓名"/>
				
			</span>
		</div>
	</div>
</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="phone">电话号码:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<input type="text" name="phone" readonly id="phone" value="${user.phone!}" maxlength="20" class="form-control" placeholder="电话号码" onblur="onblurs()"/>
					<font color="red" id="cue" name="cue"></font>
				</span>
			</div>
		</div>
	</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">年龄:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"readonly value="${user.age!}"  maxlength="32" class="form-control" placeholder="年龄"/>
				
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">工作单位:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"readonly value="${user.company!}"  maxlength="32" class="form-control" placeholder="工作单位"/>
				
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nickName">qq:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"readonly value="${user.qqaccount!}"  maxlength="32" class="form-control" placeholder="联系方式"/>
				
			</span>
		</div>
	</div>
</div>


	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="passWord">密码:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<input type="passWord" name="password"readonly id="password" value="${user.password!}" maxlength="32" class="form-control" placeholder="密码"/>
					
				</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="passWord">余额:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<input type="text" name="password" id="password" readonly value="￥${user.personalMany!}" maxlength="32" class="form-control" placeholder="余额"/>
					
				</span>
			</div>
		</div>
	</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="expert">注册时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="expert" readonly id="expert" value="${tagUtils.formatDate(user.ctiontime)}" maxlength="32" value="" class="form-control" placeholder="擅长"/>
				
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		
	          <input id="btn2"  type="button" onclick="returnlist();" value="返  回"/>
	       
		<!--<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>提  交</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>-->
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

	function returnlist(){
		window.location.href="${ctx}/admin/user/list/";
	}
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
		RP.addBreadcrumb([{name:"基础设置"}, {name:"修改用户",  active: true}]);
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