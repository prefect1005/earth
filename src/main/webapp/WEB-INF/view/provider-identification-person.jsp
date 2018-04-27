<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>个人实名认证</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
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
    <script src="../static/js/jquery.validate.js"></script>
    <script src="../static/js/select2.js"></script>
    <script type="text/javascript" src="../static/js/area.js"></script>
    <script type="text/javascript" src="../static/js/location.js"></script>
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
        <div class="fwcontent-right" style="float: none; margin-left: auto; margin-right: auto; min-height: 1000px;">

            <div class="header"><img src="../static/img/renzheng_header.png"></div>

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
                    <li><div class="fwcontent-right-bt"><span>*</span>所在地：</div>
                        <select id="loc_province" name="verlocProvience" style="width:100px;">
                        </select>
                        <select id="loc_city" name="verlocCity" style="width:100px; margin-left: 10px">
                        </select>
                        <select id="loc_town" name="verlocTown" style="width:100px;margin-left: 10px">
                        </select>
                    </li>
                    <li><div class="fwcontent-right-bt"><span>*</span>银行卡号：</div>
                        <div class="fwcontent-right-wb">
                            <input id="bank-card-id" name="bankCardId" type="text">
                            <div class="btxm">必填项目</div>
                        </div>
                        <div class="fwcontent-right-tixi">请正确填写您的银行卡号</div>
                    </li>
                    <li>
                        <div class="fwcontent-right-bt"><span>*</span>银行预留手机号：</div>
                        <div class="fwcontent-right-wb">
                            <input type="text" id="phoneNumber" name="phoneNumber">
                            <div class="btxm">必填项目</div>
                        </div>
                        <div class="fwcontent-right-tixi">请填写您的真实手机号</div>
                    </li>
                    <li><div class="fwcontent-right-bt"><span>*</span>短信验证码：</div>
                        <div class="fwcontent-right-wb">
                            <input id="yzmtext" type="text" name="yzmtext" style="width: 146px;margin-right: 30px;">
                            <input class="yanzhengma" style="width: 150px; border: none;" type="submit" value="点击获取短信验证码">
                        </div>
                    </li>

                    <li><div class="fwcontent-right-bt">邮箱：</div>
                        <div class="fwcontent-right-wb">
                            <input id="person-email" type="text" name="personEmail">
                            <div class="btxm">必填项目</div>
                        </div>
                    </li>
                    <li><div class="fwcontent-right-bt" style="line-height: 15px;"><span></span>上传资质（毕业证或从业资格证）：</div>
                        <div class="fwcontent-right-wb4"><div class="file"><input id="file" type="file" multiple accept=".jpg,.gif,.png" onchange="return fileUpload_onselect()">点击上传</div></div>
                        <div class="fwcontent-right-tis">支持jpg/gif/png格式，RGB模式，单张（长<8000,宽<2000,大小<10M）,<br>最多上传3张图片，支持批量上传。不要在图片上放置商业推广图信息（案例信息可以放置）</div>
                        <div style=" width:100%; float:left;">
                            <ul class="fwcontent-right-yyjt">
                                <li><div class="fwcontent-right-yyjt01"><img src="../static/img/yingye.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                                <li><div class="fwcontent-right-yyjt01"><img src="../static/img/yingye.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                            </ul>
                        </div></li>
                    <li><div class="fwcontent-right-fb"><input id="btn-save" type="submit" value="保存"></div></li>
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
		
		showLocation();
		getLoginStatusToShow();
		
		$('.yanzhengma').on('click', function () {
            var that = this;
            var phone = $('#phoneNumber').val();
            $.ajax({
                type: "get",
                url: "/getVCode",
                data: {phone: phone},
                dataType: "json",
                success: function(data){
                    if (data.success) {
                    	console.log("发送成功");
                        $(that).html('重新发送');
                    }

                }
            });
        });
		
		
		$("#content-form").validate({
			ignore: '*:not([name])',
			onsubmit : true,// 是否在提交是验证
			onfocusout : false,// 是否在获取焦点时验证
			onkeyup : false,// 是否在敲击键盘时验证
			debug : true,
			rules : {
				personName : "required",
				idCardNumber : "required",
				verlocProvience : "required",
				verlocCity : "required",
				verlocTown : "required",
				bankCardId : "required",
				phoneNumber : "required",
				yzmtext : "required"
			},
			messages : {
				personName : "姓名不允许为空",
				idCardNumber : "身份证号不允许为空",
				verlocProvience : "省份不允许为空",
				verlocCity : "城市不允许为空",
				verlocTown : "城市不允许为空",
				bankCardId : "卡号不允许为空",
				phoneNumber : "手机号不允许为空",
				yzmtext : "验证码不允许为空"
			},
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			
			},
	        submitHandler:function(form){ //通过之后回调
	        	var jsonData = {};
	            jsonData.name = $('#person-name').val(),
	            jsonData.idCardNumber = $('#id-card-number').val(),
	            
	            jsonData.locationProvinceId = $('#loc_province').val(),
	            jsonData.locationCityId = $('#loc_city').val(),
	            jsonData.locationTownId = $('#loc_town').val(),
	            jsonData.location = $('#loc_province').select2('data').text + ',' + $('#loc_city').select2('data').text + ',' +  $('#loc_town').select2('data').text,
	            
	            jsonData.bankCardId = $('#bank-card-id').val(),
	            
	            jsonData.phoneNumber = $('#phoneNumber').val(),
	            jsonData.verCode = $('#yzmtext').val(),
	            jsonData.email = $('#person-email').val(),
	            jsonData.imgSrcList = new Array();
	            
	            var index = 0;
	       		$(".fwcontent-right-yyjt li div img").each(function(index){
					 if (this.src.indexOf("static/img/yingye.png") <= 0) {
						 jsonData.imgSrcList[index++] = this.src;
					 }
				 })
	            
	            $.ajax({
	                type: "post",
	                url: "/providerIdentify/uploadDetailForPerson",
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
		
		//提交
      //  $('#btn-save').on('click', function () {
        //    var that = this;
            
       // });
		
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
	
	function fileUpload_onselect() {
		var formData = new FormData();
		for (var i = 0; i < 2; i++) {
			formData.append('file', $('#file')[0].files[i]);
		}
		
		$.ajax({
		    url: '/providerIdentify/uploadFile',
		    type: 'POST',
		    cache: false,
		    data: formData,
		    processData: false,
		    contentType: false
		}).done(function(data) {
			console.log(data.success)
			if (data.success) {
				console.log(data.data.imageNumber)
				 var i = 0;
				 $(".fwcontent-right-yyjt li div img").each(function(index){
					 console.log(this.src)
					 if (this.src.indexOf("static/img/yingye.png") > 0) {
						 console.log(data.data.imageSrc[i])
						 $(this).attr("src", data.data.imageSrc[i++])
					 }
				 })
			}
		}).fail(function(res) {});
	}
</script>
</body>
</html>