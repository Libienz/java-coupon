package coupon.coupon.presentation;

import coupon.coupon.dto.CouponCreateRequest;
import coupon.coupon.dto.CouponResponse;
import coupon.coupon.service.CouponService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;

    @GetMapping("/coupons/{couponId}")
    public ResponseEntity<CouponResponse> getCoupon(@PathVariable final Long couponId) {
        final var coupon = couponService.getCoupon(couponId);
        return ResponseEntity.ok(CouponResponse.from(coupon));
    }

    @PostMapping("/coupons")
    public ResponseEntity<CouponResponse> createCoupon(@RequestBody final CouponCreateRequest couponRequest) {
        final var savedCoupon = couponService.createCoupon(couponRequest);
        final var location = "/coupons/" + savedCoupon.getId();
        return ResponseEntity.created(URI.create(location)).body(CouponResponse.from(savedCoupon));
    }
}