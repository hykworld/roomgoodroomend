package com.room.good.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URLEncoder;

@Getter
@Setter
public class ProductImageDTO {

    private String piuuid; // 이미지 파일명
    private String piimgName; //원본 이미지 파일명
    private String pipath; // 이미지 경로

    public String getImageURL(){
        try {
            return URLEncoder.encode(pipath+"/"+piuuid+"_"+piimgName,"UTF-8");
            // URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";//실패하면 빈칸넘기고 있네
    }
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(pipath+"/"+piuuid+"_"+piimgName,"UTF-8");
            //URLEncoder.encode 이렇게 인코딩하는 경우 예외발생이 잦대요~ 그래서 트라이캐치를 이미지할때는 다 쓰는 듯합니다
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}