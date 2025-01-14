package cart.application.domain;

import cart.exception.CartItemException;
import java.util.Objects;

public class CartItem {

    private final Long id;
    private Integer quantity;
    private final Product product;
    private final Member member;

    public CartItem(final Product product, final Member member) {
        this(null, 1, product, member);
    }

    public CartItem(final int quantity, final Product product, final Member member) {
        this(null, quantity, product, member);
    }

    public CartItem(final Long id, int quantity, final Product product, final Member member) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Product getProduct() {
        return product;
    }

    public Long getMemberId() {
        return member.getId();
    }

    public Long getProductId() {
        return product.getId();
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void validateOwner(Member member) {
        if (!Objects.equals(this.member.getId(), member.getId())) {
            throw new CartItemException.IllegalMember(this, member);
        }
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }
}
