package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.ExceptionMsgConstant;
import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 页面跳转
 * 主要功能：
 * 通过后台方法跳转到界面
 * <p>
 * Created by jason on 2016-05-17.
 */
@Controller
public class PageJumpController extends BaseController {

    /**
     * 进入后台首页
     *
     * @return
     */
    @RequestMapping("/system-manage/index")
    public String index() {
        if (ObjectUtils.isEmpty(SessionUtil.getAttribute(SystemConstant.SYSTEM_SESSION_USER))) {
            return SystemConstant.SYSTEM_MANAGE_LOGIN_BY_PAGE;
        }
        return SystemConstant.SYSTEM_MANAGE_HOME_BY_PAGE;
    }

    /**
     * 公共页面跳转
     * 不带数据
     *
     * @param url 目的页面位置
     * @return
     * @throws Exception
     */
    @RequestMapping("/system-manage/gotoPage")
    public String gotoPage(@RequestParam(name = "url") String url) throws Exception {
        if (StringUtils.isEmpty(url)) {
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }
        return url;
    }

}
