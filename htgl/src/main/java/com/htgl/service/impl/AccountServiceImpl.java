package com.htgl.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.htgl.mapper.AccountMapper;
import com.htgl.mapper.UsersMapper;
import com.htgl.pojo.Account;
import com.htgl.service.AccountService;
import com.htgl.utils.TokenUtil;
import com.htgl.utils.crypto.CryptoUtils;
import com.htgl.utils.response.ResponseUtils;
import com.htgl.utils.response.entity.Response;
import com.htgl.utils.response.entity.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@PropertySource("classpath:db.properties")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Value("${SAVE_PATH}")
    private String SAVE_PATH;
    @Value("${NET_PATH}")
    private String NET_PATH;

    public Response login(String sid, String password) throws NoSuchAlgorithmException {
        if(sid == null || "".equals(sid) || password == null || "".equals(password)){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 密码进行MD5加密
        password = CryptoUtils.encryptMD5(password);
        Map map = new HashMap<>();
        map.put("sid", sid);
        map.put("password", password);
        Account account = accountMapper.queryAccount(map);
        if(account == null){
            return ResponseUtils.response(ResponseEnum.LOGIN_ERROR);
        }
        Map user = usersMapper.queryUserBySid(sid);
        List result = new ArrayList<>();
        result.add(account);
        result.add(user);
        // id、sid、password生成token
        String token = TokenUtil.sign(account.getAid(), sid, password);
        account.setPassword(token);// 将token返回
        return ResponseUtils.respData(result);
    }

    public Response resetPwd(String one_password, String two_password, String token) throws NoSuchAlgorithmException {
        one_password = one_password == null ? null : one_password.trim();
        two_password = two_password == null ? null : two_password.trim();
        if(one_password == null || "".equals(one_password) || two_password == null || "".equals(two_password)
            || token == null || "".equals(token)){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 判断两次密码是否一致
        if(!one_password.equals(two_password)){
            return ResponseUtils.error("两次密码不一致");
        }
        // 对密码加密
        one_password = CryptoUtils.encryptMD5(one_password);
        // 解析token获取id与学号
        DecodedJWT tokenData = TokenUtil.parseJWT(token);
        Integer aid = tokenData.getClaim("aid").asInt();
        String sid = tokenData.getClaim("sid").asString();
        Map map = new HashMap<>();
        map.put("aid", aid);
        map.put("sid", sid);
        map.put("password", one_password);
        accountMapper.resetPassword(map);
        return ResponseUtils.success();
    }

    public Response resetHead(MultipartFile multipartFile, String token) throws IOException {
        // 校验参数
        if(multipartFile == null || token == null || "".equals(token)){
            return ResponseUtils.response(ResponseEnum.ARGUMENT_VALID_ERROR);
        }
        // 校验图片名
        String fileType = multipartFile.getOriginalFilename();
        fileType = fileType.substring(fileType.lastIndexOf("."));
        if(!fileType.equals(".jpg") && !".jpeg".equals(fileType) && !".png".equals(fileType)){
            return ResponseUtils.error("图片格式只能是jpg、jpeg、png");
        }
        // 学号做图片名
        String sid = TokenUtil.parseJWT(token).getClaim("sid").asString();
        Integer aid = TokenUtil.parseJWT(token).getClaim("aid").asInt();
        multipartFile.transferTo(new File(SAVE_PATH, sid + fileType));
        // 更新数据库
        Map map = new HashMap<>();
        map.put("aid", aid);
        map.put("sid", sid);
        map.put("headIcon", NET_PATH + sid + fileType);
        accountMapper.resetHeadIcon(map);
        return ResponseUtils.respMsg(NET_PATH + sid + fileType);
    }

    public Response verify(String token) {
        // 判断token是否存在
        if(token == null || "".equals(token)){
            return ResponseUtils.response(ResponseEnum.LOGIN_CODE);
        }
        // 验证token是否合法
        if(!TokenUtil.verify(token)){
            return ResponseUtils.response(ResponseEnum.LOGIN_CODE);
        }
        DecodedJWT decodedJWT = TokenUtil.parseJWT(token);
        // 获取token信息
        Integer aid = decodedJWT.getClaim("aid").asInt();
        String sid = decodedJWT.getClaim("sid").asString();
        String password = decodedJWT.getClaim("password").asString();
        Long t = decodedJWT.getClaim("t").asLong();
        t += 5*60*60 * 1000;// 过期时间戳
        if(new Date().getTime() - new Date(t).getTime() <= 10*60*1000){// 十分钟
            // 重新生成token
            token = TokenUtil.sign(aid, sid, password);
        }
        return ResponseUtils.respMsg(token);
    }

    public Response editInterface(Integer vid, Boolean state) {
        accountMapper.editView(vid, (state ? 1 : 0));
        return ResponseUtils.success();
    }

    public Response allView() {
        List views = accountMapper.allView();
        return ResponseUtils.respData(views);
    }
}
