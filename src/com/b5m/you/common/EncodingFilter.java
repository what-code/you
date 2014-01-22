/**
 * EncodingFilter.java
 *
 * 功  能：[demo]过滤类
 * 类  名：EncodingFilter
 *
 *   ver     变更日       公司      作者     变更内容
 * ──────────────────────────────────────────────
 *   V1.00  '10-11-26  iZENEsoft  金录       初版
 *
 * Copyright (c) 2009 iZENEsoft Business Software corporation All Rights Reserved.
 * LICENSE INFORMATION
 */
package com.b5m.you.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b5m.dao.domain.cnd.Cnd;

/**
 * 本类是过滤类。<BR>
 * 
 * @author 金录
 * @version Ver 1.00 10-11-26 初版
 */
public class EncodingFilter implements Filter {

    public static final String[] FILTER_EXT_NM = { ".gif", ".js", ".css",
            ".jpg", ".htc", ".vbs" };

    protected String encoding = null;

    protected FilterConfig filterConfig = null;

    protected boolean ignore = true;

    // destroy
    public void destroy() {

        this.encoding = null;
        this.filterConfig = null;

    }

    // doFilter
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {

            String servletPath = ((HttpServletRequest) request)
                    .getServletPath();
            for (int i = 0; i < FILTER_EXT_NM.length; i++) {
                if (servletPath.endsWith(FILTER_EXT_NM[i])) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            if (ignore || (request.getCharacterEncoding() == null)) {
                String encoding = selectEncoding(request);
                if (encoding != null) request.setCharacterEncoding(encoding);
            }

            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, response);
            return;
        }
    }

    // init
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
        String value = filterConfig.getInitParameter("ignore");
        if (value == null) this.ignore = true;
        else if (value.equalsIgnoreCase("true")) this.ignore = true;
        else if (value.equalsIgnoreCase("yes")) this.ignore = true;
        else this.ignore = false;
    }

    // selectEncoding
    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }

}
