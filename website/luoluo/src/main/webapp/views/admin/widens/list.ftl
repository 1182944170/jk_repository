<title>系统信息管理</title>

<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
		<br>
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>系统标题</th>
						<th>系统图片</th>
						<th>系统内容</th>
						<th>系统时间</th>
						<th></th>
						<!--<th>logo</th>-->
					</tr>
				</thead>
				<tbody>
			<#list pager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="gray center">${u.syTitle!}</span></td>
						<th><img src="${tagUtils.getFileFullPath(u.syimages!)}" width="90px" height="40px"/></th>
						<td width:200px>
						<input type="text" value="${u.sycontent}" readonly style="overflow-x:visible;width:210px;border:0px solid #F9F9F9" >
						
						
						</td>
						<td><span class="gray center">${tagUtils.formatDate(u.sytime)}</span></td>
				
						<td>
							<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
								<a class="green" href="${ctx}/admin/windens/${u.id}/edit${suffix}" alt="Edit">
									<i class="icon-pencil bigger-130"></i>
								</a>
							<#if u.type==0>
								<a class="red" href="${ctx}/admin/windens/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定禁止用户么?');" title="允许" alt="Delete">
									<img src="${ctx}/resources/images/zhengque.jpg" width="15" height="15" alt="" />
								</a>
							<#elseif u.type==1>
								<a class="red" href="${ctx}/admin/windens/${u.id}/saveUserda${suffix}" onclick="return confirm('你确定允许用户么?');"title="禁止"alt="Delete">
									<img src="${ctx}/resources/images/jinzhi.jpg" width="15" height="15" alt="" />
								</a>
							</#if>
								
								<a class="red" href="${ctx}/admin/windens/${u.id}/deletUser${suffix}" onclick="return confirm('你确定删除么?');" alt="Delete">
									<i class="icon-trash bigger-130"></i>
								</a>
							
							</div>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=pager action="${ctx}/admin/windens/list" />
	</div><!-- /span -->
</div><!-- /row -->

<#if errorMsg??>
	<div class="hr hr-18 hr-double dotted"></div>
	<h4 class="lighter">
		<i class="icon-hand-right icon-animated-hand-pointer red"></i>
		<span class="pink">${infoMsg}</span>
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
		RP.addBreadcrumb([{name:"基础配置"}, {name:"系统消息", active: true}]);
		$("#breadcrumbs ul").append('&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/admin/windens/add${suffix}"><i class="icon-zoom-in"></i><span class="label label-warning arrowed-in arrowed-in arrowed-right">添加系统信息</span></a>');
	});
</script>
