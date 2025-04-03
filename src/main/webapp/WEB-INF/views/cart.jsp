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
    <title>カート</title>
    
	<jsp:include page="include/css.jsp" />
	    
	  </head>
	  <body> 
	
	<jsp:include page="include/header.jsp" />
	 
	<jsp:include page="include/menu.jsp" /> 

 <!-- Cart view section -->
 
 
  <section id="cart-view">

   
   <div class="container">
     <div class="row">
       <div class="col-md-12">
         <h2 style="margin-top:20px;">カート</h2>
         <div class="cart-view-area">
           <div class="cart-view-table">
             <form action="/deleteCart" method="post">

               <div class="table-responsive">
               <span class="allCheck">
               	<input type="checkbox" name="allCheck" id="allCheck"/><label for="allCheck">全部選択</label>
               </span>
               <span class="delBtn">
               	<button type="button" class="selectDelete_btn pull-right btn-danger" style="margin-bottom:10px; border-radius: 8px;">選択削除</button>
               </span>
               
                  
                  <table class="table">
                  <thead>
                      <tr>
                       	<th>商品イメージ</th>
                        <th>商品名</th>   
                        <th>商品サイズ</th>
                        <th>一個あたりの価格</th>
                        <th>購入数量</th>
                        <th>総金額</th>
                        <th>商品削除</th>
                        
                        </tr>
                        </thead>
                       

                       
                    <tbody>
                     <c:forEach var="row" items="${map.list}" varStatus="i">
<input type="hidden" id="gdsNo" name="gdsNo" value="${row.gdsNo}" readonly/>
                     <tr>
                     <td><img src="/resources/admin/img/${row.uploadPath }/${row.uuid}_${row.fileName}" class="simplelens-big-image"  style="width:100px; height:100px;"></td>
                 
                     
                       <td><c:out value="${row.gdsName }"/></td>

                       <td><c:out value="${row.selSize}"/></td>
                       <td><fmt:formatNumber pattern="###,###,###" value="${row.gdsPrice}" />ウォン</td>
                       <td><c:out value="${row.cartStock}"/></td>
                       <td><fmt:formatNumber pattern="###,###,###" value="${row.gdsPrice * row.cartStock}" /> ウォン</td>
                       <td><input type="checkbox" name="chBox" class="chBox" data-cartNo="${row.cartNo }"></td>
                       
                       </tr>
                      </c:forEach>
                      <tr>
                      <td>총 금액</td><td colspan="7"><fmt:formatNumber pattern="###,###,###" value="${map.allSum}" />ウォン</td>
                     </tr> 
                   </tbody>
                     

   </table>
   
                </div><hr>
             </form>
             <h3>注文する</h3>
                 <form role="form" action="/order" method="post" autocomplete="off" class="aa-login-form">
                 <c:forEach items="${list}" var="cartList">
					<input type="hidden" id="gdsNo" name="gdsNo" value='<c:out value="${cartList.gdsNo}"/>' readonly/>
					<input type="hidden" id="gdsName" name="gdsName" value='<c:out value="${cartList.gdsName}"/>' readonly/>
					<input type="hidden" id="cartNo" name="cartNo" value='<c:out value="${cartList.cartNo}" />' readonly/>					               
				</c:forEach>
				 	<input type="hidden" value="user01" name="userId">  
                    <label for="name">宛先</label>
                    <input id="name" name="orderRec" type="text"><br>  
                    <label for="phone">連絡先</label>
                    <input id="phone" name="orderPhone" type="text"/><br>
					<div class="address_name">アドレス</div>
					<button type="button" class="btn btn-primary" style="margin-bottom:10px;" onclick="execution_daum_address()">アドレス探し</button><br>
					<input class="address_input_1" type="text" name="orderAddr1" ><br>
					<input class="address_input_2" type="text" name="orderAddr2" ><br>
					<input class="address_input_3" type="text" name="orderAddr3" ><br>
					<div class="pull-right">
						<button type="submit" class="order_btn btn btn-primary" onclick='alert("注文完了")'>注文</button>
						<button type="button" class="btn btn-danger" onclick='location.href="/"'>取り消し</button>
					</div>
					
                  </form>
           </div>
   
         </div>
       </div>
     </div>
   </div>
   
 </section>

 
 <!-- / Cart view section -->

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
$("#allCheck").click(function(){
 var chk = $("#allCheck").prop("checked");
 if(chk) {
  $(".chBox").prop("checked", true);
 } else {
  $(".chBox").prop("checked", false);
 }
});
</script>

<script>
 $(".chBox").click(function(){
  $("#allCheck").prop("checked", false);
 });
</script>

<script>
 $(".selectDelete_btn").click(function(){
  var confirm_val = confirm("削除しますか?");
  
  if(confirm_val) {
   var checkArr = new Array();
   
   $("input[class='chBox']:checked").each(function(){
    checkArr.push($(this).attr("data-cartNo"));
   });
    
   $.ajax({
    url : "/deleteCart",
    type : "post",
    data : { chbox : checkArr },
    success : function(result){
    	if(result==1)
    		 location.href = "/cart";		
    	setTimeout('location.reload()',1000);
    	
    
    }
   });
  } 
 });
</script>

<script>
$(".cancel_btn").click(function(){
 $(".orderInfo").slideUp();
 $(".orderOpne_btn").slideDown();
});      
</script>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
var addressCheck = false 		// アドレス
$(document).ready(function(){
	$(".join_button").click(function(){
		/* 住所情報有効性をチェック */
		if(addr == ""){
			$('.final_addr_ck').css('display','block');
			addressCheck = false;
		}else{
			$('.final_addr_ck').css('display', 'none');
			addressCheck = true;
		}	
		return false;
	});
});
/* Korea Daum Web Portal Adress API Service Used */
function execution_daum_address() {
    new daum.Postcode({
        oncomplete: function(data) {
            // This section runs when the user clicks on an address result in the popup.
            
            // Compose the address string based on the display rules for each address type.
            // If a returned field is empty, it will contain an empty string ('').
            var addr = ''; // Main address
            var extraAddr = ''; // Additional address info

            // Determine which address type the user selected
            if (data.userSelectedType === 'R') { // Road name address
                addr = data.roadAddress;
            } else { // Land-lot (jibun) address
                addr = data.jibunAddress;
            }

            // If the user selected a road name address, compose additional address info
            if (data.userSelectedType === 'R') {
                // If legal dong name exists and ends with dong/ro/ga, add it (excluding legal "ri")
                if (data.bname !== '' && /[dong|ro|ga]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }

                // If building name exists and is an apartment, add it
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                // If any additional address info exists, wrap it in parentheses
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }

                // Combine main address and additional info
                addr += extraAddr;
            } else {
                addr += ' ';
            }

            // Fill in the postal code and address fields
            $(".address_input_1").val(data.zonecode);
            $(".address_input_2").val(addr);

            // Enable the detailed address input field and move the cursor to it
            $(".address_input_3").attr("readonly", false);
            $(".address_input_3").focus();
        }
    }).open();
}

/* 入力メールアドレス有効性をチェック */
function mailFormCheck(email){
	var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return form.test(email);
}
</script>
    <script>
    $(document).ready(function(){

      $("#cancel").on("click", function(e){
			e.preventDefault();
			location.href='/user/mypage';
      });
    });
  </script>


  

    <jsp:include page="include/footer.jsp" />
  </body>
</html>