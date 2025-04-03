<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <title>男性衣類</title>
      
<jsp:include page="include/css.jsp" />
    
  </head>
  <body class="productPage">  
 
<jsp:include page="include/header.jsp" />
 
<jsp:include page="include/menu.jsp" /> 

  <!-- product category -->
  <section id="aa-product-category">
    <div class="container">
      <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
          <div class="aa-product-catg-content">
            <h2>남성 의류</h2>
            <div class="aa-product-catg-body">
              <ul class="aa-product-catg">
				<c:forEach items="${manProduct}" var="manProduct">
	                <li style="margin-left:0px;">
	                  <figure>
	                    <a class="aa-product-img" href='/review/product_detail?gdsNo=<c:out value="${manProduct.gdsNo}" />'>
	                    <img src="/resources/admin/img/${manProduct.uploadPath }/${manProduct.uuid}_${manProduct.fileName}" alt="polo shirt img" style="width:250px; height:300px;"></a>
	                    <figcaption>
	                      <h4 class="aa-product-title"><a href='/review/product_detail?gdsNo=<c:out value="${manProduct.gdsNo}" />'><c:out value="${manProduct.gdsName}" /></a></h4>
	                      <span class="aa-product-price"><fmt:formatNumber value="${manProduct.gdsPrice }" pattern="#,###" />ウォン</span>
	                    </figcaption>
	                  </figure>                         
	                </li>
				</c:forEach>
              </ul>
            </div>
            <div class="aa-product-catg-pagination">
              <nav>
                <ul class="pagination">
					<c:if test="${pageMaker3.prev}">
						<li class="paginate_button previous"><a href='<c:out value="${pageMaker3.startPage -1}"/>'>以前</a></li>
					</c:if>
					
					<c:forEach var="num" begin="${pageMaker3.startPage}" end="${pageMaker3.endPage}">
						<li class="paginate_button ${pageMaker3.cri.pageNum == num ? 'active':''}"><a href="${num}"><c:out value="${num}"/></a></li>
					</c:forEach>
					
					<c:if test="${pageMaker3.next}">
						<li class="paginate_button next"><a href='<c:out value="${pageMaker3.endPage + 1}"/>'>次</a></li>
					</c:if>
                </ul>
           		<form id='actionForm' action="/manProduct" method='get'>
					<input type='hidden' name='pageNum' value='<c:out value="${pageMaker3.cri.pageNum}"/> '>
					<input type='hidden' name='amount' value='<c:out value="${pageMaker3.cri.amount}"/> '>
					<input type='hidden' name='type' value='<c:out value="${pageMaker3.cri.type}"/>'>
				</form>
              </nav>
            </div>
          </div>
        </div>
        <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
          <aside class="aa-sidebar">
            <!-- single sidebar -->
            <div class="aa-sidebar-widget">
              <h3>カテゴリー</h3>
              <form id='searchForm' action="/manProduct" method='get'>
				<input type="radio" name="type" value="トップス">トップス<br>
				<input type="radio" name="type" value="ズボン">ズボン<br>
				<input type="radio" name="type" value="アウター">アウター<br>
				
				<input type='hidden' name='pageNum' value='<c:out value="${pageMaker3.cri.pageNum}" />' />
				<input type='hidden' name='amount' value='9' />
				<button class="btn btn-primary">選択</button>
			  </form>
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
  
	<script>
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			console.log("click");
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.find("input[name='amount']").val(9);
			actionForm.submit();
		});
		
		var searchForm = $("#searchForm");
		$("#searchForm button").on("click", function(e){
			searchForm.find("input[name='pageNum']").val("1");
			e.preventDefault();
			searchForm.submit();	 
		});
	</script>


   <jsp:include page="include/footer.jsp" />
  </body>
</html>