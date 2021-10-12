package com.qys.server.service;


import com.qys.server.dao.FileMapper;
import com.qys.server.entity.UploadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class FileServiceiml implements FileService{

    @Autowired
    FileMapper fileMapper;


    @Override
    public int add(UploadFile uploadFile ) {

        fileMapper.addFile(uploadFile);
        return 0;
    }

    @Override
    public UploadFile findFileUrl(String uiid) {
        return fileMapper.findUrl(uiid);
    }
}
