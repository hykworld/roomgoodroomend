package com.room.good.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImageDTO {

    private long pinum;
    private String piuuid; // 이미지 파일명
    private String piimgName; //원본 이미지 파일명
    private String pipath; // 이미지 경로


    public String getImageURL(){
        try {
            return URLEncoder.encode(pipath+"/"+piuuid+"_"+piimgName,"UTF-8");
            // URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";//실패하면 빈칸넘기고 있네
    }
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(pipath+"/"+piuuid+"_"+piimgName,"UTF-8");
            //URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}