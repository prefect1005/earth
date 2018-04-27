<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的订单</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="../static/css/publish-success.css"/>
    <link rel="stylesheet" href="../static/js/simplePagination/jquery.simplePagination.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="../static/css/ie7.css" type="text/css" media="screen" />
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" href="../static/css/ie8.css" type="text/css" media="screen" />
    <![endif]-->
    <!--[if lte IE 8]><!-->
    <script type="text/javascript" src="../static/js/HTML5.js"></script>
    <script type="text/javascript" src="../static/js/jquery-1.11.3.min.js"></script>
    <!--<![endif]-->

    <!--[if !IE]><!-->
    <script type="text/javascript" src="../static/js/jquery-2.1.4.min.js"></script>
    <!--<![endif]-->

    <!--[if gte IE 9]><!-->
    <script type="text/javascript" src="../static/js/jquery-2.1.4.min.js"></script>
    <!--<![endif]-->
    <script type="text/javascript" src="../static/js/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="../static/js/location.js"></script>
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
    <style>
        .fwcontent-right-bottom>ul>li{
            width: 601px;
            margin-left: 243px;
        }
        .right{
            position: relative;
            width: 925px;
            float: left;
            margin-left: 25px;
            margin-top: 20px;
            padding-top: 15px;
            min-height: 895px;
            margin-bottom: 100px;
        }
        .title,.lists{
            width: 100%;
            background: #FFFFFF;
        }
        .title input[type='text']{
            width: 290px;
            padding: 0 10px;
            overflow: hidden;
            height: 28px;
            border: 1px solid #a8a8a8;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        .title input[type='button']{
            width: 90px;
            height: 28px;
            border: none;
            background: #009fe9;
            color: #ffffff;
            font-family: "微软雅黑";
            font-size: 16px;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        .title select{
            height: 28px;
            width: 100px;
            margin-right: 15px;
        }
        .top{
            background: #ffffff;
            width: 100%;
            height: 26px;
            line-height: 26px;
            border-bottom: 1px solid #bfbfbf;
        }
        .row div{
            float: left;
            padding: 10px 0px;
            border-bottom: 2px solid #bfbfbf;
        }
        .ser-describe div{
            text-align: left;
            font-family: '微软雅黑';
            font-size: 12px;
            margin-left: 20px;
            width: 170px;;
        }
        .cost input[type='button']{
            cursor: pointer;
            width: 90px;
            height: 35px;
            line-height: 35px;
            background: #e1eef7;
            color: #42413f;
            font-family: "微软雅黑";
            font-size: 14px;
            border: none;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        .remark{
            height: 25px;
            text-align: left;
            padding: 0 10px;
            color: cadetblue;
        }
        .record-list{
    		width: 100%;
		    line-height: 26px;
		    padding-top: 15px;
		    margin-left: 36px;
		    font-family: "微软雅黑";
		    font-size: 14px;
		    color: #434343;
		    float: left;
    	}
        .black_overlay {
            display: none;
            position: absolute;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 200%;
            background-color: black;
            z-index:1001;
            -moz-opacity: 0.8;
            opacity:.80;
            filter: alpha(opacity=80);
        }
        .modal-container {
            display: none;
            position: fixed;
            background-color: #ffffff;
            border-radius:4px;
            top: 20%;
		    left: 40%;
		    width: 20%;
            z-index:1002;
            overflow: auto;
            margin: auto;
            padding: 10px;
        }
        .modal-body{
            height: 75%;
            overflow: auto;
            padding: 20px 10px 10px 10px;
        }
        .modal-container .closeIcon {
            background: url(../images/icons.png) no-repeat -201px -106px;
            cursor: pointer;
        }
        .modal-title {
            text-align: center;
            font-size: 17px;
            color: #5d7cad;
            font-weight: bold;
            height: 30px;
        }
        #subText{
            width: 100%;
            color: #414141;
            height: 24px;
        }
        #subBtn{
            width: 90px;
            height: 28px;
            border: none;
            background: #009fe9;
            color: #ffffff;
            font-family: "微软雅黑";
            font-size: 16px;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
            width: 100%;
            margin-top: 30px;
        }
    </style>
</head>
<body>
<!------------------------------------top----------------------------------------->
<div class="fwtop">
    <div class="fwcore">
        <a href="/home/index"><div class="logo"><img src="../static/img/serlogo.png"></div></a>
        <div class="fwaccount">
            <ul>
                <li><a href="">个人中心</a></li>
                <li><a id="quitOrLogin" href="#"><span class="t">退出</span></a></li>
                <li><span id="userName" class="n">Hi，sea joan</span></li>
            </ul>
        </div>

    </div>
