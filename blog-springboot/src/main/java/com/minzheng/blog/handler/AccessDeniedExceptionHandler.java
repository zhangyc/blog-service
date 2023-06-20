package com.minzheng.blog.handler;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by 大雪冬至 on 2021/7/6.
 * 解决 GlobalExceptionHandler 会消费掉 AccessDeniedException ,导致 AccessDeniedHandler 不会被触发的问题.
 */
@ControllerAdvice
public class AccessDeniedExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        throw e;
    }
}
