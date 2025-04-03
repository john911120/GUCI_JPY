<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>レビュー 登録</title>
  <link href="/resources/css/bootstrap.css" rel="stylesheet"> 
  </head>
  <body> 


<div class="row">
  	<div class="col-lg-8 col-lg-offset-2">
  		<h1 class="page-header">レビュー 作成</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-8 col-lg-offset-2">
    <div class="panel panel-default">

      <!-- /.panel-heading -->
      <div class="panel-body">

        <form role="form" action="/review/register" method="post" enctype="multipart/form-data" >
           <input type="hidden" class="form-control" name='gdsNo' value='<c:out value="${goods.gdsNo }"/>' readonly>
           <div class="form-group">
            <label>商品名</label> <input class="form-control" name='gdsName' value='<c:out value="${goods.gdsName }"/>' readonly>
          </div>
           <div class="form-group">
            <label>カスタマーレビュー</label>
            <select class="form-control" name="score" id="score" >
            <option value='' selected>-- レートを 選択してください。 --</option>
            <option value="★★★★★">★★★★★</option>
            <option value="★★★★☆">★★★★☆</option>
            <option value="★★★☆☆">★★★☆☆</option>
            <option value="★★☆☆☆">★★☆☆☆</option>
            <option value="★☆☆☆☆">★☆☆☆☆</option>
            <option value="☆☆☆☆☆">☆☆☆☆☆</option>
            </select>
          </div>

          <div class="form-group">
            <label>レビュー</label>
            <textarea class="form-control" rows="3" name='revCon'></textarea>
          </div>

          <div class="form-group">
            <label>작성자</label> <input class="form-control" name='userId' value='<c:out value="${user.userId }"/>' readonly>
          </div>
        <div class="panel panel-default">
			<div class="panel-heading">ファイル添付</div>
			<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="form-group uploadDiv">
			<input type="file" name='uploadFile' multiple>
		</div>

		<div class='uploadResult'>
			<ul>

			</ul>
		</div>
	<!--  end panel-body -->
	</div>
</div>
          <button type="reset" class="btn btn-danger pull-right" onclick="history.back()">取り消し</button>
          <button type="submit" class="btn btn-primary pull-right" style="margin-right:5px;" onclick='alert("コメントが 登録しました。")'>登録</button>
        </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <!-- Include all compiled plugins (below), or include individual files as needed -->
  <script src="/resources/js/bootstrap.js"></script>  
  <!-- SmartMenus jQuery plugin -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.js"></script>
  <!-- SmartMenus jQuery Bootstrap Addon -->
  <script type="text/javascript" src="/resources/js/jquery.smartmenus.bootstrap.js"></script>  
  <!-- slick slider -->
  <script type="text/javascript" src="/resources/js/slick.js"></script>
  
  <script>
  	$(document).ready(function(e){
  		var formObj = $("form[role='form']");
  		$("button[type='submit']").on("click", function(e){
  			e.preventDefault();
			var str = "";
			
			$(".uploadResult ul li").each(function(i, obj){
				var jobj = $(obj);
				
				console.dir(jobj);
			    console.log("-------------------------");
			    console.log(jobj.data("filename"));
				
			      str += "<input type='hidden' name='attachList["+i+"].fileName' value='"+jobj.data("filename")+"'>";
			      str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
			      str += "<input type='hidden' name='attachList["+i+"].uploadPath' value='"+jobj.data("path")+"'>";
			      str += "<input type='hidden' name='attachList["+i+"].fileType' value='"+ jobj.data("type")+"'>";
			});
			console.log(str);
			
			formObj.append(str).submit();
	  	});

  		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
  		var maxSize = 5242880;
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
  				processData : false,
  				contentType : false,
  				data:formData,
  				type: 'POST',
  				dataType:'json',
  				success: function(result){
  					console.log(result);
  					showUploadResult(result); //アップロード結果処理処理関数
  				}
  			});
  		});
  		
  		function showUploadResult(uploadResultArr) {
  			if(!uploadResultArr || uploadResultArr.length == 0){
  				return;
  			}
  			var uploadUL = $(".uploadResult ul");
  			var str = "";
  			
  			$(uploadResultArr).each(function(i, obj){
  				//image type
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
  					str += "class='btn btn-warning btn-circle'><i class='fa fa-times'>X</i></button><br>";
  					str += "<img src='/resources/img/attach.png'></a>";
  					str += "</div>";
  					str +"</li>";
  				}
  		    });
  		    uploadUL.append(str);
  		  }
  		
  		// 添付ファイルの変更処理ロジック
  	  $(".uploadResult").on("click", "button", function(e){
  		  console.log("delete file");
  		  
  		  var targetFile = $(this).data("file");
  		  var type = $(this).data("type");
  		  
  		  var targetLi = $(this).closest("li");
  		  
  		  $.ajax({
  			  url: '/revdeleteFile',
  			  data: {fileName: targetFile, type:type},
  			  dataType:'text',
  			  type:'POST',
  			  success:function(result){
  				  alert(result);
  				  targetLi.remove();
  			  }
  		  });
  	  });
  	});
  </script>
  
  </body>
</html>