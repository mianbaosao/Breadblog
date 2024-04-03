package com.itmianbao.controller;

import com.itmianbao.pojo.*;
import com.itmianbao.service.DeptService;
import com.itmianbao.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @Autowired
    private DeptService deptService;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws Exception{
       /* String originalFilename=image.getOriginalFilename();
        int index= originalFilename.lastIndexOf(".");
        String extname = originalFilename.substring(index);
        String newFilename = UUID.randomUUID().toString()+extname;
        image.transferTo(new File("D:\\HeWeitao\\csdn\\front\\src\\image\\"+newFilename));
        String url="http://localhost:3001/src/image/" + newFilename; // 前端可访问的绝对 URL*/
        String url=aliOSSUtils.upload(image);
        return Result.success(url);
    }

}
