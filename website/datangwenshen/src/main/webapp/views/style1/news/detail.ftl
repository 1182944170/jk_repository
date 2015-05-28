
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>${notice.title} - 资讯</title>

</head>

<body>

<div class="main">
    <!--纹身资讯-->    
    <div class="con_left fl">
        <div class="con_about">
            <h4><img src="${ctx}/resources/${webSiteStyle}/images//tit06.jpg" alt="" /></h4>
            <ul>
                <li><a class="first" href="${ctx}/news${suffix}">新闻中心</a></li>
                <li><a href="${ctx}/contact${suffix}">联系我们</a></li>
            </ul>
            <#include "../contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">      
        <div class="news_con">

            <h4>${notice.title}</h4>
            <p>${notice.content}</p>
        </div>        
    </div>
</div>


</body>























































