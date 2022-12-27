package org.zerock.sony.product.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResultDTO {
	private String fileName;
    private String uuid;
    private String folderPath;

    // 원본 이미지
    public String getImageURL(){
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    // 섬네일
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}