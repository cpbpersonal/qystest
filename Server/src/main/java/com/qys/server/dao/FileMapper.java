package com.qys.server.dao;


import com.qys.server.entity.UploadFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    public int addFile(UploadFile uploadFile);
    public UploadFile findUrl(String uuid);
}

