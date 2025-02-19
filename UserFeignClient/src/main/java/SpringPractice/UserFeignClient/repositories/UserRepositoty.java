package SpringPractice.UserFeignClient.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import SpringPractice.UserFeignClient.models.User;


@Repository
public interface UserRepositoty extends JpaRepository<User, Long>{
	
	 // Query to fetch user by mobile number
    @Query("SELECT u FROM User u WHERE u.mobileNo = :mobileNo")
    Optional<User> findByMobileNo(@Param("mobileNo") String mobileNo);
    
    // Query to fetch user by email ID
    @Query("SELECT u FROM User u WHERE u.emailId = :emailId")
    Optional<User> findByEmailId(@Param("emailId") String emailId);

}
