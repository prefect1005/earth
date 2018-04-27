<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>支付</title>
    
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
    
    <style type="text/css">
        body{
            font-family: 'Microsoft YaHei', sans-serif;
            color:  #4d4d4d;
            margin-top: 0px;
            width: 100%;
            margin-left: auto;
            margin-right: auto;
            background: #eeeeee
        }
        .main{
            margin-left: auto;
            margin-right: auto;
            width: 800px;
            height: 800px;
            background: #ffffff;
            margin-top: 50px;
        }
        .mIcon{
            background: url(../static/img/mIcon.png) 0 center no-repeat;
            padding-left: 60px;
            height: 60px;
        }
        .clearfix:before, .clearfix:after {
            display: table;
            content: " ";
        }
        .clearfix:after {
            clear: both;
        }
        input[type="button"]{
            color: #fff;
            font-size: 16px;
            margin-left: -2px;
            padding: 2px 37px;
            border-color: #00a0e9;
            background-color: #00a0e9;
        }
    </style>
</head>
<body>
<div class="header" style="height: 100px;background: #ffffff"></div>
<div class="main">
    <div class="title" style="padding: 30px;">
        <div class="mIcon">
            <div style="font-size: 16px;font-weight: bold;padding: 5px;">收银台</div>
            <div style="font-size: 14px;padding: 5px;">您正在使用【地心引力】的担保交易，资金更安全</div>
        </div>
    </div>
    <div class="QR-code clearfix" style="padding: 10px 30px;border-bottom: 1px solid #eeeeee">
        <div style="float: left;width: 128px;height: 128px;">
            <img height="100%" width="100%" src="${payOrderShow.serviceIconUrl}" alt="">
        </div>
        <!-- <div style="float: left;width: 23px;color: #00a0e9;font-size: 12px;padding-left: 5px;">使用微信扫码支付</div> -->
        <div style="float: left;width: 375px;">
            <div style="padding: 12px 30px;height: 59px;font-weight: bold">交易名称：<span style="font-weight: normal">${payOrderShow.serviceTitle}</span></div>
            <div style="padding: 12px 30px;height: 21px;font-weight: bold">交易号：<span style="font-weight: normal">${payOrderShow.orderNo}</span></div>
        </div>
        <div style="float: right; font-weight: bold;font-size: 16px;padding: 74px 0 33px 0;">应付金额：￥<span>${payOrderShow.totalPirce}</span></div>
        <input id="token" type="hidden" value="${payOrderShow.token}">
        <input id="idNo" type="hidden" value="${payOrderShow.orderNo}">
    </div>
    <div style="text-align:center">
    	<img src="/pay/payImage?orderNo=${payOrderShow.orderNo}&token=${payOrderShow.token}" alt="">
    	<div style="color: #00a0e9;font-size: 12px;padding-left: 5px;">微信扫码支付</div>
    </div>
    <!--  <div class="pay-type" style="border: 1px solid #00a0e9;height: 23px;margin: 30px;padding: 15px 20px;">
        <form action="" method="get">选择支付方式：
            <label><input name="Fruit" type="radio" value="" />支付宝 </label>
            <label><input name="Fruit" type="radio" value="" />微信</label>
        </form>
    </div>
    <div style="text-align: center"><input id="sub" type="button" value="提交"/></div>-->
</div>
<script>
$(function () {
	//定时请求刷新  
    setInterval(checkStatus,2000);
});

//ajax方法执行  
function checkStatus(){
	
	var idNo = $('#idNo').val();
    $.ajax({  
        "url":"/pay/checkOrderPayStatus?idNo="+idNo,
        "datatype":"json",
        "type":"get",  
        "async":"true"
    }).success(function(data){  
          if (data.data.orderStatus == 1) {
        	  alert("支付成功");
        	  location.href = "/employOrder";
          }
    }).error();  
}
</script>
</body>
</html>