package coupon.coupon.service;

import coupon.coupon.domain.Coupon;
import coupon.coupon.domain.CouponRepository;
import coupon.coupon.dto.CouponCreateRequest;
import coupon.coupon.exception.CouponApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public Coupon getCoupon(final Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponApplicationException("쿠폰이 존재하지 않습니다."));
    }

    @Transactional
    public Coupon createCoupon(final CouponCreateRequest couponRequest) {
        return couponRepository.save(couponRequest.toCouponEntity());
    }

    @Transactional
    void deleteAll() {
        couponRepository.deleteAll();
    }
}
