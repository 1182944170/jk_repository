<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>培训学员</title>
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/style.css" rel="stylesheet" />
<!--tab-->
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery.SuperSlide.2.1.js"></script>
</head>

<body>

<!--main-->
<div class="main">
    <!--培训学员-->    
    <div class="con_left fl">
        <div class="con_about">
            <h4><img src="${ctx}/resources/${webSiteStyle}/images//tit07.jpg" alt="" /></h4>
            <ul>
                <li><a class="first" href="train${suffix}">培训学员</a></li>
                <li><a href="contact${suffix}">联系我们</a></li>
            </ul>
          <#include "contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">
        <div class="con_jies con_tr">
           <!-- <p style="text-indent:0em;"><strong>培训学员</strong><br />学期一周 学费1000元<br />学期两周 学费1800元<br />学期不限制 学费5000元 学会为止. 随到随学，工作上班学习两不误<br />保证可独立开设专业店.并长期技术支持.<br />纹身培训内容：<br />1纹身割线与打雾专业技法. 2规划图案与卫生消毒常识.3机器正规使用与维修 .<br />一. 理论课程<br />(1)纹身常识 (2)器材的认识与分辨 (3)纹身护理常识 (4)纹身风格的分析与欣赏<br />
二. 实践课程<br />(1)怎样塑造立体形体(2)纹身技巧(3)彩色基础.调色和配色(4)图形变形(5)基础绘图<br />三. 开店指南<br />(1)怎样开设自己的专业店 (2)怎样设计自己的专业店 (3)怎样树立自己的风格(4)怎
样定位自己的专业店<br />在这里你可以得到切身的实际操作，令你更为自信，可以从没有绘画基础到独立操作，包教包会学会为止的原则！<br />学员学习步骤：<br />
美术基础训练-〉纹身专业训练-〉实习操作训练-〉经营管理培训。（以实教造就专业）</p>-->
        		<@common cmd="help_by_aliasesTitle" aliasesTitle="train">
        		${help.context}
        	 	</@common>
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






















































