package cn.eqianyuan.erp.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 面向视图层输出的公司财政收支明细对象
 * Created by jason on 2016-05-21.
 */
public class FiscalDetailVo {
    /**
     * 序列编号
     */
    private Integer id;

    /**
     * 用户姓名
     */
    @JSONField(name = "user_name")
    private String userName;

    /**
     * 收支状态
     * 明文
     */
    @JSONField(name = "type_text")
    private String typeText;

    /**
     * 收支费用
     */
    private Double cost;

    /**
     * 数据创建时间
     */
    @JSONField(name = "create_time")
    private String createTime;

    /**
     * 收支明细描述
     */
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}