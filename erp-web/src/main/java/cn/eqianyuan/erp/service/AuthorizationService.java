package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.entity.SystemUserBo;
import cn.eqianyuan.erp.entity.SystemUserVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户授权登录操作数据转换及业务主方法调用实现类
 * 主要功能接口
 * 登录
 * 登出
 * Created by jason on 2016-05-18.
 */
@Service
public class AuthorizationService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IAuthorizationService authorizationService;

    /**
     * 无验证码的登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 系统用户VO对象
     * @throws EqianyuanException
     */
    public SystemUserVo login(String userName, String password) throws EqianyuanException {
        SystemUserBo systemUserBo = authorizationService.login(userName, password);
        SystemUserVo systemUserVo = new SystemUserVo();
        BeanUtils.copyProperties(systemUserBo, systemUserVo);
        return systemUserVo;
    }

    /**
     * 带验证码的登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param code     登录验证码
     * @return 系统用户VO对象
     * @throws EqianyuanException
     */
    public SystemUserVo login(String userName, String password, String code) throws EqianyuanException {
        SystemUserBo systemUserBo = authorizationService.login(userName, password, code);
        SystemUserVo systemUserVo = new SystemUserVo();
        BeanUtils.copyProperties(systemUserBo, systemUserVo);
        return systemUserVo;
    }
}
