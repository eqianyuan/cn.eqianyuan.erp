package cn.eqianyuan.erp.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据常量
 * Created by jason on 2016-05-29.
 */
public class DataConstant {

    /**
     * 收支状态MAP
     */
    public static final Map<String, String> FISCAL_DETAIL_TYPE_MAP = new HashMap<String, String>() {{
        put("0", "支出");
        put("1", "收入");
    }};
}
