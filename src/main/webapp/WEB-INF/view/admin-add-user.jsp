<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加运营</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
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
    <style type="text/css">
        select{
            position: relative;
            float: left;
            height: 28px;
            width: 100px;
            margin-right: 15px;
        }
        select option{
            height: 28px;
        }
        .fwcontent-right-bottom>ul>li{
            width: 747px;
            float: left;
            margin-bottom: 40px;
            margin-left: 178px;
            line-height: 27px;
        }
        .fwcontent-right-wb{
            width: 330px;
            height: 27px;
            float: left;
        }
        .fwcontent-right-wb2, .fwcontent-right-wb2 textarea, .fwcontent-right-wb input[type='text']{
            width: 330px;
        }
        .fwcontent-right-fb {
            margin-left: 195px;
        }
    </style>
</head>
<body>
<!------------------------------------top----------------------------------------->
<div class="header" style="width:100%; background: #0084C2;color: #ffffff">
    <div class="clearfix" style="width: 1200px;margin-left: auto;margin-right: auto">
        <a href="/home/index"><img style="float: left" src="../static/img/admin-logo.png"></a>
        <div style="padding: 32px;font-size: 20px;float: left">运营后台->运营添加</div>
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
                    <li><a class="dingw" href="/adminAddUser">创建运营账号</a></li>
                    <li><a href="/adminModifyPwd">修改密码</a></li>
                    <li><a href="/adminBasicInfo">个人信息</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <form id="content-form">
        <div class="fwcontent-right">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">创建运营账号</div>
                <div class="fwcontent-right-top02"></div>
            </div>

            <div class="fwcontent-right-bottom">
                <ul>
                    <li><div class="fwcontent-right-bt">手机号码：</div><div class="fwcontent-right-wb"><input id="phone" name="phone" type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt">真实姓名：</div><div class="fwcontent-right-wb"><input id="name" name="name" type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt">所属部门：</div><div class="fwcontent-right-wb"><input id="department" name="department" type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-fb"><input type="submit" value="创建"></div></li>
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
			phone : "required",
			name : "required",
			department : "required",
		},
		messages : {
			phone : "不允许为空",
			name : "不允许为空",
			department : "不允许为空"
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		
		},
        submitHandler:function(form){ //通过之后回调
        	var jsonData = {};
            jsonData.phoneNumber = $('#phone').val(),
            jsonData.name = $('#name').val(),
            jsonData.department = $('#department').val();
			 
			$.ajax({
	                type: "post",
	                url: "/adminAddUser/createAdminUser",
	                contentType : "application/json;utf-8",
	                data: JSON.stringify(jsonData),
	                dataType: "json",
	                success: function(data){
	                    if (data.success) {
	                    	console.log("添加成功");
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