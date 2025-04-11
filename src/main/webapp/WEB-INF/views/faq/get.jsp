<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ見る</title>
<jsp:include page="../include/css.jsp" />
    
  </head>
  <body>  
 
<jsp:include page="../include/header.jsp" />
 
<jsp:include page="../include/menu.jsp" /> 
<section id="aa-product-category">
   <div class="container">
      <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
	<div class="container">	
		<div class="row"  style="margin-left:10px">
			<div class="col-md-8">
				<h1 class="page-header" style="color:#4B89DC;">FAQ見る</h1>
			</div>
		</div>

		<div class="col-md-8">
			<div class="panel " style="margin:0 20px;">
				<div class="panel-body">
					
					<div class="form-group col-md-4">
					  <label for="category">カテゴリー</label><input class="form-control" name="faqCate" value='<c:out value="${faq.faqCate}" />' readonly="readonly">
					</div>

					<div class="form-group col-md-12">
						<label>タイトル</label> <input class="form-control" name="faqTit" value='<c:out value="${faq.faqTit}" />' readonly="readonly">
					</div>
											
					<div class="form-group col-md-12">
						<label>コンテンツ</label> <textarea class="form-control" rows="10" name='faqCon' readonly="readonly"><c:out value="${faq.faqCon}"/></textarea>
					</div>
					
					<div class="form-group col-md-12">
						<button data-oper="list" class="btn btn-info pull-right" onclick="location.href='/faq/list'">リスト</button>	
						<c:if test="${user.userName == 'アドミン' }">
							<button data-oper="modify" class="btn btn-primary pull-right" style="margin-right:5px;" onclick="location.href='/faq/modify?faqNo=<c:out value="${faq.faqNo}"/>'">修正</button>							
						</c:if>
					</div>	
					<form id='operForm' action="/faq/modify" method="get">
						<input type='hidden' id='faqNo' name='faqNo' value='<c:out value="${faq.faqNo }"/>'>
					</form>		
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
                <li><a id="question" href="/question/list">問い合わせする</a></li>
              </ul>
            </div>
            <!-- single sidebar -->
          </aside>
        </div>
       
      </div>
    </div>
</section>
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/resources/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- slick slider -->
  <script type="text/javascript" src="/resources/js/slick.js"></script>

<script>
	$(document).ready(function(){
		var operForm = $("#operForm");
		$("button[data-oper='modify']").on("click",function(e){
		operForm.attr("action","/faq/modify").submit();
		});
		
		$("button[data-oper='list']").on("click",function(e){
			operForm.find("#faqNo").remove(); 
			operForm.attr("action","/faq/list");
			operForm.submit();
		});
		
		$("#question").on("click",function(e){
			var user = '<c:out value="${user.userId}"/>';
			console.log(user);
			if(user == null || user == ''){
				e.preventDefault();
				alert("ログインしてください");
				e.preventDefault();
			}
		});
	});
</script>

    <jsp:include page="../include/footer.jsp" />

</body>
</html>