package cn.eqianyuan.erp.dao;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.entity.InformationContent;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InformationContentDao {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer[] id);

    int insertSelective(InformationContent record);

    /**
     * 根据ID查询单挑数据
     * @param id
     * @return
     */
    InformationContent selectByPrimaryKey(Integer id);

    /**
     * 分页查询
     */
    List<InformationContent> selectByPagination (@Param("page") Page page);

    /**
     * 根据对象查询数据总数
     */
    Long countByPagination();

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InformationContent record);

}