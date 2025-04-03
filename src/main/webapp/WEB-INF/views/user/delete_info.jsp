<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css">
</head>
<body style="margin:10px;">
<h2>会員脱退 </h2>
<p>脱退した後は、このサービスは、ご利用できません。</p><br>
<p>宜しいでしょうか?</p>

<br><br><br><br><br><br><br><br>
<form id="form" action="/user/delete_info" method="post" role="form">
	<div class="pull-right">
		<button id="delete" class="btn btn-danger" type="button">会員脱退</button>
		<button id="cancel" class="btn btn-primary" type="button">取り消し</button>
	</div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/resources/js/bootstrap.js"></script>  
<script>
	$("#delete").on("click",function(e){
		$("#form").submit();
		alert("会員脱退しました。ご利用いただきありがとうございました。");
		location.href="/";
	});
	
	$("#cancel").on("click",function(e){
		self.close();
	});
</script>

</body>
</html>