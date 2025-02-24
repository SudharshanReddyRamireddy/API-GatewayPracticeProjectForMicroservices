package SpringPractice.UserFeignClient.services;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.thoughtworks.xstream.security.ForbiddenClassException;

import SpringPractice.UserFeignClient.DTOs.MailRequest_DTO;
import SpringPractice.UserFeignClient.UserAuthDetails.UserAuth;
import SpringPractice.UserFeignClient.feginClientAPIs.MailServices;
import SpringPractice.UserFeignClient.models.User;
import SpringPractice.UserFeignClient.repositories.UserRepositoty;
import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepositoty userRepository;
	
	
	@Autowired
	private MailServices mailServices;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	//check email id is exist or not 
	public Boolean isMailIdExist(String emailId) {
		Optional<User> user = userRepository.findByEmailId(emailId);
		//System.out.println("user: " + user);
		
		if(user.isEmpty()) {
			return false;
		}else {
			return true;
		}
	}
	
	
	// check mobile No exist or not
	public Boolean isMobileNoExist(String mobileNo) {
		
		Optional<User> user = userRepository.findByMobileNo(mobileNo);
		System.out.println("user :" + user);
		if(user.isEmpty()) {
			return false;
		}else {
			return true;
		}
	
		
	}
	
	
	//adding new user
	@Transactional
	public User saveUser(User user) throws BadRequestException {
		try {
			
			String subject = "REGISTRATION SUCCESSFULL";
			
			String bodyText = "Hi...!"+ "\n" + "Mr/Ms : " + user.getName() + "\n" + "Your Successfully Registred at ___" + "\n" + "Thank You...!";
			
			MailRequest_DTO mailDetail = new MailRequest_DTO();
			mailDetail.setSubject(subject);
			mailDetail.setMsgBody(bodyText);
			mailDetail.setRecipient_mail_id(user.getEmailId());
			mailServices.sentMail(mailDetail);
			return userRepository.save(user);
		} catch (Exception e) {
			throw new BadRequestException(e.getMessage());
		}
		
		
		
	}
	
	
	
	// check user existance with given user id
	public Boolean isUserExists(Long userId) {
		
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			return false;
		}else {
			return true;
		}
	}
	
	
	//fetching all users list
	public List<User> getAllUserList(){
		return userRepository.findAll();
	}

	
	
	
	//fetching user details by user id
	public User getUserById(Long userId){
		return userRepository.findById(userId).orElse(null);
	}
	
	
	
	
	// sending mail while order placed successfully
	public void sendOrderPacedMail(Long userId, Long orderId) throws BadRequestException {
		
		User user  =  userRepository.findById(userId).orElseThrow(() -> new BadRequestException("INVALID USER "));
		
		String subject = "ORDER PLACED SUCCESSFULLY";
		String rescepitMailId = user.getEmailId();
		String bodyTecxt = "Hi...!" + "\n" + "Mr/Ms : " + "Name " + "\n" + "ORDER SUCCESSFULLY PLACED" +"\n" + "Your Order Id : " + orderId;
		
		MailRequest_DTO mailDetails = new MailRequest_DTO();
		
		mailDetails.setRecipient_mail_id(rescepitMailId);
		mailDetails.setSubject(subject);
		mailDetails.setMsgBody(bodyTecxt);
		
		mailServices.sentMail(mailDetails);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByEmailId(username);
        return user.map(UserAuth::new)
                     .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
		
	}
}
