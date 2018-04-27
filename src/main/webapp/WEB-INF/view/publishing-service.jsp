<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>发布服务</title>
<link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
<link type="text/css" rel="stylesheet" href="../static/css/publish-service.css"/>
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
        width: 147px;
        margin-right: 15px;
    }
    input.error { border: 1px solid red; }
</style>

<script type="text/javascript">
$(function(){
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("a").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
})
</script>

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
            <li><a class="dingw" href="#">发布服务</a></li>
            <li><a href="/providerMyAccount">我的账号</a></li>
            <li><a href="/providerModifyPwd">修改密码</a></li>
            <li><a href="${basicInfoUrl}">基本信息设置</a></li>
         </ul>
      </div>
      
   </div>
   <!-----------------------------content-left-End-------------------------------------->
   
   <!-----------------------------content-right-------------------------------------->
   <form id="content-form">
   <div class="fwcontent-right">
   
      <div class="fwcontent-right-top">
         <div class="fwcontent-right-top01">服务基本资料</div>
         <div class="fwcontent-right-top02">与地心引力合作，请填写如下信息，如有疑问可联系商服电话400-028-00010</div>
         <input type="hidden" value="${isApprove}" id="isApprove">
      </div>
      
      <div class="fwcontent-right-bottom">
      
         <ul>
            <li><div class="fwcontent-right-bt"><span>*</span>服务标题：</div><div class="fwcontent-right-wb"><input type="text" id="service-title" name="serviceTitle"><div class="btxm">必填项目</div></div><div class="fwcontent-right-tixi">请填写您的服务标题名称，如果有多种服务的可以写上服务名称</div></li>
            <li><div class="fwcontent-right-bt"><span>*</span>所属级别：</div>
	            <select id="firstCategory">
		            <c:forEach items="${firstCategoryList}" var="firstCategory">
		                 <option value="${firstCategory.key}">${firstCategory.value}</option>
		            </c:forEach>
	            </select>
                <select id="secondCategory">
                </select>
            <li><div class="fwcontent-right-bt"><span>*</span>关键词：</div><div class="fwcontent-right-wb"><input id="service-keyword" type="text" name="serviceKeyword"><div class="btxm">必填项目</div></div></li>
            <li><div class="fwcontent-right-bt"><span>*</span>一句话简介：</div><div class="fwcontent-right-wb"><input id="service-oneintroduction" type="text" name="serviceOneintroduction"><div class="btxm">必填项目</div></div></li>
            <li><div class="fwcontent-right-bt"><span>*</span>服务介绍：</div><div class="fwcontent-right-wb2"><textarea id="service-introduction-detail" placeholder="专业的服务团队" name="serviceIntroductionDetail"></textarea></div></li>
            <li><div class="fwcontent-right-bt"><span>*</span>服务价格：</div><div class="fwcontent-right-wb3"><div class="fwyj"><span>服务原价：</span><input id="original-price" type="text" name="originalPrice"></div><div class="tgj"><span>团购价：</span><input id="group-price" type="text" name="groupPrice"></div></div></li>
            <li>
            	<div class="fwcontent-right-bt"><span>*</span>应用截图：</div>
            	<div class="fwcontent-right-wb4">
	            	<div class="file">
	            		<input id="file" type="file" multiple accept=".jpg,.gif,.png" onchange="return fileUpload_onselect()">点击上传
	            	</div>
            	</div>
            	<div class="fwcontent-right-tis">支持jpg/gif/png格式，RGB模式，单张（长<8000,宽<2000,大小<10M）,<br>最多上传6张图片，支持批量上传。不要在图片上放置商业推广图信息（案例信息可以放置）</div>
            <div style=" width:100%; float:left;">
               <ul class="fwcontent-right-yyjt">
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
                  <li><div class="fwcontent-right-yyjt01"><img src="../static/img/qwe.jpeg"></div><div class="fwcontent-right-yyjt02"></div></li>
               </ul>
            </div></li>
            <li><div class="fwcontent-right-fb"><input type="submit" value="发布" id="submitAll"></div></li>
            
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
	
	var cate = $("#firstCategory").val();
    $.ajax({
        type: "get",
        url: "/providerPublish/getSecondCategory",
        data: {cate : cate},
        dataType: "json",
        success: function(data){
            var option = '';
            $.each(data.data.data,function (k,v) {
                option += '<option value="'+k+'">'+v+'</option>';
            });
            $("#secondCategory").html(option);
        }
    });
    
    
    $("#submitAll").click(function() {
    	var isApprove = $("#isApprove").val();
    	if (isApprove == 0) {
    		alert("请耐心等待认证通过后发布");
    	}
    });
    
    $("#content-form").validate({
		ignore: '*:not([name])',
		onsubmit : true,// 是否在提交是验证
		onfocusout : false,// 是否在获取焦点时验证
		onkeyup : false,// 是否在敲击键盘时验证
		debug : true,
		rules : {
			serviceTitle : "required",
			serviceKeyword : "required",
			serviceOneintroduction : "required",
			serviceIntroductionDetail : "required",
			originalPrice : "required",
			groupPrice : "required"
		},
		messages : {
			serviceTitle : "不允许为空",
			serviceKeyword : "不允许为空",
			serviceOneintroduction : "不允许为空",
			serviceIntroductionDetail : "不允许为空",
			originalPrice : "不允许为空",
			groupPrice : "不允许为空"
		},
		errorPlacement : function(error, element) {
			error.appendTo(element.parent());
		
		},
        submitHandler:function(form){ //通过之后回调
        	var that = this;
            var jsonData = {};
            jsonData.title = $('#service-title').val(),
            jsonData.firstlevel = $("#firstCategory").val(),
            jsonData.secondlevel = $("#secondCategory").val(),
            jsonData.keyword = $('#service-keyword').val(),
            jsonData.oneintroduction = $("#service-oneintroduction").val(),
            jsonData.introductiondetail = $("#service-introduction-detail").val(),
            jsonData.oprice = $('#original-price').val(),
            jsonData.gprice = $("#group-price").val();
            var imgSrcList = new Array();
            var index = 0;
           		$(".fwcontent-right-yyjt li div img").each(function(index){
    				 if (this.src.indexOf("static/img/qwe.jpeg") <= 0) {
    					 imgSrcList[index++] = this.src;
    				 }
    			 })
           	jsonData.imgSrcList = imgSrcList;
           		
           	var pngCount = 0;
           	$(".fwcontent-right-yyjt li div img").each(function(index){
   				 if (this.src.indexOf("static/img/qwe.jpeg") <= 0) {
   					pngCount++;
   				 }
   			})
           	
           	if (pngCount < 1) {
           		alert("请上传不少于一张图片");
           		return;
           	}
           	
            $.ajax({
                type: "post",
                url: "/providerPublish/publishServiceDetail",
                data: JSON.stringify(jsonData),
                dataType: "json",
                contentType : "application/json;utf-8",
                success: function(data){
                	console.log(data.success)
                    if (data.success) {
                    	console.log("in")
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

function fileUpload_onselect() {
	var formData = new FormData();
	for (var i = 0; i < 6; i++) {
		formData.append('file', $('#file')[0].files[i]);
	}
	
	$.ajax({
	    url: '/providerPublish/uploadFile',
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
					 if (this.src.indexOf("static/img/qwe.jpeg") > 0) {
						 console.log("index:" + i)
						 console.log(data.data.imageSrc[i])
						 $(this).attr("src", data.data.imageSrc[i++])
					 }
				 })
		}
	}).fail(function(res) {});
}


$("#firstCategory").change(function () {
    $this = $(this);
    var cate = $this.val();
    $.ajax({
        type: "get",
        url: "/providerPublish/getSecondCategory",
        data: {cate : cate},
        dataType: "json",
        success: function(data){
            var option = '';
            $.each(data.data.data,function (k,v) {
                option += '<option value="'+k+'">'+v+'</option>';
            });
            $("#secondCategory").html(option);
        }
    });

});

    </script>
</body>
</html>
