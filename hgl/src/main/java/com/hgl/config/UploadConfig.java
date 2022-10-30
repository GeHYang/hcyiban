package com.hgl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class UploadConfig {

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSizePerFile(10*1024*1024); // 每个文件大小最大10M
        multipartResolver.setMaxUploadSize(100 * 1024 * 1024); // 最大大小100M
        return multipartResolver;
    }

}
