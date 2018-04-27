<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
    <link href="../static/css/common_v2.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="../static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../static/css/owl.carousel.css" />
    <link rel="stylesheet" href="../static/css/owl.theme.css" />
    <link rel="stylesheet" href="../static/css/reset.css" />
    <link rel="stylesheet" href="../static/css/main.css" />
    <link rel="stylesheet" href="../static/js/simplePagination/jquery.simplePagination.css"/>
    <!-- js -->
    <script src="../static/js/jquery.min.js"></script>
    <script src="../static/js/bootstrap.min.js"></script>
    <script src="../static/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../static/js/location.js"></script>
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
    <!--[if lt IE 9]>
    <script src="js/html5shiv-printshiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
    <!-- title -->
    <title>地心引力-服务列表</title>
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
    </style>
</head>
<body>
<div class="hd">
	<div class="header">
		<div class="left">
			<span>不限</span> <!-- | <a class="city_change" id="city_change">切换城市</a> -->
		</div>
		<div class="city_list none" id="city_list">
			<!--  <div class="tit">
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
    <!-- header -->
    <header class="header">
        <!-- navbar -->
		
        <!-- menubar -->
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
    <!-- mainbar -->
    <div class="mainbar">
        <div class="main">
            <div class="container">
                <div class="row">
                    <div class="branch">
                        <a href="javascript:;">全部</a>
                        <span class="seprate">
                            <i class="glyphicon glyphicon-menu-right"></i>
                        </span>
                        <a href="javascript:;">${listTitle}</a>
                    </div>
                </div>
                <input id="category" type="hidden" value="${category}">
                <input id="searchKy" type="hidden" value="${searchKy}">
                <div class="row">
                    <div class="sequence">
                        <ul>
                            <li id="1">
                                <a href="javascript:;">销量<i class="icon asc-arrow"></i></a>
                            </li>
                            <li id="2">
                                <a href="javascript:;">价格<i class="icon des-arrow"></i></a>
                            </li>
                            <li id="0">
                                <a href="javascript:;">发布时间<i class="icon asc-arrow"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row" id="contentList">
                </div>
            </div>
        </div>
    </div>

    <!-- footer -->
    <div class="footer">
        <div class="footer-service">
            <div class="container">
				<span>郑州地心引力软件科技有限公司 </span>
				<span style="margin-left:10px;margin-right:10px;">联系 电话:0371-86506087</span>
				<span>豫ICP备16028996号-1</span>
            </div>
        </div>
    </div>
<script type="text/javascript">
	var page = 1;

    $(function () {
        getServicelist(1, 0, page);
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
    		$('#userName').text('Hi,'+accountShow);
    		$('#quitOrLogin span').text('退出');
    		$('#quitOrLogin').attr('href', '/quit');
    	} else {
    		$('#userName').text('Hi,游客');
    		$('#quitOrLogin span').text('登录');
    		$('#quitOrLogin').attr('href', '/loginPage');
    	}
    }
    
    function getServicelist(inOrderType, inOrderTypeUpDown, page) {
    	var secondCategory = $('#category').val();
    	var location = $.cookie('cur_location');
    	var searchKeyWord = $('#searchKy').val();
        $.ajax({
            type: "GET",
            url: '/serviceList/getService?secondCategory='+secondCategory+'&inOrderType='+inOrderType+'&inOrderTypeUpDown='+inOrderTypeUpDown+'&location='+location+'&searchKeyWord='+searchKeyWord+'&page='+page,
            data: '',
            success: function(data){
                showService(data);
                pageList(page,data.data.serviceAllNumber);
            }
        });
    }
    
	function showService(data) {
    	var serviceArr = data.data.serviceList;
        if(serviceArr.length > 0){
        	$('#contentList div').remove();
        	var htmlBody = '';
            for(var i = 0;i < serviceArr.length; i++){
                if (i % 5 == 0) {
                	htmlBody += '<div class="vertical">';
                }
                htmlBody += '<div class="item">'+
                				'<a href="javascript:;" class="box">'+
                					'<img src="'+serviceArr[i].imageSrc+'" />'+
                				'</a>'+
                				'<input type="hidden" value="'+serviceArr[i].serviceId+'">'+
                				'<p class="name">'+serviceArr[i].title+'</p>'+
                				'<p class="detail">'+serviceArr[i].oneIntroduction+'</p>'+
                				'<p class="price">'+
	                				'<span class="real">¥'+serviceArr[i].groupPrice+'</span>'+
	                				'<span class="discount">¥'+serviceArr[i].originalPrice+'</span>'+
                				'</p>'+
                			'</div>';
                
                if (i % 5 == 4 || i == serviceArr.length - 1) {
                	htmlBody += '</div>';
                }
            }
            htmlBody += '<div class="page-group"></div>';
            $(htmlBody).appendTo($('#contentList'));
            
            $('.item').click(function(){
            	var t = $(this);
        		//console.log(t.children('input').val());
        		location.href = "/serviceDetail?serviceId=" + t.children('input').val();
        	});
        }
    }
    
	function pageList(page,total) {
	   // var pg = page;
	    var currentPage = page;
	    var total = total;
	    var itemsOnPage = 3;//每页几条记录
	    if(total>999){
	    	total = '999+';
	    }
	    if(total <= itemsOnPage)
	        return;
	    
	    var options = {
	        items: total,
	        itemsOnPage: itemsOnPage,
	        currentPage: currentPage,
	        displayedPages: 3,//页面显示几个可点击页码//TODO
	        hrefTextPrefix:"?pg=",
	        edges: 0,
	        prevText: "上一页",
	        nextText: "下一页",
	        cssStyle:'light-theme',
	        onPageClick: function (page, event) {
	            if (event) {
	                event.preventDefault();
	                var href = decodeURI($(event.target).attr("href"));
	                var pg = getParameter('pg',href);
	                getServicelist(1, 0, pg);
	            }
	        }
	    };
	    $('.page-group').pagination(options);
	}
	function getParameter(sProp, search) {
	    var re = new RegExp(sProp + "=([^\&]*)", "i");
	    var a = re.exec(search);
	    if (a == null)
	        return null;
	    return a [1];
	}
    
    $("#city_change,#city_list").mouseover(function(){
		$("#city_change").attr("class","city_Hover");
		$("#city_list").attr("class","city_list");
	});
	$("#city_change,#city_list").mouseout(function(){
		$("#city_change").attr("class","city_change");
		$("#city_list").attr("class","none");
	});
	$(".sequence ul li").click(function(){
		
		var type = $(this).attr("id");
		var asd = '';
		if($(this).find('i').hasClass('des-arrow')){
			$(this).find('i').removeClass('des-arrow').addClass('asc-arrow');
			asd = '1';
		}else if($(this).find('i').hasClass('asc-arrow')){
			$(this).find('i').removeClass('asc-arrow').addClass('des-arrow');
			asd = '0';
		}
		getServicelist(type, asd, 1);
	});
</script>
</body>
</html>