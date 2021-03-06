package com.eduonline.eduoss.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.eduonline.common.R;

import com.eduonline.eduoss.handler.ConstantPropertiesUtil;

import org.joda.time.DateTime;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 文件上传
 */
@CrossOrigin //解决跨域
@RestController
@RequestMapping("/eduoss")
public class FileUpluadController {

    /**
     * 封面头像上传
     * @param file
     * @param cover
     * @return
     */
    @PostMapping("upload")
    public R uploadTeacherImg(@RequestParam("file") MultipartFile file,
                             @RequestParam(value = "host",required = false) String cover) {
        try {
            //获取文件
            String filename = file.getOriginalFilename();
            InputStream in = file.getInputStream();

            // 地域节点
            String endpoint = ConstantPropertiesUtil.ENDPOINT;
            //阿里云bucketName
            String bucketName = ConstantPropertiesUtil.BUCKETNAME;
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维.
            String accessKeyId = ConstantPropertiesUtil.KEYID;
            String accessKeySecret = ConstantPropertiesUtil.KEYSECRET;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取头像文件夹名称
            String hostName = ConstantPropertiesUtil.HOST;
            //如果上传头像host为空 上传封面host有值
            if (!StringUtils.isEmpty(cover)) { //不为空 该次为封面上传
                hostName = "cover";
            }

            //使用UUID解决重名
            String uuid = UUID.randomUUID().toString();
            filename = uuid + filename;

            //获取当前时间
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //对上传文件按时间分类 2020/2/24/xxxxx
            filename = filePath + "/"+hostName+"/"+ filename;
            // 上传文件流。
            ossClient.putObject(bucketName, filename, in);

            // 关闭OSSClient。
            ossClient.shutdown();



            //拼接要返回的文件路径
            String path = "http://" + bucketName + "." + endpoint +"/" + filename;
            System.out.println(path);
            return R.ok().data("imgUrl", path);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

}
