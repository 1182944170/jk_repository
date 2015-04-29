<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>
<!--content begin-->
<div class="content">
	<div class="dyx_ct">
    	<div class="dyx_ctl">
        	<div class="yyrm1_l"><div class="yyrm1_l2"><img src="${tagUtils.getFileFullPath(cinema.icon)}" width="217" height="190"></div></div>
            <div class="yyx_ctl2">
            	<div class="p1">${cinema.name}电影院<a href="#"><img src="${ctx}/resources/images/dy/gz1.jpg" width="55" height="20"></a></div>
            	<div class="p2">详细地址：<span> ${cinema.address}</span></div>
            	<div class="p2">联系电话：<span>${cinema.contact}</span></div>
            	<div class="p2">影院特色：<span>${cinema.feature}</span></div>
            	<div class="p2">支持产品：<span>在线选位</span></div>
            	<div class="p2">优势服务：
            		<#if tagUtils.logic2(cinema.supportService,1)><i class="i01" title="支持3D"></i></#if>
					<#if tagUtils.logic2(cinema.supportService,2)><i class="i02" title="有停车场"></i></#if>
					<#if tagUtils.logic2(cinema.supportService,4)><i class="i03" title="儿童免费"></i></#if>
				</div>
            </div>
        </div>
    	<div class="dyx_ctr">
        	<div class="dyx_ctr1">
            	<div class="p1">评分：</div>
            	<!--<div class="p2">
                	<div class="q1"><img src="${ctx}/resources/images/dy/dj.jpg" width="140" height="22"></div>
                	<div class="q2">完美，值得一看</div>
                </div>-->
            	<div class="p3">${cinema.mark}</div>
                <div class=" nofl"></div>
            </div>
            <div class="dyx_ctr2">
            	<div class="p1"><a href="#">我要评价</a></div>
                <div class="p2">已有<span>22</span>条评论</div>
            </div>
            <div class="dyx_ctr3">
            	<div class="p1"><a href="#">会员购票</a></div>
                <div class="p2">已有<span>2000</span>人购票</div>
            </div>
        
        </div>
    	<div class="nofl"></div>
    </div>
    
    
    <div class="center clearfix">
		<div class="con-l">
        	<ul class="dyx_js">
            	<li id="f5g1" onclick="h51()" class="p1">热播电影</li>
                <li id="f5g2" onclick="h52()" class="q2">影院介绍</li>
                <li id="f5g3" onclick="h53()" class="q2">购电子票</li>
                <li id="f5g4" onclick="h54()" class="q2">影院点评</li>
                <li id="f5g5" onclick="h55()" class="q2">购票须知</li>
            
            </ul>
			<!--y-city end-->
			<div id="xf5g1" class="dyaxcom">
				<!--<ul class="yy_rm1">
                	<li class="p1">今天 星期六</li><li>明天 星期天</li><li>11月11日 星期一 </li><li>11月10日 星期二</li>
                </ul>
                <div class="yy_rm2">
                	<div class="p1">正在热映：</div>
                    <ul class="p2">
                    	<li class="q1">全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    	<li>全部</li>
                    </ul>
                </div>-->
                <ul class="yy_rm3">
                	<#if movies?has_content>
                	<#list movies as movie>
                	<li><a href="${ctx}/movie/${movie.id}${suffix}">
                    	<div class="yy_rm3l"><img src="${tagUtils.getFileFullPath(movie.icon)}" width="92" height="123"></div>
                    	<div class="yy_rm3r">
                        	<div class="p1">${movie.name}</div>
                        	<div class="p2">演员 :<span>
                        		<#if movie.movieActors?has_content>
	                        	<#list movie.movieActors as movieActors>
	                        		${movieActors.actor.name}/
	                        	</#list>
	                        	</#if>
							</span></div>
                        	<div class="p2">类型 :<span> ${dicSetting.getParameterValue("movie.type." + movie.type)}</span></div>
                        	<div class="p2">片长 :<span> ${movie.time} 分钟</span></div>
                        	<div class="p2">语言 :<span> ${tagUtils.formatDate((movie.onlineTime)!0, 'yyyy-MM-dd')}</span></div>
                        	<div class="p4">评分 :<span>${movie.mark}</span></div>
                        </div>
                        <div class="nofl"></div></a>
                    </li>
                    </#list>
                	</#if>
                	
                
                </ul>
                <!-- End .pagination -->
			</div>
            <div id="xf5g2" class="dyaxcom2">
				<div class="dyx_x1">
                	<div class="p1">影院简介</div>
                    <div class="p2">
                    封号南拳王的洪拳武术家暴毙于隧道中的自家车上，尸体并没刀伤或枪伤痕迹，初步监定死于重拳，重案组总督察陆玄心（杨采妮 饰）负责调查，对死因感到不解；此时却收到监狱传来消息，犯了误杀罪的服刑犯人夏侯武（甄子丹 饰），主动表示愿意协助警方调查，条件是即时假释。 
