<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>运营中心</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
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
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
    <style type="text/css">
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
            width: 50px;
            background: #e1eef7;
            color: #42413f;
            font-family: "微软雅黑";
            font-size: 14px;
            border: none;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        .Servicer div{
        	padding:0px 5px;
        	font-size: 13px;
        }
    </style>
</head>
<body style="height: auto;width: 100%;margin: 0px">
<div class="header" style="width:100%; background: #0084C2;color: #ffffff">
    <div class="clearfix" style="width: 1200px;margin-left: auto;margin-right: auto">
        <a href="/home/index"><img style="float: left" src="../static/img/admin-logo.png"></a>
        <div style="padding: 32px;font-size: 20px;float: left">运营后台->提现列表</div>
        <div class="clearfix" style="float: right;padding: 38px 0px;">
            <div id="userName" class="userName" style="float: left;padding-right: 20px;"></div>|
            <a id="quitOrLogin" style="color: #fff; padding-left: 20px; float: right;padding-left: 20px;">退出</a>
        </div>
    </div>
</div>
<div class="fwcontent">
    <div class="fwcore">

        <!-----------------------------content-left-------------------------------------->
        <div class="fwcontent-left">

            <div class="fwcontent-left-bt">后台运营系统</div>

            <div class="fwcontent-left-nav" style="min-height: 727px;">
                <ul>
                    <li><a href="/adminOrder">所有订单</a></li>
                    <li><a href="/adminProvider">服务商列表</a></li>
                    <li><a href="/adminEmploy">雇佣方列表</a></li>
                    <li><a class="dingw" href="/adminWithDraw">提现列表</a></li>
                    <li><a href="/adminAddUser">创建运营账号</a></li>
                    <li><a href="/adminModifyPwd">修改密码</a></li>
                    <li><a href="/adminBasicInfo">个人信息</a></li>
                </ul>
            </div>
        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <div class="right">
            <div class="title" style="margin-bottom: 20px; text-align: center;font-size: 16px;padding: 40px 50px;">
                <span style="margin-left:50px;font-size: 16px;">选择要检索的类目（列）：</span>
                <select id="field-select">
                    <option value="">全部</option>
                    <option value="name">雇佣方名称</option>
                    <option value="phone">手机号</option>
                    <option value="bankId">银行卡号</option>
                    <option value="status">提现状态</option>
                </select>
                <input type="text" id="kw" placeholder="输入检索关键词" id="query"/>
                <input type="button" value="搜索" id="search-btn"/>
            </div>
            <div class="listcon" style="background: #FFFFFF;text-align: center;font-size: 16px;">
                <div class="listhead">
                    <div class="row clearfix">
                        <div style="width: 25%">服务商名称</div>
                        <div style="width: 15%">手机号</div>
                        <div style="width: 20%">银行卡号</div>
                        <div style="width: 10%">提现额度</div>
                        <div style="width: 10%">提现时间</div>
                        <div style="width: 10%">提现状态</div>
                        <div style="width: 10%">操作</div>
                    </div>
                </div>
                <div class="list"></div>
                <div class="pager" style="float: right;"></div>
            </div>
            
        </div>
        <!-----------------------------content-right-End-------------------------------------->

    </div>
