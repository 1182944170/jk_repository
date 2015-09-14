<title>编辑导游</title>

<#include "../../public/top.ftl">
<!--内容-->

<script type="text/javascript">
	function checkSub(){
		var names = "guideName,名称;sex,性别;price,价格;jobTime,从业时间;guideNo,导游证号;telphone,联系电话;serverArea,服务地区;guideDesc,导游简介;serverGauge,服务标准;booking,预约须知";
		return checkCommit(names);		
	}
	
	//每个图片div加上 class="pclass"
	$(function(){
		var img = '${guide.photos!}';
		initAnalyse(img);
	})
	function initAnalyse(imageObj){
		var photos = imageObj;
		if(photos != '' && photos != null){
			if(photos.indexOf(",")>0){
				var p = photos.split(",");
				$(".pclass").each(function (i){
					if(p.length-1>=i){
						$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p[i]+"')}");
						$(this).find("input").attr("value",p[i]);
					}else{
						$(this).find("img").eq(i).attr("src","${ctx}/resources/images/w_jia.jpg");
					}
				});
		}else{
			$(".pclass").find("img").first().attr("src","${tagUtils.getFileFullPath('"+photos+"')}");
			$(".pclass").find("input").first().attr("value",photos);
		}
	}
}
</script> 
<div class="w_percon">
	<div class="w_main">
        <div class="w_person">
            <div class="w_personl fl">
                <#include "../public.ftl">               
            </div>
            <div class="w_personr fr">
                <div class="perconxq">
                	<h5><em>编辑信息</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/guide/dosave${suffix}" method="POST" enctype="multipart/form-data">
                			<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
                			<input type="hidden" name="id" id="id" value="${guide.id!}" />
                			<input type="hidden" name="isCommend" id="isCommend" value="${guide.isCommend!}" />
                        	<ul>
                            	<li><label>姓名：</label><input type="text" id="guideName" name="guideName" value="${guide.guideName}" class="i_btn"/></li>
                                <li><label>性别：</label>
	                                <select id="sex" name="sex" >
	                                	<#if guide.sex == 1>
		                                	<option value=1 selected="selected">男</option>
				                			<option value=2>女</option>
		                                <#else>
		                                	<option value=1>男</option>
				                			<option value=2 selected="selected">女</option>
		                                </#if>
	                                </select>
	                            </li>
                                <li><label>价格：</label><input type="text" id="price" name="price" value="${guide.price}" class="i_btn" /></li>
                                <li><label>从业时间：</label><input type="text" id="jobTime" name="jobTime" value="${guide.jobTime}" class="i_btn" /></li>
                                <li><label>导游证号：</label><input type="text" id="guideNo" name="guideNo" value="${guide.guideNo}" class="i_btn" /></li>
                                <li><label>联系电话：</label><input type="text" id="telphone" name="telphone" value="${guide.telphone}" placeholder=""  class="i_btn" /></li>
                                <li><label>服务地区：</label><input type="text" id="serverArea" name="serverArea" value="${guide.serverArea}" placeholder=""  /></li>
                                <li>
                                	<label>个人简介：</label>
                                    <div class="jainj">
                                    	<textarea id="guideDesc" name="guideDesc">${guide.guideDesc}</textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>服务标准：</label>
                                    <div class="shixj">
                                    	<textarea id="serverGauge" name="serverGauge" >${guide.serverGauge}</textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>预约须知：</label>
                                    <div class="shixj">
                                    	<textarea id="booking" name="booking" >${guide.booking}</textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>温馨提示：</label>
                                    <div class="shixj">
                                    	<textarea id="warmCue" name="warmCue" >${guide.warmCue}</textarea>
                                    </div>
                                </li>
                                <li>
	                                <label>主图上传：</label>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1" class="pclass">
		                                                <img id="imageIds1" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos1" id="fileIds1" onchange="setImagePreview('fileIds1','imageIds1','cards1');">
		                                                    <label>主图01</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards2" class="pclass">
		                                                <img id="imageIds2" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos2" id="fileIds2" onchange="setImagePreview('fileIds2','imageIds2','cards2');">
		                                                    <label>主图02</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards3" class="pclass">
		                                                <img id="imageIds3" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos3" id="fileIds3" onchange="setImagePreview('fileIds3','imageIds3','cards3');">
		                                                    <label>主图03</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards4" class="pclass">
		                                                <img id="imageIds4" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos4" id="fileIds4" onchange="setImagePreview('fileIds4','imageIds4','cards4');">
		                                                    <label>主图04</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                        <dd>
                                        	<span>
		                                        <div class="w_jdwl">
		                                            <div id="cards5" class="pclass">
		                                                <img id="imageIds5" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos5" id="fileIds5" onchange="setImagePreview('fileIds5','imageIds5','cards5');">
		                                                    <label>主图05</label>
		                                                </span>
		                                            </div>	
		                                        </div>
                                    		</span>
                                        </dd>
                                    </dl>
                                </li>
                            </ul>
                          <div class="xiug_btn">
                                <input type="submit" value="提交" onclick="return checkSub(this.form)"/>
                          </div>    
                        </form>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>
<#include "../../public/foot.ftl">