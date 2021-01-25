<%@page import="com.login.entity.login_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/menu.jsp" />
<!-- 젇대경로 -->
<!-- 남의 프로필 훔쳐보기 -->
<%
	String clickprofileImg = (String)request.getAttribute("clickidProfileImg");
%>

<div class="idprofile_form">

	<div class="idprofile_img_div">
		<img name="profileimg" src="/insta/profile_img/<%=clickprofileImg%>"
			class="idprofile_img">

	</div>
	<div class="idprofile_postList">
		<div class="idprofile_postList_frame">

			<c:if test="${!empty list}">
				<c:forEach items="${list}" var="parson">
				<div class="idprofile_post_div">
					<a href="boardView.do?idx=${parson.idx}"> <img
						src="/insta/storage/${parson.imgPath}" class="idprofile_post_img">
					</a>
				</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</div>
</body>
</html>