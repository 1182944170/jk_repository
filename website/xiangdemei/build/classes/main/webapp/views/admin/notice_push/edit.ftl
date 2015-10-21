<title>增加消息推送</title>
<div class="page-header">
	<h1>
		增加消息推送<
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/notice_push/dosave${suffix}" enctype="multipart/form-data">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="thumbTitle">缩略标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="thumbTitle" id="thumbTitle" value="" class="form-control" placeholder="缩略标题"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类型:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<#assign pushTypeMap=dicSetting.getParameterMap("push.pushType") />
				<@ace.formSingleSelect options=pushTypeMap checkValue=1 name="pushType" listKey="k" listValue="v"/>
			</span>
			<br/>
			<span class="block input-icon width-80">
				<input type="text" name="userIds" id="userIds" value="" class="form-control" placeholder="会员ID集"/>
				<i class="icon-user"></i>
			</span>
			<small class="red">* 如果是“对指定会员ID集”发送 则填写用户ID集 用,逗号</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">协议地址:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="url" id="url" value="" class="form-control" placeholder="协议地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="content">内容:</label>
	<div class="col-xs-12 col-sm-9">
		<span class="block input-icon width-40">
			<textarea name="content" id="content" rows="6" value="" cols="70" placeholder="内容">
			</textarea>
		</span>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"新增消息推送",  active: true}]);
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			thumbTitle: {
				required: true
			},
			content: {
				required: true
			},
			pushType: {
				required: true
			},
			url: {
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