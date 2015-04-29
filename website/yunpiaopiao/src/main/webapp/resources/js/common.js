// tab
$(function(){
    function tabs(tabTit,on,tabCon){
        $(tabTit).children().click(function(){
            $(this).addClass(on).siblings().removeClass(on);
            var index = $(tabTit).children().index(this);
           	$(tabCon).children().eq(index).show().siblings().hide();
    	});
	};
    tabs(".tab-title","active",".tab-con");
});

$(function () {
   $('.tab ul.menu li').click(function(){
        //获得当前被点击的元素索引值
        var Index = $(this).index();
		//给菜单添加选择样式
	    $(this).addClass('p1').siblings().removeClass('p1');
		//显示对应的div
		$('.tab').children('div').eq(Index).show().siblings('div').hide();
   
   });
});
//图片无缝滚动
 $(function(){
         var len=$('.count li').length,            //图片个数
                                        
                 animateTime=800,                      //动画时间
                 liWidth=$('.count li').innerWidth(),  //包含一张图片的容器的宽度
                duration=3000;                        //滚动周期(就是每隔多长时间滚动一次),不能比动画时间小

         //向前滚动
         $('.prev').on('click',scrollPrev);
         function scrollPrev(){                
                         $('.prev').off('click');          //解除事件绑定(为了避免快速点击，程序来不及反应)
                        var left=$('.scroll-pic').position().left;  //滚动区域相对父容器的左边位置
                         if(left==0){
                                 $('.scroll-pic').css('left',-liWidth*len+'px');
                         }
                         $('.scroll-pic').animate({'left':'+='+liWidth+'px'},animateTime,function(){
                                 $('.prev').on('click',scrollPrev);  //动画完成后恢复事件绑定
                         });
                        
          }
          //向后滚动
          $('.next').on('click',scrollNext);
          function scrollNext(){
                  $('.next').off('click');
                 var left=$('.scroll-pic').position().left;
                 if(left==-liWidth*len){
                         $('.scroll-pic').css('left','0px');        
                 }
                 $('.scroll-pic').animate({'left':'-='+liWidth+'px'},animateTime,function(){
                         $('.next').on('click',scrollNext);
                 });//animate()动画队列，本行代码意思是先将left减去一个liwidth,然后过animateTime=800毫秒，恢复.next的单击事件
                
          }
          //自动滚动
          var ticker=autoRun();  //滚动循环器
          function autoRun(){
                  return setInterval(scrollNext,duration);//setInterval() 方法可按照指定的周期（以毫秒计）来调用函数或计算表达式。
          }
          //停止/恢复自动滚动
          //$('.scroll-conten').hover(
             //function(){ clearInterval(ticker);},//关闭循环器
                 //function(){ticker=autoRun();}//重新调用循环器
          //);
 });






