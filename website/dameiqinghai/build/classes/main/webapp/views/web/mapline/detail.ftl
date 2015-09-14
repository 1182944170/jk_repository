<title>线路详情</title>

<#include "../public/top.ftl">

<script type="text/javascript">
	var yellowStar ="${ctx}/resources/images/w_pf.png";
	var darkStar = "${ctx}/resources/images/w_pfh.png";
	$(function(){
		var score = "";
		var str ='' ;
		<#list guideList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("guideListClass","guideListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list rcarList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("rcarListClass","rcarListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list foodList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("foodListClass","foodListChild",str,score);
		
		str = (str=='') ? '' : str='';
		<#list hotelList as u>
			str +="${u.photos!}"+";";
		</#list>
		init("hotelListClass","hotelListChild",str,score);
	})
	
	function init(oclass,childclass,str,score){
		var score = score.split(",");
		var photoArray=str.substring(0,str.length-1);
		$("."+oclass+"").each(function(i){
			var p = photoArray.split(";")[i];
			if(p != '' && p != null){
				if(p.indexOf(",")>0){
					p = p.split(",");
					p = p[0];
					p = p.substring(1,p.length);
				}
				$(this).find("img").removeAttr("src");
				$(this).find("img").attr("src","${tagUtils.getFileFullPath('"+p+"')}");
			}
			$("."+childclass+"").each(function(k){
					if(i == k){
					 	appendScore($(this),parseInt(score[i]),yellowStar,darkStar);
					 	return false;
					 }
					
			})
		})
	
	}
	function appendScore(obj,score,y,d){
		if(score == 'NaN' || score == '' ) score = 0;
		for(i=0;i<5;i++){
			if(i<score){
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+y+"");
			}else{
				obj.find("img").eq(i).removeAttr("src");
				obj.find("img").eq(i).attr("src",""+d+"");
			}
		}
	}
</script>

<div class="w_main">
    <div class="w_jiudian_top">
    	<h4><a href="#">首页</a>&gt;<a href="#">线路</a>&gt;<em>如家酒店单人房</em></h4>
    	<script type="text/javascript" src="${ctx}/resources/js/jquery.SuperSlide.2.1.js"></script>
        <div id="w_slidebox" class="w_slidebox fl">
            <div class="bd">
                <ul>
                    <li><img src="${ctx}/resources/images/mm_img03.jpg" width="600" height="457" alt="" /></li>
                    <li><img src="${ctx}/resources/images/w_jdpic02.jpg" width="600" height="457" alt="" /></li>
                    <li><img src="${ctx}/resources/images/mm_img03.jpg" width="600" height="457" alt="" /></li>
                    <li><img src="${ctx}/resources/images/w_jdpic02.jpg" width="600" height="457" alt="" /></li>
                    <li><img src="${ctx}/resources/images/mm_img03.jpg" width="600" height="457" alt="" /></li>
                </ul>
            </div>
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>    
        </div>    
        <script type="text/javascript">
        jQuery(".w_slidebox").slide({mainCell:".bd ul",autoPlay:true});
        </script>
        <div class="w_jiudian_topr fr">
        	<div class="w_trax">
                <h5>&lt;青海湖-茶卡-敦煌-嘉峪关-张掖-门源-祁连双飞3
