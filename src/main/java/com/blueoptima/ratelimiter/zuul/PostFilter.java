package com.blueoptima.ratelimiter.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */

public class PostFilter extends ZuulFilter {
	private static final Logger LOG = LoggerFactory.getLogger(PostFilter.class);

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		boolean pass = true;
		return pass;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();


		LOG.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		return null;
	}

}
