<title>分类列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>影院名称</th>
						<th>地址</th>
						<th>联系电话</th>
						<th width="18%">特色</th>
						<th>影院图片</th>
						<th>评分</th>
						<th>是否推荐</th>
						<th>内容</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				
					<tr>
						<td><span class="green center">1</span></td>
						<td><span class="gray center">2</span></td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
						<td>6</td>
						<td>7</td>
						<td>
								<span class="label label-sm label-warning arrowed">否</span>
						</td>
						<td>
						<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
							<a class="green" href="" alt="编辑">
								<i class="icon-pencil bigger-130"></i>
							</a>
							
							<a class="red" href="" alt="Delete">
								<i class="icon-trash bigger-130"></i> 
							</a>
						</div>

						<div class="visible-xs visible-sm hidden-md hidden-lg">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown">
									<i class="icon-caret-down icon-only bigger-120"></i>
								</button>
								<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li>
										<a href="" class="tooltip-success" data-rel="tooltip" title="" data-original-title="编辑">
											<span class="green">
												<i class="icon-edit bigger-120"></i>
											</span>
										</a>
									</li>
									<li>
										<a href="" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
											<span class="red">
												<i class="icon-trash bigger-120"></i>
											</span>
										</a>
									</li>
								</ul>
							</div>
						</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/type" />
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${errorMsg}</span>
	</h4>
</#if>

<#if infoMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer green"></i>
		<span class="pink">${infoMsg}</span>
	</h4>
</#if>
<script>
$(document).ready(function(){
	RP.addBreadcrumb([{name:"基础"}, {name:"分类列表", active: true}]);
	$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/type/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">新增分类</span></a>');
});
</script>

