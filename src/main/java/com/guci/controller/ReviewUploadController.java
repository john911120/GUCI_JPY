package com.guci.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.guci.domain.ReviewAttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

// レビュー画像や添付ファイルのアップロード・ダウンロード処理を担当するコントローラー
@Controller
@Log4j
public class ReviewUploadController {
	
	// 通常のフォームによるファイルアップロード画面
	@GetMapping("/revuploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	// アップロードフォルダを年月日ごとに生成
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}


	 // 通常のフォームでアップロードされたファイルを保存(JQuery)
	@PostMapping("/revuploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/";// 업로드 되는 파일 경로를 지정

		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("Only file name : " + uploadFileName);

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} 
		} 

	}

	 // Ajaxによる非同期ファイルアップロード画面
	@GetMapping("/revuploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}

	// アップロードされたファイルが画像かどうかを判定
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	 // Ajaxアップロード時、画像の場合はサムネイル生成も行い、情報をリストで返却
		@PostMapping(value = "/revuploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<List<ReviewAttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

			List<ReviewAttachFileDTO> list = new ArrayList<>();
			String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/";// 업로드 되는 파일 경로를 지정

			String uploadFolderPath = getFolder();

			File uploadPath = new File(uploadFolder, uploadFolderPath);
			log.info("upload path : " + uploadPath);

			if(!uploadPath.exists()) {
				uploadPath.mkdirs();
			}

			for(MultipartFile multipartFile : uploadFile) {
				log.info("--------------------------------------");
				log.info("Upload File Name : " + multipartFile.getOriginalFilename());
				log.info("Upload File Size : " + multipartFile.getSize());

				ReviewAttachFileDTO attachDTO = new ReviewAttachFileDTO();

				String uploadFileName = multipartFile.getOriginalFilename();

				// IE has file path
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				log.info("Only file name : " + uploadFileName);
				attachDTO.setFileName(uploadFileName);

				UUID uuid = UUID.randomUUID();

				uploadFileName = uuid.toString() + "_" + uploadFileName;

				try {
					File saveFile = new File(uploadPath, uploadFileName);
					multipartFile.transferTo(saveFile);

					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(uploadFolderPath);
					if(checkImageType(saveFile)) {
						attachDTO.setImage(true);
						FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
						Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
					}
					list.add(attachDTO);
				} catch (Exception e) {
					log.error(e.getMessage());
				} 
			} 
			return new ResponseEntity<>(list, HttpStatus.OK);
		}

		// 画像をバイナリとして取得し返却（プレビュー表示用）
		@GetMapping("/revdisplay")
		@ResponseBody
		public ResponseEntity<byte[]> getFile(String fileName) {

			log.info("fileName : " + fileName);

			File file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/" + fileName);

			log.info("file : " + file);

			ResponseEntity<byte[]> result = null;

			try {
				HttpHeaders header = new HttpHeaders();
				header.add("Content-Type", Files.probeContentType(file.toPath()));
				result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return result;
		}

		// 添付ファイルのダウンロード処理（ユーザーエージェントによるブラウザ対応あり）
		@GetMapping(value="/revdownload", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){

			Resource resource = new FileSystemResource("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/" + fileName);

			if(!resource.exists()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}


			String resourceName = resource.getFilename();

			//remove UUID
			String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

			HttpHeaders headers = new HttpHeaders();

			try {
				String downloadName = null;

				if(userAgent.contains("Trident")) {
					log.info("IE browser");
					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
				} else if(userAgent.contains("Edge")) {
					log.info("Edge Browser");
					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
					log.info("Edge Name: " + downloadName);
				} else {
					log.info("Chrome Browser");
					downloadName = new String(resourceOriginalName.getBytes("UTF-8"), "ISO-8859-1");
				}
				log.info("downloadName: " + downloadName);

				headers.add("Content-Disposition", "attachment; filename=" + downloadName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
		}

		// アップロードされたファイルを削除（画像の場合はサムネイルも削除）
		@PostMapping("/revdeleteFile")
		@ResponseBody
		public ResponseEntity<String> deleteFile(String fileName, String type){
			log.info("Delete File : " + fileName);
			File file;
			try {
				file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/review/img/" + URLDecoder.decode(fileName, "UTF-8"));
				file.delete();
				if(type.equals("image")) {
					String largeFileName = file.getAbsolutePath().replace("s_", "");
					log.info("largeFileName : " + largeFileName);
					file = new File(largeFileName);
					file.delete();
				}
			} catch (UnsupportedEncodingException e) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		}
}