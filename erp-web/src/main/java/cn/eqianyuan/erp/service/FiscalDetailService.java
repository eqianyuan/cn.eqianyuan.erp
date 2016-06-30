package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.constant.DataConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.FiscalDetailRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.common.util.CalendarUtil;
import cn.eqianyuan.erp.entity.FiscalDetailBo;
import cn.eqianyuan.erp.entity.FiscalDetailVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 公司财政收支明细数据转换及业务主方法调用实现类
 * 主要功能接口
 * 获取明细分页列表
 * 添加明细内容
 * Created by jason on 2016-05-27.
 */
@Service
public class FiscalDetailService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IFiscalDetailService fiscalDetailService;

    /**
     * 添加新收支明细
     *
     * @param fiscalDetailRequest 收支明细对象
     * @return
     */
    public int add(FiscalDetailRequest fiscalDetailRequest) throws EqianyuanException, UnsupportedEncodingException {
        FiscalDetailBo fiscalDetailBo = new FiscalDetailBo();
        BeanUtils.copyProperties(fiscalDetailRequest, fiscalDetailBo);
        return fiscalDetailService.add(fiscalDetailBo);
    }

    /**
     * 根据编号获取数据对象
     *
     * @param id 编号
     * @return
     */
    public FiscalDetailVo getInfo(int id) throws EqianyuanException {
        FiscalDetailBo fiscalDetailBo = fiscalDetailService.getInfo(id);
        FiscalDetailVo fiscalDetailVo = new FiscalDetailVo();

        BeanUtils.copyProperties(fiscalDetailBo, fiscalDetailVo);
        fiscalDetailVo.setCreateTime(CalendarUtil.secondsTimeToDateTimeString(fiscalDetailBo.getCreateTime()));
        fiscalDetailVo.setTypeText(DataConstant.FISCAL_DETAIL_TYPE_MAP.get(fiscalDetailBo.getType().toString()));

        return fiscalDetailVo;
    }

    /**
     * 根据筛选条件和分页条件查找分页数据集合
     *
     * @param userName 用户姓名
     * @param pageNo   分页页码
     * @param pageSize 每页显示数据条目
     * @return
     */
    public PageResponse getList(String userName, int pageNo, int pageSize) {
        Long dataCount = fiscalDetailService.countByList(userName);
        if (ObjectUtils.isEmpty(dataCount)) {
            return new PageResponse(pageNo, pageSize, dataCount, null);
        }

        List<FiscalDetailBo> fiscalDetailBoList = fiscalDetailService.getList(userName, pageNo, pageSize);

        List<FiscalDetailVo> fiscalDetailVoList = new ArrayList<FiscalDetailVo>();
        for (FiscalDetailBo fiscalDetailBo : fiscalDetailBoList) {
            FiscalDetailVo fiscalDetailVo = new FiscalDetailVo();
            BeanUtils.copyProperties(fiscalDetailBo, fiscalDetailVo);
            fiscalDetailVo.setCreateTime(CalendarUtil.secondsTimeToDateTimeString(fiscalDetailBo.getCreateTime()));
            fiscalDetailVo.setTypeText(DataConstant.FISCAL_DETAIL_TYPE_MAP.get(fiscalDetailBo.getType().toString()));

            fiscalDetailVoList.add(fiscalDetailVo);
        }
        return new PageResponse(pageNo, pageSize, dataCount, fiscalDetailVoList);
    }
}
