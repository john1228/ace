<#ftl output_format="HTML" strip_whitespace=true>

<#macro bind path>
    <#if htmlEscape?exists>
        <#assign status = springMacroRequestContext.getBindStatus(path, htmlEscape)>
    <#else>
        <#assign status = springMacroRequestContext.getBindStatus(path)>
    </#if>
<#-- assign a temporary value, forcing a string representation for any
kind of variable. This temp value is only used in this macro lib -->
    <#if status.value?exists && status.value?is_boolean>
        <#assign stringStatusValue=status.value?string>
    <#else>
        <#assign stringStatusValue=status.value?default("")>
    </#if>
</#macro>

<#macro formRadioButtons path options attributes="">
    <@bind path/>
    <#list options?keys as value>
        <#assign id="${status.expression?replace('[','')?replace(']','')}${value_index}">
        <#if stringStatusValue == value>
        <label class="btn btn-primary active">
            <input type="radio" id="${id}" name="${status.expression}" value="${value}" checked
                   autocomplete="off">${options[value]}
        </label>
        <#else>
          <label class="btn btn-primary">
              <input type="radio" id="${id}" name="${status.expression}" autocomplete="off"> ${options[value]}
          </label>
        </#if>
    </#list>
</#macro>