package com.frame.easy.modular.sys.controller;

import com.frame.easy.base.controller.BaseController;
import com.frame.easy.result.Tips;
import com.frame.easy.modular.sys.service.SysStatusService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 系统状态
 *
 * @author tengchong
 * @date 2018/11/8
 */
@Controller
@RequestMapping("/auth/sys/status")
public class SysStatusController extends BaseController {

    private static String PREFIX = "modular/sys/status/";

    @Autowired
    private SysStatusService service;

    /**
     * view
     *
     * @param model model
     * @return view
     */
    @RequestMapping("/view")
    @RequiresPermissions("sys:status:select")
    public String view(Model model) {
        logger.debug("/auth/sys/status/view");
        model.addAttribute("object", service.getSysStatus());
        return PREFIX + "view";
    }

    /**
     * 获取系统状态
     *
     * @return Tips
     */
    @RequestMapping("/get/sys/status")
    @ResponseBody
    @RequiresPermissions("sys:status:select")
    public Tips getSysStatus() {
        return Tips.getSuccessTips(service.getSysStatus());
    }

    /**
     * 获取实时数据
     *
     * @return Tips
     */
    @RequestMapping("/get/real/time/status")
    @ResponseBody
    @RequiresPermissions("sys:status:select")
    public Tips getRealTimeStatus() {
        return Tips.getSuccessTips(service.getRealTimeStatus());
    }
}
