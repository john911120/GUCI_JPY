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


@Controller
@Log4j
public class QuesUploadController {
	// (495)
	@GetMapping("/quesuploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	// (508) 중복 이름 첨부파일 처리하기
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}


	// (497)
	// (504) Jquery를 이용한 전송방식(일부 코드 수정)

	@PostMapping("/quesuploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/";// 업로드 되는 파일 경로를 지정

		for(MultipartFile multipartFile : uploadFile) {
			log.info("--------------------------------------");
			log.info("Upload File Name : " + multipartFile.getOriginalFilename());
			log.info("Upload File Size : " + multipartFile.getSize());

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE 사용자를 위한 코드
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("Only file name : " + uploadFileName);

			// (499) transferTo()으로 업로드 되는 파일을 저장한다.
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			} // try~catch 종료지점
		} //for문 종료 지점

	}

	//(500) ajax으로 파일 업로드하기
	@GetMapping("/quesuploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}

	// (513) 이미지 파일 판단
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}


	// (504) Jquery를 이용한 전송방식(일부 코드 수정)
	// (508) 중복 이름 첨부파일 처리하기(일부 코드 수정)
	// (510) 중복 방지를 위한 UUIO 적용(일부 코드 수정) + UUID 범용 고유 식별자이다.
	// (514~5) 이미지타입이라면 섬네일을 생성하는 코드 추가 (일부 코드 수정)
	// (517~8) AttachFileDTO리스트 반환하는 구조로 변경 (일부 코드 수정)
		@PostMapping(value = "/quesuploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		public ResponseEntity<List<QuesAttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {

			List<QuesAttachFileDTO> list = new ArrayList<>();
			String uploadFolder = "C:\\upload";// 업로드 되는 파일 경로를 지정

			String uploadFolderPath = getFolder();
			// 폴더 생성
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

				// IE 사용자를 위한 코드
				uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
				log.info("Only file name : " + uploadFileName);
				attachDTO.setFileName(uploadFileName);

				//(511)
				UUID uuid = UUID.randomUUID();

				uploadFileName = uuid.toString() + "_" + uploadFileName;

				try {
//				File saveFile = new File(uploadFolder, uploadFileName);
					File saveFile = new File(uploadPath, uploadFileName);
					multipartFile.transferTo(saveFile);

					attachDTO.setUuid(uuid.toString());
					attachDTO.setUploadPath(uploadFolderPath);
					// 이미지 타입의 파일을 확인하는 코드
					if(checkImageType(saveFile)) {
						attachDTO.setImage(true);
						FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
						Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
						thumbnail.close();
					}
					list.add(attachDTO);
				} catch (Exception e) {
					log.error(e.getMessage());
				} // try~catch 종료지점
			} //for문 종료 지점
			return new ResponseEntity<>(list, HttpStatus.OK);
		}

		// (526) UploadController에서 섬네일 데이터 전송하기
		// http://localhost:8080/display?fileName=2020/12/23/1.jpg 이미지가 출력이 된다.
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

		// (531) 첨부파일 다운로드
		// (532) ResponseEntity<>를 처리하기 위한 코드 일부 수정
		// (533) IE/Edge 브라우저 문제 해결을 위한 코드 수정
		// (539) 순수 다운로드 파일명으로 저장될 수 있도록 코드를 수정한다.
		@GetMapping(value="/quesdownload", produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
		@ResponseBody
		public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
//			log.info("download file : " + fileName);

			Resource resource = new FileSystemResource("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/question/" + fileName);

			if(!resource.exists()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			// log.info("resource : " + resource);

			String resourceName = resource.getFilename();

			// UUID를 제거하는 코드
			String resourceOriginalName = resourceName.substring(resourceName.indexOf("_") + 1);

			HttpHeaders headers = new HttpHeaders();

			try {
				String downloadName = null;

				if(userAgent.contains("Trident")) {
					log.info("IE browser");

					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
					// IE에서는 이렇게 인코딩을 해야 한글이 깨지지 않고 다운로드가 가능해진다.
					// http://localhost:8080/download?fileName=%EA%B7%B8%EB%9E%98%ED%94%BD%EC%B9%B4%EB%93%9C%20%EA%B2%AC%EC%A0%81.txt
				} else if(userAgent.contains("Edge")) {
					log.info("Edge Browser");

					downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");

					log.info("Edge Name: " + downloadName);
					// Edge와 크롬 브라우저에서는 한글로 정상적으로 작동이 가능하다.
					// http://localhost:8080/download?fileName=그래픽카드 견적.txt
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

		// (548) 서버에서 첨부파일 제거하기
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
