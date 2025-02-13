package SpringPractice.UserFeignClient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import SpringPractice.UserFeignClient.models.User;

@Repository
public interface UserRepositoty extends JpaRepository<User, Long>{

}
