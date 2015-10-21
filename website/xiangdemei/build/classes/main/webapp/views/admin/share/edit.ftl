<title><#if share??>编辑<#else>新增</#if> 分享模板</title>
<div class="page-header">
	<h1>
		<#if share??>编辑<#else>新增</#if>分享模板
		<small class="red">
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/goods/dosave" enctype="multipart/form-data">
<#if share??>
	<input type="hidden" name="id" value="${share.id}"/>
</#if>
<input type="hidden" name="ext" id="ext" value='${(goods.ext)!}'/>
<fieldset>


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">类别:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
		<@admin_perm cmd="ad_role_list">
			<@ace.formSingleSelect options=r_list checkValue=(adminUser.adminRole.id)!-1 name="adminRole.id" listKey="id" listValue="name"/>
		</@admin_perm>
		</div>
	</div>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">标题:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="name" id="name" value="${(goods.name)!''}" class="form-control" placeholder="名字"/>
				<i class="icon-user"></i>
			</span>
		</div>
	</div>
</div>

<#assign fieldName="thumbImg" />
<#assign fieldLabel="封面图" />
<#include "/common-admin/upload/upload_field_pre.ftl" />


<#assign fieldName="attrsDescImgs" />
<#assign fieldLabel="属性描述图片" />
<#include "/common-admin/upload/upload_field_pre.ftl" />


<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="stockNumber">描述:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				<input type="text" name="stockNumber" id="stockNumber" value="${(goods.stockNumber)!'-1'}" class="form-control" placeholder="商品库存"/>
				<i class="icon-user"></i>
			</span>
			
			<small class="red">* -1 没有限制</small>
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

<#assign fieldName="thumbImg" />
<#assign rootPath="resource/goodsThumbImg/" />
<#assign fieldNameValue=(goods.thumbImg)!"" />
<#assign isSingle=1 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="imgs" />
<#assign rootPath="resource/goodsImg/" />
<#assign fieldNameValue=(goods.imgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="attrsDescImgs" />
<#assign rootPath="resource/goodsAttrDescImg/" />
<#assign fieldNameValue=(goods.attrsDescImgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<#assign fieldName="descImgs" />
<#assign rootPath="resource/goodsDescImg/" />
<#assign fieldNameValue=(goods.descImgs)!"" />
<#assign isSingle=0 />
<#include "/common-admin/upload/upload_field_after.ftl" />

<div id="attrModalTable" class="modal fade" tabindex="1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">×</span>
					</button>
					编辑规格
				</div>
			</div>

			<div class="modal-body no-padding">
				<table id="modalOptionTable" name="modalOptionTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
					<thead>
						<tr>
							<th>名称</th>
							<th>子规格(请用|分割)</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				
				<div class="modal-footer">
					<button type="button" class="btn btn-sm btn-default" onclick="addEmptyTr();">
						增加
					</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="emptyTr();">
						重置
					</button>
					<button type="button" class="btn btn-sm btn-primary" data-dismiss="modal">
						提交更改
					</button>
				</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
<script>

function fillGoodsFormat(goodsFormat){
	var goodsFormatSelect = $("#goodsFormat");
	goodsFormatSelect.empty();
	
	for(var idx in goodsFormat) {
		var goodsFormatJson = goodsFormat[idx];
		var option = "<option value='" + goodsFormatJson.title +"' selected='selected'>" + goodsFormatJson.title +"</option>";
		goodsFormatSelect.append(option);
	}
	
	var jsonString = JSON.stringify(goodsFormat);
	
	$("#ext").val(jsonString);
	$('.chosen-select').chosen().trigger("chosen:updated");
}

function addEmptyTr() {
	$('#attrModalTable tbody').append("<tr><td>请填写</td><td>请填写详细数据</td></tr>");
	$('#modalOptionTable').editableTableWidget();
}

function emptyTr(){
	$('#attrModalTable tbody').empty();
	$('#modalOptionTable').editableTableWidget();
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础"}, {name:"<#if goods??>编辑<#else>新增</#if>分享模板",  active: true}]);
	var extValue = $("#ext").val();
	if(StringUtils.isNotBlank(extValue)) {
		extValue = JSON.parse(extValue);
	} else {
		extValue = [];
	}
	fillGoodsFormat(extValue);
	
	$('#attrModalTable').on('show.bs.modal', function () {
		var extValue = $("#ext").val();
		if(StringUtils.isNotBlank(extValue)) {
			extValue = JSON.parse(extValue);
		} else {
			extValue = [];
		}
		var tbody = $('#attrModalTable tbody');
		tbody.empty();
		
		for(var idx in extValue) {
			var json = extValue[idx];
			var listString = "";
			for(var j in json.list) {
				listString += json.list[j];
				
				if(j < json.list.length - 1) {
					listString += "|";
				}
			}
			var tr = "<tr><td>" + json.title + "</td><td>" + listString +"</td></tr>";
			tbody.append(tr);
		}
		
		$('#modalOptionTable').editableTableWidget();
	});
	
	$('#attrModalTable').on('hide.bs.modal', function () {
		//读取 options
		var options = [];
		$("#modalOptionTable tbody tr").each(function(){
			var children = $(this).children();
			var option = {};
			var list2 = $(children[1]).text().split("|");
			var list = [];
			
			for(var idx in list2) {
				if(StringUtils.isNotBlank(list2[idx])) {
					list.push(list2[idx]);
				}
			}
			option.title = $(children[0]).text();
			option.list = list;
			
			options.push(option);
		});
		
		fillGoodsFormat(options);
	});
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			name: {
				required: true
			},
			salesNumber: {
				required: true,
				number:true
			},
			
			money: {
				required: true,
				number:true
			},
			
			discountMoney: {
				required: true,
				number:true
			},
			oneLevelMoney: {
				required: true,
				number:true
			},
			twoLevelMoney: {
				required: true,
				number:true
			},
			rootLevelMonry: {
				required: true,
				number:true
			},
			sortIndex: {
				required: true,
				number:true
			},
			startTimeString:{
				required: true
			},
			endTimeString:{
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