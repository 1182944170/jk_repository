<style>
	.greenc{color:red;}
</style>
<script>
$(function(){
		changeProvince();
		init();
})
var country = Object.create(B.Country);
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础"}, {name:"添加地区",  active: true}]);
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
	country.regist4Select(${(hotarea.areaCode)!-1},"provinceSelect","citySelect","countySelect");
	$('#validation-form').validate({
		errorElement: 'div',
		errorClass: 'help-block',
		focusInvalid: true,
		rules: {
			areaCode: {
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
		}
	});
});
	function sub(o){
		var arr =new Array();
		var j = 0;
		$(".cityInfo").children().each(function(i){
			if($(this).find("input").is(':checked')){
				arr[j]=$(this).find("input[type='hidden']").val();
				j++;
			}
		})
		$.ajax({
	        async : false,
	        cache : false,
	        type : 'POST',
	        dataType :JSON,
	        url : "${ctx}/admin/hotarea/add/?arr="+arr,
	        error : function(data){
        		var str = data.responseText;
        			str =str.substring(1,str.length-1);
        			str = str.replaceAll("\"","");
        		var arr = str.split(":");
        		if(arr[1]=='true'){
        			$(".subbtn").attr("disabled","disabled");
        			o.className="btn ";
        			$(".inputc").hide();
        			alert("设置推荐城市成功！");
        		}else{
        			$(".subbtn").attr("disabled","");
        			o.className="btn btn-info";
        			$(".inputc").show();
        		}
        	}
		})
	}
	function getval(o){
		$(".cityInfo").children().remove();
		if($(o).attr("disabled")==true){
			$(o).removeAttr("disabled");
		}else{
			$(o).attr("disabled","disabled");
		}
		if($(".subbtn").attr("disabled")==true){
			$(".subbtn").attr("disabled","disabled");
		}else{
			$(".subbtn").removeAttr("disabled");
		}
		var citycode = $("#citySelect").val();
		var provinceCode = $("#provinceSelect").val();
		$.ajax({
			async : false,
			cache : false,
			type : 'POST',
			dataType :JSON,
			url : "${ctx}/admin/hotarea/view/"+provinceCode,
			error : function(data){
				var arr ;
				var a;
				var city ;
				var code ;
				var name ;
				var html ="" ;
				var result = data.responseText;
				var str = result.substring(1,result.length-1);
				str = str.replaceAll("\"","");
				arr = str.split(",");
				for(i=0;i<arr.length;i++){
					a = arr[i];
					city = a.split(":");
					code = city[0];
					name = city[1];
					html = getdiv();
					$(".cityInfo").append(html);
					$(".cityInfo").children().eq(i).children().eq(0).val(code);
					$(".cityInfo").children().eq(i).children().eq(1).val(name);
				}
			}
		})
	}
	function init(){
		var areaName = "${hotarea.areaName!}";
		var html = "";
		var area = areaName.split(",");
		for(i = 0 ; i < area.length; i++){
			html = getdiv();
			$(".cityInfo").append(html);
			$(".cityInfo").children().eq(i).children().eq(1).val(area[i]);
			$(".cityInfo").children().eq(i).children().eq(1).attr("disabled","disabled");
		}
	}
	function check(o){
		if(o.checked) {
			o.checked = false
			o.className="";
		}else{
			o.checked = true;
			o.className="greenc";
		}
		$(o).prev().val();
	}
	function getdiv(){
		var div = "<div><input type=\"hidden\" value=\"\"\/><input type=\"button\" value=\"\"\ onclick=\"check(this)\"/></div>";
		return div;
	}
	function changeProvince(){
		$(".cityInfo").children().remove();
		var value = $("#provinceSelect").val(); 
		if(value=="" || value == null){
			$(".provinceClass").attr("disabled","disabled");
			$(".subbtn").attr("disabled","disabled");
		}else{
			$(".provinceClass").removeAttr("disabled");
			//$(".subbtn").removeAttr("disabled");
		}
	}
	function res(){
		location.reload();
	}
</script>
<style>
.titleB{font-size: 18px;
  color: #10b456;
  font-weight: bold}
</style>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/hotarea/dosave">
<fieldset>
	<div class="form-group inputc">
		<label style="margin-left:20%;" class="control-label col-xs-12 col-sm-3 no-padding-right">设置地址:</label>
		<div style="margin-left:20px">
			<div class="clearfix">
				<select style="margin-left:20px;width:120px;" onchange="changeProvince();" name="provinceSelect" id="provinceSelect"></select>
				<#--<select style="margin-left:20px;width:120px;" name="citySelect" id="citySelect"></select>-->
				<input type="button" class="provinceClass" value="确定" onclick="getval(this)" style="margin-left:20px"/>
			</div>
		</div>
	</div>
	<div>
		<span class="titleB" style="margin-left:50%;">所有地区</span>
		<HR style="border:3 double #987cb9" width="80%" color=#987cb9 SIZE=3>
		<div class="cityInfo" style="margin-left:50%">
		</div>
	</div>
	<div class="col-md-offset-3 col-md-9" style="margin-top:25px;" >
		<button style="margin-left:28%;" class="btn subbtn btn-info" onclick="sub(this);" type="button">
			<i class="icon-ok bigger-110"></i>
			提 交
		</button>
		&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
		<button class="btn" onclick="res();" type="button">
			<i class="icon-undo bigger-110"></i>
			重 置
		</button>
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
