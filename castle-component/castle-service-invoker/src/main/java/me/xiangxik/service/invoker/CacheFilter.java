package me.xiangxik.service.invoker;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@WebFilter(urlPatterns="/*", filterName="hystrixFilter")
public class CacheFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("******");
		HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ctx.shutdown();
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
