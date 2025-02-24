package Cart.WishlistMicroservice.Cart.Wishlist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.*;

@Entity
@Table(name = "Wishlist")
public class Wishlist {

	@Id
	private Long wishListId;
	
	private Set<Long> itemsList;

	public Long getWishListId() {
		return wishListId;
	}

	public void setWishListId(Long wishListId) {
		this.wishListId = wishListId;
	}

	public Set<Long> getItemsList() {
		return itemsList;
	}

	public void setItemsList(Set<Long> itemsList) {
		this.itemsList = itemsList;
	}
	
	
}
