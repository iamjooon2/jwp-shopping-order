package cart.persistence.entity;

public class ProductEntity {

    private final Long id;
    private final String name;
    private final int price;
    private final String imageUrl;

    public ProductEntity(final String name, final int price, final String imageUrl) {
        this(null, name, price, imageUrl);
    }

    public ProductEntity(final Long id, final String name, final int price, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
