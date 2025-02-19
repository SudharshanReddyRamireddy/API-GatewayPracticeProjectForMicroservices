package SpringPractice.UserFeignClient.models;

import SpringPractice.UserFeignClient.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Location extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	
	@NotBlank(message = "COUNTRY CANNOT BE EMPTY")
	private String country;
	
	@NotBlank(message = "STATE CANNOT BE EMPTY")
	private String state;
	
	
	@NotNull(message = "PINCODE MUST BE ENTERED")
	private Long pincode;
	
	
	@NotBlank(message = "ADDRESS CANNOT BE EMPTY")
	private String address;
	
	@NotBlank(message = "TYPE MUST BE ENTERED")
	private String type;
	
	@NotNull(message = "USER ID MUST NOT BE EMPTY")
	private Long userId;

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
