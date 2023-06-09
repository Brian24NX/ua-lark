package com.iss.ua.lark.serevice.internal;

import cn.hutool.core.bean.BeanUtil;
import com.iss.ua.lark.common.bo.FileBo;
import com.iss.ua.lark.dao.domain.FilePo;
import com.iss.ua.lark.dao.mappers.FileMapper;
import com.iss.ua.lark.serevice.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: HansonHu
 * @date: 2023-06-08 14:19
 **/
public abstract class AbstractFileService implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public List<FileBo> upload(MultipartFile[] files, FileBo fileBo) {
        List<FileBo> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            FileBo uploadBo = BeanUtil.copyProperties(fileBo, FileBo.class);
            FilePo upload = upload(files[i], uploadBo);
            uploadBo.setId(upload.getId());
            list.add(uploadBo);
        }
        return list;
    }


     @Override
    public InputStream download(String fileId) {
        FilePo filePo = getFilePo(fileId);
        InputStream content = download(filePo);
        return content;
    }

    @Override
    public FilePo getFilePo(String fileId) {
        return fileMapper.selectById(fileId);
    }
}
