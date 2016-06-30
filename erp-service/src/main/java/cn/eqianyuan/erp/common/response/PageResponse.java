package cn.eqianyuan.erp.common.response;

import java.util.List;


/**
 * PageResponse
 */
public class PageResponse {

    private long totalCount;
    private long pageCount;
    private int pageSize;
    private int pageNo;
    private List<?> list;

    public PageResponse(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageResponse(int pageNo, int pageSize, long totalCount, List<?> list) {
        this(pageNo, pageSize);
        this.totalCount = totalCount;
        this.list = list;
        if (totalCount > 0 && pageSize > 0) {
            int totalPage = (int) (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
            this.pageCount = totalPage;
        }
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}
