package be.vdab.toys.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order testOrder;
    private LinkedHashSet<Orderdetail> linkedHashSet = new LinkedHashSet<Orderdetail>();

    @BeforeEach
    void beforeEach() {
        linkedHashSet.add(new Orderdetail(5, BigDecimal.TEN));
        linkedHashSet.add(new Orderdetail(2, BigDecimal.TEN));
        linkedHashSet.add(new Orderdetail(1, BigDecimal.TEN));
        testOrder = new Order(LocalDate.now(), LocalDate.now(), LocalDate.now(), "test",
                new Customer("x", "x", "x", "x", "x", new Country("y"))
                , Status.WAITING, linkedHashSet);
    }

    @Test
    void orderTotalPrice() {
        assertThat(testOrder.orderTotalPrice()).isEqualTo(BigDecimal.valueOf(80));
        assertThat(testOrder.orderTotalPrice()).isPositive();
    }
}