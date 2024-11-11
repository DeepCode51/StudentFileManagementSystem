package com.example.sfm.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.sfm.common.bean.AjaxAuthFailureHandler;
import com.example.sfm.common.service.UserService;
import com.example.sfm.enumeration.Authority;
import com.example.sfm.enumeration.ErrorCode;
import com.example.sfm.mapper.SFMStudentMapper;
import com.example.sfm.mapper.SFMTeacherMapper;
import com.example.sfm.pojo.SFMStudent;
import com.example.sfm.pojo.SFMTeacher;
import com.example.sfm.util.JsonUtil;
import com.example.sfm.util.ResultUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户页面认证、授权
 */
@Configuration
@Order(2)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource private SFMStudentMapper sfmStudentMapper;

    @Resource private SFMTeacherMapper sfmTeacherMapper;

    /**
     * json 格式装换类
     */
    @Resource private ObjectMapper objectMapper;

    /**
     * ajax请求失败处理器。
     */
    @Resource private AjaxAuthFailureHandler ajaxAuthFailureHandler;

    /**
     * 配置以MD5验证密码。
     *
     * @return
     */
    @Bean
    @Order(1)
    public PasswordEncoder md5PasswordEncoderForTenancyUser() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(encode(rawPassword));
            }
        };
    }

    /**
     * 验证用户名、密码和授权。
     *
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetailsService userDetailsService() throws UsernameNotFoundException {
        return (username) -> {
            if (StringUtils.isBlank(username)) {
                throw new UsernameNotFoundException("登录账号不能为空");
            }

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            String password = null;
            // 登录账号SD开头表示学生，TC开头表示教师，admin为系统内置管理员。
            if ("admin".equals(username)) {
                List<Authority> studentAuthority = Authority.getAdminAuthority();
                studentAuthority.forEach( authority -> {
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
                });
                password = "123456";
            } else if (username.startsWith("SD")){
                LambdaQueryWrapper<SFMStudent> lambdaQueryWrapper = Wrappers.lambdaQuery();
                lambdaQueryWrapper.eq(SFMStudent::getNumber, username);
                SFMStudent sfmStudent = sfmStudentMapper.selectOne(lambdaQueryWrapper);
                if (sfmStudent == null) {
                    throw new UsernameNotFoundException("登录账号不存在: " + username);
                } else {
                    List<Authority> studentAuthority = Authority.getStudentAuthority();
                    studentAuthority.forEach( authority -> {
                        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
                    });
                    password = sfmStudent.getPassword();
                }
            } else if (username.startsWith("TC")){
                LambdaQueryWrapper<SFMTeacher> lambdaQueryWrapper = Wrappers.lambdaQuery();
                lambdaQueryWrapper.eq(SFMTeacher::getNumber, username);
                SFMTeacher sfmTeacher = sfmTeacherMapper.selectOne(lambdaQueryWrapper);
                if (sfmTeacher == null) {
                    throw new UsernameNotFoundException("登录账号不存在: " + username);
                } else {
                    List<Authority> studentAuthority = Authority.getTeacherAuthority();
                    studentAuthority.forEach( authority -> {
                        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
                    });
                    password = sfmTeacher.getPassword();
                }
            }

            return User.withUsername(username)
                    .password(password)
                    .authorities(simpleGrantedAuthorities)
                    .build();
        };
    }

    /**
     * 配置自定义验证用户名、密码和授权的服务。
     *
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService());
    }

    /**
     * http请求配置：
     * 1.开启权限拦截路径。
     * 2.释放资源配置。
     * 3.登录请求配置。
     * 4.退出登录配置。
     * 5.默认开启csrf防护。
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(unauthorizedEntryPoint())
                .accessDeniedHandler(handleAccessDeniedForUser())
                .and()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/public/**"
                )
                .permitAll()
                .antMatchers(
                        "/login"
                )
                .permitAll()
                .antMatchers(
                        "/api/v1/login"
                )
                .permitAll()
                .anyRequest()
                .hasAuthority("SFM_SYSTEM_USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/api/v1/login")
                .permitAll()
                .defaultSuccessUrl("/")
                .successHandler(ajaxAuthSuccessHandler())
                .failureHandler(ajaxAuthFailureHandler)
                .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .logoutSuccessHandler(ajaxLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf()
                .disable();
    }

    /**
     * 自定义 “未登入系统，直接请求资源” 处理器。
     * 判断是否ajax请求，是ajax请求则返回json，否则跳转至登录页面。
     *
     * @return
     */
    private AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
            String requestedWithHeader = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWithHeader)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ResultUtil result = ResultUtil.fail(ErrorCode.UNAUTHORIZED);
                response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
            } else {
                response.sendRedirect("/login");
            }
        };
    }

    /**
     * 自定义登录成功处理器。
     *
     * @return
     */
    private AuthenticationSuccessHandler ajaxAuthSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");

            ObjectNode root = objectMapper.createObjectNode();
            root.put("redirect",
                    request.getRequestURI().equals("/api/v1/login") ? "/" : request.getRequestURI());

            response.getOutputStream().write(root.toString().getBytes());
        };
    }

    /**
     * 自定义退出成功处理器。
     *
     * @return
     */
    private LogoutSuccessHandler ajaxLogoutSuccessHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            ObjectNode root = objectMapper.createObjectNode();
            root.put("redirect", "/login");

            response.getOutputStream().write(root.toString().getBytes());
        };
    }

    /**
     * 自定义 “无权请求的资源” 处理器。
     *
     * @return
     */
    private AccessDeniedHandler handleAccessDeniedForUser() {
        return (HttpServletRequest request,
                HttpServletResponse response,
                AccessDeniedException accessDeniedException) -> {
            String requestedWithHeader = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(requestedWithHeader)) {
                response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
                response.setContentType("application/json");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                ResultUtil result = ResultUtil.fail(ErrorCode.FORBIDDEN);
                response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
            } else {
                response.sendRedirect("/login");
            }
        };
    }
}

