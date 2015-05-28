<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>纹身资讯</title>
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/style.css" rel="stylesheet" />
<!--tab-->
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery.SuperSlide.2.1.js"></script>
</head>

<body>

<div class="main">
    <!--纹身资讯-->    
    <div class="con_left fl">
        <div class="con_about">
            <h4><img src="${ctx}/resources/${webSiteStyle}/images/tit06.jpg" alt="" /></h4>
            <ul>
                <li><a class="first" href="news.html">新闻中心</a></li>
                <li><a href="contact.html">联系我们</a></li>
            </ul>
        <#include "contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">      
        <div class="news">
            <ul>
                <!--<li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="news_detail.html">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>
                <li><a href="#">扫一扫，你就能加纹身<em>2015.04.17</em></a></li>-->
                    <@common cmd="notice_list" pagerString="1_" pageSize="17">
                  	<#if m_pager.itemList?has_content>
                  		<#list m_pager.itemList as u>
                      		 <li><a href="${ctx}/news/${u.id}${suffix}">${u.title}<em>${tagUtils.formatDate(u.recoreCreateTime)}</em></a></li>
                    	</#list>
                    <#else>
                    	none
                  	</#if>
                    
                  </@common>
                   
            </ul>
        </div>
        <!--页数--> 
        <div class="page">
            <span><a href="#">上一页</a></span>
            <span><a href="#">1</a></span>
            <span><a href="#">2</a></span>
            <span><a href="#">3</a></span>
            <span><a href="#">下一页</a></span>
        </div>            
    </div>
</div>

<!--qq悬浮窗-->
<script src='${ctx}/resources/${webSiteStyle}/js/jqqonline.js'></script>

<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=0&amp;pos=right&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/${ctx}/resources/${webSiteStyle}/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
</script>
<!-- Baidu Button END -->
</body>
</html>






















































