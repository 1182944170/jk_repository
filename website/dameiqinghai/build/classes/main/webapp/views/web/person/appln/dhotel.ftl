<title>申请酒店</title>

<#include "../../public/top.ftl">
<script src="${ctx}/resources/js/base_x.js"></script>
<script src="${ctx}/resources/js/country_static.js"></script>
<script src="${ctx}/resources/static/data_country_static.js"></script>
<#if hotelUser??>
	<#include "prompt.ftl">
<#else>
	
	<script type="text/javascript">
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
		function checkSub(){
			var names = "userId,用户id;realName,真实姓名;telphone,联系电话;cardNo,身份证号;cardFrontPage,身份证正面上传;cardNegatePage,身份证反面上传";
		    return checkCommit(names);
		}
	</script>
	<div class="w_percon">
		<div class="w_main">
	        <div class="w_person">
	            <div class="w_personl fl">
	                <#include "../public.ftl">              
	            </div>
	            <div class="w_personr fr">
	                <!--地址选择器-->
	                <div class="perconxq">
	                	<h5><em>申请酒店</em></h5>
	                    <div class="fabu_youji">
	                    	<form action="${ctx}/appln/dosave${suffix}" method="POST" enctype="multipart/form-data" onsubmit="return commitSubmit()">
	            	        	<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
	            	        	<input type="hidden" name="type" id="type" value=5 />
	                        	<ul>
	                            	<li><label>真实姓名：</label><input type="text" id="realName" name="realName" class="i_btn" onchange="checkName(this.value,'showRealName');"/><b class="classerror" id="showRealName"></b></li>
	                                <li><label>酒店名称：</label><input type="text"  id="shopName" name="shopName" value="" class="a_btn" onchange="checkName(this.value,'showName');"/><b class="classerror" id="showName"></b></li>
	                                <li><label>联系电话：</label><input type="text"  id="telphone" name="telphone" value="" class="a_btn" onchange="checkTel(this.value,'showTel');"/><b class="classerror" id="showTel"></b></li>
	                                <li>
	                                    <label>所在地区：</label>
	                                    <select style="width:100px" name="provinceSelect" id="provinceSelect"></select>
										<select style="width:100px" name="citySelect" onchange="changeC()" id="citySelect"></select>
										<select style="width:100px" onchange="initCode()" name="countySelect" id="countySelect" style="width:100px;margin-left: 10px"></select>
	                                </li>
	                                <li><label>身份证号：</label><input type="text"  id="cardNo" name="cardNo" class="a_btn" onchange="checkCard(this.value,'showCard');"/><b class="classerror" id="showCard"></b></li>
	                                <li>
	                                	<label>证件上传：</label>
	                                    <span>
	                                        <div class="w_jdwl">
	                                            <div id="cardf">
	                                                <img id="previewb" width="166" height="127" src="${ctx}/resources/images/zhengmian.jpg" style="display: block; width: 240px; height: 180px;">
	                                                <span>
	                                                    <input type="file" name="cardFrontPage" id="cardFrontPage" onchange="setImagePreview('cardFrontPage','previewb','cardf');">
	                                                    <label>身份证正面上传</label>
	                                                </span>
	                                            </div>	
	                                        </div>
	                                    </span>
	                                    <span>
	                                        <div class="w_jdwl">
	                                            <div id="cardn">
	                                                <img id="previewa" width="166" height="127" src="${ctx}/resources/images/fumain.jpg" style="display: block; width: 240px; height: 180px;">
	                                                <span>
	                                                    <input type="file" name="cardNegatePage" value="" id="cardNegatePage" onchange="setImagePreview('cardNegatePage','previewa','cardn');">
	                                                    <label>身份证反面上传</label>
	                                                </span>
	                                            </div>	
	                                        </div>
	                                    </span>
	                                </li>
	                                <li>
	                                	<div class="w_jdwl">
	                                        <div id="localImag">
	                                            <img id="preview" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 290px; height: 190px;">
	                                            <span>
	                                                <input type="file" name="cedulaPage" id="cedulaPage" value="" onchange="setImagePreview('cedulaPage','preview','localImag');">
	                                                <label>营业执照上传</label>
	                                            </span>
	                                        </div>
	                                    </div>
	                                    <script type="text/javascript">jQuery(".zc_tab").slide();</script>
	                                </li>
	                            </ul>  
	                       	  <div class="xiug_btn">
	                                <input type="submit" value="确定申请" onclick="return check(this.form)"/>
	                          </div>    
	                      </form>
	                    </div> 
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</#if>

<#include "../../public/foot.ftl">