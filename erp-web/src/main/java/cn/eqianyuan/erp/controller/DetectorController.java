package cn.eqianyuan.erp.controller;

import cn.eqianyuan.erp.common.listener.UpperComputerSocketContextListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * x
 * 地图控制器
 * Created by jason on 2016/1/9.
 */
@RestController
public class DetectorController extends BaseController {

    @RequestMapping("/parkingLine/test")
    public byte listDetector() {
        return UpperComputerSocketContextListener.status;
    }

}
