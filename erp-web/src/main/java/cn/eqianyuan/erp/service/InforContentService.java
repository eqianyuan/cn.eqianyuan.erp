package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.InformationContentRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.entity.InformationContentBo;
import cn.eqianyuan.erp.entity.InformationContentVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/7/12.
 */
@Service
public class InforContentService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private InformationContentService informationContentService;


    /**
     * 增加
     */
    public int add(InformationContentRequest informationContentRequest) throws EqianyuanException{
        InformationContentBo informationContentBo = new InformationContentBo();
        BeanUtils.copyProperties(informationContentRequest,informationContentBo);
        return informationContentService.add(informationContentBo);
    }

    /**
     * 删除
     */
    public int delete(Integer[] id) throws EqianyuanException{
        InformationContentBo informationContentBo = new InformationContentBo();
        return informationContentService.delete(id);
    }

    /**
     * 修改
     */
    public int update(InformationContentRequest informationContentRequest) throws EqianyuanException{
        InformationContentBo informationContentBo = new InformationContentBo();
        BeanUtils.copyProperties(informationContentRequest,informationContentBo);
        return informationContentService.update(informationContentBo);
    }

    /**
     * 根据ID查询单挑数据
     */
    public InformationContentVo getInfo(int id) throws EqianyuanException{
        InformationContentBo informationContentBo = informationContentService.getInfo(id);
        InformationContentVo informationContentVo = new InformationContentVo();
        BeanUtils.copyProperties(informationContentBo,informationContentVo);
        return informationContentVo;
    }

    /**
     * 分页查询
     */
    public PageResponse getList(int pageNo ,int pageSize) throws EqianyuanException{
        Long dataCount = informationContentService.countByList();
        if(ObjectUtils.isEmpty(dataCount)){
            return new PageResponse(pageNo,pageSize,dataCount,null);
        }
        List<InformationContentBo> informationContentBoList = informationContentService.getList(pageNo,pageSize);

        List<InformationContentVo> informationContentVoList = new ArrayList<InformationContentVo>();
        for(InformationContentBo informationContentBo : informationContentBoList){
            InformationContentVo informationContentVo = new InformationContentVo();
            BeanUtils.copyProperties(informationContentBo,informationContentVo);
            informationContentVoList.add(informationContentVo);
        }
        return new PageResponse(pageNo,pageSize,dataCount,informationContentVoList);
    }
}
