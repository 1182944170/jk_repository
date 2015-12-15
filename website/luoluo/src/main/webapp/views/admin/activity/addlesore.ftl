<title>查看活动</title>
<script type="text/javascript" src="${ctx}/resources/js/tool.js"></script>
<!--<link type="text/css" href="${ctx}/resources/css/demo.css"  rel="stylesheet" />-->
<link type="text/css" href="${ctx}/resources/css/easyui.css"  rel="stylesheet" />
<script type="text/javascript" src="${ctx}/resources/js/jquery1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.easyui.min.js"></script>
<script>
<script>
function selectMap(){
		$("#mapdiv").css("display","");
}
</script>
<script type="text/javascript">
	function checkSub(){
		var names = "activityname,活动名称;phone,活动电话;d-input-file-2,封面图片;activitylocation,活动地点;number,活动人数;old_expense,成人费用";
		return checkCommit(names);
	}
	
	$.fn.datebox.defaults.formatter = function(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+m+'-'+d;
}
$.fn.datebox.defaults.parser = function(s){
	var t = Date.parse(s);
	if (!isNaN(t)){
		return new Date(t);
	} else {
		return new Date();
	}
}
	
</script>
<div class="page-header">
	<h1>
		查看活动
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/actcy/fffsave${suffix}" enctype="multipart/form-data">

<#if oop??>
	<input type="hidden" name="id" value="${(oop.id)!''}"/>
	<input type="hidden" name="sponsorid" value="${(oop.sponsorid)!''}"/>
	<input type="hidden" name="nowtime" value="${(oop.nowtime)!''}"/>
	<input type="hidden" name="type" value="${(oop.type)!''}"/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">活动编号</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-40">
		<input type="text" name="activitynumber"readonly value="${(oop.activitynumber)!''}" maxlength="32" class="form-control"/>
		</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="activityname" id="name" value="${(oop.activityname)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">分类图标:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="iconFile" id="id-input-file-2">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="url">封面图片:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix" >
			
		
				<img src="${tagUtils.getFileFullPath(oop.cover)!''}"width="400px">
			
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">分类名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="ace-file-input width-20">
		<input type="text" name="fitlist"  readonly id="name" value="${(fitlist.claName)!''}" maxlength="32" class="form-control" />
		</div>
		<#--><div class="clearfix">
		<@ace.formMultiSelect options=claList checkValues=(fitlist)! name="fitlist" attributes="class='chosen-select width-20' data-placeholder='请选择分类...'" listKey="id" listValue="claName"/>
		</div>-->
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动地点:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix" style="position:relative;">
				<#include "../baidumap.ftl">
				<input type="hidden" name="lng" id="lng" value="">
				<input type="hidden" name="lat" id="lat" value="">
				<input type="text" id="lnglat" name="activitylocation"  value ="${(oop.activitylocation)!''}"onclick="selectMap();"/>
				<!--<i class="icon-user"></i>-->
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="phone" id="phone" value="${(oop.phone)!''}" maxlength="32" class="form-control" />
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动人数:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="number" id="name" value="${(oop.number)!''}" maxlength="32" class="form-control" />
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">儿童费用:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="children_expense" id="children_expense" onchange="checkInt(this.value,'msgfff')" value="${(oop.children_expense)!''}" maxlength="32" class="form-control" />
				<i class="icon-user" id="msg"></i>
				<span class="icon-user" id="msgfff"></span>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">成人费用:</label>
	<div class="col-xs-12 col-sm-9" >
		<div class="clearfix" >
			<span class="block input-icon width-40">
				<input type="text" name="old_expense" id="name" value="${(oop.old_expense)!''}" maxlength="32" class="form-control" />
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">女孩费用:</label>
	<div class="col-xs-12 col-sm-9" >
		<div class="clearfix" >
			<span class="block input-icon width-40">
				<input type="text" name="old_expense" id="name" value="${(oop.gril_expense)!''}" maxlength="32" class="form-control" />
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<#assign fieldName="activitypicture" />
<#assign fieldLabel="活动图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			
				<@fck value="${(oop.activitycontent)!''}" instanceName="activity_content" inputName="activity_content" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="starttimeString">开始时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="starttimeString" id="starttimeString"  class ="easyui-datetimebox"value="" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
				
			</span>
			<span style="color:red">温馨提示：请输入格式为(例：2015-12-8 01:01:00)</span>
		</div>
		
	</div>
	
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="outtimeString">结束时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="outtimeString" id="outtimeString" class ="easyui-datetimebox" value="" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
			<span style="color:red">温馨提示：请输入格式为(例：2015-12-8 01:01:00)</span>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-offset-3 col-md-9">
		<button class="btn btn-info" type="submit"><i class="icon-ok bigger-110"></i>
	修改
		</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" type="reset"><i class="icon-undo bigger-110"></i>重  置</button>
	</div>
</div>

</fieldset>
</form>

<#assign fieldName="activitypicture" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(goodsDO.mainImg)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="figureImg" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(goodsDO.figureImg)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

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
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if oop??>编辑<#else>新增</#if>活动管理",  active: true}]);
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
			imgAddress:{
				required: true
			},
			type: {
				required: true
			},
			state:{
				required: true,
				number:true
			}
		},
	
		messages: {
			title: {
				required: "请输入图片标题."
			},
			state: {
				required: "请选择图片状态."
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