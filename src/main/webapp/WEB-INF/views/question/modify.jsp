<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>問い合わせ 修正</title>
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
		<h1 class="page-header">問い合わせする</h1>
	</div>
</div>

		<div class="col-md-8">
			<div class="panel panel-default" style="margin:0 20px;">
				<div class="panel-body">
				
					<form role="form" action="/question/modify" method="post">
						<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
						<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
						<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
						<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
						<div class="form-group">
						  <label for="category">カテゴリー</label>
						 <select class="form-control" name='quesCateCode'>
								<option value='配送の問い合わせ' <c:if test="${ques.quesCateCode == '配送の問い合わせ'}">selected</c:if>>配送の問い合わせ</option>
								<option value='交換返品' <c:if test="${ques.quesCateCode == '交換返品'}">selected</c:if>>交換返品</option>
								<option value='商品の問い合わせ' <c:if test="${ques.quesCateCode == '商品の問い合わせ'}">selected</c:if>>商品の問い合わせ</option>
								<option value='注文決済' <c:if test="${ques.quesCateCode == '注文決済'}">selected</c:if>>注文決済</option>
								<option value='ほかの問い合わせ' <c:if test="${ques.quesCateCode == 'ほかの問い合わせ'}">selected</c:if>>ほかの問い合わせ</option>									    								   
						  </select>
						</div>
	
						<div class="form-group ">
							<label for="quesTit">タイトル</label> <input class="form-control" name="quesTit" value='<c:out value="${ques.quesTit }"/>'>
						</div>
						
						<div class="form-group">
							 <input type="hidden" class="form-control" name='quesNo'
								value='<c:out value="${ques.quesNo}"/>' readonly="readonly" />
						</div>
						
<!-- 						<div class="form-group col-md-12"> -->
<!-- 							<label for="title">商品名</label> <input class="form-control" name="gdsName"> -->
						
						
						<div class="form-group ">
							<label for="Writer">作成者</label> <input type="text" class="form-control" name='quesWri' value="${ques.quesWri }" readonly>
						</div>
						

						
						<div class="form-group">
							<label for="quesCon">問い合わせ</label> <textarea class="form-control" rows="10" name='quesCon' ><c:out value="${ques.quesCon }"></c:out></textarea>
						</div>
						
						<div class="panel panel-default">
							<div class="panel-heading">File Attach</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="form-group uploadDiv">
									<input type="file" name='uploadFile' multiple>
								</div>
				
								<div class='uploadResult'>
									<ul>
				
									</ul>
								</div>
				
				
							</div>
							<!--  end panel-body -->
				
						</div>
						
						
						<div class="form-group col-md-12">
							<button type="submit" data-oper="list" class="btn btn-info pull-right" style="margin-right:5px;">リスト</button>						
							<c:if test="${user.userName == 'アドミン' || user.userId == ques.quesWri }">
								<button type="submit" data-oper="remove" class="btn btn-danger pull-right" style="margin-right:5px;">削除</button>	
								<button type="submit" data-oper="modify" class="btn btn-primary pull-right" style="margin-right:5px;">登録する</button>						
							</c:if>
						</div>				
					
	
		<!--  end panel-body -->
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
				<li><a href="/notice">お知らせ</a></li>
                <li><a href="/faq">FAQ</a></li>
				<li><a href="/question" class="active">問い合わせする</a></li>
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
	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form");

			$('button').on("click", function(e){
				e.preventDefault();
				
				var operation = $(this).data("oper");
				
				console.log(operation);
				
				if(operation === 'remove'){
					formObj.attr("action", "/question/remove");
				} else if(operation === 'list'){
					// move to list
					formObj.attr("action", "/question/list").attr("method","get");

				} else if (operation === 'modify') {
					console.log("submit clicked");
					var str = "";
					$(".uploadResult ul li").each(function(i, obj){
						var jobj = $(obj);
						console.dir(jobj);
						
						str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
				        str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				        str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
				        str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
					});
					formObj.append(str);
				}
				formObj.submit();
			});
		});
	</script>
	
	<!-- 添付ファイルを見せるコート  -->
	<script>
	$(document).ready(function(){
		(function(){
			var quesNo = '<c:out value="${ques.quesNo}"/>';
			$.getJSON("/question/getAttachList", {quesNo:quesNo}, function(arr){
				console.log(arr);
				var str ="";
				$(arr).each(function(i, attach){
					 //image type
			          if(attach.fileType){
			            var fileCallPath =  encodeURIComponent( attach.uploadPath+ "/s_"+attach.uuid +"_"+attach.fileName);
			            
			            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
			            str +=" data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			            str += "<span> "+ attach.fileName+"</span>";
			            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='image' "
			            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			            str += "<img src='/quesdisplay?fileName="+fileCallPath+"'>";
			            str += "</div>";
			            str +"</li>";
			          }else{
			              
			            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
			            str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			            str += "<span> "+ attach.fileName+"</span><br/>";
			            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
			            str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			            str += "<img src='/resources/img/attach.png'></a>";
			            str += "</div>";
			            str +"</li>";
			          }
			       });
				$(".uploadResult ul").html(str);
			}); // json end Point
		})(); // function end Point 
		
		$(".uploadResult").on("click", "button", function(e){
			console.log("delete file");
			
			if(confirm("Remove this file?")){
				var targetLi = $(this).closest("li");
				targetLi.remove();
			}
		});
		  
		  var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		  var maxSize = 5242880; //5MB
		  
		  function checkExtension(fileName, fileSize){
		    
		    if(fileSize >= maxSize){
			  alert("ファイルサイズがオーバーしました。");
		      return false;
		    }
		    
		    if(regex.test(fileName)){
			  alert("このファイルはアップロードできません。");
		      return false;
		    }
		    return true;
		  }
		  
		  $("input[type='file']").change(function(e){

		    var formData = new FormData();
		    
		    var inputFile = $("input[name='uploadFile']");
		    
		    var files = inputFile[0].files;
		    
		    for(var i = 0; i < files.length; i++){

		      if(!checkExtension(files[i].name, files[i].size) ){
		        return false;
		      }
		      formData.append("uploadFile", files[i]);
		      
		    }
		    
		    $.ajax({
		      url: '/quesuploadAjaxAction',
		      processData: false, 
		      contentType: false,data: 
		      formData,type: 'POST',
		      dataType:'json',
		        success: function(result){
		          console.log(result); 
				  showUploadResult(result); //アップロード結果処理処理関数 

		      }
		    }); //$.ajax
		    
		  });    

		  function showUploadResult(uploadResultArr){
			    
		    if(!uploadResultArr || uploadResultArr.length == 0){ return; }
		    
		    var uploadUL = $(".uploadResult ul");
		    
		    var str ="";
		    
		    $(uploadResultArr).each(function(i, obj){
				
				if(obj.image){
					var fileCallPath =  encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);
					str += "<li data-path='"+obj.uploadPath+"'";
					str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"'"
					str +" ><div>";
					str += "<span> "+ obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' "
					str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/quesdisplay?fileName="+fileCallPath+"'>";
					str += "</div>";
					str +"</li>";
				}else{
					var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
				    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				      
					str += "<li "
					str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
					str += "<span> "+ obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
					str += "<img src='/resources/img/attach.png'></a>";
					str += "</div>";
					str +"</li>";
				}

		    });
		    
		    uploadUL.append(str);
		  }
		  
		});

		</script>
        



</body>

    <jsp:include page="../include/footer.jsp" />
</html>