package com.avijitmondal.together.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RouteFilter extends ZuulFilter {
    private final Log logger = LogFactory.getLog(this.getClass());
    @Override
    public String filterType() {
        return "route";
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
        logger.info("Inside Route Filter");
        return null;
    }
}
