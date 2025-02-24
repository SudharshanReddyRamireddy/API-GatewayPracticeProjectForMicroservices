package Cart.WishlistMicroservice.Cart.Wishlist.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Wishlist")
public class Wishlist {

	@Id
	private Long wishListId;
	
	private Set<Long> itemsList;
	
	
}
