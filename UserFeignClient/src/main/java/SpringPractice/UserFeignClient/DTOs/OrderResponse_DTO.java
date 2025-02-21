package SpringPractice.UserFeignClient.DTOs;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OrderResponse_DTO {
    
    
	 	@Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private Long id;
	    
	    private Long itemId;
	    
	    private Long userId;
	    private LocalDate dateOfOrder;
	    private Double totalCost;
	    private Long totalItems; 
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public LocalDate getDateOfOrder() {
			return dateOfOrder;
		}
		public void setDateOfOrder(LocalDate dateOfOrder) {
			this.dateOfOrder = dateOfOrder;
		}
		public Double getTotalCost() {
			return totalCost;
		}
		public void setTotalCost(Double totalCost) {
			this.totalCost = totalCost;
		}
		public Long getTotalItems() {
			return totalItems;
		}
		public void setTotalItems(Long totalItems) {
			this.totalItems = totalItems;
		}
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		
}