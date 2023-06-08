package com.iss.ua.lark.serevice.internal;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iss.ua.lark.common.bo.FileBo;
import com.iss.ua.lark.common.util.FileUtil;
import com.iss.ua.lark.dao.domain.FilePo;
import com.iss.ua.lark.dao.mappers.FileMapper;
import com.iss.ua.lark.serevice.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service("OssFileServiceImpl")
public class OssFileServiceImpl extends AbstractFileService implements FileService {
    @Value("${aliyun.oss.bucketName}")
    private  String bucketName;
    @Value("${aliyun.oss.defaultPath}")
    private  String defaultPath;
    @Value("${aliyun.oss.expiration-ms:31536000000}")
    private Long expirationConfig;
    @Value("${spring.profiles.active}")
    private String env;


    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private OSS aliyunOssClient;

    @Override
    public FilePo upload(MultipartFile file, FileBo fileBo) {
        try {
            FileUtil.fillFileBo(file, fileBo,defaultPath,env);
            //上传
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getInputStream().available());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentType(FileUtil.getContentType(fileBo.getSuffix()));
            aliyunOssClient.putObject(bucketName, fileBo.getPath()+"/"+fileBo.getFileName(), file.getInputStream(),metadata);
            // 指定签名URL过期时间一年。
            Date expiration = new Date(System.currentTimeMillis() +  expirationConfig);
            URL url = aliyunOssClient.generatePresignedUrl(bucketName, fileBo.getFileName(), expiration);
            fileBo.setUrl("https://"+url.getHost()+"/"+fileBo.getPath()+"/"+fileBo.getFileName());
            FilePo filePo = BeanUtil.copyProperties(fileBo, FilePo.class);
            fileMapper.insert(filePo);
        } catch (IOException e) {
            log.error("file upload failed",e);
        }
        QueryWrapper<FilePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FilePo::getFileName,fileBo.getFileName());
        return fileMapper.selectOne(queryWrapper);
    }



    @Override
    public InputStream download(FilePo filePo) {
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = aliyunOssClient.getObject(bucketName, filePo.getPath()+"/"+filePo.getFileName());
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        return content;
    }
}
