<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ID探し</title>
	<link rel="stylesheet" href="/resources/css/bootstrap.css">
<style>
	.center-pills,
	.searchForm {
    display: flex;
    justify-content: center;
	}

	h2{
		margin-top: 100px;
	}

	h2{
		padding-bottom: 10px;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="searchForm">
				<form action="searchId" method="post" class="form-horizontal" name="searchI">
					<div class="col-xs-8 col-xs-offset-4">
						<h2>ID 探し</h2>
					</div>
					<div class="form-group">
						<label class="control-label col-xs-4">名前</label>
						<div class="col-xs-8">
							<input type="text" class="form-control" name="userName" id="userName">
						</div>
					</div>
			
					<div class="form-group">
						<label class="control-label col-xs-4">携帯電話</label>
						<div class="col-xs-8">
							<input type="tel" class="form-control" name="userPhone" id="userPhone">
						</div>  
						      	
					</div>
					<c:if test="${check != 0 }">
					<div class="form-group">
						<div class="col-xs-8 col-xs-offset-4" style="float: right;">
							<input type="submit" class="btn btn-primary" value="確認">
							<input type="button" class="btn btn-danger" value="取り消し" onclick="closethewindow()">
						</div>
						</div>
					</c:if>
						
						<!-- 名前と 携帯電話が一致しない場合-->
		<c:if test="${check == 1}">
			<script>
				opener.document.searchI.userName.value = "";
				opener.document.searchI.userPhone.value = "";
			</script>
			<label>一致する情報がありません。</label>
		</c:if>
					
		<!-- 名前と パスワードが一致しない場合-->
		<c:if test="${check == 0 }">
		<label>探している IDは' ${userId}' です。</label>
		<div class="form-group">
			<div class="col-xs-8 col-xs-offset-4" style="float: right;">
				<input type="button" class="btn btn-danger" value="閉じる" onclick="closethewindow()">
			</div>		
		</div>
						
		</c:if>
							      
				</form>
			</div>
		</div>
	</div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="/resources/js/bootstrap.js"></script>  	
	
	<script type="text/javascript">
		function closethewindow(){
			self.close();
		}
	</script>
</body>
</html>