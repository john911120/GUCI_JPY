<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 登録</title>
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
				<h1 class="page-header" style="color:#4B89DC;">FAQ 登録</h1>
			</div>
		</div>

		<div class="col-md-8">
			<div class="panel " style="margin:0 20px;">
				<div class="panel-body">
				
					<form role="form" action="/faq/register" method="post">
						<div class="form-group col-md-4">
						  <label>カテゴリー</label>
						  <select class="form-control" name="faqCate">
							<option>配送の問い合わせ</option>
							<option>交換、返品</option>
							<option>商品の問い合わせ</option>
							<option>注文決済</option>
							<option>ほかの問い合わせ</option>
						  </select>
						</div>
	
						<div class="form-group col-md-12">
							<label>タイトル</label> <input class="form-control" name="faqTit" required>
						</div>
												
						<div class="form-group col-md-12">
							<label>コンテンツ</label> <textarea class="form-control" rows="10" name='faqCon' required></textarea>
						</div>
						
						<div class="form-group col-md-12">
							<button type="reset" class="btn btn-danger pull-right" onclick="location.href='/faq/list'">取り消し</button>	
							<c:if test="${user.userName == '관리자' }">
								<button type="submit" class="btn btn-primary pull-right" style="margin-right:5px;">登録する</button>						
							</c:if>
						</div>				
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
              <h3>카테고리</h3>
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
           <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/resources/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- slick slider -->
  <script type="text/javascript" src="/resources/js/slick.js"></script>



    <jsp:include page="../include/footer.jsp" />

</body>
</html>