日游&gt;翻越达坂山</h5>
                <span>推荐理由：仅售76元，价值289元精致大床房/时尚标准房4小时，可连续入住，内有热水免费WiFi！</span>
                <p><em>出发地：</em>西宁</p>
                <p><em>目的地：</em>茶卡盐湖  青海湖  祁连县  肃南马蹄寺风景名胜区  山丹军马场  门源县互助北山国家森林公园</p>
                <p><em>预算费用：</em><i>5000元左右</i></p>
            </div>            
        </div>
    </div>
    <div class="w_mainl fl">
        <div class="w_jdtops">
        	<div class="w_jthcs">
                <ul>
                    <li><a href="#w_xh01">路线简介</a></li>
                    <li><a href="#w_xh02">导游租车</a></li>
                    <li><a href="#w_xh03">酒店美食</a></li>
                    <li><a href="#w_xh04">费用须知</a></li>
                    <li><a href="#w_xh05">温馨提示</a></li>
                </ul>
            </div>
            </div>
            <div class="w_xlcon">
                <div class="w_xladr" id="w_xh01">
                    <h4><img src="${ctx}/resources/images/aa_ico01.png" alt="" /><em>线路简介</em></h4>
                    <div class="w_xlxq">
                    	<h6><img src="${ctx}/resources/images/aa_ico02.png" /><em>第一天   西宁—茶卡盐湖-青海湖</em></h6>
                        <p>【早上】7点起床，7点半集合，早餐后出发，经过日月山倒淌河，我们直接前往有着天空之镜之称的茶卡盐湖。茶卡盐湖与塔尔寺、青海湖、孟达天池齐名，被称作“青海四大景”，同时还被国家旅游地理杂志评为“人一生必去的55个地方”之一。同处于地球维度30°，茶卡盐湖在风光程度上不输于南美委内瑞拉的尤尼盐湖，看着各种盐颗粒结晶长成的晶体，异常美丽。我们去茶卡盐湖照镜子，牵手跳跃，端起相机把这一刻定格，仿佛与天空融为一体。 
【下午】前往青海湖（茶卡盐湖—青海湖：车程2小时左右）。途中可以看到青海湖中央的白色小岛—海心山。那是女尼修行的地方，据说她们只在冬季冰封时出来储备食物，夏天潜心修佛中途不再出来。还可以望见公路两侧若隐若现的雪山，前往茶卡盐湖还要穿过海拔3817公里的橡皮山。 
7、8月份是青海湖美好的季节，沿途可以看到大片的油菜花，和紫色的花海、拿起你的相机尽情拍照吧。公路边有时也能看到虔诚的叩头朝拜者（他们五体投地绕湖一周360公里，虔诚的膜拜），游览金银滩大草原，沿途茫茫草原，牛羊成群，一望无际，王洛宾的那首《在那遥远的地方》就出自于金银滩草原，王洛宾先生早在1940年前后就曾经多次来到金银滩草原采风，吃住在千户长同曲乎的家中，与卓玛一家人结下深厚友谊。 
穿过油菜花田下到青海湖，人在花中美，青海湖如画。别担心，这里的牧民是我们的兄弟，跟着我们走进他们的家中，问候下他们家的牛羊。队员还可以自由选择参加骑马，感受在草原上风驰电掣的感觉；沿途可以看到青藏铁路，远处的蓝天白云，下方成群的牛羊，笔直的公路，构成一幅绝美的画面； 
【傍晚】抵达青海湖畔我们下榻的酒店，这里依山傍水，风景秀丽。稍作休息，可以去湖边戏水、堆玛尼石， 烟波浩淼、碧波连天的青海湖就像一盏巨大的翡翠玉盘，镶嵌在青藏高原东北部的高山、草原间，构成了一幅群山、湖水、草原相映成趣的壮美风光和绮丽景色。坐在湖边，对着湖面静静地放空自己，晚上再一起拍湖边的灿烂星空。今天我们安排有一整个下午、一整个傍晚、明天早晨在青海湖畔体验幸福~收获快乐！</p>
						<h6><img src="${ctx}/resources/images/aa_ico02.png" /><em>第二天   西宁—茶卡盐湖-青海湖</em></h6>
                        <p>【早上】7点起床，7点半集合，早餐后出发，经过日月山倒淌河，我们直接前往有着天空之镜之称的茶卡盐湖。茶卡盐湖与塔尔寺、青海湖、孟达天池齐名，被称作“青海四大景”，同时还被国家旅游地理杂志评为“人一生必去的55个地方”之一。同处于地球维度30°，茶卡盐湖在风光程度上不输于南美委内瑞拉的尤尼盐湖，看着各种盐颗粒结晶长成的晶体，异常美丽。我们去茶卡盐湖照镜子，牵手跳跃，端起相机把这一刻定格，仿佛与天空融为一体。 
