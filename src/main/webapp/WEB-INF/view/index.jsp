<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-Type" content="text/html;charset=utf-8" />
<title>地心引力</title>
<link href="../static/css/common_v2.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var SID='951d080a5f51572cb2f5a331e764b2a2';
var SID_N='CNCQ';
</script>
<script type="text/javascript" src="../static/js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="../static/js/common.js"></script>
<script type="text/javascript" src="../static/js/area.js"></script>
<script type="text/javascript" src="../static/js/location.js"></script>
<script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
<style type="text/css">
.prev{
	position: absolute;
	width: 50px;
	height: 150px;
	cursor: pointer;
	top:60px;
	background:url('/static/img/ico12.png') no-repeat 0px 0px;
	text-align: center;
	line-height: 20px;
	font-weight: bold;
	color: #fff;
}
.next {
	position: absolute;
	width: 50px;
	height: 150px;
	cursor: pointer;
	top:60px;
	text-align: center;
	line-height: 20px;
	font-weight: bold;
	color: #fff;
}

</style>
</head><body>
<div class="hd">
	<div class="header">
	<div class="left">
		<span>不限</span> | <a class="city_change" id="city_change">切换城市</a>
	</div>
	<div class="city_list none" id="city_list">
		<ul class="city_list_ul">
			
		</ul>
	</div>
		<div class="right">
           <!-- 
           <div id="login_top_show">
	           <a href="#"><span class="n">Hi，sea joan</span></a> | 
               <a href="#"><span class="t">退出</span></a> |
            </div> -->
            
            <span id="userName" class="n">登录</span> | 
            <a id="quitOrLogin" href="#"><span class="t">退出</span></a> |
			<a href="/employOrder"><span class="g">需求方</span></a> |
			<a href="/providerOrder"><span class="g">我是服务商</span></a>
            <!--  <a href=""><span class="z">最近浏览</span></a>-->
        </div><div class="c"></div>
	</div>
</div>
<div class="main">
<div class="head">
	<div class="search_module clearfix">
		<div class="search">
			<input id="searchWord" type="text" placeholder="请输入关键词搜索">
			<div class="key1">技术</div>
			<div class="key2">社保</div>
			<div class="btn">搜索</div>
		</div>
	</div>
</div>
<div class="nav">
	<ul class="nav-menu">
		<a href="javascript:;"><li class="link_one">全部服务分类</li></a>
		<a href="/serviceList?category=40"><li class="">设计院</li></a><!-- 切换时 当前页面class用  active  -->
		<a href="/serviceList?category=41"><li>技术开发</li></a>
		<a href="/serviceList?category=42"><li>法务中心</li></a>
		<a href="/serviceList?category=43"><li>税务中心</li></a>
	</ul>
</div>
<div class="nav-left" style="position: relative;height:352px;">
	<ul class="nav_list_ul fl" id="nav_list_ul">
		<li id="av_list1"></li>
		<li id="av_list2"></li>
		<li id="av_list3"></li>
		<li id="av_list4"></li>
		<li id="av_list5"></li>
		<li id="av_list6"></li>
	</ul>
	<div class="nav-right fl"><img src="../static/img/home_banner.jpg" width="960" height="348" border="0" /></div>
	<div class="c"></div>

	<div class="sub_menu">
		<div class="nav_list_i none" id="nav_list1">
			<div class="tit">企业形象设计</div>
			<div class="link"></div>
		</div>

		<div class="nav_list_i none" id="nav_list2">
			<div class="tit">网站/APP/微信开发</div>
			<div class="link"></div>
		</div>

		<div class="nav_list_i none" id="nav_list3">
			<div class="tit">法律服务</div>
			<div class="link"></div>
		</div>

		<div class="nav_list_i none" id="nav_list4">
			<div class="tit">税务/财务</div>
			<div class="link"></div>
		</div>

		<div class="nav_list_i none" id="nav_list5">
			<div class="tit">社保、公积金</div>
			<div class="link"></div>
		</div>

		<div class="nav_list_i none" id="nav_list6">
			<div class="tit">广告推广</div>
			<div class="link"></div>
		</div>

	</div>

</div>

</div>


     <div class="slider">
         <span class="prev no_click" id="prev"><<</span>
         <span class="next " id="next">>></span>
       <div id="slider_pic">
         <ul>
         	<c:forEach items="${logoList}" var="firstCategory">
         		<li><a href="#"><img src="${firstCategory}" width="210" height="150" border="0" /></a></li>
		    </c:forEach>
          </ul>
       </div>

    </div>
	<input id="isAdmin" type="hidden" value="${isAdmin}">
<div class="foot">
	<div class="footer">
		<span>郑州地心引力软件科技有限公司 </span><span style="margin-left:10px;margin-right:10px;">联系 电话:0371-86506087</span><span>豫ICP备16028996号-1</span></div>
	</div>
</div>



<script type="text/javascript">
$(function(){
	
    var oPic=$('#slider_pic').find('ul');
    var oImg=oPic.find('li');
    var oLen=oImg.length;
    var oLi=oImg.width();
    var prev=$("#prev");
    var next=$("#next");

  	oPic.width(oLen*660);//计算总长度
    var iNow=0;
    var iTimer=null;
    prev.click(function(){
         if(iNow>0){  
          iNow-=5;
         }
        ClickScroll();
    })
    next.click(function(){
        if(iNow<oLen-3){ 
            iNow+=5
        }
        ClickScroll();
    });
   getCategory();
   getServiceLocation();
   getCurLocation();
   function ClickScroll(){
	    iNow==0? prev.addClass('no_click'): prev.removeClass('no_click');
	    iNow==oLen-3?next.addClass("no_click"):next.removeClass("no_click");

	    oPic.animate({left:-iNow*210})
	}
   
   getLoginStatusToShow();
});

