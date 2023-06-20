package com.minzheng.blog.handler;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.minzheng.blog.service.RedisService;
import com.minzheng.blog.service.impl.UserDetailsServiceImpl;
import com.minzheng.blog.util.JwtTokenUtil;
import com.minzheng.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.minzheng.blog.constant.CommonConst.APPLICATION_JSON;

/**
 * 注销处理
 *
 * @author yezhiqiu
 * @date 2021/07/28
 */
@Component
@Slf4j
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private RedisService redisService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        ///在请求头中获取用户信息
        String authHeader = httpServletRequest.getHeader(this.tokenHeader);
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isNotBlank(authHeader) && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());
            String username = jwtTokenUtil.getUserNameFromToken(authToken);
            log.info("checking authentication " + username);
            ///
            if (StringUtils.isNotBlank(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // 校验token
                if (!jwtTokenUtil.validateToken(authToken, userDetails)) {
                    log.info("非法调用" + username);


                }else{
                    ///redis中判断是否存在该token
                    if(redisService.hasKey("login"+authToken)){
                       redisService.del("login"+authToken);
                        httpServletResponse.setContentType(APPLICATION_JSON);
                        httpServletResponse.getWriter().write(JSON.toJSONString(Result.ok()));
                    }else {
                        log.info("非法调用" + username);
                    }


                }
            }
        }

    }

}
