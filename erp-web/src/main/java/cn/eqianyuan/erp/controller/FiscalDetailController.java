package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.constant.SystemConstant;
import cn.eqianyuan.erp.common.exception.EqianyuanException;
import cn.eqianyuan.erp.common.request.FiscalDetailRequest;
import cn.eqianyuan.erp.common.response.PageResponse;
import cn.eqianyuan.erp.common.response.ServerResponse;
import cn.eqianyuan.erp.entity.FiscalDetailVo;
import cn.eqianyuan.erp.service.FiscalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * 公司财政收支明细
 * 主要功能：
 * 列表查询
 * 收支明细添加
 * Created by jason on 2016-05-27.
 */
@Controller
@RequestMapping("/system-manage")
public class FiscalDetailController extends BaseController {

    @Autowired
    private FiscalDetailService fiscalDetailService;

    /**
     * 进入后台财政明细列表
     *
     * @return
     */
    @RequestMapping("/fiscal-detail/list")
    public String fiscalDetailByList() {
        return SystemConstant.MANAGE_FISCAL_DETAIL_LIST_BY_PAGE;
    }

    @RequestMapping(value = "/getListByFiscalDetail", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getList(@RequestParam(value = "user_name", required = false) String userName,
                                  @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        PageResponse pageResponse = fiscalDetailService.getList(userName, pageNo, pageSize);
        return new ServerResponse(pageResponse);
    }

    /**
     * 进入后台财政明细添加页面
     *
     * @return
     */
    @RequestMapping("/gotoAddByFiscalDetail")
    public String fiscalDetailByAdd() {
        return SystemConstant.MANAGE_FISCAL_DETAIL_ADD_BY_PAGE;
    }

    /**
     * 添加财政明细
     *
     * @param fiscalDetailRequest
     * @return
     * @throws EqianyuanException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/doAddByFiscalDetail", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(FiscalDetailRequest fiscalDetailRequest) throws EqianyuanException, UnsupportedEncodingException {
        fiscalDetailService.add(fiscalDetailRequest);
        return new ServerResponse();
    }

    /**
     * 进入后台财政明细内容详情页面
     *
     * @return
     */
    @RequestMapping("/gotoInfoByFiscalDetail")
    public ServerResponse fiscalDetailByInfo(@RequestParam(value = "id") int id) throws EqianyuanException {
        FiscalDetailVo fiscalDetailVo = fiscalDetailService.getInfo(id);
        return new ServerResponse(fiscalDetailVo);
    }
}
