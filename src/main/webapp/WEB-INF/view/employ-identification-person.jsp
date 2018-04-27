<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>个人实名认证</title>
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
    <script src="../static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
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
        .header{
            height:109px;
            width: 845px;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 50px;
            padding-top: 20px;
        }
        .yanzhengma {
            background: #e1eef7;
            color: #42413f;
            font-family: "微软雅黑";
            font-size: 14px;

            border-radius: 2px;
        }
        input.error { border: 1px solid red; }
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
        <!-----------------------------content-right-------------------------------------->
        <form id="content-form">
        <div class="fwcontent-right" style="min-height: 500px; float: none;margin-left: auto;margin-right: auto">

            <div class="header"><img src="../static/img/gu-renzheng.png"></div>

            <div class="fwcontent-right-bottom">
                <ul>
                    <li><div class="fwcontent-right-bt"><span>*</span>真实姓名：</div>
                        <div class="fwcontent-right-wb">
                            <input id="person-name" name="personName" type="text">
                            <div class="btxm">必填项目</div>
                        </div>
                        <div class="fwcontent-right-tixi">请填写您的真实姓名</div>
                    </li>
                    <li><div class="fwcontent-right-bt"><span>*</span>身份证号：</div>
                        <div class="fwcontent-right-wb">
                            <input id="id-card-number" name="idCardNumber" type="text">
                            <div class="btxm">必填项目</div>
                        </div>
                        <div class="fwcontent-right-tixi">请填写您的真实身份证号</div>
                    </li>
                    <li><div class="fwcontent-right-bt">邮箱：</div>
                        <div class="fwcontent-right-wb">
                            <input id="person-email" type="text">
                        </div>
                    </li>
                    <li><div class="fwcontent-right-fb"><input type="submit" value="确认上传"></div></li>
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
				personName : "required",
				idCardNumber : "required",
			},
			messages : {
				personName : "姓名不允许为空",
				idCardNumber : "身份证号不允许为空"
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			
			},
	        submitHandler:function(form){ //通过之后回调
	        	var jsonData = {};
	            jsonData.name = $('#person-name').val(),
	            jsonData.idCardNumber = $('#id-card-number').val(),
	            jsonData.email = $('#person-email').val();
				 
				$.ajax({
		                type: "post",
		                url: "/employIdentify/uploadDetailForPerson",
		                contentType : "application/json;utf-8",
		                data: JSON.stringify(jsonData),
		                dataType: "json",
		                success: function(data){
		                    if (data.success) {
		                    	console.log("认证成功");
		                    	location.href = data.data.location;
		                    }

		                }
		        });
	        },
	        
	        invalidHandler : function(form,validator) { //不通过回调
				return false;
			}
	    });
	})
	
	function getLoginStatusToShow() {
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
</script>
</body>
</html>