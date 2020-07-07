package be.vdab.toys.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ProductTest {
private Product testproduct;

    @BeforeEach
    void beforeEach(){testproduct = new Product("nametest","1:1","descriptiontest",30,40,BigDecimal.TEN,new Productline("nametest","descriptiontest"));
    }

    @Test
    void loweringInStock() {
        testproduct.loweringInStock(10);
        assertThat(testproduct.getInStock()).isEqualTo(20);
    }

    @Test
    void loweringInStockWithNegatifFails(){
        assertThatIllegalArgumentException().isThrownBy(() -> testproduct.loweringInStock(-1));
    }

    @Test
    void loweringInStockWithZeroFails(){
        assertThatIllegalArgumentException().isThrownBy(() -> testproduct.loweringInStock(0));
    }

    @Test
    void loweringInOrder() {
        testproduct.loweringInOrder(10);
        assertThat(testproduct.getInOrder()).isEqualTo(30);
    }

    @Test
    void loweringInOrderWithNegatifFails(){
        assertThatIllegalArgumentException().isThrownBy(() -> testproduct.loweringInOrder(-1));
    }

    @Test
    void loweringInOrderWithZeroFails(){
        assertThatIllegalArgumentException().isThrownBy(() -> testproduct.loweringInOrder(0));
    }


}