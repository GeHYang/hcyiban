package com.htgl.web.controller;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.pojo1.DutyTime;
import com.htgl.service.DutyService;
import com.htgl.service.impl.DutyTimeService;
import com.htgl.utils.ExcelTest;
import com.htgl.utils.paiban.PabBan;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Transactional
@RequestMapping("/duty")
public class DutyController {

    @Autowired
    private DutyService dutyService;
    @Autowired
    private DutyTimeService dutyTimeService;

    @Value("${static_path}")
    private String path;
    @Value("${net_path}")
    private String net;

    @PostMapping
    public PageResult queryDuty(@RequestBody QueryPageBean queryPageBean){
        try{
            return dutyService.queryDuty(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    // 查询补签
    @PostMapping("/queryDutyRepair")
    public PageResult queryDutyRepair(QueryPageBean queryPageBean){
        try{
            return dutyService.queryDutyRepair(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }
    // 同意补签
    @GetMapping("/agreeRepair")
    public Response agreeRepair(@RequestParam("did") Integer did){
        try{
            return dutyService.agreeRepair(did);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    @PostMapping("/queryDutyLeave")
    public PageResult queryDutyLeave(QueryPageBean queryPageBean){
        try{
            return dutyService.queryDutyLeave(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @PostMapping("/agreeDutyLeave")
    public Response agreeLeave(@RequestParam("dl_id") Integer dl_id, @RequestParam("vid") Integer vid){
        try{
            return dutyService.agreeLeave(dl_id, vid);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.response(ResponseEnum.FAIL);
    }
    // 获取所有值班月份
    @GetMapping("/month")
    public Response getAllMonth(){
        try{
            return dutyService.getAllMonth();
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error();
    }

    // 值班统计
    @PostMapping("/queryDutyCount")
    public PageResult dutyCount(QueryPageBean queryPageBean){
        try{
            return dutyService.dutyCount(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new PageResult();
    }

    @RequestMapping("/export")
    public Response export(QueryPageBean queryPageBean){
        try{
            return dutyService.export(queryPageBean);
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtils.error("错误");
    }

    // 生成值班表
    @RequestMapping("/exportTable")
    public Response createDutyTable(HttpServletRequest request){
        ExcelTest.export(dutyTimeService.getAllFreeTime(), path);
        return ResponseUtils.respMsg(net + "/duty.xls");
    }

}
