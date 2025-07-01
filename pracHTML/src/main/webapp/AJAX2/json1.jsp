<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON 테스트</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#checkJson").click(function(){
			var jsonStr='{"name": ["홍길동", "이순신", "임꺽정"]}';
			var jsonInfo=JSON.parse(jsonStr);	// jQuery의 json기능인 parse() 메서드를 이용해 JSON 자료형을 가져옴
			var output="회원 이름<br>";
			output+="======<br>";
			for(var i in jsonInfo.name){
				console.log(jsonInfo.name[i]);
				output+=jsonInfo.name[i]+"<br>";
			}
			$("#output").html(output);
		});
	});
</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">출력</a><br><br>
    <div id="output"></div>
</body>
</html>