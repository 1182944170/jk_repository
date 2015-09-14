<title><#if mapline??>编辑<#else>新增</#if>线路</title>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/mapline/dosave${suffix}" enctype="multipart/form-data">
<#if mapline??>
	<input type="hidden" name="id" value="${mapline.id}"/>
</#if>
<input type="hidden" name="areaCode" value=""/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">线路名称:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="title" id="title" value="${(mapline.title)!''}" maxlength="64" class="form-control" placeholder="线路名称"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">出发地:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<select class="width-10" name="provinceSelect1" id="provinceSelect1"></select>
			<select class="width-10" name="citySelect1" id="citySelect1"></select>
			<select class="width-10" name="countySelect1" id="countySelect1"></select>
		</div>
	</div>
</div>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">目的地:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<select class="width-10" name="provinceSelect2" id="provinceSelect2"></select>
			<select class="width-10" name="citySelect2" id="citySelect2"></select>
			<select class="width-10" name="countySelect2" id="countySelect2"></select>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="commendCause">推荐理由:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="commendCause" id="commendCause" value="${(mapline.commendCause)!''}" maxlength="64" class="form-control" placeholder="线路地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="budget">预算费用:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="budget" id="budget" value="${(mapline.budget)!''}" maxlength="64" class="form-control" placeholder="线路地址"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="coverPath">封面:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<div class="ace-file-input width-40">
				<input type="file" name="coverPath" id="id-input-file-1">
			</div>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="chargeKnow">费用须知:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(mapline.chargeKnow)!''}" instanceName="chargeKnow" inputName="chargeKnow" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="warmCue">温馨提示:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix  width-80">
			<@fck value="${(mapline.warmCue)!''}" instanceName="warmCue" inputName="warmCue" height="300px;" toolbarSet="Basic">
		    	${fck_body}
		    </@fck>
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
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if mapline??>编辑<#else>新增</#if>线路",  active: true}]);
	$('#id-input-file-1').ace_file_input({
		no_file:'没文件 ...',
		btn_choose:'请选择文件',
		btn_change:'重新选择文件',
		droppable:false,
		onchange:null,
		thumbnail:false
	});
	country.regist4Select(${(mapline.goAddress)!-1},"provinceSelect1","citySelect1","countySelect1");
	country.regist4Select(${(mapline.toAddress)!-1},"provinceSelect2","citySelect2","countySelect2");
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			title: {
				required: true
			},
			toAddress: {
					required: true
			},
			goAddress: {
					required: true
			},
			commendCause: {
					required: true
			},
			budget: {
					required: true
			},chargeKnow: {
					required: true
			},warmCue: {
					required: true
			}
		},
	
		messages: {
			
			"title":"请输入线路名",
			"goAddress":"请输入出发地",
			"toAddress":"请输入目的地",
			"commendCause":"请填写推荐理由",
			"budget":"请填写费用预算",
			"chargeKnow":"请填写费用须知",
			"warmCue":"请填写温馨提示"
			
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