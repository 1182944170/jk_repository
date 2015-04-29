<#include "../public/top.ftl"/>
<#include "../public/head.ftl"/>

<!--content begin-->
<div class="content">
	<div class="dyx_ct">
    	<div class="dyx_ctl">
        	<div class="dyrm1_l"><div class="dyrm1_l2"><img src="${tagUtils.getFileFullPath(movie.icon)}" width="152" height="200"></div></div>
            <div class="dyx_ctl2">
            	<div class="p1">${movie.name}</div>	
            	<div class="p2">导演：<span> 陈德森</span></div>
            	<div class="p2">主演：<span>
            	<#if movie.movieActors?has_content>
            	<#list movie.movieActors as movieActors>
            		${movieActors.actor.name} 
            	</#list>
            	</#if></span></div>
            	<div class="p2">类型：<span>${dicSetting.getParameterValue("movie.type." + movie.type)}</span></div>
            	<div class="p2">语言/片长：<span>${dicSetting.getParameterValue("movie.lang." + movie.lang)}/${movie.time}分钟</span></div>
            	<div class="p2">地区/国家：<span>${dicSetting.getParameterValue("movie.countryArea." + movie.countryArea)}</span></div>
            	<div class="p2">上映日期：<span>${tagUtils.formatDate((movie.onlineTime)!0, 'yyyy-MM-dd')}</span></div>
            </div>
        </div>
    	<div class="dyx_ctr">
        	<div class="dyx_ctr1">
            	<div class="p1">评分：</div>
            	<!--<div class="p2">
                	<div class="q1"><img src="${ctx}/resources/images/dy/dj.jpg" width="140" height="22"></div>
                	<div class="q2">完美，值得一看</div>
                </div>-->
            	<div class="p3">${movie.mark}</div>
                <div class=" nofl"></div>
            </div>
            <div class="dyx_ctr2">
            	<div class="p1"><a href="#">我要评价</a></div>
                <div class="p2">已有<span>22</span>条评论</div>
            </div>
            <div class="dyx_ctr3">
            	<div class="p1"><a href="#">点击购票</a></div>
                <div class="p2">已有<span>2000</span>人购票</div>
            </div>
        
        </div>
    	<div class="nofl"></div>
    </div>
    
    
    <div class="center clearfix">
		<div class="con-l">
        	<ul class="dyx_js">
            	<li id="fg1" onclick="h1()" class="p1">影片介绍</li>
                <li id="fg2" onclick="h2()">购电子票</li>
                <li id="fg3" onclick="h3()">影视点评</li>
            
            </ul>
			<!--y-city end-->
			<div id="xfg1" class="dyaxcom">
				<div class="dyx_x1">
                	<div class="p1">${movie.name}剧情介绍</div>
                    <div class="p2">
                    ${movie.content}
                    </div>
                </div>	
                
				<!-- End .pagination -->
			</div>
            <div id="xfg2" class="dyaxcom2">
				<div class="dyz_tu1">
                	<img src="${ctx}/resources/images/dy/zf1.jpg" width="778" height="41">
                </div>
                <div class="dyz_tu2">
                	<div class="dyz_tu2l">
                    	<div class="dg_xyy">
                        	<div class="p1">选择影院</div>
                            <div class="p2">
                            	<div class="q1">请选择区</div>
                            	<div class="q2"><img src="${ctx}/resources/images/dy/ioc1.png" width="11" height="8"></div>
                            	<ul class="q3" style=" display:none;">
                                	<li>西湖区</li>
                                	<li>西湖区</li>
                                	<li>西湖区</li>
                                </ul>
                            </div>
                            <div class="p3">
                            	<div class="q1"><input type="text"></div>
                            	<div class="q2"><input type="submit" value=" "></div>
                            </div>
                        </div>
                        <ul class="dg_xyy1">
                        	<li><a href="#">杭州近江电影大世界</a></li>
                        	<li><a href="#">杭州奥斯卡电影大世界</a></li>
                        	<li><a href="#">金逸杭州南宋御街店</a></li>
                        	<li><a href="#">杭州翠苑电影大世界</a></li>
                        	<li><a href="#">杭州上影影城</a></li>
                        	<li><a href="#">杭州和达时代电影大世界</a></li>
                            <li><a href="#">杭州近江电影大世界</a></li>
                        	<li><a href="#">杭州奥斯卡电影大世界</a></li>
                        	<li><a href="#">金逸杭州南宋御街店</a></li>
                        	<li><a href="#">杭州翠苑电影大世界</a></li>
                        	<li><a href="#">杭州上影影城</a></li>
                        	<li><a href="#">杭州和达时代电影大世界</a></li>
                            <li><a href="#">杭州近江电影大世界</a></li>
                        	<li><a href="#">杭州奥斯卡电影大世界</a></li>
                        	<li><a href="#">金逸杭州南宋御街店</a></li>
                        	<li><a href="#">杭州翠苑电影大世界</a></li>
                        	<li><a href="#">杭州上影影城</a></li>
                        	<li><a href="#">杭州和达时代电影大世界</a></li>
                        </ul>
                    </div>
                	<div class="dyz_tu2r">
                    	<div class="dg_xyyr">版本和数量</div>
                    	<div class="dg_xyyr1">2D电影票40元一张</div>
                    	<div class="dg_xyyr1">3D电影票50元一张</div>
                    	<div class="dg_xyyr2">
                        	<div class="p0">数量:</div>
                        	<div class="p1">-</div>
                        	<div class="p2"><input type="text" value="0"></div>
                        	<div class="p3">+</div>
                        
                        </div>
                        <div class="dg_xyyr3">一个订单最多可购买<span>10</span>张电子券</div>
                    	<div class="dg_xyyr4"><input type="submit" value="下一步"></div>
                    
                    </div>
                    <div class="nofl"></div>
                </div>
                
                <ul class="dyz_tu2_b">
                	<li><a href="#">电子票有效期三个月</a></li>
                	<li class="p1"><a href="#">杭州和达时代电影大世界</a></li>
                	<li><a href="#">查看地图</a></li>
                	<li>1张2D电影票合计:<span>43</span>元</li>
                </ul>
                <div class="dyz_tu2_b1">
                1.电子券有效期自购买之日起3个自然月，有效期内可到影院票台兑换购买版本的电影票。
<br>2.电子券分2D和3D版本，分别支持兑换2D和3D电影，请购买对应版的电子券。
<br>3.兑换特殊影片（超长片、特殊限价片、特殊3D影片）等，以影院公告为准，部分影片、部分影院兑换时需补差价；
<br>4.兑换特殊场次（如法定节日、电影节、首映式、情人节2.14、平安夜12.24、圣诞节12.25）、VIP厅、情侣厅等，以影院公告为准；
<br>5.影院会根据自身的价格体系打印出票，可能存在打印价格与您订购的价格不符情况；
<br>6.看购电影网发布的影讯仅供参考，如有变更以影院现场公告为准。
                
                </div>
                <div class="dyz_tu2_b2">地图导航</div>
                <div class="dyz_tu2_b3"><img src="${ctx}/resources/images/dy/map1.jpg" width="779" height="350"></div>
				<!-- End .pagination -->
			</div>
            <div id="xfg3" class="dyaxcom2">
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
                    <div class="dyx_x3p">
                    	<div class="dyx_x3pl">观影地点:</div>
                    	<div class="dyx_x3pr_a">
                        	<input type="text">
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
                
			</div>
			<!--y-cinema end-->
		</div>
		<#include "../public/common_page_right.ftl"/>
	</div>
</div>
<!--content end-->
<#include "../public/foot.ftl"/>