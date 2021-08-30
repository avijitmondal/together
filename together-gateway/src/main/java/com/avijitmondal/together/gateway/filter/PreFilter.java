package com.avijitmondal.together.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PreFilter extends ZuulFilter {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("Request Method : " + request.getMethod() + " Request URL : " + request.getRequestURL().toString());
        return null;
    }
}
