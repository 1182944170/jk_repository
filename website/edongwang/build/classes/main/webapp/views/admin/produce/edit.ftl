<title><#if produce??>编辑<#else>新增</#if>${dicSetting.getParameterValue("produce.type."+type)}商品</title>

<div class="page-header">
	<h1>
		<#if produce??>编辑<#else>新增</#if>${dicSetting.getParameterValue("produce.type."+type)}商品
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/produce/dosave" enctype="multipart/form-data">
<#if produce??>
	<input type="hidden" name="id" value="${produce.id}"/>
	<input type="hidden" name="realStock" value="${produce.realStock}"/>
	<input type="hidden" name="saleNumber" value="${produce.saleNumber}"/>
</#if>
<input type="hidden" name="type" value="${type}"/>

<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">商品名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(produce.name)!''}" class="form-control" placeholder="商品名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="oldPrice">原价:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="oldPrice" id="oldPrice" value="${(produce.oldPrice)!''}" class="form-control" placeholder="原价"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="nowPrice">现价:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<#if type == 2>
					<input type="text" name="nowPrice" id="nowPrice" value="1" class="form-control" placeholder="现价"/>
				<#else>
					<input type="text" name="nowPrice" id="nowPrice" value="${(produce.nowPrice)!''}" class="form-control" placeholder="现价"/>
				</#if>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cartage">运费:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="cartage" id="cartage" value="${(produce.cartage)!''}" class="form-control" placeholder="运费"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="realStock">真实库存:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="realStock" id="realStock" value="${(produce.realStock)!''}" class="form-control" placeholder="真实库存"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="fakeStock">库存:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="fakeStock" id="fakeStock" value="${(produce.fakeStock)!''}" class="form-control" placeholder="库存"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<#if type == 3>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="beginSecondTimeString">开始时间:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40"> 
					<#if produce??>
						<input type="text" name="beginSecondTimeString" id="beginSecondTimeString" value="${tagUtils.formatDate(produce.beginSecondTime)}" class ="form-control" placeholder="格式：${tagUtils.formatDate(systemTiem)}" maxlength="19" class="form-control" />  
					<#else>
						<input type="text" name="beginSecondTimeString" id="beginSecondTimeString" value="${tagUtils.formatDate(systemTiem)}" class ="form-control" placeholder="格式：${tagUtils.formatDate(systemTiem)}" maxlength="19" class="form-control" />
					</#if>
				</span>
			</div>
		</div>
	</div>
</#if>

<#if type == 3>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="endSecondTimeString">结束时间:</label>
		<div class="col-xs-12 col-sm-9">
			<div class="clearfix">
				<span class="block input-icon width-40">
					<#if produce??>
						<input type="text" name="endSecondTimeString" id="endSecondTimeString" value="${tagUtils.formatDate(produce.endSecondTime)}" class ="form-control" placeholder="格式：${tagUtils.formatDate(systemTiem)}" maxlength="19" class="form-control" />  
					<#else>
						<input type="text" name="endSecondTimeString" id="endSecondTimeString" value="${tagUtils.formatDate(systemTiem)}" class ="form-control" placeholder="格式：${tagUtils.formatDate(systemTiem)}" maxlength="19" class="form-control" />
					</#if>
				</span>
			</div>
		</div>
	</div>
</#if>

<#assign fieldName="img" />
<#assign fieldLabel="产品主图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<#assign fieldName="descImg" />
<#assign fieldLabel="产品详情图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">描述:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(produce.remark)!''}" instanceName="remark" inputName="remark" height="200px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(produce.state)!-1 name="state" isWrap=true/>
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

<#assign fieldName="img" />
<#assign rootPath="resources/produce/" />
<#assign fieldNameValue=(produce.img)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="descImg" />
<#assign rootPath="resources/produce/" />
<#assign fieldNameValue=(produce.descImg)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<script>
$(document).ready(function(){
	RP.addBreadcrumb([ {name:"<#if produce??>编辑<#else>新增</#if>${dicSetting.getParameterValue("produce.type."+type)}商品",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			remark:{
				required: true
			},
			oldPrice:{
				required: true,
				number:true
			},
			cartage:{
				required: true,
				number:true
			},
			nowPrice: {
				required: true,
				number:true
			},
			realStock:{
				required: true,
				number:true
			},
			<#if type == 3>
				beginSecondTimeString:{
					required: true
				},
				endSecondTimeString:{
					required: true
				},
			</#if>
			fakeStock:{
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
		
		submitHandler: function(form) {RP.Form.submitHandler(form);}
	});
});
</script>