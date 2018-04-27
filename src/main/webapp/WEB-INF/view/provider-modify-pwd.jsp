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
    <script src="../static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="../static/js/jquery.cookie.js"></script>
    <style type="text/css">
        .fwcontent-right-bottom>ul>li{
            width: 601px;
            margin-left: 243px;
        }
        .fwcontent-left-nav ul .a a:hover{ background:#F7F7F7; border-bottom:2px solid #1495cf; color:#ffffff;}
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

        <!-----------------------------content-left-------------------------------------->
        <div class="fwcontent-left">

            <div class="fwcontent-left-bt">服务机构中心</div>

            <div class="fwcontent-left-nav">
                <ul>
                    <li><a href="/providerOrder">我的订单</a></li>
                    <li><a  href="/myPublish">我的发布</a></li>
                    <li><a href="/providerPublish">发布服务</a></li>
                    <li><a href="/providerMyAccount">我的账号</a></li>
                    <li><a class="dingw" href="#">修改密码</a></li>
                    <li><a href="${basicInfoUrl}">基本信息设置</a></li>
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
	                //contentType : "application/json;utf-8",
	                data: {oldPwdd : oldPwd, newPwdd : newPwd},
	                dataType: "json",
	                success: function(data){
	                	console.log("succe");
	                    if (data.success) {
	                    	console.log("更新成功");
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
</script>
</body>
</html>