function getLoginStatusToShow(){
	var accountShow = $.cookie('accountShow');
	if (typeof(accountShow) != "undefined") {
		//var li = '<span class="n">Hi，'+accountShow+'</span> | <a href="#"><span class="t">退出</span></a> |';
		//$(li).appendTo($('#login_top_show'));
		$('#userName').text('Hi,'+accountShow);
		if ($('#isAdmin').val() == 'caibudaoAdmin') {
			console.log("aaa");
			$('#userName').css("cursor","pointer")
			$('#userName').on('click', function () {
				location.href = "/adminOrder";
			})
		}
		$('#quitOrLogin span').text('退出');
		$('#quitOrLogin').attr('href', '/quit');
	} else {
		//var li = '<span class="n">Hi，游客</span> | <a href="/loginPage"><span class="n">登录</span></a> |';
		//$(li).appendTo($('#login_top_show'));
		$('#userName').text('Hi,游客');
		$('#quitOrLogin span').text('登录');
		$('#quitOrLogin').attr('href', '/loginPage');
	}
}

$("#city_change,#city_list").mouseover(function(){
	$("#city_change").attr("class","city_hover");
	$("#city_list").attr("class","city_list");
});
$("#city_change,#city_list").mouseout(function(){
	$("#city_change").attr("class","city_change");
	$("#city_list").attr("class","none");
});
$("#nav_list_ul li").each(function(){
	$(this).mouseover(function(){
		$("#nav_list1,#nav_list2,#nav_list3,#nav_list4,#nav_list5,#nav_list6").attr("class","none");
		var blockid='#n'+$(this).attr("id");
		$(blockid).attr("class","nav_list_i");
	});
	$(this).mouseout(function(){
		$("#nav_list1,#nav_list2,#nav_list3,#nav_list4,#nav_list5,#nav_list6").attr("class","none");
	});
});
$("#nav_list1,#nav_list2,#nav_list3,#nav_list4,#nav_list5,#nav_list6").mouseover(function(){
	$(this).attr("class","nav_list_i");
});
$("#nav_list1,#nav_list2,#nav_list3,#nav_list4,#nav_list5,#nav_list6").mouseout(function(){
	$(this).attr("class","none");
});

$('.btn').on('click', function () {
	var searchWord = $('#searchWord').val();
	location.href = "/serviceList?searchKy="+searchWord;
	//$.ajax({
     //   type: "GET",
    //    url: "/serviceList?searchKy="+searchWord,
    //    data: ''
    //});
})

//$('.city_list_ul').delegate('li','click',function(){
	//console.log($('.city_list_ul li').data());
//	var t = $(this);
//	$('.left span').text(t.text());
//	console.log(t.attr("myval"))
//});

//从cookie读取，如果有设置城市，则显示设置的城市
function getCurLocation(){
	var plocation = $.cookie('cur_plocation');
	var clocation = $.cookie('cur_location');
	if (typeof(plocation) != "undefined" && typeof(clocation) != "undefined") {
		var loc	= new Location();
		var json = loc.find('0,'+plocation);
		cityName = json[clocation];
		$('.left span').text(cityName);
	}
}

//获取所有城市
function getServiceLocation(){
	$.ajax({
        type: "GET",
        url: '/home/getServiceLocation',
        data: '',
        success: function(d){
        	if(d.success){
        		showServiceLocation(d);
        	}
        }
    });
}

function showServiceLocation(d){
	var locationList = d.data.locationList;
	if(locationList.length > 0){
		$('.city_list_ul li').remove();
		var loc	= new Location();
		var initli = '<li><a mypval="undefined" myval="all">不限</a></li>';
		$(initli).appendTo($('.city_list_ul'));
		for(var i=0; i<locationList.length;i++){
			var cityId = '';
			var cityName = '';
			var proId = '';
			$.each(locationList[i],function(k, v){
				var json = loc.find('0,'+k);
				//console.log("json:" + json[v]);
				cityName = json[v];
				cityId = v;
				proId = k;
				//console.log(v);
			});
			var li = '';
			li += '<li><a mypval="'+proId+'" myval="'+cityId+'">'+cityName+'</a></li>';
			//$(li).data(cityId);
			$(li).appendTo($('.city_list_ul'));
		}
	}
	
	$('.city_list_ul li').on('click',function(){
		var t = $(this);
		var a_myVal =  $(this).children("a").attr('myval');
		var mypVal =  $(this).children("a").attr('mypval');
		$('.left span').text(t.text());
		$("#city_change").attr("class","city_change");
		$("#city_list").attr("class","none");
		$.cookie('cur_location', a_myVal, { expires: 365, path: '/' });
		$.cookie('cur_plocation', mypVal, { expires: 365, path: '/' });
	})
} 

//获取所有分类
function getCategory(){
	$.ajax({
        type: "GET",
        url: '/home/getCategroy',
        data: '',
        success: function(data){
            showCategroy(data);
        }
    });
}
function showCategroy(data){
	if(data){
		var i = 1;
		$.each(data.data.category, function(k,val){   
			$("#nav_list"+i+' > .tit').text(k);
			$("#av_list"+(i++)).text(k);
			var j = 1;
			$.each(val, function(key, v){
				var a = '';
				if(j == 1){
					a += '<a href="/serviceList?category='+key+'">'+v+'</a>';
				}else{
					a += '<span>|</span><a style="line-height: 40px;" href="/serviceList?category='+key+'">'+v+'</a>';	
				}
				$(a).appendTo($("#nav_list"+(i-1)+' .link'));
				j++;
			});
		});
	}
}
</script>
</body>
</html>
