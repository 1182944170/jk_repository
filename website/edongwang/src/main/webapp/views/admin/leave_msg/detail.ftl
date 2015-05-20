<title>意见、反馈详细信息</title>
<div class="page-header">
	<h1>
		意见、反馈详细信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>

<form class="form-horizontal" role="form" id="validation-form" method="POST">
<fieldset>

<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="name">会员信息:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				${leavelMessage.userId}/${leavelMessage.user.realName}/${leavelMessage.user.contact}
			</span>
		</div>
	</div>
</div>
<div class="form-group">
	<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="remark">反馈内容:</label>
	<div class="col-xs-12 col-sm-9">
		<div class="clearfix">
			<span class="block input-icon width-40">
				${leavelMessage.message}
			</span>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"列表", linkUrl:"${ctx}/admin/leave_msg/list${suffix}"}, {name:"反馈、意见详细信息",  active: true}]);
});
</script>