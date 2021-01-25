<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../menu.jsp"%>

<script>
var chk;
var count;

function goPopup(){
	var pop = window.open("/insta/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
}

function jusoCallBack(roadFullAddr){
		
	console.log(roadFullAddr);
		document.form_name.change_address.value=roadFullAddr;  /* input의 name값을통해 찾는다. */
}



function blankCheck(){
	var pwd = $('input[name=change_pwd]').val();
	   
	if(document.form_name.change_pwd.value == ""){
		document.form_name.change_pwd.value="${logOK.pwd}";
	}
	    return true;

	   if(chk=="true"){
	      if(document.form_name.change_pwd.value == ""){
	         document.form_name.change_pwd.value="${logOK.pwd}";
	      }
	      return true;
	   }else{
	      alert("현재 비밀번호와 틀립니다")
	      return false;
	   }
}

</script>

<form action="/insta/boardList.do" method="post" id="form_id"
	name="form_name" onsubmit="return blankCheck();">
	<table>
		<tr>
			<th colspan="2" align="center">정보 변경</th>
		</tr>
		<tr>
			<td>현재 아이디 :</td>
			<td name="real_userid">${logOK.userid}</td>
		</tr>

		<tr>
			<td>변경 비밀번호</td>
			<td><input type="password" placeholder="바꿀 비밀번호"
				name="change_pwd" autocomplete=off></td>
		</tr>

		<tr>
			<td>EMAIL</td>
			<td><input type="text" placeholder="바꿀 이메일"
				value="${logOK.email}" name="change_email" id="change_email"
				autocomplete=off></td>
		</tr>
		<tr>
			<td>주소</td>
			<td><input type="text" placeholder="바꿀 주소"
				value="${logOK.address}" name="change_address" id="change_address"><input
				type="button" value="주소검색" onclick="goPopup();" autocomplete=off></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				value="정보 변경 완료" /></td>
			<td><input type="button" value="뒤로가기" onclick="history.back() "></td>
		</tr>

	</table>
</form>
</body>
</html>