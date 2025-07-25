<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
  request.setCharacterEncoding("UTF-8");
%> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
   function backToList(obj){
	  obj.action="${contextPath}/board/listArticles.do";
  	  obj.submit();
	}
   
   function fn_enable(obj){
	   // disabled 속성을 false로 설정
	   document.getElementById("i_title").disabled=false;
	   document.getElementById("i_content").disabled=false;
	   document.getElementById("i_imageFileName").disabled=false;
	   document.getElementById("tr_btn_modify").style.display="block";
	   document.getElementById("tr_btn").style.display="none";
   }
   
   // 수정 반영하기 클릭 시 컨트롤러에 수정 데이터를 전송
   function fn_modify_article(obj){
	   obj.action="${contextPath}/board/modArticle.do";
	   const url = obj.action || "${contextPath}/board/modArticle.do";
	   const formData = new FormData(obj);
	  /* for (const [key, val] of formData.entries()) {
	     console.log(key, val);
	   }*/
		// obj.submit();	// 작동x
	   fetch(url, {
		    method: "POST",
		    body: formData,
		  }).then(response => {
		    if (response.ok) {
		      alert("수정 성공");
		    } else {
		      alert("실패");
		    }
		  });
   }
   
   function readURL(input) {
	     if (input.files && input.files[0]) {
	         var reader = new FileReader();
	         reader.onload = function (e) {
	             $('#preview').attr('src', e.target.result);
	         }
	         reader.readAsDataURL(input.files[0]);
	     }
   }
   
   
</script>
</head>
<body>
	<form name="formArticle" method="post" enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					글번호
				</td>
				<td>
					<input type="text" value="${article.articleNO }" disabled>
					<input type="hidden" name="articleNO" value="${article.articleNO }">
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					작성자 아이디
				</td>
				<td>
					<input type="text" value="${article.id }" name="id" disabled>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					제목
				</td>
				<td>
					<input type="text" value="${article.title }" name="title" id="i_title" disabled>
				</td>
			</tr>
			<tr>
				<td width="150" align="center" bgcolor="#FF9933">
					내용
				</td>
				<td>
					<textarea rows="20" cols="60" name="content" id="i_content" disabled>${article.content }</textarea>
				</td>
			</tr>
			<c:if test="${not empty article.imageFileName && article.imageFileName!='null' }">
				<tr>
					<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">
						이미지
					</td>
					<td>
						<input type="hidden" name="originalFileName" value="${article.imageFileName }">
						<img src="${contextPath }/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview">
					</td>
				</tr>
				<tr>
					<td>
						<input type="file" name="imageFileName" id="i_imageFileName" onchange="readURL(this)" disabled>
					</td>
				</tr>
			</c:if>
			<tr>
				<td width="20%" align="center" bgcolor="#FF9933">
					등록일자
				</td>
				<td>
					<input type="text" value="<fmt:formatDate value='${article.writeDate }'/>" disabled>
				</td>
			</tr>
			<tr id="tr_btn_modify">
				<td colspan="2" align="center">
					<input type="button" value="수정 반영하기" onclick="fn_modify_article(formArticle)">
					<input type="button" value="취소" onclick="backToList(formArticle)">
				</td>
			</tr>
			<tr id="tr_btn">
				<td colspan=2 align="center">
				  <input type=button value="수정하기" onClick="fn_enable(this.form)">
				  <input type=button value="삭제하기" onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})">
				  <input type=button value="리스트로 돌아가기"  onClick="backToList(this.form)">
				  <input type=button value="답글쓰기"  onClick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})">
			   </td>
			</tr>
		</table>
	</form>
</body>
</html>