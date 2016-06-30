package cn.eqianyuan.erp.common.util.yaml;

import cn.eqianyuan.erp.common.util.YamlForMapHandleUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * 服务异常yaml文件工具类
 * Created by jason on 2016-05-22.
 */
public class YamlForSystemErrMsg extends YamlForMapHandleUtil {

    public static final String SYSTEM_ERR_MSG_KEY_BY_CN = "cn";
    public static final String SYSTEM_ERR_MSG_KEY_BY_EN = "en";
    public static final String SYSTEM_ERR_MSG_KEY_BY_CODE = "code";

    private YamlForSystemErrMsg() {
    }

    private static Map<String, Object> systemErrMsg;

    /**
     * 获取副本
     *
     * @return
     */
    public static Map<String, Object> getSystemErrMsg() {
        return transcript(systemErrMsg);
    }

    public void setSystemErrMsg(Map<String, Object> systemErrMsg) {
        YamlForSystemErrMsg.systemErrMsg = systemErrMsg;
    }

    /**
     * 根据KEY获取VALUE
     *
     * @param map
     * @param msgKey
     * @return
     */
    private static Object getMapByKey(Map<String, Object> map, String msgKey) {
        if (CollectionUtils.isEmpty(map)) {
            return null;
        }

        if (StringUtils.isEmpty(msgKey)) {
            return null;
        }

        return map.get(msgKey);
    }

    /**
     * 根据key获取消息内容
     *
     * @param messageCode 消息错误码
     * @param key         消息KEY
     * @return
     */
    public static String getMsgBykey(String messageCode, String key) {
        Object obj = getMapByKey(getSystemErrMsg(), converKey(messageCode));
        if (ObjectUtils.isEmpty(obj)) {
            return null;
        }

        return getMapByKey((Map<String, Object>) obj, converKey(key)).toString();
    }

}
