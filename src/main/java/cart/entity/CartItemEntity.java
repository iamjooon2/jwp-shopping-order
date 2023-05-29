package cart.entity;

public class CartItemEntity {

    private final long id;
    private final long memberId;
    private final long productId;
    private final int quantity;

    public CartItemEntity(final long id, final long memberId, final long productId, final int quantity) {
        this.id = id;
        this.memberId = memberId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public long getMemberId() {
        return memberId;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}