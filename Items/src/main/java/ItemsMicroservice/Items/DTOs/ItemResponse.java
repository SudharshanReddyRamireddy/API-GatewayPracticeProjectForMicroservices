package ItemsMicroservice.Items.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String name;
    private String type;
    private String brandName;
    private Double price;
    private String base64Image;  // Image as Base64 string
}
