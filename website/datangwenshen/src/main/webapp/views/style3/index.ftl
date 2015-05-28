<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>网站首页</title>
<!--main-->
<div class="main">
    <div class="about">
        <!--标题-->
        <div class="tit">
            <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="about.html">关于本店</a></h3>
            <h5>十年纹身老字号</h5>
        </div>
		<!--关于本店-->
        <div class="abcon">
            <div class="pcon01 fl">
                <img src="${ctx}/resources/${webSiteStyle}/images/spic.jpg" width="298" height="241" alt="" />
            </div>
            <div class="pcon02 fl">
                 <@common cmd="help_by_aliasesTitle" aliasesTitle="gongsijianjie2" >
		        		${help.context}
		                 
		        	 </@common>
            </div>		
        </div>
        <!--视频、新闻-->
        <div class="scon">
            <div class="mv fl">
                <h4>洗纹身视频</h4>
                <div class="ship">
                <embed 
					src="http://player.youku.com/player.php/sid/XNTEyOTM1MDQ4/v.swf" 
					allowFullScreen="true" 
					quality="high" 
					width="435" 
					height="313" 
					align="middle" 
					allowScriptAccess="always" type="application/x-shockwave-flash"></embed>  
                
                    
                </div>
            </div>
            <div class="news fr">
                <h4>纹身资讯</h4>
                <ul>
                <@common cmd="notice_list" pagerString="1_" pageSize="9">
		        		<#list m_pager.itemList as u>
		                    <li><a href="${ctx}/news/${u.id}/detail${suffix}"><i></i><b>${u.title}</b><em>${tagUtils.formatDate(u.recoreCreateTime)}</em></a></li>
		        		</#list>
		        	 </@common>
                </ul>
            </div>
        </div>
        <!--纹身图库-->
        <div class="tit">
            <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="material.html">纹身图库</a></h3>
            <h5>十年纹身老字号</h5>
        </div>
        <div class="picScroll">	
        <ul>
        <@datangwenshen cmd="get_pic_list" source=1 type=1 pagerString="1_" pageSize="8">
    		<#list m_pager.itemList as u>
               <li><img src="${tagUtils.getFileFullPath(u.address)}" width="224" height="270" alt="${u.name}"/></li>
    		</#list>
    	</@datangwenshen>
               
            </ul>
    
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>
        </div>
        <script type="text/javascript">
    jQuery(".picScroll").slide({ mainCell:"ul", effect:"leftLoop", vis:5, scroll:2,  autoPage:true, switchLoad:"_src" });
        </script>
        <!--纹身作品-->
        <div class="tit">
            <h4>杭州大唐纹身(tatto)</h4>
            <h3><a href="work.html">纹身作品</a></h3>
            <h5>十年纹身老字号</h5>
        </div>
        <div class="pict">	
            <ul>
                      	 
		  <@datangwenshen cmd="get_pic_list" source=1 type=1 pagerString="1_" pageSize="8">
		        		<#list m_pager.itemList as u>
		                    <li><img src="${tagUtils.getFileFullPath(u.address)}" alt="${u.name}"/></li>
		        		</#list>
		        </@datangwenshen>
            </ul>
        </div>
           
    </div>
</div>
<#include "wechat.ftl"> 























































