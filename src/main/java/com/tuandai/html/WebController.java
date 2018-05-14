package com.tuandai.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 肖文 on 2018/5/14
 */
@Controller
@RequestMapping(WebUrl.WEB_DIR)
public class WebController {

    @GetMapping(WebUrl.WEB_HELLO)
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("host", "SprintBoot Test");
        return "hello";
    }

    @GetMapping(WebUrl.WEB_LOGIN)
    public String login(ModelMap modelAndView){
        modelAndView.addAttribute("username", "xiaowen");
        modelAndView.addAttribute("password", "123456");
        return "login";
    }
}
