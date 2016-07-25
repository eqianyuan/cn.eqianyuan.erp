package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.OrganizationRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.common.response.ServerResponse;
import cn.eqianyuan.erp.entity.OrganizationVo;
import cn.eqianyuan.erp.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by asus on 2016/7/11.
 */
@Controller
@RequestMapping("/system_organzation")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;


    /**
     * 进入后台公司明细列表
     *
     * @return
     */
    @RequestMapping("/list")
    public String fiscalDetailByList() {
        return SystemConstant.COMPANY_DETAILS_PAGE;
    }


    /**
     * 进入后台公司明细添加页面
     *
     * @return
     */
    @RequestMapping("/gotoAddByFiscalDetail")
    public String fiscalDetailByAdd() {
        return SystemConstant.COMPANY_ADD_PAGE;
    }


    /**
     * 添加
     */
    @RequestMapping("/add")
    @ResponseBody
    public ServerResponse add(OrganizationRequest organizationRequest) throws EqianyuanException{
        organizationService.add(organizationRequest);
        return new ServerResponse();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public ServerResponse delete(@RequestParam(value = "id") Integer[] id) throws EqianyuanException{
        organizationService.delete(id);
        return new ServerResponse();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @ResponseBody
    public ServerResponse update(OrganizationRequest organizationRequest) throws EqianyuanException{
        organizationService.update(organizationRequest);
        return new ServerResponse();
    }

    /**
     * 根据ID查询出单挑数据
     */
    @RequestMapping("/selectid")
    @ResponseBody
    public ServerResponse organInfo(@RequestParam(value = "id") int id) throws EqianyuanException{
        OrganizationVo organizationVo = organizationService.getInfo(id);
        return new ServerResponse.ResponseBuilder().data(organizationVo).build();
    }

    /**
     * 查询项目信息
     */
    @RequestMapping(value = "/selectname")
    @ResponseBody
    public List<OrganizationVo> getlistname() throws EqianyuanException{
        List<OrganizationVo> organizationVo = organizationService.getInfoname();
        return organizationVo;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/getListByOrganization")
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) throws EqianyuanException{
        PageResponse pageResponse = organizationService.getList(pageNo,pageSize);
        return new ServerResponse.ResponseBuilder().data(pageResponse).build();

    }
}
