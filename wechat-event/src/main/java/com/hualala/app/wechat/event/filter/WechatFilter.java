package com.hualala.app.wechat.event.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Servlet Filter implementation class WechatFilter
 */
@WebFilter(filterName = "wechatFilter", urlPatterns = "/")
public class WechatFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatFilter.class);

    /**
     * Default constructor. 
     */
    public WechatFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		logger.info(">>> Destory Wechat Filter ...");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		long st = System.currentTimeMillis();
		
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;

		// method URI Protocol
		String httpCommand = req.getMethod() + " " + req.getRequestURI() + " " + req.getProtocol();		
		String contentType = req.getContentType();
		httpCommand += " | ContentType: " + contentType;
		
		if (req.getHeader("User-Agent") != null) {	
			httpCommand += " | User-Agent: " + req.getHeader("User-Agent");
		}		
		
		// get ip router information (nginx: X-Forwarded-For)
		if (req.getHeader("X-Forwarded-For") != null) {	
			// client ip
			httpCommand += " | X-Real-IP: " + req.getHeader("X-Real-IP"); 		
			// 当你搭建两台nginx在不同的ip上，并且都使用了这段配置，那你会发现在web服务器端通过request.getAttribute("X-Forwarded-For")获得的将会是客户端ip和第一台nginx的ip
			httpCommand += " | X-Forwarded-For: " + req.getHeader("X-Forwarded-For"); 
		} else {
			String remoteHost = req.getRemoteHost();
			String remoteIP = req.getRemoteAddr();
			int remotePort = req.getRemotePort();
			httpCommand += " | getRemoteHost: " + remoteHost + "(" + remoteIP + ":" + remotePort + ")";			
		}
		
		httpCommand += " | SessionID: " + req.getSession().getId();
		
		
		// print all header
//		Enumeration en = req.getHeaderNames();
//		while (en.hasMoreElements()) {
//			String header = (String) en.nextElement();
//			System.out.println(header + ": " + req.getHeader(header));
//		}
		
		
		
		String paramString = "";
		Enumeration<?> en = req.getParameterNames();
		while(en.hasMoreElements()) {
			String name = (String)en.nextElement();
			if (paramString.equals("")) {
				paramString = " | Params: " + name + "=" + req.getParameter(name);
			} else {
				paramString = paramString + ", " + name + "=" + req.getParameter(name);
			}
		}
		
		logger.info("[" + st + "] " + httpCommand + paramString + " | " + (System.currentTimeMillis() - st) + "ms");
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		// handle response
		//HttpServletResponse resp = (HttpServletResponse) response;
		
		logger.info("[" + st + "] " + "total processing time: " + (System.currentTimeMillis() - st) + "ms");
		
		//logger.debug(">>> add response here");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info(">>> Init Wechat Filter...");
	}

}
