<meta http-equiv="description" content="" />
<meta http-equiv="keywords" content="" />
<title>联系我们</title>
<div class="main">
    <!--联系我们-->
        <!--标题-->
      <div class="tit">
          <h4>杭州大唐纹身(tatto)</h4>
          <h3><a href="contact.html">联系我们</a></h3>
          <h5>十年纹身老字号</h5>
      </div>    
      <!--百度地图-->
      <script type="text/javascript" src="http://api.map.baidu.com/api?v=1.4"></script>
	  <script type="text/javascript" src="http://api.map.baidu.com/library/SearchControl/1.4/src/SearchControl_min.js"></script>
      <script type="text/javascript" src="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.js"></script>      
      <link href="css/TrafficControl_min.css" rel="stylesheet" type="text/css" />     
          <div id="searchBox"></div>
          <div id="map_container"></div>      
          <script type="text/javascript">
          // 创建地图对象并初始化
          var mp = new BMap.Map("map_container",{
              enableHighResolution: true //是否开启高清
          });
          var point = new BMap.Point(116.404, 39.915);
          mp.centerAndZoom(point, 14); //初始化地图
          mp.enableInertialDragging(); //开启关系拖拽
          mp.enableScrollWheelZoom();  //开启鼠标滚动缩放
          
          // 添加定位控件
          var geoCtrl = new BMap.GeolocationControl({
              showAddressBar       : true //是否显示
              , enableAutoLocation : false //首次是否进行自动定位
              , offset             : new BMap.Size(0,25) 
              //, locationIcon     : icon //定位的icon图标
          });
          
          //监听定位成功事件
          geoCtrl.addEventListener("locationSuccess",function(e){
                  console.log(e);
          });
          
          //监听定位失败事件
          geoCtrl.addEventListener("locationError",function(e){
                  console.log(e);
          });
          
          //检索类型
          var type = "";
          type = LOCAL_SEARCH ;   //周边检索
          //type = TRANSIT_ROUTE; //公交检索
          //type = DRIVING_ROUTE; //驾车检索
          
          //创建鱼骨控件
          var navCtrl = new BMap.NavigationControl({
                  anchor: BMAP_ANCHOR_TOP_LEFT //设置鱼骨控件的位置
          });
          // 将鱼骨添加到地图当中
          mp.addControl(navCtrl);
          
          
          //创建检索控件
          var searchControl = new BMapLib.SearchControl({
              container : "searchBox" //存放检索控件的容器
              , map     : mp          //检索的关联地图
              , type    : type        //检索类型
          });
          
          document.getElementById("selectType").onchange = function () {
              searchControl.setType(this.value);
          };
          
          //添加路况控件
          var ctrl = new BMapLib.TrafficControl({
             showPanel: false //是否显示路况提示面板
          });      
          mp.addControl(ctrl);
          ctrl.setAnchor(BMAP_ANCHOR_TOP_RIGHT);
          </script>

      <div class="contact">          
          <h4>大唐纹身钱江市场店：</h4>
          <p>杭州大唐纹身位于上塘路与香积寺路交叉口，香积寺路319号（钱江市场边检察院正对面），交通便利，可在百度里搜*大唐纹身*找到。</p>
          <p>如果自己开车，附件有大关公园地下停车场，钱江市场3楼停车场，城北电脑城停车场（这个停车场车位有限）等等马路边收费停车场。</p>
          <p>如果坐公交有香积寺路口站（到我店30米距离）。附近350米公交站有通信市场站台和大关西六苑站台。</p>
          <p>如果坐地铁到打铁关站下坐公交403到香积寺路口下，或打的过来（打的费用10多元）。</p>
          <p>联系方式：电话：18257178988 13175009566， QQ：1922857868  ,  邮箱 : dtwenshen@qq.com , 微信号：htdtws</p>
          <h4>大唐纹身凤起路店：</h4>
          <p>杭州市下城区凤起路481号（武林路与延安路中间段）</p>
          <p>公交有十四中，孩儿巷等；本店门口可以停车，坐地铁一号线凤起站B出口，沿凤起路往武林路走500米；</p>
      </div> 
      <div class="con_talk">
          <h4>在线留言</h4>
          <form action="${ctx}/commit_message${suffix}" method="POST">
              <div><span>昵称：</span><input type="text" name="name" maxlength='20'/><em>*</em></div>
              <div><span>内容：</span><textarea name="content"maxlength='500'></textarea><em>*</em></div>
              <div><span>电话：</span><input type="text" name="phone" maxlength='20'/><em>*</em></div>
               <div><span>验证码：</span><input type="text" name="verifyCode" style="width:100px !important;" maxlength='4'/>
               
              <a href="javascript:f5VerifyCodeImg();"><image src="${ctx}/common/randomcode${suffix}" name="verifyCode"/></a><em>*</em></div>
              <div class="btn">
                  <input type="submit" value="提交" /> 
                  <input type="reset" value="重置" />
              </div>
          </form>
      </div>
</div>
<script>
function f5VerifyCodeImg(){
	$("[name='verifyCode']").attr('src', ctx+'/common/randomcode?d='+new Date().getTime());
}
</script>
<!--底部-->
<#include "wechat.ftl"/>