package com.hualala.app.wechat.event.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by renjianfei on 2017/12/7.
 */
@Slf4j
@WebFilter(filterName = "msgFilter", urlPatterns = "/msg")
public class MsgFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(">>> Init Msg Filter ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter( servletRequest,servletResponse );
    }

    @Override
    public void destroy() {
        log.info(">>> Destroy Msg Filter ...");
    }
}
