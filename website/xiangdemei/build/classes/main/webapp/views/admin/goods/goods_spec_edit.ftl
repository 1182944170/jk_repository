
<title>编辑 ${goods.name} 规格</title>
<div class="page-header">
	<h1>
		编辑 ${goods.name} 规格
		<small class="red">
			<i class="icon-double-angle-right"></i>
		</small>
		
		<a class="orange" href="${ctx}/admin/goods/${goods.id}/spec_detail_log${suffix}" alt="查看入库日志">
			<i class="icon-pencil bigger-130"></i>查看入库日志
		</a>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/goods/${goods.id}/save_spec${suffix}" enctype="multipart/form-data">
<input type="hidden" name="goodsId" value="${goods.id}"/>
<input type="hidden" name="goodsSpecs"/>
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">商品规格:</label>
		<@xdm cmd="spec_cfg_list">
		<div class="col-sm-6">
			<div class="tabbable">
				<ul class="nav nav-tabs">
					<#list m_list as spec>
					<li class=" <#if spec_index==0> active </#if>">
						<a data-toggle="tab" href="#specCfg${spec_index}">
							${spec.name}
							<span class="badge badge-danger">${spec.subSpecCfgs?size}</span>
						</a>
					</li>
					</#list>
				</ul>
		
				<div class="tab-content">
					<#list m_list as spec>
					<div id="specCfg${spec_index}" class="tab-pane <#if spec_index==0> active </#if>">
						<#list spec.subSpecCfgs as specCfg>
							<label>
								<input name="subSpecCheckboxName" value="${specCfg.code}" valueString="${specCfg.name}" parent-name="${spec.name}" parent-code="${spec.code}" type="checkbox" /> ${specCfg.name}
							</label>
						</#list>
					</div>
					</#list>
				</div>
				
			</div>
		</div><!-- /span -->
	</@xdm>
