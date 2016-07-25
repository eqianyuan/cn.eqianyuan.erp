package cn.eqianyuan.erp.entity;

import java.util.List;

public class Organization{

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 详细信息描述
     */
    private String details;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details == null ? null : details.trim();
    }
}