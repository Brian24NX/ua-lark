package com.iss.ua.lark.serevice.internal;

/**
 * @author: HansonHu
 * @date: 2023-06-08 13:58
 **/
import cn.hutool.core.bean.BeanUtil;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.iss.ua.lark.common.bo.FileBo;
import com.iss.ua.lark.common.util.FileUtil;
import com.iss.ua.lark.dao.domain.FilePo;
import com.iss.ua.lark.dao.mappers.FileMapper;
import com.iss.ua.lark.serevice.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;

import java.io.IOException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jiawei.zhao
 * Date: 2021/05/24 15:31
 */
@Service("S3FileServiceImpl")
@Slf4j
public class S3FileServiceImpl extends AbstractFileService implements FileService {
    @Value("${aws.s3.bucketName}")
    private  String bucketName;
    @Value("${aws.s3.defaultPath}")
    private  String defaultPath;
    @Value("${aws.s3.endPoint}")
    private String endPoint ="test.s3.cn-north-1.amazonaws.com.cn";
    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private FileMapper fileMapper;

    public InputStream download(FilePo filePo){
        S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucketName,filePo.getFileName()));
        InputStream content = s3Object.getObjectContent();
        return content;
    }

    @Override
    public FilePo upload(MultipartFile file, FileBo fileBo){
        try {
            FileUtil.fillFileBo(file, fileBo,defaultPath,env);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(FileUtil.getContentType(fileBo.getSuffix()));
            objectMetadata.setContentLength(file.getSize());
            //调用S3上传文件
            PutObjectResult putObjectRequest = amazonS3.
                    putObject(new PutObjectRequest(bucketName, fileBo.getFileName(), file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
            fileBo.setUrl("https://"+endPoint+"/"+fileBo.getPath()+"/"+fileBo.getFileName());
            FilePo filePo = BeanUtil.copyProperties(fileBo, FilePo.class);
            fileMapper.insert(filePo);
        } catch (IOException e) {
            log.error("file upload failed",e);
        }
        QueryWrapper<FilePo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FilePo::getFileName,fileBo.getFileName());
        return fileMapper.selectOne(queryWrapper);
    }
}