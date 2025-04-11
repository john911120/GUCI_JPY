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
    <title>詳細ページ</title>
    
    <jsp:include page="../include/css.jsp" />
    
  </head>
  <body> 


<jsp:include page="../include/header.jsp" />  
 
<jsp:include page="../include/menu.jsp" /> 
    

 
  <!-- product category -->
  <section id="aa-product-details">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-product-details-area">
            <div class="aa-product-details-content">
              <div class="row">
                <!-- Modal view slider -->
                <div class="col-md-5 col-sm-5 col-xs-12">                              
                  <div class="aa-product-view-slider">                                
                    <div id="demo-1" class="simpleLens-gallery-container">
                      <div class="simpleLens-container">
                        <div class="simpleLens-big-image-container"><img src="/resources/admin/img/${goods.uploadPath }/${goods.uuid}_${goods.fileName}" class="simpleLens-big-image" style="width:275px; height:300px;"></div>
                      </div>
                    </div>
                  </div>
                </div>
                
                <!-- Modal view content -->
                <div class="col-md-7 col-sm-7 col-xs-12">
                  <div class="aa-product-view-content">
                  	<h3><c:out value="${goods.gdsName}"/></h3>
                    
                    <form role="form" method="post">
                    
          <input type="hidden" id="gdsName" name="gdsName" value='<c:out value="${goods.gdsName}"/>' readonly/>
                    <input type="hidden" id="gdsNo" name="gdsNo" value='<c:out value="${goods.gdsNo}"/>' />
                    </form>
                    <div class="aa-price-block">
                      <label>価格 : </label><span class="aa-product-view-price"><fmt:formatNumber value="${goods.gdsPrice }" pattern="#,###" />ウォン</span>
                    </div><br>
                    <label class="radio-inline col-sm-3 col-form-label">サイズ</label>
                <div class="col-sm-2">
                 <input type="radio" class="selSize" name="selSize" id="inlineRadio1" value="S" checked> S
                </div>
                <div class="col-sm-2">
                 <input type="radio" class="selSize" name="selSize" id="inlineRadio2" value="M"> M
                </div>
                <div class="col-sm-2">
                 <input type="radio" class="selSize" name="selSize" id="inlineRadio3" value="L"> L
                </div>
                <div class="col-sm-2">
                 <input type="radio" class="selSize" name="selSize" id="inlineRadio3" value="XL"> XL
                </div><br>


                     
                      <label class="radio-inline col-sm-3 col-form-label" style="margin-top:10px;">数量</label>					    
				    <div class="col-sm-6"style=" margin-top:10px;">
                        <div class="number">
							<span class="aa-cart-quantity" id="numberUpDown">1</span>
							<button id="increaseQuantity" style="background-color:transparent;"><b>+</b></button>
							<button id="decreaseQuantity" style="background-color:transparent;"><b>-</b></button>
							
						</div>
                    </div><br><br>
					<c:if test="${user != null }">
                      <a class="aa-add-to-cart-btn btn-primary" href="/cart" style="color:white;">カートに入れる</a>                    					
					</c:if>
					<c:if test="${user == null }">
                      <p>ログインをしてご購入お願い致します。</p>                    					
					</c:if>
				    <input name="id" type="hidden" value='<c:out value="${ cart.id }"/>' type="hidden" />

                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="aa-product-details-bottom">


             <!-- Tab panes -->
                <div class="row">
                	<div class="col-lg-12">


                		<h3>리뷰 (${pageMaker.total}個)</h3>
                	    <c:if test = "${user != null }">
                			<button id="regBtn" type="button" class="btn btn-primary pull-right" style="margin-bottom:10px;" onclick="location.href='/review/register?gdsNo=${goods.gdsNo}'">レビュー登録</button>
						</c:if>
                		
                		<table class="table table-striped table-bordered table-hover">
                		<thead>
                		<tr>
                		<th></th>
                			<th>イメージ</th>
                			<th>コンテンツ</th>
                			<th>作成者</th>
                			<th>作成日</th>
                			<th>レート</th>
                		</tr>
                		</thead>
                		<tbody>
                		<c:forEach items="${product_detail }" var="review">
                		<tr>
                		<%-- <td><c:out value="${review.revNo}"></c:out></td> --%>
                		<td>
                		<c:if test="${user.userId == review.userId }">
	                		<button type="submit" data-oper='modify' class="btn btn-warning" onclick="location.href='/review/modify?revNo=<c:out value="${review.revNo }"/> '">修正</button>
                		</c:if>
                		
                		<form id='operForm' action="/review/modify" method="get">
                			<input type='hidden' id='gdsNo' name='gdsNo' value='<c:out value="${review.gdsNo}"/>'>
                			<input type='hidden' id='revNo' name='revNo' value='<c:out value="${review.revNo}"/>'>
			        		<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
			       			<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
                		
                		</form>
                			<td><div class="inputArea">

							 <img src="/resources/review/img/${review.uploadPath }/${review.uuid}_${review.fileName}" alt="image" style="width:100px; height:100px" class="oriImg"/>
							 </div>
							 </td>
                			<td><c:out value="${review.revCon }"></c:out></td>
                			<td><c:out value="${review.userId }"></c:out></td>
                			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${review.revDate }"/></td>
                			<td><c:out value="${review.score }"></c:out></td>
                		</tr>
                		
                		</c:forEach>
                		
                		</tbody>
                		</table>
                		<div class='pull-right'>
                			<ul class='pagination'>
                				<c:if test="${pageMaker.prev}">
                					<li class="paginate_button previous"><a href="${pageMaker.startPage -1 }">以前</a></li>
                				</c:if>
                				<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
                					<li class="paginate_button ${pageMaker.cri.pageNum==num?"active":"" }"><a href="${num}">${num}</a></li>
                				</c:forEach>
                				<c:if test="${pageMaker.next }">
                					<li class="paginate_button next"><a href="${pageMaker.endPage+1 }">次</a></li>
                				</c:if>
                			</ul>
                		</div>

	                <form id='actionForm' action="/review/product_detail" method='get'>                		
						<input type='hidden' name='gdsNo' value='<c:out value="${goods.gdsNo }"/>'>
						<input type='hidden' name='pageNum' value='<c:out value="${pageMaker.cri.pageNum }" />'>
						<input type='hidden' name='amount' value='<c:out value="${pageMaker.cri.amount}" />'>
					</form>
                	
                	</div>
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
  <script type="text/javascript" src="/resources/js/nouislider.js"></script>
  <!-- Custom js -->
  <script src="/resources/js/custom.js"></script> 
   
 <script>
 
  $(".aa-add-to-cart-btn").click(function(){

   var gdsNo = $("#gdsNo").val();
   var gdsName =$("#gdsName").val();
   var cartStock = $(".aa-cart-quantity").html();
   var selSize = $('input[name="selSize"]:checked').val();
   
   var data = {
      
     gdsNo : gdsNo,
     gdsName : gdsName,
     cartStock : cartStock,
     selSize : selSize
     
     };
   
   $.ajax({
    url : "/addCart",
    type : "post",
    data : data,
    success : function(result){
     alert("成功しました");
     $(".aa-cart-quantity").val("1");
    },
    error : function(){
     alert("失敗しました");
    }
   });
  });
 </script>
