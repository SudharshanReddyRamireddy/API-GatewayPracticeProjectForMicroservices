package Cart.WishlistMicroservice.Cart.Wishlist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Cart.WishlistMicroservice.Cart.Wishlist.models.Wishlist;


@Repository
public interface WishListRepository extends JpaRepository<Wishlist, Long>{

}
