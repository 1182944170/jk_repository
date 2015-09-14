<title><#if spots??>编辑<#else>新增</#if>景点</title>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/spots/dosave${suffix}" enctype="multipart/form-data">
<#if spots??>
	<input type="hidden" name="id" value="${spots.id}"/>
</#if>
<input type="hidden" name="areaCode" value=""/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="spotsName">景点名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="spotsName" id="spotsName" value="${(spots.spotsName)!''}" maxlength="64" class="form-control" placeholder="景点名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">景点地址:</label>
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
				<input type="text" name="address" id="address" value="${(spots.address)!''}" maxlength="64" class="form-control" placeholder="景点地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="fromAddress">出发地:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="fromAddress" id="fromAddress" value="${(spots.fromAddress)!''}" maxlength="64" class="form-control" placeholder="出发地"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="toAddress">目的地:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="toAddress" id="toAddress" value="${(spots.toAddress)!''}" maxlength="64" class="form-control" placeholder="目的地"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="travelDays">行程天数:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="travelDays" id="travelDays" value="${(spots.travelDays)!''}" maxlength="64" class="form-control" placeholder="行程天数"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="cost">平均花费:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="cost" id="cost" value="${(spots.cost)!''}" maxlength="64" class="form-control" placeholder="平均花费"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="propernessDate">适宜时间:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="propernessDate" id="propernessDate" value="${(spots.propernessDate)!''}" maxlength="64" class="form-control" placeholder="适宜时间"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="run">出行准备:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="run" id="run" value="${(spots.run)!''}" maxlength="64" class="form-control" placeholder="出行准备"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="ticketType">出行准备:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<#assign helpTypes=dicSetting.getParameterMap("travel.type") />
				<@ace.formSingleSelect options=helpTypes checkValue=(travel.type)!-1 name="ticketType" listKey="key" listValue="value"/>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="production">简介:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(spots.production)!''}" instanceName="production" inputName="production" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
		<small>
			${(dicSetting.getParameterValue("spots.production"))!""}
		</small>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="photosPath">主图:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="photosPath" id="id-input-file-1">
			</div>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="mapInfoPath">线路指南:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="mapInfoPath" id="id-input-file-2">
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
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if spots??>编辑<#else>新增</#if>景点",  active: true}]);
	$('#id-input-file-1').ace_file_input({
			no_file:'没文件 ...',
			btn_choose:'请选择文件',
			btn_change:'重新选择文件',
			droppable:false,
			onchange:null,
			thumbnail:false
		});
		$('#id-input-file-2').ace_file_input({
			no_file:'没文件 ...',
			btn_choose:'请选择文件',
			btn_change:'重新选择文件',
			droppable:false,
			onchange:null,
			thumbnail:false
		});
	country.regist4Select(${(spots.areaCode)!-1},"provinceSelect","citySelect","countySelect");
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			areaCode: {
				required: true
			},
			spotsName: {
					required: true
			},
			fromAddress: {
					required: true
			},
			toAddress: {
					required: true
			},
			ticketType: {
					required: true
			}
		},
	
		messages: {
			
			"spotsName":"请输入景点名",
			"fromAddress":"请输入出发地",
			"toAddress":"请输入目的地",
			"areaCode":"请选择景点地址",
			"ticketType":"请选择门票方式"
			
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
	function addCode(){
		$("#countySelect").val();
	}
</script>