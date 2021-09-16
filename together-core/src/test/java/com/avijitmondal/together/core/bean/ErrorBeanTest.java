package com.avijitmondal.together.core.bean;

import com.avijitmondal.together.core.exception.ErrorCode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ErrorBeanTest {

    private ErrorBean errorBean;

    @Test
    void testValues() {
        assertNull(errorBean);
        errorBean = new ErrorBean();
        assertNull(errorBean.errorCode);
        errorBean.setErrorCode(ErrorCode.AUTHENTICATION_NOT_ADDED.getCode());
        assertNotNull(errorBean.getErrorCode());
    }
}