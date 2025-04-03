<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビュー修正</title>
  <link href="/resources/css/bootstrap.css" rel="stylesheet">  
  </head>
  <body>
<!-- 260 -->
<div class="row">
  	<div class="col-lg-8 col-lg-offset-2">
    <h1 class="page-header">レビュー修正</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  	<div class="col-lg-8 col-lg-offset-2">
    <div class="panel panel-default">

      <!-- /.panel-heading -->
      <div class="panel-body">

      <form role="form" action="/review/modify" method="post" enctype="multipart/form-data">
      <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"></c:out>'>
      <input type='hidden' name='amount' value='<c:out value="${cri.amount }"></c:out>'>

  
  <input type="hidden" class="form-control" name='gdsNo' value='<c:out value="${review.gdsNo}"/>' readonly="readonly">            
         <div class="form-group">
  <label>商品名</label> 
  <input class="form-control" name='gdsName'
    value='<c:out value="${review.gdsName}"/>' readonly="readonly">            
</div>
           <div class="form-group">
            <label>カスタマーレビュー</label>
            <select class="form-control" name="score" id="score" >
            <option value='' selected>-- レートを 選択してください。 --</option>
            <option value="★★★★★" <c:if test="${review.score == '★★★★★'}">selected</c:if>>★★★★★</option>
            <option value="★★★★☆" <c:if test="${review.score == '★★★★☆'}">selected</c:if>>★★★★☆</option>
            <option value="★★★☆☆" <c:if test="${review.score == '★★★☆☆'}">selected</c:if>>★★★☆☆</option>
            <option value="★★☆☆☆" <c:if test="${review.score == '★★☆☆☆'}">selected</c:if>>★★☆☆☆</option>
            <option value="★☆☆☆☆" <c:if test="${review.score == '★☆☆☆☆'}">selected</c:if>>★☆☆☆☆</option>
            <option value="☆☆☆☆☆" <c:if test="${review.score == '☆☆☆☆☆'}">selected</c:if>>☆☆☆☆☆</option>
            </select>
          </div>

          <div class="form-group">
            <label>レビュー</label>
            <textarea class="form-control" rows="3" name='revCon'><c:out value='${review.revCon }'/></textarea>
          </div>

          <div class="form-group">
 
  <input type ='hidden' class="form-control" name='revNo'
    value='<c:out value="${review.revNo}"/>' readonly="readonly">            
</div>
         <div class="form-group">
  <label>作成者</label> 
  <input class="form-control" name='userId'
    value='<c:out value="${review.userId}"/>' readonly="readonly">            
</div>
		<div class='bigPictureWrapper'>
			<div class='bigPicture'>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">イメージ</div>
					<div class="panel-body">
						<div class="form-group uploadDiv">
							<input type="file" name='uploadFile' multiple="multiple">
						</div>
						<div class="uploadResult">
							<ul>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>


  <button type="submit" data-oper='list' class="btn btn-info pull-right">リスト</button>
  <button type="submit" data-oper='remove' class="btn btn-danger pull-right" style="margin-right:5px;">削除</button>
  <button type="submit" data-oper='modify' class="btn btn-primary pull-right" style="margin-right:5px;">修正</button>
</form>


      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->
<!-- 261 -->
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
$(document).ready(function() {	
	  var formObj = $("form");

	  $('button').on("click", function(e){
	    
	    e.preventDefault(); 
	    
	    var operation = $(this).data("oper");
	    
	    console.log(operation);
	    
	    if(operation === 'remove'){
	      var gdsNo = $("input[name='gdsNo']").val();
		  formObj.append(gdsNo);
	      formObj.attr("action", "/review/remove");
	      
	    }else if(operation === 'list'){
	    	
	      //move to list
	      
// 	      self.location="/review/list";
// 	      return;
	      
 	      formObj.attr("action", "/review/product_detail").attr("method","get");
//321	      
		  var gdsNo = $("input[name='gdsNo']").clone();
	      var pageNumTag = $("input[name='pageNum']").clone();
 	      var amountTag = $("input[name='amount']").clone();
//347
 	      formObj.empty();
	      
	      formObj.append(gdsNo);
 	      formObj.append(pageNumTag);
 	      formObj.append(amountTag);
//347	   
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
	<!--添付ファイルを見せるコート -->
	<script>
	$(document).ready(function(){
		(function(){
			var revNo = '<c:out value="${review.revNo}"/>';
			$.getJSON("/review/getAttachList", {revNo:revNo}, function(arr){
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
			            str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i>X</button><br>";
			            str += "<img src='/revdisplay?fileName="+fileCallPath+"'>";
			            str += "</div>";
			            str +"</li>";
			          }else{
			              
			            str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' "
			            str += "data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"' ><div>";
			            str += "<span> "+ attach.fileName+"</span><br/>";
			            str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' "
			            str += " class='btn btn-warning btn-circle'><i class='fa fa-times'></i>X</button><br>";
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
		      url: '/revuploadAjaxAction',
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
					str += "data-type='image' class='btn btn-warning btn-circle'><i class='fa fa-times'></i>X</button><br>";
					str += "<img src='/revdisplay?fileName="+fileCallPath+"'>";
					str += "</div>";
					str +"</li>";
				}else{
					var fileCallPath =  encodeURIComponent( obj.uploadPath+"/"+ obj.uuid +"_"+obj.fileName);			      
				    var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
				      
					str += "<li "
					str += "data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.image+"' ><div>";
					str += "<span> "+ obj.fileName+"</span>";
					str += "<button type='button' data-file=\'"+fileCallPath+"\' data-type='file' " 
					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'></i>X</button><br>";
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
</html>

