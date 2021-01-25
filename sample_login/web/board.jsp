<%@page import="java.util.ArrayList"%>
<%@page import="com.login.entity.login_entity"%>
<%@page import="com.board.entity.rec_entity"%>
<%@page import="com.board.dao.imgBoard_dao"%>
<%@page import="java.util.List"%>
<%@page import="com.board.entity.imgBoard_entity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<jsp:include page="/menu.jsp" />
<!-- 젇대경로 -->

<script>
var pg = 1;


$(window).scroll(function() {
   if ($(window).scrollTop() == $(document).height() - $(window).height()) {
      ++pg;
       lastPost();
}
});

</script>

<c:set var="imgDao" value='<%=new imgBoard_dao() %>'/>
<c:set var="recEntity" value='<%=new rec_entity() %>'/>

<input type="hidden" style="display: none;" id="hidden_logId" value="${logOK.userid }">
<c:if test="${!empty list }">
	<div id="appendList">
	<c:out value='list : ${list.size() }'/>
		<c:forEach items="${list}" var="imgBoard">
			<c:set target='${recEntity}' property='idx' value='${imgBoard.idx }'/>
			<c:set target='${recEntity}' property='userid' value='${logOK.userid}'/>
			<c:set var='n' value='${imgDao.recCheck(recEntity)}' scope='page'/>
			<c:set var='c' value='${imgDao.recCount(recEntity.idx)}' scope='page'/>
			<c:out value="${n}"/>
			<div class="scrolling">
				<div class="scrolling_profile_bar">
					<span id="userid">
							<c:if test='${logOK.userid eq imgBoard.userid}'>
								<a href='profileList.do?id=${imgBoard.userid}' class="test">
									<img name="profileimg"
									src="/insta/profile_img/${imgBoard.writeuserimg}"
									class='scrolling_writerImg'> <span
									class='scrolling_writerId'>${imgBoard.userid}</span>
								</a>
							</c:if>
							<c:if test='${logOK.userid ne imgBoard.userid}'>
								<a href='idprofile.do?id=${imgBoard.userid}'> <img
									name="profileimg"
									src="/insta/profile_img/${imgBoard.writeuserimg}"
									class='scrolling_writerImg'> <span
									class='scrolling_writerId'>${imgBoard.userid}</span>
								</a>
							</c:if>
					</span>
				</div>

				<div class='scrolling_board_frame'>
					<a href='boardView.do?idx=${imgBoard.idx}'
						class='scrolling_board_a_tag'> <img
						src='/insta/storage/${imgBoard.imgPath}'
						class='scrolling_board_img'></a>
				</div>
				<div class="scrolling_icon_bar">

        		<c:if test='${n eq 1}'>
        			<i class="fas fa-heart fa-2x scrooling_icon_${imgBoard.idx} likePlus" 
    						onclick="likeCheck(${imgBoard.idx})"></i>
        		</c:if>
        		<c:if test='${n eq 0}'>
        			<i class="far fa-heart fa-2x scrooling_icon_${imgBoard.idx} likePlus"
    						onclick="likeCheck(${imgBoard.idx})"></i>
        		</c:if>

         		
					<span class="rec_count_${imgBoard.idx }">${c}</span>
						 <i
						class="far fa-comment fa-2x scrooling_icon"
						onclick="location.href='boardView.do?idx=${imgBoard.idx}'"></i>
				</div>
				<div class="scrolling_content">${imgBoard.content }</div>
				<div class="scrolling_logtime">
					<span id="logtime">작성일 : ${imgBoard.logtime }</span>
				</div>

			<c:remove var="n" scope='page'/>
			</div>
		</c:forEach>
	</div>
</c:if>
<script>

