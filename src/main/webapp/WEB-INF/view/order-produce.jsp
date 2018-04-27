<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-Type" content="text/html;charset=utf-8" />
    <title>购买服务</title>
    <script src="../static/js/jquery.validate.js"></script>
    <style type="text/css">
        body *{
            font-family: 'Microsoft YaHei', sans-serif;
            color: #4d4d4d;
        }
        .main{
            width: 800px;
            margin-left: auto;
            margin-right: auto;
            margin-top: 30px;
            margin-bottom: 30px;
        }
        .title{
            font-size: 30px;
            font-weight: bold;
            height:50px;
            padding: 20px;
        }
        .service{
            padding: 35px;
            height: 120px;
            border-top: solid 1px #00a0e9;
            background: #FAFAFA;
        }
        .serviceInfo div{
            height: 30px;
            color: #0d0d0d;
            font-size: 16px;
            font-weight: bold;
        }
        .serviceInfo span{
            font-size: 14px;
            font-weight: normal;
        }
        .contact,.bottom,.beizhu{
            height: 120px;
            padding: 10px 0;
        }
        .bottom div,.contact div{
            background: #FAFAFA;
            padding: 5px 35px;
        }
        .contact input[type="text"]{
            padding: 0 10px;
            overflow: hidden;
            height: 25px;
            border: 1px solid #a8a8a8;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        input[type="button"]{
            color: #fff;
            font-size: 13px;
            margin-left: -2px;
            padding: 4px 37px;
            border-color: #00a0e9;
            background-color: #00a0e9;
        }
        .clearfix:before, .clearfix:after {
            display: table;
            content: " ";
        }
        .clearfix:after {
            clear: both;
        }
        #sub {
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
        .file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 0px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 27px;

   
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
    </style>
</head>
<body>
<form id="content-form">
<div class="main">
    <div class="title">
        购买服务
    </div>
    <div class="service clearfix">
        <div class="serviceImg" style="float: left;width: 123px;height: 123px;">
            <img style="float: left;width: 123px;height: 123px;" src="${orderShow.iconSrcUrl}">
        </div>
        <div class="serviceInfo" style="float: left;padding: 17px 40px;">
            <div class="keyWords">服务关键词：<span>${orderShow.serviceKeyWord}</span></div>
            <div class="servicer">服务商：<span>${orderShow.serviceProvider}</span></div>
            <div class="transactionPrice">服务成交价格：￥<span>${orderShow.groupPrice}</span></div>
            <input id="serviceId" type="hidden" value="${orderShow.serviceId}">
            <input id="groupPrice" type="hidden" value="${orderShow.groupPrice}">
        	<input id="isAfterSales" type="hidden" value="${orderShow.isAfterSales}">
        </div>
    </div>
    <div class="contact">
        <div class="text" style="font-size: 13px;color:gray">请留下您联系方式，服务商能够尽快的与您沟通。</div>
        <div>
            <input id="phone" type="text" name="phone" style="width: 330px;color:#A6A6A6;" placeholder="请输入手机号码">
        </div>
        <div>
            <input id="codeText" type="text" name="codeText" style="color:#A6A6A6;" placeholder="请输入收到的验证码">
            <input id="codeBtn" type="button" style="border-radius: 4px;" value="获取验证码"/>
        </div>
    </div>
    <div class="beizhu" style="background: #FAFAFA;background: #FAFAFA;padding: 10px 35px;">
        <textarea id="orderRemark" style="height: 115px; width: 100%" placeholder="专业的服务团队"></textarea>
    </div>
    <div style="font-size: 13px;color: #ffffff;border-color: #00a0e9;background-color: #00a0e9;margin-left:35px;" class="file">
        <input style="margin-left: 35px;" id="file" name="file" type="file" onchange="return fileUpload_onselect()">点击上传
        <input id="fileName" type="hidden">
    </div>
    <span style="font-size: 13px;color:gray;">如果您有相关资料，也可以上传给服务商参考</span>
    <div class="bottom" style="font-size: 13px;color:gray;margin-left: 35px;">
        <div style="padding-left:0px;">请输入优惠券码：<input id="reward" type="text" style="width: 200px;color:#A6A6A6;"></div>
        <div style="padding-left:0px;" id="money">总计：<span>${orderShow.groupPrice}</span>元</div>
        <div style="color: #000000; padding-left:0px;">官方提供线上担保交易，保障您的资金安全，且不会收取任何押金，80%的欺诈、资金盗取均有线下交易导致，请勿线下交易。</div>
    </div>
    <div style="float: right;margin: 10px 35px;"><input id="sub" type="submit" value="提交"/></div>
</div>
</form>
<script>
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
    $(function () {
    	
    });
    
    $('#codeBtn').on('click', function () {
        var that = this;
        var phone = $('#phone').val().trim();
        curCount = count;
      //设置button效果，开始计时
        $("#codeBtn").attr("disabled", "true");
        //$("#codeBtn").css('background','#b6b6b6');
        $("#codeBtn").css({'background':'#b6b6b6','border-color':'#b6b6b6'});
        $("#codeBtn").val("请在" + curCount + "秒内输入");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        
        $.ajax({
            type: "get",
            url: "/getVCode",
            data: {phone: phone},
            dataType: "json",
            success: function(data){
                //if (data.success) {
                //	console.log("发送成功");
                //    $(that).val('重新发送');
                //}

            }
        });
    });
    
  //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#codeBtn").removeAttr("disabled");//启用按钮
            $("#codeBtn").val("重新发送验证码");
            $("#codeBtn").css('background','#00a0e9')
        }
        else {
            curCount--;
            $("#codeBtn").val("请在" + curCount + "秒内输入");
        }
    }
    
    function fileUpload_onselect() {
    	var formData = new FormData();
    		formData.append('file', $('#file')[0].files[0]);
    	
    	$.ajax({
    	    url: '/order/uploadFile',
    	    type: 'POST',
    	    cache: false,
    	    data: formData,
    	    processData: false,
    	    contentType: false
    	}).done(function(data) {
    		console.log(data.success)
    		if (data.success) {
    			$('#fileName').val(data.data.fileName);
    		}
    	}).fail(function(res) {});
    }
    
    $("#content-form").validate({
		ignore: '*:not([name])',
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		debug : true,
		rules : {
			phone : "required",
			codeText : "required"
		},
		messages : {
			phone : "手机号不允许为空",
			codeText : "验证码不允许为空"
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		
		},
        submitHandler:function(form){ //通过之后回调
        	var jsonData = {};
            jsonData.serviceId = $('#serviceId').val(),
            jsonData.groupPrice = $('#groupPrice').val(),
            
            //jsonData.payType = $('#payType').val(),
            jsonData.isAfterSales = $('#isAfterSales').val(),
            jsonData.employOrderPhone = $('#phone').val(),
            jsonData.verCode = $('#codeText').val(),
            
            jsonData.orderRemark = $('#orderRemark').val(),
            
            jsonData.orderRemarkFileUrl = $('#fileName').val(),
            jsonData.privilegeId = $('#reward').val(),
            jsonData.totalPrice = $('#groupPrice').val()
            
            $.ajax({
                type: "post",
                url: "/order/submit",
                contentType : "application/json;utf-8",
                data: JSON.stringify(jsonData),
                dataType: "json",
                success: function(data){
                	if (data.success) {
                		location.href = data.data.location;
                	} else {
                		alert(data.data.msg);
                	}
                }
            });
        },
        
        invalidHandler : function(form,validator) { //不通过回调
			return false;
		}
    });
</script>
</body>
</html>