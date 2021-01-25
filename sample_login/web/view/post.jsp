<%@page import="com.login.entity.login_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../menu.jsp" />


<%
	int count = 0;
%>

<c:if test="${!empty boardView}">
	<div class="view_post_frame">
		<div class="view_post_line">
			<div class="view_post_top">
				<img src="/insta/storage/${boardView.imgPath}">
			</div>
			<div class="view_post_bottom">
				<div class="view_post_bottom_profileline">
						<img name="profileimg"
							src="/insta/profile_img/${boardView.writeuserimg}">
					<div class="view_post_bottom_userid">${boardView.userid }</div>
					<c:if test="${logOK.userid == boardView.userid }">
						<!--작성자 확인 -->
						<div>
							<a href="boardModify.do?idx=${boardView.idx}">수정</a>
						</div>
						<div>
							<a href="boardDelete.do?idx=${boardView.idx}">삭제</a>
						</div>
					</c:if>
					<div class="view_post_bottom_logtime">${boardView.logtime}</div>
				</div>

				<div class="view_post_bottom_content">${boardView.content }</div>
			</div>
			<c:if test="${!empty Replylist}">
				<c:forEach items="${Replylist }" var="replylist">
					<div class="view_post_bottom_replyList">
						<form name="form" method="post" class="view_post_bottom_replyList_form">
							<c:if test="${logOK.userid == replylist.writeid }">
								<a href="/insta/profileList.do?id=${replylist.writeid}"><span class="view_post_bottom_replyList_id">${replylist.writeid}</span></a>
							</c:if>
							<c:if test="${logOK.userid != replylist.writeid }">
								<a href="/insta/idprofile.do?id=${replylist.writeid}"><span class="view_post_bottom_replyList_id">${replylist.writeid}</span></a>
							</c:if>

							<div class="content post_reply_content_<%=count%>">${replylist.content}</div>
							<div class="view_post_bottom_replyList_logtime">${replylist.logtime}</div>
							<c:if test="${logOK.userid == replylist.writeid }">
								<input type="submit" value="&#xf2ed;" class="delbtn"
									onclick="javascript: form.action='/insta/replyDelete.do?idx=${boardView.idx}&&replyidx=${replylist.idx}';" />
								<input type="button" value="&#xf044" onclick="modifyChk(<%=count%>);"
									class="update_btn" />
								<div style="display: none;"
									class="post_reply_modify_bar_<%=count%>">
									<input type="text" value="" name="content"
										class="post_reply_modify_content_<%=count%>" /> <input
										type="submit" value="확인"
										onclick="javascript: form.action='/insta/replyUpdate.do?idx=${boardView.idx}&&replyidx=${replylist.idx}';" />
									<input type="button" value="닫기" onclick="close_bar(<%=count%>)">
								</div>
							</c:if>

						</form>
					</div>
					<%
						count++;
					%>
				</c:forEach>
			</c:if>
			<div>
				<form id="write_reply" action="replyWrite.do" method="post"  >
					<input type="text" placeholder="댓글 입력" name="reply_write" class="write_reply_inputbar">
					<input type="hidden" name="write_user" value="${logOK.userid}">
					<input type="hidden" name="board_idx" value="${boardView.idx}">
					<input type="submit" value="입력">
				</form>
			</div>
		</div>
	</div>
</c:if>

<script>



function modifyChk(count){
	
	var show_submit_btn = '.post_reply_modify_bar_';
	show_submit_btn=show_submit_btn+count;
	
	$(show_submit_btn).css('display','inline');
	
	var str = '.post_reply_content_';
	str = str + count;
	var reply_content= $(str).text();
	
	
	
	
	var str2 = ".post_reply_modify_content_";
	str2=str2+count;
	
	$(str2).val(reply_content)
	
	
}

function close_bar(count){
	
	var show_submit_btn = '.post_reply_modify_bar_';
	show_submit_btn=show_submit_btn+count;
	
	$(show_submit_btn).css('display','none');

}




</script>






</body>
</html>