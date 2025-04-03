<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>配送変更</title>

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

	 <jsp:include page="include/menu.jsp" /> 


        <!-- Begin Page Content -->
        <div class="container-fluid">

            <!-- Page Heading -->
            <h1 class="h3 mb-2 text-gray-800">配送変更</h1><br>

            <!-- DataTales Example -->
            <div class="card shadow mb-4">
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>商品名</th>
                                    <th>価格</th>
                                    <th>購買者</th>
                                    <th>購買日</th>
                                    <th>配送状態</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Tiger Nixon</td>
                                    <td>Edinburgh</td>
                                    <td>61</td>
                                    <td>2011/04/25</td>
                                    <td style="padding:10px 5px 0 5px;">
										<div class="form-group">
					 				    	<select class="custom-select">
					 				    		<!-- デフォルトを選択したときに アラットが出るように Javascript処理 -->
										        <option value="0">購買完了</option>
										        <option value="1">配送中</option>
										        <option value="1">配送完了</option>
									    	</select>
								    	</div>
									</td>
                                    <td>
                                    	<button type="button" class="btn btn-primary">確認</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tiger Nixon</td>
                                    <td>Edinburgh</td>
                                    <td>61</td>
                                    <td>2011/04/25</td>
                                    <td style="padding:10px 5px 0 5px;">
										<div class="form-group">
					 				    	<select class="custom-select">
					 				    		<!-- デフォルトを選択したときに アラットが出るように Javascript処理 -->
										        <option value="0">購買完了</option>
										        <option value="1">配送中</option>
										        <option value="1">配送完了</option>
									    	</select>
								    	</div>
									</td>
                                    <td>
                                    	<button type="button" class="btn btn-primary">確認</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Tiger Nixon</td>
                                    <td>Edinburgh</td>
                                    <td>61</td>
                                    <td>2011/04/25</td>
                                    <td style="padding:10px 5px 0 5px;">
										<div class="form-group">
					 				    	<select class="custom-select">
					 				    		<!-- デフォルトを選択したときに アラットが出るように Javascript処理 -->
												<option value="0">購買完了</option>
												<option value="1">配送中</option>
												<option value="1">配送完了</option>
									    	</select>
								    	</div>
									</td>
                                    <td>
                                    	<button type="button" class="btn btn-primary">確認</button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Garrett Winters</td>
                                    <td>Tokyo</td>
                                    <td>63</td>
                                    <td>2011/07/25</td>
                                    <td style="padding:10px 5px 0 5px;">
										<div class="form-group">
					 				    	<select class="custom-select">
					 				    		<!-- デフォルトを選択したときに アラットが出るように Javascript処理 -->
												<option value="0">購買完了</option>
												<option value="1">配送中</option>
												<option value="1">配送完了</option>
									    	</select>
								    	</div>
									</td>
                                    <td>
                                    	<button type="button" class="btn btn-primary">確認</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

		<jsp:include page="include/footer.jsp" />

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