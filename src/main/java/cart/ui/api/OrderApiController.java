package cart.ui.api;

import cart.application.service.OrderService;
import cart.application.domain.Member;
import cart.ui.dto.request.OrderRequest;
import cart.ui.dto.response.OrderResponse;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderApiController {

    private final OrderService orderService;

    public OrderApiController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> add(final Member member, @RequestBody @Valid final OrderRequest orderRequest) {
        final Long id = orderService.createOrder(member, orderRequest);
        return ResponseEntity.created(URI.create("/orders/" + id)).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(final Member member, @PathVariable final Long id) {
        final OrderResponse orderResponse = orderService.getOrder(id, member);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(final Member member) {
        final List<OrderResponse> orderResponses = orderService.getAll(member);
        return ResponseEntity.ok(orderResponses);
    }
}