</div>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">子规格:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-80">
				<table id="modalOptionTable" name="modalOptionTable" class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
					<thead>
					</thead>
					<tbody>
					</tbody>
				</table>
				<div class="hr hr-5"></div>
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
RP.GoodsSpec = {
	//{f1,f2,f3,f4,f5}
	rowDatas:null,
	chooseDataArray:null,
	subSpecCheckboxName: null,
	specCheckArr: null,
	
	setting: function(subSpecCheckboxName, modalOptionTable){
		this.subSpecCheckboxName = subSpecCheckboxName;
		this.modalOptionTable = $("[name='" + this.modalOptionTable + "']");
		var parent = this;
		$("[name='" + this.subSpecCheckboxName + "']").on("click", function(){
			parent.whenChooseSpec();
		});
	},
	
	whenChooseSpec: function() {
		this.specCheckArr = [];
		var parent = this;
		$("[name='" + this.subSpecCheckboxName + "']:checked").each(function(){
			var jThis = $(this);
			var parent_code = jThis.attr("parent-code");
			var parent_name = jThis.attr("parent-name");
			var code = jThis.attr("value");
			var valueString = jThis.attr("valueString");
			
			
			var specObj = parent.hasSpecData(parent_code);
			if(!specObj) {
				specObj = {code: parent_code, name: parent_name, list:[]};
				parent.addSpec(specObj);
			} else {
				
			}
			
			specObj.list.push({code: code, name: valueString});
		});
		
		console.log(JSON.stringify(this.specCheckArr));
		
		this.renderTable();
	},
	
	addSpec: function(specObj){
		this.specCheckArr.push(specObj);//排序
		
		this.specCheckArr.sort(function(o1, o2){
			return o1.code.localeCompare(o2.code);
		});
	},
	
	hasSpecData: function(code){
		for(var idx in this.specCheckArr) {
			if(this.specCheckArr[idx].code == code) {
				return this.specCheckArr[idx];
			}
		}
		
		return null;
	},
	
	buildRowData: function(num, logisticsNo, f1, f2){
		var vo = {};
		vo.f1 = f1;
		if(f2) {
			vo.f2 = f2;
		}
		
		vo.num = num;
		vo.logisticsNo = logisticsNo;
		
		return vo;
	},
	
	renderTable: function() {
		$('#modalOptionTable thead').empty()
		$('#modalOptionTable tbody').empty();
		
		//确认列数
		var columnCount = this.specCheckArr.length;
		if(columnCount < 1) {//列数小于1
			return;
		}
		
		if(this.chooseDataArray && this.chooseDataArray.length != columnCount) {
			//列数变了
			
		}
		//确认需要渲染的行数
		var rowCount = 1;
		for(var idx in this.specCheckArr) {
			rowCount *= this.specCheckArr[idx].list.length;
		}
		console.log("需要渲染的行数:" + rowCount);
		
		var newChooseData = [];
		this.iterationPackageChooseData(0, columnCount - 1,newChooseData);
		console.log(JSON.stringify(newChooseData));//构建一个横向的树形结构
		
		//动态添加 table 头
		var thString = "<tr>";
		for(var idx in this.specCheckArr) {
			thString += "<th>" + this.specCheckArr[idx].name  +"</th>"
		}
		thString += "<th>数量</th><th>条形编码</th></tr>";
		
		$('#modalOptionTable thead').append(thString);
		var tbody = $('#modalOptionTable tbody');
		
		var newRowDatas = [];
		for(var idx0 in newChooseData) {
			var obj0 = newChooseData[idx0];
			if(obj0.list) {
				
				for(var idx1 in obj0.list) {
					var obj1 = obj0.list[idx1];
					if(obj1.list) {
						//暂无二级以上的处理
						alert("暂无二级以上的处理");
						return;
					} else {//二级处理
						var vo = this.buildRowData(0, "", this._copySimpleSubSpec(obj0), this._copySimpleSubSpec(obj1));
						tbody.append(this.buildRowString(vo));
						newRowDatas.push(vo);
					}
				}
				
			} else {//只有一级
				var vo = this.buildRowData(0, "", this._copySimpleSubSpec(obj0), null);
				tbody.append(this.buildRowString(vo));
				newRowDatas.push(vo);
			}
		}
		
		this.chooseDataArray = newChooseData;
		this.rowDatas = newRowDatas;
		$('#modalOptionTable').editableTableWidget();
		
		this.getFillData();
	},
	
	_copySimpleSubSpec: function(subSpec) {
		var retO = {};
		for(var idx in subSpec) {
			if(idx != "list") {
				retO[idx] = subSpec[idx];
			}
			
		}
		
		return retO;
	},
	
	getHistoryData : function(vo) {
		if(!this.rowDatas) {
			return;
		}
		
		for(var idx in this.rowDatas) {
			var oldD = this.rowDatas[idx];
			
			if(this._isEquals(vo.f1, oldD.f1)
				&& this._isEquals(vo.f2, oldD.f2)
				&& this._isEquals(vo.f3, oldD.f3)
				&& this._isEquals(vo.f4, oldD.f4)
				&& this._isEquals(vo.f5, oldD.f5) ) {//找到之前的记录了
				
				vo.num = oldD.num;
				vo.logisticsNo = oldD.logisticsNo;
			}
		}
	},
	
	_isEquals: function(subSpescObj1, subSpescObj2){
		if(subSpescObj1 == subSpescObj2) {
			return true;
		}
		
		if(subSpescObj2 == null || subSpescObj2 == null) {
			return false;
		}
		
		return subSpescObj1.code == subSpescObj2.code;
	},
	
	buildRowString: function(vo) {
		this.getHistoryData(vo);
		var rowString = "<tr>";
		var len = this.specCheckArr.length;
		if(NullUtils.isNotNull(vo.f1)) {
			rowString += "<td>" + vo.f1.name +"</td>";
		} 
		if(NullUtils.isNotNull(vo.f2)) {
			rowString += "<td>" + vo.f2.name +"</td>";
		}
		if(NullUtils.isNotNull(vo.f3)) {
			rowString += "<td>" + vo.f3.name +"</td>";
		}
		if(NullUtils.isNotNull(vo.f4)) {
			rowString += "<td>" + vo.f4.name +"</td>";
		}
		if(NullUtils.isNotNull(vo.f5)) {
			rowString += "<td>" + vo.f5.name +"</td>";
		}
		
		rowString += "<td>" + vo.num +"</td><td>"+vo.logisticsNo+"</td></tr>";
		return rowString;
	},
		
	iterationPackageChooseData : function(currIdx, totalIndex, pData) {
		
		var targetObj = this.specCheckArr[currIdx];
		
		for(var idx in targetObj.list) {
			var obj = {pCode:targetObj.code, pName:targetObj.name}//
			obj.code = targetObj.list[idx].code;
			obj.name = targetObj.list[idx].name;
			if((currIdx + 1) <= totalIndex) {
				var nextList = [];
				this.iterationPackageChooseData(currIdx + 1, totalIndex, nextList);
				obj.list = nextList;
			}
			
			pData.push(obj);				
		}
	},
	
	getFillData: function(){
		var options = [];
		var currIdx = 0;
		var parent = this;
		$("#modalOptionTable tbody tr").each(function(){
			var children = $(this).children();
			var childrenLen = children.length;
			var option = {};
			
			var logisticsNo = $(children[childrenLen - 1]).text();
			var num = $(children[childrenLen - 2]).text();
			
			var vo = parent.rowDatas[currIdx];
			vo.logisticsNo = logisticsNo;
			vo.num = num;
			
			currIdx++;
		});
	
		console.log("fillData:--" + JSON.stringify(this.rowDatas));
		
		return this.rowDatas;
	}
}

