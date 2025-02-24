package SpringPractice.UserFeignClient.DTOs;

import java.util.Set;


public class Wishlist_DTO {
	
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
