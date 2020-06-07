package com.blueoptima.ratelimiter.model;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-06-2020
 */
public class ApiRegistrationReq {
	// Example: apiv1dev
	private String name;

	private String requestPath;
	private boolean includeRequestPathInUri = false;

	// Example: http://blueoptima.devapi.com
	private String downStreamServiceUrl;

	// Example: /api/v1/developers
	private String downStreamApiUri;

	// If user+api combination is not available, this limit will apply.
	private Integer defaultLimitPerSecond;

	//TODO: future support
	// private Integer defaultLimitPerMinute;

	// This flag will refresh the config data in memory as well as underlying storage
	// This will make the update with immediate effect.
	private boolean refresh;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRequestPath() {
		return requestPath;
	}

	public void setRequestPath(String requestPath) {
		this.requestPath = requestPath;
	}

	public boolean isIncludeRequestPathInUri() {
		return includeRequestPathInUri;
	}

	public void setIncludeRequestPathInUri(boolean includeRequestPathInUri) {
		this.includeRequestPathInUri = includeRequestPathInUri;
	}

	public String getDownStreamServiceUrl() {
		return downStreamServiceUrl;
	}

	public void setDownStreamServiceUrl(String downStreamServiceUrl) {
		this.downStreamServiceUrl = downStreamServiceUrl;
	}

	public String getDownStreamApiUri() {
		return downStreamApiUri;
	}

	public void setDownStreamApiUri(String downStreamApiUri) {
		this.downStreamApiUri = downStreamApiUri;
	}

	public Integer getDefaultLimitPerSecond() {
		return defaultLimitPerSecond;
	}

	public void setDefaultLimitPerSecond(Integer defaultLimitPerSecond) {
		this.defaultLimitPerSecond = defaultLimitPerSecond;
	}

	public boolean isRefresh() {
		return refresh;
	}

	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
}