var goodsSpec = Object.create(RP.GoodsSpec);
goodsSpec.setting("subSpecCheckboxName", "modalOptionTable");

function loopSpec() {
	goodsSpec.getFillData();
	setTimeout(loopSpec, 3000);
}

setTimeout(loopSpec, 3000);

var checkedCheckDatas = [];
<#if goodsSpecCfgs?has_content>
<#list goodsSpecCfgs as specCfg>
	<#list specCfg.subSpecCfgs as subSpecCfg>
	var j = {};
	j.pCode = '${subSpecCfg.specCfgCode}';
	j.code = '${subSpecCfg.code}';
	checkedCheckDatas.push(j);
	</#list>
</#list>	
</#if>
<#if goodsSpecDetails?has_content>
	goodsSpec.rowDatas = [];
	<#list goodsSpecDetails as d>
	var j = {};
	var specCodeField1 = '${d.specCodeField1}';
	var specCombinationField1 = '${d.specCombinationField1}';
	
	var specCodeField2 = '${d.specCodeField2}';
	var specCombinationField2 = '${d.specCombinationField2}';
	
	var obj1 = {pCode:specCodeField1, pName:"--"}//
		obj1.code = specCombinationField1;
		obj1.name = "--";
	var obj2;
	
	if(specCodeField2 && specCombinationField2) {
		obj2 = {pCode:specCodeField2, pName:"--"}//
			obj2.code = specCombinationField2;
			obj2.name = "--";
	}
	goodsSpec.rowDatas.push(goodsSpec.buildRowData(${d.virtualStockCount}, "${d.logisticsNo}", obj1, obj2));	
	</#list>	
</#if>

if(checkedCheckDatas.length > 0) {
	$("[name='subSpecCheckboxName']").each(function(){
		var jThis = $(this);
		var parent_code = jThis.attr("parent-code");
		var code = jThis.attr("value");
		
		for(var idx in checkedCheckDatas) {
			var vo = checkedCheckDatas[idx];
			
			if(vo.pCode == parent_code && vo.code == code) {//选中
				jThis.attr("checked", true);
			}
		}
	});
	
	//buildRowData(0, "", this._copySimpleSubSpec(obj0), this._copySimpleSubSpec(obj1));
	goodsSpec.whenChooseSpec();
}

$(document).ready(function() {
	RP.addBreadcrumb([{name:"基础"}, {name:"入库",  active: true}]);
	
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
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
			var datas = goodsSpec.getFillData();
			if(!datas) {
				alert("规格为空不能提交.");
				return false;
			}
		
			form.goodsSpecs.value = JSON.stringify(datas);
			RP.Form.submitHandler(form);
		}
	});
});
</script>