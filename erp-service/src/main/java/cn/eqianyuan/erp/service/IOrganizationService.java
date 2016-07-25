package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.entity.OrganizationBo;

import java.util.List;

public interface IOrganizationService {

    /**
     * 添加
     */
    int add(OrganizationBo organizationBo)throws EqianyuanException;

    /**
     * 删除
     */
    int delete(Integer[] id) throws EqianyuanException;

    /**
     * 修改
     */
    int update (OrganizationBo organizationBo) throws EqianyuanException;

    /**
     * 根据id查询单条信息
     */
    OrganizationBo getInfo(Integer id) throws EqianyuanException;

    /**
     * 查询多条数据
     */
    List<OrganizationBo> getList (int pageNo,int pageSize) throws EqianyuanException;

    /**
     * 查询项目信息
     */
    List<OrganizationBo> getInfoname() throws EqianyuanException;

    /**
     * 查询数据总条数
     */
    Long countByList();
}
