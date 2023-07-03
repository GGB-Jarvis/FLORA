package com.num.shop.controller;


import com.num.shop.util.UpPhotoNameUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class UploadController {
    private String UPLOAD_PATH = "D://upload/";

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   HttpServletRequest request) {
        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = UpPhotoNameUtils.getPhotoName("img", imageFileName);
            Path path = Paths.get(UPLOAD_PATH + fileName);
            Files.write(path, bytes);
            System.out.println(fileName + "\n");
            //return "http://artvery.txwsyun.cn:8080/numshop/getImage/" + fileName;
            if(fileName.endsWith(".mp3")){
                return "http://localhost:8080/numshop/getMusic/" + fileName;
            }else{
                return "http://localhost:8080/numshop/getImage/" + fileName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    //使用流将图片输出
    @GetMapping("/getImage/{name}")
    public void getImage(HttpServletResponse response, @PathVariable("name") String name) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(Files.readAllBytes(Paths.get(UPLOAD_PATH).resolve(name)));
        outputStream.flush();
        outputStream.close();
    }

    @GetMapping("/getMusic/{name}")
    public ResponseEntity<byte[]> getMusic(HttpServletRequest request, @PathVariable("name") String name) throws IOException {
        //构建将要下载的文件对象
        File file = new File(UPLOAD_PATH + File.separator + name);
        //设置响应头
        HttpHeaders headers=new HttpHeaders();
        //下载显示的文件名，解决中文名称乱码问题
        String downloadFileName=new String(name.getBytes("UTF-8"),"ISO-8859-1");
        //通知浏览器以下载方式(attachment)打开文件
        headers.setContentDispositionFormData("attachment",downloadFileName);
        //定义以二进制流数据(最常见的文件下载)的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //使用spring mvc框架的ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }


}