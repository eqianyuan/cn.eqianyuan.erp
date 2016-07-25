package cn.eqianyuan.erp.common.request;

/**
 * Created by asus on 2016/7/11.
 */
public class OrganizationRequest {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司详细信息
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
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
