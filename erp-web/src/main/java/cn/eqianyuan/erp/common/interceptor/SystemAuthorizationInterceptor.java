package cn.eqianyuan.erp.common.interceptor;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.util.SessionUtil;
import cn.eqianyuan.erp.entity.SystemUserVo;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统管理用户授权校验
 * Created by jason on 2016/1/9.
 */
public class SystemAuthorizationInterceptor implements HandlerInterceptor {

    /**
     * 校验当前会话用户是否已经登录并且存在有效session
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /**
         * 从会话中获取系统用户信息
         * 如果用户信息为空，则返回登录页面，否则继续向下执行
         */
        SystemUserVo systemUserVo = (SystemUserVo) SessionUtil.getAttribute(SystemConstant.SYSTEM_SESSION_USER);
        if (ObjectUtils.isEmpty(systemUserVo)) {
            httpServletResponse.sendRedirect("http://"+httpServletRequest.getServerName());
            return false;
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
