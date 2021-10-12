package controller;

import util.HttpUtil;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class Download {

   public  static  String downFile(String uuid, String downPath ) throws Exception {
       HttpURLConnection connection = HttpUtil.creatUtil("http://127.0.0.1:8080/test/download", "GET");
       connection.setRequestProperty("uuid",uuid);
       connection.connect();
       if(connection.getResponseCode()!=200){
           return "异常状态码"+connection.getResponseCode();
       }
       else{
           InputStream in= connection.getInputStream();
            String fileType= connection.getHeaderField("filetype");
           int length=in.available();
           OutputStream out=new FileOutputStream("D:\\down\\"+uuid+fileType);
           byte[] buf=new byte[1024];
           int bytesRead;
           while ((bytesRead=in.read(buf))>0){
               out.write(buf,0,bytesRead);
           }
            return "下载成功";
       }
   }
}