</div>
<!------------------------------------top-End----------------------------------------->
<!-----------------------------content-------------------------------------->
<div class="fwcontent">
    <div class="fwcore">

        <!-----------------------------content-left-------------------------------------->
        <div class="fwcontent-left">

            <div class="fwcontent-left-bt">雇佣机构中心</div>

            <div class="fwcontent-left-nav">
                <ul>
                    <li><a class="dingw" href="">我的订单</a></li>
                    <li><a href="/employModifyPwd">修改密码</a></li>
                    <li><a href="${basicInfoUrl}">基本信息设置</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <div class="right">
            <div class="top">
                <div class="fwcontent-right-top01">我的订单</div>
                <div class="fwcontent-right-top02"></div>
            </div>
            <div class="title" style="margin-bottom: 20px; text-align: center;font-size: 16px;padding: 40px 50px;">
                <span style="margin-left:50px;font-size: 16px;">选择要检索的类目（列）：</span>
                <select id="field-select">
                    <option value="">全部</option>
                    <option value="serviceTitle">服务名称</option>
                    <option value="status">交易状态</option>
                </select>
                <input type="text" id="kw" placeholder="输入检索关键词" id="query"/>
                <select id="status-select" style="width: 120px; display:none">
                    <option value="1">待付款</option>
                    <option value="2">服务进行中</option>
                    <option value="3">待评价</option>
                    <option value="4">已完成</option>
                    <option value="0">取消订单</option>
                </select>
                <input type="button" value="搜索" id="search-btn"/>
            </div>
            <div class="listcon" style="background: #FFFFFF;text-align: center;font-size: 16px;">
                <div class="listhead">
                    <div class="row clearfix">
                        <div style="width: 30%">服务名称</div>
                        <div style="width: 20%">成交价格</div>
                        <div style="width: 10%">数量</div>
                        
                        <div style="width: 20%">交易状态</div>
                        <div style="width: 20%">操作</div>
                    </div>
                </div>
                <div class="list"></div>
                <div class="pager" style="float: right;"></div>
            </div>
        </div>
        <!-----------------------------content-right-End-------------------------------------->
    </div>
</div>
<!-----------------------------content-End-------------------------------------->
<div class="loading" style="display:none;position:absolute;left:60%;top:60%;margin-left:-35px;z-index:9999;width:70px;">
        <img src="../static/img/loading.gif" style="margin-left:10px;">
        <div style="font-size:12px;">正在加载中...</div>
</div>
<!----------------------------------评价------------------------------------------>
<div id="modal" class="modal-container">
    <div class="modal-title" >
        <span class="title">评价</span>
        <span class="closeIcon" style="width:20px;height: 20px;;float: right">X</span>
    </div>
    <div class="modal-body">
    	<form style="padding: 10px 0px;">
    	<span>等级：</span>
			<input type="radio" checked="checked" name="level" value="1"/>1分
			<input type="radio" checked="checked" name="level" value="2"/>2分
			<input type="radio" checked="checked" name="level" value="3"/>3分
			<input type="radio" checked="checked" name="level" value="4"/>4分
			<input type="radio" checked="checked" name="level" value="5"/>5分
		</form>
        <div><textarea id="content" style="width:100%;" rows="7" cols="30">请输入评价内容。</textarea></div>
        <div><input id="subBtn" type="button" class="subBtn" value="确认" /></div>
    </div>
</div>
<div id="fade" class="black_overlay"></div>
<!-----------------------------------footer------------------------------------------>
<div class="fwfooter">
    <div class="fwcore">
        <div class="fwfooter01">©2016 nuomi.com 京ICP证030173号 京公网安备11010802014106号 营业执照信息</div>
        <div class="fwfooter02"></div>
    </div>
