package com.blueoptima.ratelimiter.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 06-06-2020
 */
@Embeddable
public class UserApiKey implements Serializable {

	@NotNull
	private String userid;

	@NotNull
	private String api;

	public UserApiKey() {
	}

	public UserApiKey(@NotNull String userid, @NotNull String api) {
		this.userid = userid;
		this.api = api;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	@Override public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserApiKey that = (UserApiKey) o;
		return Objects.equals(userid, that.userid) && Objects.equals(api, that.api);
	}

	@Override public int hashCode() {
		return Objects.hash(userid, api);
	}
}
