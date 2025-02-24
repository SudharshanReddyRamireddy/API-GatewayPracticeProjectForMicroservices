package SpringPractice.UserFeignClient.models;

import SpringPractice.UserFeignClient.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	
	
	

}
