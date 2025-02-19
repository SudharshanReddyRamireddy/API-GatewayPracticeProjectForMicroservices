package Cart.WishlistMicroservice.Cart.Wishlist.DTOs;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItem {
	
	private Long itemId;
	
	private Integer quentity;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getQuentity() {
		return quentity;
	}

	public void setQuentity(Integer quentity) {
		this.quentity = quentity;
	}

}
