<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
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
 <div class="container">
      <div class="row">
      <div class="col-md-offset-2 col-md-8">
        <br><br><br>
          <h4>ログイン</h4>
          <form class="aa-login-form" method="post">
            <label for="">ID<span>*</span></label>
            <input type="text" placeholder="ID" class="id_input" name="userId">
            <label for="">パスワード<span>*</span></label>
            <input type="password" placeholder="パスワード" class="pw_input" name="userPw">
            
          
			<c:if test = "${result == 0 }">
                <div class = "login_warn">ユーザー ID もしくは、 パスワードをご確認お願い致します。</div>
            </c:if>
            
            <button class="aa-browse-btn" type="submit" style="margin: 0px; float:right;">ログイン</button>
            </form>
  			  <p class="aa-lost-password"><button id="searchId" style="background-color:transparent; border:none;">ID</button> / <button id="searchPw" style="background-color:transparent; border:none;">PW</button>探し<a href="/user/join" style="margin-left: 20px;">会員加入</a></p>
              <div class="aa-register-now" style="margin: 0px;">
              
            <br><br><br><br><br>
            </div>
          
      </div>
</div>
</div>

  <script>
    $(document).ready(function(){
      $("#searchId").on("click", function(e){
				e.preventDefault();
				$(this).attr("method","get");
				window.open("/user/searchId","","width=600,height=600");
			});

			$("#searchPw").on("click", function(e){
				e.preventDefault();
				$(this).attr("method","get");
				window.open("/user/searchPw","","width=600,height=600");
			});
    });
  </script>
  
  <script>
 
    /* ログインボータンクリックするとロジック*/
    $(".aa-browse-btn").click(function(){
        
    	/* ログインサーバーに要請する*/
        $(".aa-login-form").attr("action", "/user/login");
        $(".aa-login-form").submit();
        
    });
 
</script>
<jsp:include page="../include/footer.jsp" />
</body>
</html>
  