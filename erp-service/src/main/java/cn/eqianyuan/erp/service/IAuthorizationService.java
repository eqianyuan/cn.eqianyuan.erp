package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.entity.SystemUserBo;

/**
 * 系统用户授权登录操作接口类
 * 主要功能接口
 * 登录
 * 登出
 * Created by jason on 2016-05-18.
 */
public interface IAuthorizationService {

    /**
     * 无验证码的登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return 系统用户BO对象
     * @throws EqianyuanException
     */
    SystemUserBo login(String userName, String password) throws EqianyuanException;

    /**
     * 带验证码的登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param code     登录验证码
     * @return 系统用户BO对象
     * @throws EqianyuanException
     */
    SystemUserBo login(String userName, String password, String code) throws EqianyuanException;
}
