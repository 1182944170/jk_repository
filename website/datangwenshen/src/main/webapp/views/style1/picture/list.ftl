
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
            <#if source==1>
            	<title>大唐作品</title>          	
            <#elseif source==2>
            	<title>国际大师作品</title>
            <#else>
            	<title>纹绣穿刺</title>
            </#if>



</head>

<body>


<div class="main">
    <!--纹绣穿刺-->    
    <!--左边--> 
    <div class="con_left fl">
        <div class="con_about">
            <h4>
            <#assign urlBase="work" />
            <#if source==1>
            	<img src="${ctx}/resources/${webSiteStyle}/images//tit02.jpg" alt="" />
            	<#assign urlBase="work" />
            <#elseif source==2>
            	<img src="${ctx}/resources/${webSiteStyle}/images//tit03.jpg" alt="" />
            	<#assign urlBase="material" />
            <#else>
            	<img src="${ctx}/resources/${webSiteStyle}/images//tit05.jpg" alt="" />
            	<#assign urlBase="piercing" />
            </#if>
            </h4>
            <ul>
            	<li><a class="first" href="${ctx}/${urlBase}${suffix}">全部作品</a></li>
        			<#assign sTypes = dicSetting.getParameterMap("picture.source."+source+".type") />
        			<#list sTypes?keys as key>
        				<#if key_index lt 7>
        				<li><a class="first" href="${ctx}/${urlBase}${suffix}?pager=1_type--${key}">${sTypes[key]}</a></li>
        				</#if>
		            </#list>
            		
            </ul>
          <#include "../contact_us.ftl">
        </div>
    </div>
    <!--右边--> 
    <div class="con_right fr">
        <div class="con_pic">
            <div class="menu">
            	<#if pager.searchMap.type??>
            		 <a class="cur" href="${ctx}/${urlBase}${suffix}?pager=1_type--${pager.searchMap.type}">${dicSetting.getParameterValue("picture.source."+source+".type." + pager.searchMap.type)}</a>
            	<#else>
            		 <a class="cur" href="${ctx}/${urlBase}${suffix}">全部作品</a>
            	</#if>
            </div> 
             <div class="con_tt">
             <#list sTypes?keys as key>       			
        	   <a href="${ctx}/${urlBase}${suffix}?pager=1_type--${key}">${sTypes[key]}</a>	
		     </#list>
            </div>
                
            <!--作品-->       
            <div class="plusview">
                <ul>
                     <#if pager.itemList?has_content>
                  		<#list pager.itemList as u>
                       	<li>
                        	<a href="${tagUtils.getFileFullPath(u.address)}" data-type="image">
                            	<img src="${tagUtils.getFileFullPath(u.address)}" width="185" height="236" alt="${u.name}">
                        	</a>
                       	</li>
                    	</#list>
                    <#else>
                    	无数据
                  	</#if>
                  
                </ul>
            </div>
            <script src="${ctx}/resources/${webSiteStyle}/js/plusview.js"></script>
            <script>
            $(function() {
                $('.plusview').plusview();
            });
            
            </script>
            
            <script>new showPages('${ctx}/${urlBase}${suffix}','${pager.totalPages?c}','${pager.totalCount?c}','${pager.pagerWebString}').printBaseHtml();
			</script>
        </div>        
    </div>
</div>


</body>