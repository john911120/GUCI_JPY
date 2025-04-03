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

    <title>アドミン ページ</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

	<jsp:include page="include/menu.jsp" /> 

       <!-- Begin Page Content -->
       <div class="container-fluid">

           <!-- Content Row -->
           <div class="row">

               <!-- Earnings (Monthly) Card Example -->
               <div class="col-xl-3 col-md-6 mb-4">
                   <div class="card border-left-primary shadow h-100 py-2">
                       <div class="card-body">
                           <div class="row no-gutters align-items-center">
                               <div class="col mr-2">
                                   <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                       	累計 販売額</div>
                                   <div class="h5 mb-0 font-weight-bold text-gray-800"><fmt:formatNumber value="${totalIncome }" pattern="#,###" />ウォン</div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>

               <!-- Earnings (Monthly) Card Example -->
               <div class="col-xl-3 col-md-6 mb-4">
                   <div class="card border-left-success shadow h-100 py-2">
                       <div class="card-body">
                           <div class="row no-gutters align-items-center">
                               <div class="col mr-2">
                                   <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                       	本日の 販売額</div>
                                   <div class="h5 mb-0 font-weight-bold text-gray-800"><fmt:formatNumber value="${todayIncome }" pattern="#,###" />ウォン</div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>

               <!-- Earnings (Monthly) Card Example -->
               <div class="col-xl-3 col-md-6 mb-4">
                   <div class="card border-left-info shadow h-100 py-2">
                       <div class="card-body">
                           <div class="row no-gutters align-items-center">
                               <div class="col mr-2">
                                   <div class="text-xs font-weight-bold text-info text-uppercase mb-1">本日の 販売数量
                                   </div>
                                   <div class="row no-gutters align-items-center">
                                       <div class="col-auto">
                                           <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><c:out value="${todaySalesQuantity}"/>個</div>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>

               <!-- Pending Requests Card Example -->
               <div class="col-xl-3 col-md-6 mb-4">
                   <div class="card border-left-warning shadow h-100 py-2">
                       <div class="card-body">
                           <div class="row no-gutters align-items-center">
                               <div class="col mr-2">
                                   <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                       	問い合わせの数</div>
                                   <div class="h5 mb-0 font-weight-bold text-gray-800"><c:out value="${questionNo }"/>個</div>
                               </div>
                           </div>
                       </div>
                   </div>
               </div>
           </div>

           <!-- Content Row -->


        <!-- Begin Page Content -->
       <div class="container-fluid">

           <!-- Page Heading -->
           <h1 class="h3 mb-2 text-gray-800" style="margin-top:20px;">販売商品</h1><br>

           <!-- DataTales Example -->
           <div class="card shadow mb-4">
               <div class="card-body">
                   <div class="table-responsive">
                       <table class="table table-bordered" id="dataTable" width="100%" style="border-collapse: separate; border-spacing: 0;">
                           <thead>
                               <tr>
                                   <th>商品名</th>
                                   <th>数量</th>
                                   <th>価格</th>
                                   <th>購買者</th>
                                   <th>購買日</th>
                               </tr>
                           </thead>
                           <tbody>
            	                <c:forEach items="${latelyOrder}" var="goods">
                           	    <tr>
                                   <td><c:out value="${goods.gdsName }"/></td>
                                   <td><c:out value="${goods.stock }"/></td>
                                   <td><c:out value="${goods.price }"/></td>
                                   <td><c:out value="${goods.userId }"/></td>
								   <td><fmt:formatDate pattern="yyyy-MM-dd" value="${goods.orderDate }" /></td>
                               </tr>
 
                           </c:forEach>
                           
                           </tbody>
                       </table>
                   </div>
                </div>
                <!-- /.container-fluid -->
              </div>

            </div>
            <!-- End of Main Content -->
	</div>
	<jsp:include page="include/footer.jsp" />
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

</body>

</html>