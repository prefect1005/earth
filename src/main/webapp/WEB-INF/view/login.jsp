<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title></title>  
    <link rel="stylesheet" href="/static/css/reset-shao.css">
    <!-- <link rel="stylesheet" href="css/content.css"> -->
<style>

.header{
    background: url(/static/img/logo.png) left center no-repeat;
    height: 100px;
    line-height: 100px;
    text-align: right;
    font-size: 16px;
    color: #fff;
}
.header span.z{
    padding: 0 10px 0 30px;
    background: url(/static/img/ico1.png) left center no-repeat;
    color: #fff;
}
.header span.d{
    margin: 0 0 0 10px;
    padding: 0 10px 0 30px;
    background: url(/static/img/ico2.png) left center no-repeat;
    color: #fff;
}
.body{
    position: relative;
}
.body .banner{
    margin: 45px 0 0;
}
.body .banner .txt{
    margin: 20px 0 0;
    font-size: 14px;
    color: #393939;
    width: 571px;
    line-height: 22px;
}
.body .right{
    margin: -15px 0 0;
    float: right;
    width: 361px;
    height: 370px;
    padding: 30px 0 0;
    border: 1px solid #e5e5e5;
    background: #f6f6f6;
    border-radius: 5px;

    font-size: 14px;
    /*padding: 30px 30px 30px 30px;*/
}
.body .right .inp{
    margin: 23px 30px ;
    position: relative;
}
.body .right .btn{
    margin: 29px 30px ;
    position: relative;
    color: #fff;
    font-size: 20px;
    text-align: center;
    background: #00a0e9;
    border-radius: 3px;
    height: 50px;
    line-height: 50px;
}
.body .right .txt{
    margin: 38px  30px ;
    position: relative;
    color: #343434;
    font-size: 14px;
}
.wjmm{
    float: right;
    margin: 0px 30px ;
    color: #393939;
    border: 1px solid #393939;
    padding: 3px 8px;
    font-size: 14px;
}
</style>
    <script type="text/javascript" src="http://auto.sina.com.cn/js/jq172.js"></script>
</head>
<body>
    <div class="outer bgblue">
        <div class="main">
            <div class="header">
            	<div style="float:left; width:12%; height:100%" id="homeUrl"></div>
                <a href="/registerPage"><span class="z">注册</span></a> | 
                <a href=""><span class="d">登录</span></a>
            </div>
        </div>
    </div>
    <div class="outer">
        <div class="main">
            <div class="body clearfix">
                
                <div class="right">
                    <div class="inp">
                        <input name="name" type="text" placeholder="请输入手机号" class="name" value = ${account}>
                    </div>
                    <div class="inp">
                        <input name="pwd" type="password" placeholder="请输入密码" class="pwd" value = ${password}>
                    </div>
                    <div class="inp" id="error" style="color:red"></div>
                    <label>
                        <input type="checkbox" class="checkbox">记住密码
                    </label>
                    <a href="/findPwdPage" class="wjmm">忘记密码?</a>
                    <div class="btn">登录</div>
                    <div class="txt">
                        还没有账号？ <a href="/registerPage">免费注册</a>
                    </div>
                </div>
                <div class="banner">
                    <img src="/static/img/banner.jpg">
                    <div class="txt">企业服务平台，私人定制，微信开发，平台推广，企业服务平台，私人定制，微信开发，平台推广。企业服务平台，私人定制，微信开发，平台推广企业服务平台，私人定制，微信开发，平台推广。</div>
                </div>
            </div>
        </div>
    </div>
    <div class="outer bd_footer">
        <div class="main">
            <div class="footer">
                <div class="txt">©2016 nuomi.com  京ICP证030173号  京公网安备11010802014106号  营业执照信息</div>
                <div class="img">
                    <img src="/static/img/footer.png">
                </div>
            </div>
        </div>
    </div>
    <script>
    $(function () {

        //提交
        $('.btn').on('click', function () {
            var that = this;
            var name = $('.name').val(),
           		pwd = $('.pwd').val(),
           		rememberPwd = $('.checkbox').is(':checked');
            $.ajax({
                type: "post",
                // url: "http://localhost:8080/register?phone="+phone+"&vCode="+vC+"&password="+password+"&typeCp="+typeCp,
                url: "/login",
                data: {account : name, password : pwd, rememberPwd: rememberPwd},
                dataType: "json",
                success: function(data){
                	console.log(data.success)
                	console.log(data.data.location)
                    if (data.success) {
                    	console.log("in")
                        //window.location.href = data.data.location;
                    	location.href = data.data.location;
                    }else{
                    	$('#error').text(data.data.error_msg);
                    }
                }
            });
        });
    })
    
    $('#homeUrl').on('click', function () {
    		location.href = "/home/index";
    });
    </script>
</body>
</html>