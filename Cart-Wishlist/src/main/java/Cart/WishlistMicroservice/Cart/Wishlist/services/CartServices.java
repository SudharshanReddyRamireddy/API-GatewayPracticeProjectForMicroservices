package Cart.WishlistMicroservice.Cart.Wishlist.services;
import java.util.ArrayList;
import java.util.List;


import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cart.WishlistMicroservice.Cart.Wishlist.DTOs.CartItem;
import Cart.WishlistMicroservice.Cart.Wishlist.FeginClientMicroservices.ItemMicroservice;
import Cart.WishlistMicroservice.Cart.Wishlist.models.Carts;
import Cart.WishlistMicroservice.Cart.Wishlist.repositories.CartRepository;

@Service
public class CartServices {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ItemMicroservice itemMicroservices;

	// * create a cart for user with user id
	// * add item into cart with cart id and item id
	// * remove item by item id from cart
	// * calculate total cart value
	// * fetching cart details by cart id
	// * make cart as empty by cart id
	
	

	// create cart for user with user id while that user register
	public void createCart(Carts cart) {
		cartRepository.save(cart);
	}
	
	

	// add item id to cart
	public String addItemToCart(Long cartId, Long itemId) throws BadRequestException {

		if (itemMicroservices.isItemExist(itemId)) {

			CartItem cartItem = new CartItem();
			cartItem.setItemId(itemId);
			cartItem.setQuentity(1);
			Carts cart = cartRepository.findById(cartId)
					.orElseThrow(() -> new BadRequestException("CART NOT FOUND ON ID : " + cartId));
			List<CartItem> cartItemsList = cart.getCartItems();
			cartItemsList.add(cartItem);
			cartRepository.save(cart);
			return "ADDED";
		} else {
			throw new BadRequestException("ITEM NOT FOUND ON ID : " + itemId);
		}

	}
	
	

	// fetching cart details by cart id
	public Carts getCartByCartId(Long cartId) throws BadRequestException {

		Carts cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new BadRequestException("CART NOT FOUND ON ID : " + cartId));
		calculateCartPrice(cart);
		return cart;
	}
	
	
	

	// clear cart
	public String clearCart(Long cartId) throws BadRequestException {

		Carts cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new BadRequestException("CART NOT FOUNF ON ID : " + cartId));
		cart.getCartItems().clear();
		cartRepository.save(cart);
		return "CART CLEAR";
	}
	
	

	// method to calculate total cart price
	private void calculateCartPrice(Carts cart) throws BadRequestException {

		Double toatlPrice = 0D;

		List<CartItem> itemList = cart.getCartItems();

		for (CartItem cartItem : itemList) {

			Double itemPrice = itemMicroservices.getItemPrice(cartItem.getItemId()) * cartItem.getQuentity();

			toatlPrice += itemPrice;
		}
		cart.setCartPrice(toatlPrice);
	}
	
	
	
	

	// method to remove item from cart by item id;
	public String removeItem(Long cartId, Long itemId) throws BadRequestException {

		Carts cart = cartRepository.findById(cartId)
				.orElseThrow(() -> new BadRequestException("CART NOT FOUND ON ID : " + cartId));
		List<CartItem> updatedCartItenList = new ArrayList<CartItem>();
		Boolean isItemExistInCart = false;
		for (CartItem cartItem : cart.getCartItems()) {
			if (cartItem.getItemId() != itemId) {
				updatedCartItenList.add(cartItem);
			} else {
				isItemExistInCart = true;
			}
		}
		if (isItemExistInCart) {
			cart.setCartItems(updatedCartItenList);
			cartRepository.save(cart);
			return "REMOVED";
		} else {
			throw new BadRequestException("ITEM NOT FOUND WITH ID : " + itemId + " IN CART FOR REMOVE.");
		}
	}

}