</div>
<script type="text/javascript">
   var page = 1, query = '' ,row = '';
   //var page = 1;
    $(function () {
    	getSearchList(page, row, query);
    	getLoginStatusToShow();
    });
    
    function getLoginStatusToShow(){
    	var accountShow = $.cookie('accountShow');
    	if (typeof(accountShow) != "undefined") {
    		if(accountShow != '13800000000'){
    			$(".fwcontent-left-nav ul").find("li").eq(4).css("display","none");
    		}
    		$('#userName').text('Hi,'+accountShow);
    		$('#quitOrLogin').text('退出');
    		$('#quitOrLogin').attr('href', '/quit');
    	} else {
    		$('#userName').text('Hi,游客');
    		$('#quitOrLogin').text('登录');
    		$('#quitOrLogin').attr('href', '/loginPage');
    	}
    }
    function getParams() {
        row = $('#field-select').val();
        query = $.trim($('#kw').val());
    }
    function clickSearch(){
    	page = 1;
        getParams();
        getSearchList(page,row,query);
    }
    //点击“检索”
    $("#search-btn").click(function () {clickSearch()});
    
    $("#kw").keydown(function (event) {
        if (event && event.keyCode == 13) {
            $("#search-btn").click();
        }
    });
    //获取订单数据
    function getSearchList(page,row,query) {
        $('div.loading').show();
        $.ajax({
            type: "GET",
            url: '/adminWithDraw/getProviderWithDrawDetail?page='+page + '&row=' + row + '&queryKey=' + query,
            data: '',
            success: function(d){
            	console.log(d);
            	$('div.loading').hide();
                showEmploy(d);
                pageList(page,d.data.count,row,query);
            }
        });
    }
    
    function showEmploy(data) {
        var withDrawList = data.data.withDrawList;
        if(withDrawList.length > 0){
        	$('.list .Servicer').remove();
            for(var i = 0;i < withDrawList.length; i++){
                var htmlBody = '';
                htmlBody += '<div class="Servicer clearfix" style="border: 1px solid #bfbfbf; height: 36px;padding: 5px 0px;color: #3e6372">'+
	                			'<div class="comName" style="float: left;width:25%"><a href="'+withDrawList[i].proviceShopUrl+'">'+withDrawList[i].providerName+'</a></div>'+
	                			'<div class="phoneNum" style="float: left;width: 15%">'+withDrawList[i].providerNumber+'</div>'+
	                			'<div class="countNum" style="float: left;width: 20%">'+withDrawList[i].providerBankIdCard+'</div>'+
	                			'<div class="money" style="float: left;width: 10%">'+withDrawList[i].amount+'</div>'+
	                			'<input type="hidden" value="'+withDrawList[i].withDrawId+'" class="withDrawId"/>'+
	                			'<div class="time" style="float: left;width: 10%">'+withDrawList[i].createTime+'</div>';
       			if(withDrawList[i].status == 0){
       				htmlBody +=	'<div class="type" style="float: left; width: 10%">待转账</div>'+
       							'<div class="cost" style="text-align: center;float: left;width: 10%;">'+
				                   	'<input type="button" value="转账" class="operation"/>'+
				                '</div>'
				  				'</div>';
       			}else if(withDrawList[i].status == 1){
       				htmlBody +=	'<div class="type" style="float: left; width: 10%">已转账</div>'+
       							'<div class="cost" style="text-align: center;float: left;width: 10%;">'+
				                   	//'<input type="button" value="转账" id="operation" disabled/>'+
				                '</div>'
				  				'</div>';
       			}
                $(htmlBody).appendTo($('.list'));
            }
            $('.operation').click(function () {
            	var tt = $(this);
                var withDrawId = $('.withDrawId',$(this).parent().parent()).val();
                console.log(withDrawId);
                $.ajax({
                    type: "GET",
                    url: '/adminWithDraw/updateWithDrawStatus?withDrawId='+withDrawId,
                    data: '',
                    success: function(d){
                    	console.log(d);
                    	if(d.data.result){
                    		alert('转账成功！');
                    		tt.parent().parent().find('.type').text('已转账');
                    		tt.remove();
                    	}else{
                    		alert('转账失败！')
                    	}
                    }
                });
            });
        }else{
        	$('.list .Servicer').remove();
        	var wu = '<div class="Servicer clearfix" style="border: 1px solid #bfbfbf; height: 36px;padding: 5px 0px;color: #3e6372">没有匹配结果</div>'
        	$(wu).appendTo($('.list'));
        }
    }
    function pageList(page,total,row,query) {
        var pg = page;
        var currentPage = pg;
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
                    var pg = getParameter('pg',href);
                    getSearchList(pg,row,query);
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
</script>
</body>
</html>