　　“先练拳次练腿，后擒拿用兵器，由内而外”，夏侯武断言凶手行凶目的并非寻仇，且会继续犯案。果然，号称北腿王、擒拿王、兵器王的武术界精英相继遇害，均死于各自成名的武功之下。得到夏侯武提供线索，以及现场遗下的仿古暗器“堂前燕”，陆玄心终于发现凶手藏身之所，这时夏侯武却违反假释令突然失踪。 
　　两个武艺超凡的杀人犯同时在逃，他们是敌是友？两雄相遇，一场巅峰对决势所难免。 
                    </div>
                    <div class="p3">
                    <img src="${ctx}/resources/images/dy/pic2.jpg" width="778" height="500">
                    </div>
                
                </div>	
                <div class="dyz_tu2_b2">地图导航</div>
                <div class="dyz_tu2_b3"><img src="${ctx}/resources/images/dy/map1.jpg" width="779" height="350"></div>
                <!-- End .pagination -->
			</div>
            <div id="xf5g3" class="dyaxcom2">
				<div class="yy_gyp">
               	   <div class="yy_gyp1">
                    	<div class="p1">影院价:</div>
                    	<div class="p2">
                        	<div class="q1"> 2D <span>40元</span></div>
                        	<div class="q1">3D <span>40元</span></div>
                    	</div>
                    </div>
                    <div class="yy_gyp1">
                    	<div class="p1">云票票价:</div>
                    	<div class="p2">
                        	<div class="q2"><label><div class="a1"><input type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_0"></div>2D <span>30元</span></label></div>
                        	<div class="q2"><label><div class="a1"><input type="radio" name="RadioGroup1" value="单选" id="RadioGroup1_1"></div>2D <span>30元</span></label></div>
                        </div>
                    </div>
                    <div class="yy_gyp2">
                    	<div class="yy_gyp2_1">数量:</div>
                    	<div class="yy_gyp2_2">
                        	<div class="p1">-</div>
                        	<div class="p2"><input type="text" value="0"></div>
                        	<div class="p3">+</div>
                        </div>
                    </div>
                    <div class="yy_gyp2">
                    	<div class="yy_gyp2_1" style=" color:#666;">接收手机号</div>
                    	<div class="yy_gyp2_3">
                        	<div class="p1"><input type="text"></div>
                        	<div class="p2"><input type="submit" value="购票"></div>
                            <div class="p3"><span>手机号不能为空!</span> 购买成功后，取票验证码将发送到此手
