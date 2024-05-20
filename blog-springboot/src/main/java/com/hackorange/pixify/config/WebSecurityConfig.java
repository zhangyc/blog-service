package com.hackorange.pixify.config;

import com.hackorange.pixify.filter.JwtAuthenticationTokenFilter;
import com.hackorange.pixify.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/**
 * Security配置类
 *
 * @author yezhiqiu
 * @date 2021/07/29
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;
    @Autowired
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailHandlerImpl authenticationFailHandler;
    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new FilterInvocationSecurityMetadataSourceImpl();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new AccessDecisionManagerImpl();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    /**
     * 密码加密
     *
     * @return {@link PasswordEncoder} 加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * JWT filter
     */
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 配置权限
     *
     * @param http http
     * @throws Exception 异常
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.
                csrf().
                disable().
                sessionManagement().

                sessionCreationPolicy(SessionCreationPolicy.STATELESS).

                and().
                authorizeRequests().

                antMatchers(HttpMethod.GET,"/example").///不需要授权的静态资源，比如swagger文档
                permitAll().
                antMatchers("/users/login","/users/code","/register","/articles").///不需要授权的api请求
                permitAll().
                antMatchers(HttpMethod.OPTIONS).
                permitAll().
                anyRequest().///除了上面的剩下所有的都需要鉴权
                authenticated();


                http.headers().cacheControl(); ///禁用缓存
                http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
                http.exceptionHandling().
                        accessDeniedHandler(accessDeniedHandler).
                        authenticationEntryPoint(authenticationEntryPoint);
                http.formLogin().loginProcessingUrl("/users/login")
                        .successHandler(authenticationSuccessHandler)
                        .failureHandler(authenticationFailHandler);

                http.logout().logoutUrl("/users/logout").logoutSuccessHandler(logoutSuccessHandler);


//
//        // 配置登录注销路径
//        http.formLogin()
//                .loginProcessingUrl("/login")
//                .successHandler(authenticationSuccessHandler)
//                .failureHandler(authenticationFailHandler)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(logoutSuccessHandler);
//        // 配置路由权限信息
//        http.authorizeRequests()
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
//                        fsi.setSecurityMetadataSource(securityMetadataSource());
//                        fsi.setAccessDecisionManager(accessDecisionManager());
//                        return fsi;
//                    }
//                })
//                .anyRequest().permitAll()
//                .and()
//                // 关闭跨站请求防护
//                .csrf().disable().exceptionHandling()
//                // 未登录处理
//                .authenticationEntryPoint(authenticationEntryPoint)
//                // 权限不足处理
//                .accessDeniedHandler(accessDeniedHandler)
//                .and()
//                .sessionManagement()
//                .maximumSessions(20)
//                .sessionRegistry(sessionRegistry());
    }

}
