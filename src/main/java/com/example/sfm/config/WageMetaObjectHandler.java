package com.example.sfm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

/**
 * @author wym
 * @date 2020/9/23
 */
@Component
public class WageMetaObjectHandler implements MetaObjectHandler {

    private final static String CREATE_DATE = "createDate";
    private final static String CREATE_BY = "createBy";
    private final static String UPDATE_DATE = "updateDate";
    private final static String UPDATE_BY = "updateBy";
    private final static String DEL_FLAG = "delFlag";

//    @Resource private EmployeeService employeeService;

    @Override
    public void insertFill(MetaObject metaObject) {
        fillValue(metaObject, CREATE_BY, () -> this.currentUserId());
        fillValue(metaObject, CREATE_DATE, () -> getDateValue(metaObject.getSetterType(CREATE_DATE)));
        fillValue(metaObject, DEL_FLAG, () -> "0");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillValue(metaObject, UPDATE_BY, () -> this.currentUserId());
        fillValue(metaObject, UPDATE_DATE, () -> getDateValue(metaObject.getSetterType(UPDATE_DATE)));
    }

    private void fillValue(MetaObject metaObject, String fieldName, Supplier<Object> valueSupplier) {
        if (!metaObject.hasGetter(fieldName)) {
            return;
        }
        Object sidObj = metaObject.getValue(fieldName);
        if (sidObj == null && metaObject.hasSetter(fieldName) && valueSupplier != null) {
            setFieldValByName(fieldName, valueSupplier.get(), metaObject);
        }
    }

    private Object getDateValue(Class<?> setterType) {
        if (Date.class.equals(setterType)) {
            return new Date();
        } else if (LocalDateTime.class.equals(setterType)) {
            return LocalDateTime.now();
        }
        return null;
    }

    /**
     * 获得当前登录人的Id，无人登录即返回null
     * @return
     */
    private String currentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getPrincipal() == null) {
            return null;
        }

        String employeeId = null;
        if (StringUtils.isNotBlank(authentication.getName())) {
            if ("admin".equals(authentication.getName())) {
                employeeId = "admin";
            } else {
//                Employee employee = employeeService.getCurrentLoginEmployee();
//                if (employee != null) {
//                    employeeId = employee.getId();
//                }
            }
        }

        return employeeId;
    }

}
