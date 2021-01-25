<%@page import="com.login.entity.login_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/menu.jsp" />
<!-- 젇대경로 -->


<c:if test="${!empty logOK}">



	<div class="user_write_frame">
		<div class="user_write_preview">
		</div>
		<form name="boardWriteForm" method="post" action="/insta/userWrite.do"
			enctype="multipart/form-data" class="user_write_form_class">
			
			<input type="text" name="userid" size="55" value="${logOK.userid}"
				readonly class="user_write_id">
				
			<input type="file" name="imgpath" size="55" class="user_write_img"
				id="user_write_upload_img">
				
			<textarea name="content" cols="47" rows="10"></textarea>
			<div class="user_write_bottom_div">
				<input type="button" value="글쓰기" onclick="checkBoardWrite()"
					class="user_write_submit"> 
				<input type="reset" value="다시작성" class="user_write_reset">
			</div>
		</form>

	</div>
</c:if>



<script>
	function checkBoardWrite() {
		if (document.boardWriteForm.content.value == "") {
			alert("내용을 입력하세요");
			boardWriteForm.subject.focus();
		} else {
			document.boardWriteForm.submit();
		}
	}
	var upload = document.querySelector('#user_write_upload_img');
	var preview = document.querySelector('.user_write_preview');

	upload.addEventListener('change', function(e) {
		var get_file = e.target.files;
		var image = document.createElement('img');
		var reader = new FileReader();
		reader.onload = (function(aImg) {
			console.log(1);
			return function(e) {
				aImg.src = e.target.result
			}
		})(image)

		if (get_file) {
			reader.readAsDataURL(get_file[0]);
		}
		image.style.width = 300 + 'px';
		image.style.height = 300 + 'px';
	
		upload.style.display='none';
		
		preview.appendChild(image);
	})
</script>


</body>
</html>