package com.room.good.controller;

import com.room.good.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
public class SubControllerKOO {

    @Value("${com.room.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {
        List<UploadResultDTO> resultDTOList = new ArrayList<>();


        ///uploadFiles를 uploadFile여기에 하나씩 !
        for (MultipartFile uploadFile : uploadFiles) {

            if (uploadFile.getContentType().startsWith("image") == false) {
                //이미지파일만 업로드가능
                log.warn("이파일은 이미지타입이 아님");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String originalName = uploadFile.getOriginalFilename();
            //옛날 IE,Edge는 전체경로가 나오므로 파일명만 구한다.
            //현재 IE사용 불가, Edge는 크로미움으로 바뀌었기 때문에 필요없는 코드가 됨.
            String fileName =
                    originalName.substring(originalName.lastIndexOf("\\") + 1);
            //파일 경로에서 마지막 \ 다음에 있는 문자열(즉, 파일 이름)을 추출하는 것입니다.
            log.info("fileName : " + fileName);
            log.info("originalName : " + originalName);
            String folderPath = makeFolder();
            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파잏 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);

            try {

                uploadFile.transferTo(savePath);
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator
                        +"s_" + uuid +"_" + fileName;
                //섬네일 파일 이름은 중간에 s_로 시작하도록
                File thumbnailFile = new File(thumbnailSaveName);
                //썸네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,360,270 );
                resultDTOList.add(new UploadResultDTO(fileName,uuid,folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }//포문 끝

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }


    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        log.info("======================================str:"+str);

        String folderPath = str.replace("//", File.separator);
        log.info("======================================folderPath:"+folderPath);
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName){
        ResponseEntity<byte[]> result = null;

        try{
            String srcFileName = URLDecoder.decode(fileName,"UTF-8");
            log.info("filename:"+srcFileName);
            File file = new File(uploadPath+File.separator+srcFileName);
            log.info("file:"+file);
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            //probeContentType 컨텐트타입을 구하는거 getcontenttype이랑 똑같음
            //컨텐트 타입을 지정해주는거구나
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),header,HttpStatus.OK);


        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        };
        return result;
    };

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){

        String srcFileName=null;
        try{
            srcFileName = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();
            File thumbnail = new File(file.getParent(),"s_"+file.getName());
            //uploadPath + File.separator +"s_"+srcFileName 이거랑 같다! 위에!
            result = thumbnail.delete();

            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    };



//    @GetMapping("/cart")
//    public ResponseEntity<Boolean> cartIn(Principal principal, Long pno){
//
//        String email = principal.getName();// 이메일일거임 아마
//        log.info(email+"cartController_principal.getName()");
//        //cartservice.additem(email,pno);
//        //
//
//
//        return new ResponseEntity<>(true,HttpStatus.OK);
//    };



}
