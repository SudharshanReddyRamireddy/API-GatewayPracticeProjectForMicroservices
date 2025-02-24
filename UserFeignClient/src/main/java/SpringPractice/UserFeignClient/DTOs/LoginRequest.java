package SpringPractice.UserFeignClient.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	@NotBlank(message = "USERNAME CANNOT BE EMPTY")
    private String username;
    
	@NotBlank(message = "PASSWORD CANNOT BE EMPTY")
    private String password;
	
    
}
