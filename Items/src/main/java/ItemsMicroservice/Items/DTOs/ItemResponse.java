package ItemsMicroservice.Items.DTOs;

public class ItemResponse {
    private Long id;
    private String name;
    private String type;
    private String brandName;
    private Double price;
    private String base64Image;  // Image as Base64 string

    // Constructor
    public ItemResponse(Long id, String name, String type, String brandName, Double price, String base64Image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.brandName = brandName;
        this.price = price;
        this.base64Image = base64Image;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBrandName() { return brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getBase64Image() { return base64Image; }
    public void setBase64Image(String base64Image) { this.base64Image = base64Image; }
}
