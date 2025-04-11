<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
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
				<h2 style="margin:20px 15px;">FAQ</h2>				
				<div class="col-md-9">
					<c:if test="${user.userName == 'アドミン' }">
						<button type="button" class="btn btn-primary pull-right" id='regBtn'>記事を書く</button>	
					</c:if>
					  <table class="table table-hover">
					    <thead>
					      <tr>
					        <th class="col-md-1"></th>
					        <th class="col-md-3">分類</th>
					        <th class="col-md-8">タイトル</th>
					      </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${list}" var="faq">
					    	<tr>
					    		<td>Q</td>
					    		<td><c:out value="${faq.faqCate}" /></td>
					    		<td><a href='/faq/get?faqNo=<c:out value="${faq.faqNo}" />'><c:out value="${faq.faqTit}"/></a></td>
					    	</tr>
					    </c:forEach>
					    </tbody>
					  </table><br><br>
					  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Modal title</h4>
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
		</div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
          <aside class="aa-sidebar">
            <!-- single sidebar -->
            <div class="aa-sidebar-widget">
              <h3>カスタムセンター</h3>
              <ul class="aa-catg-nav">
				<li><a href="/notice/list">お知らせ</a></li>
                <li><a href="/faq/list" class="active">FAQ</a></li>
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
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/resources/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- Product view slider -->
  <script type="text/javascript" src="/resources/js/jquery.simpleGallery.js"></script>
  <script type="text/javascript" src="/resources/js/jquery.simpleLens.js"></script>
  <!-- slick slider -->
  <script type="text/javascript" src="/resources/js/slick.js"></script>  

	<script type="text/javascript">
	$(document).ready(function(){
		var result = '<c:out value="${result}"/>';
	    checkModal(result);
	    history.replaceState({},null,null);

		function checkModal(result){
			if(result === '' || history.state){
	    		return;
	    	}		
	    	if(parseInt(result) > 0){
	    		$(".modal-body").html("FAQが 登録されました。");
	    	}		
    		$("#myModal").modal("show");
    	}
	    $("#regBtn").on("click",function(){
	    	self.location="/faq/register";
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