【下午】前往青海湖（茶卡盐湖—青海湖：车程2小时左右）。途中可以看到青海湖中央的白色小岛—海心山。那是女尼修行的地方，据说她们只在冬季冰封时出来储备食物，夏天潜心修佛中途不再出来。还可以望见公路两侧若隐若现的雪山，前往茶卡盐湖还要穿过海拔3817公里的橡皮山。 
7、8月份是青海湖美好的季节，沿途可以看到大片的油菜花，和紫色的花海、拿起你的相机尽情拍照吧。公路边有时也能看到虔诚的叩头朝拜者（他们五体投地绕湖一周360公里，虔诚的膜拜），游览金银滩大草原，沿途茫茫草原，牛羊成群，一望无际，王洛宾的那首《在那遥远的地方》就出自于金银滩草原，王洛宾先生早在1940年前后就曾经多次来到金银滩草原采风，吃住在千户长同曲乎的家中，与卓玛一家人结下深厚友谊。 
穿过油菜花田下到青海湖，人在花中美，青海湖如画。别担心，这里的牧民是我们的兄弟，跟着我们走进他们的家中，问候下他们家的牛羊。队员还可以自由选择参加骑马，感受在草原上风驰电掣的感觉；沿途可以看到青藏铁路，远处的蓝天白云，下方成群的牛羊，笔直的公路，构成一幅绝美的画面； 
【傍晚】抵达青海湖畔我们下榻的酒店，这里依山傍水，风景秀丽。稍作休息，可以去湖边戏水、堆玛尼石， 烟波浩淼、碧波连天的青海湖就像一盏巨大的翡翠玉盘，镶嵌在青藏高原东北部的高山、草原间，构成了一幅群山、湖水、草原相映成趣的壮美风光和绮丽景色。坐在湖边，对着湖面静静地放空自己，晚上再一起拍湖边的灿烂星空。今天我们安排有一整个下午、一整个傍晚、明天早晨在青海湖畔体验幸福~收获快乐！</p>
						<h6><img src="${ctx}/resources/images/aa_ico02.png" /><em>第三天   西宁—茶卡盐湖-青海湖</em></h6>
                        <p>【早上】7点起床，7点半集合，早餐后出发，经过日月山倒淌河，我们直接前往有着天空之镜之称的茶卡盐湖。茶卡盐湖与塔尔寺、青海湖、孟达天池齐名，被称作“青海四大景”，同时还被国家旅游地理杂志评为“人一生必去的55个地方”之一。同处于地球维度30°，茶卡盐湖在风光程度上不输于南美委内瑞拉的尤尼盐湖，看着各种盐颗粒结晶长成的晶体，异常美丽。我们去茶卡盐湖照镜子，牵手跳跃，端起相机把这一刻定格，仿佛与天空融为一体。 
