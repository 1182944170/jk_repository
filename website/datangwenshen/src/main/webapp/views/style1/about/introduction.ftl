<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>大唐简介</title>
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/images/reset.css" rel="stylesheet" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<!--tab-->
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/images/jquery1.42.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/images/jquery.SuperSlide.2.1.js"></script>
</head>

<body>


<div class="main">
    <!--大唐简介-->    
     <#include "about_left.ftl">
    <div class="con_right fr">
        <div class="con_jies">
           <@common cmd="help_by_aliasesTitle"  aliasesTitle="introduction">
             ${help.context}    
           </@common>
        </div>        
    </div>
</div>
<!--底部-->

<!--qq悬浮窗-->
<script src='${ctx}/resources/${webSiteStyle}/images/jqqonline.js'></script>

<!-- Baidu Button BEGIN -->
<script type="text/javascript" id="bdshare_js" data="type=slide&amp;img=0&amp;pos=right&amp;uid=0" ></script>
<script type="text/javascript" id="bdshell_js"></script>
<script type="text/javascript">
document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/${ctx}/resources/${webSiteStyle}/images/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
</script>
<!-- Baidu Button END -->
</body>
</html>






















































