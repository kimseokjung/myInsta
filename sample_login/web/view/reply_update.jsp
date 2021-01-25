<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	String content = request.getParameter("content");
	int board_idx = Integer.parseInt(request.getParameter("idx"));
	int reply_idx = Integer.parseInt(request.getParameter("replyidx"));
%>
<body>

	<div>
		<form action="/insta/replyUpdate.do" method="post">
			<input type="text" placeholder="댓글 입력" name="reply_update" value="<%=content%>"/>
			<input type="hidden" name="reply_idx" value="<%=reply_idx%>"/>
			<input type="hidden" name="board_idx" value="<%=board_idx%>"/>
			<input type="submit" value="수정">
			<input type="button" value="취소" onclick="history.back() ">
		</form>
	</div>

</body>
</html>