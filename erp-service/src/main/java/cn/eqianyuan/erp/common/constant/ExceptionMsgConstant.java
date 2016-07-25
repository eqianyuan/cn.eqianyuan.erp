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

    //公司信息id不存在
    public static final String COMPANY_ID_DOES_NOT_EXIST = "10030001";
    //公司信息描述不存在
    public static final String COMPANY_DESCRIBE_DOES_NOT_EXIST = "10030002";

    //链接地址不存在
    public static final String LINK_URL_ADDRESS_DOES_NOTEXIT_STAY = "10040001";
    //详细内容描述长度小于300字符
    public static final String LESS_THAN_300_CHARACTERS = "10040007";
    //公司名称长度小于30字符
    public static final String THE_COMPANY_NAME_LENGTH_IS_LESS_THAN_30_CHARACTERS = "10040008";
    //公司名称不存在
    public static final String COMPANY_NAME_CANNOT_BE_EMPTY = "10040009";
    //链接地址不能为空
    public static final String LINK_URL_ADDRESS_DOES_NOTEXIT = "10040010";
    //URL内容长度小于150个字符
    public static final String URL_LESS_THAN_150_CHARACTERS = "10040011";
    //所属机构、项目id不能为空
    public static final String ITEM_ID_CANNOT_BE_EMPTY = "10040012";
    //提交方式内容长度小于100
    public static final String LESS_THAN_100_CHARACTERS = "10040013";
    //参数内推长度小于500
    public static final String LESS_THAN_500_PARAMETER = "10040014";
    //响应内容长度小于500
    public static final String RESPONSE_CONTENT_LESS_THAN_500 = "10040015";
    //差多报告内容长度小于1000
    public static final String ERRORREPORTING_LESS_THAN_1000 = "10040016";

}
