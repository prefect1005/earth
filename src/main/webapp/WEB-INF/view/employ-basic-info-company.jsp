<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>企业信息</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link href="../static/css/common.css" rel="stylesheet"/>
    <link href="../static/css/select2.css" rel="stylesheet"/>
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
    <script type="text/javascript" src="../static/js/area.js"></script>
    <script type="text/javascript" src="../static/js/location.js"></script>
    <script src="../static/js/select2.js"></script>
    <script src="../static/js/select2_locale_zh-CN.js"></script>
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
            margin-bottom: 15px;
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
        #up{
            width: 113px;
            height: 27px;
            border: none;
            background: #66ccff;
            font-family: "微软雅黑";
            font-size: 16px;
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
                <li><span id="userName">Hi，sea joan</span></li>
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

            <div class="fwcontent-left-nav" style="min-height: 500px;margin: 0 5px 80px 5px;">
                <ul>
                    <li><a href="/employOrder">我的订单</a></li>
                    <li><a href="/employModifyPwd">修改密码</a></li>
                    <li><a class="dingw" href="#">基本信息设置</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <form id="content-form">
        <div class="fwcontent-right" style="min-height: 500px;">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">企业信息设置</div>
                <div class="fwcontent-right-top02"></div>
            </div>

            <div class="fwcontent-right-bottom">
                <ul>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司名称：</div><div class="fwcontent-right-wb"><input type="text" style="color: #C5C5C5" value="${employBasicInfoCompany.companyName}" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>法人代表：</div><div class="fwcontent-right-wb"><input type="text" value="${employBasicInfoCompany.corporateRepresentative}" style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司属地：</div>
                        <select id="loc_province" name="verlocProvience" style="width:100px;">
                        </select>
                        <select id="loc_city" name="verlocCity" style="width:100px; margin-left: 10px">
                        </select>
                        <select id="loc_town" name="verlocTown" style="width:100px;margin-left: 10px">
                        </select>
                        <input id="hd-province" type="hidden" value="${employBasicInfoCompany.companyLocationProvinceId}">
                        <input id="hd-city" type="hidden" value="${employBasicInfoCompany.companyLocationCityId}">
                        <input id="hd-town" type="hidden" value="${employBasicInfoCompany.companyLocationTownId}">
                        <input id="infoId" type="hidden" value="${employBasicInfoCompany.id}">
                    </li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司详细地址：</div><div class="fwcontent-right-wb"><input type="text" id="addressOfCompany" name="addressOfCompany" value="${employBasicInfoCompany.companyLocationDetail}"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>手机号：</div><div class="fwcontent-right-wb"><input type="text" value="${employBasicInfoCompany.phoneNumber}" style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt">公司邮箱：</div><div class="fwcontent-right-wb"><input type="text" id="companyEmail" name="companyEmail" value="${employBasicInfoCompany.companyEmail}"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt" style="line-height: 15px;"><span>*</span>注册号/社会统一信用代码：</div><div class="fwcontent-right-wb"><input type="text" value="${employBasicInfoCompany.socialUnifiedCreditCode}" style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-fb"><input type="submit" value="保存"></div></li>
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
		showLocation($('#hd-province').val(), $('#hd-city').val(), $('#hd-town').val());
		getLoginStatusToShow();

        $("#content-form").validate({
                ignore: '*:not([name])',
                onsubmit : true,// 是否在提交是验证
                onfocusout : false,// 是否在获取焦点时验证
                onkeyup : false,// 是否在敲击键盘时验证
                debug : true,
                rules : {
                    verlocProvience : "required",
                    verlocCity : "required",
                    verlocTown : "required",
                    addressOfCompany : "required",
                },
                messages : {
                    verlocProvience : "省份不允许为空",
                    verlocCity : "城市不允许为空",
                    verlocTown : "城市不允许为空",
                    addressOfCompany : "详细地址不允许为空",
                },
                errorPlacement : function(error, element) {
                    error.appendTo(element.parent());
                
                },
                submitHandler:function(form){ //通过之后回调
                    var jsonData = {};
                    jsonData.id = $('#infoId').val(),
                    jsonData.companyLocationProvinceId = $('#loc_province').val(),
                    jsonData.companyLocationCityId = $('#loc_city').val(),
                    jsonData.companyLocationTownId = $('#loc_town').val(),
                    jsonData.companyLocation = $('#loc_province').select2('data').text + ',' + $('#loc_city').select2('data').text + ',' +  $('#loc_town').select2('data').text,
                    jsonData.companyLocationDetail = $('#addressOfCompany').val(),
                    jsonData.companyEmail = $('#companyEmail').val();

                    $.ajax({
                            type: "post",
                            url: "/employBasicInfo/updateBasicInfoCompany",
                            contentType : "application/json;utf-8",
                            data: JSON.stringify(jsonData),
                            dataType: "json",
                            success: function(data){
                                if (data.success) {
                                    console.log("更新成功");
                                    alert("更新成功");
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
    		//var li = '<span class="n">Hi，'+accountShow+'</span> | <a href="#"><span class="t">退出</span></a> |';
    		//$(li).appendTo($('#login_top_show'));
    		$('#userName').text('Hi,'+accountShow);
    		$('#quitOrLogin span').text('退出');
    		$('#quitOrLogin').attr('href', '/quit');
    	} else {
    		//var li = '<span class="n">Hi，游客</span> | <a href="/loginPage"><span class="n">登录</span></a> |';
    		//$(li).appendTo($('#login_top_show'));
    		$('#userName').text('Hi,游客');
    		$('#quitOrLogin span').text('登录');
    		$('#quitOrLogin').attr('href', '/loginPage');
    	}
    }
</script>
</body>
</html>