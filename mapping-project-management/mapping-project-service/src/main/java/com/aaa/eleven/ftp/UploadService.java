package com.aaa.eleven.ftp;

import com.aaa.eleven.model.FtpFile;
import com.aaa.eleven.properties.FtpProperties;
import com.aaa.eleven.utils.FileNameUtils;
import com.aaa.eleven.utils.FtpUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
@Service
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
    /***
     * @Author ftt
     * @Description
     * 文件上传方法
     * @Date 2020/7/10 16:54
     * @Param [file, newFileName]
     * @return FtpFile
     */
    public FtpFile upload(MultipartFile file, String newFileName){
        FtpFile ftpFile = new FtpFile();
        //1.获取文件的远程名称（为了获取后缀名）
        String oldFileName = file.getOriginalFilename();
        //2.判断前端的文件名是否传过来
        if(newFileName == null){
            newFileName = FileNameUtils.getFileName();
        }
        //3.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        //4.获取文件的上传路径（2020/07/10）
        String filePath = DateUtils.formatDate(new Date(),DATE_FORMAT);
        //5.调用文件上传工具类
        try {
            //6.上传文件
            Boolean upload = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath(),
                    filePath, newFileName, file.getInputStream());
            //7.判断上传是否成功
            if(upload){
                //成功
                return ftpFile.setFilePath(filePath).setDir(oldFileName.substring(oldFileName.lastIndexOf(POINT))).setFileName(newFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
