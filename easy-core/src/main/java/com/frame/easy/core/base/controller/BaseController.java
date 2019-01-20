package com.frame.easy.core.base.controller;

import com.frame.easy.core.base.result.Tips;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @Author tengchong
 * @Date 2018/10/22
 */

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 默认成功提示
     */
    protected static Tips SUCCESS_TIPS = Tips.getSuccessTips();

    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";

}
