package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {

    private String fileName;
    private String uuid;
    private String folderPath;


    //import java.net.URLEncoder;
        // Java에서 URL을 안전하게 인코딩하는 데 사용되는 클래스입니다.
        // URL 인코딩은 URL에 포함되는 특수 문자나 공백 등을 안전한 형태로 변환하는 프로세스를 말합니다.
        //쿼리 문자열 인코딩:URL의 쿼리 문자열(query string)에 포함되는 값들을 안전하게 인코딩하여 서버로 전송합니다.
        //URL 경로 인코딩: URL 경로(path)에 포함되는 값들을 안전하게 인코딩하여 잘못된 해석을 방지합니다.

        // Decoder : 인코딩된 문자열을 원래의 형태로 디코딩하는 데 사용됩니다.
    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
            // URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
            // URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
