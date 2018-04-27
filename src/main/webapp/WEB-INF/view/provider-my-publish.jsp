<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的发布</title>
    <link type="text/css" rel="stylesheet" href="../static/css/base.css"/>
    <link type="text/css" rel="stylesheet" href="../static/css/my-publish.css"/>
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
    <script type="text/javascript" src="../static/js/simplePagination/jquery.simplePagination.js"></script>
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
                    <li><a class="dingw"  href="#">我的发布</a></li>
                    <li><a href="/providerPublish">发布服务</a></li>
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
                <div class="fwcontent-right-top01">我的发布</div>
                <div class="fwcontent-right-top02"></div>
            </div>

            <div class="fwcontent-right-bottom">
                 <ul class="my-publish"></ul> 
            </div>
            <div class="pager">
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
    var page = 1;

    $(function () {
        getPublish(page);
        getLoginStatusToShow();
    });
    
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

    function getPublish(page) {
        $.ajax({
            type: "GET",
            url: '/myPublish/getProviderMyPublishedDetail?page='+page,
            data: '',
            success: function(data){
                showPublish(data);
                pageList(page,data.data.totalNumber);
            }
        });
    }

    function showPublish(data) {
    	
        var publishArr = data.data.providerMyPublishList;
        if(publishArr.length > 0){
        	$('.my-publish li').remove();
            for(var i = 0;i < publishArr.length; i++){
                var htmlBody = '';
                htmlBody +='<li class="my-publish-item fl">'+
                                '<div class="img-container">'+
                                    '<div class="img-can">'+
                                        '<img src="'+publishArr[i].imageSrc+'">'+
                                    '</div>'+
                                    '<h5>'+publishArr[i].title+'</h5>'+
                                    '<p class="imgInfo">'+publishArr[i].oneIntroduction+'</p>'+
                                    '<p class="price-info"><span class="preferential-price">￥'+publishArr[i].groupPrice+'</span><del class="original-price">￥'+publishArr[i].originalPrice+'</del></p>'+
                                '</div>'+
                                '<div class="opera">'+
                                	'<input type="hidden" class="serviceId" value="'+publishArr[i].serviceId+'">'+
                                    '<span class="fr delete-btn">删除</span>'+
                                '</div>'+
                            '</li>';
                $(htmlBody).appendTo($('.my-publish'));
            }
            $('.delete-btn').click(function(){
            	var $th = $(this);
            	var serviceId = $('.serviceId',$(this).parent()).val();
            	$.ajax({
                    type: "get",
                    url: '/myPublish/deleteMyPublish?serviceId='+serviceId,
                    data:'',
                    dataType: "json",
                    success: function(d){
                    	console.log(d);
                    	if(d.data.msg == '删除成功'){
                    		$th.parent().parent().remove();
                    	}
                    	alert(d.data.msg);
                    }
                });
            });
        }else{
        	$('.my-publish li').remove();
        	$('.my-publish').text('您暂未发布任何内容！').css("text-align","center");
        }
    }
    function pageList(page,total) {
        var pg = page;
        var currentPage = pg;
        var total = total;
        var itemsOnPage = 2;//页面显示几个可点击页码//TODO
        if(total>999){
        	total = '999+';
        }
        if(total <= itemsOnPage)
            return;
        
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
                    var pg = getParameter('pg',href);
                    getPublish(pg);
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

</script>
</body>
</html>