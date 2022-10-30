package com.htgl.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberView {
    @RequestMapping("/index")
    public String index(){
        return "pages/member/index";
    }
}
