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


RP.FileUpload = {
	uploadFileData: null,
	formFieldName: null,
	thumbnails: null,
	modalTable: null,
	formField:null,
	isSingle:false,
	
	fillThumbnails: function() {
		this.thumbnails.empty();
		for(var idx in this.uploadFileData) {
			var liString = '<li><a href="' + webUrl + '/' + this.uploadFileData[idx] + '" data-rel="colorbox"><img alt="150x150" src="' + webUrl + '/' + this.uploadFileData[idx] +'" width=100/></a><div class="tools tools-bottom"><a name="removeUploadFileA" id="removeUploadFileA" data="' + this.uploadFileData[idx] +'" href="#"><i class="icon-remove red"></i></a></div></li>'
			this.thumbnails.append(liString);
		}
		
		if(this.uploadFileData.length > 0) {
			var root = this;
			$('#' + this.formFieldName + "Thumbnails a[name='removeUploadFileA']").click(function(){
				root.whenFileRemoved($(this).attr("data"));
			});
		}
		
		
		if(this.isSingle) {
			if(this.uploadFileData.length > 0) {
				this.formField.val(this.uploadFileData[0]);
			} else {
				this.formField.val("");
			}
		} else {
			this.formField.val(JSON.stringify(this.uploadFileData));
		}
		
		console.log("formField ----" + this.formField.val());
	},
	
	setting: function(uploadedFileString, formFieldName,isSingle, maxFilesize) {
		this.formFieldName = formFieldName;
		this.thumbnails = $('#' + formFieldName + "Thumbnails");
		this.modalTable = $('#' + formFieldName + "ModalTable");
		this.formField = $("#" + formFieldName);
		if(StringUtils.isNotBlank(uploadedFileString)) {
			try{
				this.uploadFileData = JSON.parse(uploadedFileString);
			} catch(e) {
				this.uploadFileData = [];
				this.uploadFileData.push(uploadedFileString);
			}
			
		} else {
			this.uploadFileData = [];
		}
		this.isSingle = BooleanUtils.parseBooleanWithDefault(isSingle, false);
		this.fillThumbnails();
		maxFilesize = maxFilesize || 1;
		
		
		var root = this;
		
		this.modalTable.on('show.bs.modal', function () {
		});
	
		this.modalTable.on('hide.bs.modal', function () {
		});
		
		Dropzone.options[formFieldName + 'DropzoneForm'] = {
			paramName: "file",
			addRemoveLinks : true,
			autoProcessQueue:false,
			parallelUploads:1, //每次上传的个数
			
			init: function() {
				var myDropzone = this;
				
			 	var submitButton = document.querySelector("#" + formFieldName + "UploadBtn");
			 	submitButton.addEventListener("click", function () {//手动上传所有图片
			 		if(root.isSingle && myDropzone.getQueuedFiles().length > 1) {
			 			alert("请不要上传多个图片.")
			 		} else {
			 			if(myDropzone.getUploadingFiles().length === 0) {
			 				myDropzone.processQueue();
			 			} else {
			 				console.log("有" + myDropzone.getUploadingFiles().length +"个正在上传中....");
			 			}
			 		}
				});
			 	
			 	this.on("addedfile", function(){
					$(submitButton).removeAttr("disabled");
				});
				
				this.on("removedfile", function(file){
					if(file.xhr && file.xhr.responseText) {//file was uploaded
						var dataJson = JSON.parse(file.xhr.responseText);
						root.whenFileRemoved(dataJson.path);
					}
					if (this.getAcceptedFiles().length === 0) {
						$(submitButton).attr("disabled", true); 
					}
				});
			 	   
				this.on("complete", function(file) {
					var dataJson = JSON.parse(file.xhr.responseText);
					if(dataJson.succ) {
						root.whenFileUploaded(dataJson.path);
					}
					if (this.getUploadingFiles().length === 0 && this.getQueuedFiles().length === 0) {
						
					}
				});
			}
		}
		
		$('.ace-thumbnails [data-rel="colorbox"]').colorbox({
			reposition:true,
			scalePhotos:true,
			scrolling:false,
			previous:'<i class="icon-arrow-left"></i>',
			next:'<i class="icon-arrow-right"></i>',
			close:'&times;',
			current:'{current} of {total}',
			maxWidth:'100%',
			maxHeight:'100%',
			onOpen:function(){
				document.body.style.overflow = 'hidden';
			},
			onClosed:function(){
				document.body.style.overflow = 'auto';
			},
			onComplete:function(){
				$.colorbox.resize();
			}
		});
		$("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");
	},
	
	whenFileUploaded: function(fileName) {
		this.uploadFileData.push(fileName);
		this.fillThumbnails();
	},
	
	whenFileRemoved: function(fileName){
		 var currIdx = -1;
         for(var i=0;i<this.uploadFileData.length;i++) {
             if(this.uploadFileData[i] == fileName) {
                 currIdx = i;
                 break;
             }
         }
         
         if(currIdx > -1) {
        	 this.uploadFileData.splice(currIdx, 1); //remove
         }
         this.fillThumbnails();
	}
}