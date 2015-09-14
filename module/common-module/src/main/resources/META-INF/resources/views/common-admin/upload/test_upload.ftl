<#assign fieldName="m" />
<#assign fieldLabel="Test标题" />

<#assign fieldNameValue='["resources/scoreshop/79780811登录和注册.jpg","resources/scoreshop/79780811登录和注册.jpg","resources/scoreshop/79780811登录和注册.jpg"]' />
<#assign rootPath="resources/test/" />
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right">${fieldLabel}:</label>
	<input type="hidden" name="${fieldName}" id="${fieldName}" />
	<div class="col-xs-12 col-sm-9 width-60">
		<div class="row-fluid">
			<ul class="ace-thumbnails" name="${fieldName}Thumbnails" id="${fieldName}Thumbnails">
			</ul>
		</div>
		<div><a class="green" href="#${fieldName}ModalTable" data-toggle="modal">[添加图片]</a></div>
	</div>
</div>

<div id="${fieldName}ModalTable" name="${fieldName}ModalTable" class="modal fade" tabindex="-1" aria-hidden="true" style="display: none;">
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
					<form action="${ctx}/admin/upload/uploadimg" id="${fieldName}DropzoneForm" class="dropzone" enctype="multipart/form-data">
						<input type="hidden" name="rootPath" value="${rootPath}" />
					</form>
				</div>
				
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn btn-sm btn-default">
						上传完毕
					</button>
					<button type="button" name="${fieldName}UploadBtn" id="${fieldName}UploadBtn" class="btn btn-sm btn-danger">
						上 传
					</button>
				</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div>
<script>
RP.mFileUploads = RP.mFileUploads || {};
RP.mFileUploads['${fieldName}'] = Object.create(RP.FileUpload);
$(document).ready(function(){
	RP.addBreadcrumb([{name:"upload",  active: true}]);
	RP.mFileUploads['${fieldName}'].setting('${fieldNameValue}', 'm');
});
</script>