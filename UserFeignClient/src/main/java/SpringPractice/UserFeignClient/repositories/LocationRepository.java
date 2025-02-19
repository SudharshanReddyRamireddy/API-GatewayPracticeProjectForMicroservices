package SpringPractice.UserFeignClient.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringPractice.UserFeignClient.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{

	List<Location> findByUserId(Long userId);
}
