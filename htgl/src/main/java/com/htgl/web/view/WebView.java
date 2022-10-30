package com.htgl.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebView {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/edit")
    public String edit(){
        return "pages/edit/index";
    }

}
