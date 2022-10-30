package com.hgl.web.controller;

import com.hgl.service.LeaveService;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/leave")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/{uid}")
    public Response getAllLeaveByUid(@PathVariable("uid") Integer uid){
        try{
            return leaveService.getAllLeaveByUid(uid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

}
