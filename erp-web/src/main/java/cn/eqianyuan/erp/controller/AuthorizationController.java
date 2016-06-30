package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.response.ServerResponse;
import cn.eqianyuan.erp.common.util.SessionUtil;
import cn.eqianyuan.erp.common.util.VerifyCodeUtils;
import cn.eqianyuan.erp.entity.SystemUserVo;
import cn.eqianyuan.erp.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户授权
 * 主要功能：
 * 获取验证码
 * 用户登录
 * 用户登出
 * <p>
 * Created by jason on 2016-05-17.
 */
@Controller
public class AuthorizationController extends BaseController {

    @Autowired
    private AuthorizationService authorizationService;

    /**
     * 获取验证码
     *
     * @param verifyCodeLength 验证码内容字符长度
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping("/verifyCode")
    public void verifyCode(@RequestParam(name = "verify_code_length", required = false, defaultValue = "4") Integer verifyCodeLength,
                           HttpServletResponse response) throws EqianyuanException, IOException {
        String verifyCode = VerifyCodeUtils.random(verifyCodeLength);

        /**
         * 将验证码内容写入session
         */
        SessionUtil.setAttribute(SystemConstant.VERIFY_CODE, verifyCode);

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        VerifyCodeUtils.render(verifyCode, sos, 120, 30);
        sos.close();
    }

    /**
     * 带验证码登录
     *
     * @param userName   用户名
     * @param password   密码
     * @param verifyCode 验证码
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping("/system-manage/login")
    @ResponseBody
    public ServerResponse login(@RequestParam(value = "user_name") String userName,
                                @RequestParam String password,
                                @RequestParam(value = "verify_code") String verifyCode) throws EqianyuanException {
        SystemUserVo systemUserVo = authorizationService.login(userName, password, verifyCode);

        /**
         * 将用户VO对象写入session
         */
        SessionUtil.setAttribute(SystemConstant.SYSTEM_SESSION_USER, systemUserVo);
        return new ServerResponse();
    }

    /**
     * 退出登录
     *
     * @return
     * @throws EqianyuanException
     */
    @RequestMapping("/system-manage/logout")
    public String logout() throws EqianyuanException {
        /**
         * 将用户VO对象从session中移除
         */
        SessionUtil.removeAttribute(SystemConstant.SYSTEM_SESSION_USER);
        return SystemConstant.SYSTEM_MANAGE_LOGIN_BY_PAGE;
    }

}
