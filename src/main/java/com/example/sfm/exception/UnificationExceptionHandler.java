package com.example.sfm.exception;

import com.example.sfm.enumeration.ErrorCode;
import com.example.sfm.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@ControllerAdvice
@ResponseBody
public class UnificationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnificationExceptionHandler.class);


    /**
     * 普通信息返回
     * @param e
     * @return
     */
    @ExceptionHandler(GeneralException.class)
    public ResultUtil generalException(GeneralException e) {
        LOGGER.error(ErrorCode.GENERAL.getMessage(), e);
        return resultExceptionMessage(ErrorCode.GENERAL, e.getMessage());
    }

    /**
     * 警告信息返回
     * @param e
     * @return
     */
    @ExceptionHandler(WarningException.class)
    public ResultUtil warningException(WarningException e) {
        LOGGER.error(ErrorCode.WARING.getMessage(), e);
        return resultExceptionMessage(ErrorCode.WARING, e.getMessage());
    }

    /**
     * 错误信息返回
     * @param e
     * @return
     */
    @ExceptionHandler(DangerException.class)
    public ResultUtil dangerException(DangerException e) {
        LOGGER.error(ErrorCode.ERROR.getMessage(), e);
        return resultExceptionMessage(ErrorCode.ERROR, e.getMessage());
    }

    /**
     * 系统错误
     * @param e
     * @return
     */
    @ExceptionHandler
    public ResultUtil exception(Exception e) {
        LOGGER.error(ErrorCode.SYSTEM_ERROR.getMessage(), e);
        return resultExceptionMessage(ErrorCode.SYSTEM_ERROR, null);
    }

    private ResultUtil resultExceptionMessage(ErrorCode errorCode, String message) {
        // 若未设置提示信息message，默认取ErrorCode的message
        message = StringUtils.isBlank(message) ? errorCode.getMessage() : message;
        return ResultUtil.result(errorCode.getCode(),  message);
    }
}