<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
		checkModal(result);
// 311		
		history.replaceState({}, null, null);
		
 /* 248 */
		function checkModal(result) {
			if (result === '' || history.state) {
				return;
			}
			if (parseInt(result) > 0) {
				$(".modal-body").html(
						"掲示板記事 " + parseInt(result)
								+ " 番が 登録しました。");
			}

			$("#myModal").modal("show");
		}
/* 		$("#regBtn").on("click", function() {
			self.location = "/review/register";
		}); */
//311
		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click",	function(e) {
					e.preventDefault();
					console.log('click');
					actionForm.find("input[name='pageNum']").val($(this).attr("href"));
					actionForm.submit();
		});

		
		$(function(){
			$('#decreaseQuantity').click(function(e){
			e.preventDefault();
			var stat = $('#numberUpDown').text();
			var num = parseInt(stat,10);
			num--;
			if(num<=0){
			alert('これ以上は減らしません。');
			num =1;
			}
			$('#numberUpDown').text(num);
			});
			$('#increaseQuantity').click(function(e){
			e.preventDefault();
			var stat = $('#numberUpDown').text();
			var num = parseInt(stat,10);
			num++;
			$('#numberUpDown').text(num);
			});
			});

});

</script>
  <jsp:include page="../include/footer.jsp" />
   
  </body>
</html>