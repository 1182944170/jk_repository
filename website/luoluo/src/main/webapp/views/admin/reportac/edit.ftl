<title>查看举报详细</title>
<div class="page-header">
	<h1>
		查看举报详细
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form action="${ctx}/admin/actcy/${user.activityid}/setlist${suffix}" class="form-horizontal" role="form" id="validation-form" method="POST" enctype="multipart/form-data">

	<input type="hidden" name="id" value="${user.id}"/>
	

<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="clubId">活动id:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name ="syTitle" value="${user.activityid!}"  maxlength="32" class="form-control" placeholder="活动id"/>
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
				<textarea rows="10" cols="60" name="sycontent" id="clubName"  >${user.reportContext!}</textarea >
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="clubId">活动id:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name ="syTitle" value="${user.userid!}"  maxlength="32" class="form-control" placeholder="系统提示"/>
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
	function onblurs(){
		var phone=$("#phone").val();
			var eoppos=$(".eoppos").val();
			
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

</script>
