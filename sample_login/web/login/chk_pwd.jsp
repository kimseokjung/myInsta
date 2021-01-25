<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>

function pwdchk(){
	var rpwd = document.getElementsByName("rpwd")[0].value;

	if(${logOK.pwd} == rpwd){
		document.form_name.submit();
	}else{
		alert("현재 비밀번호와 틀립니다");
	}
}

</script>

<form action="/insta/login/user_info.jsp" method="post" id="form_id" name="form_name">
	<table>
		<tr>
			<th colspan="2" align="center">정보 변경</th>
		</tr>
		<tr>
			<td>현재 아이디 :</td>
			<td name="real_userid">${logOK.userid}</td>
		</tr>
		<tr>
			<td>현재 비밀번호</td>
			<td><input type="password" id="password" placeholder="현재 비밀번호"
				name="rpwd">
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="확인" onclick="pwdchk()">
			</td>
			<td>
				<input type="button" value="취소" onclick="history.back() ">
			</td>
		</tr>

	</table>
</form>
</body>
</html>