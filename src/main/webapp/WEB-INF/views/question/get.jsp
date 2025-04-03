<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問い合わせ記事を見る</title>
<jsp:include page="../include/css.jsp" />
    <style>
.uploadResult {
	width: 100%;
	background-color: gray;
}
.uploadResult ul{
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}
.uploadResult ul li{
	list-style: none;
	padding: 10px;
	align-content: center;
	text-align: center;
}
.uploadResult ul li img{
	width: 100px;
}
.uploadResult ul li span {
	color: white;
}
.bigPictureWrapper{
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(255,255,255,0.5);
}
.bigPicture{
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img{
	width: 600px;
}
</style>
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
			<div class="col-md-10">
				<h1 class="page-header" style="color:#4B89DC;">問い合わせ掲示板</h1>
			</div>
		</div>

		<div class="col-md-10">
			<div class="panel " style="margin:0 20px;">
				<div class="panel-body">
					<input type="hidden" class="form-control" name='quesNo'	value='<c:out value="${ques.quesNo}"/>' />
					<div class="form-group">
						<label>タイトル</label> <input class="form-control" name='quesTit'
							value='<c:out value="${ques.quesTit}"/>' readonly="readonly" />
					</div>
                      
                    <div class="form-group">
						<label>カテゴリー</label> <input class="form-control" name='quesCateCode'
							value='<c:out value="${ques.quesCateCode}"/>' readonly="readonly" />
					</div>
                      
							
					<div class="form-group">
						<label>問い合わせの内容</label>
						<textarea class="form-control" rows="3" name='quesCon'readonly="readonly"><c:out value="${ques.quesCon}" />
					</textarea>
					</div>
					<div class="panel panel-default">
		
						<div class="panel-heading">アップロードされたファイル</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
						
							<div class="uploadResult">
								<ul>
								</ul>
							</div>
						</div>
						<!-- end panel-body -->
					</div>
					       <div class='row'>
  <div class="col-lg-12">

	<!-- /.pannel -->
	<div class="panel panel-default">
<!-- 		<div class="panel-heading"> 
			    <i class="fa fa-comments fa-fw"></i>Reply 
 		    </div> -->
		<div class="panel-heading">
			<i class="fa fa-comments fa-fw"></i>コメント
			<c:if test="${user.userName == '관리자' || user.userId == ques.quesWri }">
				<button id="addReplyBtn" class='btn btn-primary btn-xs pull-right'>コメントを登録する</button>
			</c:if>
		</div>
		
		<!-- /.panel-heading -->
		<div class="panel-body">
			<ul class="chat">
			<!-- start reply -->

			<!-- end reply -->
			</ul>
			<!-- ./ end ul -->
		</div>
		<!-- /.panel .chat-panel -->
<!-- 439	 -->	
		<div class="panel-footer"></div>
	</div>
</div>
<!-- ./end row -->
</div>
					
					<button data-oper='list' class="btn btn-info pull-right" onclick="location.href='/question/list'">リスト</button>
					<c:if test="${user.userId == ques.quesWri || user.userName == 'アドミン' }">
						<button data-oper='modify' class="btn btn-primary pull-right" onclick="location.href='/question/modify?quesNo=<c:out value="${ques.quesNo}"/>'" style="margin-right:5px;">修正する</button>
					</c:if>

					<form id='operForm' action="/question/modify" method="get">
						<input type='hidden' id='quesNo' name='quesNo'
							value='<c:out value="${ques.quesNo}"/>'> <input
							type='hidden' name='pageNum'
							value='<c:out value="${cri.pageNum}"/>'> <input
							type='hidden' name='amount'
							value='<c:out value="${cri.amount}"/>'>
						<input type='hidden' name='keyword'
							value='<c:out value="${cri.keyword}"/>'> <input
							type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
					</form>
	
				</div>
			</div>
		</div>
	</div>


			<!-- end panel-head -->
	</div>
		<!-- end panel -->
	

		
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
       



 	<c:if test="${user.userName == 'アドミン'}">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">コメント</h4>
	            </div>
	            <div class="modal-body">
	                <div class="form-group">
	                	<label>コメント内容</label>
	                	<input class="form-control" name='reply'>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button id="modalModBtn" type="button" class="btn btn-primary">修正</button>
	                <button id="modalRemoveBtn" type="button" class="btn btn-danger">削除</button>
	                <button id="modalRegisterBtn" type="button" class="btn btn-primary" data-dismiss='modal'>登録</button>
	                <button id="modalCloseBtn" type="button" class="btn btn-danger" data-dismiss='modal'>取り消し</button>
	            </div>
	        </div>
	        <!-- /.modal-content -->
	    </div>
	    <!-- /.modal-dialog -->
	</div>
	</c:if>
	<!-- /.modal -->
       
      </div>
    </div>

		<!-- /.row -->
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
  <script type="text/javascript" src="/resources/js/reply.js"></script>

<script type="text/javascript" >

//コメントを追加すると、動作するボタンイベントロジック
$(document).ready(function() {
	
var quesNoValue = '<c:out value="${ques.quesNo}" />';
var replyUL = $(".chat");

	showList(1);//実行し、もう一度読み込んで、最新のコメント内容をチェックする



	function showList(page) {
		
		console.log("show list : "+ page);
		
		replyService.getList({quesNo:quesNoValue, page: page || 1 }, 
			function(replyCnt, list) {
			
			console.log("replyCnt: " + replyCnt);
			console.log("list: " + list);
			console.log(list);
			
			if(page == -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			
			var str = "";		
			if(list == null || list.length == 0){
				return;
			}
			
			for (var i = 0, len = list.length || 0; i<len; i++) {
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
				str += " <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong> ";
				str += " <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
				str += " <p>"+list[i].reply+"</p></div></li>";
			}
			
			replyUL.html(str);
			
			showReplyPage(replyCnt);
			
		});//end function
	}//end showList
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	//コメントページ番号を出力する	
	function showReplyPage(replyCnt) {
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		var prev = startNum != 1;
		var next = false;		
		if (endNum * 10 >= replyCnt) {
			endNum = Math.ceil(replyCnt/10.0);
		}
		if (endNum * 10 < replyCnt) {
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str += "<li class='page-item'><a class ='page-link' href='"+(startNum -1)+"'>Previous</a></li> ";
		}
		
		for(var i = startNum ; i <= endNum; i++) {
			var active = pageNum == i? "active":"";
			str += "<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a></li>";
		}
		
		if(next){
			str += "<li class='page-item'><a class='page-link' href='"+(endNum + 1)+"'>Next</a></li> ";
		}
		str += "</ul></div>";
		
		console.log(str);	
		replyPageFooter.html(str);	
	}

//コメントページ番号をクリックすると、新しいコメントを見せる。
	replyPageFooter.on("click","li a", function(e) {
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		
		console.log("targetPageNum : " + targetPageNum);
		
		pageNum = targetPageNum;		
		showList(pageNum);
	})
	

	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e) {
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
	});
	
	//コメントを登録する
	modalRegisterBtn.on("click", function(e) {
		
		var reply = {
				reply : modalInputReply.val(),
				replyer : 'アドミン',
				quesNo : quesNoValue
		};
		replyService.add(reply, function(result){
			
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
			//showList(1) //コメントが追加された後の内容を見せるためのコード
			showList(-1) //page番号が-1に伝えると最後のページを探して、まだ呼び出す。
		});
	});
	
// コメント照会クリックすると、動作するイベントロジック
	$(".chat").on("click", "li", function(e) {
		
		var rno = $(this).data("rno");
		console.log(rno);
		
		replyService.get(rno, function(reply) {
			modalInputReply.val(reply.reply);
			modalInputReplyer.val(reply.replyer);
			modalInputReplyDate.val(replyService.displayTime( reply.replyDate )).attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide();
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});	
	});



//モーダル画面でコメントを修正する。
	modalModBtn.on("click", function(e) {
		var reply = {rno:modal.data("rno"), reply:modalInputReply.val()};
		
		replyService.update(reply, function(result) {
			
			alert(result);
			modal.modal("hide");
			showList(pageNum);
		});
	});
	
	
//モーダル画面でコメントを削除する。
	modalRemoveBtn.on("click", function(e) {
		
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result) {
			
			alert(result);
			modal.modal("hide");
			showList(pageNum);
			//showList(1);
		});
	});
	


});


