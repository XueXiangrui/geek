package aspect;

import annotation.DataSource;
import config.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by xiangrui.xue on 2021/11/9.
 */
@Aspect
public class DataSourceAspect {

    @Around("dataSourcePointCut()")  //环绕通知  就是为了获取datasource的名字和关闭datasource
    public Object around(ProceedingJoinPoint point) throws Throwable {
//        通过签名获得方法获得注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null){
            DynamicDataSource.setDataSource("write");
            System.out.println("set datasource is " + "write");
        } else {
            DynamicDataSource.setDataSource(ds.name());
            System.out.println("set datasource is " + ds.name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }

    @Pointcut("@annotation(annotation.DataSource)")
    public void dataSourcePointCut(){

    }
}
