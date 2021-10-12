package controller;

import util.HttpUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;

public class Upload {
    public static String uploadFile(String uploadFilePaths) throws Exception {

        HttpURLConnection connection = HttpUtil.creatUtil("http://127.0.0.1:8080/test/upload", "POST");

        String fileType=uploadFilePaths.substring(uploadFilePaths.indexOf("."));
        File file=new File(uploadFilePaths);
        if (!file.exists()){
            return "指定文件路径不存在";
        }
        //设置文件参数
        connection.setRequestProperty("fileSize", String.valueOf(file.length()));
        connection.setRequestProperty("fileType",fileType);
        connection.setRequestProperty("fileName", URLEncoder.encode(file.getName(), "UTF-8"));
        LocalDate today = LocalDate.now(); //获取当前日期 年月日
        connection.setRequestProperty("time",today.toString());
        /*连接*/

        OutputStream out=new DataOutputStream(connection.getOutputStream());
        /*取得文件的FileInputStream*/
        FileInputStream fStream=new FileInputStream(uploadFilePaths);
        /*设置每次写入1024bytes*/
        int bufferSize=2048;
        byte[] buffer=new byte[bufferSize];
        int length=-1;
        /*将文件读取数据至缓冲区*/
        while ((length=fStream.read(buffer))!=-1){
            /*将资料写入DataoutStream*/
            out.write(buffer,0,length);
        }
        fStream.close();
        out.flush();
        /*取得Respons内容*/
        InputStream is=connection.getInputStream();
        int ch;
        StringBuffer b=new StringBuffer();
        while ((ch=is.read())!=-1){
            b.append((char)ch);
        }
        /*将Response打印*/
        System.out.println("上传成功"+b.toString().trim());
        out.close();
        return "上传成功";
    }
}
