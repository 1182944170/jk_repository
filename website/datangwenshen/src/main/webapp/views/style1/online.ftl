<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>在线留言</title>
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/reset.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/resources/${webSiteStyle}/css/style.css" rel="stylesheet" />
<!--tab-->
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/${webSiteStyle}/js/jquery.SuperSlide.2.1.js"></script>
</head>

<body>

<div class="main">
    <!--在线留言-->    
    <div class="con_left fl">
        <div class="con_about">
            <h4><img src="${ctx}/resources/${webSiteStyle}/images//tit08.jpg" alt="" /></h4>
            <ul>
                <li><a href="contact${suffix}">联系我们</a></li>
                <li><a class="first" href="online${suffix}">在线留言</a></li>
            </ul>
           <#include "contact_us.ftl">
        </div>
    </div>
    <div class="con_right fr">      
        <div class="con_talk">
            <h4>在线留言</h4>
            <form action="${ctx}/commit_message${suffix}">
                <div><span>昵称：</span><input type="text"  name="name"/><em>*</em></div>
                <div><span>内容：</span><textarea name="content"></textarea><em>*</em></div>
                <div><span>电话：</span><input type="text" name="phone"/><em>*</em></div> 
                <div><span>验证码：</span><input type="text" name="verifyCode" style="width:100px !important;" maxlength='4'/>
              	<a href="javascript:f5VerifyCodeImg();"><image src="${ctx}/common/randomcode${suffix}" name="verifyCode"/></a><em>*</em></div>             
                <div class="btn">
                    <input type="submit" value="提交" />
                    <input type="reset" value="重置" />
                </div>
            </form>
        </div>        
    </div>
</div>

<script>
function f5VerifyCodeImg(){
	$("[name='verifyCode']").attr('src', ctx+'/common/randomcode?d='+new Date().getTime());
}
</script>
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






















































