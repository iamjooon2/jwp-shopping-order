package cart.application.service;

import cart.application.domain.CartItem;
import cart.application.domain.Member;
import cart.application.domain.Product;
import cart.ui.dto.request.CartItemQuantityUpdateRequest;
import cart.ui.dto.request.CartItemRequest;
import cart.ui.dto.response.CartItemResponse;
import cart.application.repository.CartItemRepository;
import cart.application.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItemService(final CartItemRepository cartItemRepository, final ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public List<CartItemResponse> findByMember(final Member member) {
        final List<CartItem> cartItems = cartItemRepository.findByMemberId(member.getId());
        return cartItems.stream().map(CartItemResponse::of).collect(Collectors.toList());
    }

    public Long add(final Member member, final CartItemRequest cartItemRequest) {
        final Product product = productRepository.findById(cartItemRequest.getProductId());
        final CartItem cartItem = cartItemRepository.save(new CartItem(product, member));
        return cartItem.getId();
    }

    public void updateQuantity(final Member member, final Long id, final CartItemQuantityUpdateRequest request) {
        final CartItem cartItem = cartItemRepository.findById(id);
        cartItem.checkOwner(member);

        if (request.getQuantity() == 0) {
            cartItemRepository.deleteById(id);
            return;
        }

        cartItem.changeQuantity(request.getQuantity());
        cartItemRepository.updateQuantity(cartItem);
    }

    public void remove(final Member member, final Long id) {
        final CartItem cartItem = cartItemRepository.findById(id);
        cartItem.checkOwner(member);

        cartItemRepository.deleteById(id);
    }
}
