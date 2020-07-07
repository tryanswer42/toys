package be.vdab.toys.domain;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String scale; //add patern regex if time

    @Lob
    private String description;

    @NotNull
    private int inStock;

    @NotNull
    private int inOrder;

    @NotNull
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private BigDecimal price;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "productlineId")
    private Productline productline;

    @Version
    private long version;

    @OneToMany(mappedBy = "orderdetailsId.product",cascade = CascadeType.ALL)
    @OrderBy("orderdetailsId.order.id")
    private Set<Orderdetail> orderdetails ;

    protected Product() {}

    public Product(@NotBlank String name, @NotBlank String scale, String description, @NotNull int inStock, @NotNull int inOrder, @NotNull BigDecimal price, Productline productline) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.inStock = inStock;
        this.inOrder = inOrder;
        this.price = price;
        this.productline = productline;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Productline getProductline() {
        return productline;
    }

    public Set<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void loweringInStock (int orderShipped){
        if (orderShipped <= 0){
            throw new IllegalArgumentException();
        }
        inStock -= orderShipped;
    }

    public void loweringInOrder (int orderShipped){
        if (orderShipped <= 0){
            throw new IllegalArgumentException();
        }
        inOrder -= orderShipped;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id == product.id &&
                name.equals(product.name) &&
                price.equals(product.price) &&
                Objects.equals(productline, product.productline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, productline);
    }
}
