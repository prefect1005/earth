<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>发布成功</title>
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
                    <li><a class="dingw"  href="/providerPublish">发布服务</a></li>
                    <li><a href="/providerMyAccount">我的账号</a></li>
                    <li><a href="/providerModifyPwd">修改密码</a></li>
                    <li><a href="${basicInfoUrl}">基本信息设置</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <div class="fwcontent-right">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">发布成功</div>
                <div class="fwcontent-right-top02"></div>
            </div>

            <div class="fwcontent-right-bottom">
                <div class="success-panel">
                  <i></i>
                    <div class="panel-con">
                        <div class="panel-up clearfix">
                            <div class="success-logo fl" style="float:left"></div>
                            <div class="success-info fl" style="float:right;">
                                <p class="title">恭喜您！服务发布成功</p>
                            </div>
                        </div>
                        <div class="panel-btn">
                            <span class="btn-goon fl" style="margin-left: 90px;">  <a style="color:#ffffff" href="/providerPublish">继续发布内容</a></span>
                            <a href="/myPublish" class="link fl">查看已发布的服务</a>
                        </div>
                    </div>
                </div>

            </div>

        </div>
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