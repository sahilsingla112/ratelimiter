package com.blueoptima.ratelimiter.service;

import com.blueoptima.ratelimiter.model.ApiRegistrationReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */
@Service
public class ZuulRouteConfigServiceImpl implements ZuulRouteConfigService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ZuulRouteConfigServiceImpl.class);

	@Autowired
	private ZuulProperties zuulProperties;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	private String zuulServerUrl;

	@Value("${server.port}")
	private String port;

	@PostConstruct
	public void init(){
		zuulServerUrl = String.format("%s:%s", "http://localhost", port);
	}

	@Override public void addRouteToZuulConfig(ApiRegistrationReq request) {
		//TODO
		// zuul.routes.<name>.path=/<name>/**
		// zuul.routes.<name>.url=<downStreamServiceUrl>
		String zuulRouteId = request.getName();

		String url =  request.getDownStreamServiceUrl();


		String zuulPath = String.format("/%s/**", zuulRouteId);

		ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
		zuulRoute.setId(zuulRouteId);
		zuulRoute.setUrl(url);
		zuulRoute.setPath(zuulPath);

		// Execute shell script to add this configuration to git file.
		// TODO: Future implementation may require exploring JAVA API of git instead of invoking shell script.

	}

	// It will refresh the routing information dynamically without restart required.
	@Override public void refreshZuulConfig(String zuulRouteId) {
		String personResultAsJsonStr = restTemplate.postForObject(zuulServerUrl, new HttpEntity<String>(""), String.class);
		try {
			JsonNode root = objectMapper.readTree(personResultAsJsonStr);
			LOGGER.info("");

		} catch (JsonProcessingException e) {
			LOGGER.error("Exception while parsing actuator refresh response", e);
		}
	}
}
