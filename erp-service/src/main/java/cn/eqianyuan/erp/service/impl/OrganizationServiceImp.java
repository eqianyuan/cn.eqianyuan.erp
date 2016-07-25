package cn.eqianyuan.erp.service.impl;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.common.constant.ExceptionMsgConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.dao.OrganizationMapperDao;
import cn.eqianyuan.erp.entity.Organization;
import cn.eqianyuan.erp.entity.OrganizationBo;
import cn.eqianyuan.erp.service.IOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by asus on 2016/7/11.
 */
@Service
public class OrganizationServiceImp implements IOrganizationService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private OrganizationMapperDao organizationMapperDao;


    //详细描述内容最大字节长度
    private static final int MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH = 300;
    //公司名称最大字节长度
    private static final int MASTER_COMPUTER_NAME_CONTENT_MAX_LENGTH = 30;


    /**
     * 添加
     *
     * @param organizationBo
     * @return
     * @throws EqianyuanException
     */
    public int add(OrganizationBo organizationBo) throws EqianyuanException {
        if(ObjectUtils.isEmpty(organizationBo)){
            logger.warn("add fail : because request object [organizationBo] is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }
        if(ObjectUtils.isEmpty(organizationBo.getName())){
            logger.warn("add fail : because request object [organizationBo] the field [name] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_NAME_CANNOT_BE_EMPTY);
        }

        //判断公司名称长度是否超出许可长度
        if (organizationBo.getName().length() > MASTER_COMPUTER_NAME_CONTENT_MAX_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.THE_COMPANY_NAME_LENGTH_IS_LESS_THAN_30_CHARACTERS);
        }

        //判断详细地址内容长度是否超出DB许可长度
            if (organizationBo.getDetails().length() > MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH) {
                logger.info("add fail , because Details content too long");
                throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_300_CHARACTERS);
            }


        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationBo,organization);
        return organizationMapperDao.insertSelective(organization);
    }

    /**
     * 删除
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public int delete(Integer[] id) throws EqianyuanException {
        if(ObjectUtils.isEmpty(id)){
            logger.warn("delete fail , because id is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        return organizationMapperDao.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     * @param organizationBo
     * @return
     * @throws EqianyuanException
     */
    public int update(OrganizationBo organizationBo) throws EqianyuanException {
        if(StringUtils.isEmpty(String.valueOf(organizationBo.getId()))){
            logger.warn("update fail : because request object [id] is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        if(ObjectUtils.isEmpty(organizationBo)){
            logger.warn("update fail : because request object [organizationBo] is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }
        if(ObjectUtils.isEmpty(organizationBo.getName())){
            logger.warn("update fail : because request object [organizationBo] the field [name] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_NAME_CANNOT_BE_EMPTY);
        }

        //判断公司名称长度是否超出许可长度
        if (organizationBo.getName().length() > MASTER_COMPUTER_NAME_CONTENT_MAX_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.THE_COMPANY_NAME_LENGTH_IS_LESS_THAN_30_CHARACTERS);
        }

        //判断详细地址内容长度是否超出DB许可长度
        if (organizationBo.getDetails().length() > MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_300_CHARACTERS);
        }

        Organization organization = new Organization();
        BeanUtils.copyProperties(organizationBo,organization);
        return organizationMapperDao.updateByPrimaryKeySelective(organization);
    }

    /**
     * 根据ID查询单挑数据
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public OrganizationBo getInfo(Integer id) throws EqianyuanException {
        Organization organization = organizationMapperDao.selectByPrimaryKey(id);
        if(ObjectUtils.isEmpty(organization)){
            logger.warn("get Detail Info by id [" + id + "] is empty");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        OrganizationBo organizationBo = new OrganizationBo();
        BeanUtils.copyProperties(organization,organizationBo);
        return organizationBo;
    }

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     * @throws EqianyuanException
     */
    public List<OrganizationBo> getList( int pageNo, int pageSize) throws EqianyuanException {
        Page page = new Page(pageNo,pageSize);
        List <Organization> organizationList = organizationMapperDao.selectByPagination(page);
        if(CollectionUtils.isEmpty(organizationList)){
            logger.warn("pageNo [" + pageNo + "], pageSize [" + pageSize + "] get List is null");
            return Collections.EMPTY_LIST;
        }
        List <OrganizationBo> organizationBoList = new ArrayList<OrganizationBo>();
        for (Organization organization : organizationList){
            OrganizationBo organizationBo = new OrganizationBo();
            BeanUtils.copyProperties(organization,organizationBo);
            organizationBoList.add(organizationBo);
        }
        return organizationBoList;
    }

    /**
     * 查询项目名称
     * @return
     * @throws EqianyuanException
     */
    public List<OrganizationBo> getInfoname() throws EqianyuanException {
        List<Organization> organizationList = organizationMapperDao.selectKeyName();
        if(CollectionUtils.isEmpty(organizationList)){
            logger.warn("get List is null");
            return Collections.EMPTY_LIST;
        }
        List<OrganizationBo> organizationBoList = new ArrayList<OrganizationBo>();
        for(Organization organization : organizationList){
            OrganizationBo organizationBo = new OrganizationBo();
            BeanUtils.copyProperties(organization,organizationBo);
            organizationBoList.add(organizationBo);
        }
        return organizationBoList;
    }

    /**
     * 查询数据总条数
     * @return
     */
    public Long countByList() {
        Long count = organizationMapperDao.countByPagination();
        if(ObjectUtils.isEmpty(count)){
            logger.info("get total count is null");
            return null;
        }
        return count;
    }
}
