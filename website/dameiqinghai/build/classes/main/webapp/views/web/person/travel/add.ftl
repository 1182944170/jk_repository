<title>发布游记</title>

<#include "../../public/top.ftl">

<script type="text/javascript">
	function checkSub(){
		var names = "title,标题;playPlace,景区;runType,出行方式;content,景区简要;check,请选择协议;"
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
                	<h5><em>发布游记</em></h5>
                    <div class="fabu_youji">
                    	<form action="${ctx}/travel/dosave${suffix}" method="POST" enctype="multipart/form-data"  >
                    		<input type="hidden" name="userId" value="${sessionUser.id!}"/>
                        	<ul>
                            	<li><label>标题：</label><input type="text" name="title"/></li>
                                <li><label>出发时间：</label><input type="text" name="startTime" class="i_btn" /></li>
                                <li><label>出发地：</label><input type="text" name="goAddress" class="i_btn" /></li>
                                <li><label>游玩天数：</label><input type="text" name="playNumber" class="i_btn" /></li>
                                <li><label>游玩景区：</label><input type="text" name="playPlace" /></li>
                                <li><label>花销：</label><input type="text" name="consume" /></li>
                                <li><label>出行方式：</label><input type="text" name="runType" /></li>
                                <li><label>出行准备：</label><textarea name="fixUp" cols="15" rows="8"></textarea></li>
                                <li><label>美食介绍：</label><textarea name="foodDesc" cols="15" rows="8"></textarea></li>
                                <li><label>酒店介绍：</label><textarea name="hotelDesc" cols="15" rows="8"></textarea></li>
                                <li><label>景区介绍：</label><textarea name="introduction" cols="15" rows="8"></textarea></li>
                            </ul>
                            <div class="fabu_con">
                            	<h6>
                                	<span><em>用你的语言写下最美的旅程</em></span>
                                    <span>
                                    	<input type="file" name="coverPage" style="display: none;" onchange="ye.value=value">
                                        <input type="button" value="上传图片" onclick=coverPage.click() class="fb_ys">
                                    </span>
                                </h6>
                                <textarea name="content" cols="30" rows="8"></textarea>
                                <div class="fb_dianji">
                                	<div class="fb_qy fl">
                                    	<input type="checkbox" name="check"/>
                                        <p>你已阅读并同意《大美青海协议》</p>
                                    </div>
                                    <div class="fb_btn fr">
                                    	<input type="submit" value="发布游记" onclick="return checkSub(this.form)"/>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div> 
                </div>
            </div>
        </div>
    </div>
</div>



<#include "../../public/foot.ftl">