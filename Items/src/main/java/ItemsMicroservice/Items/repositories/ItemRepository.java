package ItemsMicroservice.Items.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ItemsMicroservice.Items.models.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{

}
