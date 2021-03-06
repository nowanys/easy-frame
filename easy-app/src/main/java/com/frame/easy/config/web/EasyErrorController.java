package com.frame.easy.config.web;

import com.frame.easy.common.status.ProfilesActiveStatus;
import com.frame.easy.config.properties.ProjectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 自定义容器级错误响应内容
 *
 * @author tengchong
 * @date 2018/10/22
 */
@Controller
public class EasyErrorController extends BasicErrorController {

    @Autowired
    private ProjectProperties projectProperties;

    public EasyErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    /**
     * 自定义ajax响应内容
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        // 响应数据
        Map<String, Object> map = getErrorAttributes(request, false);
        map.put("code", map.get("status"));
        map.remove("status");
        map.put("timestamp", System.currentTimeMillis());
        return new ResponseEntity(map, status);
    }

    /**
     * 自定义页面响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        Map<String, Object> model = getErrorAttributes(request, true);
        ModelAndView modelAndView = new ModelAndView("global/" + status.value());
        modelAndView.addAllObjects(model);
        // 当前模式是否为开发模式
        modelAndView.addObject("isDev", projectProperties.getProfilesActive().equals(ProfilesActiveStatus.dev.getProfilesActive()));
        // 记录异常
        return modelAndView;
    }
}
