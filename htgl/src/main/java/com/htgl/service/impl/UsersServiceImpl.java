package com.htgl.service.impl;

import com.htgl.entity.PageResult;
import com.htgl.entity.QueryPageBean;
import com.htgl.mapper.UsersMapper;
import com.htgl.pojo.Menber;
import com.htgl.pojo.Users;
import com.htgl.service.UsersService;
import com.htgl.utils.poi.ExportExcelUtil;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Value("${static_path}")
    private String SAVE_PATH;
//    private final String SAVE_PATH = "/usr/me/static/";
    public PageResult queryUsers(QueryPageBean queryPageBean) {
        List list = usersMapper.queryUsers(queryPageBean);
        int count = usersMapper.queryUsersCount(queryPageBean);
        return new PageResult(count, list);
    }

    public Response addUser(Users users) {
        if(users == null || users.getSid() == null || "".equals(users.getSid())
            || users.getSname() == null || "".equals(users.getSname()) ||
            users.getDid() == 0 || users.getTid() == 0){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        boolean b = usersMapper.addUser(users);
//        通过学号查询用户信息
//        usersMapper.queryUserBySid1()
        if(b) return ResponseUtils.success();
        return ResponseUtils.response(ResponseEnum.FAIL);
    }

    public Response delUserByUid(Integer uid) {
        if(uid == 0){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        usersMapper.delUserByUid(uid);
        return ResponseUtils.success();
    }
    // 通过excel导入成员
    public Response addMembersForExcel(MultipartFile file, String token) throws IOException, IllegalAccessException {
        if(file == null){
            return ResponseUtils.error("请上传文件");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        fileName = fileName.substring(fileName.lastIndexOf("."));
        if(!".xlsx".equals(fileName)){
            return ResponseUtils.error("只能上传.xlsx文件");
        }
        // 保存文件
        file.transferTo(new File(SAVE_PATH, "yiban_members.xlsx"));
        // 读取文件内容
        String [] header = new String[]{"学号","姓名", "部门", "职称", "院系", "qq", "手机"};
        String [] header_name = {"sid", "sname", "dname", "tname", "cname", "qq", "phone"};
        List<Menber> members = ExportExcelUtil.readExcelData(SAVE_PATH + "yiban_members.xlsx", header, header_name, new Menber());

        // 记录已保存与未保存学号
        String saveOk = "";
        String saveErr = "";
        // 导入
        for(int i = 0; i < members.size(); i++){
            Menber menber = members.get(i);
            if(!StringUtils.hasText(menber.getSid())){
                continue;
            }
            // 判断是否已存在该成员
            Users user = usersMapper.queryUserBySid1(menber.getSid());
            if(user == null){ // 不存在
                usersMapper.addUser1(menber);
                saveOk += menber.getSid() + ",";
            } else {
                // 已存在
                saveErr += menber.getSid() + ",";
            }
        }
        Map map = new HashMap<>();
        map.put("success", saveOk);
        map.put("exists", saveErr);// 已存在
        return ResponseUtils.respData(map);
    }

}
