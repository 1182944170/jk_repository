
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>纹身资讯</title>

</head>

<body>

<div class="main">
    <!--纹身资讯-->    
    <div class="con_left fl">
        <div class="con_about">
            <h4><img src="${ctx}/resources/${webSiteStyle}/images/tit06.jpg" alt="" /></h4>
            <ul>
                <li><a class="first" href="news${suffix}">新闻中心</a></li>
                <li><a href="contact${suffix}">联系我们</a></li>
            </ul>
        <#include "../contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">      
        <div class="news">
            <ul>

                  	<#if pager.itemList?has_content>
                  		<#list pager.itemList as u>
                      		 <li><a href="${ctx}/news/${u.id}/detail${suffix}">${u.title}<em>${tagUtils.formatDate(u.recoreCreateTime)}</em></a></li>
                    	</#list>
                    <#else>
                    	none
                  	</#if>
                                       
            </ul>
        </div>
        <script>new showPages('${ctx}/news${suffix}','${pager.totalPages?c}','${pager.totalCount?c}','${pager.pagerWebString}').printBaseHtml();
		</script>
                
    </div>
</div>



</body>























