</script>
<script type="text/javascript">
$(document).ready(function() {
  
  var operForm = $("#operForm"); 
  
  $("button[data-oper='modify']").on("click", function(e){
    
    operForm.attr("action","/question/modify").submit();
    
  });
  
    
  $("button[data-oper='list']").on("click", function(e){    
    operForm.find("#quesNo").remove();
    operForm.attr("action","/question/list")
    operForm.submit();
    
  });  
});
</script>

<script type="text/javascript">

$(document).ready(function(){
/* 添付ファイルを見せてくれるコートを即時実行関数を使って、処理する */	
	(function(){
		console.log("getjson 시작")
		var quesNo = '<c:out value="${ques.quesNo}"/>';
/* JSONの形に持ってくれた添付ファイルデータをdivタグの中に見せるように処理する	 */	
		$.getJSON("/question/getAttachList", {quesNo: quesNo}, function(arr) {
			console.log(arr);
		
			var str = "";
			$(arr).each(function(i, attach) {
			
			//image Type
			if(attach.fileType){
				var fileCallPath = encodeURIComponent( attach.uploadPath + "/s_" + attach.uuid +"_"+attach.fileName);
				
				str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
				str += "<span>"+attach.fileName+"</span><br>";
				str += "<img src='/quesdisplay?fileName="+fileCallPath+"'>";
				str += "</div></li>";
			}else {
				str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
				str += "<span>"+attach.fileName+"</span><br>";
				str += "<img src='/resources/img/attach.png'>";
				str += "</div></li>";								
			}
		  });
		
		$(".uploadResult ul").html(str);
	
		});//end getjson
	})();//end function
	
	
/* 添付ファイルのイメージファイル(オリジナルイメージ), 一般のファイル処理する(ダウンロード)*/
	$(".uploadResult").on("click","li", function(e) {
		
		console.log("view image");
		
		var liObj = $(this);
		var path = encodeURIComponent(liObj.data("path") +"/"+ liObj.data("uuid") +"_"+ liObj.data("filename"));
		
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
		}else {
			//download
			self.location ="/download?fileName="+path
		}
		
	});

//オリジナルイメージ拡大
	function showImage(fileCallPath) {
		alert(fileCallPath);
		$(".bigPictureWrapper").css("display","flex").show();
		$(".bigPicture")
		.html("<img src='/quesdisplay?fileName="+fileCallPath+"'>")
		.animate({width:'100%', height: '100%'}, 1000);
	}
/* 577 クリックするとオリジナルイメージが閉じる*/
	$(".bigPictureWrapper").on("click", function(e) {
		$(".bigPicture").animate({width:'0%' , height: '0%'}, 1000); 
		setTimeout(function() {
			$('.bigPictureWrapper').hide();
		}, 1000);
	});
	
});
</script>

    <jsp:include page="../include/footer.jsp" />

</body>
</html>