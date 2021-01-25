<%@page import="com.login.entity.login_entity"%>
<%@page import="java.util.List"%>
<%@page import="com.login.entity.id_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link href="../style.css" rel="stylesheet" type="text/css"> -->
<link href="/insta/style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/b97968ecfe.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">

<style> /*a태그 색 제거  */
    a { text-decoration: none; color: black; }
    a:visited { text-decoration: none; }
    a:hover { text-decoration: none; }
    a:focus { text-decoration: none; }
    a:hover, a:active { text-decoration: none; }
</style>



</head>

<body>
	<div id="menu">
<%
	List<id_entity> idList = (List<id_entity>) session.getAttribute("idlist");
	System.out.println(idList);
	login_entity loginEntity = (login_entity) session.getAttribute("logOK");

		for (int n = 0; n < idList.size(); n++) {
			if (idList.get(n).getUserid().equals(loginEntity.getUserid())) {
				idList.remove(n);
			}

		}
%>

		<c:if test="${empty logOK}">
			<a class="a logo" href="/insta/login.jsp" >EZENSTAGRAM</a>
			<a class="a" href="/insta/login.jsp">로그인</a>
			<a class="a" href="login/signUp.jsp">회원 가입</a>
		</c:if>

		<c:if test="${!empty logOK}">

			
			<a class="a logo" href="/insta/boardList.do">EZENSTAGRAM</a>
			
			<div class="searchbox">
				<input onkeyup="filter()" type="text" id="value"
					placeholder="&#xF002; 검색" class="search_input" 
					oninput='showUserList()'>

				<div class="itemList">
					<%
						for (int n = 0; n < idList.size(); n++) {
					%>
					<div class="item" onclick="location.href='/insta/idprofile.do?id=<%=idList.get(n).getUserid()%>'">
						
						<img src="/insta/profile_img/<%=idList.get(n).getProfileimg()%>" class="itemlist_img"/>
						<span class="itemlist_name">
							<%=idList.get(n).getUserid()%>
						</span>
					</div>
					<%
						}
					%>
				</div>
			</div>
			
			
			<div class="icon_box">
			<i class="fas fa-user fa-2x mypageIcon" onclick="location.href='/insta/profileList.do?id=${logOK.userid}'"></i>
			<i class="fas fa-pencil-alt fa-2x writeIcon" onclick="location.href='/insta/write/user_write.jsp'"></i>
<!-- 			<a class="a" href="/insta/login/user_info.jsp">정보 변경</a> -->		
			<i class="fas fa-power-off fa-2x logoutIcon" onclick="location.href='/insta/logout.do'"></i>
			</div>
			

		</c:if>
	</div>

	<script type="text/javascript">
		function filter() {
			var value, name, item, i;

			value = document.getElementById("value").value.toUpperCase();
			item = document.getElementsByClassName("item");

			for (i = 0; i < item.length; i++) {
				name = item[i].getElementsByClassName("itemlist_name");
				if (name[0].innerHTML.toUpperCase().indexOf(value) > -1) {
					item[i].style.display = "flex";
				} else {
					item[i].style.display = "none";
				}
			}
		}

		function showUserList() {
			
			var chk = $('.search_input').val();
			console.log(chk);
			if (chk == '') {
				$('.itemList').css('display', 'none');
			} else {
				filter();
				$('.itemList').css('display', 'block');
			}

		}
	</script>




