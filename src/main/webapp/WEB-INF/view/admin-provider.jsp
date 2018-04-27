<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>运营中心</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link rel="stylesheet" href="../static/js/simplePagination/jquery.simplePagination.css"/>
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
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
    <style type="text/css">
        .right{
            position: relative;
            width: 925px;
            float: left;
            margin-left: 25px;
            margin-top: 20px;
            padding-top: 15px;
            min-height: 895px;
            margin-bottom: 100px;
        }
        .title,.lists{
            width: 100%;
            background: #FFFFFF;
        }
        .title input[type='text']{
            width: 290px;
            padding: 0 10px;
            overflow: hidden;
            height: 28px;
            border: 1px solid #a8a8a8;
            -moz-border-radius: 2px;
            -webkit-border-radius: 2px;
            border-radius: 2px;
        }
        .title input[type='button']{
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
        .title select{
            height: 28px;
            width: 100px;
            margin-right: 15px;
        }
        .top{
            background: #ffffff;
            width: 100%;
            height: 26px;
            line-height: 26px;
            border-bottom: 1px solid #bfbfbf;
        }
        .row div{
            float: left;
            padding: 10px 0px;
            border-bottom: 2px solid #bfbfbf;
        }
        .ser-describe div{
            text-align: left;
            font-family: '微软雅黑';
            font-size: 12px;
            margin-left: 20px;
            width: 170px;;
        }
    </style>
</head>
<body style="height: auto;width: 100%;margin: 0px">
<div class="header" style="width:100%; background: #0084C2;color: #ffffff">
    <div class="clearfix" style="width: 1200px;margin-left: auto;margin-right: auto">
        <a href="/home/index"><img style="float: left" src="../static/img/admin-logo.png"></a>
        <div style="padding: 32px;font-size: 20px;float: left">运营后台->服务商列表</div>
        <div class="clearfix" style="float: right;padding: 38px 0px;">
            <div id="userName" class="userName" style="float: left;padding-right: 20px;"></div>|
            <a id="quitOrLogin" style="color: #fff; padding-left: 20px; float: right;padding-left: 20px;">退出</a>
        </div>
    </div>
</div>
<div class="fwcontent">
    <div class="fwcore">

        <!-----------------------------content-left-------------------------------------->
        <div class="fwcontent-left">

            <div class="fwcontent-left-bt">后台运营系统</div>

            <div class="fwcontent-left-nav">
                <ul>
                    <li><a href="/adminOrder">所有订单</a></li>
                    <li><a class="dingw" href="/adminProvider">服务商列表</a></li>
                    <li><a href="/adminEmploy">雇佣方列表</a></li>
                    <li><a href="/adminWithDraw">提现列表</a></li>
                    <li><a href="/adminAddUser">创建运营账号</a></li>
                    <li><a href="/adminModifyPwd">修改密码</a></li>
                    <li><a href="/adminBasicInfo">个人信息</a></li>
                </ul>
            </div>
        </div>
        <!-----------------------------content-left-End-------------------------------------->

        <!-----------------------------content-right-------------------------------------->
        <div class="right">
            <div class="title" style="margin-bottom: 20px; text-align: center;font-size: 16px;padding: 40px 50px;">
                <span style="margin-left:50px;font-size: 16px;">选择要检索的类目（列）：</span>
                <select id="field-select">
                    <option value="">全部</option>
                    <option value=name>服务商名称</option>
                    <option value="phoneNumber">手机号</option>
                    <option value="type">类型</option>
                    <option value="location">所属地区</option>
                    <option value="isApprove">审核状态</option>
                </select>
                <input type="text" id="kw" placeholder="输入检索关键词" id="query"/>
                <select id="status-select" style="width: 120px; display:none">
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
                <input type="button" value="搜索" id="search-btn"/>
            </div>
            <div class="listcon" style="background: #FFFFFF;text-align: center;font-size: 16px;">
                <div class="listhead">
                    <div class="row clearfix">
                        <div style="width: 30%">服务商名称</div>
                        <div style="width: 20%">手机号</div>
                        <div style="width: 10%">类型</div>
                        <div style="width: 20%">服务商所属地区</div>
                        <div style="width: 10%">审核状态</div>
                        <div style="width: 10%">审核操作</div>
                    </div>
                </div>
                <div class="list"></div>
                <div class="pager" style="float: right;"></div>
            </div>
        </div>
        <!-----------------------------content-right-End-------------------------------------->

    </div>
</div>
<script type="text/javascript">
    var pg = 1, count = 30,query = '',row = '';
    $(function () {
    	$('#field-select').val('');
    	getLoginStatusToShow();
    	getSearchList();
    	
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
    function getParams() {
        row = $('#field-select').val();
        if($('#field-select').val() == 'isApprove'){
        	query =$("#status-select").val();
        }else{
        	query = $('#kw').val();
        }
    }
    
  //点击“检索”
    $("#search-btn").click(function () {
        pg = 1;
        getParams();
        getSearchList();
    });
    $("#kw").keydown(function (event) {
        if (event && event.keyCode == 13) {
            $("#search-btn").click();
        }
    });
    
  //搜‘状态’列时，显示下拉列表
    $('#field-select').change(function(){
    	console.log($(this).val());
    	if($(this).val() == 'isApprove'){
    		$("#status-select").show();
    		$('#kw').hide();
    		query = $("#status-select").val();
    	}else{
    		$("#status-select").hide();
    		$('#kw').show();
    		query = $('#kw').val();
    	}
    });
    
  //获取服务商列表
    function getSearchList() {
        $('div.loading').show();
        var data = { page : pg, row : row, queryKey : query};
        $.get('/adminProvider/getProviderDetail', data, function (d) {
            $('div.loading').hide();
            console.log(d);
            showSearchList(d);
            pageList(pg,d.data.totalNumber);
        }, 'json');
    }
    function pageList(page,total) {
        //var pg = page;
        var currentPage = page;
        var total = total;
        var itemsOnPage = 2;//页面显示几个可点击页码//TODO
        if(total>999){
        	total = '999+';
        }
        if(total <= itemsOnPage){
        	$('.pager').empty();
        	return;
        }
        
        var options = {
            items: total,
            itemsOnPage: itemsOnPage,
            currentPage: currentPage,
            displayedPages: 5,
            hrefTextPrefix:"?pg=",
            edges: 0,
            prevText: "上一页",
            nextText: "下一页",
            cssStyle:'light-theme',
            onPageClick: function (page, event) {
                if (event) {
                    event.preventDefault();
                    var href = decodeURI($(event.target).attr("href"));
                    pg = getParameter('pg',href);
                    getSearchList();
                }
            }
        };
        $('.pager').pagination(options);
    }
    
    function getParameter(sProp, search) {
        var re = new RegExp(sProp + "=([^\&]*)", "i");
        var a = re.exec(search);
        if (a == null)
            return null;
        return a [1];
    }
    
    function showSearchList(data) {
        var htmlBody = '';
        var provider = data.data.providerList;
        $('.list').empty();
        if(provider.length > 0){
            for(var i = 0;i < provider.length; i++){
            	var type = '';
            	var status = '';
            	switch (provider[i].providerType)
            	{
	            	case 1:
	            		type = "企业";
	            	  break;
	            	case 2:
	            		type = "个人";
	            	  break;
            	}
            	switch (provider[i].isApprove)
            	{
	            	case 1:
	            		 status= "是";
	            	  break;
	            	case 0:
	            		 status = "否";
	            	  break;
            	}
            	
                htmlBody +=' <div class="Servicer clearfix" style="border: 1px solid #bfbfbf; height: 36px;padding: 5px 0px;color: #3e6372">'+
                    '<div class="comName" style="float: left;width:30%"><a href="'+provider[i].shopUrl+'">'+provider[i].providerName+'</a></div>'+
                    '<div class="phoneNum" style="float: left;width: 20%">'+provider[i].phoneNumber+'</div>'+
                    '<div class="type" style="float: left; width: 10%">'+type+'</div>'+
                    '<div class="region" style="float: left;width: 20%">'+provider[i].location+'</div>'+
                    '<div class="status" style="float: left;width: 10%">'+status+'</div>'+
                    '<div class="operation" style="float: left;width: 10%">'+
	                    '<input type="hidden" class="providerIdentificationId" value="'+provider[i].providerIdentificationId+'">'+
	                	'<input type="hidden" class="userId" value="'+provider[i].userId+'">';
	                if(provider[i].isApprove == '0'){
	                	htmlBody += '<input style="width: 65px; margin: 0px 5px;" type="button" value="通过" class="pass"/>';
	                }else if(provider[i].isApprove == '1'){
	                	htmlBody += '<input style="width: 65px; margin: 0px 5px;" type="button" value="通过" class="pass" disabled/>';
	                }	
               htmlBody += '</div></div>';                     
          }
          $(htmlBody).appendTo($('.list'));
          $('.pass').click(function(){
          	var $th = $(this);
          	var providerIdentificationId = $('.providerIdentificationId',$(this).parent()).val();
          	var userId = $('.userId',$(this).parent()).val();
          	$.ajax({
                  type: "get",
                  url: '/adminProvider/setApprove?providerIdentificationId='+providerIdentificationId+'&userId='+userId,
                  data:'',
                  dataType: "json",
                  success: function(d){
                  	console.log(d);
                  	if(d.data.message){
                  		alert("通过审核成功！");
                  		$th.parent().parent().find('.status').text('是');
                  		$th.attr("disabled",true);
                  	}else{
                  		alert("通过审核失败！");
                  	}
                  }
              });
          });
       }
   }

  //DIV 多行显示文字 超出字符数 用省略号代替
  function csubstr(str, len) {
	  if(str){
		  if (str.length> len) {
	          return str.substring(0, len) + "...";
	      } else {
	          return str;
	      }
	  }
  }  
</script>
</body>
</html>