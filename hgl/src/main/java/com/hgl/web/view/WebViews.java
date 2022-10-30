package com.hgl.web.view;

import com.hgl.service.ViewService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class WebViews {
    @Autowired
    private ViewService viewService;
    @RequestMapping("/index")
    public String index(){
        return "/WEB-INF/page/index.jsp";
    }

    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView view = new ModelAndView("/WEB-INF/page/home.jsp");
        JSONObject jsonObject = viewService.duty_edit();// 返回课程表上传界面数据
        if(jsonObject != null){
            view.addObject("view", jsonObject);
        }
        return view;
    }

    @RequestMapping("/person")
    public String person(){
        return "/WEB-INF/page/person.jsp";
    }
    @RequestMapping("/duty")
    public String duty(){
        return "/WEB-INF/page/duty.jsp";
    }
    @RequestMapping("/meeting")
    public String meeting(){
        return "/WEB-INF/page/meeting.jsp";
    }
    @RequestMapping("/questions")
    public String questions(){
        return "/WEB-INF/page/questions.jsp";
    }
    @RequestMapping("/leave")
    public String leave(){
        return "/WEB-INF/page/leave.jsp";
    }
    @RequestMapping("/duty_edit")
    public String permission(){
        return "/WEB-INF/page/duty_edit.jsp";
    }
    @RequestMapping("/leaveList")
    public String leaveList(){
        return "/WEB-INF/page/leaveList.jsp";
    }

}
