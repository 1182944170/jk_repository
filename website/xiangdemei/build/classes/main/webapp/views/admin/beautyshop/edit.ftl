<title><#if beautyShop??>编辑<#else>新增</#if> 美容院</title>
<div class="page-header">
	<h1>
		<#if beautyShop??>编辑<#else>新增</#if>美容院
		<small class="red">
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/beautyshop/dosave">
<#if beautyShop??>
	<input type="hidden" name="id" value="${beautyShop.id}"/>
</#if>
<input type="hidden" name="areaCode" value=""/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-25">				
				<input type="text" name="name" id="name"  value="${(beautyShop.name)!''}"  class="form-control" placeholder="美容院名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">所属原始ID:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-25">				
				<input type="text" name="rootUserId" id="rootUserId"  value="${(beautyShop.rootUserId)!'0'}"  class="form-control" placeholder="所属原始ID"/>
				<i class="icon-user"></i>
			</span>
			
			<small class="red">* 0 为没有</small>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="address">地址:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-60">				
				<select class="width-10" name="provinceSelect" id="provinceSelect"></select>
				<select class="width-10" name="citySelect" id="citySelect"></select>
				<select class="width-10" name="countySelect" id="countySelect"></select> <hr />
					
				<input type="text" name="address" id="address"  value="${(beautyShop.address)!''}"  class="form-control" placeholder="美容院地址"/>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="longitude">经度/纬度:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="input-icon width-20">
				<input type="text" name="longitude" id="longitude" value="${(beautyShop.longitude)!'0'}" class="form-control" placeholder="经度"/>
				<i class="icon-user"></i>
			</span>
			
			<span class="input-icon width-20">
				<input type="text" name="dimensions" id="dimensions" value="${(beautyShop.dimensions)!'0'}" class="form-control" placeholder="纬度"/>
				<i class="icon-user"></i>
			</span>
			<small class="red"> * 请填准确值</small>
		</div>
	</div>
</div>

<#assign fieldName="thumbImg" />
<#assign fieldLabel="封面图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<#assign fieldName="imgs" />
<#assign fieldLabel="标题图集" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<#assign fieldName="descImgs" />
<#assign fieldLabel="描述图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="contacts">联系电话:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="contacts" id="contacts" value="${(beautyShop.contacts)!''}" class="form-control" placeholder="联系电话"/>
				<i class="icon-user"></i>
			</span>
			<small class="red">多个以,分割</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@ace.radioGroup options=ace.commonStateOptions checkValue=(beautyShop.state)!-1 name="state" isWrap=false/>
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

<#assign fieldName="thumbImg" />
<#assign rootPath="resource/beautyShop/" />
<#assign fieldNameValue=(beautyShop.thumbImg)!"" />
<#assign isSingle=1 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="imgs" />
<#assign rootPath="resource/beautyShop/" />
<#assign fieldNameValue=(beautyShop.imgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="descImgs" />
<#assign rootPath="resource/beautyShop/" />
<#assign fieldNameValue=(beautyShop.descImgs)!"" />
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
var country = Object.create(B.Country);
$(document).ready(function(){
	country.regist4Select(${(beautyShop.areaCode)!-1},"provinceSelect","citySelect","countySelect");
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			address: {
				required: true
			},
			longitude: {
				required: true,
				number:true
			},
			dimensions: {
				required: true,
				number:true
			},
			contacts: {
				required: true
			},
			state: {
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
			if(!country.getSelectData()) {
				alert("请选择城镇数据!");
				return;
			}
			
			form.areaCode.value = country.getSelectData();
			
			RP.Form.submitHandler(form);
		}
	});
});
</script>