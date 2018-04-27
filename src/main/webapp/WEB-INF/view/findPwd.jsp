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
    /*margin: 60px 0 200px 140px;*/
}
.body .fr{
    float: right;
}
.body .step{
    margin: 95px 0 0 160px;
}
.body .step .s{
    width: 120px;
    height: 40px;
    line-height: 40px;
    text-align: center;
    float: left;
    color: #fff;
    background: #dddddd;
    border-radius: 4px;
}
.body .step .ico{
    width: 40px;
    height: 40px;
    line-height: 40px;
    float: left;
    background: url(/static/img/ico9.png) center center no-repeat;
}
.body .step .s.act{
    background: #00a0e9;
}
.body .st{
    margin: 75px 0 0 290px;
}
.body .st .tag{
    position: relative;
    height: 44px;
    line-height: 44px;
    width: 330px;
    border: 1px solid #e5e5e5;
    border-radius: 5px;
}
.body .st .tag .span{
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
.body .st .tag .span.act{
    background: #00a0e9;
    color: #fff;
}
.checkbox{
    margin: 20px 10px 0 0;
}
.body .st .inp {
    position: relative;
    margin: 26px 0 0;
}
.body .st  .inp_yzm {

}
.body .st  .line {
    position: relative;
}
.body .st .inp .yzm {
    width: 158px;
    padding: 0 10px 0 10px;
}
.body .st .line .btn2 {
	cursor: pointer;
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
}
.body .st .btn {
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
.body .st .txt {
    margin: 15px 30px 0 0;
    position: relative;
    color: #343434;
    font-size: 14px;
}

.inp input.mobile{

    background: url(/static/img/ico5.png) 10px center no-repeat #fff;
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
.hide{
    display: none;
}
.body .st4 .txt{
    text-align: center;
    width: 330px;
    margin: 0 0 50px;
}
.st4 .txt span{
    padding: 20px 0 20px 50px;
    background: url(/static/img/ico10.png) 5px center no-repeat ;
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
                <a href="/registerPage">注册</a> | 
                <a href="/loginPage"><span class="z">登录</span></a>
            </div>
        </div>
    </div>
    <div class="outer">
        <div class="main">
            <div class="body clearfix">
                <div class="step clearfix">
                    <div class="s1 s act">输入账户名</div>
                    <div class="ico"></div>
                    <div class="s2 s">验证账户名</div>
                    <div class="ico"></div>
                    <div class="s3 s">重置密码</div>
                    <div class="ico"></div>
                    <div class="s4 s">重置成功</div>
                </div>
                <div class="st1 st ">
                    <div class="inp">
                        <input name="mobile" type="text" placeholder="请输入手机号" class="mobile">
                    </div>
                    <div class="line">
                        <div class="inp inp_yzm">
                            <input name="yzm" type="text" placeholder="验证码" class="yzm" id="page1yzm">
                        </div>
                        <div class="btn2">
                            <img id="refresh_verify_img_register" src="/authImage/refresh">
                        </div>
                    </div>
                    <div class="inp inp_yzm" id="error1" style="color:red"></div>
                    <div class="btn b1">下一步</div>
                </div>
                <div class="st2 st hide">
                    <div class="txt">地心引力将向您<span id="page2showtxt">153****1230</span>的手机发送验证码，点击发送</div>
                    <div class="line">
                        <div class="inp inp_yzm">
                            <input name="yzm" type="text" placeholder="验证码" class="yzm" id="page2yzm">
                        </div>
                        <div class="btn2" id="page2_get_v_code">
                            获取手机验证码
                        </div>
                    </div>
                    <div class="inp inp_yzm" id="error2" style="color:red"></div>
                    <div class="btn b2">下一步</div>
                </div>
                <div class="st3 st hide">
                    <div class="inp">
                        <input name="pwd" type="password" placeholder="请输入登录密码（6-12位数字字母组合）" class="pwd" id="resetpwd">
                    </div>
                    <div class="inp">
                        <input name="pwd2" type="password" placeholder="再次确认密码" class="pwd" id="resetpwd2">
                    </div>
                    <div class="btn b3">下一步</div>
                </div>
                <div class="st4 st hide">
                    <div class="txt"><span>您的密码已重置成功</span></div>
                    <div class="btn b4">返回登录</div>
                </div>
                <!-- <div class="form">
                    <div class="tag">
                        <div class="span qy"><span>企业注册</span></div>
                        <div class="span gr act"><span>个人注册</span></div>
                    </div>
                    <div class="inp">
                        <input name="mobile" type="text" placeholder="请输入手机号" class="mobile">
                    </div>
                    <div class="line">
                        <div class="inp inp_yzm">
                            <input name="yzm" type="text" placeholder="验证码" class="yzm">
                        </div>
                        <div class="btn2">获取手机验证码</div>
                    </div>
                    <div class="inp">
                        <input name="pwd" type="password" placeholder="请输入登录密码（6-12位数字字母组合）" class="pwd">
                    </div>
                    <div class="inp">
                        <input name="pwd2" type="password" placeholder="再次确认密码" class="pwd">
                    </div>
                    <label>
                        <input type="checkbox" class="checkbox">我已阅读并同意<a href="#">《地心引力用户协议》</a>
                    </label>
                    <div class="btn">登录</div>
                    <div class="txt">
                        还没有账号？ <a href="#">免费注册</a>
                    </div>
                </div> -->
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
            $('.b1').on('click', function () {
            	var that = this;
            	var yzm = $('#page1yzm').val(),
            	moblie = $('.mobile').val();

		        $.ajax({
		            type:"GET",
		            url:"/verifyYzm",
		            dataType:"json",
		            contentType:"application/json",
		            data:{yzm : yzm},
		            success:function(data){
						 if (data.success) {
						 	$('.step .act').removeClass('act');
			                $('.s2').addClass('act');
			                $('.st1').addClass('hide');
			                $('.st2').removeClass('hide');
			                $('#page2showtxt').html(moblie);
			             }else{
		                    	$('#error1').text('验证码不正确！');
		                 } 
		            }
		         }); 
               
            });
            
            $('#page2_get_v_code').on('click', function () {
            	var that = this;
            	var yzm = $('#page1yzm').val(),
            	mobile = $('.mobile').val();
            	
            	$.ajax({
                    type:"GET",
                    url:"/getVCodeByYzm",
                    dataType:"json",
                    contentType:"application/json",
                    data:{mobile : mobile, yzm : yzm},
                    success:function(data){
        			 if (data.success) {
        				 $(that).html('重新发送');
        	         } else{
	                    	$('#error2').text(data.data.message);
	                 }
                    }
                 }); 
            });
            
            $('.b2').on('click', function () {
            	var that = this;
            	var yzm = $('#page2yzm').val(),
            	mobile = $('.mobile').val();
            	
            	$.ajax({
                    type:"GET",
                    url:"/verifyPhoneVCode",
                    dataType:"json",
                    contentType:"application/json",
                    data:{mobile : mobile, verifyCode : yzm},
                    success:function(data){
        			 if (data.success) {
        				 $('.step .act').removeClass('act');
        	                $('.s3').addClass('act');
        	                $('.st2').addClass('hide');
        	                $('.st3').removeClass('hide');
        	         }else{
	                    	$('#error2').text(data.data.message);
	                 }  
                    }
                 }); 
            });
            $('.b3').on('click', function () {
            	var yzm = $('#page2yzm').val(),
            	mobile = $('.mobile').val(),
            	pwd = $('#resetpwd').val(),
            	pwd2 = $('#resetpwd2').val();
            	
            	if (pwd == pwd2) {
            		$.ajax({
                        type:"GET",
                        url:"/resertPwd",
                        dataType:"json",
                        contentType:"application/json",
                        data:{mobile : mobile, verifyCode : yzm, pwd : pwd},
                        success:function(data){
            			 if (data.success) {
            				 $('.step .act').removeClass('act');
            	                $('.s4').addClass('act');
            	                $('.st3').addClass('hide');
            	                $('.st4').removeClass('hide');
            	         } 
                        }
                     });
            	} else {
            		console.log("两次密码不一致");
            	}
            });
            $('.b4').on('click', function () {
            	location.href = "/loginPage";
            });
            
            $("#refresh_verify_img_register").click(function() {
				$("#refresh_verify_img_register").attr(
								"src",
								"/authImage/refresh?"+ new Date().getTime());
			});
        })
        
        $('#homeUrl').on('click', function () {
    		location.href = "/home/index";
    	});
    </script>
</body>
</html>