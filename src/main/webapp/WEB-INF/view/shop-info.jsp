<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="keywords" content="">
	<meta name="description" content="">
	<!--让 IE 浏览器运行最新的渲染模式-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>店铺详情</title>
	<link rel="stylesheet" type="text/css" href="../static/css/style.css">
	<link href="/static/css/common_v2.css" rel="stylesheet" type="text/css" />
	<script src="/static/js/jquery-v1.10.2.min.js"></script>
	<script type="text/javascript" src="/static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="/static/js/location.js"></script>
	<style type="text/css">
    .hd a{
			display: inline !important;
		}
	.header .left .city_Hover {
		    position: absolute;
		    top: 0;
		    left: 34px;
		    z-index: 99;
		    background: url('/static/img/ico16.png') #fff no-repeat 76px 21px;
		    border-left: 1px solid #eee;
		    border-right: 1px solid #eee;
		    color: #009be1;
		    height: 52px !important;
		    padding: 15px 40px 0px 20px;
		}	
	</style>
</head>
<body>
<!--头部-->
<div class="hd">
	<div class="header">
		<div class="left">
		<span>不限</span>
		</div>
				<div class="city_list none" id="city_list">
					<!-- <div class="tit">
						热门城市
					</div>
					<ul class="city_list_ul">
						<li><a href="#">北京</a></li>
						<li><a href="#">北京</a></li>
						<li><a href="#">北京</a></li>
						<li><a href="#">北京</a></li>
						<li><a href="#">北京</a></li>
					</ul>
					<div class="c"></div>
					<div class="more"><a href="#">更多城市 ></a></div>-->
				</div>
				<div class="right">
               <span id="userName" class="n">Hi，sea joan</span> | 
            <a id="quitOrLogin" href="#"><span class="t">退出</span></a> |
			<a href="/employOrder"><span class="g">需求方</span></a> |
			<a href="/providerOrder"><span class="g">我是服务商</span></a>
            </div><div class="c"></div>
	</div>
</div>
<header class="header">
	<div class="head">
		<div class="search_module clearfix">
		<a href="/home/index"><div style="height: 100px; width: 130px;"> </div></a>
			<div class="search">
				<input type="text" placeholder="请输入关键词搜索">
				<div class="key1">技术</div>
				<div class="key2">社保</div>
				<div class="btn">搜索</div>
			</div>
		</div>
	</div>          
</header>
<div class="w1200">
    
    <!--图片导航-->
    <div class="banner-nav">
        <img src="${shopDetail.storeDisplayImgSrc}" alt="">
        <ul>
            <li class="active"><a href="#C1">店铺介绍</a></li>
            <li><a href="#C2">资质证书</a></li>
            <li><a href="#C3">其他服务列表</a></li>
            <li><a href="#C4">成交项目</a></li>
        </ul>
    </div>
    <!--多科介绍-->
    <div class="d-intro">
        <a name="C1"></a>
        <h2>服务商：${shopDetail.name}<span class=""><img src="../static/img/shopInfoImg/icon-1.png" alt="">关注</span></h2>
        <div class="d-intro-txt">
            <ul class="left">
                <li>完成项目数：${shopDetail.completeServiceNumber}</li>
                <li>手机号码：${shopDetail.phoneNumber}</li>
                <li>好评数量：${shopDetail.goodReputationNumber}</li>
                <li>注册日期：${shopDetail.registerDate}</li>
                <li>服务范围：${shopDetail.serviceScope}</li>
                <li>所在地址：${shopDetail.userLocation}</li>
            </ul>
            <ul class="ul1">
                <li><span>服务商简介：</span><p>${shopDetail.servicerProfile}</p>
                </li>
            </ul>
            <input id="shopId" type="hidden" value="${shopDetail.shopId}">
        </div>
    </div>
    <!--资质证书-->
    <div class="d-zizhi">
        <a name="C2"></a>
        <h2>资质证书</h2>
        <ul>
        	<c:forEach items="${shopDetail.proveImgUrl}" var="proveImgUrl">
        		<li><img src="${proveImgUrl}" alt=""></li>
		    </c:forEach>
        </ul>
    </div>
    <!--其他服务列表-->
    <div class="fwlist">
        <a name="C3"></a>
        <h2>其他服务列表<div class="page"><a class="a1"></a><a class="a2"></a></div></h2>
        <table>
            <thead>
                <tr>
                    <th style="width:10%">序号</th>
                    <th style="width:30%">标题</th>
                    <th style="width:20%">服务类型</th>
                    <th style="width:20%">团购价格</th>
                    <th style="width:20%">发布时间</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
    <div class="cjxm">
        <a name="C4"></a>
     	 <h2>成交项目<div class="page"><a class="a1"></a><a class="a2"></a></div></h2>
        <table>
            <thead>
                <tr>
                    <th style="width:5%">序号</th>
                    <th style="width:40%">服务标题</th>
                    <th style="width:35%">雇佣商</th>
                    <th style="width:10%">团购价格</th>
                    <th style="width:10%">成交时间</th>
                </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
