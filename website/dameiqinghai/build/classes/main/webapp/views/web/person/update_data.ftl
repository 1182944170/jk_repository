<title>修改资料</title>

<#include "../public/top.ftl">
<script src="${ctx}/resources/js/base_x.js"></script>
<script src="${ctx}/resources/js/country_static.js"></script>
<script src="${ctx}/resources/static/data_country_static.js"></script>
<#--<script src="${ctx}/resources/js/touxiang/main.js"></script>-->
<script src="${ctx}/resources/js/touxiang/cropper.js"></script>
<script>
var country = Object.create(B.Country);
$(document).ready(function(){
	country.regist4Select(${(sessionUser.areaCode)!-1},"provinceSelect","citySelect","countySelect");
})
function initCode(){
	var code = country.getSelectData();
	$("#areaCode").val(code);
}

function changeC(){
	$("#countySelect").val();
}
</script>
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "public.ftl">             
            </div>
            
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>修改资料</em></h5>
                    <form role="form"  method="POST" action="${ctx}/user/update_data${suffix}" enctype="multipart/form-data">
                    	<input type="hidden" name="id" value="${sessionUser.id}"/>
                    	<input type="hidden" name="password" value="${sessionUser.password}"/>
                    	<input type="hidden" name="areaCode" id="areaCode" value="${sessionUser.areaCode!}"/>
                    	<input type="hidden" name="type" value="${sessionUser.type}"/>
                    	<input type="hidden" name="isGuide" value="${sessionUser.isGuide}"/>
                    	<input type="hidden" name="isFood" value="${sessionUser.isFood}"/>
                    	<input type="hidden" name="isHotel" value="${sessionUser.isHotel}"/>
                    	<input type="hidden" name="isCar" value="${sessionUser.isCar}"/>
                    	<input type="hidden" name="isArt" value="${sessionUser.isArt}"/>
                        <div class="fabu_youji">
                            <div class="w_jbtoux">
                                <div class="htmleaf-container">
                                    <div class="img-container">
                                       <img src="${tagUtils.getFileFullPath(sessionUser.photo!)}" style="width:90px; height: 90px;" />
                                    </div>
                                    <div class="row">
                                        <div class="btn-group">	  
                                          <label class="btn btn-primary btn-upload" for="inputPhoto" title="Upload image file">
                                            <input class="sr-only" id="inputPhoto" name="inputPhoto" type="file" />		
                                            <span>更改头像</span>
                                          </label>
                                        </div>    
                                    </div>
                                </div>
                            </div>
                            <ul>
                                <li><label>昵称：</label><input type="text" name="nickName" id="nickName" value="${sessionUser.nickName!}" class="i_btn"/></li>
                                <li><label>姓名：</label><input type="text" name="userName" id="userName" value="${sessionUser.userName!}" class="i_btn" /></li>
                                <li><label>联系方式：</label><input type="text" name="telphone" id="telphone"  value="${sessionUser.telphone!}" readonly class="i_btn" /></li>
                                <li><label>性别：</label>
                                	<select name="sex" id="sex">
	                                	<#if sessionUser.sex == 1>
				                			<option value="1" selected="selected">男</option>
				                			<option value="2">女</option>
				                		<#else>
				                			<option value="1">男</option>
				                			<option value="2" selected="selected">女</option>
				                		</#if>
	                                </select>
                                </li>
                                <li><label>年龄：</label><input type="text" value="${sessionUser.age!}" name="age" id="age" class="i_btn"/></li>
                                <li><label>出生年月：</label><input type="text" id="time" name="birthday" value="${sessionUser.birthday!}" onClick="return Calendar('time');" class="text_time"/></li>
                                <li>
                                    <label>所在地区：</label>
                                    <select style="width:100px" name="provinceSelect" id="provinceSelect"></select>
									<select style="width:100px" name="citySelect" onchange="changeC()" id="citySelect"></select>
									<select style="width:100px" onchange="initCode()" name="countySelect" id="countySelect" style="width:100px;margin-left: 10px"></select>
                                </li>
                                <li><label>邮箱：</label><input type="text" name="email" id="email" value="${sessionUser.email!}"/></li>
                                <li><label>个人简介：</label><textarea name="userDesc" id="userDesc">${sessionUser.userDesc!}</textarea></li>
                            </ul>
                            <div class="xiug_btn">
                                <input type="submit" value="保存资料" />
                            </div>     
							<#if errorMsg??>
								<div class="hr hr-18 hr-double dotted"></div>
								<h4 class="lighter">
									<i class="icon-hand-right icon-animated-hand-pointer red"></i>
									<span class="pink">${errorMsg!}</span>
								</h4>
							</#if>
							<#if infoMsg??>
								<div class="hr hr-18 hr-double dotted"></div>
							
								<h4 class="lighter">
									<i class="icon-hand-right icon-animated-hand-pointer green"></i>
									<span class="pink">${infoMsg}</span>
								</h4>
							</#if>
                        </div>                    
                    </form>
                </div> 
            </div>
        </div>
    </div>
</div>

<!-- 修改头像 -->
<#include "../public/foot.ftl">
