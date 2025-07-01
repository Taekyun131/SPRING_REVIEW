<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON 테스트</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").click(function(){
			$.ajax({
				type: "post",
				async: false,
				url: "${contextPath}/json3",
				success:function(data, textStatus){
					var jsonInfo=JSON.parse(data);	// 서버에서 전송한 JSON 데이터를 파싱
					var memberInfo="회원정보<br>";
					memberInfo+="======<br>";
					for(var i in jsonInfo.members){
						memberInfo+="이름: "+jsonInfo.members[i].name+"<br>";
						memberInfo+="나이: "+jsonInfo.members[i].age+"<br>";
						memberInfo+="성별: "+jsonInfo.members[i].gender+"<br>";
						memberInfo+="별명: "+jsonInfo.members[i].nickname+"<br><br><br>";
					}
					
					var booksInfo="<br><br><br>도서정보<br>";
					booksInfo+="======<br>";
					for(var i in jsonInfo.books){
						booksInfo+="제목: "+jsonInfo.books[i].title+"<br>";
						booksInfo+="저자: "+jsonInfo.books[i].writer+"<br>";
						booksInfo+="가격: "+jsonInfo.books[i].price+"<br>";
						booksInfo+="장르: "+jsonInfo.books[i].genre+"<br>";
						imageURL=jsonInfo.books[i].image;
						booksInfo+='<img src='+imageURL+'>'+"<br><br><br>";
					}
						$("#output").html(memberInfo+"<br>"+booksInfo);
				},
				error:function(data, textStatus){
					alert("에러가 발생했습니다.");
				},
				complete:function(data, textStatus){
				}
			});
		});
	});
</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>