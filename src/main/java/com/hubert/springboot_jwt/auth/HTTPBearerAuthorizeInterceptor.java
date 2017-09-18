package com.hubert.springboot_jwt.auth;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT权限验证拦截器
 * Created by Hubrt on 2017/9/12.
 */
@Component
public class HTTPBearerAuthorizeInterceptor implements HandlerInterceptor {


    private String authSecret = "123";    //密钥

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUri = request.getRequestURI();
        //login/logout/error不拦截  || 测试阶段：带/auth/的才拦截
        //if(!requestUri.endsWith("login") && !requestUri.endsWith("logout") && !requestUri.endsWith("error")) {
        if(requestUri.indexOf("oauthMethod") != -1){
            ResultMsg resultMsg;
            String auth = request.getHeader("Authorization");
            if (auth != null) {
                if (JwtHelper.parseJWT(auth, authSecret) != null) {
                    //验证通过
                    return true;
                }
            }
            //jwt不通过
            resultMsg = new ResultMsg(ResultStatusCode.INVALID_TOKEN.getErrcode(), ResultStatusCode.INVALID_TOKEN.getErrmsg(), null);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(JSON.toJSONString(resultMsg));
            response.getWriter().flush();
            return false;

        }else{
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
