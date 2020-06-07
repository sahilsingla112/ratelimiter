package com.blueoptima.ratelimiter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 04-06-2020
 */

@Component
public class ConfigurationParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationParser.class);

	private Properties source = new Properties();

	@PostConstruct
	public void init(){
		initProperties();
	}

	private void initProperties() {
		InputStream is = null;
		try {
			String configFilePath = System.getProperty("CONFIG_FILE");
			if (StringUtils.isEmpty(configFilePath)){
				configFilePath = "application.properties";
				is = RateLimiterConfig.class.getClassLoader().getResourceAsStream(configFilePath);
			}else{
				is = new FileInputStream(configFilePath);
			}

			this.source.load(is);
		} catch (final IOException e) {
			LOGGER.error("Error while loading configuration from config file", e);
		}finally{
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					LOGGER.error("Error while closing the input stream", e);
				}
		}
	}
}
