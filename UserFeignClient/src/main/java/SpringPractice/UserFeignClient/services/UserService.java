package SpringPractice.UserFeignClient.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringPractice.UserFeignClient.models.User;
import SpringPractice.UserFeignClient.repositories.UserRepositoty;

@Service
public class UserService {
	
	@Autowired
	private UserRepositoty userRepository;
	
	
	//adding new user
	public User saveUser(User user) {
		return userRepository.save(user);
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

}
