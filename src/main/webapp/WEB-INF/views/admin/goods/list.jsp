<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>商品管理</title>

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
            <h1 class="h3 mb-2 text-gray-800">全体 商品リスト</h1><br>

            <!-- DataTales Example -->
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
						<button class="btn btn-primary" type="button" style="float:right;" onclick="location.href='/admin/goods/register'">商品登録</button><br><br>
                         <!-- <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0"> -->
                         <table class="table table-bordered" id="dataTable" width="100%" style="border-collapse: separate; border-spacing: 0;">
                            <thead>
                                <tr>
                                    <th>商品番号</th>
                                    <th>カテゴリー</th>
                                    <th>商品名</th>
                                    <th>価格</th>
                                    <th>登録日</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${list}" var="goods">
                            		<tr>
                            			<td><c:out value="${goods.gdsNo }" /></td>
                            			<td><c:out value="${goods.cateName }" /></td>
                            			<td><a href='/admin/goods/get?gdsNo=<c:out value="${goods.gdsNo}"/>' style="color:#858796;"><c:out value="${goods.gdsName }" /></a></td>
                            			<td><c:out value="${goods.gdsPrice }" />원</td>
                            			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${goods.gdsDate }"/></td>
                            		</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                      
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									</div>
									<div class="modal-body">無事に処理致しました。</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-dismiss="modal">閉じる</button>
									</div>
								</div>
							</div>
						</div>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->
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

	<script type="text/javascript">
		$(document).ready(function(){
			var result = '<c:out value="${result}"/>';
		    checkModal(result);
		    history.replaceState({},null,null);
			// 登録されていないなら、なにも見せない。登録された場合は、何番が登録されてるのかをモーダル画面でお知らせする。
		    function checkModal(result){		
		    	if(result === '' || history.state){
		    		return;
		    	}		
		    	if(parseInt(result) > 0){
		    		$(".modal-body").html("商品が無事登録されました");
		    	}		
	    		$("#myModal").modal("show");
	    	}
	    });
	</script>
</body>

</html>