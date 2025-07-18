package com.store.amazing.service;

import com.store.amazing.dto.request.cart.AddToCartRequest;
import com.store.amazing.dto.request.cart.UpdateCartRequest;
import com.store.amazing.dto.response.cart.CartItemResponse;
import com.store.amazing.dto.response.cart.CartResponse;
import com.store.amazing.entity.Cart;
import com.store.amazing.entity.CartItem;
import com.store.amazing.entity.Product;
import com.store.amazing.repository.CartItemRepository;
import com.store.amazing.repository.CartRepository;
import com.store.amazing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public void addToCart(AddToCartRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() ->new RuntimeException("Product doesnt exists"));

        Cart cart = cartRepository.findByUserId(request.userId())
                .orElseGet(() -> cartRepository.save(Cart.builder().userId(request.userId()).build()));

        CartItem existingItem = cart.getItems() == null ? null :
                cart.getItems().stream()
                        .filter(item -> Objects.equals(item.getProduct().getId(), request.productId()))
                        .findFirst().orElse(null);

        if (Objects.isNull(existingItem)) {
            existingItem = CartItem.builder()
                    .product(product)
                    .cart(cart)
                    .quantity(request.quantity())
                    .build();
            cartItemRepository.save(existingItem);
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + request.quantity());
            cartItemRepository.save(existingItem);
        }
    }

    public void updateCart(UpdateCartRequest request) {
        CartItem cartItem = cartItemRepository.findById(request.cartItemId()).orElseThrow(() -> new RuntimeException("Cannot find Cart item"));

        if (!cartItem.getCart().getUserId().equals(request.userId())) {
            throw new RuntimeException("Cart does not belong to the userId");
        }

        cartItem.setQuantity(request.quantity());
        cartItemRepository.save(cartItem);
    }

    public CartResponse getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Could not find cart for the User"));

        List<CartItemResponse> items = cart.getItems()
                .stream()
                .map(item -> new CartItemResponse(item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity(),
                        item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))))
                .toList();

        BigDecimal totalCartPrice = items.stream()
                .map(CartItemResponse::totalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .items(items)
                .totalPrice(totalCartPrice)
                .build();

    }

}
