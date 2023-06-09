package com.iss.ua.lark.common.util;

import cn.hutool.core.util.IdUtil;
import com.iss.ua.lark.common.bo.FileBo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author: HansonHu
 * @date: 2023-06-08 14:11
 **/
public class FileUtil {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getNewFileName(String originalFileName) {
        return IdUtil.fastSimpleUUID();
    }

    public static String getNewFilePath(String moudle,String defaultPath,String env) {
        LocalDate now = LocalDate.now();
        String yyyyMMdd = now.format(dateFormat);
        if(StringUtils.isNotBlank(defaultPath)){
            return defaultPath + "/" + env + "/" + moudle + "/" + yyyyMMdd;
        }else{
            return env + "/" + moudle + "/" + yyyyMMdd;
        }

    }

    public static void fillFileBo(MultipartFile file, FileBo fileBo, String defaultPath, String env) {
        String originalFileName = file.getOriginalFilename();
        String newFileName = FileUtil.getNewFileName(originalFileName);
        String newFilePath = FileUtil.getNewFilePath(fileBo.getModuleName(),defaultPath,env);
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        fileBo.setOriginalFileName(file.getOriginalFilename());
        fileBo.setPath(newFilePath);
        fileBo.setSuffix(extension);
        fileBo.setSize(file.getSize());
        fileBo.setFileName(newFileName+"."+extension);
    }


    public static String getContentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (filenameExtension.equalsIgnoreCase(".mp4")) {
            return "video/mp4";
        }
        if (filenameExtension.equalsIgnoreCase(".pdf")) {
            return "application/pdf";
        }
        if (filenameExtension.equalsIgnoreCase(".xls")) {
            return "application/vnd.ms-excel";
        }
        if (filenameExtension.equalsIgnoreCase(".xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        }
        return "image/jpg";
    }
}
