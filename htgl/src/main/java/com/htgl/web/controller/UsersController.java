package com.htgl.web.controller;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.pojo.Users;
import com.htgl.service.UsersService;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
@Transactional
@EnableTransactionManagement
@CrossOrigin(origins = "*", maxAge = 3600)
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public PageResult queryUsers(@RequestBody QueryPageBean queryPageBean){
        try{
            return usersService.queryUsers(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @DeleteMapping("/{uid}")
    public Response delUserByUid(@PathVariable("uid") Integer uid){
        try{
            return usersService.delUserByUid(uid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    @PostMapping("/add")
    public Response addUser(@RequestBody Users user){
        try{
            return usersService.addUser(user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    /**
     * 通过excel添加成员
     * @return
     */
    @RequestMapping("/addMembersForExcel")
    public Response addMembersForExcel(@RequestParam("file")MultipartFile file, HttpServletRequest req){
        try{
            String token = req.getHeader("Authorization");
            return usersService.addMembersForExcel(file, token);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("失败");
    }

}