</div>
<!-----------------------------------footer-End------------------------------------------>
<script type="text/javascript">
    var pg = 1, count = 30,query = '',row = '';
    
    var providerId = '';
    var token = '';
    var serviceId = '';
    var idNo = '';
    var THIS = '';
    
    $(function () {
    	getLoginStatusToShow();
    	getSearchList();
    });
    
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
    	$('#field-select').val('');
    }
    
    function getParams() {
        row = $('#field-select').val();
        if($('#field-select').val() == 'status'){
        	query =$("#status-select").val();
        }else{
        	query = $('#kw').val();
        }
    }

    //点击“检索”
    $("#search-btn").click(function () {
        pg = 1;
        getParams();
        getSearchList();
       
    });
    $("#kw").keydown(function (event) {
        if (event && event.keyCode == 13) {
            $("#search-btn").click();
        }
    });
    //搜‘状态’列时，显示下拉列表
    $('#field-select').change(function(){
    	console.log($(this).val());
    	if($(this).val() == 'status'){
    		$("#status-select").show();
    		$('#kw').hide();
    		query = $("#status-select").val();
    	}else{
    		$("#status-select").hide();
    		$('#kw').show();
    		query = $('#kw').val();
    	}
    });
    //获取订单数据
    function getSearchList() {
        $('div.loading').show();
        var data = { page : pg, row : row, queryKey : query};
        $.get('/employOrder/getEmployOrderDetail', data, function (d) {
            $('div.loading').hide();
            showSearchList(d);
            pageList(pg,d.data.totalSize);
        }, 'json');
    }
    
    function pageList(page,total) {
        //var pg = page;
        var currentPage = page;
        var total = total;
        var itemsOnPage = 2;//页面显示几个可点击页码//TODO
        if(total>999){
        	total = '999+';
        }
        if(total <= itemsOnPage){
        	$('.pager').empty();
        	return;
        }
        
        var options = {
            items: total,
            itemsOnPage: itemsOnPage,
            currentPage: currentPage,
            displayedPages: 5,
            hrefTextPrefix:"?pg=",
            edges: 0,
            prevText: "上一页",
            nextText: "下一页",
            cssStyle:'light-theme',
            onPageClick: function (page, event) {
                if (event) {
                    event.preventDefault();
                    var href = decodeURI($(event.target).attr("href"));
                    pg = getParameter('pg',href);
                    getSearchList();
                }
            }
        };
        $('.pager').pagination(options);
    }
    
    function getParameter(sProp, search) {
        var re = new RegExp(sProp + "=([^\&]*)", "i");
        var a = re.exec(search);
        if (a == null)
            return null;
        return a [1];
    }
    
    function showSearchList(data) {
        var htmlBody = '';
        
        var employOrder = data.data.employOrderShowList;
        $('.list').empty();
        if(employOrder.length > 0){
            for(var i = 0;i < employOrder.length; i++){
            	var isCancel = employOrder[i].isCancel;
            	var orderStatus = employOrder[i].orderStatus;
            	var textStatus;
            	var buttonText;
            	var buttonCancel = '';
            	switch (orderStatus)
            	{
            	case 0:
            		textStatus = "待付款";
            		buttonText = "付款";
            		buttonCancel = '取消订单';
            	  break;
            	case 1:
            		textStatus = "待确认服务";
            		buttonText = "确认服务完成";
            	  break;
            	case 2:
            		textStatus = "待评价";
            		buttonText = "评价";
            	  break;
            	case 3:
            		textStatus = "已完成";
            		buttonText = "完成";
            	  break;
            	}
            	
            	if (isCancel == 1) {
            		textStatus = "已取消";
            		buttonText = "完成";
            	}
            	
                htmlBody += '<div class="indent" style="border: 1px solid #bfbfbf; height: 150px;">'+
				                '<div class="indentUp clearfix" style="background: #F1F1F1;height:40px;">'+
				                	'<div class="time" style="float: left; width: 260px;padding-right: 20px;margin-top: 9px;margin-bottom: 9px;">订单生成时间：<span>'+employOrder[i].createTime+'</span></div>'+
				                	'<div class="iid" style="float: left; width: 260px; margin-top: 9px;margin-bottom: 9px;">订单编号：<span>'+employOrder[i].orderNo+'</span></div>'+
				               		'<div class="ser-com" style="float: left; width: 260px; margin-top: 9px;margin-bottom: 9px; color: #00A1E9"><span >'+employOrder[i].serviceProviderName+'</span></div>'+
				                '</div>'+
				                '<div class="indentBle clearfix" style="height: 110px;padding: 19px 10px;">'+
					                '<div class="server-name clearfix" style="float: left;width: 35%">'+
					                '<div class="img" style="float: left"><img style="width: 71px; height: 67px;" src="'+employOrder[i].serviceLogoUrl+'"></div>'+
					                '<div class="ser-describe" style="float: left;">'+
					                	'<div class="ser-title" style="color: #00A1E9">'+employOrder[i].serviceTitle+'</div>'+
					                	'<div style="color: #C5C5C5;">此商品为定制服务，不支持中途变更服务需求。</div>'+
				                	'</div>'+
				                '</div>'+
				                '<div class="cost" style="text-align: center;float: left;width: 15%;padding: 25px 0px;">￥<span>'+employOrder[i].dealPrice+'</span></div>'+
				                '<div class="num" style="text-align: center;float: left;width: 10%;padding: 25px 0px;">1 </div>'+
				                '<div class="textStatus" style="text-align: center;float: left;width: 20%;padding: 25px 0px;"><span>'+textStatus+'</span></div>'+
				                '<div class="cost" style="text-align: center;float: left;width: 20%;padding: 25px 0px;">'+
				                	'<input type="hidden" class="providerId" value="'+employOrder[i].providerId+'">'+
				                	'<input type="hidden" class="token" value="'+employOrder[i].token+'">'+
				                	'<input type="hidden" class="serviceId" value="'+employOrder[i].serviceId+'">';
			                	if(buttonCancel != ''){
			                		htmlBody += '<input style="width: 65px; margin: 0px 5px;" type="button" value="'+buttonText+'" class="operation"/>'+
			                					'<input style="width: 65px; margin: 0px 5px;" type="button" value="'+buttonCancel+'" class="cancle"/>';
			                	}else{
			                		htmlBody += '<input type="button" value="'+buttonText+'" class="operation"/>';
			                	}
				              	htmlBody += '</div>'+
				                '</div>'+
				                '</div>';
            }
            $(htmlBody).appendTo($('.list'));
            $('.cancle').click(function(){
            	var $th = $(this);
            	var cancleToken = $('.token',$(this).parent().parent()).val();
            	$.ajax({
                    type: "get",
                    url: '/employOrder/cancelOrder?token='+cancleToken,
                    data:'',
                    dataType: "json",
                    success: function(d){
                    	console.log(d);
                    	if(d.data.msg == '取消订单成功'){
                    		$th.parent().parent().find('.textStatus span').text('已取消');
                    		$th.parent().find('.operation').remove();
                    		$th.val('完成');
                    	}
                    	alert(d.data.msg);
                    }
                });
            });
            $('.operation').click(function () {
            	var tt = $(this);
            	THIS = tt;
                providerId = $('.providerId',$(this).parent().parent()).val();
                token = $('.token',$(this).parent().parent()).val();
                serviceId = $('.serviceId',$(this).parent().parent()).val();
                idNo = $('.iid span',$(this).parent().parent().parent()).text();
                console.log(providerId);
                console.log(token);
                console.log(serviceId);
                console.log(idNo);
                
                if($(this).val() == '付款'){
                	location.href = "/order/payOrderParams?idNo="+idNo+"&token="+token;
                }else if($(this).val() == '确认服务完成'){
                	var jsonData = {};
                    jsonData.idNo = idNo,
                    jsonData.proviceId = providerId,
                    jsonData.token = token;
                    
                	$.ajax({
                        type: "post",
                        url: '/employOrder/confimeServiceFinish',
                        contentType : "application/json;utf-8",
                        data: JSON.stringify(jsonData),
                        dataType: "json",
                        success: function(d){
                        	if(d.data.msg == 'true'){
                        		THIS.parent().parent().find('.textStatus span').text('待评价');
                        		THIS.val('评价');
                        		alert('确认成功');
                        	} else {
                        		alert(d.data.msg);
                        	}
                        }
                    });
                }else if($(this).val() == '评价'){
                	//弹出窗口
                	$('#fade').css('display','block');
                    $('#modal').css('display','block');
                    $('body').css('overflow-y','hidden');
                }
            });
        }else{
        	var wu = '<div class="indent" style="border: 1px solid #bfbfbf; height: 150px;padding: 60px;">没有搜索结果</div>'
        	$(wu).appendTo($('.list'));
        }
    }
    //确认评价
    $('#subBtn').click(function (){
    	var level  = $('input[name="level"]:checked').val(); 
    	var content = $('#content').val();
    	console.log(level);
    	console.log(content);
    	var jsonData = {};
        jsonData.idNo = idNo,
        jsonData.serviceId = serviceId,
        jsonData.proviceId = providerId,
        jsonData.evaluateLevel = level,
        jsonData.evaluateContent = content,
        jsonData.token = token;
    	$.ajax({
            type: "post",
            url: '/employOrder/saveEvaluateDetail',
            contentType : "application/json;utf-8",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function(d){
            	if(d.data.msg == '评价成功'){
            		THIS.parent().parent().find('.textStatus span').text('已完成');
            		THIS.val('完成');
            	}
            	alert(d.data.msg);
            }
        });
    	
        $('#modal').css('display','none');
        $('#fade').css('display','none');
        $('body').css('overflow-y','auto');
    });
   
    $(".closeIcon").click(function(){
        $('#modal').css('display','none');
        $('#fade').css('display','none');
        $('body').css('overflow-y','auto');
    });
</script>
</body>
</html>