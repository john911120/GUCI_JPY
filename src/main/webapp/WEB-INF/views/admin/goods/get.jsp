<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品を見る</title>

    <!-- Custom fonts for this template -->
    <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="/resources/admin/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

	 <jsp:include page="../include/menu.jsp" /> 
	 
	         <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-800">商品を見る</h1><br>	 

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
						
								<div class="form-group row">
									<label class="col-md-1 col-form-label">分類</label>
					
								    <input id="gender" class="col-md-1 col-sm-4 form-control" type="text" style="margin-left:12px;" value='<c:out value="${goods.cateCodeRef}"/>' readonly style="margin-right:10px;">
			 				    	<input id="cate" class="col-md-1 col-sm-4 form-control" type="text" name="cateCode" style="margin-left:10px;" value='<c:out value="${goods.cateCode}"/>' readonly>
								</div>
			
								  <div class="form-group row">
								    <label class="col-md-1 col-form-label">商品名</label>
								      <div class="col-md-6">
								      <input type="text" name="gdsName" class="form-control" value='<c:out value="${goods.gdsName}"/>' readonly>
								    </div>
								  </div>
			
								  <div class="form-group row">
								    <label class="col-md-1 col-form-label">価格</label>
								    <div class="col-md-2">
								      <input type="text" name="gdsPrice" class="form-control" value='<c:out value="${goods.gdsPrice }"/>' readonly>
								    </div>
								 
								    <label class="col-md-1 col-form-label">サイズ</label>
								    <div class="col-md-4" style="padding-top:5px;">
								      <input type="text" class="form-control" value='<c:out value="${goods.gdsSize }"/>' readonly>
								    </div>/
								  </div>
								
								<div class="form-group col-md-12 row">
									<label>商品説明</label> <textarea class="form-control" name="gdsDes" rows="10" readonly><c:out value="${goods.gdsDes}"/></textarea>
								</div>
												
								<div style="float:right;">
									<button data-oper='modify' class="btn btn-primary">修正</button>	
									<button data-oper='list' class="btn btn-info" style="margin-right:24px">リスト</button>										
								</div>	
							<form id='operForm' action="/admin/goods/modify" method="get">
								<input type='hidden' id='gdsNo' name='gdsNo' value='<c:out value="${goods.gdsNo }"/>'>
							</form>
						</div>
					</div>
				</div>
			</div>
			
			<div class='bigPictureWrapper'>
				<div class='bigPicture'>
				</div>
			</div>
			
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">イメージ</div>
						<div class="panel-body">
							<div class='uploadResult'>
								<ul style="list-style:none; padding-left:0px; margin-top:20px;">
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
		<jsp:include page="../include/footer.jsp" />
	</div>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="/resources/admin/vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="/resources/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="/resources/admin/js/demo/datatables-demo.js"></script>

	<script>
	
    var cateCodeRef = $("#gender").val();
	
	$(function(){
		if(cateCodeRef == "100"){
			$("#gender").val('男性');
		} else if(cateCodeRef == "200"){
			$("#gender").val("女性");
		} else if(cateCodeRef == "300"){
			$("#gender").val("共用");
		}
	});
	
	var cate = $("#cate").val();
	
 	$(function(){
		if(cate == "101"){
			$("#cate").val("トップス");
		}else if(cate == "102"){
			$("#cate").val("ズボン");
		}else if(cate == "103"){
			$("#cate").val("アウター");
		}else if(cate == "201"){
			$("#cate").val("トップス");
		}else if(cate == "202"){
			$("#cate").val("ズボン");
		}else if(cate == "203"){
			$("#cate").val("スカート");
		}else if(cate == "204"){
			$("#cate").val("ワンピース");
		}else if(cate == "301"){
			$("#cate").val("トップス");
		}else if(cate == "302"){
			$("#cate").val("ズボン");
		}else if(cate == "303"){
			$("#cate").val("アウター");
		}
	});

 </script>
 <script>
 	$(document).ready(function(){
 		var operForm = $("#operForm");
 		$("button[data-oper='modify']").on("click",function(e){
 			operForm.attr("action","/admin/goods/modify").submit();
	 	});
 		
 		$("button[data-oper='list']").on("click",function(e){
 			operForm.find("#gdsNo").remove();
 			operForm.attr("action","/admin/goods/list");
 			operForm.submit();
 		});
 		
 		(function(){
 			var gdsNo = '<c:out value="${goods.gdsNo}"/>';
 			$.getJSON("/admin/goods/getAttachList", {gdsNo:gdsNo}, function(arr){
 				console.log(arr);
 				
 				var str = "";
 				$(arr).each(function(i,attach){
 					if(attach.fileType){
						var fileCallPath = encodeURIComponent(attach.uploadPath+"/s_"+attach.uuid+"_"+attach.fileName);
						
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<img src='/display?fileName="+fileCallPath+"'>";
						str += "</div>";
						str += "</li>";
					} else{
						str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
						str += "<span>"+attach.fileName+"</span><br>";
						str += "<img src='/resources/img/attach.png'>";
						str += "</div>";
						str += "</li>";
					}
				});
				$(".uploadResult ul").html(str);
 			});
 		})();
		//添付ファイルをクリックした時に処理するイベントロジック
		$(".uploadResult").on("click","li",function(e){
			console.log("view image");
			var liObj = $(this);
			
			var path = encodeURIComponent(liObj.data("path")+"/"+liObj.data("uuid")+"_"+liObj.data("filename"));
			
			if(liObj.data("type")){
				showImage(path.replace(new RegExp(/\\/g),"/"));
			} else{
				//download
				self.location = "/download?fileName="+path;
			}
		});
		
		function showImage(fileCallPath){
			$(".bigPictureWrapper").css("display","flex").show();
			$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
							.animate({width: '100%', height: '100%'},1000);
		}
		//オリジナルイメージを閉じる
		$(".bigPictureWrapper").on("click", function(e){
			$(".bigPicture").animate({width:'0%', height: '0%'});
			setTimeout(function(){
				$(".bigPictureWrapper").hide();
			},1000);
		});
 	});
 </script>
</body>

</html>