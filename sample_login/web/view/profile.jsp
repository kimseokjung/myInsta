<%@page import="com.login.entity.login_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/menu.jsp"/>  <!-- 젇대경로 -->
<!-- 개인정보 수정하기 -->

	<form name="uploadForm" method="post" action="/insta/myProfile.do" enctype="multipart/form-data" class="profile_form" onsubmit='return chk();'>
		<div class="profile_img_div">
			<img  src="/insta/profile_img/${logOK.profileimg}"  class="profile_img">
			<div class="btn_upload_div">
				<img src="/insta/myIcon/plus.png" id="btn_upload" class="btn_upload"/>
			</div>
		</div>
		<div>
			<div>
				<input type="file" name="imgpath" id="file" size="55" style="display:none;" >
			</div>
			<div class="edit_bar">
				<input type="submit" value="프로필 변경 확인" id="id_change_btn"/>
				<!-- <input type="button" onClick="location.href='/insta/login/chk_pwd.jsp'" value="정보 변경"/> -->
				
				<i class="fas fa-user-edit fa-2x" onClick="location.href='/insta/login/chk_pwd.jsp'"></i>
			</div>
		</div>
		<div class="postList">
			<div class="postList_frame">
		
		<c:if test="${!empty polist}">
		<c:forEach items="${polist}" var="parson">
			<div class="post_div">
			<a href="boardView.do?idx=${parson.idx}" class="post_a">
				<img src="/insta/storage/${parson.imgPath}" width="200" class="post_img">
			</a>
			</div>	
		</c:forEach>
		</c:if>
			</div>
		
		</div>
	</form>

<script>

$(function () {

	$('.btn_upload').click(function (e) {
		
	e.preventDefault();
	$('#file').click();

	});

	});



var upload = document.querySelector('#file');
var preview = document.querySelector('.profile_img_div');

 upload.addEventListener('change',function (e) {
        var get_file = e.target.files;
        
        var image = document.createElement('img');
        var reader = new FileReader();
        reader.onload = (function (aImg) {
 
            return function (e) {
                /* base64 인코딩 된 스트링 데이터 */
                aImg.src = e.target.result
            }
        })(image)
 
        if(get_file){

            reader.readAsDataURL(get_file[0]);
        }
        
        $('.btn_upload_div').remove();
        $('.profile_img').remove();
        $('.btn_upload').remove();
        
        
        var imagePlusBtn_div=document.createElement('div');
        imagePlusBtn_div.classList.add('btn_upload_div');
        
        
        var imagePlusBtn = document.createElement('img');
        imagePlusBtn.classList.add('btn_upload');
		
        imagePlusBtn.src= '/insta/myIcon/plus.png';
        imagePlusBtn.classList.add('btn_upload');
   
        imagePlusBtn_div.appendChild(imagePlusBtn);
        
        
        $('#id_change_btn').css("display","inline");
        image.classList.add('profile_img');
 
        preview.appendChild(image);
        preview.appendChild(imagePlusBtn_div);
        
        $('.btn_upload').click(function (e) {
    		
        	e.preventDefault();
        	$('#file').click();

        	});
    });
    
    
function chk() {
	var chk = $('#file').val();

	if (chk =='') {
		alert('사진 파일을 등록하세요');
		return false;
	} else {
		return true;
	}

}
	</script>


</body>
</html>