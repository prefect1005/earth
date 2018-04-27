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
            margin-bottom: 25px;
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
                    <li><a href="/myPublish">我的发布</a></li>
                    <li><a href="/providerPublish">发布服务</a></li>
                    <li><a href="/providerMyAccount">我的账号</a></li>
                    <li><a href="/providerModifyPwd">修改密码</a></li>
                    <li><a class="dingw" href="#">基本信息设置</a></li>
                </ul>
            </div>

        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <form id="content-form">
        <div class="fwcontent-right" style="min-height: 1360px;">

            <div class="fwcontent-right-top">
                <div class="fwcontent-right-top01">个人信息设置</div>
                <div id="status" class="fwcontent-right-top02"></div>
                <input type="hidden" value="${isApprove}" id="isApprove">
            </div>

            <div class="fwcontent-right-bottom">
                <ul>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司名称：</div><div class="fwcontent-right-wb"><input type="text" style="color: #C5C5C5" value=${providerBasicInfoCompany.companyName} readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>法人代表：</div><div class="fwcontent-right-wb"><input type="text" value=${providerBasicInfoCompany.corporateRepresentative} style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司属地：</div>
                        <select id="loc_province" name="verlocProvience" style="width:100px;">
                        </select>
                        <select id="loc_city" name="verlocCity" style="width:100px; margin-left: 10px">
                        </select>
                        <select id="loc_town" name="verlocTown" style="width:100px;margin-left: 10px">
                        </select>
                        <input id="hd-province" type="hidden" value=${providerBasicInfoCompany.companyLocationProvinceId}>
                        <input id="hd-city" type="hidden" value=${providerBasicInfoCompany.companyLocationCityId}>
                        <input id="hd-town" type="hidden" value=${providerBasicInfoCompany.companyLocationTownId}>
                    </li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司详细地址：</div><div class="fwcontent-right-wb"><input id="addressOfCompany" name="addressOfCompany" value=${providerBasicInfoCompany.companyLocationDetail} type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>联系人姓名：</div><div class="fwcontent-right-wb"><input id="contactsName" name="contactsName" value=${providerBasicInfoCompany.contactsName} type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司简介：</div><div class="fwcontent-right-wb2"><textarea id="company-profile" name="companyProfile" placeholder="专业的服务团队">${providerBasicInfoCompany.companyProfile}</textarea></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司邮箱：</div><div class="fwcontent-right-wb"><input id="companyEmail" name="companyEmail" value=${providerBasicInfoCompany.companyEmail} type="text"><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt"><span>*</span>公司账户：</div><div class="fwcontent-right-wb"><input value=${providerBasicInfoCompany.companyBankAccount} type="text" style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt" style="line-height: 15px;"><span>*</span>注册号/社会统一信用代码：</div><div class="fwcontent-right-wb"><input value=${providerBasicInfoCompany.socialUnifiedCreditCode} type="text" style="color: #C5C5C5" readonly><div class="btxm">必填项目</div></div></li>
                    <li><div class="fwcontent-right-bt" style="line-height: 15px;"><span>*</span>上传营业执照扫描件（加盖公章）：</div>
                        <!--<div class="fwcontent-right-wb4"><input type="submit" value="点击上传"></div> 
                        <div class="fwcontent-right-tis">支持jpg/gif/png格式，RGB模式，单张（长<8000,宽<2000,大小<10M）,<br>最多上传100张图片，支持批量上传。不要在图片上放置商业推广图信息（案例信息可以放置）</div> -->
                        <div style=" width:100%; float:left;">
                            <ul class="fwcontent-right-yyjt">
                                <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><!-- <div class="fwcontent-right-yyjt02"></div> --></li>
                                <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><!--<div class="fwcontent-right-yyjt02"></div>--></li>
                                <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><!--<div class="fwcontent-right-yyjt02"></div>--></li>
                            	<input id="img1" type="hidden" value=${providerBasicInfoCompany.imgSrcList[0]}>
                            	<input id="img2" type="hidden" value=${providerBasicInfoCompany.imgSrcList[1]}>
                            	<input id="img3" type="hidden" value=${providerBasicInfoCompany.imgSrcList[2]}>
                            	<input id="infoId" type="hidden" value=${providerBasicInfoCompany.id}>
                            </ul>
                        </div></li>
                   <li><div class="fwcontent-right-bt" style="line-height: 15px;">店铺首页展示图片：</div>
                        <div class="fwcontent-right-wb4"><div class="file"><input id="file" type="file" accept=".jpg,.png" onchange="return fileUpload_onselect()">点击上传</div></div>
                        <div class="fwcontent-right-tis">支持jpg/png格式，单张（长=8000,宽=2000,大小<10M）,<br>本图片用于店铺首页展示，可体现公司整体形象</div>
                        <div style=" width:100%; float:left;">
                            <ul class="fwcontent-right-yyjt">
                                <li><div class="fwcontent-right-yyjt01"><img id="storeDisplayImg" src="../static/img/store_home_default_display.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                            	<input id="store_img_home_stc_" type="hidden" value=${providerBasicInfoCompany.storeDisplayImg}>
                            </ul>
                        </div></li>
                   <!--  <li><div class="fwcontent-right-bt" style="line-height: 15px;">上传开户许可证扫描件（加盖公章）：</div>
                    <div class="fwcontent-right-wb4"><input type="submit" value="点击上传"></div>
                    <div class="fwcontent-right-tis">支持jpg/gif/png格式，RGB模式，单张（长<8000,宽<2000,大小<10M）,<br>最多上传100张图片，支持批量上传。不要在图片上放置商业推广图信息（案例信息可以放置）</div>
                    <div style=" width:100%; float:left; display: none">
                        <ul class="fwcontent-right-yyjt">
                            <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                            <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                            <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qiye_yingye.png"></div><div class="fwcontent-right-yyjt02"></div></li>
                        </ul>
                    </div></li> -->
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
		
		//是否审核
		if($('#isApprove').val() == '0'){
			$('#status').text('（待审核）').css('color','red');
		}else if($('#isApprove').val() == '1'){
			$('#status').text('（已通过）').css('color','green');
		}//审核end
		
		var i = 1;
   		$(".fwcontent-right-yyjt li div img").each(function(index){
   			console.log($('#img' + i).val());
			 if ($('#img' + i).val() != '') {
				 $(this).attr("src", $('#img' + i++).val());
			 }
		 })
		 
		 if ($('#store_img_home_stc_').val() !=  '') {
			 $('#storeDisplayImg').attr('src', $('#store_img_home_stc_').val());
		 }
		 
		 
		 $('.fwcontent-right-yyjt02').on('click', function () {
			 $(this).prev().children().attr("src", "../static/img/store_home_default_display.png");
		 })
		//showLocation();
   		
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
					contactsName : "required",
					companyProfile : {
				            required:true,
				            minlength:8
					},
					companyEmail : "required"
				},
				messages : {
					verlocProvience : "省份不允许为空",
					verlocCity : "城市不允许为空",
					verlocTown : "城市不允许为空",
					addressOfCompany : "详细地址不允许为空",
					contactsName : "联系人姓名不允许为空",
					companyProfile : "公司简介不允许为空",
					companyEmail : "公司邮箱不允许为空"
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
		            jsonData.contactsName = $('#contactsName').val(),
		            jsonData.companyProfile = $('#company-profile').val(),
		            jsonData.companyEmail = $('#companyEmail').val(),
	            	jsonData.storeDisplayImg = $('#storeDisplayImg').attr('src');

					$.ajax({
			                type: "post",
			                url: "/providerBasicInfo/updateBasicInfoCompany",
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
		 
	});
	
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
		formData.append('file', $('#file')[0].files[0]);
		
		$.ajax({
		    url: '/providerBasicInfo/uploadFile',
		    type: 'POST',
		    cache: false,
		    data: formData,
		    processData: false,
		    contentType: false
		}).done(function(data) {
			console.log(data.success)
			if (data.success) {
				 $(".fwcontent-right-yyjt li div img").each(function(index){
					 console.log(this.src)
					 if (this.src.indexOf("static/img/store_home_default_display.png") > 0) {
						 console.log(data.data.imageSrc)
						 $(this).attr("src", data.data.imageSrc)
					 }
				 })
			}
		}).fail(function(res) {});
	}
</script>
</body>
</html>