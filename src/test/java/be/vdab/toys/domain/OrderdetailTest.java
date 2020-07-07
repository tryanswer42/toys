package be.vdab.toys.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class OrderdetailTest {

    private Orderdetail orderdetail;

    @BeforeEach
    void beforeEach(){orderdetail = new Orderdetail(10, BigDecimal.valueOf(2.5));

    }

    @Test
    void productTotalPrice() {
        ;
        assertThat(orderdetail.productTotalPrice()).isEqualTo(BigDecimal.valueOf(25.0));
    }
}