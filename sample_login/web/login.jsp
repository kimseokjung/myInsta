<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/insta/style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>

<body class="login_body">

<!--12월 17일 수정 -->
		<%
			String errMsg = (String)session.getAttribute("errMsg");
			if(errMsg==null){
				errMsg="";
			}
			session.removeAttribute("errMsg");
		%>
		
		<form action="/insta/login.do" method="post" onsubmit="return blankCheck();"  class="login_form" autocomplete='off' >
					<div class="login_form_title">ezensta</div>
					<div class="login_err_msg"><%=errMsg %></div>
					<input type="text" placeholder="ID" name="input_id" id="input_id_id"  autocomplete='off' class="login_input">
					<input type="password" placeholder="PASSWORD" name="input_pwd"  id="input_id_pwd" autocomplete=off class="login_input">
					<input type="submit" value="login" class="login_submit_btn">
					<input type="button" class="login_signIn_btn" onclick="location.href='/insta/enterJoin.do'" value="회원가입">
		</form>
		
		
		<script>
		/*공백 허가X 처리  */
		function blankCheck(){
			var id=$('#input_id_id').val();
			var pwd=$('#input_pwd_id').val();

			if(id==''||pwd==''){
				console.log(id);
				console.log(pwd);
				alert("공백을 입력하지마세요.");
				return false;
			}
			else{
				return true;
			}			
			
		} 
		</script>
		
	</body>
</html>