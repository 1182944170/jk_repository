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
					<img id="avatar" class="editable img-responsive" alt="${user.realName}" src="${tagUtils.getFileFullPath(user.headImg!)}" />
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
						<a class="btn btn-link" href="${ctx}/admin/user/${user.id}/reset_pwd${suffix}">
							<i class="icon-globe bigger-120 red"></i>
							重置密码
						</a>
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
					<div class="profile-info-name"> 状态 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">
							<#if user.state == 0>
								<span class="label label-sm label-warning arrowed">禁用状态</span>
							<#elseif user.state == 1>
								<span class="label label-sm label-success arrowed">正常状态</span>
							<#else>
							</#if>
						</span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name"> 性别 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username"><#if user.sex == 1>男<#else>女</#if></span>
					</div>
				</div>
				<div class="profile-info-row">
					<div class="profile-info-name">注册地区</div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">${commonTag.getCountyPath(user.countyCode)}</span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 金额信息 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">
							当前金额: ${user.money} <br/>
							冻结金额: ${user.freezeMoney} <br/>
							已经使用金额: ${user.usedMoney} <br/>
							
							总金额: ${user.money + user.freezeMoney + user.usedMoney} <br/>
							
							<a class="orange" href="${ctx}/admin/user_money_log/list${suffix}?pager=1_contact--${user.contact}">查看明细</a>
						</span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 积分人缘信息 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">
							当前积分: ${user.score} <br/>
							已经使用积分: ${user.usedScore} <br/>
							
							总积分: ${user.score + user.usedScore} <br/>
							
							<a class="orange" href="${ctx}/admin/user_score_log/list${suffix}?pager=1_contact--${user.contact}">查看明细</a>
						</span>
					</div>
				</div>
				
				<div class="profile-info-row">
					<div class="profile-info-name"> 注册信息 </div>
	
					<div class="profile-info-value">
						<span class="editable" id="username">
							注册时间:${tagUtils.formatDate(user.recordCreateTime)} </br>
							登陆时间:${tagUtils.formatDate(user.loginTime!)} </br>
							登陆IP:${user.loginIp!} </br>
							上一次登陆时间:${tagUtils.formatDate(user.lastLoginTime!)} </br>
							上一次登陆IP:${user.lastLoginIp!} </br>
						</span>
					</div>
				</div>
				
			</div>

		</div>
	</div>
</div>