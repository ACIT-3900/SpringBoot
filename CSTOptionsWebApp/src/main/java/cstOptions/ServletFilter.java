package cstOptions;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ServletFilter implements Filter {
    
    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter (ServletRequest request,
                          ServletResponse response, FilterChain chain)
              throws IOException, ServletException {
        String url = request instanceof HttpServletRequest ?
                  ((HttpServletRequest) request).getRequestURL().toString() : "N/A";
        System.out.println("from filter, processing url: " + url);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy () {
    }
}