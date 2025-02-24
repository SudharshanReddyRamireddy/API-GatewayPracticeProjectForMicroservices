package ItemsMicroservice.Items.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@NotBlank(message = "NAME CAN'T EMPTY")
	@Size(min = 2, max = 14, message = "Name must be between 2 and 14 characters")
    private String name;
	
	@NotBlank(message = "TYPE CAN'T EMPTY")
	@Size(min = 2, max = 8, message = "type must be between 2 and 8 characters")
    private String type;
    
    
	@NotBlank(message = "BRAND NAME CAN'T EMPTY")
	@Size(min = 2, max = 8, message = "brand name must be between 2 and 8 characters")
    private String brandName;
	
	@NotNull(message = "PRICE CAN'T BE EMPTY")
    private Double price;

    @Lob
    private byte[] image;
}
