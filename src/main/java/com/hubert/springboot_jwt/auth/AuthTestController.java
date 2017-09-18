package com.hubert.springboot_jwt.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * token生成器
 * Created by Hubrt on 2017/9/12.
 */
@Controller
public class AuthTestController {
//    @Value("${auth.audience}")
//    private String AuthAudience;   //接受者
    @Value("${auth.issuer}")
    private String authIssuer;   //签发者
    @Value("${auth.secret}")
    private String authSecret;    //密钥
    @Value("${auth.expiresSecond}")
    private String authExpiresSecond;  //过期时间


    @RequestMapping("/oauth/token")
    @ResponseBody
    public ResultMsg getAccessToken(@RequestBody User user){
        ResultMsg resultMsg;
        if(StringUtils.isBlank(user.getLoginName())){
            resultMsg = new ResultMsg(ResultStatusCode.NOT_USER_PASSWORD.getErrcode(),ResultStatusCode.NOT_USER_PASSWORD.getErrmsg(),null);
            return resultMsg;
        }
        //通过OSP验证账号密码

        //暂时测试
        if("admin".equals(user.getLoginName()) && "admin".equals(user.getPassword())) {
            //拼装accessToken
            String accessToken = JwtHelper.createJWT(user.getLoginName(),authIssuer,Long.parseLong(authExpiresSecond),authSecret);

            //返回accessToken
            AccessToken accessTokenEntity = new AccessToken();
            accessTokenEntity.setAccess_token(accessToken);
            accessTokenEntity.setExpires_in(Long.parseLong(authExpiresSecond));
            accessTokenEntity.setToken_type("bearer");

            resultMsg = new ResultMsg(ResultStatusCode.OK.getErrcode(), ResultStatusCode.OK.getErrmsg(), accessTokenEntity);
            return resultMsg;
        }

        resultMsg = new ResultMsg(ResultStatusCode.INVALID_LOGIN.getErrcode(), ResultStatusCode.INVALID_LOGIN.getErrmsg(), null);
        return resultMsg;
    }

    @RequestMapping("/oauthMethod/queryUser")
    @ResponseBody
    public ResultMsg queryUser(){
        return new ResultMsg(ResultStatusCode.OK.getErrcode(),ResultStatusCode.OK.getErrmsg(),null);
    }


}
