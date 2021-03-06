package com.eduonline.eduvideo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger基本配置文件
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     * webAPI
     * @return
     */
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
//                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("阿里云视频上传 API文档")  //标题
                .description("本文档描述了视频上传接口定义")//描述
                .version("1.0") //版本
                //作者信息
                .contact(new Contact("Chenxinyi", "http://chenxinyi.top", "1170398847@qq.com"))
                .build();
    }

    /**
     * 管理员API
     * @return
     */

//    @Bean
//    public Docket adminApiConfig(){
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("adminApi")
//                .apiInfo(webApiInfo())
//                .select()
//                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
//                .build();
//    }




//    private ApiInfo adminApiInfo(){
//
//        return new ApiInfoBuilder()
//                .title("后台管理系统-课程中心API文档")
//                .description("本文档描述了后台管理系统课程中心微服务接口定义")
//                .version("1.0")
//                .contact(new Contact("Chenxinyi", "http://chenxinyi.top", "1170398847@qq.com"))
//                .build();
//    }
}
