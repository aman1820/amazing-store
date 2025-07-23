package com.store.amazing.controller;

import com.store.amazing.dto.request.cart.AddToCartRequest;
import com.store.amazing.dto.request.cart.UpdateCartRequest;
import com.store.amazing.dto.response.cart.CartResponse;
import com.store.amazing.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@Validated
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping()
    public ResponseEntity<String> addToCart(@Valid @RequestBody AddToCartRequest request) {
        cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponse> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(cartService.getCart(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateCart(@RequestBody UpdateCartRequest request) {
        cartService.updateCart(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long cartItemId,
                                               @RequestParam Long userId) {
        cartService.removeItem(userId, cartItemId);
        return ResponseEntity.noContent().build();
    }
}
