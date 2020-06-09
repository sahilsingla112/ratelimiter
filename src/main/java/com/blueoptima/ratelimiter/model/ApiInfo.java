package com.blueoptima.ratelimiter.model;

import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

/**
 * @author Sahil Singla
 * @version 1.0
 * @since 07-06-2020
 */

@Entity
public class ApiInfo {

	@Id
	@GeneratedValue
	private Long id;

	private String url;

	private Integer ratelimit;

	@Column(name = "accuracy")
	@ColumnTransformer(read = "UPPER(accuracy)", write = "LOWER(?)")
	@Enumerated(EnumType.STRING)
	private RateLimitAccuracy accuracy;

	public ApiInfo() {
	}

	public ApiInfo(String url, Integer ratelimit, RateLimitAccuracy accuracy) {
		this.url = url;
		this.ratelimit = ratelimit;
		this.accuracy = accuracy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getRatelimit() {
		return ratelimit;
	}

	public void setRatelimit(Integer ratelimit) {
		this.ratelimit = ratelimit;
	}

	public RateLimitAccuracy getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(RateLimitAccuracy accuracy) {
		this.accuracy = accuracy;
	}
}
