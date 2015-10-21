<title>分享赚钱</title>
<div class="hr hr-5"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">ID</th>
						<th class="center">标题</th>
						<th class="center">类型</th>
						<th class="center">状态</th>
						<th class="center"></th>
					</tr>
				</thead>
				<tbody>
				for xunhuan
				</tbody>
			</table>
		</div>
		<!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}</span>
	</h4>
</#if>


<script>
function fromSearch(f){
	return true;
}

$(document).ready(function(){
	RP.addBreadcrumb([{name:"分享赚钱", active: true}]);

	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/share/add"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增分享模板</span></a>');
	
});
</script>