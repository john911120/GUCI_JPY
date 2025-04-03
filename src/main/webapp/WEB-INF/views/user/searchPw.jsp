<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>PW探し</title>
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
        	<div class="col-xs-8 col-xs-offset-4">
				<h2>PW 探し</h2>
			</div>

                    <div class="form-group">
                        <label class="control-label col-xs-4">ID</label>	
						<div class="col-xs-8">
							<input class="form-control" id="userId" name="userId" type="text" placeholder="IDを入力してください。" required  style="margin-bottom:10px;">
						</div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-xs-4">メールアドレス</label>	
						<div class="col-xs-8">
							<input class="form-control" id="userEmail" name="userEmail" type="text" placeholder="メールアドレスを入力してください。" required style="margin-bottom:10px;">
						</div>
                    </div>
          			<div class="form-group">
						<div class="col-xs-8 col-xs-offset-4" style="float: right;">
	                       	<button type="button" id="findBtn" class="btn btn-primary">確認</button>
	               			<button type="button" onclick="self.close()" class="btn btn-danger">取り消し</button>
	           			</div>
            		</div>
                </div>
            </div>
    
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="/resources/js/bootstrap.js"></script> 	
 <script>
  
	$(function(){
		$("#findBtn").click(function(){
			$.ajax({
				url : "/user/searchPw",
				type : "POST",
				data : {
					userId : $("#userId").val(),
					userEmail : $("#userEmail").val()
				},
				success : function(result) {
					alert(result);
					self.close();
				},
			});
		});
	});
</script>
</body>
</html>