package cn.eqianyuan.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 面向视图层输出的系统用户对象
 * Created by jason on 2016-05-21.
 */
public class SystemUserVo {

    /**
     * 用户名
     */
    @JSONField(name = "user_name")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
