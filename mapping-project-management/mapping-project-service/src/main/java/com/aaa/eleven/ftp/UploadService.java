package com.aaa.eleven.ftp;

import com.aaa.eleven.properties.FtpProperties;
import com.aaa.eleven.utils.FileNameUtils;
import com.aaa.eleven.utils.FtpUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.eleven.staticproperties.RedisProperties.POINT;
import static com.aaa.eleven.staticproperties.TimeForatProperties.DATE_FORMAT;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/10 19:01
 * @Description
 */
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;
    /***
     * @Author ftt
     * @Description
     *  文件上传
     * @Date 2020/7/10 19:10
     * @Param [file]
     * @return boolean
     */
    public boolean upload(MultipartFile file) {
        //获取文件的远程名称(为了获取后缀名)
        String oldFileName = file.getOriginalFilename();
        //生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        //截取后缀名，拼接到新的文件名
        newFileName += oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //获取文件的上传路径
        String filePath = DateUtil.formatDate(new Date(),DATE_FORMAT);
        //调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(),ftpProperties.getPort(),ftpProperties.getUsername(),
                    ftpProperties.getPassword(),ftpProperties.getBasePath(),filePath,newFileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
