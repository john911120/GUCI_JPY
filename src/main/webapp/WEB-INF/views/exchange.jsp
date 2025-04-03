<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>交換/払い戻し申請</title>
<jsp:include page="include/css.jsp" />
</head>
<body>  
<div class="row">
	<div class="col-sm-10" style="margin:10px 20px;">
		<h3>交換/払い戻し申請</h3> 
		<div class="panel panel-default">
			<div class="panel-body">
			
				<form role="form" action="/board/register" method="post" style="margin-left:12px;">
					<div class="form-group">
 				    	<select class="custom-select col-md-2 col-sm-4" style="margin-right:10px;">
 					        <option value="">選択</option>
					        <option value="1">交換</option>
					        <option value="2">払い戻し</option>
				    	</select>
					</div>
				
					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">商品名</label>
					    <div class="col-sm-10">
					      <input type="text" class="form-control" readonly value="ジーンズ">
					    </div>
					  </div>

					  <div class="form-group row">
					    <label class="col-sm-2 col-form-label">数量</label>
					    <div class="col-sm-10">
					      <input type="number" class="form-control">
					    </div>
					  </div>
									
					<div class="form-group col-md-12" style="padding:0px;">
						<label>사유</label> <textarea class="form-control" rows="5"></textarea>
					</div>
					<div class="form-group " style="float:right">
						<button type="submit" id="register_btn" class="btn btn-primary">申し込み</button>	
						<button type="button" id="cancel_btn" class="btn btn-danger">取り消し</button>					
					</div>												
				</form>
			</div>
		</div>
		<small style="color:red;">*商品に不良があった場合や、配送遅延など当店に過失がある場合は、追加料金なしで速やかに対応させていただきます。<br>
		*一方、お客様のご都合による返品・交換（例：サイズ違い、イメージと異なる等）の場合は、往復の送料をご負担いただきますので、予めご了承ください。</small>
	</div>
</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="/resources/js/bootstrap.js"></script> 
  <script>
	$(function(){
		$("#cancel_btn").on("click",function(e){
			window.open('','_self').close(); 
		});
	});
  </script>
</body>
</html>