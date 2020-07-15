package com.aaa.eleven.annotation;

import com.aaa.eleven.model.LoginLog;
import com.aaa.eleven.model.User;
import com.aaa.eleven.service.MappingProjectService;
import com.aaa.eleven.utils.AddressUtils;
import com.aaa.eleven.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.util.DateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import static com.aaa.eleven.staticproperties.TimeForatProperties.TIME_FORMAT;

/**
 * @Company
 * @Author ftt
 * @Date Create in 2020/7/15 16:21
 * @Description
 */
@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private MappingProjectService mappingProjectService;
    /***
     * @Author ftt
     * @Description
     * 定义切点信息，当自定义注解存在的时候，切面触发
     * @Date 2020/7/15 16:23
     * @Param []
     * @return void
     */
    @Pointcut("@annotation(com.aaa.eleven.annotation.LoginAnnotation)")
    public void pointcut(){
    }
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws ClassNotFoundException{
        Object result = null;
        try{
            result = proceedingJoinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        //获取request对象
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        //获取ip地址 getIpAddr需要一个request对象
        String ipAddr = IPUtils.getIpAddr(request);
        //获取地理位置
        Map<String, Object> map = AddressUtils.getAddresses(ipAddr, "UTF-8");

        LoginLog loginLog = new LoginLog();
        loginLog.setIp(ipAddr);
        loginLog.setLocation(map.get("province")+"|"+map.get("city"));
        loginLog.setLoginTime(DateUtil.formatDate(new Date(),TIME_FORMAT));
        //获取username ，先获取目标方法的参数
        Object[] args = proceedingJoinPoint.getArgs();
        User user = (User) args[0];

        loginLog.setUsername(user.getUsername());
        //获取操作类型及具体操作内容
        //获取目标类名
        String tarClassName = proceedingJoinPoint.getTarget().getClass().getName();
        //获取目标方法名
        String tarMethodName = proceedingJoinPoint.getSignature().getName();
        //获取类对象
        Class tarClass = Class.forName(tarClassName);
        //获取目标类的所有方法
        Method[] methods = tarClass.getMethods();
        String operationType = "";
        String operationName = "";
        for(Method method : methods){
            String methodName = method.getName();
            if(tarMethodName.equals(methodName)){
                //名字一样，但是为了重载
                //进一步，判断
                // 获取目标方法的参数
                Class[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == args.length){
                    //获取目标方法
                   operationName = method.getAnnotation(LoginAnnotation.class).operationName();
                   operationType = method.getAnnotation(LoginAnnotation.class).operationType();
                }
            }
        }
        loginLog.setOperationName(operationName);
        loginLog.setOperationType(operationType);
        mappingProjectService.addLoginLog(loginLog);
        return result;
    }


}
