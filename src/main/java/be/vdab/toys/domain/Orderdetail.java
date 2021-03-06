package be.vdab.toys.domain;
/**
 * @author Mulangu C
 */

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
@AssociationOverrides({
        @AssociationOverride(name = "orderdetailsId.order", joinColumns = @JoinColumn(name = "orderId")),
        @AssociationOverride(name = "orderdetailsId.product", joinColumns = @JoinColumn(name = "productId"))
})//Because EmbeddedId is used
public class Orderdetail {

    //Composed Id key serves as PK
    @EmbeddedId
    private OrderdetailsId orderdetailsId = new OrderdetailsId();

    @NotNull
    @PositiveOrZero
    private int ordered;

    @NotNull
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private BigDecimal priceEach;

    //---
    protected Orderdetail() {
    }

    public Orderdetail(@NotNull @PositiveOrZero int ordered, @NotNull @PositiveOrZero BigDecimal priceEach) {
        this.ordered = ordered;
        this.priceEach = priceEach;
    }
    //---

    public OrderdetailsId getOrderdetailsId() {
        return orderdetailsId;
    }

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public BigDecimal productTotalPrice() {
        if (ordered < 0 || priceEach.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        return priceEach.multiply(BigDecimal.valueOf(ordered));
    }

    public boolean isDeliverable() {
        boolean deliverable = false;
        if (getProduct().getInOrder() - getOrdered() >= 0 && getProduct().getInStock() - getOrdered() >= 0) {
            deliverable = true;
        }
        return deliverable;
    }


    @Transient // so that Hibernate doesn’t try to map these getters
    public Order getOrder() {
        return getOrderdetailsId().getOrder();
    }

    @Transient
    public Product getProduct() {
        return getOrderdetailsId().getProduct();
    }

}
