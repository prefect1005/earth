<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的账户</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="../static/css/my-account.css"/>
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
    <link rel="stylesheet" href="../static/js/simplePagination/jquery.simplePagination.css"/>
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
    <style type="text/css">
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
            position: absolute;
            background-color: #ffffff;
            border-radius:4px;
            top: 30%;
            left: 40%;
            width: 20%;
            height: 30%;
            z-index:1002;
            overflow: auto;
            margin: auto;
            padding: 10px;
        }
        .modal-body{
            height: 65%;
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

            <div class="fwcontent-left-bt">服务机构中心</div>

            <div class="fwcontent-left-nav">
                <ul>
                    <li><a href="/providerOrder">我的订单</a></li>
                    <li><a  href="/myPublish">我的发布</a></li>
                    <li><a href="/providerPublish">发布服务</a></li>
                    <li><a class="dingw"  href="#">我的账户</a></li>
                    <li><a href="/providerModifyPwd">修改密码</a></li>
                    <li><a href="${basicInfoUrl}">基本信息设置</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <div class="fwcontent-right">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">账户信息</div>
                <div class="fwcontent-right-top02">会员等级<span class="vip-level"></span> D${amountList[2]}</div>
            </div>

            <div id="deal" class="fwcontent-right-bottom">
                <div class="account-info">
                       <div class="accountInfo-list">
                           <ul>
                               <li class="record-item fl" style="float:left;">
                                  <div class="record-detail">
                                      <p class="record-price"><span class="price-num">${amountList[0]}</span><span class="point-num">.00</span>元</p>
                                      <p class="record-tag">交易额度</p>
                                      <p class="record-piple"></p>
                                  </div>
                               </li>
                               <li class="record-item fl" style="float:left;">
                                   <div class="record-detail">
                                       <p class="record-price"><span id="yuE1" class="price-num">${amountList[1]}</span><span class="point-num">.00</span>元</p>
                                       <p class="record-tag">账户余额</p>
                                       <p class="record-piple"></p>
                                   </div>
                               </li>
                           </ul>
                       </div>
                       <div class="accountInfo-detail">
                           <p class="toBank">可提现到银行卡</p>
                           <p class="record-price"><span id="yuE" class="price-num">${amountList[1]}</span><span class="point-num">.00</span>元</p>
                           <p><span id="drawBtn" class="withdraw-deposit fl">提现</span></p>
                       </div>
                </div>
                <div class="record-list" style="height: 41px;">交易记录</div>
                <table>
                    <thead>
                       <tr>
                           <th>订单编号</th>
                           <th>订单名称</th>
                           <th>订单日期</th>
                           <th>收支情况</th>
                       </tr>
                    </thead>
                    <tbody></tbody>
                </table>
			 <div class="pager" style="margin-top: 10px; float: right;position: static;"></div>
            </div>
        </div>
        <!-----------------------------content-right-End-------------------------------------->

    </div>
</div>
<!-----------------------------content-End-------------------------------------->
<div id="modal" class="modal-container">
    <div class="modal-title" >
        <span class="title">提现到银行卡</span>
        <span class="closeIcon" style="width:20px;height: 20px;;float: right">X</span>
    </div>
    <div class="modal-body">
        <div><input id="subText" type="text" value="" placeholder="输入提现金额" /></div>
        <div><input id="subBtn" type="button" class="subBtn" value="确认提现" /></div>
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
</body>
<script>
var page = 1;
    $(function(){
    	getLoginStatusToShow();
    	getSearchList(page);
    })
    
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
    //弹出提现窗口
    $("#drawBtn").click(function(){
        $('#fade').css('display','block');
        $('#modal').css('display','block');
        $('body').css('overflow-y','hidden');
    });
    $(".subBtn").click(function(){
    	var amount = $("#subText").val();
    	$.ajax({
            type: "GET",
            url: '/providerMyAccount/providerWithDraw',
            data: {amount:amount},
            success: function(d){
            	if(d.success){
            		var y = parseInt(amount);
            		var x = parseInt($("#yuE").text());
            		var z = parseInt(x-y);
            		$("#yuE").text(z);
            		$("#yuE1").text(z);
            		$("#yuE2").text(z+'.00');
            	}
            	console.log(d);
            	alert(d.data.result);
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
    //获取交易记录列表
    function getSearchList(page) {
    	console.log(page);
//        $('div.loading').show();
        $.ajax({
            type: "GET",
            url: '/providerMyAccount/getProviderIncomeDetail?page='+page,
            data: '',
            success: function(d){
            	console.log(d);
            	//$('div.loading').hide();
                showDealList(d);
                pageList(page,d.data.count);
            }
        });
    }
    
    function showDealList(d) {
        var dealArr = d.data.providerMyAccountIncomeList;
        if(dealArr.length > 0){
        	$('#deal table tbody tr').remove();
            for(var i = 0;i < dealArr.length; i++){
                var tr = '<tr>'+
                    '<td>'+dealArr[i].orderNo+'</td>'+
                    '<td>'+dealArr[i].orderName+'</td>'+
                    '<td>'+dealArr[i].orderNo+'</td>'+
                    '<td>'+dealArr[i].inOutcomeAmount+'</td>'+
                '</tr>';
                $(tr).appendTo($('#deal table tbody'));
            }
        }
    }
    function pageList(page,total) {
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
                    getSearchList(pg);
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
</html>