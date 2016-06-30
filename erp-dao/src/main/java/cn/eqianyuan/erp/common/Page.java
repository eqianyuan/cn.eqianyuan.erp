package cn.eqianyuan.erp.common;

import java.util.List;

/**
 * 分页对象
 * Created by jason on 2016-05-28.
 */
public class Page {

    /**
     * 分页页码
     */
    private int pageNo = 1;

    /**
     * 分页每页显示条目
     */
    private int pageSize = 10;

    /**
     * 分页排序字段
     */
    private List<String> orderByColumn;

    /**
     * 分页排序对应字段的排序方式
     */
    private List<String> orderByType;

    public Page(int pageNo, int pageSize) {
        this.pageNo = (pageNo <= 0 ? 1 : pageNo - 1) * pageSize;
        this.pageSize = pageSize;
    }

    public Page(int pageNo, int pageSize, List<String> orderByColumn, List<String> orderByType) {
        this(pageNo, pageSize);
        this.orderByColumn = orderByColumn;
        this.orderByType = orderByType;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<String> getOrderByColumn() {
        return orderByColumn;
    }

    public List<String> getOrderByType() {
        return orderByType;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = (pageNo <= 0 ? 1 : pageNo - 1) * this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setOrderByColumn(List<String> orderByColumn) {
        this.orderByColumn = orderByColumn;
    }

    public void setOrderByType(List<String> orderByType) {
        this.orderByType = orderByType;
    }
}
