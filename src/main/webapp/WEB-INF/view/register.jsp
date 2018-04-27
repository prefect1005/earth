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
    <script type="text/javascript" src="../static/js/jquery-1.11.3.min.js"></script>
    <!--<![endif]-->

    <!--[if !IE]><!-->
    <script type="text/javascript" src="../static/js/jquery-2.1.4.min.js"></script>
    <!--<![endif]-->

    <!--[if gte IE 9]><!-->
    <script type="text/javascript" src="../static/js/jquery-2.1.4.min.js"></script>
    <!--<![endif]-->
    <script type="text/javascript" src="../static/js/jquery-1.7.min.js"></script>
    <!-- <link rel="stylesheet" href="css/content.css"> -->
<style>
body{
    background: #fff;
}
.headerbg{
    border-bottom: 2px solid #00a0e9;
}
.header{
    background: url(/static/img/logo2.png) left center no-repeat;
    height: 50px;
    line-height: 50px;
    text-align: right;
    font-size: 12px;
    color: #666;
    position: relative;
}
.header .txt{
    position: absolute;
    top: 22px;
    left: 90px;
    padding: 0 10px;
    border-left: 1px solid #e5e5e5;
    height: 20px;
    line-height: 20px;
}
.header .btn{
    padding: 3px 8px;
    border: 1px solid #e5e5e5;
}
.header span{
    padding: 0 10px 0 0px;
    color: #666;
    margin: 0 0 0 10px;
    /*background: url(img/ico1.png) left center no-repeat;*/
    /*color: #fff;*/
}
.inp input{
    width: 280px;
}
.body{
    padding: 1px 0;
    margin: 60px 0 200px 140px;
}
.body .fr{
    float: right;
}
.body .form{
    
}
.body .form .tag{
    position: relative;
    height: 44px;
    line-height: 44px;
    width: 330px;
    border: 1px solid #e5e5e5;
    border-radius: 5px;
}
.body .form .tag .span{
    width: 50%;
    text-align: center;
    height: 44px;
    line-height: 44px;
    color: #010101;
    font-size: 18px;
    display: block;
    float: left;
    cursor: pointer;
}
.body .form .tag .span.act{
    background: #00a0e9;
    color: #fff;
}
.checkbox{
    margin: 20px 10px 0 0;
}
.body .form .inp {
    position: relative;
    margin: 26px 0 0;
}
.body .form  .inp_yzm {

}
.body .form  .line {
    position: relative;
}
.body .form .inp .yzm {
    width: 158px;
    padding: 0 10px 0 10px;
}
.body .form .line .btn2 {
    width: 140px;
    height: 46px;
    line-height: 46px;
    text-align: center;
    color: #fff;
    background: #00a0e9;
    position: absolute;
    top: 0px;
    left: 190px;
    border-radius: 5px; 
    font-size:16px
}
.body .form .btn {
    margin: 15px 30px 15px 0;
    position: relative;
    color: #fff;
    font-size: 20px;
    text-align: center;
    background: #00a0e9;
    border-radius: 3px;
    height: 50px;
    width: 330px;
    line-height: 50px;
}
.body .form .txt {
    margin: 15px 30px 0 0;
    position: relative;
    color: #343434;
    font-size: 14px;
}

.inp input.pwd{

    background: url(/static/img/ico6.png) 10px center no-repeat #fff;
}
.tag .span span{
    padding: 0 0 0 30px;
    background: url(/static/img/ico7.png) 5px center no-repeat ;
}

.tag .span.act span{

    background: url(/static/img/ico8.png) 5px center no-repeat ;
}
.inp input.mobile{

    background: url(/static/img/ico5.png) 10px center no-repeat #fff;
}
.inp .info{
    position: absolute;
    top: 0;
    left: 340px;
    color: #ccc;
    line-height: 24px;
    font-size: 14px;
}
</style>
    <script type="text/javascript" src="http://auto.sina.com.cn/js/jq172.js"></script>
