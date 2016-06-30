package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.response.ServerResponse;
import cn.eqianyuan.erp.common.util.yaml.YamlForSystemErrMsg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jason on 2016-05-22.
 */
public class BaseController {

    /**
     * 系统异常代码
     */
    private static final String EXCEPTION_CODE = "10000001";

    /**
     * 用于统一处理异常信息
     *
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ServerResponse exception(Exception ex) {
        if (ex instanceof EqianyuanException) {
            return new ServerResponse(YamlForSystemErrMsg.getMsgBykey(ex.getMessage(), YamlForSystemErrMsg.SYSTEM_ERR_MSG_KEY_BY_CODE),
                    YamlForSystemErrMsg.getMsgBykey(ex.getMessage(), YamlForSystemErrMsg.SYSTEM_ERR_MSG_KEY_BY_CN));
        } else {
            return new ServerResponse(YamlForSystemErrMsg.getMsgBykey(EXCEPTION_CODE, YamlForSystemErrMsg.SYSTEM_ERR_MSG_KEY_BY_CODE),
                    YamlForSystemErrMsg.getMsgBykey(EXCEPTION_CODE, YamlForSystemErrMsg.SYSTEM_ERR_MSG_KEY_BY_CN));
        }
    }

}
