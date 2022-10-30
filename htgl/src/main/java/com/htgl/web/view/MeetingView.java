package com.htgl.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting")
public class MeetingView {
    @RequestMapping("/index")
    public String index(){
        return "pages/meeting/index";
    }
    @RequestMapping("/count")
    public String count(){
        return "pages/meeting/count";
    }
    @RequestMapping("/leave")
    public String leave(){
        return "pages/meeting/leave";
    }
}
