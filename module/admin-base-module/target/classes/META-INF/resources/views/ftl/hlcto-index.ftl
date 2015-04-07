<#ftl strip_whitespace=true>
<!-- 分页 -->
<#macro page pager action="list.htm" pagerName="pager">
	<script>new showPages('${action}','${pager.totalPages?c}','${pager.totalCount?c}','${pager.pagerWebString}', '${pagerName}', '${pager.costTime}').printBaseHtml();
	</script>
</#macro>

<!-- 普通的状态解析 -->
<#macro c_state state>
	<#if state == 1>
		<font color=green>正常</font>
	<#elseif state == -1>
		<font color=red>禁用</font>
	<#elseif state == 0>
		<font color=blue>未审核</font>
	</#if>
</#macro>



