<title>活动管理</title>

</h5>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>几楼</th>
						<th>报名者</th>
						<th>电话</th>
						<th>紧急联系人</th>
						<th>紧急电话</th>
						<th>小孩</th>
						<th>成人</th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
				<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.name!}</span></td>
						<td><span class="gray center">${u.phone!}</span></td>
				        <td><span class="gray center">${u.emergencyname!}</span></td>
				        <td><span class="gray center">${u.emergencyphone!}</span></td>
				        <td><span class="gray center">${u.oldboy!}</span></td>
				        <td><span class="gray center">${u.chindenboy!}</span></td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/good/list" />
	</div><!-- /span -->
</div><!-- /row -->

