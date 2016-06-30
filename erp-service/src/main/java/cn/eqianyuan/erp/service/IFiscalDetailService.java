package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.entity.FiscalDetail;
import cn.eqianyuan.erp.entity.FiscalDetailBo;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 公司财政收支操作接口类
 * 主要功能接口
 * 添加收支明细
 * 查询收支明细
 * Created by jason on 2016-05-18.
 */
public interface IFiscalDetailService {

    /**
     * 添加财政收支明细
     *
     * @param fiscalDetailBo 财政收支BO对象
     * @return
     */
    int add(FiscalDetailBo fiscalDetailBo) throws EqianyuanException, UnsupportedEncodingException;

    /**
     * 根据编号获取财政收支明细数据
     *
     * @param id 编号
     * @return
     */
    FiscalDetailBo getInfo(int id) throws EqianyuanException;

    /**
     * 根据筛选条件获取数据总条数
     *
     * @param userName 用户姓名
     * @return
     */
    Long countByList(String userName);

    /**
     * 根据筛选条件和分页条件获取分页数据列表
     *
     * @param userName 用户姓名
     * @param pageNo   当前页码
     * @param pageSize 每页显示内容条目
     * @return
     */
    List<FiscalDetailBo> getList(String userName, int pageNo, int pageSize);

}
