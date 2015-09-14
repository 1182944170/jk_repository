<title>用户列表</title>
<form class="form-horizontal" role="form" id="validation-form" method="POST" action="${ctx}/admin/user/list" onsubmit="return fromSearch(this)">
	<input type="hidden" name="pager" value="1_"/>
	<input type="hidden" name="state" value="${state!}"/>
	<label>会员手机号:</label>
	<input type="text" name="telphone" value="${telphone!''}" placeholder="会员手机号"/>
	
	<label>会员姓名:</label>
	<input type="text" name="userName" value="${userName!''}" placeholder="会员姓名"/>
	<button class="btn btn-minier btn-success" type="submit"><i class="icon-search"></i>搜  索</button>
</form>
</br>
<table>
	<tr>
		<th width="70px;"> <a href="${ctx}/admin/user/list${suffix}?state=1" > 所有用户 </a> </th>
		<th width="70px;"> <a href="${ctx}/admin/user/list${suffix}?state=2" > 黑名单 </a> </th>
	</tr>
</table>

<div class="hr hr-5 dotted"></div>
<div class="row">
	<div class="col-xs-12">
		<div class="table-responsive">
			<table id="sample-table-1" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>手机号码</th>
						<th>拥有角色</th>
						<th>状态</th>
						<th>注册时间</th>
						<th> 操作 </th>
					</tr>
				</thead>
				<tbody>
				<#list userPager.itemList as u>
					<tr>
						<td><span class="green center">${u.id}</span></td>
						<td><span class="green center">${u.userName!}</span></td>
						<td><span class="green center">${u.telphone!}</span></td>
						<td>
							<#if u.state == 1>
								《会员》 - 
								<#if u.isGuide == 1> <b>《导游》</b> - </#if>
								<#if u.isCar == 1> <b>《司机》</b> - </#if>
								<#if u.isFood == 1> <b>《美食》</b> - </#if>
								<#if u.isHotel == 1> <b>《酒店》</b> - </#if>
								<#if u.isArt == 1> <b>《手工》</b> </#if>
							<#else>
								
							</#if>
						</td>
						<td>
							<#if u.state == 1>
								<span class="label label-sm label-success arrowed">正常</span>
							<#else>
								<span class="label label-sm label-warning arrowed">黑名单</span>
							</#if>
						</td>
						<td>${tagUtils.formatDate(u.createTime)!}</td>
						<td>
							<#if u.state==1>
								<span class="label label-sm label-success arrowed">
									<a href="${ctx}/admin/user/update-${u.id}${suffix}" onclick="return confirm('确定将《${u.userName!}》拉黑吗？拉黑后所有的店铺将清空');" style="text-decoration : none; font-size : 100%; color:#F1F1F1;">拉黑</a>
								</span>
							<#elseif u.state==2>		 
								<span class="label label-sm label-warning arrowed">
									<a href="${ctx}/admin/user/update-${u.id}${suffix}" onclick="return confirm('确定将《${u.userName!}》恢复正常状态吗');" style="text-decoration : none; font-size : 135%; color:#F1F1F1;">恢复</a>
								</span>
							</#if>
						</td>
					</tr>
				</#list>
				</tbody>
			</table>
		</div><!-- /.table-responsive -->
		
		<div class="hr hr-18 dotted hr-double"></div>
		<@h.page pager=userPager action="${ctx}/admin/user/list${suffix}" />
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
	$(document).ready(function() {
		RP.addBreadcrumb([{name:"用户列表"}]);
	});
</script>

