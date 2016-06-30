package cn.eqianyuan.erp.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * session操作工具
 * Created by jason on 2016/1/11.
 */
public class SessionUtil {

    /**
     * 禁止多次构造对象
     */
    private SessionUtil(){}

    public static Object getAttribute(String name) {
        Object sessionObj = null;
        try {
            sessionObj = getSession().getAttribute(name);
        } catch (Exception e) {
            sessionObj = null;
        }finally{
            return sessionObj;
        }
    }

    public static void setAttribute(String name, Object value){
        getSession().setAttribute(name, value);
    }

    private static HttpSession getSession(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    public static void removeAttribute(String key){
        getSession().removeAttribute(key);
    }
}