</head>
<body>      

    <div class="outer headerbg">                     
        <div class="main">
            <div class="header">
            <div style="float:left; width:8%; height:100%" id="homeUrl"></div>
                <div class="txt">地心引力注册</div>
                <a href="javascript:void(0)"><span class="n">我已注册，现在就</span></a>
                <a href="/loginPage"><span class="btn">登录</span></a>
            </div>
        </div>
    </div>
    <div class="outer">
    <form id="content-form">
        <div class="main">
            <div class="body clearfix">
                <div class="fr">
                    <img src="/static/img/banner.png">
                </div>
                <div class="form">
                    <div class="tag">
                        <div class="span qy" data-i="1"><span>企业注册</span></div>
                        <div class="span gr act" data-i="2"><span>个人注册</span></div>
                    </div>
                    <div class="inp">
                        <input id="mobile" name="mobile" type="text" placeholder="请输入手机号" class="mobile">
                        <div class="info" id="mobileInfo">
                            可用于密码找回，其他用户不可见
                        </div>
                    </div>
                    <div class="line">
                        <div class="inp inp_yzm">
                            <input name="yzm" type="text" placeholder="验证码" class="yzm">
                        </div>
                        <input type="button" id="btnSendCode" class="btn2" value="获取手机验证码"/>
                    </div>
                    <div class="inp">
                        <input name="pwd" type="password" placeholder="请输入登录密码（6-12位数字字母组合）" class="pwd">
                        <div class="info">
                            可用于密码找回 <br>   
                            不支持有空格键的密码
                        </div>
                    </div>
                    <div class="inp">
                        <input name="pwd2" type="password" placeholder="再次确认密码" class="pwd">
                    </div>
                    <label>
                        <input type="checkbox" class="checkbox">我已阅读并同意<a href="#">《地心引力用户协议》</a>
                    </label>
                    <!-- <a href="#" class="wjmm">我已阅读并同意<span>《地心引力用户协议》</span></a> -->
                    <div class="btn">注册</div>
                    <div class="txt">
                        已有账户？ <a href="/loginPage">返回登录</a>
                    </div>
                </div>
            </div>
        </div>
        </form>
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
    var InterValObj; //timer变量，控制时间
    var count = 60; //间隔函数，1秒执行
    var curCount;//当前剩余秒数
    $(function () {
        $('.form .tag').delegate('.span', 'click', function () {
            $('.tag .span.act').removeClass('act');
            $(this).addClass('act');
        });
    })
    
    $('#btnSendCode').on('click', function () {
    	var that = this;
        var phone = $("#mobile").val().trim();
        curCount = count;
        //设置button效果，开始计时
        $("#btnSendCode").attr("disabled", "true");
        $("#btnSendCode").css('background','#b6b6b6')
        $("#btnSendCode").val("请在" + curCount + "秒内输入");
        InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
        
        //向后台发送处理数据
        $.ajax({
            type: "get",
            url: "/getVCode",
            data: {phone: phone},
            dataType: "json",
            success: function(data){
                //if (data.success) {
               // 	console.log("发送成功");
              //     $(that).html('重新发送');
              // }
            }
        });
    });
    
    $('#homeUrl').on('click', function () {
    	location.href = "/home/index";
    });
    
    //timer处理函数
    function SetRemainTime() {
        if (curCount == 0) {
            window.clearInterval(InterValObj);//停止计时器
            $("#btnSendCode").removeAttr("disabled");//启用按钮
            $("#btnSendCode").val("重新发送验证码");
            $("#btnSendCode").css('background','#00a0e9')
        }
        else {
            curCount--;
            $("#btnSendCode").val("请在" + curCount + "秒内输入");
        }
    }

    //提交
    $('.btn').on('click', function () {
        var that = this;
        var jsonData = {};
        jsonData.phone = $('.mobile').val(),
        jsonData.verCode = $('.yzm').val(),
        jsonData.password = $('.pwd').val(),
        jsonData.typeCp = $('.tag .act').attr('data-i');
        
        $.ajax({
            type: "post",
            url: "/register",
            contentType : "application/json;utf-8",
            //data: {phone: phone, verCode: verCode, password:password, typeCp:typeCp},
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function(data){
                if (data.success) {
                	console.log("注册成功");
                    location.href = data.data.location;
                }

            }
        });
    });
    
    
    $("#mobile").on("blur",function(){
    	var val = $("#mobile").val().trim();
            /*校验手机号是否注册*/
            $.ajax({
                type:"get",
                url:"/verifyIsRegister",
                data:{mobile:val},
                success:function(data){
                    if(data.data.isRegister){
                        $("#mobileInfo").html("此账号已被注册");
                        $("#mobileInfo").css("color","red");
                    }else{
                    	$("#mobileInfo").html("可用于密码找回，其他用户不可见")
                    	$("#mobileInfo").css("color","");
                    }
                }
            })
    })
    </script>
</body>
</html>