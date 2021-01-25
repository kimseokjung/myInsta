<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../menu.jsp"/>

<script>
function checkBoardUpdate(){
   if(document.boardUpdateForm.title.value=="") {
      alert("제목을 입력하세요");  
      boardUpdateForm.subject.forcus();
   } else if(document.boardUpdateForm.content.value=="") {
      alert("내용을 입력하세요");
      boardUpdateForm.subject.forcus();
   }else {
      document.boardUpdateForm.submit();
   }
}
</script>
<c:if test="${!empty view}">

	<form name="boardUpdateForm" method="post" action="../userBoardUpdate.do">
	<h3>수정하기</h3>
	<table border="0" >
	   <tr>
	      <td>아이디</td>
	      <td><input type="text" name="userid" size="55"  value="${view.userid}" readonly></td>
	   </tr>
	   <tr>
	      <td>제 목</td>
	      <td><input type="text" name="title" size="55" value="${view.title}"></td>
	   </tr>   
	   <tr>
	      <td>내 용</td>
	      <td><textarea name="content" cols="47" rows="10">${view.content }</textarea></td>
	   </tr>
	   <tr>
	      <td colspan="2" align="center">
	      <input type="button" value="수정" onclick="checkBoardUpdate()">
	      <input type="hidden" value="${idx }" name="idx">
	      <input type="button" value="취소" onclick="history.back(-1);">
	      </td>
	   </tr>
	</table>
   	</form>

</c:if>

</body>
</html>