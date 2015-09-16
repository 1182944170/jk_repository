<title><#if oop??>编辑<#else>新增</#if>商品管理</title>

<div class="page-header">
	<h1>
		<#if oop??>编辑<#else>新增</#if>商品管理
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/good/dosave" >
<#if oop??>
	<input type="hidden" name="id" value=""/>
</#if>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">活动编号</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<span class="block input-icon width-40">
		<input type="text" readonly value="${(oop.activity_number)!''}" maxlength="32" class="form-control"/>
		</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.activityname)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
				
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">分类名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.formMultiSelect options=claList checkValues=(fitlist)! name="fitlist" attributes="class='chosen-select width-20' data-placeholder='请选择分类...'" listKey="id" listValue="claName"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动地点:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.activitylocation)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动人数:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.number)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">儿童费用:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.children_expense)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">成人费用:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.old_expense)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<#assign fieldName="mainImg" />
<#assign fieldLabel="活动图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">活动内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(oop.activity_content)!''}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<link type="text/css" href="${ctx}/resources/css/demo.css"  rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/css/easyui.css"  rel="stylesheet" />

<script type="text/javascript" src="${ctx}/resources/js/jquery_1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.easyui.min.js"></script>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">开始时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name"  class ="easyui-datetimebox"value="${oop.starttime!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">结束时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" class ="easyui-datetimebox" value="${oop.outtime!}" maxlength="32" class="form-control" />
				<!--<i class="icon-user"></i>-->
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">是否成功</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
			<#if oop.typeok==1>
				<input type="text" readonly value="未成功">
				</#if><!--<i class="icon-user"></i>-->
			<#if oop.typeok==2>
				<input type="text" readonly value="成功">
				</#if>
				<input type="hidden" value="${oop.typeok}">
				<!--<i class="icon-user"></i>-->
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

<#assign fieldName="mainImg" />
<#assign rootPath="jiaju/resource/goodsImg/" />
<#assign fieldNameValue=(goodsDO.mainImg)!"" />
<#assign isSingle=1 />
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
	RP.addBreadcrumb([{name:"基础设置"}, {name:"<#if oop??>编辑<#else>新增</#if>商品管理",  active: true}]);
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