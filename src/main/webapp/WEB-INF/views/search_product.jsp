<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
    
<jsp:include page="include/css.jsp" />
    
  </head>
  <body>  
 
<jsp:include page="include/header.jsp" />
 
<jsp:include page="include/menu.jsp" /> 

  <!-- product category -->
  <section id="aa-product-category">
    <div class="container">
      <div class="row">
        <div class="col-lg-10 col-md-9 col-sm-8">
          <div class="aa-product-catg-content">
            <h2>'<c:out value="${pageMaker6.cri.keyword}"/>'に 対する検索結果です。</h2>
            <div class="aa-product-catg-body">
              <ul class="aa-product-catg">
				<c:forEach items="${searchProduct}" var="searchProduct">
	                <li style="margin-left:0px;">
	                  <figure>
	                    <a class="aa-product-img" href='/review/product_detail?gdsNo=<c:out value="${searchProduct.gdsNo}" />'>
	                    <img src="/resources/admin/img/${searchProduct.uploadPath }/${searchProduct.uuid}_${searchProduct.fileName}" alt="polo shirt img" style="width:250px; height:300px;"></a>
	                    <figcaption>
	                      <h4 class="aa-product-title"><a href="/review/product_detail?gdsNo=<c:out value="${searchProduct.gdsNo}" />"><c:out value="${searchProduct.gdsName}" /></a></h4>
	                      <span class="aa-product-price"><fmt:formatNumber value="${searchProduct.gdsPrice }" pattern="#,###" />ウォン</span>
	                    </figcaption>
	                  </figure>                         
	                </li>
				</c:forEach>
			  </ul>
			</div>
            <div class="aa-product-catg-pagination">
              <nav>
                <ul class="pagination">
					<c:if test="${pageMaker6.prev}">
						<li class="paginate_button previous"><a href='<c:out value="${pageMaker6.startPage -1}"/>'>以前</a></li>
					</c:if>
					
					<c:forEach var="num" begin="${pageMaker6.startPage}" end="${pageMaker6.endPage}">
						<li class="paginate_button ${pageMaker6.cri.pageNum == num ? 'active':''}"><a href="${num}"><c:out value="${num}"/></a></li>
					</c:forEach>
					
					<c:if test="${pageMaker6.next}">
						<li class="paginate_button next"><a href='<c:out value="${pageMaker6.endPage + 1}"/>'>次</a></li>
					</c:if>
                </ul>
           		<form id='actionForm' action="/search_product" method='get'>
					<input type='hidden' name='pageNum' value='<c:out value="${pageMaker6.cri.pageNum}"/>'>
					<input type='hidden' name='amount' value='<c:out value="${pageMaker6.cri.amount}"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${pageMaker6.cri.keyword }"/>'>
				</form>
              </nav>
            </div>
          </div>
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
  <!-- Price picker slider -->
  
  <script>
	var actionForm = $("#actionForm");
	$(".paginate_button a").on("click", function(e){
		console.log(actionForm.find("input[name='keyword']").val());
		console.log("1");
		e.preventDefault();
		console.log("click");
		actionForm.find("input[name='pageNum']").val($(this).attr("href"));
		actionForm.find("input[name='amount']").val(9);
		actionForm.submit();
	});
  
    var searchForm1 = $("#searchForm1");
  
  	$("#searchForm1 button").on("click",function(e){
  		if(!searchForm1.find("input[name='keyword']").val()){
  			alert("키워드를 입력하세요");
  			return false;
  		}
  		
  		searchForm1.find("input[name='pageNum']").val("1");
  		e.preventDefault();
  		searchForm1.submit();
  	});
  </script>
	

   <jsp:include page="include/footer.jsp" />
</body>
</html>