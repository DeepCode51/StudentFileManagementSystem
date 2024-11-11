package com.example.sfm.common.bean;

import com.example.sfm.enumeration.ErrorCode;
import com.example.sfm.util.JsonUtil;
import com.example.sfm.util.ResultUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 自定义认证失败处理器。
 */
@Component
public class AjaxAuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        ResultUtil result = ResultUtil.fail(ErrorCode.BAD_REQUEST);
        response.getOutputStream().write(JsonUtil.OBJECT_MAPPER.writeValueAsBytes(result));
    }
}
