<meta http-equiv="description" content="" />
	<meta http-equiv="keywords" content="" />
	<title>纹绣穿刺</title>
<div class="main">
    <!--纹绣穿刺-->
    <div class="dabout">
        <!--标题-->
        <div class="tit">
            <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="piercing.html">纹绣穿刺</a></h3>
            <h5>十年纹身老字号</h5>
        </div>  
        <div class="d_dajj">
            <div class="d_tit">
           	     <h4>人体穿刺</h4>
                <div class="pict">	
                    <ul>
                        <@datangwenshen cmd="get_pic_list" source=3 type=2 pagerString="1_" pageSize="4">
		        		<#list m_pager.itemList as u>
		                    <li><img src="${tagUtils.getFileFullPath(u.address)}" width="224" height="270" alt="${u.name}"/></li>
		        		</#list>
		        </@datangwenshen>
                    </ul>
                </div>
            </div>
            
            <div class="d_tit">
                <h4>绣眉漂唇</h4>
                <div class="picd">
                    <ul>
                        <@datangwenshen cmd="get_pic_list" source=3 type=3 pagerString="1_" pageSize="8">
		        		<#list m_pager.itemList as u>
		                    <li><img src="${tagUtils.getFileFullPath(u.address)}" width="242" height="164"; alt="${u.name}"/></li>
		        		</#list>
		        </@datangwenshen>
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
