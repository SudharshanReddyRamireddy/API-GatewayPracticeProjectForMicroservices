package ItemsMicroservice.Items.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "Items")
public class Item {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;
    private String brandName;
    private Double price;

    @Lob
    private byte[] image;

    // Constructors
    public Item() {}

    public Item(String name, String type, String brandName, Double price, byte[] image) {
        this.name = name;
        this.type = type;
        this.brandName = brandName;
        this.price = price;
        this.image = image;
    }

    // Getters and Setters
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

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}
