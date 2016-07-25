package cn.eqianyuan.erp.service.impl;

import cn.eqianyuan.erp.common.Page;
import cn.eqianyuan.erp.common.constant.ExceptionMsgConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.dao.InformationContentDao;
import cn.eqianyuan.erp.entity.InformationContent;
import cn.eqianyuan.erp.entity.InformationContentBo;
import cn.eqianyuan.erp.service.InformationContentService;
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
 * Created by asus on 2016/7/12.
 */
@Service
public class InformationContentImpl implements InformationContentService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private InformationContentDao informationContentDao;

    //链接地址最大字节长度
    private static final int URL_MAXIMUM_LENGTH = 150;
    //详细描述内容最大字节长度
    private static final int MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH = 300;
    //提交方式内容最大长度为100
    private static final int SUBMISSION_CONTENT_LENGTH_100 = 100;
    //参数内容最大长度为500
    private static final int PARAMETER_CONTENT_LENGTH_500 = 500;
    //响应内容最大长度为500
    private static final int RESPONSE_CONTENT_CONTENT_LENGTH_500 = 500;
    //报错日志最大长度为1000
    private static final int ERRORREPORTING_CONTENT_LENGTH_1000 = 1000;

    /**
     * 添加
     *
     * @param informationContentBo
     * @return
     * @throws EqianyuanException
     */
    public int add(InformationContentBo informationContentBo) throws EqianyuanException {
        if (ObjectUtils.isEmpty(informationContentBo)) {
            logger.warn("add fail : because request object [informationContentBo] is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }

        if(ObjectUtils.isEmpty(informationContentBo.getOrganizationId())){
            logger.warn("add fail : because request organization id null");
            throw new EqianyuanException(ExceptionMsgConstant.ITEM_ID_CANNOT_BE_EMPTY);
        }
        if (ObjectUtils.isEmpty(informationContentBo.getUrladdress())) {
            logger.warn("add fail : because request object [informationContentBo] the field [urladdress] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.LINK_URL_ADDRESS_DOES_NOTEXIT);
        }

        //判断url内容长度
        if (informationContentBo.getUrladdress().length() > URL_MAXIMUM_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.URL_LESS_THAN_150_CHARACTERS);
        }
        //判断描述内容长度
        if (informationContentBo.getInstructions().length() > MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_300_CHARACTERS);
        }
        //判读提交方式长度
        if (informationContentBo.getSubmission().length() > SUBMISSION_CONTENT_LENGTH_100) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_100_CHARACTERS);
        }
        //判读参数长度
        if (informationContentBo.getParameter().length() > PARAMETER_CONTENT_LENGTH_500) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_500_PARAMETER);
        }
        //判断响应内容长度
        if (informationContentBo.getResponsecontent().length() > RESPONSE_CONTENT_CONTENT_LENGTH_500) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.RESPONSE_CONTENT_LESS_THAN_500);
        }
        //差错报告内容长度
        if (informationContentBo.getErrorreporting().length() > ERRORREPORTING_CONTENT_LENGTH_1000) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.ERRORREPORTING_LESS_THAN_1000);
        }

        InformationContent informationContent = new InformationContent();
        BeanUtils.copyProperties(informationContentBo, informationContent);
        return informationContentDao.insertSelective(informationContent);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public int delete(Integer[] id) throws EqianyuanException {
        if (ObjectUtils.isEmpty(id)) {
            logger.warn("delete fail , because id is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        return informationContentDao.deleteByPrimaryKey(id);
    }

    /**
     * 修改
     */
    public int update(InformationContentBo informationContentBo) throws EqianyuanException {
        if (StringUtils.isEmpty(String.valueOf(informationContentBo.getId()))) {
            logger.warn("update fail : because request object [id] is null");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        if (ObjectUtils.isEmpty(informationContentBo)) {
            logger.warn("update fail : because request object [informationContentBo] is null");
            throw new EqianyuanException(ExceptionMsgConstant.SYSTEM_LACK_OF_REQUEST_PARAMETER);
        }
        if (ObjectUtils.isEmpty(informationContentBo.getUrladdress())) {
            logger.warn("add fail : because request object [informationContentBo] the field [urladdress] value is null");
            throw new EqianyuanException(ExceptionMsgConstant.LINK_URL_ADDRESS_DOES_NOTEXIT_STAY);
        }

        //判断url内容长度
        if (informationContentBo.getUrladdress().length() > URL_MAXIMUM_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.URL_LESS_THAN_150_CHARACTERS);
        }
        //判断描述内容长度
        if (informationContentBo.getInstructions().length() > MASTER_COMPUTER_ADDRESS_CONTENT_MAX_LENGTH) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_300_CHARACTERS);
        }
        //判读提交方式长度
        if (informationContentBo.getSubmission().length() > SUBMISSION_CONTENT_LENGTH_100) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_100_CHARACTERS);
        }
        //判读参数长度
        if (informationContentBo.getParameter().length() > PARAMETER_CONTENT_LENGTH_500) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.LESS_THAN_500_PARAMETER);
        }
        //判断响应内容长度
        if (informationContentBo.getResponsecontent().length() > RESPONSE_CONTENT_CONTENT_LENGTH_500) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.RESPONSE_CONTENT_LESS_THAN_500);
        }
        //差错报告内容长度
        if (informationContentBo.getErrorreporting().length() > ERRORREPORTING_CONTENT_LENGTH_1000) {
            logger.info("add fail , because Details content too long");
            throw new EqianyuanException(ExceptionMsgConstant.ERRORREPORTING_LESS_THAN_1000);
        }

        InformationContent informationContent = new InformationContent();
        BeanUtils.copyProperties(informationContentBo, informationContent);
        return informationContentDao.updateByPrimaryKeySelective(informationContent);
    }

    /**
     * 根据ID查询单挑数据
     *
     * @param id
     * @return
     * @throws EqianyuanException
     */
    public InformationContentBo getInfo(Integer id) throws EqianyuanException {
        InformationContent informationContent = informationContentDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(informationContent)) {
            logger.warn("get Detail Info by id [" + id + "] is empty");
            throw new EqianyuanException(ExceptionMsgConstant.COMPANY_ID_DOES_NOT_EXIST);
        }
        InformationContentBo informationContentBo = new InformationContentBo();
        BeanUtils.copyProperties(informationContent, informationContentBo);
        return informationContentBo;
    }

    /**
     * 分页查询数据
     *
     * @param pageNo
     * @param pageSize
     * @return
     * @throws EqianyuanException
     */
    public List<InformationContentBo> getList(int pageNo, int pageSize) throws EqianyuanException {
        Page page = new Page(pageNo, pageSize);
        List<InformationContent> informationContentList = informationContentDao.selectByPagination(page);
        if (CollectionUtils.isEmpty(informationContentList)) {
            logger.warn("pageNo [" + pageNo + "], pageSize [" + pageSize + "] get List is null");
            return Collections.EMPTY_LIST;
        }
        List<InformationContentBo> informationContentBoList = new ArrayList<InformationContentBo>();
        for (InformationContent informationContent : informationContentList) {
            InformationContentBo informationContentBo = new InformationContentBo();
            BeanUtils.copyProperties(informationContent, informationContentBo);
            informationContentBoList.add(informationContentBo);
        }
        return informationContentBoList;
    }

    /**
     * 根据对象查询数据总数
     *
     * @return
     */
    public Long countByList() {
        Long count = informationContentDao.countByPagination();
        if(ObjectUtils.isEmpty(count)){
            logger.warn("get total count is null");
            return null;
        }
        return count;
    }

}
