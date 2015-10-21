<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>登陆</title>
<link type="text/css" href="${ctx}/resources/css/reset.css" rel="stylesheet">
<link href="${ctx}/resources/cssframework/ace/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	<div class="mf_denglu">
    	<div class="mf_top"><img src="${ctx}/resources/images/top.png"></div>
        <div class="mf_con">
        	<div class="mf_title"><img src="${ctx}/resources/images/title.png"></div>
            <form action="${ctx}/admin/dologin${suffix}" method="post">
            	<ul class="mf_input">
                	<li class="mf_li01">
                    	<label></label>
                        <input type="text" class="mf_input01" id="userName" name="userName">
                    </li>
                    <li class="mf_li02">
                    	<label></label>
                        <input type="password" class="mf_input01" id="pwd" name="pwd">
                    </li>
                    <li class="mf_li03">
                        <div class="mf_div"><input type="text" class="mf_input02" id="verifyCode" name="verifyCode"></div>
                        <div class="mf_yan"><img src="${ctx}/common/randomcode/admin" width="100" height="40"></div>
                    </li>
                    <li class="mf_li04">
                        <input type="submit" class="mf_input03" value="提交">
                    </li>
                    
                    <#if errorMsg??>
						<div class="text-danger center">&nbsp;&nbsp;${errorMsg}</div>
					</#if>
						
                </ul>
            </form>
        </div>
    </div>
</body>
</html>