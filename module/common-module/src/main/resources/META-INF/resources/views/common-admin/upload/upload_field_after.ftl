<div id="${fieldName}ModalTable" class="modal fade" tabindex="-1">
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
</div></div>
<script>
RP.mFileUploads = RP.mFileUploads || {};
RP.mFileUploads['${fieldName}'] = Object.create(RP.FileUpload);
$(document).ready(function(){
	RP.mFileUploads['${fieldName}'].setting('${fieldNameValue}', '${fieldName}', '${(isSingle)!"0"}');
});
</script>