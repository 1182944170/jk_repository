var RP = RP || {}
RP.MultipleFile = {
	commonDel:function(aId, imgValue, formImgValueName, addFileName, isArray){
		$("#" + aId).closest(".widget-box").hide();
		var formImgValueObj = $("[name='"+formImgValueName+"']");
		var succ = false;
		if(isArray) {
			try{
				var valueArray = JSON.parse(formImgValueObj.val());
				for(var idx in valueArray) {
					if(valueArray[idx] == imgValue) {
						succ = true;
						valueArray.splice(idx, 1);
						//delete valueArray[idx];
					}
				}
				
				formImgValueObj.val(JSON.stringify(valueArray));
			} catch(e){
				throw e;
			}
			
		} else {
			formImgValueObj.val("");
			succ = true;
		}
		
		if(succ) {
			/**$("#" + addFileName).closest(".ace-file-input").append('<input type="file" name="'+addFileName+'" id="'+addFileName+'" />');
			$('[id='+addFileName+']').ace_file_input({
				no_file:'没有选择图片 ...',
				btn_choose:'选择图片',
				btn_change:'重新选择图片',
				droppable:false,
				onchange:null,
				thumbnail:false
			});*/
		}
	}	
}
RP.Form = {
		invalidHandler: function (event, validator) { //display error alert on form submit   
			
		},

		highlight: function (e) {
			$(e).closest('.form-group').removeClass('has-info').addClass('has-error');
		},

		errorPlacement: function (error, element) {
			if(element.is(':checkbox') || element.is(':radio')) {
				var controls = element.closest('div[class*="col-"]');
				if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
				else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
			}
			else if(element.is('.select2')) {
				error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
			}
			else if(element.is('.chosen-select')) {
				error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
			}
			else error.insertAfter(element.parent());
		},

		success: function (e) {
			$(e).closest('.form-group').removeClass('has-error').addClass('has-info');
			$(e).remove();
		},

		submitHandler: function(form) { form.submit(); }
}

RP.addBreadcrumb = function(param){
	var breadcrumb = $("#breadcrumbs ul");
	var arr = null;
	if(param instanceof Array) {
		arr = param;
	} else {
		arr = [];
		arr.push(param);
	}
	
	for(var idx in arr) {
		var vo = arr[idx];
		if(vo.active) {
			breadcrumb.append('<li>'+vo.name+'</li>');
		} else {
			breadcrumb.append('<li class="active"><a href="'+ (vo.linkUrl ? vo.linkUrl : "#") +'">'+vo.name+'</a></li>');
		}
	}
}
