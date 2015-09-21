<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>根据地址查询经纬度</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <script type="text/javascript" src="http://api.map.baidu.com/api??v=1.5&ak=Bkr2mjnARtGsksAIs2FcHU55"></script>
</head>
<div id="mapdiv" style="display:none;position: absolute; top:0px; right:0px;">
    <div id="r-result" style="float:left;width:100px;display:none">打印坐标</div>  
    <div style="width:500px;">   
        <li><label>要查询的地址：</label><input id="text_" type="text" value="杭州拱墅区祥茂路2号" style="width: 220px;"/>
        	<input type="button" value="查询" onclick="searchByStationName();" style="width: 100px;background:#eee;"/>
        	<input type="button" value="确定" onclick="setLngLat();" style="width: 100px;background:#eee;"/>
        <li>
        
        <div id="container" 
            style="position: absolute; 
                width: 500px; 
                height: 300px; 
                top: 50px; 
                right: 0px;
                border: 1px solid gray;
                overflow:hidden;
                margin-left:19%;">
        </div>
    </div>
</div>
<script type="text/javascript">
    var map = new BMap.Map("container");
	map.centerAndZoom("杭州", 13);
	var point = new BMap.Point(120.120044,30.339848);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用

    map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
    map.addControl(new BMap.OverviewMapControl()); //添加默认缩略地图控件
    map.addControl(new BMap.OverviewMapControl({ isOpen: true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT }));   //右下角，打开

    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){   //定位结果对象会传递给r变量
	    if(this.getStatus() == BMAP_STATUS_SUCCESS){  //通过Geolocation类的getStatus()可以判断是否成功定位。
	        var mk = new BMap.Marker(r.point);    //基于定位的这个点的点位创建marker
	        map.addOverlay(mk);    //将marker作为覆盖物添加到map地图上
	        map.panTo(r.point);   //将地图中心点移动到定位的这个点位置。注意是r.point而不是r对象。
	       // alert('您的位置：'+r.point.lng+','+r.point.lat); 
	       $("#lng").val(r.point.lng);
	       $("#lat").val(r.point.lat);
	 	//r对象的point属性也是一个对象，这个对象的lng属性表示经度，lat属性表示纬度。
			
	    }else {
	        alert('failed'+this.getStatus());
	    }        
	},{
		enableHighAccuracy: true
	});
	var iscreatr=false; 
	map.addEventListener("click", function(e){  
		if(iscreatr==true)return;  
		//---------------------------------------------遮盖物---------------------------------------------  
		iscreatr=true;  
		map.clearOverlays();//清空原来的标注
		 var point = new BMap.Point(e.point.lng ,e.point.lat);//默认  
		 // 创建标注对象并添加到地图    
		 var marker = new BMap.Marker(point);    
		 //var label = new BMap.Label("我是可以拖动的",{offset:new BMap.Size(20,-10)});  
		 //marker.setLabel(label)  
		 map.addOverlay(marker);    
		 marker.enableDragging();    //可拖拽  
		 marker.addEventListener("dragend", function(e){   
				document.getElementById("r-result").innerHTML = e.point.lng + ", " + e.point.lat;//打印拖动结束坐标  
				$("#lng").val(e.point.lng);
			    $("#lat").val(e.point.lat);
		 });  
	}); 
	function searchByStationName() {
	    map.clearOverlays();//清空原来的标注
	    var keyword = document.getElementById("text_").value;
	    localSearch.setSearchCompleteCallback(function (searchResult) {
	        var poi = searchResult.getPoi(0);
	        //document.getElementById("result_").value = poi.point.lng + "," + poi.point.lat;
	        map.centerAndZoom(poi.point, 13);
	        var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
	        map.addOverlay(marker);
	        var content = document.getElementById("text_").value + "<br/><br/>经度：" + poi.point.lng + "<br/>纬度：" + poi.point.lat;	
	        $("#lng").val(poi.point.lng);
			$("#lat").val(poi.point.lat);
	        var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
	        
			//var label = new BMap.Label("我是可以拖动的",{offset:new BMap.Size(20,-10)});  
			 //marker.setLabel(label)  
			 map.addOverlay(marker);    
			 marker.enableDragging();    //可拖拽  
			 marker.addEventListener("dragend", function(e){   
					document.getElementById("r-result").innerHTML = e.point.lng + ", " + e.point.lat;//打印拖动结束坐标  
					$("#lng").val(e.point.lng);
				    $("#lat").val(e.point.lat);
			 });  
	        // marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
	    });
	    localSearch.search(keyword);
	} 
	function setLngLat(){
	    	var lng = $("#lng").val();
	    	var lat =$("#lat").val();
	    	$("#mapdiv").hide();
			address(lat,lng);
	}
	function address(lt,lg){
		var point = new BMap.Point(lg,lt);
		var geoc = new BMap.Geocoder();    
		geoc.getLocation(point, function(rs){
			var addComp = rs.addressComponents;
			var info = rs.surroundingPois;
			info = info.length>0?info[0].title:"";
			var addressInfo = addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
			$("#lnglat").val(addressInfo+""+info);
		});        
	}
	//传两个点的经纬度
	function getDistance(alg,alt,blg,blt){
		var pointA = new BMap.Point(alg,alt);  // 创建点坐标A--
		var pointB = new BMap.Point(blg,blt);  // 创建点坐标B--
		return map.getDistance(pointA,pointB).toFixed(2);
	}
</script>
</html>