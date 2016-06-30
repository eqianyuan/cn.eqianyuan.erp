package cn.eqianyuan.erp.service.impl;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.common.constant.DataConstant;
import cn.eqianyuan.erp.common.constant.ExceptionMsgConstant;
import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.util.CalendarUtil;
import cn.eqianyuan.erp.dao.IFiscalDetailDao;
import cn.eqianyuan.erp.entity.FiscalDetail;
import cn.eqianyuan.erp.entity.FiscalDetailBo;
import cn.eqianyuan.erp.service.IFiscalDetailService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 公司财政收支操作接口实现类
 * 主要功能接口
 * 添加收支明细
 * 查询收支明细
 * Created by jason on 2016-05-18.
 */
@Service
public class FiscalDetailServiceImpl implements IFiscalDetailService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IFiscalDetailDao fiscalDetailDao;

    /**
     * 用户名长度为4个字符
     * UTF-8 汉子字节比 3:1
     */
    private static final int USER_NAME_LENGTH = 4 * 3;

    /**
     * 添加财政收支明细
     * 业务逻辑
     * 1.对持久对象进行非空校验
     * 2.对持久对象进行内容字节长度校验，避免DB错误
     * 3.持久化操作
     *
     * @param fiscalDetailBo 财政收支BO对象
     * @return
     * @throws EqianyuanException
     */
    public int add(FiscalDetailBo fiscalDetailBo) throws EqianyuanException, UnsupportedEncodingException {
        if (ObjectUtils.isEmpty(fiscalDetailBo)) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }
        if (StringUtils.isEmpty(fiscalDetailBo.getUserName())) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [userName] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_USER_NAME_IS_EMPTY);
        }
        if (ObjectUtils.isEmpty(fiscalDetailBo.getType())) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [status] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_TYPE_IS_EMPTY);
        }
        if (ObjectUtils.isEmpty(fiscalDetailBo.getCost())) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [cost] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_COST_IS_EMPTY);
        }
        if (StringUtils.isEmpty(fiscalDetailBo.getDescription())) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [description] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_DESCRIPTION_IS_EMPTY);
        }
        if (fiscalDetailBo.getUserName().getBytes(SystemConstant.PLATFORM_CHARSET).length > USER_NAME_LENGTH) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [userName] value byte length beyond database stores byte length[" + USER_NAME_LENGTH + "]");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_USER_NAME_CONTENT_TO_LONG);
        }
        if (DataConstant.FISCAL_DETAIL_TYPE_MAP.containsKey(fiscalDetailBo.getType())) {
            logger.warn("add Fiscal Detail fail : because request object [fiscalDetailBo] the field [status] value is system no support type");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_STATUS_IS_NOT_CORRECT);
        }

        FiscalDetail fiscalDetail = new FiscalDetail();
        BeanUtils.copyProperties(fiscalDetailBo, fiscalDetail);
        fiscalDetail.setCreateTime(CalendarUtil.getSystemSeconds());

        return fiscalDetailDao.insertSelective(fiscalDetail);
    }

    /**
     * 根据编号获取数据对象
     *
     * @param id 编号
     * @return
     */
    public FiscalDetailBo getInfo(int id) throws EqianyuanException {
        FiscalDetail fiscalDetail = fiscalDetailDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(fiscalDetail)
                || ObjectUtils.isEmpty(fiscalDetail.getId())) {
            logger.warn("get Fiscal Detail Info by id [" + id + "] is empty ");
            throw new EqianyuanException(ExceptionMsgConstant.FISCAL_DETAIL_BY_ID_IS_EMPTY);
        }

        FiscalDetailBo fiscalDetailBo = new FiscalDetailBo();
        BeanUtils.copyProperties(fiscalDetail, fiscalDetailBo);
        
        return fiscalDetailBo;
    }

    /**
     * 根据筛选条件获取数据总条数
     *
     * @param userName 用户姓名
     * @return
     */
    public Long countByList(String userName) {
        Long count = fiscalDetailDao.countByPagination(userName);
        if (ObjectUtils.isEmpty(count)) {
            logger.info("userName [" + userName + "] get total count is null");
            return null;
        }
        return count;
    }

    /**
     * 根据筛选条件和分页条件获取分页数据集合
     * 业务逻辑
     * 1.获取数据集合
     * 2.将DO集合转换为BO集合
     *
     * @param userName 用户姓名
     * @param pageNo   当前页码
     * @param pageSize 每页显示内容条目
     * @return
     */
    public List<FiscalDetailBo> getList(String userName, int pageNo, int pageSize) {
        Page page = new Page(pageNo, pageSize);
        List<FiscalDetail> fiscalDetailList = fiscalDetailDao.selectByPagination(userName, page);

        if (CollectionUtils.isEmpty(fiscalDetailList)) {
            logger.info("userName [" + userName + "], pageNo [" + pageNo + "], pageSize [" + pageSize + "] get List is null");
            return Collections.EMPTY_LIST;
        }

        List<FiscalDetailBo> fiscalDetailBoList = new ArrayList<FiscalDetailBo>();
        for (FiscalDetail fiscalDetail : fiscalDetailList) {
            FiscalDetailBo fiscalDetailBo = new FiscalDetailBo();
            BeanUtils.copyProperties(fiscalDetail, fiscalDetailBo);
            fiscalDetailBoList.add(fiscalDetailBo);
        }
        return fiscalDetailBoList;
    }

}
