package com.hgl.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.hgl.entity.TokenInfo;
import com.hgl.mapper.UsersMapper;
import com.hgl.pojo.Users;
import com.hgl.service.AuthService;
import com.hgl.utils.TokenUtil;
import com.hgl.utils.crypto.CryptoUtils;
import com.hgl.utils.response.ResponseUtils;
import com.hgl.utils.response.entity.Response;
import com.hgl.utils.response.entity.ResponseEnum;
import org.apache.commons.codec.DecoderException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${AppSecret}")
    private String AES_SECRET;// 密钥
    @Value("${AppID}")
    private String AES_IV;// 偏移量

    @Autowired
    private UsersMapper usersMapper;

    public Response getAccessToken(String verify_request) {
        String decryptStr = "";
        try {
            decryptStr = CryptoUtils.decryptAES(verify_request, AES_SECRET, AES_IV);
            JSONObject jsonObject = new JSONObject(decryptStr);
            String yb_uid = jsonObject.getJSONObject("visit_user").getString("userid");
            if(jsonObject.get("visit_oauth") instanceof Boolean){
                return ResponseUtils.response(ResponseEnum.LOGIN_AUTH);
            }
            String access_token = jsonObject.getJSONObject("visit_oauth").getString("access_token");
            // 判断是否已存在该用户信息
            Users user = usersMapper.findUserByYbUid(yb_uid);
            if(user == null){// 没有进行易班成员验证, 返回access_token
                return ResponseUtils.respMsg(1601, access_token);
            }
            // 学号、易班id、用户id生成token
            String token = TokenUtil.sign(user.getUid() + "", user.getSid(), user.getYbUid());// token
            // 将token、user信息返回
            Map<String, Object> map = new HashMap<>();
            map.put("token", token);
            map.put("info", user);
            map.put("access_token", access_token);
            return ResponseUtils.respData(map);

        } catch (DecoderException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public Response verifyToken(String token) {
        // 校验token是否过期
        boolean verify = TokenUtil.verify(token);
        if(!verify){
            // token过期，请求重新授权
            return ResponseUtils.response(ResponseEnum.LOGIN_CODE);
        }
        // 解析token
        DecodedJWT tokenInfo = TokenUtil.parseJWT(token);
        Integer uid = Integer.parseInt(tokenInfo.getClaim("uid").asString());
        String sid = tokenInfo.getClaim("sid").asString();
        String yb_uid = tokenInfo.getClaim("yb_uid").asString();
        String t = tokenInfo.getClaim("t").asString();// 创建时间
        // 检查token剩余时间
        long expire = Long.parseLong(t) + (5 * 60 * 60 * 1000); // 到期时间戳
        long l = new Date().getTime() - expire;
        if(l < 30 * 60 * 1000){
            // 剩余时间小于30分钟，重新生成token
            token = TokenUtil.sign(uid + "", sid, yb_uid);
        }
        // 通过uid获取用户信息
        Users user = usersMapper.selectByPrimaryKey(uid);
        // 将token、user信息返回
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("info", user);
        return ResponseUtils.respData(map);
    }
}
