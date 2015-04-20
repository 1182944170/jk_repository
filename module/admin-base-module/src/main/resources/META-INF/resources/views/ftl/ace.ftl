<#ftl strip_whitespace=true>
<#assign commonStateOptions = [{"value": 1, "valueString":"启用","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"禁用","labClass":"red","inputClass":"ace"}]/>
<#assign commonStateOptionsYN = [{"value": 1, "valueString":"是","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"否","labClass":"red","inputClass":"ace"}]/>
<#assign commonOptions = [{"value": 1, "valueString":"Yes","labClass":"blue","inputClass":"ace"}, {"value": 0, "valueString":"No","labClass":"red","inputClass":"ace"}]/>
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

<#macro formSingleSelect options checkValue name="_formSingleSelectDefaultName_" listKey="id" listValue="value" attributes="">
    <select id="${name}" name="${name}" ${attributes}>
    	<option value <#if checkValue?is_string> <#if "-1" == checkValue> checked="checked"</#if> <#else><#if -1 == checkValue> checked="checked"</#if></#if> >--please chooice--</option>
        <#list options as value>
        <option value="${value[listKey]?html}" <#if value[listKey] == checkValue> selected="selected"</#if> >${value[listValue]?html}</option>
        </#list>
    </select>
</#macro>