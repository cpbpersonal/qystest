package com.qys.server.controller;

import com.qys.server.entity.UploadFile;
import com.qys.server.service.FileService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.sql.Date;
import java.util.UUID;

@Controller
@RequestMapping("/test")
public class FileServer {
    private  String dirPath="D:\\up\\";

    @Autowired
    FileService fileService;
    private final Logger logger =  LoggerFactory.getLogger(getClass());

    /*上传Controller*/
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String FileUploadServer(HttpServletRequest request, HttpServletResponse response)  throws Exception {


        String uuid="";
        uuid= UUID.randomUUID().toString();
        logger.info("接收文件的UUID="+uuid);
        InputStream inStream = request.getInputStream();

        File file=new File(dirPath+"\\"+request.getHeader("time"));
        if(!file.exists()){
            file.mkdir();
          logger.info("新建文件夹");
        }


        File newFile = new File( dirPath+request.getHeader("time")+"\\"+uuid+(request.getHeader("filetype")));
        FileOutputStream fos=new FileOutputStream(newFile);
        byte[] buffer = new byte[2048];
        int len;
        while ((len=inStream.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }

        /*获取文件参数存入数据库*/
        /*文件大小*/
        Long size=Long.valueOf(request.getHeader("fileSize"));
        /*文件类型*/
        String type=request.getHeader("fileType");
        /*原始文件名*/
        String oldName= URLDecoder.decode(request.getHeader("fileName"),"UTF-8");
        /*创建时间*/
        Date time=new Date(System.currentTimeMillis());
        /*文件保存目录*/
        String url=dirPath+request.getHeader("time")+"\\"+uuid+(request.getHeader("filetype"));
        /*文件UUID*/
        String uuidName=uuid;
        UploadFile uploadFile=new UploadFile(size,type,oldName,time,url,uuidName);
        logger.info("上传文件详情"+uploadFile);

        fileService.add(uploadFile);
        inStream.close();
        fos.close();
        logger.info("上传成功");
        return uuid;
    }

    /*下载Controller*/
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    @ResponseBody
    public void FileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UploadFile uploadfile = fileService.findFileUrl(request.getHeader("uuid"));
        if(uploadfile==null){
            response.setStatus(401);
            logger.info("文件不存在");
            return;
        }
        /*定位数据源*/
        File f=new File(uploadfile.getUrl());
        /*建立管道*/
       InputStream in=new FileInputStream(f);
       //OutputStream out=new FileOutputStream("D:\\down\\a"+uploadfile.getType());
        OutputStream out=new DataOutputStream(response.getOutputStream());
        response.setHeader("fileType",uploadfile.getType());
        response.setHeader("uuid",uploadfile.getUuidName());
        /*操作管道*/
       byte[] buf=new byte[2048];
       int bytesRead;
       while ((bytesRead=in.read(buf))>0){
           out.write(buf,0,bytesRead);
       }
        in.close();
        out.close();
        logger.info("文件下载成功");

    }

    /*获取文件元数据*/
    @RequestMapping(value = "/fileinfo",method = RequestMethod.GET)
    @ResponseBody
    public UploadFile getFileInfo(String uuid) throws IOException {
        UploadFile uploadfile = fileService.findFileUrl(uuid);

        return uploadfile;
    }
}
