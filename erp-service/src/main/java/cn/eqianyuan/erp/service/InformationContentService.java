package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.entity.InformationContentBo;

import java.util.List;

public interface InformationContentService {

    /**
     * 添加
     */
    int add(InformationContentBo informationContentBo) throws EqianyuanException;

    /**
     * 删除
     */
    int delete (Integer[] id) throws EqianyuanException;

    /**
     * 修改
     */
    int update(InformationContentBo informationContentBo) throws EqianyuanException;

    /**
     * 根据ID查询单挑数据
     */
    InformationContentBo getInfo(Integer id) throws EqianyuanException;

    /**
     * 查询多条数据
     */
    List<InformationContentBo> getList(int pageNo,int pageSize) throws EqianyuanException;

    /**
     * 查询数据总数
     */
    Long countByList();
}