</div>
<div class="e5"></div>
<!--底部-->
<div class="footer">
    <div class="fa">
        <div class="w1200">
            <div class="footer-bottom">
                <p class="p1">©2016 nuomi.com  京ICP证030173号  京公网安备11010802014106号  营业执照信息</p>
                <p class="p2">
                    <a><img src="../static/img/shopInfoImg/lj-1.jpg" alt=""></a>
                    <a><img src="../static/img/shopInfoImg/lj-2.jpg" alt=""></a>
                    <a><img src="../static/img/shopInfoImg/lj-3.jpg" alt=""></a>
                    <a><img src="../static/img/shopInfoImg/lj-4.jpg" alt=""></a>
                </p>
            </div>
        </div>
    </div>
</div>
<!-- 返回顶部 -->
<a href="javascript:void(0)" class="page_up"></a>
</body>
<script>
$('.banner-nav ul li').on('click',function(){
    $(this).addClass('active');
    $(this).siblings('').removeClass('active');
});
/*返回顶部*/
$(window).on('scroll',function(){
    if($(this).scrollTop()>200){
        $('.page_up').show();
    }else{
        $('.page_up').hide();
    }
});
$('.page_up').on('click',function(){
    $('html,body').stop().animate({scrollTop:0},500);
});

$("#city_change,#city_list").mouseover(function(){
	$("#city_change").attr("class","city_Hover");
	$("#city_list").attr("class","city_list");
});
$("#city_change,#city_list").mouseout(function(){
	$("#city_change").attr("class","city_change");
	$("#city_list").attr("class","none");
});

var OtherServicePG=1;
var OtherServiceCount = '';
var DownServicePG=1;
var DownServiceCount = '';
$(function () {
        getOtherService(1);
        getDownService(1);
        
        getLoginStatusToShow();
        getCurLocation();
    });
    
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

function getLoginStatusToShow(){
	var accountShow = $.cookie('accountShow');
	if (typeof(accountShow) != "undefined") {
		//var li = '<span class="n">Hi，'+accountShow+'</span> | <a href="#"><span class="t">退出</span></a> |';
		//$(li).appendTo($('#login_top_show'));
		$('#userName').text('Hi,'+accountShow);
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
    
//其他服务列表

$('.fwlist .a2').click(function(){
    ++OtherServicePG;
    if(OtherServicePG > OtherServiceCount){
        OtherServicePG = 1;
    }
    getOtherService(OtherServicePG);
});
$('.fwlist .a1').click(function(){
    --OtherServicePG;
    if(OtherServicePG < 1){
        OtherServicePG = OtherServiceCount;
    }
    getOtherService(OtherServicePG);
});
function getOtherService(pg){
    var shopId = $("#shopId").val();
    $.ajax({
        type: "GET",
        url:'/shopInfo/getServiceByShopId?shopId='+shopId,
        data: {page:pg},
        success: function(data){
            OtherServiceCount = data.data.count;
            showOtherService(data);
        }
    });
}
function showOtherService(data){
    var shopOtherService = data.data.shopOtherService;
    if(shopOtherService.length > 0){
        $('.fwlist table tbody tr').remove();
        for(var i = 0;i < shopOtherService.length; i++){
            var tbody = '';
            tbody +='<tr><td style="width:10%">'+shopOtherService[i].no+'</td>'+
                    '<td style="width:30%" class="td2"><a href="/serviceDetail?serviceId='+shopOtherService[i].serviceId+'">'+shopOtherService[i].title+'</a></td>'+
                    '<td style="width:20%">'+shopOtherService[i].secondType+'</td>'+
                    '<td style="width:20%">'+shopOtherService[i].gprice+'</td>'+
                    '<td style="width:20%">'+shopOtherService[i].publishTime+'</td></tr>';
            $(tbody).appendTo($('.fwlist table tbody'));
        }
    }
}

//成交项目

$('.cjxm .a2').click(function(){
    ++DownServicePG;
    if(DownServicePG > DownServiceCount){
        DownServicePG = 1;
    }
    getDownService(DownServicePG);
});
$('.cjxm .a1').click(function(){
    --DownServicePG;
    if(DownServicePG < 1){
        DownServicePG = DownServiceCount;
    }
    getDownService(DownServicePG);
});
function getDownService(pg){
    var shopId = $("#shopId").val();
    $.ajax({
        type: "GET",
        url:'/shopInfo/getDownServiceByShopId?shopId='+shopId,
        data: {page:pg},
        success: function(data){
            DownServiceCount = data.data.count;
            showDownService(data);
        }
    });
}
function showDownService(data){
    var shopDownService = data.data.shopDownService;
    if(shopDownService.length > 0){
        $('.cjxm table tbody tr').remove();
        for(var i = 0;i < shopDownService.length; i++){
            var tbody = '';
            tbody +='<tr><td style="width:5%">'+shopDownService[i].no+'</td>'+
                    '<td style="width:40%" class="td2"><a href="#">'+shopDownService[i].title+'</a></td>'+
                    '<td style="width:35%">'+shopDownService[i].employName+'</td>'+
                    '<td style="width:10%">'+shopDownService[i].groupPrice+'</td>'+
                    '<td style="width:10%">'+shopDownService[i].dealTime+'</td></tr>';
            $(tbody).appendTo($('.cjxm table tbody'));
        }
    }
}
</script>
</html>
