<title>行程计划列表</title>
<script>
	function doSearch(){
		alert("查询");
	}
	$(document).ready(function() {
		RP.addBreadcrumb([{name:"列表"}]);
	});
</script>
<table>
	<tr>
	<th>
	模糊查询 : 
	<input type="text" class="width:180px;height:20px;"/><span><img src="${ctx}/resources/images/search.gif" onclick="doSearch();"></span>	
	</th>
	</tr>
</table><p></p>

<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>第几天</th>
						<th>相关图片</th>
						<th>当日详情</th>
						<th>创建时间</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				<#list travelPlanPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.daySort!}</span></td>
						<td><span class="green center">${u.dayPhoto!}</span></td>
						<td><span class="green center">${u.dayInfo!}</span></td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td><span class="green center">${u.memo!}</span></td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=travelPlanPager action="${ctx}/admin/order/list${suffix}" />
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


