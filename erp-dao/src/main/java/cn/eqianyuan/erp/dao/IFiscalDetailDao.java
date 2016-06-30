package cn.eqianyuan.erp.dao;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.entity.FiscalDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IFiscalDetailDao {
    int insertSelective(FiscalDetail record);

    FiscalDetail selectByPrimaryKey(int id);

    /**
     * 根据对象获取数据总条数
     *
     * @param userName
     * @return
     */
    Long countByPagination(@Param("userName") String userName);

    /**
     * 根据对象及分页条件获取分页数据集合
     *
     * @param userName 用户姓名
     * @param page     分页对象
     * @return
     */
    List<FiscalDetail> selectByPagination(@Param("userName") String userName, @Param("page") Page page);
}