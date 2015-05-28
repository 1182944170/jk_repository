<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>纹身资讯 内页</title>
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
            <h4><img src="${ctx}/resources/${webSiteStyle}/images//tit06.jpg" alt="" /></h4>
            <ul>
                <li><a class="first" href="#">新闻中心</a></li>
                <li><a href="#">联系我们</a></li>
            </ul>
            <#include "contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">      
        <div class="news_con">
           <!-- <h4>行行出状元。纹身行业也出状元。</h4>
            <p>文身作为人类历史文化的一部分，延续几千多年。生活中文身给人印象多半坏的感觉，很直接就想到是黑道人物的专属。其实这种观念并不适合现代，近几年来社会日渐开放民众大量接受欧美、日本等国的文化资讯，现代人不像以前那么传统，已不再把文身当作禁忌，在文身人员中有些是夫妻，情侣，表作爱意将对方的相貌留在身上，还有社会精英，时尚达人，学生等等。  </p>
            <p>随着纹身行业的新兴，滋生了大量的国内外优秀纹身大师，Nikko Hurtado   Mike Deveries    Jesse Smith  Chil Victor MALE因为我i也不怎么会英文，所以不能一一列举，国内比较有名的，传统的仓龙，肖像的小龙，大气杨卓，石雕的新刺客，磨砂的针藏，天尊堂，等等，尤其上海北京的纹身高手太多了，技术绝不差于国外纹身大师，，更多年轻纹身大师，比如清鬼，文粹，王雷等等，中国自古就是文人墨客的地方，，，纹身也不落后国外，，，如果你喜欢不一样的风格，如果你有充足财力，我帮你建议你去找国内外纹身大师做哦！</p>
            <p>不过，好的纹身师都要提前预约，不一定有钱就可以做纹身，最好提前预约，，，，杭州大唐纹身耐心为你解答！</p>-->
            <h4>${news.title}</h4>
            <p>${news.content}</p>
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






















































