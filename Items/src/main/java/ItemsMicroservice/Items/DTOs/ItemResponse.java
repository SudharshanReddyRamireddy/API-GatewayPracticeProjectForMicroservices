package ItemsMicroservice.Items.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 14, message = "Name must be between 2 and 14 characters")
    private String name;

    @NotBlank(message = "Type cannot be blank")
    @Size(max = 8, message = "Type must not exceed 8 characters")
    private String type;

    @NotBlank(message = "Brand name cannot be blank")
    @Size(max = 8, message = "Brand name must not exceed 8 characters")
    private String brandName;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    
    private String base64Image;  // Image as Base64 string
}
