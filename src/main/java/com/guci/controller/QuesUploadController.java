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

import com.guci.domain.QuesAttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

/*
  Q&A質問投稿の添付ファイル機能を処理する専用コントローラー
  - アップロード、サムネイル生成、ダウンロード、削除などを担当
 */

@Controller
@Log4j
public class QuesUploadController {
	// 添付ファイルフォームを表示（基本フォーム）
	@GetMapping("/quesuploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	// yyyy-MM-dd 形式でフォルダ名を生成
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}


	// 添付ファイルを受け取り、サーバーに保存する（form方式）
	@PostMapping("/quesuploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/";

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

	// Ajax方式のアップロード画面を表示
	@GetMapping("/quesuploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}

	// ファイルが画像タイプかを判定する
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	// Ajax + JSONリスポンス方式でファイルをアップロード処理する（サムネイル生成対応）
		@PostMapping(value = "/quesuploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<List<QuesAttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

			List<QuesAttachFileDTO> list = new ArrayList<>();
			String uploadFolder = "C:\\upload";

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

				QuesAttachFileDTO attachDTO = new QuesAttachFileDTO();

				String uploadFileName = multipartFile.getOriginalFilename();

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

		// サムネイルなどを画像として表示（ブラウザ表示用）
		@GetMapping("/quesdisplay")
		@ResponseBody
		public ResponseEntity<byte[]> getFile(String fileName) {

			log.info("fileName : " + fileName);

			File file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/" + fileName);

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

		// ファイルをダウンロード（ブラウザ別対応）
		@GetMapping(value="/quesdownload", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){

			Resource resource = new FileSystemResource("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/" + fileName);

			if(!resource.exists()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			String resourceName = resource.getFilename();

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

		// 添付ファイルを削除する（画像のサムネイルも含む）
		@PostMapping("/quesdeleteFile")
		@ResponseBody
		public ResponseEntity<String> deleteFile(String fileName, String type){
			log.info("Delete File : " + fileName);
			File file;
			try {
				file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/" + URLDecoder.decode(fileName, "UTF-8"));
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