【下午】前往青海湖（茶卡盐湖—青海湖：车程2小时左右）。途中可以看到青海湖中央的白色小岛—海心山。那是女尼修行的地方，据说她们只在冬季冰封时出来储备食物，夏天潜心修佛中途不再出来。还可以望见公路两侧若隐若现的雪山，前往茶卡盐湖还要穿过海拔3817公里的橡皮山。 
7、8月份是青海湖美好的季节，沿途可以看到大片的油菜花，和紫色的花海、拿起你的相机尽情拍照吧。公路边有时也能看到虔诚的叩头朝拜者（他们五体投地绕湖一周360公里，虔诚的膜拜），游览金银滩大草原，沿途茫茫草原，牛羊成群，一望无际，王洛宾的那首《在那遥远的地方》就出自于金银滩草原，王洛宾先生早在1940年前后就曾经多次来到金银滩草原采风，吃住在千户长同曲乎的家中，与卓玛一家人结下深厚友谊。 
穿过油菜花田下到青海湖，人在花中美，青海湖如画。别担心，这里的牧民是我们的兄弟，跟着我们走进他们的家中，问候下他们家的牛羊。队员还可以自由选择参加骑马，感受在草原上风驰电掣的感觉；沿途可以看到青藏铁路，远处的蓝天白云，下方成群的牛羊，笔直的公路，构成一幅绝美的画面； 
【傍晚】抵达青海湖畔我们下榻的酒店，这里依山傍水，风景秀丽。稍作休息，可以去湖边戏水、堆玛尼石， 烟波浩淼、碧波连天的青海湖就像一盏巨大的翡翠玉盘，镶嵌在青藏高原东北部的高山、草原间，构成了一幅群山、湖水、草原相映成趣的壮美风光和绮丽景色。坐在湖边，对着湖面静静地放空自己，晚上再一起拍湖边的灿烂星空。今天我们安排有一整个下午、一整个傍晚、明天早晨在青海湖畔体验幸福~收获快乐！</p>
                    </div>
                </div>
                <div class="w_xladr" id="w_xh02">
                    <h4><img src="${ctx}/resources/images/aa_ico03.png" alt="" /><em>导游租车</em></h4>
                    <div class="w_guidedd">
                    	<div class="w_gdcon">
                            <div class="w_food">
                                <dl>
                                	<#list guideList as u>
	                                    <dd>
	                                        <a href="${ctx}/guide/detail-${u.id}${suffix}" class="guideListClass"><img src="${ctx}/resources/images/w_guide_pic01.jpg" width="260" height="155" alt="" /></a>
	                                        <div class="w_guidetx">  
	                                            <ul>
	                                                <li><a href="${ctx}/guide/detail-${u.id}${suffix}" ><img src="${tagUtils.getFileFullPath(u.user.photo!)}" width="36" height="36" alt="" /></a></li>
	                                                <li>
	                                                    <div>
	                                                        <a href="${ctx}/guide/detail-${u.id}${suffix}"><i>${u.guideName!}</i>
	                                                        <#if u.sex == 1>
								                        		<img src="${ctx}/resources/images/w_sex2.png" width="10" height="15" alt="" />
								                        	<#else>
								                        		<img src="${ctx}/resources/images/w_sex1.png" width="10" height="15" alt="" />
								                        	</#if></a>
	                                                        <span><em>￥</em>${u.price!}<em>/天</em></span>
	                                                    </div>
	                                                    <div><b>实名</b><b>导游证</b></div>
	                                                </li>
	                                            </ul> 
	                                        </div>
	                                        <p>${u.guideDesc!}</p>
	                                        <div class="guideListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u></div>
	                                    </dd>
                                    </#list>
                                    
                                    <#list rcarList as u>
	                                    <dd>
	                                        <a href="${ctx}/rcar/detail-${u.id}${suffix}" class="rcarListClass"><img src="${ctx}/resources/images/w_car.jpg" width="260" height="155" alt="" /></a>
	                                        <div class="w_guidetx">  
	                                            <ul>
	                                                <li><a href="${ctx}/rcar/detail-${u.id}${suffix}"><img src="${tagUtils.getFileFullPath(u.user.photo!)}" width="36" height="36" alt="" /></a></li>
	                                                <li>
	                                                    <div>
	                                                        <a href="${ctx}/rcar/detail-${u.id}${suffix}"><i>${u.rcarName!}</i></a>
	                                                        <span><em>￥</em>${u.price!}<em>/天</em></span>
	                                                    </div>
	                                                    <div><b>实名</b><b>行驶证</b><b>司机</b></div>
	                                                </li>
	                                            </ul> 
	                                        </div>
	                                        <p><u>${u.carModel!}</u>${u.rcarDesc!}</p>
	                                        <div class="rcarListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u></div>
	                                    </dd>
                                    </#list>
                                </dl>                   
                            </div>
                        </div>
                    </div>
                </div>
                <div class="w_xladr" id="w_xh03">
                    <h4><img src="${ctx}/resources/images/aa_ico04.png" alt="" /><em>酒店美食</em></h4>
                    <div class="w_jiuda">
                        <div class="w_food">
                            <dl>
                            	<#list foodList as u>
	                                <dd>
	                                    <a href="${ctx}/food/detail-${u.id}${suffix}"><img src="${ctx}/resources/images/w_hpic03.jpg" width="196" height="134" alt="" /></a>
	                                    <h5><a href="${ctx}/food/detail-${u.id}${suffix}">${u.shopName!}</a></h5>
	                                    <p>${u.info!}</p>
	                                    <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                                    <div class="foodListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u><i>售出302</i></div>
	                                </dd>
                                </#list>
                            	<#list hotelList as u>
	                                <dd>
	                                    <a href="${ctx}/hotel/detail-${u.id}${suffix}" class="hotelListClass"><img src="${ctx}/resources/images/w_hpic03.jpg" width="196" height="134" alt="" /></a>
	                                    <h5><a href="${ctx}/hotel/detail-${u.id}${suffix}">${u.shopName!}</a></h5>
	                                    <p>${u.info!}</p>
	                                    <span><em>￥</em>${u.price!}<i>￥${u.oldPrice!}</i></span>
	                                    <div class="hotelListChild"><u><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pf.png" /><img src="${ctx}/resources/images/w_pfh.png" />4分</u><i>售出302</i></div>
	                                </dd>
                                </#list>
                            </dl>                   
                        </div>
                    </div>
                </div>
                <div class="w_xladr" id="w_xh04">
                    <h4><img src="${ctx}/resources/images/aa_ico05.png" alt="" /><em>费用须知</em></h4>
                    <div class="fyxuz">
                    	<p>1.交通：往返团队经济舱机票含税费1200元</p>
                        <p>2.小交通：景区内用车（张掖七彩丹霞区间车）。 </p>
                        <p>3.门票：塔尔寺12元、可鲁克湖28元、玉门关90元、莫高窟50元、鸣沙山69元、月牙泉56元、嘉峪关城楼58元、七彩丹霞。 </p>
                        <p>4.儿童价标准：①儿童指12周岁且1.2米以下，不含门票</p>
                    </div>
                </div>
                <div class="w_xladr" id="w_xh05">
                    <h4><img src="${ctx}/resources/images/aa_ico06.png" alt="" /><em>温馨提示</em></h4>
                    <div class="adxuz">
                    	<p>1.请提前2小时左右抵达机场，检查证件，避免误机，请做好身体方面的准备，自带常用药品，如：感冒药、腹泻药、白服宁、保心丸等药品及创可帖，冲锋衣、户外背包、抓绒衣裤、户外帽、户外鞋，排汗内衣、户外眼镜、手套；</p>
                        <p>2.兰州特色小吃主要集中在大众巷美食街和正宁路夜市，小伙伴们可去尝尝其中极具代表性的有马子禄牛肉面、吾穆勒蓬灰牛肉面、阿福杏皮水、阿西娅羊羔肉、清真胖妈妈手抓餐厅、老马家牛奶鸡蛋醪糟、杜记甜食灰豆子、马冬青金城面片第一炒、一定记着在吃大漠烤羊肉的时候，泡上一杯香甜的三炮台盖碗茶哦~；</p>
                        <p>3.今天抵达早的团友，也可根据个人情况自行前往兰州市内著名的黄河风情线，看看被称为天下黄河第一桥的【中山铁桥】【黄河母亲塑像】【水车园】哦~第一天抵达，总要用心爱的相机留下自己的足迹哦~；</p>
                        <p>4.甘肃海拔1100米左右，西宁海拔2200米，和东部地区存在一定的时差（约相差1小时）昼夜温差较大，要注意携带御寒衣服，以免感冒；即使在夏季也建议您带两件较厚的衣物，及时增添衣物；</p>
                    </div>
                </div>
            </div>
    </div>
    <div class="w_mainr fr">
    	<div class="w_guider">
            <div class="w_tjlx">
                <h3>热门路线<a href="#">更多&gt;&gt;</a></h3>
                <ul>
                    <li>
                        <div class="w_load">
                            <a href="#"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="#">西宁三日游玩</a></h5>
                            <p>出发地：<em>青海飞机场</em></p>
                            <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                            <p>预算费用：<em>3000元</em></p>
                            <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                        </div>
                        <div class="w_load">
                            <a href="#"><img src="${ctx}/resources/images/w_pic01.jpg" width="295" height="121" alt="" /></a>
                            <h5><a href="#">西宁三日游玩</a></h5>
                            <p>出发地：<em>青海飞机场</em></p>
                            <p>目的地：<i>西宁</i><span>适宜月份：<em>12月-3月</em></span></p>
                            <p>预算费用：<em>3000元</em></p>
                            <h6>推荐理由：一提到华盛顿就会让人想起权利、政治以及枯燥无味。它无疑是世</h6>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic06.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic07.jpg" width="320" height="90" alt="" /></a></div>
        <div class="w_ggw"><a href="#"><img src="${ctx}/resources/images/w_ypic08.jpg" width="320" height="144" alt="" /></a></div>
    </div>
</div>

<#include "../public/foot.ftl">