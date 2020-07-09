package be.vdab.toys.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class OrderdetailTest {

    private Orderdetail orderdetail;
    private Orderdetail orderdetail2;
    private Orderdetail orderdetail3;
    @BeforeEach
    void beforeEach() {
        orderdetail = new Orderdetail(10, BigDecimal.valueOf(2.5));
        orderdetail2 = new Orderdetail(-10, BigDecimal.valueOf(2.5));
        orderdetail3 = new Orderdetail(10, BigDecimal.valueOf(-2.5));
    }

    @Test
    void productTotalPrice() {
        assertThat(orderdetail.productTotalPrice()).isEqualTo(BigDecimal.valueOf(25.0));
    }

    @Test
    void productTotalPriceMustBeGreaterThanOrEqualToZero() {
        assertThat(orderdetail.productTotalPrice()).isGreaterThanOrEqualTo(BigDecimal.ZERO);
    }

    @Test
    void productTotalPriceIllegalIfOrderedIsNegatif(){
        assertThatIllegalArgumentException().isThrownBy(()->orderdetail2.productTotalPrice());
    }

    @Test
    void productTotalPriceIllegalIfPriceEachIsNegatif(){
        assertThatIllegalArgumentException().isThrownBy(()->orderdetail3.productTotalPrice());
    }
}