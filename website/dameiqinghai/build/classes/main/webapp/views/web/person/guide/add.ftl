<title>发布导游</title>
<#include "../../public/top.ftl">
<!--内容-->

<link href="${ctx}/resources/css/select.css" type="text/css" rel="stylesheet" />

<script type="text/javascript" src="${ctx}/resources/js/sdmenu.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/area.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/location.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/adr/select2.js"></script> 
<script type="text/javascript">
	function checkSub(){
		var names = "guideName,名称;sex,性别;price,价格;jobTime,从业时间;guideNo,导游证号;telphone,联系电话;serverArea,服务地区;guideDesc,导游简介;serverGauge,服务标准;booking,预约须知";
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
                <div class="perconxq">
                	<h5><em>发布信息</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/guide/dosave${suffix}" method="POST" enctype="multipart/form-data">
                			<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
                        	<ul>
                            	<li><label>姓名：</label><input type="text" id="guideName" name="guideName" value="" class="i_btn"/></li>
                                <li><label>性别：</label>
	                                <select id="sex" name="sex" >
	                                	<option value=2>女</option>
	                                	<option value=1>男</option>
	                                </select>
	                            </li>
                                <li><label>价格：</label><input type="text" id="price" name="price" value="" class="i_btn" /></li>
                                <li><label>从业时间：</label><input type="text" id="jobTime" name="jobTime" value="" class="i_btn" /></li>
                                <li><label>导游证号：</label><input type="text" id="guideNo" name="guideNo" value="" class="i_btn" /></li>
                                <li><label>联系电话：</label><input type="text" id="telphone" name="telphone" value="" placeholder=""  class="i_btn" /></li>
                                <li><label>服务地区：</label><input type="text" id="serverArea" name="serverArea" value="" placeholder=""  /></li>
                                <li>
                                	<label>个人简介：</label>
                                    <div class="jainj">
                                    	<textarea id="guideDesc" name="guideDesc"></textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>服务标准：</label>
                                    <div class="shixj">
                                    	<textarea id="serverGauge" name="serverGauge"></textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>预约须知：</label>
                                    <div class="shixj">
                                    	<textarea id="booking" name="booking"></textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>温馨提示：</label>
                                    <div class="shixj">
                                    	<textarea id="warmCue" name="warmCue"></textarea>
                                    </div>
                                </li>
                                <li>
	                                <label>主图上传：</label>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1">
		                                                <img id="imageIds1" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards2">
		                                                <img id="imageIds2" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards1">
		                                                <img id="imageIds3" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards4">
		                                                <img id="imageIds4" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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
		                                            <div id="cards5">
		                                                <img id="imageIds5" width="166" height="127" src="${ctx}/resources/images/w_jia.jpg" style="display: block; width: 166px; height: 127px;">
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