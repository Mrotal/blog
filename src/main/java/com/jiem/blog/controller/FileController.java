package com.jiem.blog.controller;

import com.jiem.blog.util.MinioUtil;
import com.jiem.blog.util.Result;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

@RestController
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    MinioUtil minioUtil;

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public Result fileupload(@RequestParam MultipartFile uploadfile, @RequestParam String bucket,
                             @RequestParam(required = false) String objectName) throws Exception {
        minioUtil.createBucket(bucket);
        String url; // d41d8cd98f00b204e9800998ecf8427e
        if (objectName != null) {
            url = minioUtil.uploadFile(uploadfile.getInputStream(), bucket, objectName + "/" + uploadfile.getOriginalFilename());
        } else {
            url = minioUtil.uploadFile(uploadfile.getInputStream(), bucket, uploadfile.getOriginalFilename());
        }
        return Result.success(url);
    }

    @RequestMapping(value = "/listBuckets", method = RequestMethod.GET)
    public Result listBuckets() throws Exception {
        return Result.success(minioUtil.listBuckets());
    }

//    @RequestMapping(value = "/listFiles", method = RequestMethod.GET)
//    public Result listFiles(@RequestParam String bucket) throws Exception {
//        return Result.success(minioUtil.listFiles(bucket));
//    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void downloadFile(@RequestParam String bucket, @RequestParam String objectName,
                             HttpServletResponse response) throws Exception {
        InputStream stream = minioUtil.download(bucket, objectName);
        ServletOutputStream output = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(stream, output);
    }


    @RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
    public Result deleteFile(@RequestParam String bucket, @RequestParam String objectName) throws Exception {
        minioUtil.deleteObject(bucket, objectName);
        return Result.success();
    }

    @RequestMapping(value = "/deleteBucket", method = RequestMethod.GET)
    public Result deleteBucket(@RequestParam String bucket) throws Exception {
        minioUtil.deleteBucket(bucket);
        return Result.success();
    }


    @GetMapping("/copyObject")
    public Result copyObject(@RequestParam String sourceBucket, @RequestParam String sourceObject, @RequestParam String targetBucket, @RequestParam String targetObject) throws Exception {
        minioUtil.copyObject(sourceBucket, sourceObject, targetBucket, targetObject);
        return Result.success();
    }

    @GetMapping("/getObjectInfo")
    public Result getObjectInfo(@RequestParam String bucket, @RequestParam String objectName) throws Exception {

        return Result.success(minioUtil.getObjectInfo(bucket, objectName));
    }

    @GetMapping("/getPresignedObjectUrl")
    public Result getPresignedObjectUrl(@RequestParam String bucket, @RequestParam String objectName, @RequestParam Integer expires) throws Exception {

        return Result.success(minioUtil.getPresignedObjectUrl(bucket, objectName, expires));
    }

//    @GetMapping("/listAllFile")
//    public Result listAllFile() throws Exception {
//
//        return Result.success(minioUtil.listAllFile());
//    }


}