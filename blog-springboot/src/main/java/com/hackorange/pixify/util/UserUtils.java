package com.hackorange.pixify.util;

import com.hackorange.pixify.dto.UserDetailDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


/**
 * 用户工具类
 *
 * @author yezhiqiu
 * @date 2021/08/10
 */
@Component
public class UserUtils {

    /**
     * 这些用户是存在服务端的session中的。
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailDTO getLoginUser() {
        return (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
