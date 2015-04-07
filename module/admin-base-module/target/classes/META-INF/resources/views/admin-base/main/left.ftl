<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
	</script>

	<ul class="nav nav-list">
	<#function menuWasContain menuList uri>
		<#list menuList as menu1>
			<#if uri?index_of(menu1.linkUrl) != -1>
				<#return true>
			<#elseif menu1.children?has_content>
				 <#list menu1.children as menu2>
				 	<#if uri?index_of(menu2.linkUrl) != -1>
						<#return true>
					</#if>
				 </#list>
			</#if>
		</#list>
		
		<#return false>
	</#function>
	
	<#function menuWasContain2 menu1 uri>
		<#if uri?index_of(menu1.linkUrl) gt 0>
			<#return true>
		<#elseif menu1.children?has_content>
			 <#list menu1.children as menu2>
			 	<#if uri?index_of(menu2.linkUrl) gt 0>
					<#return true>
				</#if>
			 </#list>
		</#if>
		
		<#return false>
	</#function>
	
	<@admin_perm cmd="ad_menu_list" pId=0>
		<#assign tempURI=springMacroRequestContext.requestUri />
		<#if !menuWasContain(m_list,tempURI)>
			<#assign tempURI=springMacroRequestContext.servletContext.getAttribute("_admin_left_menupath_uri_") />
		</#if>
		<#list m_list as menu1>
		<@admin_perm uri=menu1.linkUrl>
		<li <#if menuWasContain2(menu1,tempURI)>class="active<#if menu1.children?has_content> open</#if>"</#if>>
			<a href="<#if menu1.linkUrl?has_content>${ctx}/${menu1.linkUrl}<#else>#</#if>" 
				class="<#if menu1.children?has_content>dropdown-toggle</#if>">
				
				<i class="${menu1.icon}"></i>
				<span class="menu-text"> ${menu1.menuName}</span>
				<#if menu1.children?has_content><b class="arrow icon-angle-down"></b></#if>
			</a>
			
			<#if menu1.children?has_content>
			<ul class="submenu">
				<#list menu1.children as menu2>
				<li <#if menuWasContain2(menu2,tempURI)>class="active"</#if>>
					<a href="${ctx}/${menu2.linkUrl}">
						<#if menu2.icon?has_content>
							<i class="${menu2.icon}"></i>
						<#else>
							<i class="icon-double-angle-right"></i>
						</#if>
						<i class="${menu2.icon}"></i>
						 ${menu2.menuName} 
					</a>
				</li>
				</#list>
			</ul>
			</#if>
		</li>
		</@admin_perm>
		</#list>
	</@admin_perm>
	</ul><!-- /.nav-list -->

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
	</script>
</div>