<title><#if house??>编辑<#else>新增</#if>楼盘</title>
<div class="page-header">
	<h1>
		<#if house??>编辑<#else>新增</#if>楼盘
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/house/dosave${suffix}" enctype="multipart/form-data">
<#if house??>
	<input type="hidden" name="id" value="${house.id}"/>
	<input type="hidden" name="houseImg" value="${house.houseImg}"/>
	<input type="hidden" name="houseTypeImg" value="${house.houseTypeImg}"/>
	<input type="hidden" name="recordCreateTime" value="${house.recordCreateTime}"/>
</#if>
	<input type="hidden" name="areaCode" value=""/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">楼盘名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(house.name)!''}" class="form-control" placeholder="楼盘名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类别:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign propertyTypes=dicSetting.getParameterMap("house.propertyType") />
		<@ace.formSingleSelect options=propertyTypes checkValue=(house.propertyType)!-1 name="propertyType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">城市:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<select class="width-10" name="provinceSelect" id="provinceSelect"></select>
			<select class="width-10" name="citySelect" id="citySelect"></select>
			<select class="width-10" name="countySelect" id="countySelect"></select>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="address">详细地址:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="address" id="address" value="${(house.address)!''}" class="form-control" placeholder="详细地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">面积:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign surfaceTypes=dicSetting.getParameterMap("house.surfaceType") />
		<@ace.formSingleSelect options=surfaceTypes checkValue=(house.surfaceType)!-1 name="surfaceType" listKey="key" listValue="value"/>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="avgPrice">均价:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="avgPrice" id="avgPrice" value="${(house.avgPrice)!''}" class="form-control" placeholder="均价"/>
				<i class="icon-user"></i>
			</span>
			
			<small>* 单位 (元)</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="recommendPrice">推荐奖励:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="recommendPrice" id="recommendPrice" value="${(house.recommendPrice)!'0'}" class="form-control" placeholder="推荐奖励"/>
				<i class="icon-user"></i>
			</span>
			
			<small>* 单位 (元) 暂无的情况下写 0</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="bargainPrice">成交奖励:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="bargainPrice" id="bargainPrice" value="${(house.bargainPrice)!''}" class="form-control" placeholder="成交奖励"/>
				<i class="icon-user"></i>
			</span>
			
			<small>* 单位 (元)</small>
		</div>
	</div>
</div>



<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="protocolBeginTimeString">协议开始时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="protocolBeginTimeString" name="protocolBeginTimeString" value="${tagUtils.formatDate((house.protocolBeginTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="protocolEndTimeString">协议结束时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="input-group  width-40">
				<input class="form-control date-picker" id="protocolEndTimeString" name="protocolEndTimeString" value="${tagUtils.formatDate((house.protocolEndTime)!0, 'yyyy-MM-dd')}" type="text" data-date-format="yyyy-mm-dd" />
				<span class="input-group-addon">
					<i class="icon-calendar bigger-110"></i>
				</span>
			</div>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="houseImgIconFile">楼盘主图:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="houseImgIconFile" id="houseImgIconFile">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="houseTypeImgIconFile">户型图:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="houseTypeImgIconFile" id="houseTypeImgIconFile">
			</div>
			<small>* 已经存在的icon如果不修改则不需要填写</small>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">推荐流程及奖励说明:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(house.flowIntro)!''}" instanceName="flowIntro" inputName="flowIntro" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">楼盘简介:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="clearfix">
		<@fck value="${(house.intro)!''}" instanceName="intro" inputName="intro" height="300px;" toolbarSet="Basic">
	    	${fck_body}
	    </@fck>
		</div>
	</div>
</div> 

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">状态:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<#assign commonStateOptions = [{"value": 1, "valueString":"启用","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"即将上线","labClass":"red","inputClass":"ace"}, {"value": -1, "valueString":"删除状态","labClass":"red","inputClass":"ace"}]/>
		<@ace.radioGroup options=commonStateOptions checkValue=(house.state)!-1 name="state" isWrap=false/>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if house??>编辑<#else>新增</#if>楼盘",  active: true}]);
	country.regist4Select(${(house.areaCode)!-1},"provinceSelect","citySelect","countySelect");
	$('#houseImgIconFile').ace_file_input({
		no_file:'没有选择图片 ...',
		btn_choose:'选择图片',
		btn_change:'重新选择图片',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('#houseTypeImgIconFile').ace_file_input({
		no_file:'没有选择图片 ...',
		btn_choose:'选择图片',
		btn_change:'重新选择图片',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	
	$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
				
	$(".chosen-select").chosen(); 
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			propertyType: {
				required: true
			},
			areaCode: {
				required: true
			},
			address: {
				required: true
			},
			surfaceType: {
				required: true
			},
			totalPrice: {
				required: true
			},
			recommendPrice: {
				required: true
			},
			bargainPrice:{
				required: true
			},
			protocolBeginTimeString:{
				required: true
			},
			protocolEndTimeString:{
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