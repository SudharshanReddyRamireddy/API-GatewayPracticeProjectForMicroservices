package SpringPractice.UserFeignClient.models;



import SpringPractice.UserFeignClient.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Users")
public class User extends Auditable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "NAME CANNOT BE EMPLY")
	private String name;
	
	
	@NotBlank(message = "Mobile number cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
	private String mobileNo;
	
	
	@NotBlank(message = "EMAIL CANNOT BE EMPTY")
	@Email(message = "INVALID MAIL ID")
	private String emailId;
	
	@NotBlank(message = "USERNAME CANNOT BE EMPTY.")
	private String username;
	
	@NotBlank(message = "PASSWORD MUST NOT BE EMPTY.")
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
