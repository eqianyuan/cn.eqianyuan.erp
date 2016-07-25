package cn.eqianyuan.erp.service;

import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.OrganizationRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.entity.OrganizationBo;
import cn.eqianyuan.erp.entity.OrganizationVo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/7/11.
 */
@Service
public class OrganizationService {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private IOrganizationService organizationService;

    /**
     * 增加
     */
    public int add(OrganizationRequest organizationRequest) throws EqianyuanException {
        OrganizationBo organizationBo = new OrganizationBo();
        BeanUtils.copyProperties(organizationRequest,organizationBo);
        return organizationService.add(organizationBo);
    }

    /**
     * 删除
     */
    public int delete (Integer[] id) throws EqianyuanException{
        OrganizationBo organizationBo = new OrganizationBo();
        return organizationService.delete(id);
    }

    /**
     * 修改
     */
    public int update(OrganizationRequest organizationRequest) throws EqianyuanException{
        OrganizationBo organizationBo = new OrganizationBo();
        BeanUtils.copyProperties(organizationRequest,organizationBo);
        return organizationService.update(organizationBo);
    }

    /**
     * 通过ID查询单挑数据
     */
    public OrganizationVo getInfo(int id) throws EqianyuanException{
        OrganizationBo organizationBo = organizationService.getInfo(id);
        OrganizationVo organizationVo = new OrganizationVo();
        BeanUtils.copyProperties(organizationBo,organizationVo);
        return organizationVo;
    }

    /**
     * 查询项目信息
     */
    public List<OrganizationVo> getInfoname() throws EqianyuanException {
        List<OrganizationBo> organizationBoList = organizationService.getInfoname();
        List<OrganizationVo> organizationVoList = new ArrayList<OrganizationVo>();
        for(OrganizationBo organizationBo : organizationBoList){
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(organizationBo,organizationVo);
            organizationVoList.add(organizationVo);
        }
        return organizationVoList;
    }

    /**
     * 分页查询
     */
    public PageResponse getList (int pageNo ,int pageSize) throws EqianyuanException{
        Long dataCount = organizationService.countByList();
        if(ObjectUtils.isEmpty(dataCount)){
            return new PageResponse(pageNo,pageSize,dataCount,null);
        }
        List <OrganizationBo> organizationBoList = organizationService.getList(pageNo,pageSize);

        List <OrganizationVo> organizationVoList = new ArrayList<OrganizationVo>();
        for(OrganizationBo organizationBo : organizationBoList){
            OrganizationVo organizationVo = new OrganizationVo();
            BeanUtils.copyProperties(organizationBo,organizationVo);
            organizationVoList.add(organizationVo);
        }
        return new PageResponse(pageNo,pageSize,dataCount,organizationVoList);
    }
}
