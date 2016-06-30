package cn.eqianyuan.erp.common.request;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 公司财务财政收支明细请求对象
 * Created by jason on 2016-05-27.
 */
public class FiscalDetailRequest {
    private String userName;

    private int type;

    private Double cost;

    private String description;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
