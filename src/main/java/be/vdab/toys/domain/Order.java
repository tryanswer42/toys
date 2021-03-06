package be.vdab.toys.domain;
/**
 * @author Mulangu C
 */

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
@NamedEntityGraph(name = Order.WITH_CUSTOMERSANDCOUNTRY,
        attributeNodes = @NamedAttributeNode(value = "customer",
                subgraph = "withCountry"),
        subgraphs = @NamedSubgraph(name = "withCountry",
                attributeNodes = @NamedAttributeNode("country")))

public class Order {
    public static final String WITH_CUSTOMERSANDCOUNTRY = "Order.withCustomersAndCountry";
    public static final String WITH_CUSTOMERSANDCOUNTRYANDORDERDATAILS = "Order.withCustomersAndCountryAndOrderdetails";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private LocalDate ordered;

    @NotNull
    private LocalDate required;

    private LocalDate shipped;

    @Lob
    private String comments;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private Status status;

    @Version
    private long version;


    @OneToMany(mappedBy = "orderdetailsId.order", cascade = CascadeType.ALL)
    @OrderBy("orderdetailsId.product.id")
    private Set<Orderdetail> orderdetails = new LinkedHashSet<>();

    //--
    protected Order() {
    }

    public Order(@NotNull LocalDate ordered, @NotNull LocalDate required, LocalDate shipped, String comments, Customer customer, @NotBlank Status status, Set<Orderdetail> orderdetails) {
        this.ordered = ordered;
        this.required = required;
        setShipped(shipped);
        this.comments = comments;
        this.customer = customer;
        setStatus(status);
        this.orderdetails = orderdetails;
    }

    //--


    public long getId() {
        return id;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public LocalDate getRequired() {
        return required;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Status getStatus() {
        return status;
    }

    public Set<Orderdetail> getOrderdetails() {
        return orderdetails;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setShipped(LocalDate shipped) {
        this.shipped = shipped;
    }

    public BigDecimal orderTotalPrice() {
        return this.getOrderdetails().stream().map(productsPrices -> productsPrices.productTotalPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id &&
                ordered.equals(order.ordered) &&
                required.equals(order.required) &&
                Objects.equals(shipped, order.shipped) &&
                Objects.equals(customer, order.customer) &&
                status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordered, required, shipped, customer, status);
    }

}
