<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <!-- meta -->
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="../static/css/common_v2.css"  />
    <link rel="stylesheet" href="../static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../static/css/owl.carousel.css" />
    <link rel="stylesheet" href="../static/css/owl.theme.css" />
    <link rel="stylesheet" href="../static/css/home-reset.css" />
    <link rel="stylesheet" href="../static/css/main.css" />
    <!-- js -->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <script src="../static/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="../static/js/location.js"></script>
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
    <!--[if lt IE 9]>
    <script src="js/html5shiv-printshiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <!-- title -->
    <title>地心引力-服务详情</title>
    <style type="text/css">
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
		.baozhangTitle{
			
		    text-align: center;
		    height: 103px;
		    padding: 40px;
		    font-size: 23px;
		    color: #000000;
    	}
    	.baozhang1 span{
    		display: inline-block;
		    background: url('../static/img/icon/checkbox.png') #fff no-repeat 0px;
		    height: 23px;
		    width: 24%;
		    padding: 5px 40px;
		    color: #000000;
    	}
    	.ibox{
            line-height:20px;
            width:300px;
            height:20px;
            background:#ccc;
            text-align:left;
            margin:10px auto 0 auto;
        }
        .iLoading{
            color:#fff;
            font-size:12px;
            line-height:20px;
            width:0px;
            height:20px;
            background:red;
            text-align:center;
            position: absolute;
        }
        .se-info{
        	font-size: 16px;
        }
        .se-info span{
        	font-weight: normal;
        }
        .se-info .left div{
        	padding: 5px;
        	height:50px;
        	font-weight: bold;
        }
       .se-info .right div{
        	padding: 14px;
        	height: 45px;
        	font-weight: bold;
        }
    </style>
</head>
<body>
<div class="hd">
	<div class="header">
		<div class="left">
		<span>不限</span>
		</div>
		<div class="city_list none" id="city_list">
		<!--	<div class="tit">
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
			<div class="more"><a href="#">更多城市 ></a></div> -->
		</div>
		<div class="right">
            <span id="userName" class="n">Hi，sea joan</span> | 
            <a id="quitOrLogin" href="#"><span class="t">退出</span></a> |
			<a href="/employOrder"><span class="g">需求方</span></a> |
			<a href="/providerOrder"><span class="g">我是服务商</span></a>
           </div>
           <div class="c"></div>
	</div>
