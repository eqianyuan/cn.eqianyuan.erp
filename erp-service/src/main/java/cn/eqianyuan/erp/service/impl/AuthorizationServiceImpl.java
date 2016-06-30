package cn.eqianyuan.erp.service.impl;

import cn.eqianyuan.erp.common.constant.ExceptionMsgConstant;
import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.util.Md5Util;
import cn.eqianyuan.erp.common.util.SessionUtil;
import cn.eqianyuan.erp.common.util.yaml.YamlForSystemUser;
import cn.eqianyuan.erp.entity.SystemUserBo;
import cn.eqianyuan.erp.service.IAuthorizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 系统用户授权登录操作接口实现类
 * 主要功能接口
 * 登录
 * 登出
 * Created by jason on 2016-05-18.
 */
@Service
public class AuthorizationServiceImpl implements IAuthorizationService {

    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 无验证码登录
     * 业务逻辑
     * 1.检查request请求参数中是否带有登录用户名，如果不存在数据，则抛出登录用户名为空的异常
     * 2.检查request请求参数中是否带有登录密码，如果不存在数据，则抛出登录密码为空的异常
     * 3.获取yaml配置用户信息集合，如果不存在配置用户信息，则抛出系统错误异常
     * 4.密码加密
     * 5.遍历配置用户集合，根据请求用户名和加密后密码进行匹配
     * 6.成功匹配返回用户BO对象，失败匹配则抛出用户名或密码错误的异常
     *
     * @param userName 用户名
     * @param password 未加密密码
     * @return 系统用户BO对象
     * @throws EqianyuanException
     */
    public SystemUserBo login(String userName, String password) throws EqianyuanException {
        if (StringUtils.isEmpty(userName)) {
            logger.warn("login fail, because request param [ userName ] is empty.");
            throw new EqianyuanException(ExceptionMsgConstant.LOGIN_USER_NAME_IS_EMPTY);
        }

        if (StringUtils.isEmpty(password)) {
            logger.warn("userName [" + userName + "] login fail, because request param [ password ] is empty.");
            throw new EqianyuanException(ExceptionMsgConstant.LOGIN_PASSWORD_IS_EMPTY);
        }

        /**
         * 获取系统用户数据
         * 因为当下系统用户数据采用yaml文件进行配置，所以需要取出配置用户
         * 数据，然后进行遍历
         * todo 后期根据业务需要，考虑将用户移植到数据库中
         */

        if (CollectionUtils.isEmpty(YamlForSystemUser.getSystemUserList())) {
            logger.error("userName [" + userName + "] login fail, because system user configuration file content is empty.");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_ERROR);
        }

        //密码加密处理
        String encryptionPwd = Md5Util.MD5By32(StringUtils.lowerCase(password));

        for (SystemUserBo systemUserBo : YamlForSystemUser.getSystemUserList()) {
            if (!StringUtils.equalsIgnoreCase(userName, systemUserBo.getUserName())) {
                continue;
            }
            if (!StringUtils.equalsIgnoreCase(encryptionPwd, systemUserBo.getPassword())) {
                continue;
            }

            logger.info("userName [" + userName + "] login success");
            return systemUserBo;
        }

        logger.info("userName [" + userName + "] login fail, because userName or password is error.");
        throw new EqianyuanException(ExceptionMsgConstant.LOGIN_USER_NAME_OR_PASSWORD_ERROR);
    }

    /**
     * 带验证码登录
     * <p>
     * 业务逻辑
     * 1.检查request请求参数中是否带有登录验证码，如果验证码数据为空，则抛出验证码为空的异常
     * 2.检查session中是否存在验证码，如果验证码不存在，则抛出验证码错误的异常
     * 3.检查request的请求验证码和session中的验证码是否一致，如果不一致，则抛出验证码错误的异常
     * 4.转无验证码登录处理
     *
     * @param userName 用户名
     * @param password 未加密密码
     * @param code     验证码
     * @param userName 用户名
     * @param password 密码
     * @param code     登录验证码
     * @return
     * @throws EqianyuanException
     */
    public SystemUserBo login(String userName, String password, String code) throws EqianyuanException {
        if (StringUtils.isEmpty(code)) {
            logger.warn("login fail, because request param [ code ] is empty.");
            throw new EqianyuanException(ExceptionMsgConstant.LOGIN_VALIDATA_CODE_IS_EMPTY);
        }

        String codeByValidation = (String) SessionUtil.getAttribute(SystemConstant.VERIFY_CODE);
        if (StringUtils.isEmpty(codeByValidation)) {
            logger.warn("login fail, because there is no verification code in the session attribute.");
            throw new EqianyuanException(ExceptionMsgConstant.LOGIN_VALIDATA_CODE_IS_ERROR);
        }

        if (!StringUtils.equalsIgnoreCase(code, codeByValidation)) {
            logger.warn("login fail, because session attribute verification code and request param code not consistent.");
            throw new EqianyuanException(ExceptionMsgConstant.LOGIN_VALIDATA_CODE_IS_ERROR);
        }

        return this.login(userName, password);
    }
}
