<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報修正</title>
<jsp:include page="../include/css.jsp" />

  </head>
  <body>  
 
<jsp:include page="../include/header.jsp" />
 
<jsp:include page="../include/menu.jsp" />
 <!-- product category -->
  <section>
    <div class="container">
      <div class="row">
        <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
		    <div class="row">
              <div class="col-md-9">
                <div class="aa-login-form">                 
                 <h3 style="margin-top:20px; display:inline-block;">会員情報修正</h3>
                 <a href="/user/delete_info" id="delete" style="float:right; margin-top:20px;">会員脱退</a>
                 
                 <form action="/user/update_info" method="post" class="aa-login-form">
                    <label for="id">ID</label>
                    <input id="id" name="userId" type="text" value='<c:out value="${user.userId }"/>' readonly> <br> 
                    <label for="pw">パスワード</label>
                    <input id="pw" name="userPw" type="password" required><br>
                    <label for="name">名前</label>
                    <input id="name" name="userName" type="text" value='<c:out value="${user.userName }"/>' readonly><br>  
					<label for=email>メールアドレス</label>
					<input id="email" name="userEmail" type="text" value='<c:out value="${user.userEmail }"/>' required /><br>
                    <label for="phone">連絡先</label>
                    <input id="phone" name="userPhone" type="text" value='<c:out value="${user.userPhone }"/>' required /><br>
					<div class="address_name">アドレス</div>
					<button class="btn btn-primary" style="margin-bottom:10px;" onclick="execution_daum_address()">アドレス探し</button><br>
					<input class="address_input_1" type="text" name="userAddr1" value='<c:out value="${user.userAddr1 }"/>' readonly="readonly"><br>
					<input class="address_input_2" type="text" name="userAddr2" value='<c:out value="${user.userAddr2 }"/>' readonly="readonly"><br>
					<input class="address_input_3" type="text" name="userAddr3" value='<c:out value="${user.userAddr3 }"/>' readonly="readonly"><br>
					 <br>
                    <button id="cancel" type="submit" class="aa-browse-btn" style="float: right; background-color:#d9534f;">取り消し</button>                    
                    <button type="submit" class="aa-browse-btn" style="float: right; margin-bottom:20px;">修正</button> 
                  </form>
                </div>
              </div>
            </div>          
		  
        </div>
      </div>
    </div>
  </section>
  <!-- / product category -->
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
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
var addressCheck = false 		// アドレスチェック
$(document).ready(function(){
	$(".join_button").click(function(){
		/* 住所情報 有効性をチェックする */
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
/* Korea Daum Portal Site Address API Used */
function execution_daum_address(){
	
    new daum.Postcode({
        oncomplete: function(data) {
			// This is the section where you write the code to execute 
			// when you click on a search result item in the popup.

			// Combine addresses based on exposure rules.
			// When the incoming variable has no value, it gets a blank ('') value. Refer to this for branching.
            var addr = ''; // Address variable
            var extraAddr = ''; // Reference item variable
            
			// Retrieve the address value based on the type of address selected by the user.
            if (data.userSelectedType === 'R') { // When the user selects a road name address
                addr = data.roadAddress;
            } else { // When the user selects a land-lot address (J)
                addr = data.jibunAddress;
            }
			
            // When the user selects a road name type address, combine the reference items.
            if(data.userSelectedType === 'R'){
				// Add the legal dong name if available (excluding "li").
				// In case of legal dongs, the name ends with "dong/ro/ga".
                if(data.bname !== '' && /[dong|ro|ga]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
				
                // If there is a building name and it is an apartment, add it.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
				
                // If there are reference items to display, create the final string with parentheses.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                 // Combine the address string and the reference item string.
      			addr += extraAddr;
            
            } else {
                addr += ' ';
            }
            // Insert the postal code and address information into the corresponding fields.
            $(".address_input_1").val(data.zonecode);
            $(".address_input_2").val(addr);
			// Change the disabled property of the detailed address input field 
			// and move the cursor to the detailed address field.
            $(".address_input_3").attr("readonly",false);
            $(".address_input_3").focus();
            
        }
    }).open();   
    
}
/* 入力したメールアドレスの有効性をチェックするロジック */
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
</body>
</html>