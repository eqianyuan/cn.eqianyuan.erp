package cn.eqianyuan.erp.common.constant;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统常量
 * Created by jason on 2016/1/9.
 */
public class SystemConstant {

    /**
     * 系统用户session对象
     */
    public static final String SYSTEM_SESSION_USER = "systemUser";

    /**
     * 验证码sessionkey
     */
    public static final String VERIFY_CODE = "verifyCode";

    /**
     * 系统目录分隔符
     */
    public static final String SYSTEM_SEPARATOR = File.separator;

    /**
     * 系统管理首页页面位置
     */
    public static final String SYSTEM_MANAGE_INDEX_BY_PAGE = "index";

    /**
     * 系统登录页面位置
     */
    public static final String SYSTEM_MANAGE_LOGIN_BY_PAGE = "login";

    public static final String SYSTEM_MANAGE_HOME_BY_PAGE = "home";
    /**
     * 系统后台财政明细列表页面
     */
    public static final String MANAGE_FISCAL_DETAIL_LIST_BY_PAGE = SYSTEM_SEPARATOR + "fiscal detail" + SYSTEM_SEPARATOR + "list";

    /**
     * 系统后台财政明细添加页面
     */
    public static final String MANAGE_FISCAL_DETAIL_ADD_BY_PAGE = SYSTEM_SEPARATOR + "fiscal detail" + SYSTEM_SEPARATOR + "add";

    /**
     * 系统后台API明细添加页面
     */
    public static final String API_DETAIL_ADD_BY_PAGE = SYSTEM_SEPARATOR + "inforcontent" + SYSTEM_SEPARATOR + "inforcontentadd";

    /**
     * 系统后台公司明细页面
     */
    public static final String COMPANY_DETAILS_PAGE = SYSTEM_SEPARATOR + "organization" + SYSTEM_SEPARATOR + "list";

    /**
     * 系统后台公司明细添加页面
     */
    public static final String COMPANY_ADD_PAGE = SYSTEM_SEPARATOR + "organization" + SYSTEM_SEPARATOR + "add";


    /**
     * 单个CPU线程池大小
     */
    private static final int POOL_SIZE = 2;

    /**
     * CPU核数
     */
    private static final int CPU_NUMS = Runtime.getRuntime().availableProcessors();

    /**
     * 上位机通信线程池
     * 可重用固定线程数的线程池
     */
    public static ExecutorService PARKING_UPPER_COMPUTER_THREAD_POOL = Executors.newFixedThreadPool(CPU_NUMS * POOL_SIZE);

}
