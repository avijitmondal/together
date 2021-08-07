package com.avijitmondal.together.core.bean;

import com.avijitmondal.together.core.exception.ErrorCode;
import junit.framework.TestCase;

public class ErrorBeanTest extends TestCase {

    private ErrorBean errorBean;

    public void testValues() {
        assertNull(errorBean);
        errorBean = new ErrorBean();
        assertNull(errorBean.errorCode);
        errorBean.setErrorCode(ErrorCode.AUTHENTICATION_NOT_ADDED.getCode());
        assertNotNull(errorBean.getErrorCode());
    }

}