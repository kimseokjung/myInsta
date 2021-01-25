<%@page import="com.login.entity.login_entity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../style.css" rel="stylesheet" type="text/css">
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

<script language="javascript">
	function goPopup() {
		var pop = window.open("/insta/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");
	}

	function jusoCallBack(roadFullAddr) {

		document.form_name.input_address.value = roadFullAddr; /* input의 name값을통해 찾는다. */
	}
</script>
</head>

<body class="join_body">

	<%
		ArrayList<login_entity> UserList = (ArrayList<login_entity>)session.getAttribute("UserList");
	%>
	<form action="/insta/join.do" method="get" id="form_id" class="join_form"
		onsubmit='return emptyCheck();' name="form_name"  autocomplete='off'>
		
		
			<div class="join_form_title">sign up</div>
			<div class="input_frame">	
				<input type="text" placeholder="ID" name="input_id"
					id="input_id" autocomplete='off' class="join_input join_input_id">
				<span 
					id="check_id_span"></span> 
			
				<input type="password" placeholder="PASSWORD" name="input_pwd"
					autocomplete='off' class="input_pwd join_input" id="input_pwd">
					
				<input type="password" placeholder="PASSWORD CHECK" autocomplete='off'
					class="input_pwd join_input" id="input_pwd_re" >

				<span 
					id="check_pwd_span"></span>
					
				<input type="text" placeholder="NAME" name="input_name"
					autocomplete='off' id="input_name" class="join_input">
				<input type="text" placeholder="EMAIL" name="input_email"
					autocomplete='off' id="input_email" class="join_input">
				<input type="text" placeholder="ADDRESS" name="input_address"
					id="input_address_id" class="join_input"
					autocomplete='off'>
				<input type="button" value="주소검색" id="input_address_btn_id"
					onclick="goPopup();"class="join_address_btn">
					
			<div class="check_div">
				<span id="span_user">유저계정</span>
				<input type="radio" name="input_chk" value="user" checked class="check_button">
				<span id="span_seller">판매자 계정</span>
				<input type="radio" name="input_chk" value="seller" class="check_button">
			</div>
			
				<input type="submit" value="가입" class="join_submit_btn"/>
				<input type="button" value="뒤로가기" onclick="history.back()" class="join_submit_btn">
				</div>
	</form>


	<script>
	
	
		function emptyCheck(){
			
			var id=$('#input_id').val();
			var pwd=$('#input_pwd').val();
			var pwd_re=$('#input_pwd_re').val();
			var name=$('#input_name').val();
			var email=$('#input_email').val();
			var address=$('#input_address').val();
			
			if(id==''||pwd==''||pwd_re==''||name==''||email==''||address==''){
				alert('공백을 입력하지마세요');
				return false;
			}
			else{
				<%for(int i = 0 ;i<UserList.size();i++){%>
					
					if('<%=UserList.get(i).getUserid()%>'== id){
						alert("중복된 아이디입니다.");
				  		return false;
					}
					
				<%}%>
					
					var input_pw=$('#input_pwd').val();
				  	var input_pw_re=$('#input_pwd_re').val();
				  	
				  	  	if(input_pw==input_pw_re){
				  	  		alert("회원가입 되었습니다.")
							return true;
				  	  	}
					  	else{
					  		alert("비밀번호 일치여부를 확인해주세요.")
							return false;
					  	}
				
			}
			
		}
		
		
		
		$('#input_id').focusout(function (){
			//실시간체크
			var input_id=$('#input_id').val();
			
			if(input_id==''){
				$('#check_id_span').css('color','blue');
				$('#check_id_span').css('opacity','1');
		  		$('#check_id_span').html("아이디를 입력해주세요");
				
			}
			
			$.ajax({
                url:"joinCheckAjax",                   // url : 요청할 url(필수속성!!)
                data:{input:$('#input_id').val()},    // data : 요청시 전달할 데이터(key:value 값들의 객체형태)
                type:"get",

                success:function(result){                         //success : ajax통신 성공시 실행할 함수 지정
                    console.log("ajax통신 성공!");
	                if(result == 0){
	                    $('#check_id_span').css('color','green');
						$('#check_id_span').css('opacity','1');
				  		$('#check_id_span').html("아이디 사용가능");
	                }else if(result == 1) {
						$('#check_id_span').css('color','red');
						$('#check_id_span').css('opacity','1');
				  		$('#check_id_span').html("아이디 중복");
	                }
                },
                error:function(){                            // error : ajax 통신 실패시 실행할 함수 지정 
                    console.log("ajax통신 실패!");
                }
            });
			
// 			for (var i = 0; i < list.length; i++) {
// 				if(input_id==''){
// 					$('#check_id_span').css('color','blue');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디를 입력해주세요");
					
// 				}
				
// 				else if(list[i].userid == input_id){
// 					$('#check_id_span').css('color','red');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디 중복");
// 			  		return false;
// 				}
// 				else{
// 					console.log(list[i].userid)
// 					$('#check_id_span').css('color','green');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디 사용가능");
// 				}
// 			}
<%-- 			<%for(int i = 0 ;i<UserList.size();i++){%> --%>
			
// 				if(input_id==''){
// 					$('#check_id_span').css('color','blue');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디를 입력해주세요");
					
// 				}
				
<%-- 				else if('<%=UserList.get(i).getUserid()%>'== input_id){ --%>
// 					$('#check_id_span').css('color','red');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디 중복");
// 			  		return false;
// 				}
// 				else{
// 					$('#check_id_span').css('color','green');
// 					$('#check_id_span').css('opacity','1');
// 			  		$('#check_id_span').html("아이디 사용가능");
// 				}
				
<%-- 			<%}%> --%>
			
		});

		$('.input_pwd').focusout(function () {
			  
			  	var input_pw=$('#input_pwd').val();
			  	var input_pw_re=$('#input_pwd_re').val();
			  	if(input_pw==''||input_pw_re==''){
			  		null;
			  	}
			  	else{
			  	  	if(input_pw==input_pw_re){
				  		$('#check_pwd_span').css('color','green');
						$('#check_pwd_span').css('opacity','1');
				  		$('#check_pwd_span').html("비밀번호가 일치합니다.");
				  	}
				  	else{
				  		$('#check_pwd_span').css('color','red');
						$('#check_pwd_span').css('opacity','1');
				  		$('#check_pwd_span').html("비밀번호를 다시 확인해주세요.");
				  	}
			  	}
		  });
		
		


	</script>




</body>
</html>