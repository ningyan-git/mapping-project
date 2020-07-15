package com.aaa.eleven.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/10 18:27
 * @Description
 */
public class FtpUtils {
    private FtpUtils(){}
    public static boolean upload(String host, Integer port, String username, String password,
                                 String basePath, String filePath, String fileName, InputStream inputStream){
        //创建临时路径
        String tempPath = "";
        //创建FTPClient对象(这个对象是FTP为java提供的API)
        FTPClient ftpClient = new FTPClient();
        try{
            //定义返回状态码
            int replyCode;
            //连接FTP
            ftpClient.connect(host,port);
            //登录ftp
            ftpClient.login(username,password);
            //接收返回状态码
            replyCode = ftpClient.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                //连接失败
                ftpClient.disconnect();
                return  false;
            }
            //检测上传目录是否存在
            // basePath:/home/ftp/www
            // filePath: /2020/07/10
            // --->/home/ftp/www/2020/07/10
            if(!ftpClient.changeWorkingDirectory(basePath+filePath)){
                //文件夹并不存在
                //创建文件夹
                String[] dirs = filePath.split("/");
                //把basePath赋值给临时路径
                tempPath = basePath;
                for(String dir : dirs){
                    if(null == dir || StringUtils.isEmpty(dir)){
                        //没有数据
                        continue;
                    }
                    //拼接临时路径
                    tempPath += "/" + dir;
                    //再次检测tempPath是否存在
                    if(!ftpClient.changeWorkingDirectory(tempPath)){
                        //文件不存在，创建文件夹
                        if(!ftpClient.makeDirectory(tempPath)){
                            //说明文件夹创建失败
                            return  false;
                        }else {
                            //严谨判断，判断创建的目录确实存在
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //把问津转换为二进制的形式进行上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            //文件真正上传
            if(!ftpClient.storeFile(fileName,inputStream)){
                //上传失败
                return false;
            }
            //关闭输入流
            inputStream.close();
            //退出ftp
            ftpClient.logout();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(ftpClient.isConnected()){
                try {
                    //还在连接中
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  true;
    }
}
