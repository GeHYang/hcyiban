package com.htgl.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/duty")
public class DutyView {
    @RequestMapping("/index")
    public String index(){
        return "pages/duty/index";
    }
    @RequestMapping("/count")
    public String count(){
        return "pages/duty/count";
    }
    @RequestMapping("/leave")
    public String leave(){
        return "pages/duty/leave";
    }
    @RequestMapping("/repair")
    public String repair(){
        return "pages/duty/repair";
    }
}
