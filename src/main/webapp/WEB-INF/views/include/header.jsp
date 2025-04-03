<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- Start header section -->
  <header id="aa-header">
    <!-- start header top  -->
    <div class="aa-header-top">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="aa-header-top-area">
              <div class="aa-header-top-right">
                <ul class="aa-head-top-nav-right">
                  <c:if test = "${user != null }">
                    <li class="hidden-xs"><a href="/cart">カート</a></li>
                  	<li class="hidden-xs"><a href="/user/mypageCheckPw">情報修正</a></li>
  				  </c:if>
				<c:if test="${user.userName == 'アドミン'}">
				  <li class="hidden-xs"><a href="/admin/index">アドミンページ</a></li>
				</c:if>
                      <c:if test = "${user == null }">
                  <li class="hidden-xs"><a href="/user/login">ログイン</a></li></c:if> 
                    <c:if test="${ user != null }">
            	<span> ${user.userName}</span>
            	<span>/</span>
            	<span><a href="/user/logout">ログアウト</a></span></c:if>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- / header top  -->

    <!-- start header bottom  -->
    <div class="aa-header-bottom">
      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <div class="aa-header-bottom-area">
              <!-- logo  -->
              <div class="aa-logo">
                <!-- Text based logo -->
                <a href="/">
                  <span class="fa fa-shopping-cart"></span>
                  <p>GUCI <span>最低価格 オンラインショウピングモール</span></p>
                </a>
                <!-- img based logo -->
                <!-- <a href="index.html"><img src="img/logo.jpg" alt="logo img"></a> -->
              </div>
              <!-- / logo  -->
              <!-- search box -->
              <div class="aa-search-box">
                <form action="/search_product" id="searchForm1">
                  <input type="text" name="keyword" placeholder="検索語をご入力ください。">
                  <input type="hidden" name="pageNum" value="1">
                  <input type="hidden" name="amount" value="9">
                  <button type="submit"><span class="fa fa-search"></span></button>
                </form>
              </div>
              <!-- / search box -->             
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- / header bottom  -->
  </header>
  <!-- / header section -->
