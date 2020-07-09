package be.vdab.toys.domain;
/**
 * @author Mulangu C
 */
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Access(AccessType.FIELD)
public class OrderdetailsId implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