</div>
    <!-- header -->
    <header class="header">
        <!-- navbar -->
        
        <!-- menubar -->
		<div class="head">
			<div class="search_module clearfix" style="width: 1200px;">
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

    <!-- categorybar -->
    <div class="nav" style="width: 1200px; margin:0 auto;">
		<ul class="nav-menu">
			<a href="javascript:;"><li class="link_one">全部服务分类</li></a>
			<a href="javascript:;"><li class="">设计院</li></a><!-- 切换时 当前页面class用  active  -->
			<a href="javascript:;"><li>技术开发</li></a>
			<a href="javascript:;"><li>法务中心</li></a>
			<a href="javascript:;"><li>税务中心</li></a>
		</ul>
	</div>
    
    <!-- prod -->
    <div class="mainbar">
        <div class="prod">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <div class="left">
                            <a href="javascript:;" class="big">
                                <img id="showBigImg" src="${serviceDetail.imgSrcList[0]}" />
                                <input id="iconSrcUrl" type="hidden" value="${serviceDetail.imgSrcList[0]}">
                            </a>
                            <div class="owl-carousel jq-slider">
                            	<c:forEach  items="${serviceDetail.imgSrcList}" var="name">
   									<div class="owl-item">
	                                    <a href="javascript:;">
	                                        <img class="thumbImg" src="${name}" />
	                                    </a>
                                	</div>
								</c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="right">
                            <div class="prod-name">
                                <p>${serviceDetail.title}</p>
                            </div>
                            <div class="prod-price">
                                <div class="fl">
                                    <p>团购价格：<span class="price">¥${serviceDetail.groupPrice}</span>/个</p>
                                </div>
                                <div class="fr">
                                    <ul>
                                        <li>
                                            <p>${serviceDetail.discount}折</p>
                                            <p>折扣</p>
                                        </li>
                                        <li>
                                            <p>¥${serviceDetail.originalPrice}</p>
                                            <p>原价</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            
                            <input id="groupPrice" type="hidden" value="${serviceDetail.groupPrice}">
                            
                            <!--  <div class="prod-way">
                                <span>付款方式：</span>
                                <label>
                                    <input type="radio" name="way" class="way" checked value="0"/>全额付
                                </label>
                                <label>
                                    <input type="radio" name="way" class="way" value="3"/>分3期
                                </label>
                                <label>
                                    <input type="radio" name="way" class="way" value="6"/>分6期
                                </label>
                                <label>
                                    <input type="radio" name="way" class="way" value="12"/>分12期
                                </label>
                            </div>  -->
                            <div class="prod-sale">
                                <span>是否售后：</span>
                                <label>
                                    <input type="radio" name="sale" class="sale" checked value="1"/>是
                                </label>
                                <label>
                                    <input type="radio" name="sale" class="sale" value="0"/>否
                                </label>
                            </div>
                            <div class="prod-total">
                                 <a href="javascirpt:;">${serviceDetail.salesNumber}人已团购</a>
                                 <span>|</span>
                                 <a href="javascript:;">收藏<i class="icon icon-love"></i></a>
                            </div>
                            <div class="prod-operate">
                                <a href="javascript:;" class="order">立即下单</a>
                                <a href="/shopInfo?shopId=${serviceDetail.shopId}" class="store">进入店铺</a>
                                <input id="shopId" type="hidden" value="${serviceDetail.shopId}">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row detail">
                    <div class="col-md-9" style="width: 100%;">
                        <div class="bs-example-tabs">
                            <ul class="nav nav-tabs">
                                <li class="active" style="width: 33%;">
                                    <a href="#home-1" data-toggle="tab" aria-expanded="true">服务详情</a>
                                </li>
                                <li id="evaluate" class="" style="width: 33%;">
                                    <a href="#home-2" data-toggle="tab" aria-expanded="false">累计评价</a>
                                </li>
                                <li style="width: 33%;">
                                    <a href="#home-3" data-toggle="tab" aria-expanded="false">交易担保</a>
                                </li>
                            </ul>
                            <input type="hidden" id="serviceId" value="${serviceDetail.id}">
                            <div class="tab-content">
                                <div class="tab-pane fade active in clearfix" id="home-1">
                                	<div class="se-info" style="height:210px;width:100%; padding:30px;">
                                		<div class="left" style="background-color: #fafafa;float:left;padding: 10px;width:30%;border-right:1px solid #666;height:150px;">
                                			<div>产品名称 : <span>${serviceDetail.title}</span></div>
                                			<div>关键字：<span>${serviceDetail.keyword}</span></div>
                                			<div>一句话简介：<span>${serviceDetail.oneIntroduction}</span></div>
                                		</div>
                                		<div class="mid" style="background-color: #fafafa;padding: 10px;float:left;width:40%;border-right:1px solid #666;height:150px;">
                                			<div style="height:30px;width:100%;padding: 5px;font-weight: bold;">服务介绍：</div>
                                			<div style="height:120px;width:100%;padding:5px;line-height:24px;">
                                				${serviceDetail.introductionDetail}
                                			</div>
                                		</div>
                                		<div class="right" style="background-color: #fafafa;float:left;width:30%;padding: 10px;height:150px;">
                                			<div>原价：¥<span>${serviceDetail.originalPrice}</span></div>
                                			<div>折扣：<span>${serviceDetail.discount}</span></div>
                                			<div>单价：¥<span>${serviceDetail.groupPrice}</span></div>
                                		</div>
                                	</div>
                                </div>
                                <div class="tab-pane fade" id="home-2">
                                    <div style="height:50px;padding:15px 20px">* 团购评价是用户对本团购的消费点评</div>
                                    <div class="clearfix" style="padding: 15px 30px;width:100%">
                                    	<div class="score" style="float:left; width:50%;height: 190px;padding: 20px 190px; border-right: 1px solid #666;">
                                    		<div class="grade" style="text-align:center;font-size: 30px;color: #00a0e9;height:45px;color:#00a0e9;">4.6</div>
	                                    	<div class="prod-grade">
				                                <span>评分：</span>
				                                <a href="javascript:;" class="star"></a>
				                                <a href="javascript:;" class="star"></a>
				                                <a href="javascript:;" class="star"></a>
				                                <a href="javascript:;" class="star"></a>
				                                <a href="javascript:;" class="star"></a>
				                            </div>
                                    		<div class="numPeople" style="text-align:center;"></div>
                                    	</div>
                                    	<div class="percent" style="float:left; width:50%;height:164px;">
    										<div class="clearfix">
    											<div style="float:left;padding: 0 20px 0 82px; margin-top: 13px;">5分</div>
	    										<div id="ibox5" class="ibox" style="float:left;">
												    <div id="iLoading5" class="iLoading"></div>
												</div>
    										</div>
											<div class="clearfix">
    											<div style="float:left;padding: 0 20px 0 82px; margin-top: 13px;">4分</div>
	    										<div id="ibox4" class="ibox" style="float:left;">
												    <div id="iLoading4" class="iLoading"></div>
												</div>
    										</div>
											<div class="clearfix">
    											<div style="float:left;padding: 0 20px 0 82px; margin-top: 13px;">3分</div>
	    										<div id="ibox3" class="ibox" style="float:left;">
												    <div id="iLoading3" class="iLoading"></div>
												</div>
    										</div>
											<div class="clearfix">
    											<div style="float:left;padding: 0 20px 0 82px; margin-top: 13px;">2分</div>
	    										<div id="ibox2" class="ibox" style="float:left;">
												    <div id="iLoading2" class="iLoading"></div>
												</div>
    										</div>
											<div class="clearfix">
    											<div style="float:left;padding: 0 20px 0 82px; margin-top: 13px;">1分</div>
	    										<div id="ibox1" class="ibox" style="float:left;">
												    <div id="iLoading1" class="iLoading"></div>
												</div>
    										</div>
                                    	</div>
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="home-3">
                                	<div class="baozhangTitle" style="background: url('../static/img/icon/step-one.png') #fff no-repeat 375px 35px;"><span>地心引力，全程保障交易安全</span></div>
                                	<div class="baozhang1" style="width:100%;text-align:center;">
                                		<span>验收满意后，再付款给服务商</span>
                                		<span>所有服务商，100%实名认证</span>
                                		<span>交易出问题，可获双倍赔付</span>
                                		<span>承诺不向雇主收取任何费用</span>
                                	</div>
                                	<div class="baozhangTitle" style="background: url('../static/img/icon/step-two.png') #fff no-repeat 375px 35px;"><span>担保交易，交易全程护航</span></div>
                                    <div class="baozhang2" style="background: url('../static/img/baozhang1.png') #fff no-repeat 165px 21px;width:100%; height:405px;text-algin:center;"></div>
                                    <div class="baozhangTitle" style="background: url('../static/img/icon/step-three.png') #fff no-repeat  320px 33px;"><span>服务商承诺以下服务保障，违约双倍赔付</span></div>
                                	<div class="baozhang3">
	                                	<div class="complete" style="font-weight: bold; padding: 10px 40px; height: 34px; background: url('../static/img/icon/complete.png') #fff no-repeat 5px 0px;">保证完成</div>
	                                	<div style="padding: 15px 0px 15px 40px; height: 45px;">自交易完成10天内，若发现服务商提交的作品未达到双方协议要求，雇主有权发起维权，经判定维权成功，雇主将得到双倍赔付。</div>
                                		<div class="maintain" style="font-weight: bold; padding: 10px 40px; height: 34px; background: url('../static/img/icon/maintain.png') #fff no-repeat 5px 0px;">保证维护</div>
                                		<div style="padding: 15px 0px 15px 40px; height: 45px;">自交易完成三个月内，若服务商拒绝为雇主提供维护服务，雇主有权发起维权，经判定维权成功，雇主将得到双倍赔付。</div>
                                	</div>
                                	<div class="baozhangTitle" style="background: url('../static/img/icon/step-four.png') #fff no-repeat 375px 35px;"><span>担保交易，交易全程护航</span></div>
                                    <div class="baozhang4" style="background: url('../static/img/baozhang2.png') #fff no-repeat 165px 21px;width:100%; height:405px;text-algin:center;"></div>
    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- exapmplebar -->
    <div class="exapmplebar">
        <div class="exapmple">
            <div class="container">
                <div class="row">
                    <p class="fl">
                        <i class="icon icon-example"></i>往期经典案例
                    </p>
                    <a href="javascript:;" class="fr">
                        换一批<i class="icon icon-flush"></i>
                    </a>
                </div>
                <div class="row">
                    <table class="table table-hover table-bordered table-responsive" cellspacing="0" rules="all" border="1"> 
                        <thead>
                            <tr>
                                <th style="width:30%"'>服务名称</th>
                                <th style="width:10%">价格</th>
                                <th style="width:20%">成交时间</th>
                                <th style="width:40%">评价</th>
                            </tr>
                        </thead>
                        <tbody class="evaluate-detail-tbody">
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <div class="footer">
        <div class="footer-service">
            <div class="container">
                <div class="row">
                    <p class="address">©2016 nuomi.com  京ICP证030173号  京公网安备11010802014106号  营业执照信息</p>
                    <ul class="img">
                        <li>
                            <a href="javascript:;">
                                <img src="../static/img/footer-01.png" />
                            </a>    
                        </li>
                        <li>
                            <a href="javascript:;">
                                <img src="../static/img/footer-02.png" />
                            </a>    
                        </li>
                        <li>
                            <a href="javascript:;">
                                <img src="../static/img/footer-03.png" />
                            </a>    
                        </li>
                        <li>
                            <a href="javascript:;">
                                <img src="../static/img/footer-04.png" />
                            </a>    
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <script>
    var onePercentage = '';
    var twoPercentage = '';
	var threePercentage = '';
	var fourPercentage = '';
	var fivePercentage = '';
    
	$(function(){
		
		getLoginStatusToShow();
        getCurLocation();
        
		$('.thumbImg').on('click', function () {
			var that = this;
			var imgUrl = that.src;
			console.log(imgUrl);
			$('#showBigImg').attr("src", imgUrl);
		})
	    $('.jq-slider').owlCarousel({
	        items: 4,
	        autoPlay: true,
	        navigation : true,
	        navigationText: [],
	        slideSpeed : 300
	    });
	    $("#city_change,#city_list").mouseover(function(){
	    	$("#city_change").attr("class","city_Hover");
	    	$("#city_list").attr("class","city_list");
	    });
	    $("#city_change,#city_list").mouseout(function(){
	    	$("#city_change").attr("class","city_change");
	    	$("#city_list").attr("class","none");
	    });
	    getServiceEvaluateDetail();
	    getServiceEvaluateScore();
	    
	    $('.order').on('click', function () {
	    	var that = this;
            var jsonData = {};
            jsonData.iconSrcUrl = $('#iconSrcUrl').val(),
            jsonData.serviceId = $('#serviceId').val(),
            jsonData.groupPrice = $('#groupPrice').val(),
            //jsonData.payType = $("input[name='way']:checked").val(),
            jsonData.isAfterSales = $("input[name='sale']:checked").val();
            
            $.ajax({
                type: "POST",
                url: "/order/placeOrder",
                contentType : "application/json;utf-8",
                data: JSON.stringify(jsonData),
                dataType: "html",
                success: function(data){
                	$('html').html(data);
                }
            });
	    });
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
	
	//获取累计评价
	function getServiceEvaluateScore(){
		var serviceId = $('#serviceId').val();
		$.ajax({
	        type: "GET",
	        url: '/serviceDetail/serviceEvaluateScore?serviceId='+serviceId,
	        data: '',
	        success: function(d){
	        	if(d.success){
	        		showServiceEvaluateScore(d.data.data);
	        	}
	        }
	    });
	}
	function showServiceEvaluateScore(data){
		$('.numPeople').text('共有'+data.totalSize+'人评价');
		$('.grade').text(data.avargeScore);
		onePercentage = data.onePercentage;
		twoPercentage = data.twoPercentage;
		threePercentage = data.threePercentage;
		fourPercentage = data.fourPercentage;
		fivePercentage = data.fivePercentage;
		//console.log(onePercentage);
		
		$('.prod-grade a').each(function(k, v){
			//console.log(k);
			if(k+1 <= data.avargeScore){
				$(this).addClass("active");
			}
		});
		
	}
	//获取服务评价
	function getServiceEvaluateDetail(){
		
		var serviceId = $('#serviceId').val();
		$.ajax({
	        type: "GET",
	        url: '/serviceDetail/serviceEvaluateDetail?serviceId='+serviceId,
	        data: '',
	        success: function(d){
	        	if(d.success){
	        		showServiceEvaluateDetail(d);
	        	}
	        }
	    });
	}
	
	function showServiceEvaluateDetail(d){
		var locationList = d.data.contentList;
		var title = d.data.title;
		var price = d.data.price;
		if(locationList.length > 0){
			$('.evaluate-detail-tbody tr').remove();
			var htmlBody = '';
			for(var i=0; i<locationList.length;i++){
				htmlBody += '<tr>'+
							'<td style="width:30%">'+title+'</td>'+
							'<td style="width:10%">'+price+'</td>'+
							'<td style="width:20%">'+locationList[i].createTime.substring(0,10)+'</td>'+
							'<td style="width:40%">'+locationList[i].evaluateContent+'</td>'+
							'</tr>';
			}
			$(htmlBody).appendTo($('.evaluate-detail-tbody'));
		}
	} 
	//换一批
	$(".icon-flush").click(function(){
		getServiceEvaluateDetail();
	});
	$('#evaluate').click(function(){

	    var idiv1=document.getElementById('iLoading1');
	    var ibox1=document.getElementById('ibox1');
	    var idiv2=document.getElementById('iLoading2');
	    var ibox2=document.getElementById('ibox2');
	    var idiv3=document.getElementById('iLoading3');
	    var ibox3=document.getElementById('ibox3');
	    var idiv4=document.getElementById('iLoading4');
	    var ibox4=document.getElementById('ibox4');
	    var idiv5=document.getElementById('iLoading5');
	    var ibox5=document.getElementById('ibox5');
	    var timer1=null,timer2=null,timer3=null,timer4=null,timer5=null;
	    
	    if(onePercentage != 0){
	    	timer1=setInterval(function(){
		        var iWidth=idiv1.offsetWidth/ibox1.offsetWidth*100;
		        idiv1.style.width=idiv1.offsetWidth+1+'px';
		        idiv1.innerHTML=Math.round(iWidth)+"%";
		        if(iWidth == onePercentage){
		            clearInterval(timer1);
		        }
		    },1);
	    }
	    if(twoPercentage != 0){
		    timer2=setInterval(function(){
		        var iWidth=idiv2.offsetWidth/ibox2.offsetWidth*100;
		        idiv2.style.width=idiv2.offsetWidth+1+'px';
		        idiv2.innerHTML=Math.round(iWidth)+"%";
		        if(iWidth == twoPercentage){
		        	clearInterval(timer2);
		        }
		    },1);
	    }
	    if(threePercentage != 0){
	    	timer3=setInterval(function(){
		        var iWidth=idiv3.offsetWidth/ibox3.offsetWidth*100;
		        idiv3.style.width=idiv3.offsetWidth+1+'px';
		        idiv3.innerHTML=Math.round(iWidth)+"%";
		        if(iWidth == threePercentage){
		            clearInterval(timer3);
		        }
		    },1);
	    }
	   if (fourPercentage != 0) {
		   timer4=setInterval(function(){
		        var iWidth=idiv4.offsetWidth/ibox4.offsetWidth*100;
		        idiv4.style.width=idiv4.offsetWidth+1+'px';
		        idiv4.innerHTML=Math.round(iWidth)+"%";
		        if(iWidth == fourPercentage){
		            clearInterval(timer4);
		        }
		    },1);
	   }
	   if (fivePercentage != 0) {
	    	timer5=setInterval(function(){
		        var iWidth=idiv5.offsetWidth/ibox5.offsetWidth*100;
		        idiv5.style.width=idiv5.offsetWidth+1+'px';
		        idiv5.innerHTML=Math.round(iWidth)+"%";
		        if(iWidth==fivePercentage){
		            clearInterval(timer5);
		        }
		    },1);
	   }
	});
</script>
</body>
</html>