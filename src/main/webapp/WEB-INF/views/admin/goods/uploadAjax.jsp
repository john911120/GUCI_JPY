<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Upload with Ajax</h1>
<div class='uploadDiv'>
	<input type='file' name='uploadFile' multiple>
</div>
<div class="uploadResult">
	<ul>
	</ul>
</div>
<button id='uploadBtn'>Upload</button>
<!-- サムネールをクリックするとオリジナルイメージを見せる。 -->
<!-- <div class="bigPictureWrapper">
	<div class="bigPicture">
	</div>
</div> -->
  <script src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script>
	$(document).ready(function(){
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5MBまで
		
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
		
		var cloneObj = $(".uploadDiv").clone();
		
		var uploadResult = $(".uploadResult ul");
		function showUploadedFile(uploadResultArr){
			var str = "";
			
			$(uploadResultArr).each(function(i,obj){
				if(!obj.image){
					str += "<li><img src='/resources/admin/img/attach.png' style='height:100px;'>" + obj.fileName_goods+"</li>";
				} else{
//					str += "<li>"+obj.fileName_goods+"</li>";
					var fileCallPath = encodeURIComponent(obj.path_goods+"/s_"+obj.uuid_goods+"_"+obj.fileName_goods);
					str += "<li><img src='/display?fileName_goods="+fileCallPath+"'</li>";
				}
			});
			
			uploadResult.append(str);
		}
		
		$("#uploadBtn").on("click", function(e){
			var formData = new FormData();
			var inputFile = $("input[name='uploadFile']");
			var files = inputFile[0].files;
			console.log(files);
			
			for(var i=0; i<files.length; i++){
				if(!checkExtension(files[i].name, files[i].size)){
					return false;
				}
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url: '/admin/goods/uploadAjaxAction',
				processData: false,
				contentType: false,
				data: formData,
				type: 'POST',
				dataType:'JSON',
				success: function(result){
					console.log(result);
					showUploadedFile(result);
					$(".uploadDiv").html(cloneObj.html());
				}
			});
		});
	});
</script>
</body>
</html>