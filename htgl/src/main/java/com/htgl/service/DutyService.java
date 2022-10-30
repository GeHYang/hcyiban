package com.htgl.service;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.utils.response.entity.Response;

import java.io.IOException;

public interface DutyService {
    PageResult queryDuty(QueryPageBean queryPageBean);

    PageResult queryDutyRepair(QueryPageBean queryPageBean);

    Response agreeRepair(Integer did);

    PageResult queryDutyLeave(QueryPageBean queryPageBean);

    Response agreeLeave(Integer dl_id, Integer vid);

    Response getAllMonth();

    PageResult dutyCount(QueryPageBean queryPageBean);

    Response export(QueryPageBean queryPageBean) throws IOException;
}
