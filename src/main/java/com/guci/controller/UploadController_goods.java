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

import com.guci.domain.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController_goods {
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	//파일처리는 스프링에서 제공하는 MultipartFile타입을 이용, 여러개를 선택하기 위해 배열로 설정
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/";

		for(MultipartFile multipartFile : uploadFile ) {
			log.info("-----------------------------------");
			log.info("Upload File Name: "+multipartFile.getOriginalFilename());
			log.info("Upload File Size: "+multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}


	private String getFolder() {
		//년/월/일에 대한 정보를 받아와야 하기 때문에 작성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		//파일구분자를 -로 변경
		return str.replace("-", File.separator);
	}


	//p.513 : 파일이 이미지타입인지 검사하는 메소드 추가
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	//p.517 : AttachFileDTO의 리스트를 반환하는 구조로 변경
	@PostMapping(value="/uploadAjaxAction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/";
		//업로드 폴더 경로 지정
		String uploadFolderPath = getFolder();

		//make folder
		File uploadPath = new File(uploadFolder, getFolder());

		//업로드 경로에 년-월-일에 해당하는 폴더가 존재하지 않으면 새로 만들기-
		if(!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		for(MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO(); //객체생성
			String uploadFileName = multipartFile.getOriginalFilename();

			//IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/")+1);
			log.info("only file name: "+uploadFileName);
			attachDTO.setFileName(uploadFileName); //attachDTO에 파일이름 저장

			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString()+"_"+uploadFileName;

			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				//uuid와 uploadPath 저장
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);

				//이미지타입이면 섬네일 생성
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_"+uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}
				//리스트에 객체 추가
				list.add(attachDTO);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("/display")
	@ResponseBody
	//문자열로 파일의 경로가 포함된 fileName을 파라미터로 받고 byte[]를 전송
	public ResponseEntity<byte[]> getFile(String fileName){
		log.info("fileName: "+fileName);
		//새로운 파일 생성
		File file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/"+fileName);
		log.info("file: "+file);
		ResponseEntity<byte[]> result = null; //초기화

		try {
			//HttpHeaders객체를 생성하여 Content-Type를 추가하고 ResponseEntity에 file을 byte배열로 카피한 것과 헤더, HTTP상태를 전달함
			HttpHeaders header = new HttpHeaders();
			//byte[]로 이미지파일 데이터를 전송할때 브라우저에서 보내주는 MIME타입이 파일의 종류에 따라 바뀜
			//probeContentType()을 이용해 적절한 MIME타입 데이터를 Http의 헤더 메시지에 포함되도록 처리
			header.add("Content-Type",Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	//p.530 첨부파일 다운로드
	//MIME타입이 고정되므로 produces = MediaType.APPLICATION_OCTET_STREAM_VALUE로 작성
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	//ResponseEntity<>의 타입은 byte[]등을 사용할 수 있지만 여기서는 Resource타입을 이용해 간단하게 처리
	public ResponseEntity<Resource> downloadFile(@RequestHeader("User-Agent") String userAgent, String fileName){
		Resource resource = new FileSystemResource("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/"+fileName);
		//resource가 존재하지 않으면 NOT FOUND 에러 발생
		if(!resource.exists()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		//resource객체에 저장된 파일 이름을 가져옴
		String resourceName = resource.getFilename();

		//remove UUID
		String resourceOriginalName = resourceName.substring(resourceName.indexOf("_")+1);

		HttpHeaders headers = new HttpHeaders(); //Headers객체 생성

		try {
			String downloadName = null;
			if(userAgent.contains("Trident")) {
				log.info("IE browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8").replaceAll("/+"," ");
			} else if(userAgent.contains("Edge")) {
				log.info("Edge browser");
				downloadName = URLEncoder.encode(resourceOriginalName,"UTF-8");
				log.info("Edge name: "+downloadName);
			} else {
				log.info("Chrome browser");
				downloadName = new String(resourceOriginalName.getBytes("UTF-8"),"ISO-8859-1");
			}
			log.info("downloadName: "+downloadName);
			//다운로드시 지정되는 이름을 Content-Disposition을 이용해 지정
			//파일이름이 한글일때 깨지는 문제를 막기 위해 문자열처리
			headers.add("Content-Disposition", "attachment; filename="+downloadName);
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	//p.548 서버에서 첨부파일의 삭제
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(String fileName, String type){
		log.info("deleteFile: "+ fileName);
		File file;

		try {
			file = new File("C:/GUCI/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/GUCI12/resources/admin/img/"+URLDecoder.decode(fileName,"UTF-8"));
			file.delete();

			//이미지파일일때는 섬네일도 삭제
			if(type.equals("image")) {
				String largeFileName = file.getAbsolutePath().replace("s_", "");
				log.info("largeFileName: "+largeFileName);
				file = new File(largeFileName);
				file.delete();
			}
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("delete",HttpStatus.OK);
	}


}
