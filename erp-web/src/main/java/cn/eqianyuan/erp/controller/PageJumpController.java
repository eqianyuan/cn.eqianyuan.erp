package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.util.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return SystemConstant.MANAGE_FISCAL_DETAIL_LIST_BY_PAGE;
    }

}
