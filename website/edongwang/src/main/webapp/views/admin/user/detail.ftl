<title>客户信息</title>
<div class="page-header">
	<h1>
		查看用户信息
		<small>
			<i class="icon-double-angle-right"></i>
		</small>
	</h1>
</div>
<div>
	<div id="user-profile-1" class="user-profile row">
		<div class="col-xs-12 col-sm-3 center">
			<div>
				<span class="profile-picture">
					<img id="avatar" class="editable img-responsive" alt="${user.realName}" src="${tagUtils.getFileFullPath(user.headImg)}" />
				</span>

				<div class="space-4"></div>

				<div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
					<div class="inline position-relative">
						<a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
							<i class="icon-circle light-green middle"></i>
							&nbsp;
							<span class="white">${user.realName}</span>
						</a>
					</div>
				</div>
			</div>

			<div class="space-6"></div>

			<div class="profile-contact-info">
				<div class="space-6"></div>

				<div class="profile-social-links center">
					<div class="profile-contact-links align-left">
						<!--
						<a class="btn btn-link" href="${ctx}/admin/user/${user.id}/resetPwd${suffix}">
							<i class="icon-globe bigger-120 red"></i>
							重置密码
						</a>-->
					</div>
				</div>
			</div>

			<div class="hr hr12 dotted"></div>

		</div>

		<div class="col-xs-12 col-sm-9">
			<div class="center">
			</div>
			<div class="profile-user-info profile-user-info-striped">
				<div class="profile-info-row">
					<div class="profile-info-name"> 会员姓名 </div>
	
					<div class="profile-info-value">
					
						<span class="editable" id="username">
						<#if user.realName?has_content>
							${user.realName}
						<#else>
							暂无
						</#if>
						</span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> 手机号 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">${user.contact}</span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> 性别 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username"><#if user.sex == 1>先生<#else>女士</#if></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> 状态 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">
							<#if user.isSalesman == 0 >
								<#if user.userSalesman??>
									<#if user.userSalesman.state==0>
										<span class="label label-sm label-warning arrowed arrowed-righ">申请二级会员中...</span>
									<#elseif user.userSalesman.state==-1>
										<span class="label label-sm label-warning arrowed arrowed-righ">申请二级会员失败</span>
									</#if>
									[<a href="${ctx}/admin/usersalesman/${user.userSalesman.userId}/edit${suffix}">查看申请信息</a>]
								<#else>
									一级会员
								</#if>
							<#else>
								<span class="text-warning bigger-110 orange">
								二级会员[所在楼盘:${user.userSalesman.house.name}<#if user.userSalesman.isLeader==1>-该楼盘的负责人</#if>]
								</span>
							</#if>
						</span>
					</div>
				</div>
	
				<div class="profile-info-row">
					<div class="profile-info-name"> 注册时间 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">${tagUtils.formatDate(user.recordCreateTime)}</span>
					</div>
				</div>
				
				<#if userBankCard??>
					<div class="profile-info-row">
						<div class="profile-info-name"> 会员银行卡信息 </div>
		
						<div class="profile-info-value">
							<span class="editable" id="username">
							银行名：${userBankCard.cfgBank.name}</br>
							账号：${userBankCard.account}</br>
							账户名：${userBankCard.name}</br>
							开户地址：${userBankCard.address}</br>
							</span>
						</div>
					</div>
				</#if>
				
				<#if user.isSalesman == 1 >
					<div class="profile-info-row">
						<div class="profile-info-name">二级会员申请信息</div>
		
						<div class="profile-info-value">
							<span class="editable" id="username">
							申请楼盘名称：${user.userSalesman.house.name}</br>
							证件照片：<img src="${tagUtils.getFileFullPath(user.userSalesman.credentialsImg)}"/></br>
							申请时间：${tagUtils.formatDate(user.userSalesman.recordCreateTime)}</br>
							审核时间：${tagUtils.formatDate(user.userSalesman.recordModifyTime)}</br>
							<#if user.userSalesman.isLeader == 1>
								</br>该楼盘的负责人
							</#if>
							</span>
						</div>
					</div>
				<#else>
				</#if>
			</div>

		</div>
	</div>
</div>