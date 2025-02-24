package Cart.WishlistMicroservice.Cart.Wishlist.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import Cart.WishlistMicroservice.Cart.Wishlist.FeginClientMicroservices.ItemMicroservice;
import Cart.WishlistMicroservice.Cart.Wishlist.models.Wishlist;
import Cart.WishlistMicroservice.Cart.Wishlist.repositories.WishListRepository;

@Service
public class WishListServices {
	
	@Autowired
	private WishListRepository wishListRepository;
	
	@Autowired
	private ItemMicroservice itemServices;
	
	
	
	
	//fetch users wishlist by user id
	public Wishlist getUsersWishList(Long userId) throws BadRequestException {
		return wishListRepository.findById(userId).orElseThrow(() -> new BadRequestException("WISHLIST NOT FOUND ON GIVEN USER ID"));
	}
	
	
	
	
	
	
	
	
	
	// creating a wish list for user with user id only
	public void createWish(Long userId) {
		
		Wishlist wishList = new Wishlist();
		wishList.setWishListId(userId);
		Set<Long> itemsList = new HashSet<Long>();
		wishList.setItemsList(itemsList);
		wishListRepository.save(wishList);
		
	}
	
	
	
	// adding items ids to wish list
	public String addItemToWishlist(Long wishListId, Long itemId) throws BadRequestException {
		
		Wishlist wishList = wishListRepository.findById(wishListId).orElseThrow(() -> new BadRequestException("WISHLIST NOT FOUND WITH ID :" + wishListId));
		System.out.println(wishList.getItemsList());
		if(itemServices.isItemExist(itemId)) {// checking item is there or not
			if(!wishList.getItemsList().contains(itemId)) {// checking that given item id was existance or not
				System.out.println("2");
				wishList.getItemsList().add(itemId);
				wishListRepository.save(wishList);
			}
			return "ITEM ADDED SUCCESSFULLY.";
		}else {
			throw new BadRequestException("ITEM NOT FOUND WITH ID : " + itemId + " TO ADD INTO WISH LIST");
		}
		
	}
	
	
	
	//remove item from respective user wish list
	public String removeItemFromWishList(Long wishListId, Long itemId) throws BadRequestException {
		
		Wishlist wishList = wishListRepository.findById(wishListId).orElseThrow(() -> new BadRequestException("WISHLIST NOT FOUND WITH ID : "+ wishListId+ "TO REMOVE."));
		
		if(wishList.getItemsList().contains(itemId)) {
			wishList.getItemsList().remove(itemId);
			wishListRepository.save(wishList);
			return "ITEM REMOVED FROM WISH LIST.";
		}else {
			throw new BadRequestException("ITEM NOT FOUND IN WISH LIST TO REMOVE.");
		}
		
	}

}
