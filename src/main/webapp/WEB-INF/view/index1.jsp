<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
	<h2>Hello World!</h2>
	<form id="enterprise_login" method="get" action="/earth/login">
		<input type="text" name="account" id="log_username" tabindex="1"
			value="请输入用户名"
			onfocus="if(this.value == '请输入用户名'){this.value = ''; $(this).css('color','#333');}"
			onblur="if(this.value ==''){this.value = '请输入用户名'; $(this).css('color','#b0b0b0');}">
		<input type="text" name="password" id="log_password" tabindex="2"
			value="请输入密码"
			onfocus="if(this.value == '请输入密码'){this.value = ''; $(this).attr('type','password');$(this).css('color','#333');}"
			onblur="if(this.value ==''){this.value = '请输入密码'; $(this).attr('type','text');$(this).css('color','#b0b0b0');}">
		<button type="submit" tabindex="6">立即登录</button>
	</form>
	<script type="text/javascript">
	$(document).ready(function(){  
        var saveDataAry=[];
        var data1={"phone":"13800000000","vCode":"gz"};
        saveDataAry.push(data1);
        $.ajax({
            type:"POST",
          //  url:"/earth/register",
            dataType:"json",
            contentType:"application/json",
            data:JSON.stringify(saveData),
            success:function(data){
            } 
         }); 
    });  
	</script>
</body>
</html>