function lastPost(){
	   $.ajax({
	      url : "/insta/boardAjax",      //Servlet으로 비동기 요청
	      data : {pg:pg},               // 몇페이지? 
	      type : "get",
	      dataType: "json",
	      success : function(listObj) { //펑션안에 들어가는문장은 명명하기따라 다르다
	         for (var i = 0; i < listObj.length; i++) {
	        	 <c:set var="imgDao" value='<%=new imgBoard_dao() %>'/>
	         var append_div = "";
	       		append_div += "<c:set var='or' value='${imgDao.recCheck(recEntity)}'/>";
	            append_div += "<div class='scrolling'>";
	            append_div += "<div class='scrolling_profile_bar'><span id='userid'>";
	            append_div += "<c:if test='${logOK.userid eq imgBoard.userid}'>";
	          	append_div += "<a href='profileList.do?id="+listObj[i].boardUserid+"' class=''>";
	           	append_div += "<img name='profileimg' src='/insta/profile_img/"+listObj[i].boardWriteuserimg+"' class='scrolling_writerImg'>";
	           	append_div += "<span class='scrolling_writerId'>"+listObj[i].boardUserid+"</span></a></c:if>";
	        	append_div += "<c:if test='${logOK.userid ne imgBoard.userid}'>";
	          	append_div += "<a href='idprofile.do?id="+listObj[i].boardUserid+"' >";
	           	append_div += "<img name='profileimg' src='/insta/profile_img/"+listObj[i].boardWriteuserimg+"' class='scrolling_writerImg'>";
	           	append_div += "<span class='scrolling_writerId'>"+listObj[i].boardUserid+"</span></a></c:if>";
	            append_div += "</span></div>"; 
	            append_div += "<div  class='scrolling_board_frame'><a href='boardView.do?idx="+listObj[i].boardIdx+"' class='scrolling_board_a_tag'>";
	            append_div += "<img src='/insta/storage/"+listObj[i].boardImgPath+"'  class='scrolling_board_img'></a>";
	            append_div += "</div>";
	            append_div += "<div class='scrolling_icon_bar'>";
	            <%-- 좋아요 수정부분 --%>
				append_div +="<c:if test='${n eq 1}'>";
				append_div +="<i class='fas fa-heart fa-2x scrooling_icon_"+listObj[i].boardIdx+" likePlus' onclick='likeCheck("+listObj[i].boardIdx+")'></i></c:if>";
				append_div +="<c:if test='${n eq 0}'>";
				append_div +="<i class='far fa-heart fa-2x scrooling_icon_"+listObj[i].boardIdx+" likePlus' onclick='likeCheck("+listObj[i].boardIdx+")'></i></c:if>";
	    		append_div +="<span class='rec_count_"+listObj[i].boardIdx+"'>sdasda</span>";
	    		
	           	append_div +="<i class='far fa-comment fa-2x scrooling_icon' onclick=location.href='boardView.do?idx="+listObj[i].boardIdx+"'></i>";	
	           	append_div +="</div>";
	           	
	           	append_div +="<div class='scrolling_content'>"+listObj[i].boardContent+"</div>";
	           	append_div +="<div class='scrolling_logtime'>";
	           	append_div +="<span id='logtime'>작성일 : "+listObj[i].boardDate+"</span>";
	           	append_div +="</div>";
	           	append_div +="<c:remove var='n'/>";

	            $("#appendList:last").append(append_div);
	            $('.rec_count_'+listObj[i].boardIdx).html(${imgDao.recCount(listObj[i].boardIdx)});
	            }
	         }
	      });
	}

	function likeCheck(idxValue){
		   
		  var className='.scrooling_icon_';
				className=className+idxValue;
	  			
	             var  userid= $("#hidden_logId").val();
	             var idx = idxValue;
					
	             console.log(className);
	             
	     $.ajax({
	         url : "/insta/RecUpdateService_url",    
	         data : {userid:userid , idx:idx},               
	         type : "get",

	         success : function(count){ 
	           	 console.log(':::'+count)
	        	   console.log(">>" + className)
	        	   
	        	   var tempStr=".rec_count_";
	           	 
	           	 tempStr=tempStr+idx;
	           	 
	        	  $(tempStr).html(count);
	        	  console.log(tempStr)
	        	   
	        	   if($(className).hasClass("fas") === true) {

	            	   $(className).attr('class','far fa-heart fa-2x scrooling_icon_'+idxValue);      
						console.log("좋아요 취소")
	        		}
	        	   else if($(className).hasClass("far") === true){
	            	   $(className).attr('class','fas fa-heart fa-2x scrooling_icon_'+idxValue);      	   
						console.log("좋아요 등록")
	        	   }
	           	 
	           	 
	         },
	         error : function(count){ 
	      	   
	      	   alert("no,,");
	           	

	      	   
	         }
	     });
	 };
	 

	</script>



</body>
</html>