package spittr.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class SpittleForm {

  @NotNull
  @Size(min=1, max=140)
  private String message;
  
  @Min(-180)
  @Max(180)
  private Double longitude;
  
  @Min(-90)
  @Max(90)
  private Double latitude;
  @NotNull
  private long spitter;
  
  public long getSpitter() {
	return spitter;
}

public void setSpitter(long spitter) {
	this.spitter = spitter;
}

public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
  public Double getLongitude() {
    return longitude;
  }

  public SpittleForm(String message, Double longitude, Double latitude, long spitter) {
	super();
	this.message = message;
	this.longitude = longitude;
	this.latitude = latitude;
	this.spitter = spitter;
}

public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }
}
