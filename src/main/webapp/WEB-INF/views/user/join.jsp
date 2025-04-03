<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員加入</title>
<link rel="stylesheet" href="/resources/css/user/join.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>

<div class="wrapper">
	<form id="join_form" method="post">
	<div class="wrap">
			<div class="subjecet">
				<span>会員加入</span>
			</div>
			<div class="id_wrap">
				<div class="id_name">ID</div>
				<div class="id_input_box">
					<input class="id_input" name="userId">
				</div>
				<span class="id_input_re_1">ご利用できるIDです。</span>
				<span class="id_input_re_2">このIDは既に存在しております。</span>
				<span class="final_id_ck">IDを入力してください。</span>
			</div>
			<div class="pw_wrap">
				<div class="pw_name">パスワード</div>
				<div class="pw_input_box">
					<input class="pw_input" type="password" name="userPw">
				</div>
				<span class="final_pw_ck">パスワードを入力してください。</span>
			</div>
			<div class="pwck_wrap">
				<div class="pwck_name">パスワードチェック</div>
				<div class="pwck_input_box">
					<input class="pwck_input" type="password">
				</div>
				<span class="final_pwck_ck">パスワード チェックボータンを押してください。</span>
				 <span class="pwck_input_re_1">パスワードが一致します。</span>
                <span class="pwck_input_re_2">パスワードが一致しません。</span>
			</div>
			<div class="user_wrap">
				<div class="user_name">名前</div>
				<div class="user_input_box">
					<input class="user_input" name="userName">
				</div>
				<span class="final_name_ck">名前を入力してください。</span>
			</div>
			
			<div class="phon_wrap">
				<div class="phon_name">携帯電話番号</div>
				<div class="phon_input_box">
					<input class="phon_input" name="userPhone">
				</div>
				<span class="final_phon_ck">携帯電話番号を入力してください。</span>
			</div>
			<div class="mail_wrap">
				<div class="mail_name">メールアドレス</div> 
				<div class="mail_input_box">
					<input class="mail_input" name="userEmail">
				</div>
				<span class="final_mail_ck">メールアドレスを入力してください。</span>
				<div class="mail_check_wrap">
					<div class="mail_check_input_box" id="mail_check_input_box_false">
						<input class="mail_check_input"  disabled="disabled">
					</div>
					<div class="mail_check_button">
						<span>認証番号 転送</span>
					</div>
					<div class="clearfix"></div>
					<span id="mail_check_input_box_warn"></span>
				</div>
			</div>
			<div class="address_wrap">
				<div class="address_name">アドレス</div>
				<div class="address_input_1_wrap">
					<div class="address_input_1_box">
						<input class="address_input_1" name="userAddr1" readonly="readonly">
					</div>
					<div class="address_button" onclick="execution_daum_address()">
						<span>アドレス探し</span>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class ="address_input_2_wrap">
					<div class="address_input_2_box">
						<input class="address_input_2" name="userAddr2" readonly="readonly">
					</div>
				</div>
				<div class ="address_input_3_wrap">
					<div class="address_input_3_box">
						<input class="address_input_3" name="userAddr3" readonly="readonly">
					</div>
				</div>
				<span class="final_addr_ck">アドレスを入力してください。</span>
			</div>
			<div class="join_button_wrap">
				<input type="button" class="join_button" value="加入する" onclick='alert("加入しました")'>
			</div>
		</div>
	</form>
</div>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
var code = "";				// メール送信認証コードを保存するためのコード
/* バリデーション通過有無の変数 */
 var idCheck = false;			// ユーザーID
 var idckCheck = false;			// ユーザーIDの重複チェック
 var pwCheck = false;			// パスワード
 var pwckCheck = false;			// パスワード確認
 var pwckcorCheck = false;		// パスワード確認の一致確認
 var nameCheck = false;			// 名前
 var phonCheck = false;
 var mailCheck = false;			// メールアドレス
 var mailnumCheck = false;		// メール認証コード確認
 var addressCheck = false 		// 住所
$(document).ready(function(){
	// 会員登録ボタン（会員登録機能を作動）
	$(".join_button").click(function(){
		
		/* 入力値の変数 */
		var id = $('.id_input').val(); 				// ユーザーID入力欄
		var pw = $('.pw_input').val();				// パスワード入力欄
		var pwck = $('.pwck_input').val();			// パスワード確認入力欄
		var name = $('.user_input').val();			// 名前入力欄
		var phon = $('.phon_input').val();			// 電話番号入力欄
		var mail = $('.mail_input').val();			// メールアドレス入力欄
		var addr = $('.address_input_3').val();		// 住所入力欄
		
				/* ユーザーIDのバリデーション */
				if(id == ""){
					$('.final_id_ck').css('display','block');
					idCheck = false;
				}else{
					$('.final_id_ck').css('display', 'none');
					idCheck = true;
				}
				
				/* パスワードのバリデーション */
				if(pw == ""){
					$('.final_pw_ck').css('display','block');
					pwCheck = false;
				}else{
					$('.final_pw_ck').css('display', 'none');
					pwCheck = true;
				}
				
				/* パスワード確認のバリデーション */
				if(pwck == ""){
					$('.final_pwck_ck').css('display','block');
					pwckCheck = false;
				}else{
					$('.final_pwck_ck').css('display', 'none');
					pwckCheck = true;
				}
				
				/* 名前のバリデーション */
				if(name == ""){
					$('.final_name_ck').css('display','block');
					nameCheck = false;
				}else{
					$('.final_name_ck').css('display', 'none');
					nameCheck = true;
				}		
				
				/* 電話番号のバリデーション */
				if(phon == ""){
					$('.final_phon_ck').css('display','block');
					phonCheck = false;
				}else{
					$('.final_phon_ck').css('display', 'none');
					phonCheck = true;
				}		
				
				/* メールアドレスのバリデーション */
				if(mail == ""){
					$('.final_mail_ck').css('display','block');
					mailCheck = false;
				}else{
					$('.final_mail_ck').css('display', 'none');
					mailCheck = true;
				}		
				
				/* 住所のバリデーション */
				if(addr == ""){
					$('.final_addr_ck').css('display','block');
					addressCheck = false;
				}else{
					$('.final_addr_ck').css('display', 'none');
					addressCheck = true;
				}		
				
				/* 最終バリデーション */
				if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nameCheck&&phonCheck&&mailCheck&&mailnumCheck&&addressCheck ){
					$("#join_form").attr("action", "/user/join");
					$("#join_form").submit();			
					
				}		
				
				return false;
			});
		});
