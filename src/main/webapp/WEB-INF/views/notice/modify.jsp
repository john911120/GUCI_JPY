<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

   <div class="col-md-9">
      <div class="panel panel-default">
         <div class="panel-heading">Board Modify</div>
         <div class="panel-body">
              <form role="form" action="/notice/modify" method="post">
        <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
        <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>       
        <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'> 
      <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
      
 
<div class="form-group">
  <label>No</label> 
  <input class="form-control" name='noticeNo' 
     value='<c:out value="${notice.noticeNo}"/>' readonly="readonly">
</div>

<div class="form-group">
  <label>Title</label> 
  <input class="form-control" name='noticeTit' 
    value='<c:out value="${notice.noticeTit}"/>' >
</div>

<div class="form-group">
  <label>Text area</label>
  <textarea class="form-control" rows="3" name='noticeCon' ><c:out value="${notice.noticeCon}"/></textarea>
</div>

<div class="form-group">
  <label>Writer</label> 
  <input class="form-control" name='noticeWri'
    value='<c:out value="${notice.noticeWri}"/>' readonly="readonly">            
</div>

<div class="form-group">
  <label>noticeDate</label> 
  <input class="form-control" name='noticeDate'
    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${notice.noticeDate}" />'  readonly="readonly">            
</div>

          
<c:if test="${user.userName == 'アドミン'}">
  <button type="submit" data-oper='modify' class="btn btn-default">修正</button>
  <button type="submit" data-oper='remove' class="btn btn-danger">削除</button>
</c:if>
  <button type="submit" data-oper='list' class="btn btn-info">リスト</button>
  </form>
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
              <h3>カテゴリー</h3>
              <ul class="aa-catg-nav">
            <li><a href="/notice/list">お知らせ</a></li>
                <li><a href="/faq/list" class="active">FAQ</a></li>
            <li><a href="/question/list">問い合わせする</a></li>
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
$(document).ready(function() {

	  var formObj = $("form");

	  $('button').on("click", function(e){
	    
	    e.preventDefault(); 
	    
	    var operation = $(this).data("oper");
	    
	    console.log(operation);
	    
	    if(operation === 'remove'){
	      formObj.attr("action", "/notice/remove");
	      
	    }else if(operation === 'list'){
	    		      
 	      formObj.attr("action", "/notice/list").attr("method","get");
	      
	    }
	    
	    formObj.submit();
	  });

});
</script>

   <jsp:include page="../include/footer.jsp" />



  </body>
</html>