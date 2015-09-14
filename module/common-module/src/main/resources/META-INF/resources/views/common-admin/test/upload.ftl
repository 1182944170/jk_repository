<title>upload</title>
<div class="page-header">
	<h1>
		upload
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>
						
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/common/help/dosave${suffix}">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="title">upload标题:</label>
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="row-fluid">
			<ul class="ace-thumbnails" name="mThumbnails" id="mThumbnails">
			</ul>
		</div>
		<div><a class="green" href="#mModalTable" data-toggle="modal">[添加图片]</a></div>
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
<div id="mModalTable" name="mModalTable" class="modal fade" tabindex="-1" aria-hidden="true" style="display: none;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">×</span>
					</button>
					添加图片
				</div>
			</div>

			<div class="modal-body no-padding">
				<div id="dropzone">
					<form action="${ctx}/admin/test/upload" id="mDropzoneForm" class="dropzone" enctype="multipart/form-data">
					</form>
				</div>
				
				<div class="modal-footer">
					<button type="button" name="reset" class="btn btn-sm btn-default">
						重 置
					</button>
					<button type="button" name="upload" id="mUploadBtn" class="btn btn-sm btn-danger">
						上 传
					</button>
				</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}
		</span>
	</h4>
</#if>

<script>

B.FileUpload = {
	uploadFileData: null,
	thumbnails: null,
	modalTable: null,
	
	fillThumbnails: function(){
		this.thumbnails.empty();
		for(var idx in this.uploadFileData) {
			var liString = '<li><a href="' + webUrl + '/' + this.uploadFileData[idx] + '" data-rel="colorbox"><img alt="150x150" src="' + webUrl + '/' + this.uploadFileData[idx] +'" width=100/></a><div class="tools tools-bottom"><a href="#"><i class="icon-remove red"></i></a></div></li>'
			this.thumbnails.append(liString);
		}
	},
	
	setting: function(uploadedFileString, formFieldName, maxFilesize) {
		this.thumbnails = $('#' + formFieldName + "Thumbnails");
		this.modalTable = $('#' + formFieldName + "ModalTable");
		
		if(StringUtils.isNotBlank(uploadedFileString)) {
			this.uploadFileData = JSON.parse(uploadedFileString);
		} else {
			this.uploadFileData = [];
		}
		
		this.fillThumbnails();
		maxFilesize = maxFilesize || 1;
		
		var root = this;
		
		this.modalTable.on('show.bs.modal', function () {
		});
	
		this.modalTable.on('hide.bs.modal', function () {
		});
		
		Dropzone.options[formFieldName + 'DropzoneForm'] = {
			 paramName: "files",
			addRemoveLinks : true,
			autoProcessQueue:false,
			uploadMultiple:true,
			
			init: function() {
				myDropzone = this;
				
			 	var submitButton = document.querySelector("#mUploadBtn");
			 	submitButton.addEventListener("click", function () {//手动上传所有图片
					myDropzone.processQueue();
				});
			 	   
				this.on("successmultiple", function(files, responseText, e) {
					var dataJson = JSON.parse(responseText);
					if(dataJson.succ) {
						root.whenFileUploaded(dataJson.paths);
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
	
	whenFileUploaded: function(fileNames) {
		if(fileNames instanceof Array) {
			for(var idx in fileNames) {
				this.uploadFileData.push(fileNames[idx]);
			}
		} else {
			this.uploadFileData.push(fileNames);
		}
		
		this.fillThumbnails();
	},
	
	whenFileRemoved: function(fileName){
	
	}
}
var mFileUpload = Object.create(B.FileUpload);
$(document).ready(function(){
	RP.addBreadcrumb([{name:"upload",  active: true}]);
	var sss = '["resources/scoreshop/79780811登录和注册.jpg","resources/scoreshop/79780811登录和注册.jpg","resources/scoreshop/79780811登录和注册.jpg"]';
	mFileUpload.setting(sss, 'm');
});
</script>