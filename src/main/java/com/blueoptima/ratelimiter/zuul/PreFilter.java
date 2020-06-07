package com.blueoptima.ratelimiter.zuul;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.ZuulFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import java.util.Enumeration;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */


public class PreFilter extends ZuulFilter {

	private static final Logger LOG = LoggerFactory.getLogger(PreFilter.class);

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		LOG.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

		Enumeration <String> enume = ctx.getRequest().getHeaderNames();
		String header;
		while (enume.hasMoreElements()) {
			header = enume.nextElement();
			LOG.info(String.format("Headers: %s = %s \n", header, ctx.getRequest().getHeader(header)));
		}

		return null;
	}

}