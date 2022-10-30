package com.htgl.service.impl;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.mapper.DutyLeaveMapper;
import com.htgl.mapper.DutyMapper;
import com.htgl.service.DutyService;
import com.htgl.utils.poi.ExportExcelUtil;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@PropertySource("classpath:db.properties")
public class DutyServiceImpl implements DutyService {
    @Autowired
    private DutyMapper dutyMapper;

    @Value("${static_path}")
    private String static_path;

    @Value("${net_path}")
    private String net_path;
    @Autowired
    private DutyLeaveMapper dutyLeaveMapper;
    @Override
    public PageResult queryDuty(QueryPageBean queryPageBean) {
        List list = dutyMapper.queryDuty(queryPageBean);
        Integer count = dutyMapper.queryDutyCount(queryPageBean);
        return new PageResult(count, list);
    }

    public PageResult queryDutyRepair(QueryPageBean queryPageBean) {
        List list = dutyMapper.queryDutyRepair(queryPageBean);
        Integer count = dutyMapper.queryDutyRepairCount(queryPageBean);
        return new PageResult(count, list);
    }

    public Response agreeRepair(Integer did) {
        if(did == 0) return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        dutyMapper.agreeRepair(did);
        return ResponseUtils.success();
    }

    public PageResult queryDutyLeave(QueryPageBean queryPageBean) {
        List list = dutyLeaveMapper.queryDutyLeave(queryPageBean);
        Integer count = dutyLeaveMapper.queryDutyLeaveCount(queryPageBean);
        return new PageResult(count, list);
    }

    public Response agreeLeave(Integer dl_id, Integer vid) {
        if(dl_id == 0 || vid == 0){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        dutyLeaveMapper.agreeDutyLeave(dl_id, vid);
        return ResponseUtils.success();
    }

    public Response getAllMonth() {
        return ResponseUtils.respData(dutyMapper.findAllMonth());
    }

    public PageResult dutyCount(QueryPageBean queryPageBean) {
        return new PageResult(dutyMapper.queryDutyByCountNum(queryPageBean), dutyMapper.queryDutyByCount(queryPageBean));
    }

    public Response export(QueryPageBean queryPageBean) throws IOException {
        List list = dutyMapper.export(queryPageBean);
        return exportDutyCount(list);
    }

    // 值班统计导出
    private Response exportDutyCount(List list) throws IOException {
        OutputStream out = null;
        String[] header = new String[]{"学号", "姓名", "部门", "职称", "统计"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fileName = sdf.format(new Date()) + ".xlsx";
        out = new FileOutputStream(new File(static_path + "excels/duty_" +
                fileName));
        ExportExcelUtil.exportExcelByRows("测试", header, list, out);
        out.close();
        return ResponseUtils.respMsg(net_path + "/excels/duty_" + fileName);
    }

}
