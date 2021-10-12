package com.qys.server.service;



import com.qys.server.entity.UploadFile;

import javax.servlet.http.HttpServletRequest;

public interface FileService {

    public int add(UploadFile uploadFile );
    public UploadFile findFileUrl(String uuid);
}
