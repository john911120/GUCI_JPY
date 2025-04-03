<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顧客情報修正</title>
<style>
.login_warn{
    margin-top: 30px;
    text-align: center;
    color : red;
}
</style>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<jsp:include page="../include/css.jsp" />
</head>
<body>
<jsp:include page="../include/header.jsp" />
<jsp:include page="../include/menu.jsp" />
<section id="aa-product-category">
    <div class="container">
      <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
		    <div class="row">
              <div class="col-md-9">
        <br><br><br> 
          <h4>パスワード確認</h4>
          <form class="aa-login-form" method="post">
            <input type="hidden" class="id_input" name="userId" value='<c:out value="${user.userId }"/>'>
            <label for="">パスワード</label>
            <input type="password" placeholder="パスワード" class="pw_input" name="userPw">
            
          
			<c:if test = "${result == 0 }">
                <div class = "login_warn">パスワード再確認お願い致します。</div>
            </c:if>
            
            <button class="aa-browse-btn" type="submit" style="margin: 0px; float:right;">ログイン</button>
            </form>
             <br><br><br><br> <br><br><br><br><br><br>
    </div></div></div>   
       

      </div>
    </div>
  </section>

  <script>
 
    /* ログインボータンクリックするとロジック*/
    $(".aa-browse-btn").click(function(){
        
    	/* ログインサーバーに要請する*/
        $(".aa-login-form").attr("action", "/user/mypageCheckPw");
        $(".aa-login-form").submit();
        
    });
 
</script>
<jsp:include page="../include/footer.jsp" />
</body>
</html>
  