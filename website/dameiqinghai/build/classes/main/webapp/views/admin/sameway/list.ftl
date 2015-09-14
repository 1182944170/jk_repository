<title>同游列表</title>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>同游类型</th>
						<th>出发地点</th>
						<th>目标地点</th>
						<th>出行方式</th>
						<th>同游人数</th>
						<th>是否推荐</th>
						<th>联系方式</th>
						<th>状态</th>
						<th>发布时间</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				<#list sameWayPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id!}</span></td>
						<td><span class="green center">${u.user.userName!}</span></td>
						<td>${u.startTime!}</td>
						<td>${u.endTime!}</td>
						<td><span class="green center">${u.wayType!}</span></td>
						<td><span class="green center">${u.goAddress!}</span></td>
						<td><span class="green center">${u.toAddress!}</span></td>
						<td><span class="green center">${u.rentType!}</span></td>
						<td><span class="green center">${u.wayNumber!}</span></td>
						<td>
							<#if u.isCommend==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/sameway/commend-${u.id}${suffix}" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">已推荐</a>
								</span>
							<#else>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/sameway/commend-${u.id}${suffix}"  style="text-decoration : none; font-size : 135%; color:#F1F1F1;">推荐</a>
								</span>
							</#if>
						</td>
						<td><span class="green center">${u.contact!}</span></td>
						<td><span class="green center">${u.state!}</span></td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td><span class="green center">${u.memo!}</span></td>
					</tr>
				</#list>
				<div class="hr hr-18 dotted hr-double"></div>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
			<@h.page pager=sameWayPager action="${ctx}/admin/order/list${suffix}" />
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