// ユーザーIDの重複チェック
$('.id_input').on("propertychange change keyup paste input", function(){
	
	var userId = $('.id_input').val();			// .id_inputにインプットされる
	var data = {userId : userId}				// 'コントローラーに転送するデータ名' : 'データ(.id_inputに入力される)'
	
	$.ajax({
		type : "post",
		url : "/user/userIdChk",
		data : data,
		success : function(result){
			if(result != 'fail'){
				$('.id_input_re_1').css("display","inline-block");
				$('.id_input_re_2').css("display", "none");	
				idckCheck = true;
			} else {
				$('.id_input_re_2').css("display","inline-block");
				$('.id_input_re_1').css("display", "none");
				idckCheck = false;
			}	
		}// success 終了
	}); // ajax 終了	
});// function 終了
/* 認証番号 メールアドレス 転送 */
$(".mail_check_button").click(function(){
	
	var email = $(".mail_input").val();			// 入力したアドレス
	var cehckBox = $(".mail_check_input");		// 認証番号入力
	var boxWrap = $(".mail_check_input_box");	// 認証番号入力ボックス
	var warnMsg = $(".mail_input_box_warn");	// メールアドレス入力アラット
	
	/* メールアドレス形有効性をチェック */
	if(mailFormCheck(email)){
		warnMsg.html("メールが転送しました。ご確認お願い致します。");
		warnMsg.css("display", "inline-block");
	} else {
		warnMsg.html("正しくないメールアドレスです。");
		warnMsg.css("display", "inline-block");
		return false;
	}	
	
	$.ajax({
		
		type:"GET",
		url:"mailCheck?email=" + email,
		success:function(data){
			
			//console.log("data : " + data);
			cehckBox.attr("disabled",false);
			boxWrap.attr("id", "mail_check_input_box_true");
			code = data;
			
		}
				
	});
	
});
/* 認証番号比べる */
$(".mail_check_input").blur(function(){
	
	var inputCode = $(".mail_check_input").val();		// 認証番号	
	var checkResult = $("#mail_check_input_box_warn");	// 比べ結果 	
	
	if(inputCode == code){							// 一致する
		checkResult.html("認証番号が一致します。");
		checkResult.attr("class", "correct");		
		mailnumCheck = true;
	} else {											// 一致しない
		checkResult.html("認証番号が一致しません。");
		checkResult.attr("class", "incorrect");
		mailnumCheck = false;
	}	
	
});
/*Korea Daum Web Portal API Service Used*/
function execution_daum_address(){

    new daum.Postcode({
        oncomplete: function(data) {
            // This is the section where code is executed when clicking a search result item in the popup.
            
            // Combine addresses according to the address display rules.
            // If the incoming variable has no value, it holds a blank ('') value. Refer to this for branching.
            var addr = ''; // Address variable
            var extraAddr = ''; // Reference item variable
            // Retrieve the corresponding address value based on the type of address selected by the user.
            if (data.userSelectedType === 'R') { // When the user selects a road name address
                addr = data.roadAddress;
            } else { // When the user selects a land-lot address (J)
                addr = data.jibunAddress;
            }
            // Combine reference items when the selected address type is a road name type.
            if(data.userSelectedType === 'R'){
                // Add the legal dong name if it exists (exclude legal "ri").
                // In the case of legal dongs, the name ends with "dong/ro/ga".
                if(data.bname !== '' && /[dong|ro|ga]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // Add the building name if it exists and if it's an apartment.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // If there are reference items to display, create the final string with parentheses.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // Combine the address variable string and reference item string.
      			addr += extraAddr;
            
            } else {
                addr += ' ';
            }
            // Insert postal code and address information into the respective fields.
            $(".address_input_1").val(data.zonecode);
            $(".address_input_2").val(addr);
            // Change the disabled property of the detailed address input field and move the cursor to the detailed address field.
            $(".address_input_3").attr("readonly",false);
            $(".address_input_3").focus();
            
        }
    }).open();   

}
/* パスワード 確認と一致有効性をチェック */
$('.pwck_input').on("propertychange change keyup paste input", function(){
	
	var pw = $('.pw_input').val();
	var pwck = $('.pwck_input').val();
	$('.final_pwck_ck').css('display', 'none');
	
	if(pw == pwck){
		$('.pwck_input_re_1').css('display','block');
		$('.pwck_input_re_2').css('display','none');
		pwckcorCheck = true;
	}else{
		$('.pwck_input_re_1').css('display','none');
		$('.pwck_input_re_2').css('display','block');
		pwckcorCheck = false;
	}
	
	
});
 /* 入力メールアドレス有効性をチェック*/
 function mailFormCheck(email){
	var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	return form.test(email);
}
</script>
</body>
</html>