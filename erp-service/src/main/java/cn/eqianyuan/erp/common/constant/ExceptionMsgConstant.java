package cn.eqianyuan.erp.common.constant;

/**
 * 自定义异常错误消息静态常量类
 * Created by jason on 2016-05-19.
 */
public class ExceptionMsgConstant {

    //系统错误
    public static final String SYSTEM_ERROR = "10000001";
    //缺少请求参数
    public static final String SYSTEM_LACK_OF_REQUEST_PARAMETER = "10000002";

    //登录验证码是空
    public static final String LOGIN_VALIDATA_CODE_IS_EMPTY = "10010001";
    //登录验证码错误
    public static final String LOGIN_VALIDATA_CODE_IS_ERROR = "10010002";
    //登录用户名为空
    public static final String LOGIN_USER_NAME_IS_EMPTY = "10010003";
    //登录密码为空
    public static final String LOGIN_PASSWORD_IS_EMPTY = "10010004";
    //用户名或密码错误
    public static final String LOGIN_USER_NAME_OR_PASSWORD_ERROR = "10010005";
    //公司财政收支明细用户名为空
    public static final String FISCAL_DETAIL_USER_NAME_IS_EMPTY = "10020001";
    //公司财政收支明细收支类型为空
    public static final String FISCAL_DETAIL_TYPE_IS_EMPTY = "10020002";
    //公司财政收支明细内容为空
    public static final String FISCAL_DETAIL_DESCRIPTION_IS_EMPTY = "10020003";
    //公司财政收支明细费用为空
    public static final String FISCAL_DETAIL_COST_IS_EMPTY = "10020004";
    //公司财政收支明细用户名内容太长
    public static final String FISCAL_DETAIL_USER_NAME_CONTENT_TO_LONG = "10020005";
    //公司财政收支明细状态不是正确值
    public static final String FISCAL_DETAIL_STATUS_IS_NOT_CORRECT = "10020006";
    //公司财政收支明细by编号不存在
    public static final String FISCAL_DETAIL_BY_ID_IS_EMPTY = "10020007";
}
