package cn.eqianyuan.erp.entity;

public class FiscalDetail {
    /**
     * 序列编号
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 收支状态
     */
    private Integer type;

    /**
     * 收支费用
     */
    private Double cost;

    /**
     * 数据创建时间
     */
    private Long createTime;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}