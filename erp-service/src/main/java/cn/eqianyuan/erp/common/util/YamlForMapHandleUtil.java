package cn.eqianyuan.erp.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * yaml for map文件工具
 * Created by jason on 2016/1/9.
 */
public class YamlForMapHandleUtil {

    /**
     * 获取MAP副本，避免主版本MAP被修改数据
     *
     * @param map
     * @return
     */
    protected static Map<String, Object> transcript(Map map) {
        return new HashMap<String, Object>(map);
    }

    /**
     * 转换key，让key符合yaml的key格式
     *
     * @param key
     * @return
     */
    protected static String converKey(String key) {
        try {
            Double.parseDouble(key);
            return new StringBuilder("[").append(key).append("]").toString();
        } catch (NumberFormatException e) {
            return key;
        }
    }
}
