<#ftl strip_whitespace=true>
<#assign commonStateOptions = [{"value": 1, "valueString":"启用","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"禁用","labClass":"red","inputClass":"ace"}]/>
<#assign commonStateOptionsYN = [{"value": 1, "valueString":"是","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"否","labClass":"red","inputClass":"ace"}]/>
<#assign commonOptions = [{"value": 1, "valueString":"Yes","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"No","labClass":"red","inputClass":"ace"}]/>
<#assign genderOptions = [{"value": 1, "valueString":"男","labClass":"blue","inputClass":"ace"}, {"value": 2, "valueString":"女","labClass":"red","inputClass":"ace"}]/>
<#--
 *
-->
<#macro radioGroup options checkValue name="_radioGroupDefaultName_" isWrap=true attributes="">
<#list options as value>
	<#if isWrap><div></#if>
	<label class="${value.labClass}">
		<input  type="radio" name="${name}" id="${name}" value="${value.value?html}" class="${value.inputClass}" <#if value.value == checkValue> checked="checked"</#if> ${attributes}/>
		<span class="lbl"> ${value.valueString?html}</span>
	</label>
	<#if isWrap></div></#if>
</#list>
</#macro>

<#macro checkSelected stringStatusValue value>
	<#if stringStatusValue?? && value??>
		<#if stringStatusValue?is_number && stringStatusValue == value?number>selected="selected"</#if>
    	<#if stringStatusValue?is_string && stringStatusValue == value>selected="selected"</#if>
	<#else>
	</#if>
</#macro>

<#macro checkMultiSelected stringStatusValues value listKey listValue>
	<#if stringStatusValues?has_content>
		<#list stringStatusValues as stringStatusValue>
			<#if stringStatusValue?? && value??>
				<@deepGetValue stringStatusValue listKey />
				<#if _deep_value?is_number && _deep_value == value?number>selected="selected"</#if>
		    	<#if _deep_value?is_string && _deep_value == value>selected="selected"</#if>
			<#else>
			</#if>
		</#list>
	</#if>
	
</#macro>

<#macro deepGetValue root key>
	<#if key?index_of('.') gt -1>
		<#list key?split('.') as x>
			<#if x_index == 0>
				<#assign _deep_value=root[x]!>
			<#else>
				<#assign _deep_value=_deep_value[x]!>
			</#if>
		</#list>
		
	<#else>
		<#assign _deep_value=root[key]!>
	</#if>	
</#macro>

<#macro formSingleSelect options checkValue name="_formSingleSelectDefaultName_" listKey="id" listValue="value" attributes="">
    <select id="${name}" name="${name}" ${attributes}>
    	<option value="-1" <@checkSelected checkValue "-1"/> >--please chooice--</option>
    	
    	<#if options?is_hash>
            <#list options?keys as value>
             <option value="${value?html}" <@checkSelected checkValue value/> >${options[value]?html}</option>
            </#list>
        <#else> 
	        <#list options as value>
	        <@deepGetValue value listKey/>
	        <option value="${_deep_value?html}" <@checkSelected checkValue _deep_value/> > <@deepGetValue value listValue/> ${_deep_value?html}</option>
	        </#list>
        </#if>
    </select>
</#macro>
<#macro formMultiSelect options checkValues name="_formMultiSelectDefaultName_" listKey="id" listValue="value" attributes="">
    <select id="${name}" name="${name}" multiple="multiple" ${attributes} class="chosen-select">
    	<option value="-1" <#if !checkValues?has_content>selected="selected"</#if> >--please chooice--</option>
    	<#if options?is_hash>
            <#list options?keys as value>
             <option value="${value?html}" <@checkMultiSelected checkValues value listKey listValue/> >${options[value]?html}</option>
            </#list>
        <#else> 
	        <#list options as value>
	        <@deepGetValue value listKey/>
	        <option value="${_deep_value?html}" <@checkMultiSelected checkValues _deep_value listKey listValue/> ><@deepGetValue value listValue/> ${_deep_value?html}</option>
	        </#list>
        </#if>
    </select>
</#macro>