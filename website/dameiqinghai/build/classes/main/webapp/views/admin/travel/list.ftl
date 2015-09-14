<title>游记列表</title>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户</th>
						<th>标题</th>
						<th>出发地点</th>
						<th>游玩景点</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				<#list travelPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.user.userName!}</span></td>
						<td><span class="green center">${u.title!}</span></td>
						<td>${u.goAddress!}</td>
						<td>${u.playPlace!}</td>
						<td></td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
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


