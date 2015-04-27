<title>控制台</title>
<div class="page-header">
	<h1>
		控制台
		<small>
			<i class="icon-double-angle-right"></i>
			 查看
		</small>
	</h1>
</div>

<div class="alert alert-block alert-success">
	<button type="button" class="close" data-dismiss="alert">
		<i class="icon-remove"></i>
	</button>

	<i class="icon-ok green"></i>

	欢迎使用
	<strong class="green">
		后台管理系统.
		<small></small>
	</strong>
</div>
<script>
	$(document).ready(function(){
		RP.addBreadcrumb({name:"控制台", linkUrl:"${ctx}/admin/main${suffix}", active: true});
	});
</script>