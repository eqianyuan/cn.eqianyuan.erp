package cn.eqianyuan.erp.dao;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.entity.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapperDao {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Integer[] id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(Organization record);

    int insertSelective(Organization record);

    /**
     * 查询
     * @param id
     * @return
     */
    Organization selectByPrimaryKey(Integer id);

    /**
     * 查询项目名称数据
     */
    List<Organization> selectKeyName();

    /**
     * 分页查询
     */
    List<Organization> selectByPagination( @Param("page") Page page);

    /**
     * 根据数据对象获取总条数
     */
    Long countByPagination();


    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

}