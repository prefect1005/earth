<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="../static/css/publish-success.css"/>
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
    <script src="../static/js/jquery.validate.js"></script>
    <style>
        .fwcontent-right-bottom>ul>li{
            width: 601px;
            margin-left: 243px;
        }
        .fwcontent-left-nav ul .a a:hover{ background:#F7F7F7; border-bottom:2px solid #1495cf; color:#ffffff;}
    </style>
</head>
<body>
<!------------------------------------top----------------------------------------->
<div class="header" style="width:100%; background: #0084C2;color: #ffffff">
    <div class="clearfix" style="width: 1200px;margin-left: auto;margin-right: auto">
        <a href="/home/index"><img style="float: left" src="../static/img/admin-logo.png"></a>
        <div style="padding: 32px;font-size: 20px;float: left">运营后台->修改密码</div>
        <div class="clearfix" style="float: right;padding: 38px 0px;">
            <div id="userName" class="userName" style="float: left;padding-right: 20px;"></div>|
            <a id="quitOrLogin" style="color: #fff; padding-left: 20px; float: right;padding-left: 20px;">退出</a>
        </div>
    </div>
</div>
<!------------------------------------top-End----------------------------------------->
<!-----------------------------content-------------------------------------->
<div class="fwcontent">
    <div class="fwcore">

        <!-----------------------------content-left-------------------------------------->
        <div class="fwcontent-left">

            <div class="fwcontent-left-bt">后台运营系统</div>

            <div class="fwcontent-left-nav">
                <ul>
                    <li><a href="/adminOrder">所有订单</a></li>
                    <li><a href="/adminProvider">服务商列表</a></li>
                    <li><a href="/adminEmploy">雇佣方列表</a></li>
                    <li><a href="/adminWithDraw">提现列表</a></li>
                    <li><a href="/adminAddUser">创建运营账号</a></li>
                    <li><a class="dingw" href="/adminModifyPwd">修改密码</a></li>
                    <li><a href="/adminBasicInfo">个人信息</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <form id="content-form">
        <div class="fwcontent-right">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">修改密码</div>
                <div class="fwcontent-right-top02"></div>
            </div>
            <div class="fwcontent-right-bottom" style="padding-top: 160px;">
                <ul>
                    <li><div class="fwcontent-right-bt"><span>*</span>原始密码：</div><div class="fwcontent-right-wb"><input id="oldPwd" name="oldPwd" type="text"  style="width: 200px;"></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>新  密  码：</div><div class="fwcontent-right-wb"><input id="newPwd" name="newPwd" type="text" style="width: 200px;"></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>确认新密码：</div><div class="fwcontent-right-wb"><input id="confirmNewPwd" name="confirmNewPwd" type="text" style="width: 200px;"></div></li>
                    <li><div class="fwcontent-right-fb" style="margin-left: 136px;"><input type="submit" value="确认" style="width: 200px;"></div></li>
                </ul>
            </div>

        </div>
        </form>
        <!-----------------------------content-right-End-------------------------------------->

    </div>
</div>
<!-----------------------------content-End-------------------------------------->

<!-----------------------------------footer------------------------------------------>
<div class="fwfooter">
    <div class="fwcore">
        <div class="fwfooter01">©2016 nuomi.com 京ICP证030173号 京公网安备11010802014106号 营业执照信息</div>
        <div class="fwfooter02"></div>
    </div>
</div>
<!-----------------------------------footer-End------------------------------------------>
<script type="text/javascript">
$(function () {
	getLoginStatusToShow();
	
	$("#content-form").validate({
		ignore: '*:not([name])',
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		debug : true,
		rules : {
			oldPwd : "required",
			newPwd : "required",
			confirmNewPwd : "required"
		},
		messages : {
			oldPwd : "不允许为空",
			newPwd : "不允许为空",
			confirmNewPwd : "不允许为空"
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		},
        submitHandler:function(form){ //通过之后回调
        	console.log("insss");
            var oldPwd = $('#oldPwd').val(),
            newPwd = $('#newPwd').val(),
            confirmNewPwd = $('#confirmNewPwd').val();
            if(newPwd != confirmNewPwd) {
                alert("新密码两次输入的密码不一致！");
                return;
            }
            
            $.ajax({
                type: "post",
                url: "/modifyPwd",
                data: {oldPwdd : oldPwd, newPwdd : newPwd},
                dataType: "json",
                success: function(data){
                    if (data.success) {
                    	location.href = data.data.location;
                    }
                }
            });
        },
        
        invalidHandler : function(form,validator) { //不通过回调
			return false;
		}
    });
	
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
</script>
</body>
</html>