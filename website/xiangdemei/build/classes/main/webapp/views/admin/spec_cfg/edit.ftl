<title><#if specCfg??>编辑<#else>新增</#if> 规格</title>
<div class="page-header">
	<h1>
		<#if specCfg??>编辑<#else>新增</#if>规格
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/spec_cfg/dosave${suffix}" enctype="multipart/form-data">
<#if specCfg??>
</#if>

<input type="hidden" name="subSpecOptions"/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="code">别名:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="code" id="code" value="${(specCfg.code)!''}" <#if specCfg??>readonly</#if> class="form-control" placeholder="别名"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">规格名字:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(specCfg.name)!''}" class="form-control" placeholder="规格名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">子规格:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<table id="modalOptionTable" name="modalOptionTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
					<thead>
						<tr>
							<th>子规格代码</th>
							<th>子规格名称</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="hr hr-5"></div>
				<button type="button" class="btn btn-sm btn-danger" onclick="addEmptyTr();">
					增加
				</button>
				<!--<button type="button" class="btn btn-sm btn-danger" onclick="emptyTr();">
					重置
				</button> -->
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

var subOptions = [];
<#if specCfg?? && specCfg.subSpecCfgs?has_content>
	<#list specCfg.subSpecCfgs as ssc>
		var json = {};
		json.code = '${ssc.code}';
		json.name = '${ssc.name}';
		
		subOptions.push(json);
	</#list>
</#if>
	
function addEmptyTr() {
	$('#modalOptionTable tbody').append("<tr><td>代码</td><td>名称</td></tr>");
	$('#modalOptionTable').editableTableWidget();
}

function emptyTr(){
	//$('#modalOptionTable tbody').empty();
	//$('#modalOptionTable').editableTableWidget();
}

$(document).ready(function(){
	
	var tbody = $('#modalOptionTable tbody');
	if(subOptions.length > 0) {
		for(var idx in subOptions) {
			var tr = "<tr><td>" + subOptions[idx].code + "</td><td>" + subOptions[idx].name +"</td></tr>";
			tbody.append(tr);
		}
	}
	$('#modalOptionTable').editableTableWidget();
	
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if specCfg??>编辑<#else>新增</#if>规格",  active: true}]);
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
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
			var options = [];
			$("#modalOptionTable tbody tr").each(function(){
				var children = $(this).children();
				var option = {};
				
				option.code = $(children[0]).text();
				option.name = $(children[1]).text();
				
				options.push(option);
			});
		
			form.subSpecOptions.value = JSON.stringify(options);
			RP.Form.submitHandler(form);
		}
	});
});
</script>