机号，号码填写后将无法修改，请正确填写！</div>
                        </div>
                    </div>
                    <div class="yy_gyp3">温馨提示:</div>
                    <div class="yy_gyp4">电子券有效期为三个月，可在有效期内至影院售票处兑换指定版本影票一张，不限时段，不支持线上兑换</div>
                </div>	
                <!-- End .pagination -->
			</div>
            <div id="xf5g4" class="dyaxcom2">
				<div class="dyx_x3">
                	<div class="dyx_x3p">
                    	<div class="dyx_x3pl">我要点评:</div>
                    	<div class="dyx_x3pr">
                        	<div class="p1">
                            	<label>
                                    <div class="q1"><input type="radio" name="RadioGroup1" value="好评" id="RadioGroup1_1"></div>
                                    <div class="q2">好评</div>
                                </label>
                            </div>
                        	<div class="p1">
                            	<label>
                                    <div class="q1"><input type="radio" name="RadioGroup1" value="中评" id="RadioGroup1_1"></div>
                                    <div class="q2">中评</div>
                                </label>                            
                            </div>
                            <div class="p1">
                            	<label>
                                    <div class="q1"><input type="radio" name="RadioGroup1" value="差评" id="RadioGroup1_1"></div>
                                    <div class="q2">差评</div>
                                </label>                            
                            </div>
                        </div>
                    </div>
                    <div class="dyx_x3pk">
                    	<textarea name="" cols="" rows="">温馨提示:您的评价会给影迷在购票上带来影响，请客观评价您对影院感受；</textarea>
                    </div>
                	<div class="dyx_x3pq">
                    	<div class="dyx_x3pl_a"><input type="submit" value="提交点评"></div>
                    </div>
                    
                    <ul class="dyx_x3plb">
                    	<li onclick="hx1()" id="fxg1" class="p1">全部评价(<span>2000</span>)</li>
                    	<li onclick="hx2()" id="fxg2">好评(<span>900</span>)</li>
                    	<li onclick="hx3()" id="fxg3">中评(<span>900</span>)</li>
                    	<li onclick="hx4()" id="fxg4">差评(<span>900</span>)</li>
                    </ul>
                    <div id="xfxg1" class="dyxaxpjx">
                        <ul class="dyx_pjx">
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪1<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            
                        
                        </ul>
                        <div class="nofl"></div>
                        <div class="pagination">
                            <a class="noclick">««</a><a class="noclick">上一页</a><a href="#" class="pagination-current" title="1">1</a> <a href="#" title="2">2</a><a href="#" title="3">3</a><a href="#" title="4">4</a><a href="#" title="5">5</a><a href="#">下一页</a><a href="#">»»</a>
                        </div>
                    <!-- End .pagination -->
                    </div>
                    <div id="xfxg2" class="dyxaxpjx1">
                        <ul class="dyx_pjx">
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪2<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            
                        
                        </ul>
                        <div class="nofl"></div>
                        <div class="pagination">
                            <a class="noclick">««</a><a class="noclick">上一页</a><a href="#" class="pagination-current" title="1">1</a> <a href="#" title="2">2</a><a href="#" title="3">3</a><a href="#" title="4">4</a><a href="#" title="5">5</a><a href="#">下一页</a><a href="#">»»</a>
                        </div>
                    <!-- End .pagination -->
                    </div>
                    <div id="xfxg3" class="dyxaxpjx1">
                        <ul class="dyx_pjx">
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪3<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            
                        
                        </ul>
                        <div class="nofl"></div>
                        <div class="pagination">
                            <a class="noclick">««</a><a class="noclick">上一页</a><a href="#" class="pagination-current" title="1">1</a> <a href="#" title="2">2</a><a href="#" title="3">3</a><a href="#" title="4">4</a><a href="#" title="5">5</a><a href="#">下一页</a><a href="#">»»</a>
                        </div>
                    <!-- End .pagination -->
                    </div>
                    <div id="xfxg4" class="dyxaxpjx1">
                        <ul class="dyx_pjx">
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪4<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao2.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao3.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            <li>
                                <div class="dyx_pjxl"><img src="${ctx}/resources/images/dy/pic.jpg" width="66" height="70"></div>
                                <div class="dyx_pjxr">
                                    <div class="p1">小聂爱小猪<img src="${ctx}/resources/images/dy/hao1.jpg" width="42" height="20"></div>
                                    <div class="p2">还不错，又进又方便，环境也很好，还有hello kitty厅 很可爱的 喜欢</div>
                                    <div class="p3">观影地点：<a href="#">杭州UME国际影城西城店</a>    <span>2014年6月22日11:48</span></div>
                                </div>
                                <div class="nofl"></div>
                            </li>
                            
                        
                        </ul>
                        <div class="nofl"></div>
                        <div class="pagination">
                            <a class="noclick">««</a><a class="noclick">上一页</a><a href="#" class="pagination-current" title="1">1</a> <a href="#" title="2">2</a><a href="#" title="3">3</a><a href="#" title="4">4</a><a href="#" title="5">5</a><a href="#">下一页</a><a href="#">»»</a>
                        </div>
                    <!-- End .pagination -->
                    </div>
                    
                    
                    
                    
                </div>	
                
                <!-- End .pagination -->
			</div>
            <div id="xf5g5" class="dyaxcom2">
				<div class="yyx_xz">
                	<dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                    <dl>
                    	<dt>Q:可以一次购买不同场次的影片吗？</dt>
                        <dd>A：我们只支持同一场次的影票购买，如若您想购买不同场次的电影，请分别进行购买。</dd>
                    </dl>
                </div>	
                <!-- End .pagination -->
			</div>
			<!--y-cinema end-->
            
		</div>
		<!--con-l end-->
		<#include "../public/common_page_right.ftl"/>
		<!--con-r end-->
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>