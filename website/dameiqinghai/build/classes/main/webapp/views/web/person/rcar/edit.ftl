<title>编辑租车</title>

<#include "../../public/top.ftl">
<script type="text/javascript">
	//检验带*号的字段，添加name、字段名
	function checkSub(){
		var names = "rcarName,车主;carModel,车型;price,价格;telphone,联系电话;bearNum,乘载人数;carType,类型;rcarDesc,简介;rcarItems,租车事项";
		return checkCommit(names);		
	}
	//每个图片div加上 class="pclass"
	$(function(){
		var img = '${rcar.photos!}';
		initAnalyse(img);
	})
	//解析图片
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
                	<h5><em>发布信息</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/rcar/dosave${suffix}" method="POST" enctype="multipart/form-data">
                			<input type="hidden" name="userId" id="userId" value="${sessionUser.id!}" />
                			<input type="hidden" name="id" id="id" value="${rcar.id!}" />
                			<input type="hidden" name="isCommend" id="isCommend" value="${rcar.isCommend!}" />
                        	<ul>
                            	<li><label>车主：</label><input type="text" id="rcarName" name="rcarName" value="${rcar.rcarName}" class="i_btn"/></li>
                                <li><label>车型：</label><input type="text" id="carModel" name="carModel" value="${rcar.carModel}" class="i_btn" /></li>
                                <li><label>价格：</label><input type="text" id="price" name="price" value="${rcar.price}" class="i_btn" /></li>
                                <li><label>联系号码：</label><input type="text" id="telphone" name="telphone" value="${rcar.telphone}" class="i_btn" /></li>
                                <li><label>乘载人数：</label><input type="text" id="bearNum" name="bearNum" value="${rcar.bearNum}" class="i_btn" /></li>
                                <li><label>类型：</label><input type="text" id="carType" name="carType" value="${rcar.carType}" placeholder="如：高级轿车 / 自动挡 / 4门 / 5座"  class="a_btn" /></li>
                                <li>
                                	<label>简介：</label>
                                    <div class="jainj">
                                    	<textarea id="rcarDesc" name="rcarDesc" >${rcar.rcarDesc}</textarea>
                                    </div>
                                </li>
                                <li>
                                	<label>租车事项：</label>
                                    <div class="shixj">
                                    	<textarea id="rcarItems" name="rcarItems" >${rcar.rcarItems}</textarea>
                                    </div>
	                                <label>主图上传：</label>
                                </li>
                                <li>
                                    <dl>
                                    	<dd>
                                            <span>
		                                        <div class="w_jdwl">
		                                            <div id="cards1" class="pclass">
		                                                <img id="imageIds1" width="166" height="127" style="display: block; width: 166px; height: 127px;">
		                                                <span>
		                                                    <input type="file" name="photos1" id="fileIds1" onchange="setImagePreview('fileIds1','imageIds1','cards1');">
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