<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>お知らせ</title>

<jsp:include page="../include/css.jsp" />

</head>
<body>

   <jsp:include page="../include/header.jsp" />

   <jsp:include page="../include/menu.jsp" />

   <!-- product category -->
   <section id="aa-product-category">
      <div class="container">
         <div class="row">
            <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
               <div class="container">
                  <div class="row">
                     <h2 style="margin: 20px 15px;">お知らせ</h2>
                     <div class="col-md-9">
                     	<c:if test="${user.userName == 'アドミン' }">
	                        <button class="btn btn-primary pull-right" type="button" id="regBtn">記事を書く</button>                 	
                     	</c:if>
                        <table class="table table-hover">
                           <thead>
                              <tr>
                                 <th class="col-md-1">番号</th>
                                 <th class="col-md-7">タイトル</th>
                                 <th class="col-md-2">作成者</th>
                                 <th class="col-md-2">作成日</th>
                              </tr>
                           </thead>

                           <c:forEach items="${list}" var="notice">
                              <tr>
                                 <td><c:out value="${notice.noticeNo}" /></td>
                                 <td><a class='move' href='<c:out value="${notice.noticeNo}"/>'>            
                              <c:out value="${notice.noticeTit}" /></a></td>
                                 <td><c:out value="${notice.noticeWri }" /></td>
                                 <td><fmt:formatDate pattern="yyyy-MM-dd"
                                       value="${notice.noticeDate }" /></td>
                              </tr>
                           </c:forEach>
                        </table>

  <div class='row'>
               <div class="col-lg-12">
                  <form id='searchForm' action="/notice/list" method='get'>
                     <select name='type'>
                        <option value=""
                           <c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
                        <option value="T"
                           <c:out value="${pageMaker.cri.type eq 'T'?'selected':''}"/>>タイトル</option>
                        <option value="C"
                           <c:out value="${pageMaker.cri.type eq 'C'?'selected':''}"/>>コンテンツ</option>
                        <option value="W"
                           <c:out value="${pageMaker.cri.type eq 'W'?'selected':''}"/>>作成者</option>
                        <option value="TC"
                           <c:out value="${pageMaker.cri.type eq 'TC'?'selected':''}"/>>タイトル or コンテンツ</option>
                        <option value="TW"
                           <c:out value="${pageMaker.cri.type eq 'TW'?'selected':''}"/>>タイトル or 作成者</option>
                        <option value="TWC"
                           <c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>タイトル or コンテンツ or 作成者</option>
                     </select> 
                     <input type='text' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>' /> 
                     <input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum}"/>' /> 
                     <input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}"/>' />
                     <button class='btn btn-default'>Search</button>
                  </form>
               </div>
            </div>
            

	 	<div class='pull-right'>
			<ul class="pagination">



			<c:if test="${pageMaker.prev}">
			   <li class="paginate_button previous">
			       <a href="${pageMaker.startPage -1}">Previous</a></li>
			</c:if>

			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">${num}</a>
							</li>
			</c:forEach>

			<c:if test="${pageMaker.next}">
				<li class="paginate_button next"><a	href="${pageMaker.endPage +1 }">Next</a></li>
			</c:if> 
		</ul>
		</div>
				<!--  end Pagination -->
		</div>	

		<form id='actionForm' action="/notice/list" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>

				<input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'> 
				<input type='hidden' name='keyword' value='<c:out value="${ pageMaker.cri.keyword }"/>'>
		</form>	
            

         <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
            aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">&times;</button>
                     <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                  </div>
                  <div class="modal-body">処理しました。</div>
                  <div class="modal-footer">
                     <button type="button" class="btn btn-danger" data-dismiss="modal">閉じる</button>
                  </div>
               </div>
               <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
         </div>
         <!-- /.modal -->   
                     </div>
                  </div>
               </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
               <aside class="aa-sidebar">
                  <!-- single sidebar -->
                  <div class="aa-sidebar-widget">
                     <h3>カスタムセンター</h3>
                     <ul class="aa-catg-nav">
                        <li><a href="/notice/list" class="active">お知らせ</a></li>
                        <li><a href="/faq/list">FAQ</a></li>
                        <li><a id="question" href="/question/list">問い合わせする</a></li>
                        
                     </ul>
                  </div>
                  <!-- single sidebar -->
               </aside>
            </div>
     </div>
         </div>
   </section>
   <!-- / product category -->

   <!-- jQuery library -->
   <script
      src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
   <!-- Include all compiled plugins (below), or include individual files as needed -->
   <script src="/resources/js/bootstrap.js"></script>
   <!-- SmartMenus jQuery plugin -->
   <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
   <!-- SmartMenus jQuery Bootstrap Addon -->
   <script type="text/javascript"
      src="/resources/js/jquery.smartmenus.bootstrap.js"></script>
   <!-- Product view slider -->
   <script type="text/javascript"
      src="/resources/js/jquery.simpleGallery.js"></script>
   <script type="text/javascript" src="/resources/js/jquery.simpleLens.js"></script>
   <!-- slick slider -->
   <script type="text/javascript" src="/resources/js/slick.js"></script>


 <script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
		checkModal(result);
	
		history.replaceState({}, null, null);
		

		function checkModal(result) {
			if (result === '' || history.state) {
				return;
			}
			if (parseInt(result) > 0) {
				$(".modal-body").html(
						"処理しました。");
			}

			$("#myModal").modal("show");
		}
		$("#regBtn").on("click", function() {
			self.location = "/notice/register";
		});

		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click",	function(e) {
					e.preventDefault();
					console.log('click');
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					actionForm.submit();
		});
	
		$(".move").on("click",function(e) {
					e.preventDefault();
					actionForm.append("<input type='hidden' name='noticeNo' value='"	+ $(this).attr("href")	+ "'>");
					actionForm.attr("action","/notice/get");
					actionForm.submit();
		});
		

		var searchForm = $("#searchForm");

		$("#searchForm button").on(	"click",function(e) {
					if (!searchForm.find("option:selected").val()) {
						alert("検索類を選択してください。");
						return false;
					}

					if (!searchForm.find("input[name='keyword']").val()) {
						alert("キーワードを入力してください");
						return false;
					}

					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();

					searchForm.submit();
		});
		
		$("#question").on("click",function(e){
			var user = '<c:out value="${user.userId}"/>';
			console.log(user);
			if(user == null || user == ''){
				e.preventDefault();
				alert("ログインをしてください");
				e.preventDefault();
			}
		});
});
		
</script>
   <jsp:include page="../include/footer.jsp" />



</body>
</html>