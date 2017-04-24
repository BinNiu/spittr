package spittr.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Spittle {

	private final Long id;
	@NotNull
	@Size(min = 10, max = 140)
	private final String message;
	@NotNull
	private final Date time;
	@NotNull
	private Double latitude;
	@NotNull
	private Double longitude;
	@NotNull
	private long spitter;
	
	
	
	
	public Spittle(String message, Date time, long spitter) {
		this(null,message,time,null,null,spitter);
	}

	public Spittle(Long id, String message, Date time, Double latitude, Double longitude, long spitter) {
		super();
		this.id = id;
		this.message = message;
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.spitter = spitter;
	}
	
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public long getSpitter() {
		return spitter;
	}

	public void setSpitter(long spitter) {
		this.spitter = spitter;
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getTime() {
		return time;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}



}
