package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.InformationContentRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.common.response.ServerResponse;
import cn.eqianyuan.erp.entity.InformationContentVo;
import cn.eqianyuan.erp.service.InforContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by asus on 2016/7/12.
 */
@Controller
@RequestMapping("/sys_information")
public class InformationContentController extends BaseController{

    @Autowired
    private InforContentService inforContentService;


    /**
     * 进入后台API明细添加页面
     *
     * @return
     */
    @RequestMapping("/gotoAddByFiscalDetail")
    public String fiscalDetailByAdd() {
        return SystemConstant.API_DETAIL_ADD_BY_PAGE;
    }


    /**
     * 增加
     */
    @RequestMapping("add")
    @ResponseBody
    public ServerResponse add(InformationContentRequest informationContentRequest) throws EqianyuanException{
        inforContentService.add(informationContentRequest);
        return new ServerResponse();
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("/delete")
    public ServerResponse delete(@RequestParam(value = "id") Integer[] id) throws EqianyuanException{
        inforContentService.delete(id);
        return new ServerResponse(id);
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public ServerResponse update(InformationContentRequest informationContentRequest) throws EqianyuanException{
        inforContentService.update(informationContentRequest);
        return new ServerResponse();
    }

    /**
     * 根据ID查询单挑数据
     */
    @RequestMapping("/selectid")
    @ResponseBody
    public ServerResponse getInfo(@RequestParam(value = "id") int id) throws EqianyuanException{
        InformationContentVo informationContentVo = inforContentService.getInfo(id);
        return new ServerResponse(informationContentVo);

    }

    /**
     * 分页查询
     */
    @RequestMapping("/selectpage")
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize) throws EqianyuanException{
        PageResponse pageResponse = inforContentService.getList(pageNo,pageSize);
        return new ServerResponse.ResponseBuilder().data(pageResponse).build();
    }
}
