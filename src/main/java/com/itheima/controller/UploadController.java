package com.itheima.controller;

import com.aliyuncs.exceptions.ClientException;
import com.itheima.pojo.Result;
import com.itheima.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")  //添加类的公共路径
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    //本地存储
   /* @PostMapping
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {  //文件上传，此处会接收到一组临时文件
        log.info("文件上传：{},{},{}",username,age,image);
        //获取原始文件名
        String originalFilename = image.getOriginalFilename();

        //构造唯一的文件名（非重复）--uuid（通用唯一识别码）
        int index = originalFilename.lastIndexOf(".");  //获取文件拓展名称的位数
        String extname = originalFilename.substring(index);  //获取拓展名
        String newFileName = UUID.randomUUID().toString() + extname; //拼接新文件名
        log.info("新的文件名：{}",newFileName);

        //将文件存储在服务器的磁盘目录中
        image.transferTo(new File("D:\\code\\tlias-file\\"+newFileName));
        return Result.success();
    }*/
    @PostMapping
    public Result upload(MultipartFile image) throws IOException, ClientException {
        log.info("文件上传名称为: {}",image.getOriginalFilename());
        //调用阿里云OSS工具类进行上传文件
        String url = aliOSSUtils.upload(image);
        log.info("文件上传完成，访问url为: {}",url);

        return Result.success(url);

    }
}
