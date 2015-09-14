<title>发布租车</title>

<#include "../../public/top.ftl">

<script type="text/javascript">
	function checkSub(){
		var names = "rcarName,车主;carModel,车型;price,价格;telphone,联系电话;bearNum,乘载人数;carType,类型;rcarDesc,简介;rcarItems,租车事项";
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
                    	<form action="${ctx}/rcar/dosave${suffix}" method="POST" enctype="multipart/form-data">
                			<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
                        	<ul>
                            	<li><label>车主：</label><input type="text" id="rcarName" name="rcarName" value="" class="i_btn"/></li>
                                <li><label>车型：</label><input type="text" id="carModel" name="carModel" value="" class="i_btn" /></li>
                                <li><label>价格：</label><input type="text" id="price" name="price" value="" class="i_btn" /></li>
                                <li><label>联系号码：</label><input type="text" id="telphone" name="telphone" value="" class="i_btn" /></li>
                                <li><label>乘载人数：</label><input type="text" id="bearNum" name="bearNum" value="" class="i_btn" /></li>
                                <li><label>类型：</label><input type="text" id="carType" name="carType" value="" placeholder="如：高级轿车 / 自动挡 / 4门 / 5座"  class="a_btn" /></li>
                                <li>
                                	<label>简介：</label>
                                    <div class="jainj">
                                    	<textarea id="rcarDesc" name="rcarDesc" ></textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>租车事项：</label>
                                    <div class="shixj">
                                    	<textarea id="rcarItems" name="rcarItems" ></textarea>
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