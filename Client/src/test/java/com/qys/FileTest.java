package com.qys;

import controller.Download;
import controller.FileData;
import controller.Upload;
import org.junit.Test;

public class FileTest {
    /*测试下载功能*/
    @Test
    public void Down() throws Exception {
        System.out.println(Download.downFile("f197574f-b8fc-4b75-88c3-83684f842a7f","d"));
    }
    /*测试上传功能*/
    @Test
    public void upload() throws Exception {
        System.out.println( Upload.uploadFile("D:\\image.jpg"));
    }
    /*测试上传功能*/
    @Test
    public void fileData() throws Exception {
        System.out.println( FileData.getData("f197574f-b8fc-4b75-88c3-83684f842a7f"));
    }
}
