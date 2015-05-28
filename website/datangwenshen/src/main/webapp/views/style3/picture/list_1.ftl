
<div class="main">
    <!--大唐作品-->
    <div class="work">
        <!--标题-->
       <#if source==1>
   	<meta http-equiv="description" content="" />
	<meta http-equiv="keywords" content="" />
	<title>大唐作品</title>
        	<div class="tit">
           <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="work.html">大唐作品</a></h3>
            <h5>十年纹身老字号</h5>"
      <#else>
      <meta http-equiv="description" content="" />
	<meta http-equiv="keywords" content="" />
	<title>纹身图库</title>
      	<div class="tit">
           <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="work.html">纹身图库</a></h3>
            <h5>十年纹身老字号</h5>"
      </#if>
        </div>     
        <div class="con_left">
        <div class="con_about">
        <div class="w_con">
            <#assign urlBase="work" />
            <#if source==1>
            	<#assign urlBase="work" />
            <#elseif source==2>
            	<#assign urlBase="material" />
            <#else>
            	<#assign urlBase="piercing" />
            </#if>
        	<a href="${ctx}/${urlBase}${suffix}">全部作品</a>
			<#assign sTypes = dicSetting.getParameterMap("picture.source."+source+".type") />
			<#list sTypes?keys as key>
				<a href="${ctx}/${urlBase}${suffix}?pager=1_type--${key}">${sTypes[key]}></a>
            </#list>
        </div> 
        <div class="pict">	
            <ul>
			<#list pager.itemList as u>
	           <li><img src="${tagUtils.getFileFullPath(u.address)}" width="224" height="270" alt="${u.name}"/>	</li>
			</#list>
	            </ul>
	            </div>
        </div>  
    </div>       
    		<script>
       			new showPages('${ctx}/news${suffix}','${pager.totalPages?c}','${pager.totalCount?c}','${pager.pagerWebString}').printBaseHtml();
			</script>    
</div>
</div>
<#include "../wechat.ftl"> 

<!--底部-->
